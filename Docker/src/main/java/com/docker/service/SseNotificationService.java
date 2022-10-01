package com.docker.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.docker.mapper.EventMapper;
import com.docker.model.EventDto;
import com.docker.repository.EmitterRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SseNotificationService implements NotificationService {

  private final EmitterRepository emitterRepository;
  private final EventMapper eventMapper;

  @Async
  @Override
  public void sendNotification(String memberId, EventDto event, long timeout) {
    if (event == null) {
      log.info("No server event to send to device.");
      return;
    }
    try {
      TimeUnit.SECONDS.sleep(timeout);
    } catch (InterruptedException ie) {
      Thread.currentThread().interrupt();
    }
    doSendNotification(memberId, event);
  }

  private void doSendNotification(String memberId, EventDto event) {
    emitterRepository.get(memberId).ifPresentOrElse(sseEmitter -> {
      try {
        log.info("Sending event: {} for member: {}", event, memberId);
        sseEmitter.send(eventMapper.toSseEventBuilder(event));
      } catch (IOException | IllegalStateException e) {
        log.info("Error while sending event: {} for member: {} - exception: {}", event, memberId, e);
        emitterRepository.remove(memberId);
      }
    }, () -> log.info("No emitter for member {}", memberId));
  }

}

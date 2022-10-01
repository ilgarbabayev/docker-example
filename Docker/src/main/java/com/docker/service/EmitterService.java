package com.docker.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.docker.repository.EmitterRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmitterService {
  private static final long eventsTimeout = 30000L;
  private final EmitterRepository repository;

  public SseEmitter createEmitter(String memberId) {
    SseEmitter emitter = new SseEmitter(eventsTimeout);
    emitter.onCompletion(() -> repository.remove(memberId));
    emitter.onTimeout(() -> repository.remove(memberId));
    emitter.onError(e -> {
      log.error("Create SseEmitter exception", e);
      repository.remove(memberId);
    });
    repository.addOrReplaceEmitter(memberId, emitter);
    return emitter;
  }
}

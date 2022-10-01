package com.docker.repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Repository
public class InMemoryEmitterRepository implements EmitterRepository{
  private final Map<String, SseEmitter> userEmitterMap = new ConcurrentHashMap<>();

  @Override
  public void addOrReplaceEmitter(String memberId, SseEmitter emitter) {
    log.info("Adding emitter for member: {}", memberId);
    userEmitterMap.put(memberId, emitter);
  }

  @Override
  public void remove(String memberId) {
    if (userEmitterMap.containsKey(memberId)) {
      log.info("Removing emitter for member: {}", memberId);
      userEmitterMap.remove(memberId);
    } else {
      log.info("No emitter to remove for member: {}", memberId);
    }
  }

  @Override
  public Optional<SseEmitter> get(String memberId) {
    return Optional.ofNullable(userEmitterMap.get(memberId));
  }
}

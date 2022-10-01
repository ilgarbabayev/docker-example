package com.docker.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.docker.model.EventDto;
import com.docker.model.ReqModel;
import com.docker.service.EmitterService;
import com.docker.service.NotificationService;
import com.docker.service.RegModelService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/event")
public class EventController {

  private final EmitterService emitterService;
  private final NotificationService notificationService;
  private final RegModelService service;

  @GetMapping
  public SseEmitter subscribeToEvents() {
    log.info("Subscribing member with id {}", "123");
    return emitterService.createEmitter("123");
  }

  @GetMapping("/message/{memberId}")
  public List<ReqModel> publishEvent(@PathVariable String memberId) {
    sendMessages(memberId);
    return service.getAllModels();
  }

  private void sendMessages(String memberId) {
    var event01 = EventDto.builder()
                        .type("message")
                        .body(Map.of("type", "success", "message", "Proposal is Sent"))
                        .build();

    log.info("Publishing event {} for member with id {}", event01, memberId);
    notificationService.sendNotification(memberId, event01, 5);

    var event02 = EventDto.builder()
                        .type("message")
                        .body(Map.of("type", "danger", "message", "Email is Not Sent due to error"))
                        .build();

    log.info("Publishing event {} for member with id {}", event02, memberId);
    notificationService.sendNotification(memberId, event02, 8);

    var event03 = EventDto.builder()
                        .type("message")
                        .body(Map.of("type", "success", "message", "Deal is activated"))
                        .build();

    log.info("Publishing event {} for member with id {}", event03, memberId);
    notificationService.sendNotification(memberId, event03, 10);
  }
}

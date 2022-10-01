package com.docker.mapper;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.docker.model.EventDto;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@AllArgsConstructor
@Component
public class EventMapper {

  public SseEmitter.SseEventBuilder toSseEventBuilder(EventDto event) {
    return SseEmitter.event()
                     .name(event.getType())
                     .data(event.getBody());
  }
}

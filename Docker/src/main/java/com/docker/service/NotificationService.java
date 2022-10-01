package com.docker.service;

import com.docker.model.EventDto;

public interface NotificationService {
  void sendNotification(String memberId, EventDto event, long timeout);
}

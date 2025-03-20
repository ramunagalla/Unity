package com.unity.payment_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "notification-service", path = "/notifications")
public interface NotificationServiceClient {
    
    @GetMapping("/save")
    void saveNotification(@RequestParam Long userId, @RequestParam String message,
    @RequestParam String type);
}

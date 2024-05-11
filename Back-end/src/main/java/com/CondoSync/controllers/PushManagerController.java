package com.CondoSync.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CondoSync.models.DTOs.UserSubscriptionDTO;
import com.CondoSync.services.UserSubscriptionService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/v1/push")
@Validated
public class PushManagerController {

    @Autowired
    private UserSubscriptionService userSubscriptionService;

    @Value("${notify.public.key}")
    private String publicKey;

    @Value("${notify.private.key}")
    private String privateKey;

    @GetMapping("/public-key")
    public ResponseEntity<?> publicKey() {

        return ResponseEntity.ok(publicKey);
    }

    @GetMapping("/private-key")
    public ResponseEntity<?> privaterKey() {

        return ResponseEntity.ok(privateKey);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<?> subResponseEntity(@RequestBody @Valid UserSubscriptionDTO userSubscriptionDTO) {
        return userSubscriptionService.saveSubscription(userSubscriptionDTO);

    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<?> unsubResponseEntity(@RequestBody @Valid UserSubscriptionDTO userSubscriptionDTO) {
        return userSubscriptionService.deleteSubscription(userSubscriptionDTO);
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<?> getSubscriptions(@RequestParam @Valid UUID userId) {

        return ResponseEntity.ok(userSubscriptionService.getSubscriptions(userId));
    }

}

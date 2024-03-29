package com.ifmo.payandsave.controller;

import com.ifmo.payandsave.entity.Account;
import com.ifmo.payandsave.request.ProfileRequest;
import com.ifmo.payandsave.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal Account account) {
        return profileService.get(account);
    }

    @PostMapping
    public ResponseEntity<?> updateProfile(@AuthenticationPrincipal Account account, @RequestBody ProfileRequest profileRequest) {
        return profileService.update(account, profileRequest);
    }
}

package com.ifmo.payandsave.service;

import com.ifmo.payandsave.request.auth.LoginRequest;
import com.ifmo.payandsave.request.auth.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {

    ResponseEntity<?> authUser(LoginRequest loginRequest);

    ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest);
}

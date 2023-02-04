package com.ifmo.payandsave.service;

import com.ifmo.payandsave.entity.Account;
import com.ifmo.payandsave.entity.Passport;
import com.ifmo.payandsave.jwt.JwtUtils;
import com.ifmo.payandsave.repository.AccountRepository;
import com.ifmo.payandsave.request.auth.LoginRequest;
import com.ifmo.payandsave.request.auth.SignupRequest;
import com.ifmo.payandsave.response.JwtResponse;
import com.ifmo.payandsave.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    public ResponseEntity<?> authUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        Account userDetails = (Account) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(userDetails.getAccount_id(), jwt, userDetails.getUsername(), userDetails.getName(), userDetails.getSurname()));
    }

    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        if (accountRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Phone number already exists!"));
        }
        Passport passport = new Passport(signupRequest.getName(), signupRequest.getSurname(),
                signupRequest.getPatronymic(),signupRequest.getBirthday(), signupRequest.getSeries(),
                signupRequest.getPassport_number(), signupRequest.getRegistration(), signupRequest.getIssue_place(),
                signupRequest.getIssue_date(), signupRequest.getCode_division());

        Account account = new Account(signupRequest.getUsername(), passwordEncoder.encode(signupRequest.getPassword()), signupRequest.getMail(), passport);
        account.getPassport().setAccount(account);

        accountRepository.save(account);
        return ResponseEntity.ok(new MessageResponse("Phone number registered!"));
    }
}

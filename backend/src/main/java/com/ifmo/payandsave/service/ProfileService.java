package com.ifmo.payandsave.service;

import com.ifmo.payandsave.entity.Account;
import com.ifmo.payandsave.request.ProfileRequest;
import org.springframework.http.ResponseEntity;

public interface ProfileService {

    ResponseEntity<?> get(Account account);

    ResponseEntity<?> update(Account account, ProfileRequest profileRequest);
}

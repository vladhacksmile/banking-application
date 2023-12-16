package com.ifmo.payandsave.service.impl;

import com.ifmo.payandsave.entity.Account;
import com.ifmo.payandsave.repository.AccountRepository;
import com.ifmo.payandsave.request.ProfileRequest;
import com.ifmo.payandsave.response.MessageResponse;
import com.ifmo.payandsave.response.ProfileResponse;
import com.ifmo.payandsave.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public ResponseEntity<?> get(Account account) {
        ProfileResponse profileResponse = new ProfileResponse(account.getAccount_id(), account.getUsername(),
                account.getName(), account.getSurname(),
                account.isUseCashback(), account.isEvenDistribution(), account.isPercentageOnBalance());
        return ResponseEntity.ok(profileResponse);
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(Account account, ProfileRequest profileRequest) {
        boolean needToUpdate = false;
        if(!Objects.equals(account.getName(), profileRequest.getName())) {
            account.setName(profileRequest.getName());
            needToUpdate = true;
        }

        if(!Objects.equals(account.getSurname(), profileRequest.getSurname())) {
            account.setSurname(profileRequest.getSurname());
            needToUpdate = true;
        }

        if(!Objects.equals(account.isUseCashback(), profileRequest.isUseCashBack())) {
            account.setUseCashback(profileRequest.isUseCashBack());
            needToUpdate = true;
        }

        if(!Objects.equals(account.isEvenDistribution(), profileRequest.isEvenDistribution())) {
            account.setEvenDistribution(profileRequest.isEvenDistribution());
            needToUpdate = true;
        }

        if(!Objects.equals(account.isPercentageOnBalance(), profileRequest.isPercentageOnBalance())) {
            account.setPercentageOnBalance(profileRequest.isPercentageOnBalance());
            needToUpdate = true;
        }

        if(needToUpdate) {
            accountRepository.save(account);
            return ResponseEntity.ok(new MessageResponse("Profile updated!"));
        }

        return ResponseEntity.ok(new MessageResponse("Nothing to update!"));
    }
}

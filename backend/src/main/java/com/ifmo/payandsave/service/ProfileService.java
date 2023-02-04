package com.ifmo.payandsave.service;

import com.ifmo.payandsave.entity.Account;
import com.ifmo.payandsave.repository.AccountRepository;
import com.ifmo.payandsave.request.ProfileRequest;
import com.ifmo.payandsave.response.MessageResponse;
import com.ifmo.payandsave.response.ProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    AccountRepository accountRepository;

    public ResponseEntity<?> get(Account account) {
        ProfileResponse profileResponse = new ProfileResponse(account.getAccount_id(), account.getUsername(),
                account.getName(), account.getSurname(),
                account.isUseCashback(), account.isEvenDistribution(), account.isPercentageOnBalance());
        return ResponseEntity.ok(profileResponse);
    }

    public ResponseEntity<?> update(Account account, ProfileRequest profileRequest) {
        boolean needToUpdate = false;
        if(!account.getName().equals(profileRequest.getName())) {
            account.setName(profileRequest.getName());
            needToUpdate = true;
        }

        if(!account.getSurname().equals(profileRequest.getSurname())) {
            account.setSurname(profileRequest.getSurname());
            needToUpdate = true;
        }

        if(!account.isUseCashback() == profileRequest.isUseCashBack()) {
            account.setUseCashback(profileRequest.isUseCashBack());
            needToUpdate = true;
        }

        if(!account.isEvenDistribution() == profileRequest.isEvenDistribution()) {
            account.setEvenDistribution(profileRequest.isEvenDistribution());
            needToUpdate = true;
        }

        if(!account.isPercentageOnBalance() == profileRequest.isPercentageOnBalance()) {
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

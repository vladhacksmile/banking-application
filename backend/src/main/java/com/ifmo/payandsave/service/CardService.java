package com.ifmo.payandsave.service;

import com.ifmo.payandsave.entity.Account;
import com.ifmo.payandsave.entity.card.Card;
import com.ifmo.payandsave.entity.card.CardCashbackCategory;
import com.ifmo.payandsave.request.card.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CardService {

    ResponseEntity<?> add(Account account, CardRequest cardRequest);

    ResponseEntity<?> replenishCard(Account account, Card card, CardReplenishmentRequest cardReplenishmentRequest);

    ResponseEntity<?> transfer(Account account, Card card, CardToCardReplenishmentRequest cardToCardReplenishmentRequest);

    ResponseEntity<?> payByCard(Account account, Card card, PayByCardRequest payByCardRequest);

    ResponseEntity<?> cardSettings(Account account, Card card, CardSettingsRequest cardSettingsRequest);

    ResponseEntity<?> getCardSettings(Account account, Card card);

    List<CardCashbackCategory> getCashbackCategories();
}

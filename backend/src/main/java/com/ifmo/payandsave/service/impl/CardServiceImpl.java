package com.ifmo.payandsave.service.impl;

import com.ifmo.payandsave.entity.Account;
import com.ifmo.payandsave.entity.card.*;
import com.ifmo.payandsave.repository.*;
import com.ifmo.payandsave.request.card.*;
import com.ifmo.payandsave.response.CardResponse;
import com.ifmo.payandsave.response.MessageResponse;
import com.ifmo.payandsave.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardCashbackCategoryRepository cardCashbackCategoryRepository;

    @Autowired
    private CardRateRepository cardRateRepository;

    @Autowired
    private CardDesignRepository cardDesignRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    @Transactional
    public ResponseEntity<?> add(Account account, CardRequest cardRequest) {
        CardRate cardRate = cardRateRepository.findById(1L).orElse(null);
        if (cardRate == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Card rate not found!"));
        }

        CardDesign cardDesign = cardDesignRepository.findById(1L).orElse(null);
        if (cardDesign == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Card design not found!"));
        }

        Card card = new Card(cardRequest.getType(), cardRequest.getPaymentSystem(), cardRate, cardDesign, account);
        cardRepository.save(card);

        return ResponseEntity.ok(new MessageResponse("Card created!"));
    }
    @Override
    @Transactional
    public ResponseEntity<?> replenishCard(Account account, Card card, CardReplenishmentRequest cardReplenishmentRequest) {
        if(cardReplenishmentRequest.getAmount() > 0) {
            CardTransaction cardTransaction = new CardTransaction(card, "Пополнение карты", "Банковская операция",
                    cardReplenishmentRequest.getAmount());
            card.setAmount(card.getAmount() + cardReplenishmentRequest.getAmount());
            transactionRepository.save(cardTransaction);
            cardRepository.save(card);

            return ResponseEntity.ok(new MessageResponse("Card replenished"));
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Card didn't replenish! Amount must be positive!"));
    }

    @Override
    @Transactional
    public ResponseEntity<?> transfer(Account account, Card card, CardToCardReplenishmentRequest cardToCardReplenishmentRequest) {
        cardRepository.callTransfer(card.getCardNumber(), cardToCardReplenishmentRequest.getAmount(),
                cardToCardReplenishmentRequest.getNumber());

        return ResponseEntity.ok(new MessageResponse("Transfer done!"));
    }

    @Override
    @Transactional
    public ResponseEntity<?> payByCard(Account account, Card card, PayByCardRequest payByCardRequest) {
        if(card.isActive()) {
            if (card.getAmount() >= payByCardRequest.getAmount()) {
                CardTransaction cardTransaction = new CardTransaction(card, payByCardRequest.getName(),
                        payByCardRequest.getCategory(), payByCardRequest.getAmount());

                if (cardTransaction.getRoundingAmount() != 0 && card.getAmount() >=
                        cardTransaction.getAmount() + cardTransaction.getRoundingAmount()) {
                    card.setAmount(card.getAmount() - cardTransaction.getAmount() - cardTransaction.getRoundingAmount());
                } else {
                    card.setAmount(card.getAmount() - cardTransaction.getAmount());
                }

                card.setAmount((float) (card.getAmount() + cardTransaction.getCashback()));

                transactionRepository.save(cardTransaction);
                cardRepository.save(card);

                return ResponseEntity.ok(new MessageResponse("Transaction done!"));
            }
            return ResponseEntity.badRequest().body(new MessageResponse("Not enough money for transaction!"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Sorry, but card with this id blocked!"));
        }
    }

    @Override
    public ResponseEntity<?> cardSettings(Account account, Card card, CardSettingsRequest cardSettingsRequest) {
        boolean needToUpdate = false;

        if(Objects.equals(card.getCardRoundingStep(), cardSettingsRequest.getRoundingStep())) {
            needToUpdate = true;
            card.setCardRoundingStep(cardSettingsRequest.getRoundingStep());
        }

        if(Objects.equals(card.isActive(), cardSettingsRequest.isActive())) {
            needToUpdate = true;
            card.setActive(cardSettingsRequest.isActive());
        }

        if(cardSettingsRequest.getCashback1() != null) {
            CardCashbackCategory cardCashbackCategory1 = cardCashbackCategoryRepository.findById(cardSettingsRequest.getCashback1()).orElse(null);
            if (cardCashbackCategory1 == null) {
                return ResponseEntity.badRequest().body(new MessageResponse("Card cashback category 1 not found!"));
            }
            needToUpdate = true;
            card.getCashbackCategories().add(cardCashbackCategory1);
        }

        if(cardSettingsRequest.getCashback2() != null) {
            CardCashbackCategory cardCashbackCategory2 = cardCashbackCategoryRepository.findById(cardSettingsRequest.getCashback2()).orElse(null);
            if (cardCashbackCategory2 == null) {
                return ResponseEntity.badRequest().body(new MessageResponse("Card cashback category 2 not found!"));
            }
            needToUpdate = true;
            card.getCashbackCategories().add(cardCashbackCategory2);
        }

        if(cardSettingsRequest.getCashback3() != null) {
            CardCashbackCategory cardCashbackCategory3 = cardCashbackCategoryRepository.findById(cardSettingsRequest.getCashback3()).orElse(null);
            if (cardCashbackCategory3 == null) {
                return ResponseEntity.badRequest().body(new MessageResponse("Card cashback category 3 not found!"));
            }
            needToUpdate = true;
            card.getCashbackCategories().add(cardCashbackCategory3);
        }

        if(cardSettingsRequest.getCashback4() != null) {
            CardCashbackCategory cardCashbackCategory4 = cardCashbackCategoryRepository.findById(cardSettingsRequest.getCashback4()).orElse(null);
            if (cardCashbackCategory4 == null) {
                return ResponseEntity.badRequest().body(new MessageResponse("Card cashback category 4 not found!"));
            }
            needToUpdate = true;
            card.getCashbackCategories().add(cardCashbackCategory4);
        }

        if(needToUpdate) {
            String cardMessage = "Settings for card saved!";
            cardRepository.save(card);
            if(card.isActive()) {
                return ResponseEntity.ok(new MessageResponse(cardMessage + " Card is active!"));
            } else {
                return ResponseEntity.ok(new MessageResponse(cardMessage + " Card is blocking!"));
            }
        }

        return ResponseEntity.ok(new MessageResponse("Nothing to update card settings!"));
    }

    @Override
    public ResponseEntity<?> getCardSettings(Account account, Card card) {
        return ResponseEntity.ok(new CardResponse(card.isActive(), card.getCardRoundingStep()));
    }

    @Override
    public List<CardCashbackCategory> getCashbackCategories() {
        return cardCashbackCategoryRepository.findAll();
    }
}

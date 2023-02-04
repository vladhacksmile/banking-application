package com.ifmo.payandsave.service;

import com.ifmo.payandsave.entity.Account;
import com.ifmo.payandsave.entity.card.*;
import com.ifmo.payandsave.repository.*;
import com.ifmo.payandsave.request.card.*;
import com.ifmo.payandsave.response.CardResponse;
import com.ifmo.payandsave.response.MessageResponse;
import com.ifmo.payandsave.request.card.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardCashbackCategoryRepository cardCashbackCategoryRepository;

    @Autowired
    CardRateRepository cardRateRepository;

    @Autowired
    CardDesignRepository cardDesignRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public ResponseEntity<?> add(Account account, CardRequest cardRequest) {
        CardRate cardRate = cardRateRepository.findById(1L).get();
        CardDesign cardDesign = cardDesignRepository.findById(1L).get();
        Card card = new Card(cardRequest.getType(), cardRequest.getPaymentSystem(), cardRate, cardDesign, account);
        cardRepository.save(card);
        return ResponseEntity.ok(new MessageResponse("Card created!"));
    }

    @Transactional
    public ResponseEntity<?> replenishCard(Account account, Card card, CardReplenishmentRequest cardReplenishmentRequest) {
        if(cardReplenishmentRequest.getAmount() > 0) {
            CardTransaction cardTransaction = new CardTransaction(card, "Пополнение карты", "Банковская операция", cardReplenishmentRequest.getAmount());
            card.setAmount(card.getAmount() + cardReplenishmentRequest.getAmount());
            transactionRepository.save(cardTransaction);
            cardRepository.save(card);

            return ResponseEntity.ok(new MessageResponse("Card replenished"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Card didn't replenish! Amount must be positive!"));
    }

    @Transactional
    public ResponseEntity<?> transfer(Account account, Card card, CardToCardReplenishmentRequest cardToCardReplenishmentRequest) {
        cardRepository.callTransfer(card.getCardNumber(), cardToCardReplenishmentRequest.getAmount(), cardToCardReplenishmentRequest.getNumber());
        return ResponseEntity.ok(new MessageResponse("Transfer done!"));
    }

    @Transactional
    public ResponseEntity<?> payByCard(Account account, Card card, PayByCardRequest payByCardRequest) {
        if(card.isActive()) {
            if (card.getAmount() >= payByCardRequest.getAmount()) {
                CardTransaction cardTransaction = new CardTransaction(card, payByCardRequest.getName(), payByCardRequest.getCategory(), payByCardRequest.getAmount());

                if (cardTransaction.getRoundingAmount() != 0 && card.getAmount() >= cardTransaction.getAmount() + cardTransaction.getRoundingAmount()) {
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

    public ResponseEntity<?> cardSettings(Account account, Card card, CardSettingsRequest cardSettingsRequest) {
        boolean needToUpdate = false;

        if(card.getCardRoundingStep() != cardSettingsRequest.getRoundingStep()) {
            card.setCardRoundingStep(cardSettingsRequest.getRoundingStep());
            needToUpdate = true;
        }

        if(card.isActive() != cardSettingsRequest.isActive()) {
            card.setActive(cardSettingsRequest.isActive());
            needToUpdate = true;
        }

        if(cardSettingsRequest.getCashback1() != null)
            card.getCashbackCategories().add(cardCashbackCategoryRepository.findById(cardSettingsRequest.getCashback1()).get());
        if(cardSettingsRequest.getCashback2() != null)
            card.getCashbackCategories().add(cardCashbackCategoryRepository.findById(cardSettingsRequest.getCashback2()).get());
        if(cardSettingsRequest.getCashback3() != null)
            card.getCashbackCategories().add(cardCashbackCategoryRepository.findById(cardSettingsRequest.getCashback3()).get());
        if(cardSettingsRequest.getCashback4() != null)
            card.getCashbackCategories().add(cardCashbackCategoryRepository.findById(cardSettingsRequest.getCashback4()).get());
        needToUpdate = true; // TODO поправить

        if(needToUpdate) {
            cardRepository.save(card);
            if(!card.isActive()) {
                return ResponseEntity.ok(new MessageResponse("Settings for card saved! Card is blocking!"));
            } else {
                return ResponseEntity.ok(new MessageResponse("Settings for card saved! Card is active!"));
            }
        }

        return ResponseEntity.ok(new MessageResponse("Nothing to update card settings!"));
    }

    public ResponseEntity<?> getCardSettings(Account account, Card card) {
        return ResponseEntity.ok(new CardResponse(card.isActive(), card.getCardRoundingStep()));
    }

    public List<CardCashbackCategory> getCashbackCategories() {
        return cardCashbackCategoryRepository.findAll();
    }

    @Transactional
    public boolean operationByCard(Account account, Long card_id, String name, String category, Float amount) {
        Optional<Card> cardById = cardRepository.findById(card_id);
        if(cardById.isPresent()) {
            Card card = cardById.get();
            if (account.getAccount_id().equals(card.getAccount().getAccount_id())) {
                if(card.getAmount() >= amount) {
                    CardTransaction cardTransaction = new CardTransaction(card, name, category, amount);

                    transactionRepository.save(cardTransaction);
                    cardRepository.save(card);
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }
}

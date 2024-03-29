package com.ifmo.payandsave.controller;

import com.ifmo.payandsave.entity.Account;
import com.ifmo.payandsave.entity.card.Card;
import com.ifmo.payandsave.exception.CardNotFoundException;
import com.ifmo.payandsave.request.card.*;
import com.ifmo.payandsave.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<?> add(@AuthenticationPrincipal Account account, @RequestBody CardRequest cardRequest) {
        return cardService.add(account, cardRequest);
    }

    @GetMapping
    public @ResponseBody Iterable<?> getAllCards(@AuthenticationPrincipal Account account) {
        return account.getCards();
    }

    @PostMapping("/{id}/replenish")
    public ResponseEntity<?> replenishCardById(@AuthenticationPrincipal Account account, @PathVariable long id, @RequestBody CardReplenishmentRequest cardReplenishmentRequest) {
        return cardService.replenishCard(account, getCardById(account, id), cardReplenishmentRequest);
    }

    @PostMapping("/{id}/transfer")
    public ResponseEntity<?> replenishCardToCardById(@AuthenticationPrincipal Account account, @PathVariable long id, @RequestBody CardToCardReplenishmentRequest cardToCardReplenishmentRequest) {
        return cardService.transfer(account, getCardById(account, id), cardToCardReplenishmentRequest);
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<?> payByCard(@AuthenticationPrincipal Account account, @PathVariable long id, @RequestBody PayByCardRequest payByCardRequest) {
        return cardService.payByCard(account, getCardById(account, id), payByCardRequest);
    }

    @PostMapping("/{id}/settings")
    public ResponseEntity<?> cardSettings(@AuthenticationPrincipal Account account, @PathVariable long id, @RequestBody CardSettingsRequest cardSettingsRequest) {
        return cardService.cardSettings(account, getCardById(account, id), cardSettingsRequest);
    }

    @GetMapping("/{id}/settings")
    public ResponseEntity<?> cardSettings(@AuthenticationPrincipal Account account, @PathVariable long id) {
        return cardService.getCardSettings(account, getCardById(account, id));
    }

    @GetMapping("/{id}")
    public Card getCardById(@AuthenticationPrincipal Account account, @PathVariable long id) {
        return account.getCards().stream().filter(card -> card.getCard_id().equals(id)).findFirst().orElseThrow(CardNotFoundException::new);
    }

    @GetMapping("/cashback")
    public @ResponseBody Iterable<?> getCashbackCategories(@AuthenticationPrincipal Account account) {
        return cardService.getCashbackCategories();
    }
}

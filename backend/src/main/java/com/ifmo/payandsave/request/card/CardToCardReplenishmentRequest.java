package com.ifmo.payandsave.request.card;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CardToCardReplenishmentRequest {
    private float amount;
    private String number;

    public CardToCardReplenishmentRequest(float amount, String number) {
        this.number = number;
        this.amount = amount;
    }
}

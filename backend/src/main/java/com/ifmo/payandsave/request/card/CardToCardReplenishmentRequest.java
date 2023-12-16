package com.ifmo.payandsave.request.card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CardToCardReplenishmentRequest {
    private float amount;
    private String number;
}

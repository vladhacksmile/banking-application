package com.ifmo.payandsave.request.card;

import com.ifmo.payandsave.model.card.enums.CardPaymentSystem;
import com.ifmo.payandsave.model.card.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardRequest {
    private CardPaymentSystem paymentSystem;
    private CardType type;
}

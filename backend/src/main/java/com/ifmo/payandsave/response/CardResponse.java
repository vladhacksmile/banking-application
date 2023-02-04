package com.ifmo.payandsave.response;

import com.ifmo.payandsave.model.card.enums.CardRoundingStep;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CardResponse {
    private boolean active;
    private CardRoundingStep roundingStep;
}
package com.ifmo.payandsave.model.card.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardRoundingStep {
    STEP10(10), STEP50(50), STEP100(100), STEP1000(1000);

    private final int roundingStep;
}

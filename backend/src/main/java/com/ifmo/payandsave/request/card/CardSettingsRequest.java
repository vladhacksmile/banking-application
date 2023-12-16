package com.ifmo.payandsave.request.card;

import com.ifmo.payandsave.model.card.enums.CardRoundingStep;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CardSettingsRequest {
    private boolean active;
    private CardRoundingStep roundingStep;
    private Long cashback1;
    private Long cashback2;
    private Long cashback3;
    private Long cashback4;
}
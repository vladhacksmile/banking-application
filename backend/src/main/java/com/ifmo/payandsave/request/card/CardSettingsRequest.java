package com.ifmo.payandsave.request.card;

import com.ifmo.payandsave.model.card.enums.CardRoundingStep;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CardSettingsRequest {
    private boolean active;
    private CardRoundingStep roundingStep;
    private Long cashback1;
    private Long cashback2;
    private Long cashback3;
    private Long cashback4;

    @Override
    public String toString() {
        return "CardSettingsRequest{" +
                "active=" + active +
                ", roundingStep=" + roundingStep +
                ", cashback1=" + cashback1 +
                ", cashback2=" + cashback2 +
                ", cashback3=" + cashback3 +
                ", cashback4=" + cashback4 +
                '}';
    }
}
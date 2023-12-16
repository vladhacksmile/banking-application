package com.ifmo.payandsave.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileRequest {
    private String name;
    private String surname;
    private boolean isUseCashBack;
    private boolean isEvenDistribution;
    private boolean isPercentageOnBalance;
}

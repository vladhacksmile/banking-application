package com.ifmo.payandsave.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileResponse {
    private Long accountId;
    private String phoneNumber;
    private String name;
    private String surname;
    private boolean isUseCashBack;
    private boolean isEvenDistribution;
    private boolean isPercentageOnBalance;
}

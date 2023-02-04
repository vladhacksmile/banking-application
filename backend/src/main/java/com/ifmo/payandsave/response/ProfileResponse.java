package com.ifmo.payandsave.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProfileResponse {
    private Long account_id;
    private String phoneNumber;
    private String name;
    private String surname;
    private boolean isUseCashBack;
    private boolean isEvenDistribution;
    private boolean isPercentageOnBalance;
}

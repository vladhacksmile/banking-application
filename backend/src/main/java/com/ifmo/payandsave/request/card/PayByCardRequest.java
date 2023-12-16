package com.ifmo.payandsave.request.card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PayByCardRequest {
    private String name;
    private String category;
    private Float amount;
}

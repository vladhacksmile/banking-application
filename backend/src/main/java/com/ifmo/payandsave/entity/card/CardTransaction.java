package com.ifmo.payandsave.entity.card;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "card_transactions")
public class CardTransaction {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long transaction_id;
    @ManyToOne
    @JoinColumn(name = "card_id")
    @JsonIgnore
    private Card card;
    private String name;
    private String category;
    private Float amount;
    private Date date;
    private Double cashback = 0D;
    private float roundingAmount;
    private float percentageOnBalance;
    private String operationSecurityCode;

    public CardTransaction(Card card, String name, String category, Float amount) {
        this.card = card;
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.date = new Date();

        if(!category.equals("Банковская операция")) {
            Optional<CardCashbackCategory> cashbackCategory = card.getCashbackCategories().stream().filter(c -> c.getName().toUpperCase(Locale.ROOT).equals(category.toUpperCase(Locale.ROOT))).findFirst();
            this.cashback = cashbackCategory.map(cardCashbackCategory -> amount * cardCashbackCategory.getPercent() / 100).orElseGet(() -> (double) (amount / 100));
        }

        this.operationSecurityCode = String.valueOf(hashCode());
    }
}

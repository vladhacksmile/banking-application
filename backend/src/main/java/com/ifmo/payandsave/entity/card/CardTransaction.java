package com.ifmo.payandsave.entity.card;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifmo.payandsave.model.card.CardUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@NoArgsConstructor
@Getter
@Setter

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
    private float roundingAmount = 0;
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

    @Override
    public String toString() {
        return "CardTransaction{" +
                "transaction_id=" + transaction_id +
                ", card=" + card +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", cashback=" + cashback +
                ", roundingAmount=" + roundingAmount +
                '}';
    }
}

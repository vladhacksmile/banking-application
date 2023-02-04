package com.ifmo.payandsave.entity.card;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifmo.payandsave.entity.Account;
import com.ifmo.payandsave.model.card.CardUtils;
import com.ifmo.payandsave.model.card.enums.CardPaymentSystem;
import com.ifmo.payandsave.model.card.enums.CardRoundingStep;
import com.ifmo.payandsave.model.card.enums.CardType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "account_cards")
public class Card {
    @Id
    @GeneratedValue
    private Long card_id;
    @Enumerated(EnumType.ORDINAL)
    private CardType cardType;
    @Enumerated(EnumType.ORDINAL)
    private CardPaymentSystem cardPaymentSystem;
    @Enumerated(EnumType.ORDINAL)
    private CardRoundingStep cardRoundingStep = CardRoundingStep.STEP10;
    private Float amount;
    private boolean isActive;
    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;
    @ManyToOne
    @JoinColumn(name = "rate_id")
    @JsonIgnore
    private CardRate cardRate;
    @ManyToOne
    @JoinColumn(name = "design_id")
    @JsonIgnore
    private CardDesign cardDesign;
    @OneToMany(targetEntity = CardTransaction.class, mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CardTransaction> cardTransactions;
    private String cardExpiry;
    private String encryptedPan;
    private String cvv;
    private String pin;
    private String cardNumber;
    private String embossingName;
    private Long withdrawalLimit;
    private Long onlineLimit;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="join_card_cashback",
            joinColumns = { @JoinColumn (name="card_id") },
            inverseJoinColumns = { @JoinColumn (name="cashbackCategory_id") }
    )
    private Set<CardCashbackCategory> cashbackCategories = new HashSet<>();

    public Card(CardType cardType, CardPaymentSystem cardPaymentSystem, CardRate cardRate, CardDesign cardDesign, Account account) {
        this.cardType = cardType;
        this.cardPaymentSystem = cardPaymentSystem;
        this.cardRate = cardRate;
        this.cardDesign = cardDesign;
        this.amount = 0F;
        this.isActive = true;
        this.account = account;
        this.cardExpiry = "infinity";
        this.encryptedPan = String.valueOf(hashCode());
        this.cvv = String.valueOf(CardUtils.generateEncryptedPan(100, 999));
        this.cardNumber = "1000 " + CardUtils.generateEncryptedPan(1000, 9999) + " " + CardUtils.generateEncryptedPan(1000, 9999) + " " + CardUtils.generateEncryptedPan(1000, 9999);
        this.embossingName = account.getSurname().toUpperCase(Locale.ROOT) + " " + account.getName().toUpperCase(Locale.ROOT);
        this.pin = String.valueOf(CardUtils.generateEncryptedPan(1000, 9999));
    }
}

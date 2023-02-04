package com.ifmo.payandsave.entity.card;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "cardRate")
public class CardRate {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long cardRate_id;
    private String name;
    private Double rate_condition;
    private Double card_rate;
    @OneToMany(mappedBy = "cardRate")
    @JsonIgnore
    private Set<Card> cards = new HashSet<>();

    public CardRate(String name, Double rate_condition, Double card_rate) {
        this.name = name;
        this.rate_condition = rate_condition;
        this.card_rate = card_rate;
    }
}
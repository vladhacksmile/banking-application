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
    private Double rateCondition;
    private Double cardRate;
    @OneToMany(mappedBy = "cardRate")
    @JsonIgnore
    private Set<Card> cards = new HashSet<>();

    public CardRate(String name, Double rateCondition, Double cardRate) {
        this.name = name;
        this.rateCondition = rateCondition;
        this.cardRate = cardRate;
    }
}
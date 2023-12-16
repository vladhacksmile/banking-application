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
@Table(name = "cashbackCategories")
public class CardCashbackCategory {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long cashbackCategory_id;
    private String name;
    private Double percent;
    @ManyToMany(mappedBy = "cashbackCategories")
    @JsonIgnore
    private Set<Card> cards = new HashSet<>();

    public CardCashbackCategory(String name, Double percent) {
        this.name = name;
        this.percent = percent;
    }
}

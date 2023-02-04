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
@Table(name = "cardDesign")
public class CardDesign {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long cardDesign_id;
    private String name;
    private String source;
    @OneToMany(mappedBy = "cardDesign")
    @JsonIgnore
    private Set<Card> cards = new HashSet<>();

    public CardDesign(String name, String source) {
        this.name = name;
        this.source = source;
    }
}
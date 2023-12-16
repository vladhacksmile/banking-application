package com.ifmo.payandsave.request.auth;

import com.ifmo.payandsave.entity.card.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDateTime birthday;
    private Long series;
    private Long passportNumber;
    private String registration;
    private String issuePlace;
    private LocalDateTime issueDate;
    private Long codeDivision;
    private String mail;
    private List<Card> cards = new ArrayList<>();
}


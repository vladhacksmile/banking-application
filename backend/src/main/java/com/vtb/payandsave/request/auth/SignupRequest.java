package com.vtb.payandsave.request.auth;

import com.vtb.payandsave.entity.card.Card;
import com.vtb.payandsave.entity.Target;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter

public class SignupRequest {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private Date birthday;
    private Long series;
    private Long passport_number;
    private String registration;
    private String issue_place;
    private Date issue_date;
    private Long code_division;
    private String mail;
    private List<Target> targets = new ArrayList<>();
    private List<Card> cards = new ArrayList<>();
}


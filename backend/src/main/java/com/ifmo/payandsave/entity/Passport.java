package com.ifmo.payandsave.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "account_passports")
public class Passport {
    @Id
    @GeneratedValue
    private Long passport_id;
    @OneToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;
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

    public Passport(String name, String surname, String patronymic, Date birthday, Long series, Long passport_number,
                    String registration, String issue_place, Date issue_date, Long code_division) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.series = series;
        this.passport_number = passport_number;
        this.registration = registration;
        this.issue_place = issue_place;
        this.issue_date = issue_date;
        this.code_division = code_division;
    }
}

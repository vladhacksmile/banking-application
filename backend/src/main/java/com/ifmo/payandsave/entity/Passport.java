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
    private Long passportNumber;
    private String registration;
    private String issuePlace;
    private Date issueDate;
    private Long codeDivision;

    public Passport(String name, String surname, String patronymic, Date birthday, Long series, Long passportNumber,
                    String registration, String issuePlace, Date issueDate, Long codeDivision) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.series = series;
        this.passportNumber = passportNumber;
        this.registration = registration;
        this.issuePlace = issuePlace;
        this.issueDate = issueDate;
        this.codeDivision = codeDivision;
    }
}

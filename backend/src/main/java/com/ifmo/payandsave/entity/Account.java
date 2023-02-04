package com.ifmo.payandsave.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifmo.payandsave.entity.card.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "accounts",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        })
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long account_id;
    private String username;
    @JsonIgnore
    private String password;
    private String name;
    private String surname;
    private String mail;
    private Long withdrawalLimit;
    private boolean isUseCashback;
    private boolean isEvenDistribution;
    private boolean isPercentageOnBalance;
    private boolean usedService;
    @OneToMany(targetEntity = Card.class, mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Card> cards;
    @OneToOne(targetEntity = Passport.class, mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Passport passport;

    public Account(String username, String password, String mail, Passport passport) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.passport = passport;
        this.name = passport.getName();
        this.surname = passport.getSurname();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}

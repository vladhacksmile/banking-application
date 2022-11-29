package com.vtb.payandsave.repository;

import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.entity.card.CardCashbackCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardCashbackCategoryRepository extends JpaRepository<CardCashbackCategory, Long> {

}

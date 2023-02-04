package com.ifmo.payandsave.repository;

import com.ifmo.payandsave.entity.card.CardRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRateRepository extends JpaRepository<CardRate, Long> {

}

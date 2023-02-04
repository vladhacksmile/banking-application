package com.ifmo.payandsave.repository;

import com.ifmo.payandsave.entity.card.CardDesign;
import com.ifmo.payandsave.entity.card.CardRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDesignRepository extends JpaRepository<CardDesign, Long> {

}

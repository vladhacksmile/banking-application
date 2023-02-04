package com.ifmo.payandsave.repository;

import com.ifmo.payandsave.entity.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "CALL do_transfer(:req_card_number,:amount,:recipient_card_number);")
    void callTransfer(@Param("req_card_number") String req_card_number,
                        @Param("amount") float amount,
                        @Param("recipient_card_number") String recipient_card_number);
}

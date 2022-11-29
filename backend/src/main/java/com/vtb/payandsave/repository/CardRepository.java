package com.vtb.payandsave.repository;

import com.vtb.payandsave.entity.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardRepository extends JpaRepository<Card, Long> {
    @Query(nativeQuery = true, value = "Call do_operation((:req_card_number), (:amount), (:recipient_card_number))")
    String callTransfer(@Param("req_card_number") String req_card_number,
                        @Param("amount") double amount,
                        @Param("recipient_card_number") String recipient_card_number);
}

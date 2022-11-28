package com.vtb.payandsave.repository;

import com.vtb.payandsave.entity.Target;
import com.vtb.payandsave.model.target.TargetPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetRepository extends JpaRepository<Target, Long> {

}

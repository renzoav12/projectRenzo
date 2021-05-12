package com.lickvalue.change.repository;

import com.lickvalue.change.model.Vwap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface VWapRepository extends JpaRepository<Vwap, Long> {

}

package com.test.coinDesk.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.coinDesk.model.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
  public Optional<Currency> findByCode(String code); 
  public Optional<Currency> deleteByCode(String code); 
}

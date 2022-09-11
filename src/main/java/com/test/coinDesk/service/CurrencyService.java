package com.test.coinDesk.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;

import com.test.coinDesk.model.Currency;
import com.test.coinDesk.model.NewCoinDesk;

public interface CurrencyService {

  void save(Currency currency);

  List<Currency> queryAll();

  Optional<Currency> findByCode(String code);

  void delete(String code);

  boolean checkCurrency(String code);

  NewCoinDesk transCoinDesk(JSONObject json) throws Exception;
  
  void updateFromCoinDesk(JSONObject json) throws Exception;

  JSONObject readJsonFromUrl(String url) throws Exception;
}

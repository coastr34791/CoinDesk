package com.test.coinDesk.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.test.coinDesk.dao.CurrencyRepository;
import com.test.coinDesk.model.CoinDesk;
import com.test.coinDesk.model.Currency;
import com.test.coinDesk.model.NewCoinDesk;
import com.test.coinDesk.model.NewCoinDesk.Curr;
import com.test.coinDesk.service.CurrencyService;

import lombok.var;

@Service
public class CurrencyServiceImpl implements CurrencyService {

  @Autowired
  private CurrencyRepository currencyRepository;

  public void save(final Currency currency) {
    currencyRepository.save(currency);
  }

  public List<Currency> queryAll() {
    return currencyRepository.findAll();
  }

  public Optional<Currency> findByCode(final String code) {
    return currencyRepository.findByCode(code);
  }

  @Modifying     
  @Transactional
  public void delete(final String code) {
    currencyRepository.deleteByCode(code);
  }

  public boolean checkCurrency(final String code) {
    return currencyRepository.findByCode(code).isPresent();
  }
  
  @Override
  public NewCoinDesk transCoinDesk(JSONObject json) throws Exception {
    CoinDesk coinDesk = new Gson().fromJson(json.toString(), CoinDesk.class);
    SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy HH:mm:ss z", Locale.UK);
    Date update = sdf.parse(coinDesk.getTime().getUpdated());
    
    NewCoinDesk newCoinDesk = new NewCoinDesk();
    newCoinDesk.setUpdateTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(update));
    if(newCoinDesk.getCurrencies() == null) {
      newCoinDesk.setCurrencies(new ArrayList<Curr>());
    }

    coinDesk.getBpi().forEach((k, v) -> {
      var opt = findByCode(k);
      if (opt.isPresent()) {
        Curr curr = newCoinDesk.new Curr(v.getCode(), opt.get().getName(), v.getRate());
        newCoinDesk.getCurrencies().add(curr);
      }
    });
    
    return newCoinDesk;
  }

  @Override
  public void updateFromCoinDesk(JSONObject json) throws Exception {
    CoinDesk coinDesk = new Gson().fromJson(json.toString(), CoinDesk.class);
    SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy HH:mm:ss z", Locale.UK);
    LocalDateTime ldt = LocalDateTime.ofInstant(sdf.parse(coinDesk.getTime().getUpdated()).toInstant(), ZoneId.systemDefault());
    
    coinDesk.getBpi().forEach((k, v) -> {
      var opt = findByCode(k);
      if (opt.isPresent()) {
        save(new Currency(opt.get().getId(), v.getCode(), opt.get().getName(), new BigDecimal(v.getRate_float()), v.getDescription(), ldt));
      }
    });
  }

  private String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public JSONObject readJsonFromUrl(String url) throws Exception {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }
}

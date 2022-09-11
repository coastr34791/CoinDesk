package com.test.coinDesk;

import java.math.BigDecimal;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.test.coinDesk.controller.CoinDeskController;
import com.test.coinDesk.controller.CurrencyController;
import com.test.coinDesk.dto.CurrencyDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CoinDeskApplicationTests {
  @Autowired
  CurrencyController currencyController;
  @Autowired
  CoinDeskController coinDeskController;

  @Test
  void contextLoads() throws Exception {
    testQuery();
    testQueryByCode();
    testCreate();
    testUpdate();
    testDelete();
    System.out.println("CoinDesk API ---------------------------------------------------");
    testCallCoinDesk();
    testTransCoinDesk();
    testUpdateFromCoinDeskAPI();
  }
  
  private void testQuery() throws Exception {
    System.out.println("testQuery ===============================");
    System.out.println("All : " + currencyController.queryAll());
    System.out.println("=====================================");
  }

  private void testQueryByCode() throws Exception {
    System.out.println("testQueryByCode ===============================");
    System.out.println("USD : "+currencyController.query("USD"));
    System.out.println("=====================================");
  }

  private void testCreate() throws Exception {
    System.out.println("testCreate ===============================");
    CurrencyDTO dto = new CurrencyDTO();
    dto.setCode("TWD");
    dto.setDescription("Unit test create TWD");
    dto.setName("台幣");
    dto.setRate(new BigDecimal(650000.5555));
    currencyController.create(dto);
    System.out.println("All : " + currencyController.queryAll());
    System.out.println("=====================================");
  }

  private void testUpdate() throws Exception {
    System.out.println("testUpdate ===============================");
    CurrencyDTO dto = new CurrencyDTO();
    dto.setCode("TWD");
    dto.setDescription("Unit test update TWD");
    dto.setName("台幣 UPDATE");
    dto.setRate(new BigDecimal(651111.5555));
    currencyController.update(dto);
    System.out.println("TWD : "+currencyController.query("TWD"));
    System.out.println("=====================================");
  }

  private void testDelete() throws Exception {
    System.out.println("testDelete ===============================");
    currencyController.delete("TWD");
    System.out.println("All : " + currencyController.queryAll());
    System.out.println("=====================================");
  }
  
  private void testCallCoinDesk() throws Exception {
    System.out.println("testCallCoinDesk ===============================");
    System.out.println("CoinDesk API : " + coinDeskController.call());
    System.out.println("=====================================");
  }
  
  private void testTransCoinDesk() throws Exception {
    System.out.println("testTransCoinDesk ===============================");
    System.out.println("CoinDesk API : " + coinDeskController.trans());
    System.out.println("=====================================");
  }
  
  private void testUpdateFromCoinDeskAPI() throws Exception {
    System.out.println("updateFromCoinDeskAPI ===============================");
    System.out.println("CoinDesk API : " + coinDeskController.updateFromCoinDeskAPI());
    System.out.println("=====================================");
  }


}

package com.test.coinDesk.controller;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.coinDesk.service.CurrencyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(tags = "CoinDesk API")
@RestController
@RequestMapping(value = "/api/coindesk")
public class CoinDeskController {
  final static String URL = "https://api.coindesk.com/v1/bpi/currentprice.json";
  
  @Autowired
  private CurrencyService currencyService;

  @ApiOperation(value = "call CoinDesk API", notes = "call CoinDesk API")
  @GetMapping("/call")
  public ResponseEntity<?> call() throws JSONException, IOException {
    try {
      JSONObject json = currencyService.readJsonFromUrl(URL);
      return ResponseEntity.ok().body(json.toString());
    }catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Update Fial");
    }
  }

  @ApiOperation(value = "call CoinDesk API 資料轉換", notes = "將 CoinDesk API 資料更新回 DB")
  @GetMapping("/trans")
  public ResponseEntity<?> trans() throws Exception {
    try {
      JSONObject json = currencyService.readJsonFromUrl(URL);
      return ResponseEntity.ok().body(currencyService.transCoinDesk(json));
    }catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Update Fial");
    }
  }
  
  @ApiOperation(value = "call CoinDesk API 資料轉換", notes = "將 CoinDesk API 資料更新回 DB")
  @PostMapping("/update")
  public ResponseEntity<?> updateFromCoinDeskAPI() throws Exception {
    try {
      JSONObject json = currencyService.readJsonFromUrl(URL);
      currencyService.updateFromCoinDesk(json);      
      return ResponseEntity.ok().body(currencyService.queryAll());
    }catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Update Fial");
    }
  }
  
}
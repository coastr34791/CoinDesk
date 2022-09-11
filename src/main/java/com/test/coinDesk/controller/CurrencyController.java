package com.test.coinDesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.coinDesk.dto.CurrencyDTO;
import com.test.coinDesk.model.Currency;
import com.test.coinDesk.service.CurrencyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.var;

@Api(tags = "Currency")
@RestController
@RequestMapping(value = "/api/currency")
public class CurrencyController {

  @Autowired
  private CurrencyService currencyService;

  @ApiOperation(value = "新增貨幣", notes = "新增貨幣")
  @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody CurrencyDTO currencyDTO) {
    var opt = currencyService.findByCode(currencyDTO.getCode());
    if (!opt.isPresent()) {
      currencyService.save(new Currency(null, currencyDTO));
    } else {
      return ResponseEntity.ok("Date already exists.");
    }
    return ResponseEntity.ok("Create successfully!");
  }

  @ApiOperation(value = "取得全部貨幣資訊", notes = "取得全部貨幣資訊")
  @GetMapping("/query")
  public ResponseEntity<?> queryAll() {
    return ResponseEntity.ok().body(currencyService.queryAll());
  }

  @ApiOperation(value = "取得貨幣資訊", notes = "取得貨幣資訊")
  @GetMapping("/query/{code}")
  public ResponseEntity<?> query(@PathVariable String code) {
    var opt = currencyService.findByCode(code);
    if (opt.isPresent()) {
      return ResponseEntity.ok().body(opt.get());
    } else {
      return ResponseEntity.ok("Date not exists.");
    }
  }

  @ApiOperation(value = "更新貨幣資訊", notes = "更新貨幣資訊")
  @PutMapping("/update")
  public ResponseEntity<?> update(@RequestBody CurrencyDTO currencyDTO) {
    var opt = currencyService.findByCode(currencyDTO.getCode());
    if (opt.isPresent()) {
      currencyService.save(new Currency(opt.get().getId(), currencyDTO));
    } else {
      return ResponseEntity.ok("Date not exists.");
    }
    return ResponseEntity.ok().body(new Currency(opt.get().getId(), currencyDTO));
  }

  @ApiOperation(value = "移除貨幣", notes = "移除貨幣")
  @DeleteMapping("/delete/{code}")
  public ResponseEntity<?> delete(@PathVariable String code) {
    if (currencyService.checkCurrency(code)) {
      currencyService.delete(code);
    } else {
      return ResponseEntity.ok("Date not exists.");
    }
    return ResponseEntity.ok("Ssuccessfully deleted!");
  }

}
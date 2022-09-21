package com.test.coinDesk.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class NewCoinDesk {

  @Getter
  @Setter
  private String updateTime;
  @Getter
  @Setter
  private List<Curr> currencies;
  
  @AllArgsConstructor
  public class Curr {
    @Getter
    @Setter
    String code;
    @Getter
    @Setter
    String name;
    @Getter
    @Setter
    String rate;

  }
}
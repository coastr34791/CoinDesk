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
//    public Curr(String code, String name, String rate) {
//      this.code = code;
//      this.name = name;
//      this.rate = rate;
//    }
//    public Curr(String code2, String name2, String rate2) {
//      // TODO Auto-generated constructor stub
//    }
  }
}
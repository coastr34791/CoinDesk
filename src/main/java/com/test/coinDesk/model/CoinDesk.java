package com.test.coinDesk.model;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

public class CoinDesk {
  @Getter
  @Setter
  private Time time;
  @Getter
  @Setter
  private String disclaimer;
  @Getter
  @Setter
  private String chartName;
  @Getter
  @Setter
  private Map<String, CoinDeskCurr> bpi;

  public class Time {
    @Getter
    @Setter
    String updated;
    @Getter
    @Setter
    String updatedISO;
    @Getter
    @Setter
    String updateduk;
  }

  public class CoinDeskCurr {
    @Getter
    @Setter
    String code;
    @Getter
    @Setter
    String symbol;
    @Getter
    @Setter
    String rate;
    @Getter
    @Setter
    String description;
    @Getter
    @Setter
    String rate_float;
  }
}
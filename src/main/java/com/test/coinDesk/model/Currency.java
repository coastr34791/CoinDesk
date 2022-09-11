package com.test.coinDesk.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.test.coinDesk.dto.CurrencyDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private Integer id;
  @Getter
  @Setter
  private String code;
  @Getter
  @Setter
  private String name;
  @Getter
  @Setter
  private BigDecimal rate;
  @Getter
  @Setter
  private String description;
  @Getter
  @Setter
  private LocalDateTime lastupdate;

  public Currency(Integer id, CurrencyDTO dto) {
    this.id = id;
    this.code = dto.getCode();
    this.name = dto.getName();
    this.rate = dto.getRate();
    this.description = dto.getDescription();
    this.lastupdate = LocalDateTime.now();
  }
//  public Currency(Integer id, String code, String name, BigDecimal rate, String description, LocalDateTime lastupdate) {
//    this.id = id;
//    this.code = code;
//    this.name = name;
//    this.rate = rate;
//    this.description = description;
//    this.lastupdate = lastupdate;
//  }
}
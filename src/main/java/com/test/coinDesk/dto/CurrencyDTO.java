package com.test.coinDesk.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@ApiModel(description = "幣別資訊")
public class CurrencyDTO {
    
    @Getter
    @Setter
    @ApiModelProperty(value = "貨幣代碼", required = true)
    private String code;
    
    @Getter
    @Setter
    @ApiModelProperty(value = "貨幣名稱", required = true)
    private String name;
    
    @Getter
    @Setter
    @ApiModelProperty(value = "貨幣對比特幣匯率", required = true)
    private BigDecimal rate;
    
    @Getter
    @Setter
    @ApiModelProperty(value = "貨幣簡敘", required = true)
    private String description;

}

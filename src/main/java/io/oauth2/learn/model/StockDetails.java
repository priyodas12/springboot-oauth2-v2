package io.oauth2.learn.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StockDetails
{
    private String stockName;
    private String stockPrice;
    private String stockBusinessEmail;

}

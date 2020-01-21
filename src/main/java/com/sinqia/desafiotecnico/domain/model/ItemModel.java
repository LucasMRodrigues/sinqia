package com.sinqia.desafiotecnico.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.math.MathContext;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemModel {

    private Long itemId;
    private Long itemQuantity;
    private Double itemValue;

    public BigDecimal getItemTotalValue() {
        return new BigDecimal(itemQuantity * itemValue, MathContext.DECIMAL64);
    }
}

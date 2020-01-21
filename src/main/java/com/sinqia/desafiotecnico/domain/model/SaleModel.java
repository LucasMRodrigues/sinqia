package com.sinqia.desafiotecnico.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleModel {

    private Long id;
    private List<ItemModel> itemList;
    private String salesmanName;

    public BigDecimal getTotalValue() {

        return itemList
                .stream()
                .filter(Objects::nonNull)
                .map(ItemModel::getItemTotalValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

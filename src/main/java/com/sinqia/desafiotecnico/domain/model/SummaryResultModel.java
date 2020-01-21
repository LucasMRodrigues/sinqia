package com.sinqia.desafiotecnico.domain.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryResultModel {

    private Long starterNumberOfClients;
    private Long starterNumberOfSellers;
    private Long mostExpensiveSellId;
    private String worstSellerName;

    public Long increaseNumberOfSellers() {
        return ++this.starterNumberOfSellers;
    }

    public Long increaseNumberOfClients() {
        return ++this.starterNumberOfClients;
    }
}

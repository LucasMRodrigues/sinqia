package com.sinqia.desafiotecnico.factory;

import com.sinqia.desafiotecnico.domain.model.SaleModel;
import org.apache.commons.lang3.StringUtils;

import static com.sinqia.desafiotecnico.factory.ItemFactory.*;

public class SaleFactory {

    public static SaleModel mapToModel(String saleString, String separatorPattern) {

        return SaleModel.builder()
                .id(extractId(saleString, separatorPattern))
                .itemList(mapToModelList(saleString.substring(saleString.indexOf("[") + 1, saleString.indexOf("]"))))
                .salesmanName(extractSalesmanName(saleString, separatorPattern))
                .build();
    }

    private static Long extractId(String saleString, String separatorPattern) {
        return Long.parseLong(saleString.substring(saleString.indexOf(separatorPattern) + 1,
                StringUtils.ordinalIndexOf(saleString, separatorPattern, 2)));
    }

    private static String extractSalesmanName(String saleString, String separatorPattern) {
        return saleString.substring(StringUtils.ordinalIndexOf(saleString, separatorPattern, 3) + 1);
    }

}

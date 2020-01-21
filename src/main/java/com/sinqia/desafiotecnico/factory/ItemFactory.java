package com.sinqia.desafiotecnico.factory;

import com.sinqia.desafiotecnico.domain.model.ItemModel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ItemFactory {

    public static List<ItemModel> mapToModelList(String saleString) {
        return Stream.of(saleString.split(","))
                .map(ItemFactory::mapToModel)
                .collect(Collectors.toList());
    }

    public static ItemModel mapToModel(String saleString) {

        String[] data = saleString.split("-");

        return ItemModel
                .builder()
                .itemId(Long.valueOf(data[0]))
                .itemQuantity(Long.valueOf(data[1]))
                .itemValue(Double.valueOf(data[2]))
                .build();

    }
}

package com.example.demo.clr.bootstrap;

import com.example.demo.beans.Item;
import com.example.demo.beans.ItemType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ItemFactory {

    private static int COUNT = 1;


    public static Item generate() {


        return Item.builder()
                .itemType(generateItemType())
                .name(String.format("Item %d", COUNT++))
                .price(generatePrice())
                .build();

    }

    private static ItemType generateItemType() {
        int val = (int) (Math.random() * ItemType.values().length);
        return ItemType.values()[val];
    }

    private static BigDecimal generatePrice() {
        double price = Math.random() * 101;
        return BigDecimal.valueOf(price);
    }


    public static List<Item> generateItems() {
        return Arrays.asList(generate(), generate(), generate(), generate(), generate(), generate(), generate(), generate(), generate(), generate());
    }
}

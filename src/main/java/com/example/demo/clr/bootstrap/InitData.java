package com.example.demo.clr.bootstrap;

import com.example.demo.beans.Item;
import com.example.demo.beans.ItemType;
import com.example.demo.repos.ItemRepository;
import com.example.demo.utils.AppArtUtils;
import com.example.demo.utils.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Order(1)
public class InitData implements CommandLineRunner {

    private final ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.print(AppArtUtils.ROUND_1_BOOTSTRAP);

        for (int i = 0; i < 10; i++) {
            Item item = Item.builder()
                    .itemType(ItemType.SPORTS)
                    .name("Number" + i)
                    .price(BigDecimal.valueOf(100 + i)).build();

            itemRepository.save(item);
        }

        TablePrinter.print(itemRepository.findAll());
    }
}

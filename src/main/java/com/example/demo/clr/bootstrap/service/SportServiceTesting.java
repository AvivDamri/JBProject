package com.example.demo.clr.bootstrap.service;

import com.example.demo.beans.Item;
import com.example.demo.beans.ItemType;
import com.example.demo.clr.bootstrap.ItemFactory;
import com.example.demo.repos.ItemRepository;
import com.example.demo.service.Interface.SportService;
import com.example.demo.utils.AppArtUtils;
import com.example.demo.utils.TablePrinter;
import com.example.demo.utils.TestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Order(2)
public class SportServiceTesting implements CommandLineRunner {

    private final ItemRepository itemRepository;
    private final SportService sportService;

    @Override
    public void run(String... args) throws Exception {
        System.out.print(AppArtUtils.ROUND_2_SERVICE_TESTING);

        System.out.println("sport service - get all items");
        TablePrinter.print(sportService.getAllItems());
        Thread.sleep(500);

        TestUtils.printTestInfo("sport service - cannot get an item outside your domain");
        try {
            Item toAdd = ItemFactory.generate();
            toAdd.setItemType(ItemType.FOOD);
            sportService.saveItem(toAdd);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(500);

        TestUtils.printTestInfo("sport service - add sport item");
        Item toAdd = ItemFactory.generate();
        toAdd.setItemType(ItemType.SPORTS);
        System.out.println("Before :" + sportService.count());
        sportService.saveItem(toAdd);
        System.out.println("After :" + sportService.count());
        TablePrinter.print(sportService.getAllItems());
        Thread.sleep(500);

        TestUtils.printTestInfo("sport service - cannot update item outside of domain");
        Item toAddNow = ItemFactory.generate();
        toAddNow.setItemType(ItemType.FOOD);
        toAddNow.setId(2L);
//        itemRepository.save(toAddNow);
        try {
            Item toUpdate = itemRepository.findFirstByOrderByIdDesc();
            toUpdate.setPrice(BigDecimal.valueOf(17.80));
            sportService.updateItem(toAddNow);
            TablePrinter.print(sportService.getAllItems());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(500);

        TestUtils.printTestInfo("sport service - cannot update not existing id");
        try {
            Item toUpdate = itemRepository.findFirstByOrderByIdDesc();
            toUpdate.setId(Long.valueOf(101));
            sportService.updateItem(toUpdate);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(500);

        TestUtils.printTestInfo("sport service - update sport item");
        try {
            Item toUpdate = itemRepository.findFirstByOrderByIdDesc();
            System.out.println(toUpdate.getId());
            toUpdate.setPrice(BigDecimal.valueOf(44.44));
            System.out.println(toUpdate.getPrice());
            sportService.updateItem(toUpdate);
            TablePrinter.print(sportService.getAllItems());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(500);

        TestUtils.printTestInfo("sport service - count all sport items");
        TablePrinter.print(sportService.getAllItems());
        System.out.println(sportService.count());
        Thread.sleep(500);

        TestUtils.printTestInfo("sport service - cannot get item by id not exist");
        try {
            System.out.println(sportService.getOneItemById(101L));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(500);

        TestUtils.printTestInfo("sport service - cannot get item by id when type is not belong to the domain");
        Item toAddNow2 = ItemFactory.generate();
        toAddNow2.setItemType(ItemType.FOOD);
        itemRepository.save(toAddNow2);
        try {
            System.out.println(sportService.getOneItemById(itemRepository.findFirstByOrderByIdDesc().getId()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(500);

        TestUtils.printTestInfo("sport service - get one");
        Long id = itemRepository.findFirstByItemType(ItemType.SPORTS.name()).getId();
        System.out.println("the Id is: " + id);
        TablePrinter.print(sportService.getOneItemById(id));
    }
}

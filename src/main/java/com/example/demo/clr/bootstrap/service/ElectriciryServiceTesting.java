package com.example.demo.clr.bootstrap.service;

import com.example.demo.beans.Item;
import com.example.demo.beans.ItemType;
import com.example.demo.clr.bootstrap.ItemFactory;
import com.example.demo.repos.ItemRepository;
import com.example.demo.service.Interface.ElectricityService;
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
@Order(3)
class ElectriciryServiceTesting implements CommandLineRunner {

    private final ItemRepository itemRepository;
    private final ElectricityService electricityService;

    @Override
    public void run(String... args) throws Exception {
        System.out.print(AppArtUtils.ROUND_3_SERVICE_TESTING);

        System.out.println("electricity service - get all items");
        TablePrinter.print(electricityService.getAllItems());
        Thread.sleep(500);

        TestUtils.printTestInfo("electricity service - cannot get an item outside your domain");
        try {
            Item toAdd = ItemFactory.generate();
            toAdd.setItemType(ItemType.FOOD);
            electricityService.saveItem(toAdd);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(500);

        TestUtils.printTestInfo("electricity service - add electricity item");
        Item toAdd = ItemFactory.generate();
        toAdd.setItemType(ItemType.ELECTRICITY);
        System.out.println("Before :" + electricityService.count());
        electricityService.saveItem(toAdd);
        System.out.println("After :" + electricityService.count());
        TablePrinter.print(electricityService.getAllItems());
        Thread.sleep(500);

        TestUtils.printTestInfo("electricity service - cannot update item outside of domain");
        Item toAddNow = ItemFactory.generate();
        toAddNow.setItemType(ItemType.FOOD);
        toAddNow.setId(2L);
//        itemRepository.save(toAddNow);
        try {
            Item toUpdate = itemRepository.findFirstByOrderByIdDesc();
            toUpdate.setPrice(BigDecimal.valueOf(17.80));
            electricityService.updateItem(toAddNow);
            TablePrinter.print(electricityService.getAllItems());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(500);

        TestUtils.printTestInfo("electricity service - cannot update not existing id");
        try {
            Item toUpdate = itemRepository.findFirstByOrderByIdDesc();
            toUpdate.setId(Long.valueOf(101));
            electricityService.updateItem(toUpdate);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(500);

        TestUtils.printTestInfo("electricity service - update electricity item");
        try {
            Item toUpdate = itemRepository.findFirstByOrderByIdDesc();
            System.out.println(toUpdate.getId());
            toUpdate.setPrice(BigDecimal.valueOf(55.55));
            System.out.println(toUpdate.getPrice());
            electricityService.updateItem(toUpdate);
            TablePrinter.print(electricityService.getAllItems());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(500);

        TestUtils.printTestInfo("electricity service - count all electricity items");
        TablePrinter.print(electricityService.getAllItems());
        System.out.println(electricityService.count());
        Thread.sleep(500);

        TestUtils.printTestInfo("electricity service - cannot get item by id not exist");
        try {
            System.out.println(electricityService.getOneItemById(101L));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(500);

        TestUtils.printTestInfo("electricity service - cannot get item by id when type is not belong to the domain");
        Item toAddNow2 = ItemFactory.generate();
        toAddNow2.setItemType(ItemType.FOOD);
        itemRepository.save(toAddNow2);
        try {
            System.out.println(electricityService.getOneItemById(itemRepository.findFirstByOrderByIdDesc().getId()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(500);

        TestUtils.printTestInfo("electricity service - get one");
        Long id = itemRepository.findFirstByItemType(ItemType.ELECTRICITY.name()).getId();
        System.out.println("the Id is: " + id);
        TablePrinter.print(electricityService.getOneItemById(id));
    }
}

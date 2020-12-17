package com.example.demo.clr.bootstrap.service;

import com.example.demo.beans.Item;
import com.example.demo.beans.ItemType;
import com.example.demo.clr.bootstrap.ItemFactory;
import com.example.demo.repos.ItemRepository;
import com.example.demo.service.Interface.AdminService;
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
@Order(4)
public class AdminServiceTesting implements CommandLineRunner {
    private final ItemRepository itemRepository;
    private final AdminService adminService;

    @Override
    public void run(String... args) throws Exception {
        System.out.print(AppArtUtils.ADMIN);

        System.out.println("admin service - get all items");
        TablePrinter.print(adminService.getAllItems());
        Thread.sleep(500);

        TestUtils.printTestInfo("electricity service - add item");
        Item toAdd = ItemFactory.generate();
        toAdd.setItemType(ItemType.ELECTRICITY);
        System.out.println("Before :" + adminService.countItems());
        adminService.saveItem(toAdd);
        System.out.println("After :" + adminService.countItems());
        TablePrinter.print(adminService.getAllItems());
        Thread.sleep(500);

        TestUtils.printTestInfo("admin service - cannot update not existing id");
        try {
            Item toUpdate = itemRepository.findFirstByOrderByIdDesc();
            toUpdate.setId(Long.valueOf(101));
            adminService.updateItem(toUpdate);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(500);

        TestUtils.printTestInfo("admin service - update electricity item");
        try {
            Item toUpdate = itemRepository.findFirstByOrderByIdDesc();
            System.out.println(toUpdate.getId());
            toUpdate.setPrice(BigDecimal.valueOf(66.66));
            System.out.println(toUpdate.getPrice());
            adminService.updateItem(toUpdate);
            TablePrinter.print(adminService.getAllItems());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(500);

        TestUtils.printTestInfo("admin service - count all items");
        TablePrinter.print(adminService.getAllItems());
        System.out.println(adminService.countItems());
        Thread.sleep(500);

        TestUtils.printTestInfo("admin service - cannot get item by id not exist");
        try {
            System.out.println(adminService.getOneItemById(101L));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(500);

        TestUtils.printTestInfo("Admin service - get one");
        Long id = itemRepository.findFirstByItemType(ItemType.ELECTRICITY.name()).getId();
        System.out.println("the Id is: " + id);
        TablePrinter.print(adminService.getOneItemById(id));
    }
}

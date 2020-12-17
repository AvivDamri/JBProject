package com.example.demo.clr.controller;

import com.example.demo.beans.Item;
import com.example.demo.beans.ItemType;
import com.example.demo.dto.ItemDto;
import com.example.demo.utils.AppArtUtils;
import com.example.demo.utils.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Order(6)
public class ElectricityControllerTest implements CommandLineRunner {

    private static final String URL = "http://localhost:8080/electricity/items/";


    private final RestTemplate restTemplate;

//    There was a problem with the encoder, i used in a hardcoded encoder result from the internet
//    String userAndPass = "admin:1234";
//    private BaseAST Base64Utility;


    @Override
    public void run(String... args) {

        System.out.print(AppArtUtils.ROUND_6 + "Electricity");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic " + "YWRtaW46MTIzNA==");
        HttpEntity entity = new HttpEntity(headers);

        System.out.println("Get item by id");
        ResponseEntity<Item> response = restTemplate.exchange(URL+13, HttpMethod.GET, entity, Item.class);
        Item body = response.getBody();
        TablePrinter.print(body);

        System.out.println("Get all items");
        Object res2 = restTemplate.exchange(URL+"all-items", HttpMethod.GET, entity, Object.class);
        TablePrinter.print(res2);


        System.out.println("Add new Item");
        Item toAdd = new Item();
        toAdd.setId(1L);
        toAdd.setItemType(ItemType.ELECTRICITY);
        toAdd.setName("Moshi");
        toAdd.setPrice(BigDecimal.valueOf(5));
        HttpEntity<Item> entityPost = new HttpEntity<>(toAdd, headers);
        TablePrinter.print(entityPost);
        TablePrinter.print(restTemplate.exchange(URL, HttpMethod.POST, entityPost, String.class));


        System.out.println("Update existing Item");
        Item toUpdate = restTemplate.exchange(URL+13, HttpMethod.GET, entity, Item.class).getBody();
        toUpdate.setName("Ronaldo");
        HttpEntity<Item> entityPut = new HttpEntity<>(toUpdate, headers);
        TablePrinter.print(restTemplate.exchange(URL, HttpMethod.PUT, entityPut, String.class));
        TablePrinter.print(restTemplate.exchange(URL+"/all-items", HttpMethod.GET, entity, Object.class));


    }
}

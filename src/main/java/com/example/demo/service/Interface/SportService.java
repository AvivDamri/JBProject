package com.example.demo.service.Interface;


import com.example.demo.beans.Item;
import com.example.demo.exceptions.InvalidEntity;
import com.example.demo.exceptions.InvalidOperationException;

import java.util.List;

public interface SportService {

    void saveItem(Item item) throws InvalidEntity;
    void updateItem (Item item) throws InvalidEntity, InvalidOperationException;
    Item getOneItemById(Long id) throws InvalidEntity, InvalidOperationException;
    List<Item> getAllItems();
    long count();

}

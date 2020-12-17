package com.example.demo.service.Interface;

import com.example.demo.beans.Item;
import com.example.demo.exceptions.InvalidEntity;

import java.util.List;

public interface AdminService {

    public void saveItem(Item item);
    public void updateItem(Item item) throws InvalidEntity;
    public void deleteItem(Item item) throws InvalidEntity;
    public Item getOneItemById(Long id) throws InvalidEntity;
    public List<Item> getAllItems();
    public Long countItems();

}

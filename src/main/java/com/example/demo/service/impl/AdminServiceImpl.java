package com.example.demo.service.impl;

import com.example.demo.beans.Item;
import com.example.demo.exceptions.InvalidEntity;
import com.example.demo.repos.ItemRepository;
import com.example.demo.service.Interface.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    // DI using final + @RequiredArgsConstructor
    private final ItemRepository itemRepository;

    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public void updateItem(Item item) throws InvalidEntity {
        itemRepository.findById(item.getId()).orElseThrow(()->new InvalidEntity("Cannot update not existing id"));
        itemRepository.saveAndFlush(item);
    }

    public void deleteItem(Item item) throws InvalidEntity {
        itemRepository.findById(item.getId()).orElseThrow(()->new InvalidEntity("cannot delete - id not exist"));
        itemRepository.delete(item);
    }

    public Item getOneItemById(Long id) throws InvalidEntity {
        itemRepository.findById(id).orElseThrow(()->new InvalidEntity("Item not found"));
        return itemRepository.findById(id).get();
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
    public Long countItems() {
        return itemRepository.count();
    }
}

package com.example.demo.service.impl;

import com.example.demo.beans.Item;
import com.example.demo.beans.ItemType;
import com.example.demo.exceptions.InvalidEntity;
import com.example.demo.exceptions.InvalidOperationException;
import com.example.demo.repos.ItemRepository;
import com.example.demo.service.Interface.SportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SportServiceImpl implements SportService {

    private final ItemRepository itemRepository;

    @Override
    public void saveItem(Item item) throws InvalidEntity {
        if (item.getItemType().equals(ItemType.SPORTS)) {
            itemRepository.save(item);
        } else {
            throw new InvalidEntity("cannot add an item outside your domain");
        }
    }

    @Override
    public void updateItem(Item item) throws InvalidEntity, InvalidOperationException {
        itemRepository.findById(item.getId()).orElseThrow(()-> new InvalidEntity("cannot update not existing id"));
        itemRepository.findByIdAndItemType(item.getId(), item.getItemType()).
                orElseThrow(()-> new InvalidOperationException("cannot update an item outside your domain"));

        itemRepository.saveAndFlush(item);
    }

    @Override
    public Item getOneItemById(Long id) throws InvalidEntity, InvalidOperationException {
        itemRepository.findById(id).orElseThrow(()-> new InvalidEntity("Item not found"));

        ItemType itemType = itemRepository.findById(id).get().getItemType();
        itemRepository.findByIdAndItemType(id, itemType).
                orElseThrow(()-> new InvalidOperationException("cannot get an item outside your domain"));

        return itemRepository.getOne(id);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAllByItemType(ItemType.SPORTS);
    }

    @Override
    public long count() {
        return itemRepository.countByItemType(ItemType.SPORTS);
    }
}

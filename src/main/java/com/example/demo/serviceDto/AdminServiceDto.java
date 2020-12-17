package com.example.demo.serviceDto;


import com.example.demo.dto.ItemDto;
import com.example.demo.exceptions.InvalidEntity;
import com.example.demo.exceptions.InvalidOperationException;

import java.util.List;

public interface AdminServiceDto {

    void saveItem(ItemDto itemDto) throws InvalidOperationException, InvalidEntity;
    void updateItem(ItemDto itemDto) throws InvalidEntity, InvalidOperationException;
    void deleteItem(ItemDto itemDto) throws InvalidEntity;

    ItemDto getOneItem(Long id) throws InvalidEntity, InvalidOperationException;
    List<ItemDto> getAllItems();
    long countItems();
}

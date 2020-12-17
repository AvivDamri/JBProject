package com.example.demo.serviceDto;

import com.example.demo.repos.ItemRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BaseDtoService {
    protected final ItemRepository itemRepository;
    protected final ItemMapper itemMapper;
}
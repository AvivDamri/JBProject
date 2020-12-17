package com.example.demo.serviceDto;

import com.example.demo.beans.Item;
import com.example.demo.dto.ItemDto;
import com.example.demo.exceptions.InvalidEntity;
import com.example.demo.exceptions.InvalidOperationException;
import com.example.demo.repos.ItemRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceDtoImpl extends BaseDtoService implements AdminServiceDto{

    @Builder
    public AdminServiceDtoImpl(ItemRepository itemRepository, ItemMapper itemMapper) {
        super(itemRepository, itemMapper);
    }

    @Override
    public void saveItem(ItemDto itemDto) throws InvalidOperationException, InvalidEntity {
        Item item = this.itemMapper.itemDtoToItem(itemDto);
        itemRepository.save(item);
    }

    @Override
    public void updateItem(ItemDto itemDto) throws InvalidEntity, InvalidOperationException {
        itemRepository.findById(itemDto.getId()).orElseThrow(()-> new InvalidEntity("Item not found"));
        Item item = itemMapper.itemDtoToItem(itemDto);
        itemRepository.saveAndFlush(item);
    }

    @Override
    public void deleteItem(ItemDto itemDto) throws InvalidEntity {
        itemRepository.findById(itemDto.getId()).orElseThrow(()->new InvalidEntity("cannot delete - id not exist"));

        Item item = itemMapper.itemDtoToItem(itemDto);
        itemRepository.delete(item);
    }

    @Override
    public ItemDto getOneItem(Long id) throws InvalidEntity, InvalidOperationException {
        itemRepository.findById(id).orElseThrow(()->new InvalidEntity("Item not found"));

        ItemDto itemDto = itemMapper.itemToItemDto(itemRepository.findById(id).get());
        return itemDto;
    }

    @Override
    public List<ItemDto> getAllItems() {
        List<Item> itemList = itemRepository.findAll();

        List<ItemDto> itemDtos = itemMapper.mapItemsToItemDtos(itemRepository.findAll());
        return itemDtos;
    }

    @Override
    public long countItems() {
        return itemRepository.count();
    }
}

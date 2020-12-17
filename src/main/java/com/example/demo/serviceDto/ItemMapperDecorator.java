package com.example.demo.serviceDto;


import com.example.demo.beans.Item;
import com.example.demo.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ItemMapperDecorator implements ItemMapper {
    private ItemMapper mapper;

    @Autowired
    public void setMapper(ItemMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ItemDto itemToItemDto(Item item) {
        ItemDto itemDto = mapper.itemToItemDto(item);
        itemDto.setId(item.getId());
//        itemDto.setItemType(item.getItemType());
//        itemDto.setPrice(item.getPrice());
//        itemDto.setItemName(item.getName());
        return itemDto;
    }

    @Override
    public Item itemDtoToItem(ItemDto itemDto) {
        System.out.println(itemDto);
        Item item = mapper.itemDtoToItem(itemDto);
//        item.setItemType(itemDto.getItemType());
//        item.setPrice(itemDto.getPrice());
//        item.setName(itemDto.getItemName());
        return item;
    }

}

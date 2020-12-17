package com.example.demo.serviceDto;

import com.example.demo.beans.Item;
import com.example.demo.dto.ItemDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
@DecoratedWith(ItemMapperDecorator.class)
public interface ItemMapper {

    @Mapping(source = "item.name",target = "itemName")
    ItemDto itemToItemDto(Item item);

    @Mapping(source = "itemDto.itemName",target = "name")
    Item itemDtoToItem(ItemDto itemDto);

    List<Item> mapItemDtosToItems(List<ItemDto> itemsDto);
    List<ItemDto> mapItemsToItemDtos(List<Item> Item);
}

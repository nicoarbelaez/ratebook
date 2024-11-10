package com.nicoarbelaez.ratebook.endpoint.item.dto.mapper;

import com.nicoarbelaez.ratebook.endpoint.item.Item;
import com.nicoarbelaez.ratebook.endpoint.item.dto.ItemCreationDto;
import com.nicoarbelaez.ratebook.endpoint.item.dto.ItemResponseDto;

public class ItemMapper {
    public static Item toEntity(ItemCreationDto dto) {
        Item item = new Item();
        item.setTitle(dto.getTitle());
        item.setImageUrl(dto.getImageUrl());
        item.setDescription(dto.getDescription());
        item.setTag(dto.getTag());
        item.setType(dto.getType());
        return item;
    }

    public static ItemResponseDto toResponseDto(Item item) {
        return ItemResponseDto.builder()
                .id(item.getId())
                .title(item.getTitle())
                .imageUrl(item.getImageUrl())
                .description(item.getDescription())
                .tag(item.getTag())
                .type(item.getType())
                .build();
    }
}

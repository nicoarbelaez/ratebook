package com.nicoarbelaez.ratebook.endpoint.item.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.nicoarbelaez.ratebook.endpoint.item.enums.ItemType;

import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCreationDto {
    private String title;
    private String imageUrl;
    private String description;
    private String tag;
    private ItemType type;
}

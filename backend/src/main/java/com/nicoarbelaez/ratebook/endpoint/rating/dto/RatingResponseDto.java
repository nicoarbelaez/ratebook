package com.nicoarbelaez.ratebook.endpoint.rating.dto;

import com.nicoarbelaez.ratebook.endpoint.item.dto.ItemResponseDto;
import com.nicoarbelaez.ratebook.endpoint.user.dto.UserResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponseDto {

    private Long id;
    private UserResponseDto user;
    private ItemResponseDto item;
    private Float stars;
}

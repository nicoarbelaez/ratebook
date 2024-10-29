package com.nicoarbelaez.ratebook.endpoint.rating.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingCreationDto {
    private Long userId;
    private Long itemId;
    private Float stars;
}
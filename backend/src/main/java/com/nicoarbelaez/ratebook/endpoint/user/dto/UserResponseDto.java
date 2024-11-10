package com.nicoarbelaez.ratebook.endpoint.user.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private int reviewsCount;
    private int itemsEvaluatedCount;

    private String firstName;
    private String lastName;
    private String profileImageUrl;
    
    private LocalDate date;
}

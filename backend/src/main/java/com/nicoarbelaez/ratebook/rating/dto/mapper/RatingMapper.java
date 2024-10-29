// package com.nicoarbelaez.ratebook.rating.dto.mapper;

// import com.nicoarbelaez.ratebook.rating.Rating;
// import com.nicoarbelaez.ratebook.rating.dto.RatingCreationDto;
// import com.nicoarbelaez.ratebook.rating.dto.RatingResponseDto;
// import com.nicoarbelaez.ratebook.item.Item;
// import com.nicoarbelaez.ratebook.item.dto.ItemResponseDto;
// import com.nicoarbelaez.ratebook.user.User;
// import com.nicoarbelaez.ratebook.user.dto.UserResponseDto;

// public class RatingMapper {

//     public static Rating toEntity(RatingCreationDto dto, User user, Item item) {
//         Rating rating = new Rating();
//         rating.setUser(user);
//         rating.setItem(item);
//         rating.setStars(dto.getStars());

//         return rating;
//     }

//     public static RatingResponseDto toResponseDto(Rating rating, UserResponseDto userDto, ItemResponseDto itemDto) {
//         RatingResponseDto dto = new RatingResponseDto();
//         dto.setId(rating.getId());
//         dto.setUser(userDto);
//         dto.setItem(itemDto);
//         dto.setStars(rating.getStars());

//         return dto;
//     }
// }

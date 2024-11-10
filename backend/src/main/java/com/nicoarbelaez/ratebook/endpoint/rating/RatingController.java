// package com.nicoarbelaez.ratebook.rating;

// import java.util.Optional;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.nicoarbelaez.ratebook.item.dto.ItemResponseDto;
// import com.nicoarbelaez.ratebook.item.dto.mapper.ItemMapper;
// import com.nicoarbelaez.ratebook.rating.dto.RatingCreationDto;
// import com.nicoarbelaez.ratebook.rating.dto.RatingResponseDto;
// import com.nicoarbelaez.ratebook.rating.dto.RatingUpdateSatrtDto;
// import com.nicoarbelaez.ratebook.rating.dto.mapper.RatingMapper;
// import com.nicoarbelaez.ratebook.user.dto.UserResponseDto;
// import com.nicoarbelaez.ratebook.user.dto.mapper.UserMapper;

// import lombok.RequiredArgsConstructor;

// @RestController
// @RequiredArgsConstructor
// @RequestMapping("/api/ratings")
// public class RatingController {

//     private final RatingService ratingService;

//     @GetMapping
//     public ResponseEntity<Iterable<RatingResponseDto>> getAllRatings() {
//         return ResponseEntity.status(HttpStatus.OK)
//                 .body(ratingService.getAllRatings().stream().map(rating -> {
//                     UserResponseDto userDto = UserMapper.toDto(rating.getUser());
//                     ItemResponseDto itemDto = ItemMapper.toResponseDto(rating.getItem());
//                     return RatingMapper.toResponseDto(rating, userDto, itemDto);
//                 }).toList());
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<RatingResponseDto> getRatingById(@PathVariable Long id) {
//         Optional<Rating> rating = ratingService.getRatingById(id);
//         return rating.map(value -> {
//             RatingResponseDto responseDto = RatingMapper.toResponseDto(value, UserMapper.toDto(value.getUser()),
//                     ItemMapper.toResponseDto(value.getItem()));
//             return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//         }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//     }

//     @PostMapping
//     public ResponseEntity<RatingResponseDto> createRating(@RequestBody RatingCreationDto dto) {
//         return ratingService.createRating(dto)
//                 .map(rating -> ResponseEntity.status(HttpStatus.CREATED).body(rating))
//                 .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<RatingResponseDto> updateRatingStars(@PathVariable Long id,
//             @RequestBody RatingUpdateSatrtDto dto) {
//         return ratingService.updateRatingStars(id, dto)
//                 .map(rating -> {
//                     RatingResponseDto responseDto = RatingMapper.toResponseDto(rating,
//                             UserMapper.toDto(rating.getUser()),
//                             ItemMapper.toResponseDto(rating.getItem()));
//                     return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//                 })
//                 .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
//         return ratingService.deleteRating(id)
//                 ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
//                 : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//     }
// }

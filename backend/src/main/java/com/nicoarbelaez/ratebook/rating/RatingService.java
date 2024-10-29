// package com.nicoarbelaez.ratebook.rating;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.stereotype.Service;

// import com.nicoarbelaez.ratebook.item.Item;
// import com.nicoarbelaez.ratebook.item.ItemRepository;
// import com.nicoarbelaez.ratebook.item.dto.mapper.ItemMapper;
// import com.nicoarbelaez.ratebook.rating.dto.RatingCreationDto;
// import com.nicoarbelaez.ratebook.rating.dto.RatingResponseDto;
// import com.nicoarbelaez.ratebook.rating.dto.RatingUpdateSatrtDto;
// import com.nicoarbelaez.ratebook.rating.dto.mapper.RatingMapper;
// import com.nicoarbelaez.ratebook.user.User;
// import com.nicoarbelaez.ratebook.user.UserRepository;
// import com.nicoarbelaez.ratebook.user.dto.mapper.UserMapper;

// import jakarta.transaction.Transactional;
// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class RatingService {

//     private final RatingRepository ratingRepository;
//     private final UserRepository userRepository;
//     private final ItemRepository itemRepository;

//     public List<Rating> getAllRatings() {
//         return ratingRepository.findAll();
//     }

//     public Optional<Rating> getRatingById(Long id) {
//         return ratingRepository.findById(id);
//     }

//     @Transactional
//     public Optional<RatingResponseDto> createRating(RatingCreationDto dto) {
//         Optional<User> user = userRepository.findById(dto.getUserId());
//         Optional<Item> item = itemRepository.findById(dto.getItemId());

//         if (!(user.isPresent() && item.isPresent())) {
//             return Optional.empty();
//         }

//         if (ratingRepository.findByUserAndItem(user.get(), item.get()).isPresent()) {
//             return Optional.empty();
//         }

//         Rating rating = RatingMapper.toEntity(dto, user.get(), item.get());
//         ratingRepository.save(rating);
//         return Optional.of(
//                 RatingMapper.toResponseDto(rating, UserMapper.toDto(user.get()), ItemMapper.toResponseDto(item.get())));
//     }

//     @Transactional
//     public Optional<Rating> updateRatingStars(Long id, RatingUpdateSatrtDto dto) {
//         Optional<Rating> existingRating = ratingRepository.findById(id);
//         Float starts = dto.getStars();

//         if (!existingRating.isPresent() || starts < 1 || starts > 5) {
//             return Optional.empty();
//         }

//         existingRating.get().setStars(starts);
//         return Optional.of(ratingRepository.save(existingRating.get()));
//     }

//     @Transactional
//     public boolean deleteRating(Long id) {
//         return ratingRepository.findById(id).map(rating -> {
//             ratingRepository.delete(rating);
//             return true;
//         }).orElse(false);
//     }
// }

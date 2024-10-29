// package com.nicoarbelaez.ratebook.review;

// import org.springframework.stereotype.Service;

// import jakarta.transaction.Transactional;
// import lombok.RequiredArgsConstructor;

// import java.util.List;
// import java.util.Optional;

// @Service
// @RequiredArgsConstructor
// public class ReviewService {

//     private final ReviewRepository reviewRepository;

//     public List<Review> getAllReviews() {
//         return reviewRepository.findAll();
//     }

//     public Optional<Review> getReviewById(Long id) {
//         return reviewRepository.findById(id);
//     }

//     @Transactional
//     public Review createReview(Review review) {
//         return reviewRepository.save(review);
//     }

//     @Transactional
//     public Optional<Review> updateReview(Long id, Review updatedReview) {
//         Optional<Review> existingReview = reviewRepository.findById(id);
//         existingReview.ifPresent(review -> {
//             review.setContent(updatedReview.getContent());
//             review.setLikes(updatedReview.getLikes());
//             review.setDislikes(updatedReview.getDislikes());
//             review.setUser(updatedReview.getUser());
//             review.setItem(updatedReview.getItem());
//             reviewRepository.save(review);
//         });
//         return existingReview;
//     }

//     public boolean deleteReview(Long id) {
//         Optional<Review> review = reviewRepository.findById(id);
//         review.ifPresent(reviewRepository::delete);
//         return review.isPresent();
//     }
// }

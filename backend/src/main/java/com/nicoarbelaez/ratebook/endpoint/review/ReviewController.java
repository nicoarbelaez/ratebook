// package com.nicoarbelaez.ratebook.review;

// import org.springframework.web.bind.annotation.RestController;

// import lombok.RequiredArgsConstructor;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequiredArgsConstructor
// @RequestMapping("/reviews")
// public class ReviewController {

//     private final ReviewService reviewService;

//     @GetMapping
//     public ResponseEntity<List<Review>> getAllReviews() {
//         List<Review> reviews = reviewService.getAllReviews();
//         return ResponseEntity.status(HttpStatus.OK).body(reviews);
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
//         Optional<Review> review = reviewService.getReviewById(id);
//         if (review != null) {
//             return ResponseEntity.status(HttpStatus.OK).body(review.get());
//         } else {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//         }
//     }

//     @PostMapping
//     public ResponseEntity<Review> createReview( @RequestBody Review dto) {
//         Review createdReview = reviewService.createReview(dto);
//         return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review dto) {
//         Optional<Review> updatedReview = reviewService.updateReview(id, dto);
//         if (updatedReview != null) {
//             return ResponseEntity.status(HttpStatus.OK).body(updatedReview.get());
//         } else {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//         }
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
//         boolean deleted = reviewService.deleteReview(id);
//         if (deleted) {
//             return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//         } else {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//         }
//     }
// }

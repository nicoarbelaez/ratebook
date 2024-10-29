// package com.nicoarbelaez.ratebook.review;

// import java.time.LocalDateTime;


// import com.nicoarbelaez.ratebook.item.Item;
// import com.nicoarbelaez.ratebook.user.User;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// @Table(name = "reviews")
// public class Review {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String content;
//     private Integer likes;
//     private Integer dislikes;

//     @Builder.Default
//     private LocalDateTime created_at = LocalDateTime.now();

//     @ManyToOne
//     @JoinColumn(name = "user_id", referencedColumnName = "id")
//     private User user;

//     @ManyToOne
//     @JoinColumn(name = "item_id", referencedColumnName = "id")
//     private Item item;
// }

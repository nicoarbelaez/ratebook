// package com.nicoarbelaez.ratebook.comment;

// import java.time.LocalDateTime;

// import com.nicoarbelaez.ratebook.item.Item;
// import com.nicoarbelaez.ratebook.user.User;

// import jakarta.persistence.Column;
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
// @AllArgsConstructor
// @NoArgsConstructor
// @Builder
// @Table(name = "comments")
// public class Comment {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false)
//     private String content;

//     @Column(nullable = false)
//     @Builder.Default
//     private Integer likes = 0;

//     @Column(nullable = false)
//     @Builder.Default
//     private Integer dislikes = 0;

//     @Column(nullable = false)
//     @Builder.Default
//     private LocalDateTime createdAt = LocalDateTime.now();

//     @ManyToOne
//     @JoinColumn(name = "item_id", referencedColumnName = "id")
//     private Item item;

//     @ManyToOne
//     @JoinColumn(name = "user_id", referencedColumnName = "id")
//     private User user;
// }

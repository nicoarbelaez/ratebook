// package com.nicoarbelaez.ratebook.rating;

// import lombok.Data;
// import lombok.NoArgsConstructor;
// import lombok.AllArgsConstructor;
// import lombok.Builder;

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

// @Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// @Table(name = "ratings")
// public class Rating {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false)
//     private Float stars;

//     @ManyToOne
//     @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
//     private User user;

//     @ManyToOne
//     @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false)
//     private Item item;
// }

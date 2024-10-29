// package com.nicoarbelaez.ratebook.comment.replie;

// import java.time.LocalDateTime;

// import com.nicoarbelaez.ratebook.comment.Comment;
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
// @Table(name = "comment_replies")
// public class CommentReply {

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
//     @JoinColumn(name = "comment_id", referencedColumnName = "id")
//     private Comment comment;

//     @ManyToOne
//     @JoinColumn(name = "user_id", referencedColumnName = "id")
//     private User user;

//     @ManyToOne
//     @JoinColumn(name = "parent_reply_id", referencedColumnName = "id")
//     private CommentReply parentReply;
// }

// package com.nicoarbelaez.ratebook.rating;

// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import com.nicoarbelaez.ratebook.item.Item;
// import com.nicoarbelaez.ratebook.user.User;

// @Repository
// public interface RatingRepository extends JpaRepository<Rating, Long> {

//     Optional<Rating> findByUserAndItem(User user, Item item);
// }
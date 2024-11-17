// package com.nicoarbelaez.ratebook.user;

// import java.util.Optional;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.nicoarbelaez.ratebook.user.dto.UserResponseDto;

// import lombok.RequiredArgsConstructor;

// @RestController
// @RequiredArgsConstructor
// @RequestMapping("/users")
// @CrossOrigin(origins = "http://localhost:3000")
// public class UserController {

//     private final UserService userService;

//     @GetMapping()
//     public ResponseEntity<Iterable<UserResponseDto>> getAllUsers() {
//         return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
//         if (id == null) {
//             return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//         }

//         Optional<UserResponseDto> user = userService.getUserById(id);
//         return user.map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
//                 .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody User user) {
//         Optional<UserResponseDto> updatedUser = userService.updateUser(id, user);
//         return updatedUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteUser(@PathVariable Long id) {;
//         return userService.deleteUser(id)
//                 ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
//                 : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//     }
// }
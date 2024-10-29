// package com.nicoarbelaez.ratebook.auth;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.annotation.Rollback;

// import static org.assertj.core.api.Assertions.assertThat;

// import com.nicoarbelaez.ratebook.user.User;

// @SpringBootTest
// public class AuthRepositoryTest {

//     @Autowired
//     private AuthRepository authRepository;

//     private User user;
//     private Auth auth;

//     @BeforeEach
//     public void setUp() {
//         // Configuración inicial
//         user = User.builder()
//                 .firstName("Nicolas")
//                 .lastName("Arbelaez")
//                 .profileImageUrl("https://www.google.com")
//                 .build();

//         auth = Auth.builder()
//                 .email("asd@usc.edu.co")
//                 .password("123") // Aquí deberías usar un método para encriptar la contraseña
//                 .user(user)
//                 .build();

//         authRepository.save(auth); // Guarda el auth en la base de datos antes de cada prueba
//     }

//     @AfterEach
//     public void tearDown() {
//         authRepository.deleteAll(); // Limpia la base de datos después de cada prueba
//     }

//     @Test
//     @Rollback // Asegura que no se persistan cambios en la base de datos
//     public void saveAuth() {
//         // Auth foundAuth = authRepository.findByEmail("asd@usc.edu.co").orElse(null);
//         // assertThat(foundAuth).isNotNull();
//         // assertThat(foundAuth.getEmail()).isEqualTo("asd@usc.edu.co");
//     }

//     @Test
//     @Rollback
//     public void deleteAuth() {
//         authRepository.delete(auth);
//         Auth foundAuth = authRepository.findByEmail("asd@usc.edu.co").orElse(null);
//         assertThat(foundAuth).isNull();
//     }

//     @Test
//     @Rollback
//     public void findByEmail() {
//         Auth foundAuth = authRepository.findByEmail("asd@usc.edu.co").orElse(null);
//         assertThat(foundAuth).isNotNull();
//         assertThat(foundAuth.getEmail()).isEqualTo("asd@usc.edu.co");
//     }

//     @Test
//     @Rollback
//     public void findByUserId() {
//         Auth foundAuth = authRepository.findByUserId(auth.getUser().getId()).orElse(null);
//         assertThat(foundAuth).isNotNull();
//         assertThat(foundAuth.getUser().getId()).isEqualTo(auth.getUser().getId());
//     }
// }

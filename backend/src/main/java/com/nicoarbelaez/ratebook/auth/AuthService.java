package com.nicoarbelaez.ratebook.auth;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nicoarbelaez.ratebook.auth.dto.AuthRegisterDto;
import com.nicoarbelaez.ratebook.auth.dto.AuthTokenDto;
import com.nicoarbelaez.ratebook.jwt.JwtService;
import com.nicoarbelaez.ratebook.user.Role;
import com.nicoarbelaez.ratebook.user.User;
import com.nicoarbelaez.ratebook.util.ConsolePrinter;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Optional<AuthTokenDto> create(AuthRegisterDto authUser) {
        ConsolePrinter.info("Creating user: " + authUser.getEmail());
        
        if (authRepository.findByEmail(authUser.getEmail()).isPresent()) {
            ConsolePrinter.error("User already exists with email: " + authUser.getEmail());
            return Optional.empty();
        }
        
        // Crear instancia de User
        User user = User.builder()
                .firstName(authUser.getFirstName())
                .lastName(authUser.getLastName())
                .role(Role.USER) // Asignación de rol de usuario
                .build();
        
        // Crear instancia de Auth
        Auth auth = Auth.builder()
                .email(authUser.getEmail())
                .password(passwordEncoder.encode(authUser.getPassword()))
                .user(user)
                .build();

        try {
            authRepository.save(auth); // Guardar Auth y User
            ConsolePrinter.info("User registered successfully: " + authUser.getEmail());
        } catch (Exception e) {
            ConsolePrinter.error("Error saving Auth: " + e.getMessage());
            return Optional.empty();
        }

        String token = jwtService.getJwtToken(user);
        ConsolePrinter.info("Generated token for user: " + authUser.getEmail());
        return Optional.of(new AuthTokenDto(token));
    }

    public Optional<AuthTokenDto> login(Auth dto) {
        ConsolePrinter.info("Authenticating user: " + dto.getEmail());
        // Lógica de autenticación y generación de token
        return Optional.empty();
    }
}

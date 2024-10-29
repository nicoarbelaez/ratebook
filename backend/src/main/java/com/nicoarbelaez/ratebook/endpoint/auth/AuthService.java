package com.nicoarbelaez.ratebook.endpoint.auth;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nicoarbelaez.ratebook.endpoint.auth.dto.AuthRegisterDto;
import com.nicoarbelaez.ratebook.endpoint.auth.dto.AuthTokenDto;
import com.nicoarbelaez.ratebook.endpoint.user.Role;
import com.nicoarbelaez.ratebook.endpoint.user.User;
import com.nicoarbelaez.ratebook.session.jwt.JwtService;
import com.nicoarbelaez.ratebook.util.ConsolePrinter;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

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

        String token = jwtService.getJwtToken(auth);
        ConsolePrinter.info("Generated token for user: " + authUser.getEmail());
        return Optional.of(new AuthTokenDto(token));
    }

    public AuthTokenDto login(Auth dto) {
        ConsolePrinter.info("Authenticating user: " + dto.getEmail());
        authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        Auth user = authRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + dto.getEmail()));

        String token = jwtService.getJwtToken(user);

        return AuthTokenDto.builder().token(token).build();
    }
}

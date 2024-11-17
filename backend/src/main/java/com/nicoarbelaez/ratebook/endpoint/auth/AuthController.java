package com.nicoarbelaez.ratebook.endpoint.auth;

import com.nicoarbelaez.ratebook.endpoint.auth.dto.AuthRegisterDto;
import com.nicoarbelaez.ratebook.endpoint.auth.dto.AuthTokenDto;
import com.nicoarbelaez.ratebook.util.ConsolePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthTokenDto> register(@RequestBody AuthRegisterDto dto) {
        ConsolePrinter.info("Attempting to register user with email: " + dto.getEmail());

        try {
            AuthTokenDto tokenDto = authService.create(dto)
                    .orElseThrow(() -> new IllegalStateException("Registration failed"));

            ConsolePrinter.info("Registration successful for user: " + dto.getEmail());
            return ResponseEntity.ok(tokenDto);
        } catch (Exception e) {
            ConsolePrinter.error("Registration error for user " + dto.getEmail() + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthTokenDto> login(@RequestBody Auth dto) {
        ConsolePrinter.info("Attempting to log in user with email: " + dto.getEmail());

        try {
            AuthTokenDto tokenDto = authService.login(dto);

            ConsolePrinter.info("Login successful for user: " + dto.getEmail());
            return ResponseEntity.ok(tokenDto);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception e) {
            ConsolePrinter.error("Login error for user " + dto.getEmail() + ": " +
                    e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<String> me() {
        return ResponseEntity.ok("ME");
    }
}

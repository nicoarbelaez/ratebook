package com.nicoarbelaez.ratebook.user;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nicoarbelaez.ratebook.auth.Auth;
import com.nicoarbelaez.ratebook.auth.AuthRepository;
import com.nicoarbelaez.ratebook.user.dto.UserResponseDto;
import com.nicoarbelaez.ratebook.user.dto.mapper.UserMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthRepository authRepository;

    public List<UserResponseDto> getAllUser() {
        return userRepository.findAll().stream().map(UserMapper::toDto).toList();
    }

    public Optional<UserResponseDto> getUserById(Long id) {
        return userRepository.findById(id).map(UserMapper::toDto);
    }

    @Transactional
    public Optional<UserResponseDto> updateUser(Long id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        Optional<Auth> existingAuth = authRepository.findById(id);

        if (!existingUser.isPresent() && existingAuth.isPresent()) {
            return Optional.empty();
        }

        User userToUpdate = existingUser.get();
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setProfileImageUrl(user.getProfileImageUrl());
        userToUpdate.setDate(user.getDate());

        userRepository.save(userToUpdate);
        return Optional.of(UserMapper.toDto(userToUpdate));
    }

    @Transactional
    public boolean deleteUser(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }

    public String getPassword(Long id) {
        return authRepository.findByUserId(id).map(Auth::getPassword).orElse(null);
    }

    public String getEmail(Long id) {
        return authRepository.findByUserId(id).map(Auth::getEmail)
                .orElseThrow(() -> new UsernameNotFoundException("No username found for user ID " + id));
    }
}

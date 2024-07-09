package com.example.musicgame.test.controller;

import com.example.musicgame.model.User;
import com.example.musicgame.repository.UserRepository;
import com.example.musicgame.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userService = new UserService();

        // Use reflection to inject the mocks
        ReflectionTestUtils.setField(userService, "userRepository", userRepository);
        ReflectionTestUtils.setField(userService, "passwordEncoder", passwordEncoder);
    }

    @Test
    void testRegisterUser() {
        when(passwordEncoder.encode(anyString())).thenReturn("encoded_password");
        User user = new User("username", "encoded_password");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User registeredUser = userService.registerUser("username", "password");
        assertEquals("encoded_password", registeredUser.getPassword());
    }

    @Test
    void testChangePassword() {
        User user = new User("username", "password");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(anyString())).thenReturn("new_encoded_password");

        assertDoesNotThrow(() -> userService.changePassword("username", "new_password"));
        assertEquals("new_encoded_password", user.getPassword());
    }

    @Test
    void testDeleteUser() {
        User user = new User("username", "password");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(any(User.class));

        assertDoesNotThrow(() -> userService.deleteUser("username"));
    }

    @Test
    void testLoadUserByUsername() {
        User user = new User("username", "password");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        User foundUser = (User) userService.loadUserByUsername("username");
        assertEquals(user, foundUser);
    }

    @Test
    void testLoadUserByUsernameNotFound() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("username"));
    }
}

package com.example.musicgame.test.controller;

import com.example.musicgame.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserCreation() {
        User user = new User("username", "password");
        assertNotNull(user);
    }

    @Test
    void testSetUsername() {
        User user = new User();
        user.setUsername("newUsername");
        assertEquals("newUsername", user.getUsername());
    }

    @Test
    void testSetPassword() {
        User user = new User();
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }
}

package com.example.musicgame.test.controller;

import com.example.musicgame.model.Player;
import com.example.musicgame.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayerCreation() {
        User user = new User("username", "password");
        Player player = new Player(user);
        assertNotNull(player);
    }

    @Test
    void testSetUser() {
        User user = new User("username", "password");
        Player player = new Player();
        player.setUser(user);
        assertEquals(user, player.getUser());
    }
}

package com.example.musicgame.test.controller;

import com.example.musicgame.model.*;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void testGameCreation() {
        Deck deck = new Deck();
        Player player = new Player();
        Game game = new Game(deck, GameState.CREATED, player);
        assertNotNull(game);
    }

    @Test
    void testAddPlayer() {
        Deck deck = new Deck();
        Player player = new Player();
        Game game = new Game(deck, GameState.CREATED, player);
        game.addPlayer(player);
        assertTrue(game.getPlayers().contains(player));
    }

    @Test
    void testAddSamePlayerTwice() {
        Deck deck = new Deck();
        Player player = new Player();
        Game game = new Game(deck, GameState.CREATED, player);
        game.addPlayer(player);
        assertThrows(RuntimeException.class, () -> game.addPlayer(player));
    }

    @Test
    void testGetNextPlayer() {
        Deck deck = new Deck();
        Player player1 = new Player();
        Player player2 = new Player();
        Game game = new Game(deck, GameState.CREATED, player1);
        game.addPlayer(player1);
        game.addPlayer(player2);
        assertEquals(player2, game.getNextPlayer());
    }
}

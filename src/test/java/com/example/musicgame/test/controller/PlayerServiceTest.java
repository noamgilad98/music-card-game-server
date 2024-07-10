package com.example.musicgame.test.controller;

import com.example.musicgame.model.Player;
import com.example.musicgame.model.User;
import com.example.musicgame.repository.PlayerRepository;
import com.example.musicgame.repository.GameRepository;
import com.example.musicgame.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerServiceTest {

    private PlayerRepository playerRepository;
    private GameRepository gameRepository;
    private PlayerService playerService;

    @BeforeEach
    void setUp() {
        playerRepository = Mockito.mock(PlayerRepository.class);
        gameRepository = Mockito.mock(GameRepository.class);
        playerService = new PlayerService();

        // Use reflection to inject the mocks
        ReflectionTestUtils.setField(playerService, "playerRepository", playerRepository);
        ReflectionTestUtils.setField(playerService, "gameRepository", gameRepository);
    }

    @Test
    void testJoinGame() {
        Player player = new Player();
        when(playerRepository.save(any(Player.class))).thenReturn(player);

        Player joinedPlayer = playerService.joinGame(1L, player);
        assertNotNull(joinedPlayer);
    }

    @Test
    void testGetPlayerById() {
        Player player = new Player();
        when(playerRepository.findById(anyLong())).thenReturn(Optional.of(player));

        Player foundPlayer = playerService.getPlayerById(1L);
        assertEquals(player, foundPlayer);
    }

    @Test
    void testLeaveGame() {
        Player player = new Player();
        when(playerRepository.findById(anyLong())).thenReturn(Optional.of(player));
        doNothing().when(playerRepository).save(any(Player.class));

        assertDoesNotThrow(() -> playerService.leaveGame(1L));
    }

    @Test
    void testCreatePlayer() {
        User user = new User("username", "password");
        Player player = new Player(user);
        when(playerRepository.save(any(Player.class))).thenReturn(player);

        Player createdPlayer = playerService.createPlayer(user);
        assertEquals(player, createdPlayer);
    }
}

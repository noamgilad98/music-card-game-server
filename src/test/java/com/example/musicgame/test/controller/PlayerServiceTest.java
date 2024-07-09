package com.example.musicgame.test.controller;

import com.example.musicgame.model.Game;
import com.example.musicgame.model.Player;
import com.example.musicgame.model.User;
import com.example.musicgame.repository.PlayerRepository;
import com.example.musicgame.repository.GameRepository;
import com.example.musicgame.service.PlayerService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {

    @InjectMocks
    private PlayerService playerService;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private GameRepository gameRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testJoinGame() {
        Player player = new Player();
        player.setId(1L); // Make sure the player has a non-null ID
        Game game = new Game();
        game.setId(1L);

        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(game));
        when(playerRepository.save(any(Player.class))).thenReturn(player);

        playerService.joinGame(1L, player);

        verify(playerRepository).save(any(Player.class));
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

//package com.example.musicgame.test.service;
//
//import com.example.musicgame.model.*;
//import com.example.musicgame.repository.*;
//import com.example.musicgame.service.*;
//import com.example.musicgame.util.JwtUtil;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import java.util.Optional;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class GameServiceTest {
//
//    private GameRepository gameRepository;
//    private PlayerRepository playerRepository;
//    private CardRepository cardRepository;
//    private UserRepository userRepository;
//    private DeckService deckService;
//    private PlayerService playerService;
//    private JwtUtil jwtUtil;
//    private CardService cardService;
//    private GameService gameService;
//
//    @BeforeEach
//    void setUp() {
//        gameRepository = Mockito.mock(GameRepository.class);
//        playerRepository = Mockito.mock(PlayerRepository.class);
//        cardRepository = Mockito.mock(CardRepository.class);
//        userRepository = Mockito.mock(UserRepository.class);
//        deckService = Mockito.mock(DeckService.class);
//        playerService = Mockito.mock(PlayerService.class);
//        jwtUtil = Mockito.mock(JwtUtil.class);
//        cardService = Mockito.mock(CardService.class);
//        gameService = new GameService();
//
//        // Use reflection to inject the mocks
//        ReflectionTestUtils.setField(gameService, "gameRepository", gameRepository);
//        ReflectionTestUtils.setField(gameService, "playerRepository", playerRepository);
//        ReflectionTestUtils.setField(gameService, "cardRepository", cardRepository);
//        ReflectionTestUtils.setField(gameService, "userRepository", userRepository);
//        ReflectionTestUtils.setField(gameService, "deckService", deckService);
//        ReflectionTestUtils.setField(gameService, "playerService", playerService);
//        ReflectionTestUtils.setField(gameService, "jwtUtil", jwtUtil);
//        ReflectionTestUtils.setField(gameService, "cardService", cardService);
//    }
//
//    @Test
//    void testCreateGame() {
//        User user = new User("username", "password");
//        Player player = new Player(user);
//        when(jwtUtil.getUsernameFromToken(anyString())).thenReturn("username");
//        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
//        when(playerService.createPlayer(any(User.class))).thenReturn(player);
//        when(deckService.createDeck(anyList())).thenReturn(new Deck());
//        when(gameRepository.save(any(Game.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        Game game = gameService.createGame("Bearer token");
//        assertNotNull(game);
//        assertEquals(GameState.CREATED, game.getGameState());
//    }
//
//    @Test
//    void testGetGameById() {
//        Game game = new Game();
//        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(game));
//
//        Game foundGame = gameService.getGameById(1L);
//        assertEquals(game, foundGame);
//    }
//
//    @Test
//    void testStartGame() {
//        Game game = new Game();
//        Player player = new Player();
//        player.setId(1L);  // Set the player ID
//        game.addPlayer(player);
//
//        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(game));
//        when(gameRepository.save(any(Game.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
////        Game startedGame = gameService.startGame(1L);
//        assertEquals(GameState.STARTED, startedGame.getGameState());
//        assertNotNull(startedGame.getCurrentPlayer());
//    }
//
//    @Test
//    void testAddPlayerToGame() {
//        Game game = new Game();
//        User user = new User("username", "password");
//        Player player = new Player(user);
//        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(game));
//        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
//        when(playerService.createPlayer(any(User.class))).thenReturn(player);
//        when(gameRepository.save(any(Game.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
////        Game updatedGame = gameService.addPlayerToGame(1L, user);
////        assertTrue(updatedGame.getPlayers().contains(player));
//    }
//
//    @Test
//    void testEndGame() {
//        Game game = new Game();
//        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(game));
//        when(gameRepository.save(any(Game.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        Game endedGame = gameService.endGame(1L);
//        assertEquals(GameState.ENDED, endedGame.getGameState());
//    }
//
//    @Test
//    void testDrawCard() {
//        Game game = new Game();
//        Player player = new Player();
//        player.setId(1L);  // Set the player ID
//        Card card = new Card("Artist", "Preview URL", "Song Title", "Spotify ID", 2020);
//        game.addPlayer(player);
//        game.setDeck(new Deck(Set.of(card)));
//
//        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(game));
//        when(gameRepository.save(any(Game.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        Card drawnCard = gameService.drawCard(1L, player.getId());
//        assertEquals(card, drawnCard);
//    }
//
//    @Test
//    void testDrawCardFromEmptyDeck() {
//        Game game = new Game();
//        Player player = new Player();
//        player.setId(1L);  // Set the player ID
//        game.addPlayer(player);
//
//        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(game));
//
//        assertThrows(RuntimeException.class, () -> gameService.drawCard(1L, player.getId()));
//    }
//}

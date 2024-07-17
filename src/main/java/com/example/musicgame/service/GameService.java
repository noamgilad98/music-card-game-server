package com.example.musicgame.service;

import com.example.musicgame.dto.model.CardDTO;
import com.example.musicgame.dto.response.GamePlacementResult;
import com.example.musicgame.model.*;
import com.example.musicgame.repository.CardRepository;
import com.example.musicgame.repository.GameRepository;
import com.example.musicgame.repository.PlayerRepository;
import com.example.musicgame.repository.UserRepository;
import com.example.musicgame.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GameService {

    private GameRepository gameRepository;
    private PlayerRepository playerRepository;
    private CardRepository cardRepository;
    private UserRepository userRepository;
    private DeckService deckService;
    private PlayerService playerService;
    private CardService cardService;
    private JwtUtil jwtUtil;

    @Autowired
    public GameService(GameRepository gameRepository, PlayerRepository playerRepository, CardRepository cardRepository, UserRepository userRepository, DeckService deckService, PlayerService playerService, CardService cardService, JwtUtil jwtUtil) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
        this.deckService = deckService;
        this.playerService = playerService;
        this.cardService = cardService;
        this.jwtUtil = jwtUtil;
    }


    public GameService() {

    }

    public Game createGame(String token) {
        String username = jwtUtil.getUsernameFromToken(token.substring(7)); // Remove "Bearer " prefix
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Player player = playerService.createPlayer(user);
        Deck deck = deckService.createDeck(cardRepository.findAll());
        Game createdGame = new Game(deck, GameState.CREATED, player);
        gameRepository.save(createdGame); // Save to generate ID
        createdGame.addPlayer(player);
        return gameRepository.save(createdGame); // Save again to persist changes
    }

    public Game getGameById(Long gameId) {
        return gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
    }

    public Game startGame(Long gameId, String token) {
        String username = jwtUtil.getUsernameFromToken(token.substring(7)); // Remove "Bearer " prefix
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Game game = getGameById(gameId);
        if (game.getPlayers().isEmpty()) {
            throw new RuntimeException("Cannot start game without players");
        }
        if(!game.getCreator().getUser().equals(user)) {
            throw new RuntimeException("Only the creator of the game can start the game");
        }
        game.setGameState(GameState.STARTED);
        game.setCurrentPlayer(game.getPlayers().iterator().next());
        return gameRepository.save(game);
    }


    public Game addPlayerToGame(Long gameId, String token) {
        Game game = getGameById(gameId);
        String username = jwtUtil.getUsernameFromToken(token.substring(7)); // Remove "Bearer " prefix
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Player player = playerService.createPlayer(user); // Create player
        game.addPlayer(player);
        return gameRepository.save(game);
    }

    public Game endGame(Long gameId) {
        Game game = getGameById(gameId);
        game.setGameState(GameState.ENDED);
        return gameRepository.save(game);
    }

    public Card drawCard(Long gameId, String token) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        String username = jwtUtil.getUsernameFromToken(token.substring(7)); // Remove "Bearer " prefix
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        long playerId = user.getPlayer(gameId).getId();
        game.getPlayers().stream()
                .filter(p -> p.getId().equals(playerId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Player not found"));

        Optional<Card> card = Optional.ofNullable(game.getDeck().drawCard());
        if (card.isEmpty()) {
            throw new RuntimeException("No card drawn from the deck");
        }

        Card drawnCard = card.get();
        gameRepository.save(game);

        return drawnCard;
    }

    public GamePlacementResult placeCard(Long gameId, String token, Long cardId, int position) {
        Objects.requireNonNull(gameId, "gameId must not be null");
        Objects.requireNonNull(token, "token must not be null");
        Objects.requireNonNull(cardId, "cardId must not be null");
        String username = jwtUtil.getUsernameFromToken(token.substring(7)); // Remove "Bearer " prefix
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        long playerId = user.getPlayer(gameId).getId();

        Game game = getGameById(gameId);
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Player not found"));
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new RuntimeException("Card not found"));

        boolean isValidPlacement = validateCardPlacement(player, card, position);
        String message;
        if (isValidPlacement) {
            player.addCardToTimeline(card, position);
            message = "Card placed correctly";
        } else {
            message = "Card placed incorrectly";
        }

        playerRepository.save(player);
        List<CardDTO> timeline = player.getTimeline().stream().map(cardService::convertToDTO).toList();
        return new GamePlacementResult(gameId, isValidPlacement, timeline, message);
    }

    private boolean validateCardPlacement(Player player, Card card, int position) {
        List<Card> timeline = player.getTimeline();
        if (position < 0 || position > timeline.size()) {
            return false;
        }

        Card previousCard = (position > 0) ? timeline.get(position - 1) : null;
        Card nextCard = (position < timeline.size()) ? timeline.get(position) : null;

        if (previousCard != null && previousCard.getYear() > card.getYear()) {
            return false;
        }

        return nextCard == null || nextCard.getYear() >= card.getYear();
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
}

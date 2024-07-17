package com.example.musicgame.controller;

import com.example.musicgame.dto.model.CardDTO;
import com.example.musicgame.dto.model.GameDTO;
import com.example.musicgame.dto.model.PlayerDTO;
import com.example.musicgame.dto.request.PlaceCardRequest;
import com.example.musicgame.dto.response.CreateGameResponse;
import com.example.musicgame.dto.response.GamePlacementResult;
import com.example.musicgame.dto.response.StartGameResponse;
import com.example.musicgame.model.Card;
import com.example.musicgame.model.Game;
import com.example.musicgame.model.Player;
import com.example.musicgame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/create")
    public ResponseEntity<CreateGameResponse> createGame(@RequestHeader("Authorization") String token) {
        try {
            Game createdGame = gameService.createGame(token);
            CreateGameResponse response = new CreateGameResponse();
            response.setGameId(createdGame.getId());
            response.setStatus(createdGame.getGameState().toString());
            response.setCreator(convertToDTO(createdGame.getCreator()));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CreateGameResponse("Error creating game"));
        }
    }


    @GetMapping("/{gameId}")
    public ResponseEntity<GameDTO> getGame(@PathVariable Long gameId) {
        try {
            Game game = gameService.getGameById(gameId);
            return ResponseEntity.ok(convertToDTO(game));
        } catch (Exception e) {
            e.printStackTrace(); // Add this line to print the stack trace for debugging
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GameDTO("Game not found"));
        }
    }

    @PostMapping("/{gameId}/start")
    public ResponseEntity<StartGameResponse> startGame(@PathVariable Long gameId, @RequestHeader("Authorization") String token){
        try {
            Game startedGame = gameService.startGame(gameId, token);
            StartGameResponse response = new StartGameResponse();
            response.setGameId(startedGame.getId());
            response.setStatus(startedGame.getGameState().toString());
            response.setCurrentTurnPlayerId(startedGame.getCurrentPlayer().getId().toString());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StartGameResponse("Error starting game"));
        }
    }

    @PostMapping("/{gameId}/addPlayer")
    public ResponseEntity<GameDTO> addPlayerToGame(@PathVariable Long gameId, @RequestHeader("Authorization") String token) {
        Game updatedGame = gameService.addPlayerToGame(gameId, token);
        return ResponseEntity.ok(convertToDTO(updatedGame));
    }

    @PostMapping("/{gameId}/end")
    public ResponseEntity<GameDTO> endGame(@PathVariable Long gameId) {
        Game endedGame = gameService.endGame(gameId);
        return ResponseEntity.ok(convertToDTO(endedGame));
    }

    @PostMapping("/{gameId}/drawCard")
    public ResponseEntity<CardDTO> drawCard(@PathVariable Long gameId, @RequestHeader("Authorization") String token) {
        Card drawnCard = gameService.drawCard(gameId, token);
        return ResponseEntity.ok(convertToDTO(drawnCard));
    }

    @PostMapping("/{gameId}/placeCard")
    public ResponseEntity<GamePlacementResult> placeCard(@PathVariable Long gameId, @RequestBody PlaceCardRequest request) {
        String token = request.getToken();

        GamePlacementResult result = gameService.placeCard(gameId, token, request.getCard().getId(), request.getPosition());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GameDTO>> getAllGames() {
        List<Game> games = gameService.getAllGames();
        return ResponseEntity.ok(games.stream().map(this::convertToDTO).toList());
    }

    private PlayerDTO convertToDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setId(player.getId());
        dto.setName(player.getName());
        return dto;
    }

    private CardDTO convertToDTO(Card card) {
        CardDTO dto = new CardDTO();
        dto.setId(card.getId());
        dto.setArtist(card.getArtist());
        dto.setSongTitle(card.getSongTitle());
        dto.setYear(card.getYear());
        return dto;
    }

    private GameDTO convertToDTO(Game game) {
        GameDTO dto = new GameDTO();
        dto.setId(game.getId());
        dto.setStatus(game.getGameState().toString());
        dto.setPlayers(game.getPlayers().stream().map(this::convertToDTO).toList());
        dto.setGameCreatorId(game.getCreator().getId().toString());
        dto.setCurrentTurnPlayerId(game.getCurrentPlayer().getId().toString());
        dto.setTimelines(convertToDTO(game.getTimelines()));
        return dto;
    }

    private Map<Long, List<CardDTO>> convertToDTO(Map<Long, List<Card>> timelines) {
        return timelines.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream().map(this::convertToDTO).toList()
                ));
    }
}

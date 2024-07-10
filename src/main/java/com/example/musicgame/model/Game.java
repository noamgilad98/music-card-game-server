package com.example.musicgame.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "deck_id", nullable = false)
    private Deck deck;

    @Enumerated(EnumType.STRING)
    private GameState gameState;

    @ManyToMany
    @JoinTable(
            name = "game_player",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private Set<Player> players = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private Player creator;

    @ManyToOne
    @JoinColumn(name = "current_player_id")
    private Player currentPlayer;

    private int currentPlayerIndex;


    public Game() {
    }

    public Game(Deck deck, GameState gameState, Player creator) {
        this.deck = deck;
        this.gameState = gameState;
        this.players = new LinkedHashSet<>();
        this.creator = creator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getCreator() {
        return creator;
    }

    public void setCreator(Player creator) {
        this.creator = creator;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        if (this.players.contains(player))
            throw new RuntimeException("Player already in game");
        this.players.add(player);
        if (this.players.size() == 1) {
            this.currentPlayer = player;
        }
    }

    public Player getNextPlayer() {
        if (players.isEmpty()) {
            return null;
        }
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        currentPlayer = players.stream().skip(currentPlayerIndex).findFirst().get();
        return currentPlayer;
    }

    public void setCurrentPlayer(Player next) {
    }

    public User getCurrentPlayer() {
        return null;
    }

    public Map<Long, List<Card>> getTimelines() {
        Map<Long, List<Card>> timelines = new HashMap<>();
        for (Player player : players) {
            timelines.put(player.getId(), player.getTimeline());
        }
        return timelines;
    }
}

package com.example.musicgame.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
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

    public Map<Long, List<Card>> getTimelines() {
        Map<Long, List<Card>> timelines = new HashMap<>();
        for (Player player : players) {
            timelines.put(player.getId(), player.getTimeline());
        }
        return timelines;
    }
}

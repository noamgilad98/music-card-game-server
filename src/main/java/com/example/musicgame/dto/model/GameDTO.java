package com.example.musicgame.dto.model;

import java.util.List;
import java.util.Map;

public class GameDTO {
    private Long id;
    private String status;
    private List<PlayerDTO> players;
    private String gameCreatorId;
    private String currentTurnPlayerId;
    private Map<Long, List<CardDTO>> timelines;

    public GameDTO() {
    }

    public GameDTO(String status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setGameCreatorId(String gameCreatorId) {
        this.gameCreatorId = gameCreatorId;
    }

    public String getGameCreatorId() {
        return gameCreatorId;
    }

    public void setCurrentTurnPlayerId(String currentTurnPlayerId) {
        this.currentTurnPlayerId = currentTurnPlayerId;
    }

    public String getCurrentTurnPlayerId() {
        return currentTurnPlayerId;
    }

    public void setTimelines(Map<Long, List<CardDTO>> timelines) {
        this.timelines = timelines;
    }

    public Map<Long, List<CardDTO>> getTimelines() {
        return timelines;
    }



    // Getters and setters
}

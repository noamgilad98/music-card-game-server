package com.example.musicgame.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StartGameResponse {

    @JsonProperty("gameId")
    private Long gameId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("currentTurnPlayerId")
    private String currentTurnPlayerId;

    @JsonProperty("message")
    private String message;

    // Default constructor for JSON deserialization
    public StartGameResponse() {
    }

    // Constructor for error message
    public StartGameResponse(String message) {
        this.message = message;
    }

    // Getters and setters
    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentTurnPlayerId() {
        return currentTurnPlayerId;
    }

    public void setCurrentTurnPlayerId(String currentTurnPlayerId) {
        this.currentTurnPlayerId = currentTurnPlayerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

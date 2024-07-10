package com.example.musicgame.dto.response;

public class StartGameResponse {
    private Long gameId;
    private String status;
    private String currentTurnPlayerId;

    public StartGameResponse() {
    }

    public StartGameResponse(String status) {
        this.status = status;
    }

    public void setGameId(Long id) {
        this.gameId = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCurrentTurnPlayerId(String currentTurnPlayerId) {
        this.currentTurnPlayerId = currentTurnPlayerId;
    }



    // Getters and setters
}

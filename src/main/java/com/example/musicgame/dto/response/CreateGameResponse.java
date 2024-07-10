package com.example.musicgame.dto.response;

import com.example.musicgame.dto.model.PlayerDTO;

public class CreateGameResponse {
    private Long gameId;
    private String status;
    private PlayerDTO creator;

    public CreateGameResponse(String errorCreatingGame) {
        this.status = errorCreatingGame;
    }

    public CreateGameResponse() {
    }

    public void setGameId(Long id) {
        this.gameId = id;
    }

    public void setStatus(String string) {
        this.status = string;
    }

    public void setCreator(PlayerDTO playerDTO) {
        this.creator = playerDTO;
    }

    // Getters and setters
}

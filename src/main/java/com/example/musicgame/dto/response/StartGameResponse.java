package com.example.musicgame.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StartGameResponse {

    // Getters and setters
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

}

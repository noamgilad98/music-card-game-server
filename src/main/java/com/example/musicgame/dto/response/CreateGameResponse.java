package com.example.musicgame.dto.response;

import com.example.musicgame.dto.model.PlayerDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateGameResponse {

    // Getters and setters
    @JsonProperty("gameId")
    private Long gameId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("creator")
    private PlayerDTO creator;

    @JsonProperty("message")
    private String message;

    // Default constructor for JSON deserialization
    public CreateGameResponse() {
    }

    // Constructor for error message
    public CreateGameResponse(String message) {
        this.message = message;
    }

}

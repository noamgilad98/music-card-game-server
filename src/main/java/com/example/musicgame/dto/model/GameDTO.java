package com.example.musicgame.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
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
}

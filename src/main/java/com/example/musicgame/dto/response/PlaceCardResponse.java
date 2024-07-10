package com.example.musicgame.dto.response;

import com.example.musicgame.dto.model.CardDTO;

import java.util.List;

public class PlaceCardResponse {
    private Long gameId;
    private boolean correct;
    private String message;
    private String currentTurnPlayerId;
    private List<CardDTO> timeline;

    public void setGameId(Long id) {
        this.gameId = id;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void setTimeline(List<CardDTO> timeline) {
        this.timeline = timeline;
    }



    // Getters and setters
}

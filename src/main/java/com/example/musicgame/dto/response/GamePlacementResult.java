package com.example.musicgame.dto.response;

import com.example.musicgame.dto.model.CardDTO;

import java.util.List;

public class GamePlacementResult {
    private Long gameId;
    private boolean correct;
    private List<CardDTO> timeLine;
    private String message;

    public GamePlacementResult(Long gameId, boolean correct, List<CardDTO> timeLine, String message) {
        this.gameId = gameId;
        this.correct = correct;
        this.timeLine = timeLine;
        this.message = message;
    }

    public void setGameId(Long id) {
        this.gameId = id;
    }

    public boolean isCorrect() {
        return correct;
    }

    public String getMessage() {
        return message;
    }

    public List<CardDTO> getTimeLine() {
        return timeLine;
    }

    public Long getGameId() {
        return gameId;
    }

    public void SetCorrect(boolean correct) {
        this.correct = correct;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimeLine(List<CardDTO> timeLine) {
        this.timeLine = timeLine;
    }
}

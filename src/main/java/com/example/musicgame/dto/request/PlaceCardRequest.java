package com.example.musicgame.dto.request;

import com.example.musicgame.dto.model.CardDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlaceCardRequest {
    // Getters and setters
    private String token;
    private CardDTO card;
    private int position;

    // Default constructor
    public PlaceCardRequest() {}

    // Parameterized constructor
    public PlaceCardRequest(String token, CardDTO card, int position) {
        this.token = token;
        this.card = card;
        this.position = position;
    }

    @Override
    public String toString() {
        return "PlaceCardRequest{" +
                "token='" + token +
                ", card=" + card +
                ", position=" + position +
                '}';
    }
}

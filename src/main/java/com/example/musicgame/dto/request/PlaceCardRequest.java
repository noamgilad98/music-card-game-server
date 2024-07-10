package com.example.musicgame.dto.request;

import com.example.musicgame.model.Card;
import com.example.musicgame.model.Player;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlaceCardRequest {
    // Getters and setters
    private Player player;
    private Card card;
    private int position;

    // Default constructor
    public PlaceCardRequest() {}

    // Parameterized constructor
    public PlaceCardRequest(Player player, Card card, int position) {
        this.player = player;
        this.card = card;
        this.position = position;
    }

    @Override
    public String toString() {
        return "PlaceCardRequest{" +
                "player=" + player +
                ", card=" + card +
                ", position=" + position +
                '}';
    }
}

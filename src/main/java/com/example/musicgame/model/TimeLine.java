package com.example.musicgame.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Entity
public class TimeLine {

    // Getters and setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "timeline_card",
            joinColumns = @JoinColumn(name = "timeline_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<Card> cards = new ArrayList<>();


    public TimeLine() {
    }

    public TimeLine(List<Card> cards) {
        this.cards = cards;
    }


    public void addCardAtPosition(Card card, int position) {
        if (position < 0 || position > cards.size()) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }
        cards.add(position, card);
    }
}
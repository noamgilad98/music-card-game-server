package com.example.musicgame.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@Entity
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "deck_card",
            joinColumns = @JoinColumn(name = "deck_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private Set<Card> cards;


    public Deck() {
        this.cards = new HashSet<>();
    }

    public Deck(Set<Card> cards) {
        this.cards = new HashSet<>(cards);
    }


    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new RuntimeException("Deck is empty");
        }
        Card card = cards.iterator().next();
        cards.remove(card);
        return card;
    }
}

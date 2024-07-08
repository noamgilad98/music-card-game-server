package com.example.musicgame.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<Card> cards = new HashSet<>();

    public Deck() {
    }

    public Deck(Set<Card> cards) {
        this.cards = cards;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> deckCards) {
        this.cards = deckCards;
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

package com.example.musicgame.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL)
    private Set<DeckCard> deckCards = new HashSet<>();

    public Deck() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<DeckCard> getDeckCards() {
        return deckCards;
    }

    public void setDeckCards(Set<DeckCard> deckCards) {
        this.deckCards = deckCards;
    }

    public void addCard(Card card) {
        DeckCard deckCard = new DeckCard(this, card);
        deckCards.add(deckCard);
    }

    public void removeCard(Card card) {
        deckCards.removeIf(deckCard -> deckCard.getCard().equals(card));
    }

    public Card drawCard() {
        if (deckCards.isEmpty()) {
            throw new RuntimeException("Deck is empty");
        }

        DeckCard deckCard = deckCards.iterator().next();
        deckCards.remove(deckCard);

        return deckCard.getCard();
    }
}

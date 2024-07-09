package com.example.musicgame.test.controller;

import com.example.musicgame.model.Card;
import com.example.musicgame.model.Deck;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void testDeckCreation() {
        Deck deck = new Deck();
        assertNotNull(deck);
    }

    @Test
    void testDrawCard() {
        Card card = new Card("Artist", "Preview URL", "Song Title", "Spotify ID", 2020);
        Deck deck = new Deck(Set.of(card));
        assertNotNull(deck.drawCard());
    }

    @Test
    void testDrawCardFromEmptyDeck() {
        Deck deck = new Deck();
        assertThrows(RuntimeException.class, deck::drawCard);
    }
}

package com.example.musicgame.test.controller;

import com.example.musicgame.model.Card;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void testCardCreation() {
        Card card = new Card("Artist", "Preview URL", "Song Title", "Spotify ID", 2020);
        assertNotNull(card);
        assertEquals("Artist", card.getArtist());
    }

    @Test
    void testCardSetters() {
        Card card = new Card();
        card.setArtist("New Artist");
        assertEquals("New Artist", card.getArtist());
    }
}

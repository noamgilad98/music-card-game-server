package com.example.musicgame.test.controller;

import com.example.musicgame.model.Card;
import com.example.musicgame.model.TimeLine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeLineTest {

    @Test
    void testTimeLineCreation() {
        TimeLine timeLine = new TimeLine();
        assertNotNull(timeLine);
    }

    @Test
    void testAddCardAtPosition() {
        Card card = new Card("Artist", "Preview URL", "Song Title", "Spotify ID", 2020);
        TimeLine timeLine = new TimeLine();
        timeLine.addCardAtPosition(card, 0);
        assertTrue(timeLine.getCards().contains(card));
    }

    @Test
    void testAddCardAtInvalidPosition() {
        Card card = new Card("Artist", "Preview URL", "Song Title", "Spotify ID", 2020);
        TimeLine timeLine = new TimeLine();
        assertThrows(IndexOutOfBoundsException.class, () -> timeLine.addCardAtPosition(card, -1));
    }
}

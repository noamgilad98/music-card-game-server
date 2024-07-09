package com.example.musicgame.test.controller;

import com.example.musicgame.model.Card;
import com.example.musicgame.repository.CardRepository;
import com.example.musicgame.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardServiceTest {

    private CardRepository cardRepository;
    private CardService cardService;

    @BeforeEach
    void setUp() {
        cardRepository = Mockito.mock(CardRepository.class);
        cardService = new CardService();

        // Use reflection to inject the mocks
        ReflectionTestUtils.setField(cardService, "cardRepository", cardRepository);
    }

    @Test
    void testCreateOrGetCard() {
        Card card = new Card("Artist", "Preview URL", "Song Title", "Spotify ID", 2020);
        when(cardRepository.findBySpotifyTrackId(anyString())).thenReturn(Optional.empty());
        when(cardRepository.save(any(Card.class))).thenReturn(card);

        Card createdCard = cardService.createOrGetCard("Artist", "Preview URL", "Song Title", "Spotify ID", 2020);
        assertNotNull(createdCard);
    }

    @Test
    void testGetCardById() {
        Card card = new Card("Artist", "Preview URL", "Song Title", "Spotify ID", 2020);
        when(cardRepository.findById(anyLong())).thenReturn(Optional.of(card));

        Card foundCard = cardService.getCardById(1L);
        assertEquals(card, foundCard);
    }

    @Test
    void testDeleteCard() {
        doNothing().when(cardRepository).deleteById(anyLong());
        assertDoesNotThrow(() -> cardService.deleteCard(1L));
    }
}

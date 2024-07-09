package com.example.musicgame.test.controller;

import com.example.musicgame.model.Card;
import com.example.musicgame.model.Deck;
import com.example.musicgame.repository.CardRepository;
import com.example.musicgame.repository.DeckRepository;
import com.example.musicgame.service.CardService;
import com.example.musicgame.service.DeckService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeckServiceTest {

    private DeckRepository deckRepository;
    private CardRepository cardRepository;
    private DeckService deckService;
    private CardService cardService;

    @BeforeEach
    void setUp() {
        deckRepository = Mockito.mock(DeckRepository.class);
        cardRepository = Mockito.mock(CardRepository.class);
        cardService = new CardService();
        deckService = new DeckService();

        // Use reflection to inject the mocks
        ReflectionTestUtils.setField(deckService, "deckRepository", deckRepository);
        ReflectionTestUtils.setField(deckService, "cardService", cardService);
        ReflectionTestUtils.setField(cardService, "cardRepository", cardRepository);
    }

    @Test
    void testCreateDeck() {
        Card card = new Card("Artist", "Preview URL", "Song Title", "Spotify ID", 2020);
        when(cardRepository.findAll()).thenReturn(List.of(card));
        Deck deck = new Deck(Set.of(card));
        when(deckRepository.save(any(Deck.class))).thenReturn(deck);

        Deck createdDeck = deckService.createDeck(List.of(card));
        assertNotNull(createdDeck);
    }

    @Test
    void testGetDeckById() {
        Deck deck = new Deck();
        when(deckRepository.findById(anyLong())).thenReturn(Optional.of(deck));

        Deck foundDeck = deckService.getDeckById(1L);
        assertEquals(deck, foundDeck);
    }

    @Test
    void testDeleteDeck() {
        doNothing().when(deckRepository).deleteById(anyLong());
        assertDoesNotThrow(() -> deckService.deleteDeck(1L));
    }
}

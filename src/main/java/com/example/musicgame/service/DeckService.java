package com.example.musicgame.service;

import com.example.musicgame.model.Card;
import com.example.musicgame.model.Deck;
import com.example.musicgame.model.DeckCard;
import com.example.musicgame.repository.DeckCardRepository;
import com.example.musicgame.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckService {

    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private DeckCardRepository deckCardRepository;

    @Autowired
    private CardService cardService;

    public Deck createDeck(List<Card> cards) {
        Deck deck = new Deck();
        deckRepository.save(deck);

        for (Card card : cards) {
            DeckCard deckCard = new DeckCard(deck, card);
            deckCardRepository.save(deckCard);
        }

        return deck;
    }

    public Deck getDeckById(Long deckId) {
        return deckRepository.findById(deckId).orElseThrow(() -> new RuntimeException("Deck not found"));
    }

    public void deleteDeck(Long deckId) {
        deckRepository.deleteById(deckId);
    }
}

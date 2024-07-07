package com.example.musicgame.service;

import com.example.musicgame.model.Card;
import com.example.musicgame.model.Deck;
import com.example.musicgame.repository.CardRepository;
import com.example.musicgame.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DeckService {

    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private CardService cardService;
    @Autowired
    private CardRepository cardRepository;


    public Deck createDeck(List<Card> cards) {
        //convert to set
        Set<Card> cardSet = Set.copyOf(cards);
        Deck deck = new Deck(cardSet);
        deckRepository.save(deck);

        return deck;
    }

    public Deck getDeckById(Long deckId) {
        return deckRepository.findById(deckId).orElseThrow(() -> new RuntimeException("Deck not found"));
    }

    public void deleteDeck(Long deckId) {
        deckRepository.deleteById(deckId);
    }
}

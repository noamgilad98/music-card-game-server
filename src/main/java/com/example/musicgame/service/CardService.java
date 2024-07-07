package com.example.musicgame.service;

import com.example.musicgame.model.Card;
import com.example.musicgame.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Card createOrGetCard(String artist, String previewUrl, String songTitle, String spotifyTrackId, int year) {
        return cardRepository.findBySpotifyTrackId(spotifyTrackId)
                .orElseGet(() -> cardRepository.save(new Card(artist, previewUrl, songTitle, spotifyTrackId, year)));
    }

    public Card getCardById(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(() -> new RuntimeException("Card not found"));
    }

    public void deleteCard(Long cardId) {
        cardRepository.deleteById(cardId);
    }
}

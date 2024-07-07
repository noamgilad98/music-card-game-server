package com.example.musicgame.repository;

import com.example.musicgame.model.DeckCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckCardRepository extends JpaRepository<DeckCard, Long> {
}

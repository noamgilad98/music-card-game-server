package com.example.musicgame.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String artist;

    private String previewUrl;

    private String songTitle;

    private String spotifyTrackId;

    private int year;

    @ManyToMany(mappedBy = "cards")
    private Set<Deck> decks = new HashSet<>();


    public Card() {
    }

    public Card(String artist, String previewUrl, String songTitle, String spotifyTrackId, int year) {
        this.artist = artist;
        this.previewUrl = previewUrl;
        this.songTitle = songTitle;
        this.spotifyTrackId = spotifyTrackId;
        this.year = year;
    }
}

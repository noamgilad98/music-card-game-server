package com.example.musicgame.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSpotifyTrackId() {
        return spotifyTrackId;
    }

    public void setSpotifyTrackId(String spotifyTrackId) {
        this.spotifyTrackId = spotifyTrackId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

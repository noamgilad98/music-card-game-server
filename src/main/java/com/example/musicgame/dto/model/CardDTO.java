package com.example.musicgame.dto.model;

public class CardDTO {
    private Long id;
    private String artist;
    private String songTitle;
    private int year;
    private String code;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String toString() {
        return "CardDTO{id=" + id + ", artist=" + artist + ", songTitle=" + songTitle + ", year=" + year + ", code=" + code + "}";
    }

    // Getters and setters
}

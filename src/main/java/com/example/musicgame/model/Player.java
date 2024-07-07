package com.example.musicgame.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "timeline_id")
    private TimeLine timeLine;

    @ManyToMany(mappedBy = "players")
    private List<Game> games = new ArrayList<>();

    public Player() {
    }

    public Player(User user) {
        this.name = user.getUsername();
        this.timeLine = new TimeLine();
        this.user = user;
    }

    public Player(String name) {
        this.name = name;
        this.timeLine = new TimeLine();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimeLine getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(TimeLine timeLine) {
        this.timeLine = timeLine;
    }

    public List<Card> getTimeline() {
        return timeLine.getCards();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }



    public void addCardToTimeline(Card card, int position) {
        timeLine.addCardAtPosition(card, position);
    }
}

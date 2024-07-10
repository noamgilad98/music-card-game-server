package com.example.musicgame.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
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

    public List<Card> getTimeline() {
        return timeLine.getCards();
    }


    public void addCardToTimeline(Card card, int position) {
        timeLine.addCardAtPosition(card, position);
    }
}

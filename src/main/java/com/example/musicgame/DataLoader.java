package com.example.musicgame;

import com.example.musicgame.model.*;
import com.example.musicgame.repository.*;
import com.example.musicgame.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private SpotifyService spotifyService;

    @Autowired
    private CardService cardService;

    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TimeLineRepository timeLineRepository;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Override
    public void run(String... args) throws Exception {
        deleteExistingData();
        initAllData();
    }

    private void deleteExistingData() {
        // Delete entries from join tables or associations first
        timeLineRepository.deleteAll();
        gameRepository.deleteAll();
        deckRepository.deleteAll();
        playerRepository.deleteAll();

        // Delete cards and users last
        cardRepository.deleteAll();
        userRepository.deleteAll();

        System.out.println("Existing data deleted successfully.");
    }

    private void initAllData() {
        // Users
        User user1 = new User("user1", "password1");
        User user2 = new User("user2", "password2");
        User user3 = new User("user3", "password3");
        userRepository.saveAll(List.of(user1, user2, user3));

        // Cards
        setCards();
        List<Card> cards = cardRepository.findAll();

        // Decks
        Deck deck1 = new Deck(new HashSet<>(cards));
        Deck deck2 = new Deck(new HashSet<>(cards));
        Deck deck3 = new Deck(new HashSet<>(cards));
        deckRepository.saveAll(List.of(deck1, deck2, deck3));

        // TimeLines
        TimeLine timeLine1 = new TimeLine();
        timeLine1.addCardAtPosition(deck1.drawCard(), 0);
        timeLine1.addCardAtPosition(deck1.drawCard(), 0);
        timeLine1.addCardAtPosition(deck1.drawCard(), 0);
        TimeLine timeLine2 = new TimeLine();
        timeLine2.addCardAtPosition(deck1.drawCard(), 0);
        TimeLine timeLine3 = new TimeLine();
        timeLine3.addCardAtPosition(deck1.drawCard(), 0);
        timeLineRepository.saveAll(List.of(timeLine1, timeLine2, timeLine3));

        // Players
        Player player1 = new Player(user1.getUsername());
        player1.setUser(user1);
        Player player2 = new Player(user2.getUsername());
        player2.setUser(user2);
        Player player3 = new Player(user3.getUsername());
        player3.setUser(user3);
        Player player4 = new Player(user1.getUsername());
        player4.setUser(user1);
        Player player5 = new Player(user2.getUsername());
        player5.setUser(user2);
        Player player6 = new Player(user3.getUsername());
        player6.setUser(user3);
        playerRepository.saveAll(List.of(player1, player2, player3, player4, player5, player6));

        // Games
        Game game1 = new Game(deck1, GameState.CREATED);
        game1.addPlayer(player1);
        game1.addPlayer(player2);
        game1.addPlayer(player3);
        gameRepository.save(game1);

        Game game2 = new Game(deck2, GameState.CREATED);
        game2.addPlayer(player4);
        game2.addPlayer(player5);
        gameRepository.save(game2);

        Game game3 = new Game(deck3, GameState.CREATED);
        game3.addPlayer(player6);
        gameRepository.save(game3);
        System.out.println("Data initialized successfully.");
    }

    private void setCards(){

        Set<Card> cards = new HashSet<>();
        cards.add(new Card("Ed Sheeran", "https://p.scdn.co/mp3-preview/7339548839a263fd721d01eb3364a848cad16fa7?cid=909cdedbe38145d7a5848f6d36392b69", "Shape of You", "spotify:track:7qiZfU4dY1lWllzX7mPBI3", 2017));
        cards.add(new Card("Travis Scott", "https://p.scdn.co/mp3-preview/387b31c31b72f0c16e33d0c78bab869b0a0f4eb3?cid=909cdedbe38145d7a5848f6d36392b69", "HIGHEST IN THE ROOM", "spotify:track:3eekarcy7kvN4yt5ZFzltW", 2019));
        cards.add(new Card("Megan Thee Stallion", "https://p.scdn.co/mp3-preview/adf3906bbf048ebb7b9bb0043560a129beb77b36?cid=909cdedbe38145d7a5848f6d36392b69", "Savage Remix (feat. Beyoncé)", "spotify:track:5v4GgrXPMghOnBBLmveLac", 2020));
        cards.add(new Card("Bebe Rexha", "https://p.scdn.co/mp3-preview/fa4d66e50c72c03ced38f802adf41e44cad2c2e4?cid=909cdedbe38145d7a5848f6d36392b69", "Meant to Be (feat. Florida Georgia Line)", "spotify:track:7iDa6hUg2VgEL1o1HjmfBn", 2017));
        cards.add(new Card("Ed Sheeran", "https://p.scdn.co/mp3-preview/d90f4e5f15d8ed411307945560b1db8cca6b253b?cid=909cdedbe38145d7a5848f6d36392b69", "Photograph", "spotify:track:1HNkqx9Ahdgi1Ixy2xkKkL", 2014));
        cards.add(new Card("Kygo", "https://p.scdn.co/mp3-preview/b78a834a199aaaff9f0b3025077b2ee302f4c701?cid=909cdedbe38145d7a5848f6d36392b69", "It Ain't Me (with Selena Gomez)", "spotify:track:3eR23VReFzcdmS7TYCrhCe", 2017));
        cards.add(new Card("The Chainsmokers", "https://p.scdn.co/mp3-preview/a0f968725f2164068bee9379d9fce23fbe9bea74?cid=909cdedbe38145d7a5848f6d36392b69", "Roses (feat. ROZES)", "spotify:track:6O6M7pJLABmfBRoGZMu76Y", 2016));
        cards.add(new Card("DRAM", "https://p.scdn.co/mp3-preview/72ead3dff2729ca0e0b7fa4b692a73aaa5231f4d?cid=909cdedbe38145d7a5848f6d36392b69", "Broccoli (feat. Lil Yachty)", "spotify:track:7yyRTcZmCiyzzJlNzGC9Ol", 2016));
        cards.add(new Card("The Chainsmokers", "https://p.scdn.co/mp3-preview/cfd565c4d3c621771e6d25d99749b9fc200e396c?cid=909cdedbe38145d7a5848f6d36392b69", "Closer", "spotify:track:7BKLCZ1jbUBVqRi2FVlTVw", 2016));
        cards.add(new Card("One Direction", "https://p.scdn.co/mp3-preview/9c3f89f926a702034ae182e723b2d601c2bd754d?cid=909cdedbe38145d7a5848f6d36392b69", "Story of My Life", "spotify:track:4nVBt6MZDDP6tRVdQTgxJg", 2013));
        cards.add(new Card("Chris Brown", "https://p.scdn.co/mp3-preview/74a14b8bc5d019c40321871ad15e024576d6dbfc?cid=909cdedbe38145d7a5848f6d36392b69", "Run It! (feat. Juelz Santana)", "spotify:track:7xYnUQigPoIDAMPVK79NEq", 2005));
        cards.add(new Card("Calvin Harris", "https://p.scdn.co/mp3-preview/16f9a0b74ce7aa75cfcfcd2bd928cda87ba11045?cid=909cdedbe38145d7a5848f6d36392b69", "Slide (feat. Frank Ocean & Migos)", "spotify:track:6gpcs5eMhJwax4mIfKDYQk", 2017));
        cards.add(new Card("Eagles", "https://p.scdn.co/mp3-preview/4b32d39b05829f2c442aa869354f0f63cefcef24?cid=909cdedbe38145d7a5848f6d36392b69", "Take It Easy - 2013 Remaster", "spotify:track:4yugZvBYaoREkJKtbG08Qr", 1972));
        cards.add(new Card("Shakira", "https://p.scdn.co/mp3-preview/374b492571c9ba59c2c4b455ab79ee7501adab93?cid=909cdedbe38145d7a5848f6d36392b69", "Hips Don't Lie (feat. Wyclef Jean)", "spotify:track:3ZFTkvIE7kyPt6Nu3PEa7V", 2005));
        cards.add(new Card("Aretha Franklin", "https://p.scdn.co/mp3-preview/7768dd513193e30ab1ad19deeff2dcc63d2c7555?cid=909cdedbe38145d7a5848f6d36392b69", "Respect", "spotify:track:7s25THrKz86DM225dOYwnr", 1967));
        cards.add(new Card("Bruno Mars", "https://p.scdn.co/mp3-preview/6d1a901b10c7dc609d4c8628006b04bc6e672be8?cid=909cdedbe38145d7a5848f6d36392b69", "Just the Way You Are", "spotify:track:7BqBn9nzAq8spo5e7cZ0dJ", 2010));
        cards.add(new Card("Kygo", "https://p.scdn.co/mp3-preview/6737d20b6eba8e6bc5b9270d884b04022f38d713?cid=909cdedbe38145d7a5848f6d36392b69", "Higher Love", "spotify:track:6oJ6le65B3SEqPwMRNXWjY", 2019));
        cards.add(new Card("A Great Big World", "https://p.scdn.co/mp3-preview/0178f39b1dd08aa1111138fa775c7b8826cdee7f?cid=909cdedbe38145d7a5848f6d36392b69", "Say Something", "spotify:track:6Vc5wAMmXdKIAM7WUoEb7N", 2014));
        cardRepository.saveAll(cards);
    }


}


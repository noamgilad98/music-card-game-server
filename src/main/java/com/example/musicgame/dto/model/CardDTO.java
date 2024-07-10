package com.example.musicgame.dto.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CardDTO {
    private Long id;
    private String artist;
    private String songTitle;
    private int year;
    private String code;

}

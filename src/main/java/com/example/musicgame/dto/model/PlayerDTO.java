package com.example.musicgame.dto.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlayerDTO {
    private Long id;
    private String name;

    public String toString() {
        return "PlayerDTO{id=" + id + ", name=" + name + "}";
    }
}

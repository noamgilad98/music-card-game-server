package com.example.musicgame.dto.model;

public class PlayerDTO {
    private Long id;
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "PlayerDTO{id=" + id + ", name=" + name + "}";
    }
}

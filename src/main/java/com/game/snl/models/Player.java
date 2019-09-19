package com.game.snl.models;

import java.util.UUID;

public class Player {

    private String name;
    private String userId;


    public Player(String name) {
        this.name = name;
        this.userId = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }
}

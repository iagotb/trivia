package com.adaptionsoft.games.uglytrivia;

import lombok.Data;
import lombok.Getter;

@Data
public class Player {

    private String name;

    public Player(String playerName) {
        this.name = playerName;
    }
}

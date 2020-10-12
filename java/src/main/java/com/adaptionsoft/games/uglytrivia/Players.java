package com.adaptionsoft.games.uglytrivia;

import java.util.List;

public class Players {

    private List<Player> players;

    private int currentPlayerPosition;

    public Players(List<Player> players) {
        this.players = players;
        this.currentPlayerPosition = 0;
    }

    public void add(Player player) {
        players.add(player);
    }

    public int size() {
        return players.size();
    }

    public Player getCurrentPlayer() {
        return this.players.get(currentPlayerPosition);
    }


    public void nextPlayer() {
        this.currentPlayerPosition++;
        if (this.currentPlayerPosition == players.size()) {
            this.currentPlayerPosition = 0;
        }
    }

    public int getCurrentPlayerPosition() {
        return this.currentPlayerPosition;
    }
}

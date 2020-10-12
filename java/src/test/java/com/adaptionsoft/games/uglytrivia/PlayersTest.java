package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlayersTest {

    Players players;

    @Test
    public void firstPlayer() {
        players = new Players(List.of(new Player("player1"), new Player("player2")));
        players.nextPlayer();
        assertEquals(1, players.getCurrentPlayerPosition());
    }

    @Test
    public void returnToFirstPlayerFromLastOne() {
        players = new Players(List.of(new Player("player1"), new Player("player2")));
        players.setCurrentPlayerPosition(1);
        players.nextPlayer();
        assertEquals(0, players.getCurrentPlayerPosition());
    }
}
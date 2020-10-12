package com.adaptionsoft.games.uglytrivia;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlayersTest {

    private Players players;
    private Player player2;
    private Player player1;

    @Before
    public void initialize() {
        player1 = new Player("player1");
        player2 = new Player("player2");
        players = new Players(List.of(player1, player2));
    }

    @Test
    public void firstPlayer() {
        players = new Players(List.of(player1, player2));
        assertEquals(player1, players.getCurrentPlayer());
    }

    @Test
    public void returnToFirstPlayerFromLastOne() {
        players = new Players(List.of(player1, player2));
        players.nextPlayer();
        assertEquals(player2, players.getCurrentPlayer());
    }
}
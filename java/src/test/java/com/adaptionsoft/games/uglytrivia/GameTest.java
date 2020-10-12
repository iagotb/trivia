package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import java.util.List;

public class GameTest {

    Game game;

    @Test(expected = NullPointerException.class)
    public void shouldNotRollWithoutPlayers() {

        game = new Game(null, new Questions());
        game.roll(1);
    }

    @Test
    public void shouldRollWithPlayers() {
        game = new Game(new Players(List.of(new Player("player1"))), new Questions());
        game.roll(1);
    }

}
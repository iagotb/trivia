package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

public class GameTest {

    Game game = new Game();

    @Test(expected = NullPointerException.class)
    public void shouldNotRollWithoutPlayers() {
        game.roll(1);
    }

    @Test
    public void shouldRollWithPlayers() {
        game.add("player1");
        game.roll(1);
    }

}
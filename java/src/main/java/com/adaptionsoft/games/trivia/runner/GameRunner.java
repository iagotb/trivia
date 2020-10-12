
package com.adaptionsoft.games.trivia.runner;
import java.util.List;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Player;
import com.adaptionsoft.games.uglytrivia.Players;


public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) {
		Players players = new Players(List.of(new Player("Chet"), new Player("Pat"), new Player("Sue")));
		Game aGame = new Game(players);

		Random rand = new Random();
	
		do {
			
			aGame.roll(rand.nextInt(5) + 1);
			
			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}
			
			
			
		} while (notAWinner);
		
	}
}

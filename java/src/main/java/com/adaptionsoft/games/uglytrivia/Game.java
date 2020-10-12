package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Game {
    Players players;

	int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
    
    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();
    
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(Players players){
    	this.players = players;
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
    }

	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}
	
	public boolean isPlayable() {
		return (players.size() >= 2);
	}

	public boolean add(String playerName) {
		places[players.size()] = 0;
		purses[players.size()] = 0;
		inPenaltyBox[players.size()] = false;
	    
	    System.out.println(playerName + " was added");
		System.out.println("They are player number " + players.size());
		return true;
	}

	public void roll(int roll) {
		System.out.println(players.getCurrentPlayer() + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (inPenaltyBox[players.getCurrentPlayerPosition()]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				System.out.println(players.getCurrentPlayer() + " is getting out of the penalty box");
				places[players.getCurrentPlayerPosition()] = places[players.getCurrentPlayerPosition()] + roll;
				if (places[players.getCurrentPlayerPosition()] > 11) places[players.getCurrentPlayerPosition()] = places[players.getCurrentPlayerPosition()] - 12;

				System.out.println(players.getCurrentPlayer()
						+ "'s new location is " 
						+ places[players.getCurrentPlayerPosition()]);
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(players.getCurrentPlayer() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {

			places[players.getCurrentPlayerPosition()] = places[players.getCurrentPlayerPosition()] + roll;
			if (places[players.getCurrentPlayerPosition()] > 11) places[players.getCurrentPlayerPosition()] = places[players.getCurrentPlayerPosition()] - 12;

			System.out.println(players.getCurrentPlayer()
					+ "'s new location is " 
					+ places[players.getCurrentPlayerPosition()]);
			System.out.println("The category is " + currentCategory());
			askQuestion();
		}
		
	}

	private void askQuestion() {
		if (currentCategory().equals("Pop"))
			System.out.println(popQuestions.removeFirst());
		if (currentCategory().equals("Science"))
			System.out.println(scienceQuestions.removeFirst());
		if (currentCategory().equals("Sports"))
			System.out.println(sportsQuestions.removeFirst());
		if (currentCategory().equals("Rock"))
			System.out.println(rockQuestions.removeFirst());		
	}
	
	
	private String currentCategory() {
		if (places[players.getCurrentPlayerPosition()] == 0) return "Pop";
		if (places[players.getCurrentPlayerPosition()] == 4) return "Pop";
		if (places[players.getCurrentPlayerPosition()] == 8) return "Pop";
		if (places[players.getCurrentPlayerPosition()] == 1) return "Science";
		if (places[players.getCurrentPlayerPosition()] == 5) return "Science";
		if (places[players.getCurrentPlayerPosition()] == 9) return "Science";
		if (places[players.getCurrentPlayerPosition()] == 2) return "Sports";
		if (places[players.getCurrentPlayerPosition()] == 6) return "Sports";
		if (places[players.getCurrentPlayerPosition()] == 10) return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[players.getCurrentPlayerPosition()]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[players.getCurrentPlayerPosition()]++;
				System.out.println(players.getCurrentPlayer()
						+ " now has "
						+ purses[players.getCurrentPlayerPosition()]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				players.nextPlayer();

				return winner;
			} else {
				players.nextPlayer();
				return true;
			}
			
			
			
		} else {
		
			System.out.println("Answer was corrent!!!!");
			purses[players.getCurrentPlayerPosition()]++;
			System.out.println(players.getCurrentPlayer()
					+ " now has "
					+ purses[players.getCurrentPlayerPosition()]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			players.nextPlayer();

			return winner;
		}
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.getCurrentPlayer() + " was sent to the penalty box");
		inPenaltyBox[players.getCurrentPlayerPosition()] = true;

		players.nextPlayer();
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[players.getCurrentPlayerPosition()] == 6);
	}

}

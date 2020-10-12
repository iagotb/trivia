package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Game {
    Players players;

	int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
    
    LinkedList<String> popQuestions = new LinkedList<String>();
    LinkedList<String> scienceQuestions = new LinkedList<String>();
    LinkedList<String> sportsQuestions = new LinkedList<String>();
    LinkedList<String> rockQuestions = new LinkedList<String>();
    
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
		
		if (inPenaltyBox[getCurrentPlayer()]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				System.out.println(players.getCurrentPlayer() + " is getting out of the penalty box");
				places[getCurrentPlayer()] = places[getCurrentPlayer()] + roll;
				if (places[getCurrentPlayer()] > 11) places[getCurrentPlayer()] = places[getCurrentPlayer()] - 12;

				System.out.println(players.getCurrentPlayer()
						+ "'s new location is " 
						+ places[getCurrentPlayer()]);
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(players.getCurrentPlayer() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			places[getCurrentPlayer()] = places[getCurrentPlayer()] + roll;
			if (places[getCurrentPlayer()] > 11) places[getCurrentPlayer()] = places[getCurrentPlayer()] - 12;

			System.out.println(players.getCurrentPlayer()
					+ "'s new location is " 
					+ places[getCurrentPlayer()]);
			System.out.println("The category is " + currentCategory());
			askQuestion();
		}
		
	}

	private void askQuestion() {
		if (currentCategory() == "Pop")
			System.out.println(popQuestions.removeFirst());
		if (currentCategory() == "Science")
			System.out.println(scienceQuestions.removeFirst());
		if (currentCategory() == "Sports")
			System.out.println(sportsQuestions.removeFirst());
		if (currentCategory() == "Rock")
			System.out.println(rockQuestions.removeFirst());		
	}
	
	
	private String currentCategory() {
		if (places[getCurrentPlayer()] == 0) return "Pop";
		if (places[getCurrentPlayer()] == 4) return "Pop";
		if (places[getCurrentPlayer()] == 8) return "Pop";
		if (places[getCurrentPlayer()] == 1) return "Science";
		if (places[getCurrentPlayer()] == 5) return "Science";
		if (places[getCurrentPlayer()] == 9) return "Science";
		if (places[getCurrentPlayer()] == 2) return "Sports";
		if (places[getCurrentPlayer()] == 6) return "Sports";
		if (places[getCurrentPlayer()] == 10) return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[getCurrentPlayer()]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[getCurrentPlayer()]++;
				System.out.println(players.getCurrentPlayer()
						+ " now has "
						+ purses[getCurrentPlayer()]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				players.nextPlayer();

				return winner;
			} else {
				players.nextPlayer();
				if (getCurrentPlayer() == players.size()) setCurrentPlayer(0);
				return true;
			}
			
			
			
		} else {
		
			System.out.println("Answer was corrent!!!!");
			purses[getCurrentPlayer()]++;
			System.out.println(players.getCurrentPlayer()
					+ " now has "
					+ purses[getCurrentPlayer()]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			players.nextPlayer();
			if (getCurrentPlayer() == players.size()) setCurrentPlayer(0);

			return winner;
		}
	}

	private void setCurrentPlayer(int position) {
		players.setCurrentPlayerPosition(position);
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.getCurrentPlayer() + " was sent to the penalty box");
		inPenaltyBox[getCurrentPlayer()] = true;

		players.nextPlayer();
		if (getCurrentPlayer() == players.size()) setCurrentPlayer(0);
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[getCurrentPlayer()] == 6);
	}

	public int getCurrentPlayer() {
		return players.getCurrentPlayerPosition();
	}
}

package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;
import java.util.Optional;

public class Game {
    private Players players;
	private Questions questions;

	int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];

    boolean isGettingOutOfPenaltyBox;

    public  Game(Players players, Questions questions){
    	this.players = players;
    	this.questions = questions;
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
				askQuestion(currentCategory());
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
			askQuestion(currentCategory());
		}
		
	}

	private void askQuestion(Category category) {
		this.questions.getNextQuestionFromCategory(category).ifPresent(question -> {
			System.out.println(question.getDescription());
			questions.removeQuestion(question.getId());
		});
	}
	
	
	private Category currentCategory() {
		int currentPlace = places[players.getCurrentPlayerPosition()];
		if (currentPlace == 0 || currentPlace == 4 || currentPlace == 8) return Category.POP;
		if (currentPlace == 1 || currentPlace == 5 || currentPlace == 9) return Category.SCIENCE;
		if (currentPlace == 2 || currentPlace == 6 || currentPlace == 10) return Category.SPORTS;
		return Category.ROCK;
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

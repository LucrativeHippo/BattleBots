package model;

public class GameBoard {
	
	public Hex[][] spaces;
	Robot robotArray[];	
	
	private int numHumans;
	private int numPlayers;
	private int boardSize;


	public GameBoard(int size) {

	}

	public int getNumHumans() {
		return numHumans;
	}

	public void setNumHumans(int numHumans) {
		this.numHumans = numHumans;
	}

	public int getNumPlayers() {
		return numPlayers;
	}

	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}
	

	
}

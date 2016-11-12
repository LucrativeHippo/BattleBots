package model;

public class GameBoard {
	
	public Hex[][] spaces;
	Robot robotArray[];	
	
	private int numHumans;
	private int numPlayers;
	private int boardSize;


	public GameBoard(int size) {
            if(size == 5){
                this.spaces = new Hex[9][9];
                boardSize = 5;
            }
            else if(size == 7){
                this.spaces = new Hex[13][13];
                boardSize = 7;
            }
            else{
                System.out.println("There is an invalid board size given.");
            }
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

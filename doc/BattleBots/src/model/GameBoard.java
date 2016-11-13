package model;

/**
 * 
 * @author mkp003
 */
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
                throw new RuntimeException();
            }
	}

        /**
         * This method will return the number of Human players that will be
         * playing on the current game
         * 
         * @return Integer representing the number of human players (0-6)
         */
	public int getNumHumans() {
		return numHumans;
	}

        /**
         * This method is responsible for setting the number of human players 
         * that will be playing the game.
         * 
         * @param numHumans The number of humans playing the game
         */
	public void setNumHumans(int numHumans) {
		this.numHumans = numHumans;
	}

        /**
         * This method will retrieve the total number of players (human and AI)
         * that are playing the game.
         * 
         * @return Integer representing the Total players in the game
         */
	public int getNumPlayers() {
		return numPlayers;
	}

        /**
         * This function will set the number of players that are involved in
         * playing the game (2, 3, or 6)
         * 
         * @param numPlayers Integer value of the total number of players
         */
	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

        /**
         * This function will return the board size that is currently being
         * played on.There are only 2 sizes: 5 or 7.
         * 
         * @return integer of either 5 or 7
         */
	public int getBoardSize() {
		return boardSize;
	}

        /**
         * This function will set the board size of the game. The size can only
         * be either 5 or 7, otherwise an exception is thrown.
         * 
         * @param boardSize 
         * @exception RuntimeException
         */
	public void setBoardSize(int boardSize) {
            if((boardSize == 5)||(boardSize == 7)){
		this.boardSize = boardSize;
            }
            else{
                System.out.println("Cannot have a game size other than 5 or 7");
                throw new RuntimeException();
            }
	}
	

	
}

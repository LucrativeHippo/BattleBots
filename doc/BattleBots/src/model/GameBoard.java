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
                for(int i = 0; i < 9; i++){
                    for(int j = 0; j < 9; j++){
                        this.spaces[i][j] = new Hex(i, j , 60 );
                    }
                }
                this.spaces[0][0].hexExists = false;
                this.spaces[1][0].hexExists = false;
                this.spaces[7][0].hexExists = false;
                this.spaces[8][0].hexExists = false;
                this.spaces[0][1].hexExists = false;
                this.spaces[7][1].hexExists = false;
                this.spaces[8][1].hexExists = false;
                this.spaces[0][2].hexExists = false;
                this.spaces[8][2].hexExists = false;
                this.spaces[8][3].hexExists = false;
                this.spaces[8][5].hexExists = false;
                this.spaces[0][6].hexExists = false;
                this.spaces[8][6].hexExists = false;
                this.spaces[0][7].hexExists = false;
                this.spaces[7][7].hexExists = false;
                this.spaces[8][7].hexExists = false;
                this.spaces[0][8].hexExists = false;
                this.spaces[1][8].hexExists = false;
                this.spaces[7][8].hexExists = false;
                this.spaces[8][8].hexExists = false;
                
            }
            else if(size == 7){
                this.spaces = new Hex[13][13];
                boardSize = 7;
                for(int i = 0; i < 13; i++){
                    for(int j = 0; j < 13; j++){
                        this.spaces[i][j] = new Hex(i, j, 60);
                    }
                }
                this.spaces[0][0].hexExists = false;
                this.spaces[1][0].hexExists = false;
                this.spaces[2][0].hexExists = false;
                this.spaces[10][0].hexExists = false;
                this.spaces[11][0].hexExists = false;
                this.spaces[12][0].hexExists = false;
                this.spaces[0][1].hexExists = false;
                this.spaces[1][1].hexExists = false;
                this.spaces[10][1].hexExists = false;
                this.spaces[11][1].hexExists = false;
                this.spaces[12][1].hexExists = false;
                this.spaces[0][2].hexExists = false;
                this.spaces[1][2].hexExists = false;
                this.spaces[11][2].hexExists = false;
                this.spaces[12][2].hexExists = false;
                this.spaces[0][3].hexExists = false;
                this.spaces[11][3].hexExists = false;
                this.spaces[12][3].hexExists = false;
                this.spaces[0][4].hexExists = false;
                this.spaces[12][4].hexExists = false;
                this.spaces[12][5].hexExists = false;
                this.spaces[12][7].hexExists = false;
                this.spaces[0][8].hexExists = false;
                this.spaces[12][8].hexExists = false;
                this.spaces[0][9].hexExists = false;
                this.spaces[11][9].hexExists = false;
                this.spaces[12][9].hexExists = false;
                this.spaces[0][10].hexExists = false;
                this.spaces[1][10].hexExists = false;
                this.spaces[11][10].hexExists = false;
                this.spaces[12][10].hexExists = false;
                this.spaces[0][11].hexExists = false;
                this.spaces[1][11].hexExists = false;
                this.spaces[10][11].hexExists = false;
                this.spaces[11][11].hexExists = false;
                this.spaces[12][11].hexExists = false;
                this.spaces[0][12].hexExists = false;
                this.spaces[1][12].hexExists = false;
                this.spaces[2][12].hexExists = false;
                this.spaces[10][12].hexExists = false;
                this.spaces[11][12].hexExists = false;
                this.spaces[12][12].hexExists = false;
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
        
        public static void main(String [] args) {
       Scout robot1 = new Scout("scout");
       Tank robot2 = new Tank("tank");
       Sniper robot3 = new Sniper("sniper");
       Gang redTeam = new Gang(robot1, robot3, robot2);
       GameBoard game = new GameBoard(5);
       
       if(robot3 == null){
           System.out.println("robot is null");
           //throw new RuntimeException();
       }
       else{
       game.spaces[5][5].robotList.add(robot3);
       game.spaces[5][5].robotList.add(robot2);
       game.spaces[5][5].robotList.add(robot1);
       robot1.board = game;
       robot2.board = game;
       robot3.board = game;
       System.out.println(game.spaces[5][5].robotList.size());
       //Testing the gameBoard Class
       robot1.shoot(game.spaces[5][5]);
       }
       System.out.println(robot2.getHealthLeft() + "tank");
       System.out.println(robot1.getHealthLeft() + "scout");
       System.out.println(robot3.getHealthLeft() + "sniper");
       
       
       ScoutAI robot4 = new ScoutAI("scoutAI", null);
       TankAI robot5 = new TankAI("tankAI", null);
       SniperAI robot6 = new SniperAI("sniperAI", null);
       
       game.spaces[3][3].robotList.add(robot4);
       game.spaces[3][3].robotList.add(robot5);
       game.spaces[3][3].robotList.add(robot6);
       
       robot4.board = game;
       robot5.board = game;
       robot6.board = game;
       
       robot4.setRelativeDirection(0);
       robot5.setRelativeDirection(0);
       robot6.setRelativeDirection(0);
       
       robot4.setHorizontalLocation(3);
       robot4.setVerticalLocation(3);
       robot5.setHorizontalLocation(3);
       robot5.setVerticalLocation(3);
       robot6.setHorizontalLocation(3);
       robot6.setVerticalLocation(3);
       
       robot4.scan();
       
       System.out.println(robot4.scannedRobotsList.contains(robot5));
       System.out.println(robot4.scannedRobotsList.contains(robot6));
       System.out.println(robot4.scannedRobotsList.contains(robot4));
       
       robot4.turn(3);
       System.out.println(robot4.getRelativeDirection());
       
       robot4.move();
       System.out.println("moved to" + robot4.getVerticalLocation());
       System.out.println("moved to" + robot4.getHorizontalLocation());
       
       System.out.println(game.spaces[2][3].robotList.contains(robot4));
       
       robot4.move();
       System.out.println("moved to" + robot4.getVerticalLocation());
       System.out.println("moved to" + robot4.getHorizontalLocation());
       
       System.out.println(game.spaces[1][3].robotList.contains(robot4));
       
       robot4.scan();
       
    }
	

	
}

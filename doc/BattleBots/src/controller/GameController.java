package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Game;
import model.GameInfo;
import model.GameObserver;
import model.Robot;
import model.RobotController;
import view.View;

public class GameController implements ActionListener, KeyListener, GameObserver{

	
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public Robot currentRobot;
    
    private View view;
	
    private GameInfo gameInfo;
    
    private RobotController gameControl;
    
        private int numPlayers;
        private int numHumans;
        private int boardSize;
        
        public void setNumPlayers(int numPlayers){
            this.numPlayers = numPlayers;
        }
        
        public int getNumPlayers(){
            return this.numPlayers;
        }
        
        public void setNumHumans(int numHumans){
            this.numHumans = numHumans;
        }
        
                
        public int getNumHumans(){
            return this.numPlayers;
        }
        
        public void setBoardSize(int boardSize){
            this.boardSize = boardSize;
        }

        
        public int getBoardSize(){
            return this.boardSize;
        }
	
    public void start() {
        view = new View(WIDTH, HEIGHT);
        view.showMainMenu(this); 
    }
    
    
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
            if(arg0.getKeyChar() == 'd'){
                currentRobot.move('d');
            }
            if(arg0.getKeyChar() == 'x'){
                currentRobot.move('x');
            }
            if(arg0.getKeyChar() == 'z'){
                currentRobot.move('z');
            }
            if(arg0.getKeyChar() == 'a'){
                currentRobot.move('a');
            }
            if(arg0.getKeyChar() == 'w'){
                currentRobot.move('w');
            }
            if(arg0.getKeyChar() == 'e'){
                currentRobot.move('e');
            }
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
            
         String actionCommand = arg0.getActionCommand();
		 if (actionCommand.equals("quit")){
	            System.exit(0);
		 }
	        else if (actionCommand.equals("help")){
	            view.showHelp(this, WIDTH, HEIGHT);
	        }
	        else if (actionCommand.equals("begin")){
	            view.showGameProperties(this);
	        }
	        else if (actionCommand.equals("backGP")){
	            view.showMainMenu(this);
	        }
	        else if (actionCommand.equals("continueGP")){
	            view.showTeamSelection(this);
	        }
	        else if (actionCommand.equals("continueTS")){
	        	Game game = new Game(WIDTH, HEIGHT);
		 		gameControl = game;
		 		gameInfo = game;
		 		gameInfo.addObserver(this); 		
	        	view.showGame(this, gameInfo);
	        	gameControl.start();
	        }
	        else if (actionCommand.equals("backTS")){
	            view.showGameProperties(this);
	        }
                                else if (actionCommand.equals("five")){
                    setBoardSize(5);
                }
                else if (actionCommand.equals("seven")){
                    this.setBoardSize(7);
                }
                else if (actionCommand.equals("zeroHumans")){
                    this.setNumHumans(0);
                }
                else if (actionCommand.equals("oneHuman")){
                    this.setNumHumans(1);
                }
                else if (actionCommand.equals("twoHumans")){
                    this.setNumHumans(2);
                }
                else if (actionCommand.equals("threeHumans")){
                    this.setNumHumans(3);
                }
                else if (actionCommand.equals("fourHumans")){
                    this.setNumHumans(4);
                }
                else if (actionCommand.equals("fiveHumans")){
                    this.setNumHumans(5);
                }
                else if (actionCommand.equals("sixHumans")){
                    this.setNumHumans(6);
                }
                else if (actionCommand.equals("twoTeams")){
                    this.setNumPlayers(2);
                }
                else if (actionCommand.equals("threeTeams")){
                    this.setNumPlayers(3);
                }
                else if (actionCommand.equals("sixTeams")){
                    this.setNumPlayers(6);
                }
	        else{
	        	throw new IllegalStateException("The event has action command " + actionCommand
	        			+ " that is invalid.");
	        }
		
	}

	@Override
	public void gameChanged() {
		// TODO Auto-generated method stub
		
	}
        
        public static void main(String [] args){
        	GameController gc = new GameController();
        	gc.start();        
        }

}

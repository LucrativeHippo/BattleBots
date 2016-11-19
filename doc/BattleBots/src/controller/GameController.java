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
    
    private int boardSize;
    private int numPlayers;
    private int numHumans;
    
    private boolean boardSizeSet = false;
    private boolean numPlayersSet = false;
    private boolean numHumansSet = false;

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
	public void actionPerformed(ActionEvent arg0) {
            
         String actionCommand = arg0.getActionCommand();
                switch(actionCommand){
                    case "quit":{
                            System.exit(0);
                            break;
                    }
                    case "help":{
                	    view.showHelp(this, WIDTH, HEIGHT);
                            break;
                    }
                    case "begin":{
                            view.showGameProperties(this);
                            break;
                    }
                    case "backGP":{
	                    view.showMainMenu(this);
                            break;
                    }
                    case "continueGP":{
                        if (boardSizeSet && numPlayersSet && numHumansSet ){
	                    view.showTeamSelection(this);
                            break;
                        }
                        else break;
                    }
                    case "continueTS":{
                            Game game = new Game(WIDTH, HEIGHT);
		 		gameControl = game;
		 		gameInfo = game;
		 		gameInfo.addObserver(this);
                                gameInfo.setNumPlayers(numPlayers);
                                gameInfo.setNumHumans(numHumans);
                                gameInfo.setBoardSize(boardSize);
	        	view.showGame(this, gameInfo);
	        	gameControl.start();
                        break;
                    }
                    case "backTS":{
                            view.showGameProperties(this);
                            break;
                    }
                    case "five":{
                            boardSizeSet = true;
                            boardSize = 5;
                            break;
                    }
                    case "seven":{
                            boardSizeSet = true;
                            boardSize = 7;
                            break;
                    }
                    case "zeroHumans":{
                        numHumansSet = true;
                        numHumans = 0;
                            break;
                            }
                    case "oneHuman":{
                        numHumansSet = true;
                        numHumans = 1;
                            break;
                            }
                    case "twoHumans":{
                        numHumansSet = true;
                        numHumans = 2;
                            break;
                            }
                    case "threeHumans":{
                        numHumansSet = true;
                        numHumans = 3;
                            break;
                            }
                    case "fourHumans":{
                        numHumansSet = true;
                        numHumans = 4;
                            break;
                            }
                    case "fiveHumans":{
                        numHumansSet = true;
                        numHumans = 5;
                            break;
                            }
                    case "sixHumans":{
                        numHumansSet = true;
                        numHumans = 6;
                            break;
                            }
                    case "twoTeams":{
                        numPlayersSet = true;
                        numPlayers = 2;
                            break;
                            }
                    case "threeTeams":{
                        numPlayersSet = true;
                        numPlayers= 3;
                            break;
                            }
                    case "sixTeams":{
                        numPlayersSet = true;
                        numPlayers = 6;
                            break;
                            }
                    default:{
                            throw new IllegalStateException("The event has action command " + actionCommand
	        			+ " that is invalid.");
                            }
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

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

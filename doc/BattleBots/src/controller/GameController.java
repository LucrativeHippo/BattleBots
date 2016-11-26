package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import model.Game;
import model.GameBoard;
import model.GameInfo;
import model.GameObserver;
import model.Gang;
import model.GangAI;
import model.Robot;
import model.RobotController;
import model.Scout;
import model.ScoutAI;
import model.Sniper;
import model.SniperAI;
import model.Tank;
import model.TankAI;
import org.json.simple.JSONObject;
import view.View;

public class GameController implements ActionListener, KeyListener, GameObserver{

	
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public Robot currentRobot;
    
    private View view;
	
    private GameInfo gameInfo;
    
    private RobotController gameControl;
    
    private int boardSize = 7;
    private int numPlayers = 6;
    private int numHumans = 0;
    
    private Stack<JSONObject> chosenRobotCodes;
    
    private List<Robot> robotTurnOrder;
    

    public void start() {
        chosenRobotCodes = new Stack();
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
	                    view.showTeamSelection(this);
                            break;
                    }
                    case "continueTS":{
                            Game game = new Game(WIDTH, HEIGHT);
		 		gameControl = game;
		 		gameInfo = game;
		 		gameInfo.addObserver(this);
                                gameInfo.setNumPlayers(numPlayers);
                                gameInfo.setNumHumans(numHumans);
                                gameInfo.setBoardSize(boardSize);
	        	view.showGame(this, this, gameInfo);
	        	gameControl.start();
                        break;
                    }
                    case "backTS":{
                            view.showGameProperties(this);
                            break;
                    }
                    case "five":{
                            boardSize = 5;
                            numPlayers = 2;
                            break;
                    }
                    case "seven":{
                            boardSize = 7;
                            numPlayers = 6;
                            break;
                    }
                    case "zeroHumans":{
                        numHumans = 0;
                            break;
                            }
                    case "oneHuman":{
                        numHumans = 1;
                            break;
                            }
                    case "twoHumans":{
                        numHumans = 2;
                            break;
                            }
                    case "threeHumans":{
                        numHumans = 3;
                            break;
                            }
                    case "fourHumans":{
                        numHumans = 4;
                            break;
                            }
                    case "fiveHumans":{
                        numHumans = 5;
                            break;
                            }
                    case "sixHumans":{
                        numHumans = 6;
                            break;
                            }
                    case "twoTeams":{
                        numPlayers = 2;
                        boardSize = 5;
                            break;
                            }
                    case "threeTeams":{
                        numPlayers= 3;
                            break;
                            }
                    case "sixTeams":{
                        numPlayers = 6;
                        boardSize = 7;
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
        
        /**
         * This function will generate all of the robots and their gangs with
         * the given stack of robot codes given from the team selection page.
         * It will then return a Linked list of Robots all assigned and in the 
         * order of their place in a round.
         * @return LinkedList of robots in play
         */
        public LinkedList<Robot> createTeams(){
            Stack humanGangs = new <Gang>Stack();
            Stack CMPTGangs = new <GangAI>Stack();
            Robot array[] = new Robot[numPlayers*3];
            Interpreter assign = new Interpreter();
            //For however many humans there are, generate the robots off of the 
            //Stack of robot code and put them into a new Gang
            for ( int i = 0; i < numHumans; i++){
                Scout temp1 = new Scout(assign.getRobotName(chosenRobotCodes.pop()));
                Sniper temp2 = new Sniper(assign.getRobotName(chosenRobotCodes.pop()));
                Tank temp3 = new Tank(assign.getRobotName(chosenRobotCodes.pop()));
                Gang humans = new Gang(temp1, temp2, temp3);
                humanGangs.push(humans);
            }
            //Similar to the for loop above, we will do the same except we will
            //do it for computer controlled robots
            for ( int j = 0; j < (numPlayers - numHumans); j++){
                ScoutAI temp11 = new ScoutAI(assign.getRobotName(chosenRobotCodes.peek()), chosenRobotCodes.peek());
                chosenRobotCodes.pop();
                SniperAI temp21 = new SniperAI(assign.getRobotName(chosenRobotCodes.peek()), chosenRobotCodes.peek());
                chosenRobotCodes.pop();
                TankAI temp31 = new TankAI(assign.getRobotName(chosenRobotCodes.peek()), chosenRobotCodes.peek());
                chosenRobotCodes.pop();
                GangAI CMPT = new GangAI(temp11, temp21, temp31);
                CMPTGangs.push(CMPT);
            }
            //Depending on how many players there are, we will need to have the
            //correct colors of teams avaliable to assign
            Stack<String> teams = new Stack<String>();
            if(numPlayers == 2){
                teams.push("GREEN");
                teams.push("RED");
            }
            else if(numPlayers == 3){
                teams.push("BLUE");
                teams.push("YELLOW");
                teams.push("RED");
            }
            else{
                teams.push("PURPLE");
                teams.push("BLUE");
                teams.push("GREEN");
                teams.push("YELLOW");
                teams.push("ORANGE");
                teams.push("RED");
            }
            //Each gang will be assigned a color team and then added to a list
            LinkedList<Robot> listOfRobots = new LinkedList();
            for(int k = 0; k < numPlayers; k++){
                while(!humanGangs.empty()){
                    Gang toColor = (Gang) humanGangs.pop();
                    toColor.setTeam(teams.pop());
                    listOfRobots.addLast(toColor.getScoutRobot());
                    listOfRobots.addLast(toColor.getSniperRobot());
                    listOfRobots.addLast(toColor.getTankRobot());
                }
                while(!CMPTGangs.empty()){
                    GangAI toColor2 = (GangAI) CMPTGangs.pop();
                    toColor2.setTeam(teams.pop());
                    listOfRobots.addLast(toColor2.getScout());
                    listOfRobots.addLast(toColor2.getSniper());
                    listOfRobots.addLast(toColor2.getTank());
                }
            }
            //We want the list of robots to be in the order of all scouts,
            //followed by snipers, followed by tanks.
            int count = 0;
            while(!listOfRobots.isEmpty()){
                array[count] =listOfRobots.removeFirst();
                count = count + 3;
                if(count > numPlayers*3){
                    count = count%(numPlayers*3);
                }
            }
            listOfRobots.clear();
            for(int l = 0; l < (numPlayers*3); l++){
                listOfRobots.add(array[l]);
            }
            return listOfRobots;
        }
        
        /**
         * This function takes in the gameBoard and then places all the teams 
         * into the correct starting spaces on the board based on the number
         * of players and the size of the board
         * @param gameBoard 
         */
        public void setTeamPositions(GameBoard gameBoard){
            Iterator<Robot> iterate = gameBoard.robotList.iterator();
            if(gameBoard.getBoardSize() == 5){
                if(numPlayers == 2){
                    //RED team
                    gameBoard.spaces[0][4].robotList.add(iterate.next());
                    gameBoard.spaces[0][4].robotList.add(iterate.next());
                    gameBoard.spaces[0][4].robotList.add(iterate.next());
                    //GREEN team
                    gameBoard.spaces[8][4].robotList.add(iterate.next());
                    gameBoard.spaces[8][4].robotList.add(iterate.next());
                    gameBoard.spaces[8][4].robotList.add(iterate.next());
                }
                else{//number of players must be 3
                    //RED team
                    gameBoard.spaces[0][4].robotList.add(iterate.next());
                    gameBoard.spaces[0][4].robotList.add(iterate.next());
                    gameBoard.spaces[0][4].robotList.add(iterate.next());
                    //YELLOW team
                    gameBoard.spaces[6][0].robotList.add(iterate.next());
                    gameBoard.spaces[6][0].robotList.add(iterate.next());
                    gameBoard.spaces[6][0].robotList.add(iterate.next());
                    //BLUE team
                    gameBoard.spaces[6][8].robotList.add(iterate.next());
                    gameBoard.spaces[6][8].robotList.add(iterate.next());
                    gameBoard.spaces[6][8].robotList.add(iterate.next());
                }
            }
            else{
                if(numPlayers == 3){
                    //RED team
                    gameBoard.spaces[0][6].robotList.add(iterate.next());
                    gameBoard.spaces[0][6].robotList.add(iterate.next());
                    gameBoard.spaces[0][6].robotList.add(iterate.next());
                    //YELLOW team
                    gameBoard.spaces[9][0].robotList.add(iterate.next());
                    gameBoard.spaces[9][0].robotList.add(iterate.next());
                    gameBoard.spaces[9][0].robotList.add(iterate.next());
                    //BLUE team
                    gameBoard.spaces[9][12].robotList.add(iterate.next());
                    gameBoard.spaces[9][12].robotList.add(iterate.next());
                    gameBoard.spaces[9][12].robotList.add(iterate.next());
                }
                else{
                    //RED team
                    gameBoard.spaces[0][6].robotList.add(iterate.next());
                    gameBoard.spaces[0][6].robotList.add(iterate.next());
                    gameBoard.spaces[0][6].robotList.add(iterate.next());
                    //ORANGE team
                    gameBoard.spaces[3][0].robotList.add(iterate.next());
                    gameBoard.spaces[3][0].robotList.add(iterate.next());
                    gameBoard.spaces[3][0].robotList.add(iterate.next());
                    //YELLOW team
                    gameBoard.spaces[9][0].robotList.add(iterate.next());
                    gameBoard.spaces[9][0].robotList.add(iterate.next());
                    gameBoard.spaces[9][0].robotList.add(iterate.next());
                    //GREEN team
                    gameBoard.spaces[12][6].robotList.add(iterate.next());
                    gameBoard.spaces[12][6].robotList.add(iterate.next());
                    gameBoard.spaces[12][6].robotList.add(iterate.next());
                    //BLUE team
                    gameBoard.spaces[9][12].robotList.add(iterate.next());
                    gameBoard.spaces[9][12].robotList.add(iterate.next());
                    gameBoard.spaces[9][12].robotList.add(iterate.next());
                    //PURPLE team
                    gameBoard.spaces[3][12].robotList.add(iterate.next());
                    gameBoard.spaces[3][12].robotList.add(iterate.next());
                    gameBoard.spaces[3][12].robotList.add(iterate.next());
                }
            }
        }
        
        /**
         * This function will determine if the game is over; that either there
         * are no robots left in play or there is only robots from one team left 
         * in play; a boolean of true or false will be returned
         * @param AliveRobotList
         * @return True if the game is over, false otherwise
         */
        public boolean isGameOver(LinkedList<Robot> AliveRobotList){
            if(AliveRobotList.isEmpty()){//Are there no robots in play?
                return true;
            }
            Iterator<Robot> iterate = AliveRobotList.iterator();
            String firstColor = iterate.next().getGang();
            while(iterate.hasNext()){
                //If there are any robots that have a different color than the
                //First robot in the list, then the game is not over
                if(firstColor.compareTo(iterate.next().getGang()) != 0){
                    return false;
                }
            }
            return true;
        }
        
        
        public void play(GameBoard gameBoard){
            while(!isGameOver(gameBoard.deadAliveList)){
                Iterator<Robot> turnIterate = gameBoard.deadAliveList.iterator();
                Robot currentPlayer = turnIterate.next();
               // if(currentPlayer.)
            }
            
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

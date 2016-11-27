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
import java.util.logging.Level;
import java.util.logging.Logger;

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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import view.View;

public class GameController implements ActionListener, KeyListener, GameObserver{

	
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public Robot currentRobot;
    
    public static GameController gameController;
    
    public View view;
	
    private GameInfo gameInfo;
    
    private RobotController gameControl;
    
    private int boardSize = 7;
    private int numPlayers = 6;
    private int numHumans = 0;
    
    public static Stack<JSONObject> chosenRobotCodes;
    
    private List<Robot> robotTurnOrder;
    
    static public GameBoard gameBoard; ////Need to set this
    

    public void start() {
        chosenRobotCodes = new Stack();
        view = new View(WIDTH, HEIGHT);
        JSONObject testRobot = new JSONObject();
        testRobot.put("team", "A5");
        testRobot.put("class", "Scout");
        testRobot.put("name", "runner");
        testRobot.put("matches", 5);
        testRobot.put("wins", 2);
        testRobot.put("losses", 3);
        testRobot.put("executions", 5);
        testRobot.put("lived", 1);
        testRobot.put("died", 4);
        testRobot.put("absorbed", 4);
        testRobot.put("killed", 0);
        testRobot.put("moved", 17);
        JSONArray list = new JSONArray();
        list.add("1 1 +");
        testRobot.put("code", list);
        JSONObject testScript = new JSONObject();
        testScript.put("script", testRobot);
        chosenRobotCodes.push(testScript);
        chosenRobotCodes.push(testScript);
        chosenRobotCodes.push(testScript);
        chosenRobotCodes.push(testScript);
        chosenRobotCodes.push(testScript);
        chosenRobotCodes.push(testScript);
        
        chosenRobotCodes.push(testScript);
        chosenRobotCodes.push(testScript);
        chosenRobotCodes.push(testScript);
        chosenRobotCodes.push(testScript);
        chosenRobotCodes.push(testScript);
        chosenRobotCodes.push(testScript);
        
        chosenRobotCodes.push(testScript);
        chosenRobotCodes.push(testScript);
        chosenRobotCodes.push(testScript);
        chosenRobotCodes.push(testScript);
        chosenRobotCodes.push(testScript);
        chosenRobotCodes.push(testScript);
        view.showMainMenu(this); 
        //These robot codes are for testing, must remove at some point
        
    }
    
    
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
            if(arg0.getKeyChar() == 'd'){
                try {
                    currentRobot.move('d');
                } catch (Exception ex) {
                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(arg0.getKeyChar() == 'x'){
                try {
                    currentRobot.move('x');
                } catch (Exception ex) {
                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(arg0.getKeyChar() == 'z'){
                try {
                    currentRobot.move('z');
                } catch (Exception ex) {
                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(arg0.getKeyChar() == 'a'){
                try {
                    currentRobot.move('a');
                } catch (Exception ex) {
                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(arg0.getKeyChar() == 'w'){
                try {
                    currentRobot.move('w');
                } catch (Exception ex) {
                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(arg0.getKeyChar() == 'e'){
                try {
                    currentRobot.move('e');
                } catch (Exception ex) {
                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                    case "playagain":{
                        view.showGameProperties(this);
                    }
                    case "stats":{
                        view.showStats(this);
                    }
                    case "endTurn":{
                        
                        Iterator<Robot> iterate = gameController.gameBoard.aliveList.iterator();
                        while(iterate.hasNext() && gameInfo.getCurrentRobot() != iterate.next()){
                        }
                        if (!iterate.hasNext() ){
                            iterate = gameController.gameBoard.aliveList.iterator();
                            gameInfo.getCurrentRobot().restartParameters();
                            gameInfo.setCurrentRobot(iterate.next());
                            if (!gameInfo.getCurrentRobot().isHuman()){
                              Interpreter interpret = new Interpreter();
                              ScoutAI temp = (ScoutAI) gameInfo.getCurrentRobot();
                                try {
                                    interpret.executeCode(temp.getCode(), gameInfo.getCurrentRobot());
                                    System.out.println("hihihihihihihihi");
                                    System.out.println(interpret.getRobotClass(temp.getCode()));
                                } catch (NoSuchMethodException ex) {
                                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        else{
                            gameInfo.getCurrentRobot().restartParameters();
                            gameInfo.setCurrentRobot(iterate.next());
                            if (!gameInfo.getCurrentRobot().isHuman()){
                        Interpreter interpret = new Interpreter();
                       // ScoutAI temp = (ScoutAI) gameInfo.getCurrentRobot();
                                try {
                                    interpret.executeCode(gameInfo.getCurrentRobot().getCode(), gameInfo.getCurrentRobot());
                                } catch (NoSuchMethodException ex) {
                                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            
                        }
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
             try {
                 view.showGame(this, this, gameInfo);
             } catch (NoSuchMethodException ex) {
                 Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
             }
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
        public LinkedList<Robot> createTeams(GameBoard game){
            Stack humanGangs = new <Gang>Stack();
            Stack CMPTGangs = new <GangAI>Stack();
            Robot array[] = new Robot[numPlayers*3];
            Interpreter assign = new Interpreter();
            System.out.println("Humans: " + numHumans );
            //For however many humans there are, generate the robots off of the 
            //Stack of robot code and put them into a new Gang
            for ( int i = 0; i < numHumans; i++){
                Scout temp1 = new Scout(assign.getRobotName(chosenRobotCodes.pop()));
                temp1.setGameBoard(game);
                Sniper temp2 = new Sniper(assign.getRobotName(chosenRobotCodes.pop()));
                temp2.setGameBoard(game);
                Tank temp3 = new Tank(assign.getRobotName(chosenRobotCodes.pop()));
                temp3.setGameBoard(game);
                Gang humans = new Gang(temp1, temp2, temp3);
                humanGangs.push(humans);
            }
            //Similar to the for loop above, we will do the same except we will
            //do it for computer controlled robots
            for ( int j = 0; j < (numPlayers - numHumans); j++){
                ScoutAI temp11 = new ScoutAI(assign.getRobotName(chosenRobotCodes.peek()), chosenRobotCodes.peek());
                temp11.setGameBoard(game);
                chosenRobotCodes.pop();
                SniperAI temp21 = new SniperAI(assign.getRobotName(chosenRobotCodes.peek()), chosenRobotCodes.peek());
                temp21.setGameBoard(game);
                chosenRobotCodes.pop();
                TankAI temp31 = new TankAI(assign.getRobotName(chosenRobotCodes.peek()), chosenRobotCodes.peek());
                temp31.setGameBoard(game);
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
                    System.out.println("Size: " + humanGangs.size());
                    Gang toColor = (Gang) humanGangs.pop();
                    toColor.setTeam(teams.pop());
                    listOfRobots.addLast(toColor.getScoutRobot());
                    listOfRobots.addLast(toColor.getSniperRobot());
                    listOfRobots.addLast(toColor.getTankRobot());
                }
                while(!CMPTGangs.empty()){
                    GangAI toColor2 = (GangAI) CMPTGangs.pop();
                    toColor2.setTeam(teams.pop());
                    if(toColor2.getTeam().compareTo("RED") == 0){
                      toColor2.getScout().setRelativeDirection(0);
                      toColor2.getSniper().setRelativeDirection(0);
                      toColor2.getTank().setRelativeDirection(0);
                    }
                    if(toColor2.getTeam().compareTo("ORANGE") == 0){
                      toColor2.getScout().setRelativeDirection(1);
                      toColor2.getSniper().setRelativeDirection(1);
                      toColor2.getTank().setRelativeDirection(1);
                    }
                    if(toColor2.getTeam().compareTo("YELLOW") == 0){
                      toColor2.getScout().setRelativeDirection(2);
                      toColor2.getSniper().setRelativeDirection(2);
                      toColor2.getTank().setRelativeDirection(2);
                    }
                    if(toColor2.getTeam().compareTo("GREEN") == 0){
                      toColor2.getScout().setRelativeDirection(3);
                      toColor2.getSniper().setRelativeDirection(3);
                      toColor2.getTank().setRelativeDirection(3);
                    }
                    if(toColor2.getTeam().compareTo("BLUE") == 0){
                      toColor2.getScout().setRelativeDirection(4);
                      toColor2.getSniper().setRelativeDirection(4);
                      toColor2.getTank().setRelativeDirection(4);
                    }
                    if(toColor2.getTeam().compareTo("PURPLE") == 0){
                      toColor2.getScout().setRelativeDirection(5);
                      toColor2.getSniper().setRelativeDirection(5);
                      toColor2.getTank().setRelativeDirection(5);
                    }
                    listOfRobots.addLast(toColor2.getScout());
                    listOfRobots.addLast(toColor2.getSniper());
                    listOfRobots.addLast(toColor2.getTank());
                }
            }

            //We want the list of robots to be in the order of all scouts,
            //followed by snipers, followed by tanks.
            int count = 0;
            while(!listOfRobots.isEmpty()){
                array[count] =listOfRobots.pop();
                count = count + numPlayers;
                if(count >= numPlayers*3){
                    count = count%((numPlayers*3)-1);
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
                    //RED then GREEN repeat
                    gameBoard.spaces[0][4].robotList.add(iterate.next());
                    gameBoard.spaces[8][4].robotList.add(iterate.next());
                    gameBoard.spaces[0][4].robotList.add(iterate.next());
                    gameBoard.spaces[8][4].robotList.add(iterate.next());
                    gameBoard.spaces[0][4].robotList.add(iterate.next());
                    gameBoard.spaces[8][4].robotList.add(iterate.next());
                    System.out.println(gameBoard.spaces[0][4].robotList.size());
                    Iterator<Robot> iterate2 = gameBoard.spaces[0][4].robotList.iterator();
                    while(iterate2.hasNext()){
                        iterate2.next().setVerticalAndHorizontal(0, 4);
                    }
                    iterate2 = gameBoard.spaces[8][4].robotList.iterator();
                    while(iterate2.hasNext()){
                        iterate2.next().setVerticalAndHorizontal(8, 4);
                    }
                }
                else{//number of players must be 3
                    //RED the YELLOW the BLUE
                    gameBoard.spaces[0][4].robotList.add(iterate.next());
                    gameBoard.spaces[6][0].robotList.add(iterate.next());
                    gameBoard.spaces[6][8].robotList.add(iterate.next());
                    gameBoard.spaces[0][4].robotList.add(iterate.next());
                    gameBoard.spaces[6][0].robotList.add(iterate.next());
                    gameBoard.spaces[6][8].robotList.add(iterate.next());
                    gameBoard.spaces[0][4].robotList.add(iterate.next());
                    gameBoard.spaces[6][0].robotList.add(iterate.next());
                    gameBoard.spaces[6][8].robotList.add(iterate.next());
                    Iterator<Robot> iterate2 = gameBoard.spaces[0][4].robotList.iterator();
                    while(iterate2.hasNext()){
                        iterate2.next().setVerticalAndHorizontal(0, 4);
                    }
                    iterate2 = gameBoard.spaces[6][0].robotList.iterator();
                    while(iterate2.hasNext()){
                        iterate2.next().setVerticalAndHorizontal(6, 0);
                    }
                    iterate2 = gameBoard.spaces[6][8].robotList.iterator();
                    while(iterate2.hasNext()){
                        iterate2.next().setVerticalAndHorizontal(6, 8);
                    }
                }
            }
            else{
                if(numPlayers == 3){
                    //RED the YELLOW the BLUE
                    gameBoard.spaces[0][6].robotList.add(iterate.next());
                    gameBoard.spaces[9][0].robotList.add(iterate.next());
                    gameBoard.spaces[9][12].robotList.add(iterate.next());
                    gameBoard.spaces[0][6].robotList.add(iterate.next());
                    gameBoard.spaces[9][0].robotList.add(iterate.next());
                    gameBoard.spaces[9][12].robotList.add(iterate.next());
                    gameBoard.spaces[0][6].robotList.add(iterate.next());
                    gameBoard.spaces[9][0].robotList.add(iterate.next());
                    gameBoard.spaces[9][12].robotList.add(iterate.next());
                    Iterator<Robot> iterate2 = gameBoard.spaces[0][6].robotList.iterator();
                    while(iterate2.hasNext()){
                        iterate2.next().setVerticalAndHorizontal(0, 6);
                    }
                    iterate2 = gameBoard.spaces[9][0].robotList.iterator();
                    while(iterate2.hasNext()){
                        iterate2.next().setVerticalAndHorizontal(9, 0);
                    }
                    iterate2 = gameBoard.spaces[9][12].robotList.iterator();
                    while(iterate2.hasNext()){
                        iterate2.next().setVerticalAndHorizontal(9, 12);
                    }
                }
                else{
                    //RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE
                    //scout
                    gameBoard.spaces[0][6].robotList.add(iterate.next());
                    gameBoard.spaces[3][0].robotList.add(iterate.next());
                    gameBoard.spaces[9][0].robotList.add(iterate.next());
                    gameBoard.spaces[12][6].robotList.add(iterate.next());
                    gameBoard.spaces[9][12].robotList.add(iterate.next());
                    gameBoard.spaces[3][12].robotList.add(iterate.next());
                    //Sniper
                    gameBoard.spaces[0][6].robotList.add(iterate.next());
                    gameBoard.spaces[3][0].robotList.add(iterate.next());
                    gameBoard.spaces[9][0].robotList.add(iterate.next());
                    gameBoard.spaces[12][6].robotList.add(iterate.next());
                    gameBoard.spaces[9][12].robotList.add(iterate.next());
                    gameBoard.spaces[3][12].robotList.add(iterate.next());
                    //Tank
                    gameBoard.spaces[0][6].robotList.add(iterate.next());
                    gameBoard.spaces[3][0].robotList.add(iterate.next());
                    gameBoard.spaces[9][0].robotList.add(iterate.next());
                    gameBoard.spaces[12][6].robotList.add(iterate.next());
                    gameBoard.spaces[9][12].robotList.add(iterate.next());
                    gameBoard.spaces[3][12].robotList.add(iterate.next());
                    Iterator<Robot> iterate2 = gameBoard.spaces[0][6].robotList.iterator();
                    while(iterate2.hasNext()){
                        iterate2.next().setVerticalAndHorizontal(0, 6);
                    }
                    iterate2 = gameBoard.spaces[3][0].robotList.iterator();
                    while(iterate2.hasNext()){
                        iterate2.next().setVerticalAndHorizontal(3, 0);
                    }
                    iterate2 = gameBoard.spaces[9][0].robotList.iterator();
                    while(iterate2.hasNext()){
                        iterate2.next().setVerticalAndHorizontal(9, 0);
                    }
                    iterate2 = gameBoard.spaces[12][6].robotList.iterator();
                    while(iterate2.hasNext()){
                        iterate2.next().setVerticalAndHorizontal(12, 6);
                    }
                    iterate2 = gameBoard.spaces[9][12].robotList.iterator();
                    while(iterate2.hasNext()){
                        iterate2.next().setVerticalAndHorizontal(9, 12);
                    }
                    iterate2 = gameBoard.spaces[3][12].robotList.iterator();
                    while(iterate2.hasNext()){
                        iterate2.next().setVerticalAndHorizontal(3, 12);
                    }
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
        
        
        public void play(GameBoard gameBoard) throws NoSuchMethodException{
            while(!isGameOver(gameBoard.aliveList)){
                Iterator<Robot> turnIterate = gameBoard.aliveList.iterator();
                currentRobot = turnIterate.next();
                if(!currentRobot.isHuman()){
                    ScoutAI temp = (ScoutAI) currentRobot;
                    JSONObject code = temp.getCode();
                    Interpreter interpret = new Interpreter();
                    interpret.executeCode(code, temp);
                }
                else{
                    
                }
                
            }
            
        }
        
        
        
        
        
        public static void main(String [] args){
        	GameController gc = new GameController();
                gameController = gc;
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

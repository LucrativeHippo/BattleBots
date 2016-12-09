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
import javax.swing.SwingUtilities;

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
import static view.TeamSelectionPanel.selectedRobots;

public class GameController implements ActionListener, KeyListener, GameObserver {

  public static final int WIDTH = 800;  // width of the View JFrame 
  public static final int HEIGHT = 600; // height of the View JFrame
  public Robot currentRobot; // during game play this will be set to proper robot

  public static GameController gameController;

  public View view;

  private GameInfo gameInfo;
  
  // boolean to determine whether default robots need to be initialized
  // into the TeamSelectionPanel
  public static boolean firstPlay = true;

  private RobotController gameControl;  //not really used
  
  
  // stores the board size, initialized to the default radio button setting
  private int boardSize = 5;    
  
  // stores the number of Players in the game, initialized to default 
  //radio button setting
  public static int numPlayers = 3;
  
  // stores the number of human players in the game, initialized to default 
  // radio button setting
  private int numHumans = 0;

  // AI codes avaliable to play game
  public static Stack<JSONObject> chosenRobotCodes;

  private List<Robot> robotTurnOrder;   // not used

  // reference to the gameboard 
  static public GameBoard gameBoard; ////Need to set this

  public void start() {
    chosenRobotCodes = new Stack();
    view = new View(WIDTH, HEIGHT);
    if(firstPlay == true){
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
    list.add("variable moved ; ( have i moved? )");
    list.add(": moved? moved ? ; ");
    list.add("moved false ! 15 15 ; ");
    list.add(": firstMove ( move to center first ) moved ? if ( already moved ) else move move move true moved ! then ;");
    list.add("variable shot ;  ( have I shot this play? )");
    list.add(": canShoot? ( --b ) ( shot available? ) shot ? ; ");
    list.add(": shoot!! ( id ir -- ) ( shoot if allowed ) canShoot? if drop drop ( remove ir id ) else shoot! ( really shoot ) shot true ! ( remember it ) then then ; ");
    list.add(": doNotShoot ( id ir -- ) ( pretend shoot ) drop drop ; ");
    list.add(": enemy? ( s--b ) ( decide if enemy ) team <> ;");
    list.add(": nonZeroRange? ( i --bi ) dup 0 <> ; ");
    list.add(": tryShooting! ( ih id ir st -- ) enemy? swap nonZeroRange? rot and if shoot!! else doNotShoot then drop ( remove ih ) ;");
    list.add(": shootEveryone ( try shot at all targets ) scan! 1 - dup 0 < if ( no one to shoot at ) else 0 do identify! tryShooting! loop then ;");
    list.add(": play ( -- ) false moved ! firstMove shot false ! ( prepare to shoot ) shootEveryone ; ");
    //list.add("moved? ;");

    testRobot.put("code", list);
    JSONObject testScript = new JSONObject();
    testScript.put("script", testRobot);
    chosenRobotCodes.push(testScript);
    
    
    JSONObject testRobot1 = new JSONObject();
    testRobot1.put("team", "A5");
    testRobot1.put("class", "Sniper");
    testRobot1.put("name", "sniper");
    testRobot1.put("matches", 5);
    testRobot1.put("wins", 2);
    testRobot1.put("losses", 3);
    testRobot1.put("executions", 5);
    testRobot1.put("lived", 1);
    testRobot1.put("died", 4);
    testRobot1.put("absorbed", 4);
    testRobot1.put("killed", 0);
    testRobot1.put("moved", 17);
    JSONArray list1 = new JSONArray();
    //Centralizer
    /*
    list.add("variable moved ; ( have i moved? )");
    list.add(": moved? moved ? ; ");
    list.add("moved false ! 15 15 ; ");
    list.add(": firstMove ( move to center first ) moved ? if ( already moved ) else move move move true moved ! then ;");
    list.add("variable shot ;  ( have I shot this play? )");
    list.add(": canShoot? ( --b ) ( shot available? ) shot ? ; ");
    list.add(": shoot!! ( id ir -- ) ( shoot if allowed ) canShoot? if drop drop ( remove ir id ) else shoot! ( really shoot ) shot true ! ( remember it ) then then ; ");
    list.add(": doNotShoot ( id ir -- ) ( pretend shoot ) drop drop ; ");
    list.add(": enemy? ( s--b ) ( decide if enemy ) team <> ;");
    list.add(": nonZeroRange? ( i --bi ) dup 0 <> ; ");
    list.add(": tryShooting! ( ih id ir st -- ) enemy? swap nonZeroRange? rot and if shoot!! else doNotShoot then drop ( remove ih ) ;");
    list.add(": shootEveryone ( try shot at all targets ) scan! 1 - dup 0 < if ( no one to shoot at ) else 0 do identify! tryShooting! loop then ;");
    list.add(": play ( -- ) false moved ! firstMove shot false ! ( prepare to shoot ) shootEveryone ; ");
    //list.add("moved? ;");
    */
    list.add("variable moved ; ( have i moved? )");
    list.add(": moved? moved ? ; ");
    list.add("moved false ! ");
    list.add(": firstMove ( align along left edge ) moved? if ( already moved ) else 5 turn! then ;");
    list.add(": edgeMove ( -- ) ( move along the edge ) 0 check! .\" OUT OF BOUNDS \" = if 1 turn! else ( ) then move ; ");
    list.add(": noMovesLeft? ( --b ) ( no moves left? ) movesLeft 0 <> ; ");
    list.add("variable shot ;  ( have I shot this play? )");
    list.add(": canShoot? ( --b ) ( shot available? ) shot ? ; ");
    list.add(": shoot!! ( id ir -- ) ( shoot if allowed ) canShoot? if drop drop ( remove ir id ) else shoot! ( really shoot ) shot true ! ( remember it ) then then ; ");
    list.add(": doNotShoot ( id ir -- ) ( pretend shoot ) drop drop ; ");
    list.add(": enemy? ( s--b ) ( decide if enemy ) team <> ;");
    list.add(": nonZeroRange? ( i --bi ) dup 0 <> ; ");
    list.add(": tryShooting! ( ih id ir st -- ) enemy? swap nonZeroRange? rot and if shoot!! else doNotShoot then drop ( remove ih ) ;");
    list.add(": shootEveryone ( try shot at all targets ) scan! 1 - dup 0 < if ( no one to shoot at ) else 0 do identify! tryShooting! loop then ;");
    list.add(": play ( -- ) firstMove shot false ! ( prepare to shoot ) begin edgeMove shootEveryone noMovesLeft? until ; ");

    testRobot1.put("code", list1);
    JSONObject testScript1 = new JSONObject();
    testScript1.put("script", testRobot1);
    chosenRobotCodes.push(testScript1);
    
    JSONObject testRobot2 = new JSONObject();
    testRobot2.put("team", "A5");
    testRobot2.put("class", "Tank");
    testRobot2.put("name", "tanker");
    testRobot2.put("matches", 5);
    testRobot2.put("wins", 2);
    testRobot2.put("losses", 3);
    testRobot2.put("executions", 5);
    testRobot2.put("lived", 1);
    testRobot2.put("died", 4);
    testRobot2.put("absorbed", 4);
    testRobot2.put("killed", 0);
    testRobot2.put("moved", 17);
    JSONArray list2 = new JSONArray();
    //Centralizer
    /*
    list.add("variable moved ; ( have i moved? )");
    list.add(": moved? moved ? ; ");
    list.add("moved false ! 15 15 ; ");
    list.add(": firstMove ( move to center first ) moved ? if ( already moved ) else move move move true moved ! then ;");
    list.add("variable shot ;  ( have I shot this play? )");
    list.add(": canShoot? ( --b ) ( shot available? ) shot ? ; ");
    list.add(": shoot!! ( id ir -- ) ( shoot if allowed ) canShoot? if drop drop ( remove ir id ) else shoot! ( really shoot ) shot true ! ( remember it ) then then ; ");
    list.add(": doNotShoot ( id ir -- ) ( pretend shoot ) drop drop ; ");
    list.add(": enemy? ( s--b ) ( decide if enemy ) team <> ;");
    list.add(": nonZeroRange? ( i --bi ) dup 0 <> ; ");
    list.add(": tryShooting! ( ih id ir st -- ) enemy? swap nonZeroRange? rot and if shoot!! else doNotShoot then drop ( remove ih ) ;");
    list.add(": shootEveryone ( try shot at all targets ) scan! 1 - dup 0 < if ( no one to shoot at ) else 0 do identify! tryShooting! loop then ;");
    list.add(": play ( -- ) false moved ! firstMove shot false ! ( prepare to shoot ) shootEveryone ; ");
    //list.add("moved? ;");
    */
    list.add("variable moved ; ( have i moved? )");
    list.add(": moved? moved ? ; ");
    list.add("moved false ! ");
    list.add(": firstMove ( align along left edge ) moved? if ( already moved ) else 5 turn! then ;");
    list.add(": edgeMove ( -- ) ( move along the edge ) 0 check! .\" OUT OF BOUNDS \" = if 1 turn! else ( ) then move ; ");
    list.add(": noMovesLeft? ( --b ) ( no moves left? ) movesLeft 0 <> ; ");
    list.add("variable shot ;  ( have I shot this play? )");
    list.add(": canShoot? ( --b ) ( shot available? ) shot ? ; ");
    list.add(": shoot!! ( id ir -- ) ( shoot if allowed ) canShoot? if drop drop ( remove ir id ) else shoot! ( really shoot ) shot true ! ( remember it ) then then ; ");
    list.add(": doNotShoot ( id ir -- ) ( pretend shoot ) drop drop ; ");
    list.add(": enemy? ( s--b ) ( decide if enemy ) team <> ;");
    list.add(": nonZeroRange? ( i --bi ) dup 0 <> ; ");
    list.add(": tryShooting! ( ih id ir st -- ) enemy? swap nonZeroRange? rot and if shoot!! else doNotShoot then drop ( remove ih ) ;");
    list.add(": shootEveryone ( try shot at all targets ) scan! 1 - dup 0 < if ( no one to shoot at ) else 0 do identify! tryShooting! loop then ;");
    list.add(": play ( -- ) firstMove shot false ! ( prepare to shoot ) begin edgeMove shootEveryone noMovesLeft? until ; ");

    testRobot2.put("code", list2);
    JSONObject testScript2 = new JSONObject();
    testScript2.put("script", testRobot2);
    chosenRobotCodes.push(testScript2);
    
    JSONObject centralizer1 = new JSONObject();
    centralizer1.put("team", "A7");
    centralizer1.put("class", "Scout");
    centralizer1.put("name", "centralizersscout");
    centralizer1.put("matches", 5);
    centralizer1.put("wins", 2);
    centralizer1.put("losses", 3);
    centralizer1.put("executions", 5);
    centralizer1.put("lived", 1);
    centralizer1.put("died", 4);
    centralizer1.put("absorbed", 4);
    centralizer1.put("killed", 0);
    centralizer1.put("moved", 17);
    JSONArray listc = new JSONArray();
    listc.add("variable moved ; ( have i moved? )");
    listc.add(": moved? moved ? ; ");
    listc.add("moved false ! ; ");
    listc.add(": firstMove ( move to center first ) moved ? if ( already moved ) else move move move true moved ! then ;");
    listc.add("variable shot ;  ( have I shot this play? )");
    listc.add(": canShoot? ( --b ) ( shot available? ) shot ? ; ");
    listc.add(": shoot!! ( id ir -- ) ( shoot if allowed ) canShoot? if drop drop ( remove ir id ) else shoot! ( really shoot ) shot true ! ( remember it ) then then ; ");
    listc.add(": doNotShoot ( id ir -- ) ( pretend shoot ) drop drop ; ");
    listc.add(": enemy? ( s--b ) ( decide if enemy ) team <> ;");
    listc.add(": nonZeroRange? ( i --bi ) dup 0 <> ; ");
    listc.add(": tryShooting! ( ih id ir st -- ) enemy? swap nonZeroRange? rot and if shoot!! else doNotShoot then drop ( remove ih ) ;");
    listc.add(": shootEveryone ( try shot at all targets ) scan! 1 - dup 0 < if ( no one to shoot at ) else 0 do identify! tryShooting! loop then ;");
    listc.add(": play ( -- ) false moved ! firstMove shot false ! ( prepare to shoot ) shootEveryone ; ");
    
    centralizer1.put("code", listc);
    JSONObject testScriptc = new JSONObject();
    testScriptc.put("script", centralizer1);
    chosenRobotCodes.push(testScriptc);
    
     JSONObject centralizer2 = new JSONObject();
    centralizer2.put("team", "A7");
    centralizer2.put("class", "Sniper");
    centralizer2.put("name", "centralizerssniper");
    centralizer2.put("matches", 5);
    centralizer2.put("wins", 2);
    centralizer2.put("losses", 3);
    centralizer2.put("executions", 5);
    centralizer2.put("lived", 1);
    centralizer2.put("died", 4);
    centralizer2.put("absorbed", 4);
    centralizer2.put("killed", 0);
    centralizer2.put("moved", 17);
    JSONArray listc2 = new JSONArray();
    listc2.add("variable moved ; ( have i moved? )");
    listc2.add(": moved? moved ? ; ");
    listc2.add("moved false ! ; ");
    listc2.add(": firstMove ( move to center first ) moved ? if ( already moved ) else move move move true moved ! then ;");
    listc2.add("variable shot ;  ( have I shot this play? )");
    listc2.add(": canShoot? ( --b ) ( shot available? ) shot ? ; ");
    listc2.add(": shoot!! ( id ir -- ) ( shoot if allowed ) canShoot? if drop drop ( remove ir id ) else shoot! ( really shoot ) shot true ! ( remember it ) then then ; ");
    listc2.add(": doNotShoot ( id ir -- ) ( pretend shoot ) drop drop ; ");
    listc2.add(": enemy? ( s--b ) ( decide if enemy ) team <> ;");
    listc2.add(": nonZeroRange? ( i --bi ) dup 0 <> ; ");
    listc2.add(": tryShooting! ( ih id ir st -- ) enemy? swap nonZeroRange? rot and if shoot!! else doNotShoot then drop ( remove ih ) ;");
    listc2.add(": shootEveryone ( try shot at all targets ) scan! 1 - dup 0 < if ( no one to shoot at ) else 0 do identify! tryShooting! loop then ;");
    listc2.add(": play ( -- ) false moved ! firstMove shot false ! ( prepare to shoot ) shootEveryone ; ");
    
    centralizer2.put("code", listc2);
    JSONObject testScriptc2 = new JSONObject();
    testScriptc2.put("script", centralizer2);
    chosenRobotCodes.push(testScriptc2);
    
     JSONObject centralizer3 = new JSONObject();
    centralizer3.put("team", "A7");
    centralizer3.put("class", "Tank");
    centralizer3.put("name", "centralizerstank");
    centralizer3.put("matches", 5);
    centralizer3.put("wins", 2);
    centralizer3.put("losses", 3);
    centralizer3.put("executions", 5);
    centralizer3.put("lived", 1);
    centralizer3.put("died", 4);
    centralizer3.put("absorbed", 4);
    centralizer3.put("killed", 0);
    centralizer3.put("moved", 17);
    JSONArray listc3 = new JSONArray();
    listc3.add("variable moved ; ( have i moved? )");
    listc3.add(": moved? moved ? ; ");
    listc3.add("moved false ! ; ");
    listc3.add(": firstMove ( move to center first ) moved ? if ( already moved ) else move move move true moved ! then ;");
    listc3.add("variable shot ;  ( have I shot this play? )");
    listc3.add(": canShoot? ( --b ) ( shot available? ) shot ? ; ");
    listc3.add(": shoot!! ( id ir -- ) ( shoot if allowed ) canShoot? if drop drop ( remove ir id ) else shoot! ( really shoot ) shot true ! ( remember it ) then then ; ");
    listc3.add(": doNotShoot ( id ir -- ) ( pretend shoot ) drop drop ; ");
    listc3.add(": enemy? ( s--b ) ( decide if enemy ) team <> ;");
    listc3.add(": nonZeroRange? ( i --bi ) dup 0 <> ; ");
    listc3.add(": tryShooting! ( ih id ir st -- ) enemy? swap nonZeroRange? rot and if shoot!! else doNotShoot then drop ( remove ih ) ;");
    listc3.add(": shootEveryone ( try shot at all targets ) scan! 1 - dup 0 < if ( no one to shoot at ) else 0 do identify! tryShooting! loop then ;");
    listc3.add(": play ( -- ) false moved ! firstMove shot false ! ( prepare to shoot ) shootEveryone ; ");
    
    centralizer3.put("code", listc3);
    JSONObject testScriptc3 = new JSONObject();
    testScriptc3.put("script", centralizer3);
    chosenRobotCodes.push(testScriptc3);
    
    
    
    }
    
    
    firstPlay = false;
//    chosenRobotCodes.push(testScript);
//    chosenRobotCodes.push(testScript);
//    chosenRobotCodes.push(testScript);
//    chosenRobotCodes.push(testScript);
//    chosenRobotCodes.push(testScript);
//
//    chosenRobotCodes.push(testScript);
//    chosenRobotCodes.push(testScript);
//    chosenRobotCodes.push(testScript);
//    chosenRobotCodes.push(testScript);
//    chosenRobotCodes.push(testScript);
//    chosenRobotCodes.push(testScript);
//    
//    chosenRobotCodes.push(testScript);
//    chosenRobotCodes.push(testScript);
//    chosenRobotCodes.push(testScript);
//    chosenRobotCodes.push(testScript);
//    chosenRobotCodes.push(testScript);
//    chosenRobotCodes.push(testScript);
    view.showMainMenu(this);
        //These robot codes are for testing, must remove at some point

  }

  @Override
  public void keyPressed(KeyEvent arg0) {
     if (arg0.getKeyChar() == 'd') {
      try {
        currentRobot.move('d');
      } catch (Exception ex) {
        Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    if (arg0.getKeyChar() == 'x') {
      try {
        currentRobot.move('x');
      } catch (Exception ex) {
        Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    if (arg0.getKeyChar() == 'z') {
      try {
        currentRobot.move('z');
      } catch (Exception ex) {
        Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    if (arg0.getKeyChar() == 'a') {
      try {
        currentRobot.move('a');
      } catch (Exception ex) {
        Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    if (arg0.getKeyChar() == 'w') {
      try {
        currentRobot.move('w');
      } catch (Exception ex) {
        Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    if (arg0.getKeyChar() == 'e') {
      try {
        currentRobot.move('e');
      } catch (Exception ex) {
        Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

  }
/**
 * this function takes actions sent from view to 
 * and performs the proper task
 * @param arg0 
 */
  @Override
  public void actionPerformed(ActionEvent arg0) {

    String actionCommand = arg0.getActionCommand();
    switch (actionCommand) {
      case "quit": {
        System.exit(0);
        break;
      }
      case "playagain": {
        view.showGameProperties(this);
      }
      case "stats": {
        view.showStats(this);
      }
      case "endTurn": {

        Iterator<Robot> iterate = gameController.gameBoard.aliveList.iterator();
                        //While there are still robots, we will iterate through
        //the list until we come accross the current robot
        while (iterate.hasNext() && gameInfo.getCurrentRobot() != iterate.next()) {
        }
        //If the last robot to play was at the end of the list
        if (!iterate.hasNext()) {
          iterate = gameController.gameBoard.aliveList.iterator();
          gameInfo.getCurrentRobot().restartParameters();
          gameInfo.setCurrentRobot(iterate.next());
          //If the robot is not a human, it should execute code instead
          if (!gameInfo.getCurrentRobot().isHuman()) {
            Interpreter interpret = new Interpreter();
            
            try {
              if(gameInfo.getCurrentRobot().getType().compareTo("SCOUT") == 0){
              ScoutAI temp = (ScoutAI) gameInfo.getCurrentRobot();
              interpret.executeCode(temp.getCode(), gameInfo.getCurrentRobot());
              System.out.println(interpret.getRobotClass(temp.getCode()));
            }
            else if(gameInfo.getCurrentRobot().getType().compareTo("SNIPER") == 0){
              SniperAI temp = (SniperAI) gameInfo.getCurrentRobot();
              interpret.executeCode(temp.getCode(), gameInfo.getCurrentRobot());
              System.out.println(interpret.getRobotClass(temp.getCode()));
            }
            else{
              TankAI temp = (TankAI) gameInfo.getCurrentRobot();
              interpret.executeCode(temp.getCode(), gameInfo.getCurrentRobot());
              System.out.println(interpret.getRobotClass(temp.getCode()));
            }
              
            } catch (NoSuchMethodException ex) {
              Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
              System.out.println("Error, code not executed");
            }
          }
        } //If the round is not over
        else {
          gameInfo.getCurrentRobot().restartParameters();
          gameInfo.setCurrentRobot(iterate.next());
          if (!gameInfo.getCurrentRobot().isHuman()) {
            Interpreter interpret = new Interpreter();
            //     ScoutAI temp = (ScoutAI) gameInfo.getCurrentRobot();
            try {
              interpret.executeCode(gameInfo.getCurrentRobot().getCode(), gameInfo.getCurrentRobot());
            } catch (NoSuchMethodException ex) {
              Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
          }

        }
        
        // check for dead robots and remove them from the board
        int size = 0;
        if(gameInfo.getBoardSize() == 5){
           // if 5 spaces on each size 2d array of hexes is 9 by 9
          size = 9;     
        }
        else{
           // if 5 spaces on each size 2d array of hexes is 13 by 13
          size = 13;
        }
        for(int i = 0; i < size; i++){
      	for(int j = 0; j < size; j++){
      		if(gameBoard.spaces[i][j] != null && gameBoard.spaces[i][j].hexExists == true){
                        Iterator<Robot> robots2 = gameBoard.spaces[i][j].robotList.iterator();
                        while(robots2.hasNext()){
      			if(!gameBoard.spaces[i][j].robotList.isEmpty() && robots2.next().getHealthLeft() == 0){
                            Iterator<Robot> robots = gameBoard.spaces[i][j].robotList.iterator();
                            while (robots.hasNext()){
                            gameBoard.spaces[i][j].robotList.remove(robots.next());
                            //view.repaint();//Added
                            }
                            //view.repaint();//Added
                        }
                        }
                }
              }
        }
        view.repaint();

        break;
      }
      case "help": {
        view.showHelp(this, WIDTH, HEIGHT);
        break;
      }
      case "begin": {
        view.showGameProperties(this);
        break;
      }
      case "backGP": {
        view.showMainMenu(this);
        break;
      }
      case "continueGP": {
        view.showTeamSelection(this);
        break;
      }
      case "continueTS": {
        Game game = new Game(WIDTH, HEIGHT);
        gameControl = game;
        gameInfo = game;
        gameInfo.addObserver(this);
        gameInfo.setNumPlayers(numPlayers);
        gameInfo.setNumHumans(numHumans);
        gameInfo.setBoardSize(boardSize);
        chosenRobotCodes = new Stack<>();
        Iterator<JSONObject> iterateSelect = selectedRobots.iterator();
        while(iterateSelect.hasNext()){
          chosenRobotCodes.push(iterateSelect.next());
        }
        
        
        
        try {
          view.showGame(this, this, gameInfo);
        } catch (NoSuchMethodException ex) {
          Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        gameControl.start();
        break;
      }
      case "backTS": {
        view.showGameProperties(this);
        break;
      }
      case "five": {
        boardSize = 5;
        break;
      }
      case "seven": {
        boardSize = 7;
        break;
      }
      case "zeroHumans": {
        numHumans = 0;
        break;
      }
      case "oneHuman": {
        numHumans = 1;
        break;
      }
      case "twoHumans": {
        numHumans = 2;
        break;
      }
      case "threeHumans": {
        numHumans = 3;
        break;
      }
      case "fourHumans": {
        numHumans = 4;
        break;
      }
      case "fiveHumans": {
        numHumans = 5;
        break;
      }
      case "sixHumans": {
        numHumans = 6;
        break;
      }
      case "twoTeams": {
        numPlayers = 2;
        boardSize = 5;
        break;
      }
      case "threeTeams": {
        numPlayers = 3;
        break;
      }
      case "sixTeams": {
        numPlayers = 6;
        boardSize = 7;
        break;
      }
      default: {
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
   * This function will generate all of the robots and their gangs with the
   * given stack of robot codes given from the team selection page. It will then
   * return a Linked list of Robots all assigned and in the order of their place
   * in a round.
   *
   * @return LinkedList of robots in play
   */
  public LinkedList<Robot> createTeams(GameBoard game) {
    Stack humanGangs = new <Gang>Stack();
    Stack CMPTGangs = new <GangAI>Stack();
    Robot array[] = new Robot[numPlayers * 3];
    Interpreter assign = new Interpreter();
    System.out.println("Humans: " + numHumans);
            //For however many humans there are, generate the robots off of the 
    //Stack of robot code and put them into a new Gang
    for (int i = 0; i < numHumans; i++) {
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
    for (int j = 0; j < (numPlayers - numHumans); j++) {
      ScoutAI temp11 = new ScoutAI(assign.getRobotName(chosenRobotCodes.peek()), chosenRobotCodes.peek());
      temp11.setAbsorbed(assign.getRobotAbsorbed(chosenRobotCodes.peek()));
      temp11.setMoves(assign.getRobotMoved(chosenRobotCodes.peek()));
      temp11.setKilled(assign.getRobotKilled(chosenRobotCodes.peek()));
      temp11.setDied(assign.getRobotDied(chosenRobotCodes.peek()));
      temp11.setExecutions(assign.getRobotKilled(chosenRobotCodes.peek()) + 1);
      temp11.setLived(assign.getRobotLived(chosenRobotCodes.peek()));
      temp11.setMatches(assign.getRobotMatches(chosenRobotCodes.peek()) + 1);
      temp11.setLosses(assign.getRobotLosses(chosenRobotCodes.peek()));
      temp11.setWins(assign.getRobotWins(chosenRobotCodes.peek()));
      temp11.setGameBoard(game);
      chosenRobotCodes.pop();
      SniperAI temp21 = new SniperAI(assign.getRobotName(chosenRobotCodes.peek()), chosenRobotCodes.peek());
      temp21.setAbsorbed(assign.getRobotAbsorbed(chosenRobotCodes.peek()));
      temp21.setMoves(assign.getRobotMoved(chosenRobotCodes.peek()));
      temp21.setKilled(assign.getRobotKilled(chosenRobotCodes.peek()));
      temp21.setDied(assign.getRobotDied(chosenRobotCodes.peek()));
      temp21.setExecutions(assign.getRobotKilled(chosenRobotCodes.peek()) + 1);
      temp21.setLived(assign.getRobotLived(chosenRobotCodes.peek()));
      temp21.setMatches(assign.getRobotMatches(chosenRobotCodes.peek()) + 1);
      temp21.setLosses(assign.getRobotLosses(chosenRobotCodes.peek()));
      temp21.setWins(assign.getRobotWins(chosenRobotCodes.peek()));
      temp21.setGameBoard(game);
      chosenRobotCodes.pop();
      TankAI temp31 = new TankAI(assign.getRobotName(chosenRobotCodes.peek()), chosenRobotCodes.peek());
      temp31.setAbsorbed(assign.getRobotAbsorbed(chosenRobotCodes.peek()));
      temp31.setMoves(assign.getRobotMoved(chosenRobotCodes.peek()));
      temp31.setKilled(assign.getRobotKilled(chosenRobotCodes.peek()));
      temp31.setDied(assign.getRobotDied(chosenRobotCodes.peek()));
      temp31.setExecutions(assign.getRobotKilled(chosenRobotCodes.peek()) + 1);
      temp31.setLived(assign.getRobotLived(chosenRobotCodes.peek()));
      temp31.setMatches(assign.getRobotMatches(chosenRobotCodes.peek()) + 1);
      temp31.setLosses(assign.getRobotLosses(chosenRobotCodes.peek()));
      temp31.setWins(assign.getRobotWins(chosenRobotCodes.peek()));
      temp31.setGameBoard(game);
      chosenRobotCodes.pop();
      GangAI CMPT = new GangAI(temp11, temp21, temp31);
      CMPTGangs.push(CMPT);
    }
            //Depending on how many players there are, we will need to have the
    //correct colors of teams avaliable to assign

    Stack<String> teams = new Stack<String>();
    if (numPlayers == 2) {
      teams.push("GREEN");
      teams.push("RED");
    } else if (numPlayers == 3) {
      teams.push("BLUE");
      teams.push("YELLOW");
      teams.push("RED");
    } else {
      teams.push("PURPLE");
      teams.push("BLUE");
      teams.push("GREEN");
      teams.push("YELLOW");
      teams.push("ORANGE");
      teams.push("RED");
    }
    //Each gang will be assigned a color team and then added to a list
    LinkedList<Robot> listOfRobots = new LinkedList();
    for (int k = 0; k < numPlayers; k++) {
      while (!humanGangs.empty()) {
        System.out.println("Size: " + humanGangs.size());
        Gang toColor = (Gang) humanGangs.pop();
        toColor.setTeam(teams.pop());
        listOfRobots.addLast(toColor.getScoutRobot());
        listOfRobots.addLast(toColor.getSniperRobot());
        listOfRobots.addLast(toColor.getTankRobot());
      }
      while (!CMPTGangs.empty()) {
        GangAI toColor2 = (GangAI) CMPTGangs.pop();
        toColor2.setTeam(teams.pop());
        if (toColor2.getTeam().compareTo("RED") == 0) {
          toColor2.getScout().setRelativeDirection(0);
          toColor2.getSniper().setRelativeDirection(0);
          toColor2.getTank().setRelativeDirection(0);
        }
        if (toColor2.getTeam().compareTo("ORANGE") == 0) {
          toColor2.getScout().setRelativeDirection(1);
          toColor2.getSniper().setRelativeDirection(1);
          toColor2.getTank().setRelativeDirection(1);
        }
        if (toColor2.getTeam().compareTo("YELLOW") == 0) {
          toColor2.getScout().setRelativeDirection(2);
          toColor2.getSniper().setRelativeDirection(2);
          toColor2.getTank().setRelativeDirection(2);
        }
        if (toColor2.getTeam().compareTo("GREEN") == 0) {
          toColor2.getScout().setRelativeDirection(3);
          toColor2.getSniper().setRelativeDirection(3);
          toColor2.getTank().setRelativeDirection(3);
        }
        if (toColor2.getTeam().compareTo("BLUE") == 0) {
          toColor2.getScout().setRelativeDirection(4);
          toColor2.getSniper().setRelativeDirection(4);
          toColor2.getTank().setRelativeDirection(4);
        }
        if (toColor2.getTeam().compareTo("PURPLE") == 0) {
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
    while (!listOfRobots.isEmpty()) {
      array[count] = listOfRobots.pop();
      count = count + numPlayers;
      if (count >= numPlayers * 3) {
        count = count % ((numPlayers * 3) - 1);
      }
    }
    listOfRobots.clear();
    for (int l = 0; l < (numPlayers * 3); l++) {
      listOfRobots.add(array[l]);

    }
    return listOfRobots;
  }

  /**
   * This function takes in the gameBoard and then places all the teams into the
   * correct starting spaces on the board based on the number of players and the
   * size of the board
   *
   * @param gameBoard
   */
  public void setTeamPositions(GameBoard gameBoard) {
    Iterator<Robot> iterate = gameBoard.robotList.iterator();
    if (gameBoard.getBoardSize() == 5) {
      if (numPlayers == 2) {
        //RED then GREEN repeat
        gameBoard.spaces[0][4].robotList.add(iterate.next());
        gameBoard.spaces[8][4].robotList.add(iterate.next());
        gameBoard.spaces[0][4].robotList.add(iterate.next());
        gameBoard.spaces[8][4].robotList.add(iterate.next());
        gameBoard.spaces[0][4].robotList.add(iterate.next());
        gameBoard.spaces[8][4].robotList.add(iterate.next());
        System.out.println(gameBoard.spaces[0][4].robotList.size());
        Iterator<Robot> iterate2 = gameBoard.spaces[0][4].robotList.iterator();
        while (iterate2.hasNext()) {
          iterate2.next().setVerticalAndHorizontal(0, 4);
        }
        iterate2 = gameBoard.spaces[8][4].robotList.iterator();
        while (iterate2.hasNext()) {
          iterate2.next().setVerticalAndHorizontal(8, 4);
        }
      } else {//number of players must be 3
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
        while (iterate2.hasNext()) {
          iterate2.next().setVerticalAndHorizontal(0, 4);
        }
        iterate2 = gameBoard.spaces[6][0].robotList.iterator();
        while (iterate2.hasNext()) {
          iterate2.next().setVerticalAndHorizontal(6, 0);
        }
        iterate2 = gameBoard.spaces[6][8].robotList.iterator();
        while (iterate2.hasNext()) {
          iterate2.next().setVerticalAndHorizontal(6, 8);
        }
      }
    } else {
      if (numPlayers == 3) {
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
        while (iterate2.hasNext()) {
          iterate2.next().setVerticalAndHorizontal(0, 6);
        }
        iterate2 = gameBoard.spaces[9][0].robotList.iterator();
        while (iterate2.hasNext()) {
          iterate2.next().setVerticalAndHorizontal(9, 0);
        }
        iterate2 = gameBoard.spaces[9][12].robotList.iterator();
        while (iterate2.hasNext()) {
          iterate2.next().setVerticalAndHorizontal(9, 12);
        }
      } else {
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
        while (iterate2.hasNext()) {
          iterate2.next().setVerticalAndHorizontal(0, 6);
        }
        iterate2 = gameBoard.spaces[3][0].robotList.iterator();
        while (iterate2.hasNext()) {
          iterate2.next().setVerticalAndHorizontal(3, 0);
        }
        iterate2 = gameBoard.spaces[9][0].robotList.iterator();
        while (iterate2.hasNext()) {
          iterate2.next().setVerticalAndHorizontal(9, 0);
        }
        iterate2 = gameBoard.spaces[12][6].robotList.iterator();
        while (iterate2.hasNext()) {
          iterate2.next().setVerticalAndHorizontal(12, 6);
        }
        iterate2 = gameBoard.spaces[9][12].robotList.iterator();
        while (iterate2.hasNext()) {
          iterate2.next().setVerticalAndHorizontal(9, 12);
        }
        iterate2 = gameBoard.spaces[3][12].robotList.iterator();
        while (iterate2.hasNext()) {
          iterate2.next().setVerticalAndHorizontal(3, 12);
        }
      }

    }
  }

  /**
   * This function will determine if the game is over; that either there are no
   * robots left in play or there is only robots from one team left in play; a
   * boolean of true or false will be returned
   *
   * @param AliveRobotList
   * @return True if the game is over, false otherwise
   */
  public boolean isGameOver(LinkedList<Robot> AliveRobotList) {
    if (AliveRobotList.isEmpty()) {//Are there no robots in play?
      return true;
    }
    Iterator<Robot> iterate = AliveRobotList.iterator();
    String firstColor = iterate.next().getGang();
    while (iterate.hasNext()) {
                //If there are any robots that have a different color than the
      //First robot in the list, then the game is not over
      if (firstColor.compareTo(iterate.next().getGang()) != 0) {
        return false;
      }
    }
    return true;
  }

  public void play(GameBoard gameBoard) throws NoSuchMethodException {
    while (!isGameOver(gameBoard.aliveList)) {
      Iterator<Robot> turnIterate = gameBoard.aliveList.iterator();
      currentRobot = turnIterate.next();
      if (!currentRobot.isHuman()) {
        ScoutAI temp = (ScoutAI) currentRobot;
        JSONObject code = temp.getCode();
        Interpreter interpret = new Interpreter();
        interpret.executeCode(code, temp);
      } else {

      }

    }

  }

  public static void main(String[] args) {
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

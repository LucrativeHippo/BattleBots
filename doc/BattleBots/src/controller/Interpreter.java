/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import forth.WordTranslator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import model.Robot;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import model.ScoutAI;
import model.SniperAI;
import model.TankAI;
/**
 *
 * @author mkp003
 */
public class Interpreter {
    
    
    /**
     * This function will return a string of a robot's name from a JSONObject
     * @param instructionCode which is the JSONObject
     * @return String name of the robot this particular JSON object contains
     */
    public String getRobotName(JSONObject instructionCode){
       JSONObject temp = (JSONObject)instructionCode.get("script");
       return (String)temp.get("name");
    }
    
    /**
     * This function will return a string of a robot's team from a JSONObject
     * @param instructionCode which is the JSONObject
     * @return String team of the robot this particular JSON object contains
     */
    public String getRobotTeam(JSONObject instructionCode){
        JSONObject temp = (JSONObject)instructionCode.get("script");
        return (String)temp.get("team");
    }
    
    /**
     * This function will return a string of a robot's class from a JSONObject
     * @param instructionCode which is the JSONObject
     * @return String class of the robot this particular JSON object contains
     */
    public String getRobotClass(JSONObject instructionCode){
       JSONObject temp = (JSONObject)instructionCode.get("script");
       return (String)temp.get("class");
    }
    
    /**
     * This function will return an integer of a robotScout's total played matches
     * from a JSONObject
     * @param instructionCode which is the JSONObject
     * @return integer matches of the robot this particular JSON object contains
     */
    public int getRobotMatches(JSONObject instructionCode){
        JSONObject temp = (JSONObject)instructionCode.get("script");
        return (int)temp.get("matches");
    }
    
    /**
     * This function will return an integer of a robot's total wins
     * from a JSONObject
     * @param instructionCode which is the JSONObject
     * @return integer wins of the robot this particular JSON object contains
     */
    public int getRobotWins(JSONObject instructionCode){
       JSONObject temp = (JSONObject)instructionCode.get("script");
       return (int)temp.get("wins");
    }
    
    /**
     * This function will return an integer of a robot's total losses
     * from a JSONObject
     * @param instructionCode which is the JSONObject
     * @return integer losses of the robot this particular JSON object contains
     */
    public int getRobotLosses(JSONObject instructionCode){
        JSONObject temp = (JSONObject)instructionCode.get("script");
        return (int)temp.get("losses");
    }
    
    /**
     * This function will return an integer of a robot's total executions
     * from a JSONObject (Executions = lived + died, the amount of times a robot
     * has been played)
     * @param instructionCode which is the JSONObject
     * @return integer executions of the robot this particular JSON object 
     * contains
     */
    public int getRobotExecutions(JSONObject instructionCode){
       JSONObject temp = (JSONObject)instructionCode.get("script");
       return (int)temp.get("executions");
    }
    
    /**
     * This function will return an integer of how many times the robot has 
     * lived through a match from a JSONObject
     * @param instructionCode which is the JSONObject
     * @return integer lived of the robot this particular JSON object contains
     */
    public int getRobotLived(JSONObject instructionCode){
        JSONObject temp = (JSONObject)instructionCode.get("script");
        return (int)temp.get("lived");
    }
    
    /**
     * This function will return an integer of how many times the robot has 
     * died from a JSONObject
     * @param instructionCode which is the JSONObject
     * @return integer died of the robot this particular JSON object contains
     */
    public int getRobotDied(JSONObject instructionCode){
        JSONObject temp = (JSONObject)instructionCode.get("script");
        return (int)temp.get("died");
    }
    
    /**
     * This function will return an integer of how much damage the robot has 
     * received over the course of its plays from a JSONObject
     * @param instructionCode which is the JSONObject
     * @return integer absorbed of the robot this particular JSON object contains
     */
    public int getRobotAbsorbed(JSONObject instructionCode){
        JSONObject temp = (JSONObject)instructionCode.get("script");
        return (int)temp.get("absorbed");
    }
    
    /**
     * This function will return an integer of how many robots this robot has 
     * killed from a JSONObject
     * @param instructionCode which is the JSONObject
     * @return integer killed of the robot this particular JSON object contains
     */
    public int getRobotKilled(JSONObject instructionCode){
        JSONObject temp = (JSONObject)instructionCode.get("script");
        return (int)temp.get("killed");
    }
    
    /**
     * This function will return an integer of how many times the robot has 
     * moved from a JSONObject
     * @param instructionCode which is the JSONObject
     * @return integer move of the robot this particular JSON object contains
     */
    public int getRobotMoved(JSONObject instructionCode){
        JSONObject temp = (JSONObject)instructionCode.get("script");
        return (int)temp.get("moved");
    }
    
    
    
    //Checks if a string is an integer
    public boolean isInteger(String s){
        int size = s.length();

    for (int i = 0; i < size; i++) {
        if (!Character.isDigit(s.charAt(i))) {
            return false;
        }
    }

    return size > 0;
    
    }
    
    //Checks if a string is a boolean
    public boolean isBoolean(String s){
        return "true".equals(s)||"false".equals(s);
    }
    
    
    
    /**
     * This function is responsible for executing the forth code associated with
     * with a given robot during it's turn.
     * @param instructionCode The JSON object associated with the specific robot
     * @param robot Robot associated with the code
     */
    public void executeCode(JSONObject instructionCode, Robot robot) throws NoSuchMethodException{
        System.out.println("The interpreter is starting");
        Stack initialfourthWords = new Stack();
        JSONObject temp = (JSONObject)instructionCode.get("script");
        List commands = (List)temp.get("code");
        int lines = commands.size();
        String line;
        for(int i = 0; i < lines; i++){
           line = (String) commands.remove(0);
           String[] commandArray = line.split("\\s+");
           int x = commandArray.length;
           for(int j = 0; j < x; j++){
               initialfourthWords.add(commandArray[j]);
           }
        }
        Stack medium = new Stack();
        while(!initialfourthWords.empty()){
            medium.push(initialfourthWords.pop());
        }
        Stack forthWords = new Stack();
        while(!medium.empty()){
            forthWords.add(medium.remove(0));
        }
        System.out.println("Words interpreted from JSON");
        WordTranslator translate = new WordTranslator(robot, forthWords);
        while(!forthWords.empty()){ 
            System.out.println(forthWords.peek());
            if(this.isInteger((String)forthWords.peek())){
                robot.forthValues.push(Integer.parseInt((String)forthWords.pop()));
            }else if(this.isBoolean((String)forthWords.peek())){
                robot.forthValues.push(Boolean.parseBoolean((String)forthWords.pop()));
            }else if(robot.variables.containsKey(forthWords.peek())){
                    translate.getHashMap().get("variable").execute();
                    }else
            {
                
                translate.getHashMap().get(forthWords.pop()).execute();
               
            }
            
            if(!robot.forthValues.empty()){
                
                System.out.println("The top of the stack is " + robot.forthValues.peek());
               
            }
        }
    }
    
    
    
    
    
    
    //Here we will test the functions associated with the Interpreter
    public static void main(String [] args) throws NoSuchMethodException {
       
        /*
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
        list.add("variable lastShot ; ");
        list.add("0 lastShot ! ");
        list.add(": play ( -- ) ");
        list.add(" 0 begin dup lastShot ? + 1 6 /mod drop ");
        list.add(" empty? if .\"no one there\" ");
        list.add(" else dup lastShot ! ");
        list.add(" dup 1 shoot! leave ");
        list.add(" then 1 + dup 5 > ");
        list.add(" until drop ; ");
        testRobot.put("code", list);
        JSONObject testScript = new JSONObject();
        testScript.put("script", testRobot);
        //testScript.putAll(testRobot);
        
        //System.out.println(testScript.toString());
        
        Interpreter interpret = new Interpreter();
        String robotName = interpret.getRobotName(testScript);
        if(robotName.compareTo("runner") != 0){
            System.out.println("Error: The robot's name is: " + robotName + "but should be runner");
        }
        String robotTeam = interpret.getRobotTeam(testScript);
        if(robotTeam.compareTo("A5") != 0){
            System.out.println("Error: The robot's Team is: " + robotTeam + "but should be A5");
        }
        String robotClass = interpret.getRobotClass(testScript);
        if(robotClass.compareTo("Scout") != 0){
            System.out.println("Error: The robot's Class is: " + robotClass + "but should be Scout");
        }
        int robotMatches = interpret.getRobotMatches(testScript);
        if(robotMatches != 5){
            System.out.println("Error: The robot's Matches is: " + robotMatches + "but should be 5");
        }
        int robotWins = interpret.getRobotWins(testScript);
        if(robotWins != 2){
            System.out.println("Error: The robot's Wins is: " + robotWins + "but should be 2");
        }
        int robotLosses = interpret.getRobotLosses(testScript);
        if(robotLosses != 3){
            System.out.println("Error: The robot's Losses is: " + robotLosses + "but should be 3");
        }
        int robotLived = interpret.getRobotLived(testScript);
        if(robotLived != 1){
            System.out.println("Error: The robot's Lived is: " + robotLived + "but should be 1");
        }
        int robotExecutions = interpret.getRobotExecutions(testScript);
        if(robotExecutions != 5){
            System.out.println("Error: The robot's Executions is: " + robotExecutions + "but should be 5");
        }
        int robotAbsorbed = interpret.getRobotAbsorbed(testScript);
        if(robotAbsorbed != 4){
            System.out.println("Error: The robot's absorbed is: " + robotAbsorbed + "but should be 4");
        }
        int robotKilled = interpret.getRobotKilled(testScript);
        if(robotKilled != 0){
            System.out.println("Error: The robot's kills is: " + robotKilled + "but should be 0");
        }
        int robotMoved = interpret.getRobotMoved(testScript);
        if(robotMoved != 17){
            System.out.println("Error: The robot's moves is: " + robotMoved + "but should be 17");
        }
        
        //test functions
        
        //interpret.executeCode(testScript, null);
        
        
        
        //Testing a sample code
        JSONArray list2 = new JSONArray();
        list.add("variable lastShot ; ");
        list.add("0 lastShot ! ");
        list.add(": play ( -- ) ");
        list.add(" 0 begin dup lastShot ? + 1 6 /mod drop ");
        list.add(" empty? if .\"no one there\" ");
        list.add(" else dup lastShot ! ");
        list.add(" dup 1 shoot! leave ");
        list.add(" then 1 + dup 5 > ");
        list.add(" until drop ; ");
    */
        
        Stack forthWords = new Stack();
        
        forthWords.push("-");
        ScoutAI scout1 = new ScoutAI("scout", null);
        JSONArray test1 = new JSONArray();
        /*
        test1.add("1 1 +");
        test1.add("2 -");
        test1.add("1 1 *");
        test1.add("2 /mod");
        test1.add("<");
        test1.add("1 0 >");
        test1.add("0 0 =");
        test1.add("0 1 =>");
        test1.add("0 1 <=");
        test1.add("0 0 <>");
        test1.add("true false and");
        test1.add("true false or");
        test1.add("false invert");
        test1.add("1 2 3 drop drop drop");
        test1.add("true dup 1 dup");
        test1.add("true false swap swap swap");
        test1.add("health");
        test1.add("moves");
        test1.add("movesLeft");
        test1.add("healthLeft");
        test1.add("range");
        test1.add("team");
        test1.add("type");
                
        test1.add("true if 1 1 + else 1 1 - then 1");
        test1.add("false if 1 1 + else 1 1 - then 1");
                */
        //test1.add("true if false if true if 5 5 + else 1 1 - then 1 + else 7 7 * then 2 - else 1 then 5 -");
        //Not sure how to test        
        //test1.add(".\"hi\"");
        //test1.add("5 random 50 random");
        //test1.add(": hi 1 1 + ;");
        //test1.add("hi hi hi ? hi");
        //test1.add("10 0 do 1 1 + loop ;");
        
        //test1.add("variable a ; ");
        //test1.add("19 12 * a ! ");
        //test1.add("a ? ");
        test1.add("variable moved ; ( have i moved? )");
        test1.add(": moved? moved ? ; ");
        test1.add("moved? ");
        
        Interpreter interpret = new Interpreter();
        JSONObject testRobot = new JSONObject();
        testRobot.put("code", test1);
        JSONObject testScript = new JSONObject();
        testScript.put("script", testRobot);
        
        interpret.executeCode(testScript, scout1);
        //System.out.println(scout1.forthValues.pop());
       
                
                }
              
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.util.List;
import java.util.Stack;
import model.Robot;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
    
    public void executeCode(JSONObject instructionCode, Robot robot){
        Stack initalfourthWords = new Stack();
        JSONObject temp = (JSONObject)instructionCode.get("script");
        List commands = (List)temp.get("code");
        int lines = commands.size();
        String line;
        for(int i = 0; i < lines; i++){
           line = (String) commands.remove(0);
           String[] commandArray = line.split("\\s+");
           int x = commandArray.length;
           for(int j = 0; j < x; j++){
               initalfourthWords.add(commandArray[j]);
           }
        }
        Stack forthWords = new Stack();
        while(!initalfourthWords.empty()){
            forthWords.add(initalfourthWords.remove(0));
        }
    }
    
    
    
    //Here we will test the functions associated with the Interpreter
    public static void main(String [] args) {
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
        interpret.executeCode(testScript, null);
    }
    
}

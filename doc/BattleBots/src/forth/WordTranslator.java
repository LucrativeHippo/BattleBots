/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forth;

import controller.Interpreter;
import java.util.Hashtable;
import java.util.Stack;
import model.Robot;
import model.ScoutAI;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.lang.reflect.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import forth.Execute;


/**
 *
 * @author mkp003
 */
public class WordTranslator implements Execute{
    HashMap <String, Execute> ht;
    Robot robot;
    Stack forthCommands;
    
    public WordTranslator(Robot robotAI, Stack commands) throws NoSuchMethodException{
    
        robot = robotAI;
        forthCommands = commands;
        ht= new HashMap<String, Execute>();
        
        //ht.put("play", null);
        
        //ht.put("begin", null);
        
        //ht.put(("until"), null);
        
        //This will deal with "-" for subtraction
        ht.put("-", (Execute) () -> {
            int var1 = (int)robot.forthValues.pop();
            int var2 = (int)robot.forthValues.pop();
            int var3;
            var3 = var2 - var1;
            robot.forthValues.push(var3);
        });
        
        
        
        ht.put("(", (Execute) () -> {
            while((String)forthCommands.peek() != ")"){
                forthCommands.pop();
            }
            forthCommands.pop();
        });
        
        
        //This will deal with "+" for addition
        ht.put("+", (Execute) () -> {
            int var1 = (int)robot.forthValues.pop();
            int var2 = (int)robot.forthValues.pop();
            int var3;
            var3 = var1 + var2;
            robot.forthValues.push(var3);
        });
      
        
        
        //This will deal with "*" for multiplication
        ht.put("*", (Execute) () -> {
            int var1 = (int)robot.forthValues.pop();
            int var2 = (int)robot.forthValues.pop();
            int var3;
            var3 = var1 * var2;
            robot.forthValues.push(var3);
        });
        
        //This will deal with "/mod" for division, which will push the quotient
        //and remainder to the stack
        ht.put("/mod", (Execute) () -> {
            int var1 = (int)robot.forthValues.pop();
            int var2 = (int)robot.forthValues.pop();
            int var3;
            int var4;
            var3 = var2/var1;
            var4 = var2%var1;
            robot.forthValues.push(var4);
            robot.forthValues.push(var3);
        });
        
        //This will deal with "<", pushing a boolean on the stack
        ht.put("<", (Execute) () -> {
            int var1 = (int)robot.forthValues.pop();
            int var2 = (int)robot.forthValues.pop();
            Boolean var3;
            if(var1 > var2){
                var3 = true;
            }else{
                var3 = false;
            }
            robot.forthValues.push(var3);
        });
        
        //This will deal with "<=", pushing a boolean on the stack
        ht.put("<=", (Execute) () -> {
            int var1 = (int)robot.forthValues.pop();
            int var2 = (int)robot.forthValues.pop();
            Boolean var3;
            if(var1 >= var2){
                var3 = true;
            }else{
                var3 = false;
            }
            robot.forthValues.push(var3);
        });
        
        //This will deal with "=", pushing a boolean on the stack
        ht.put("=", (Execute) () -> {
           int var1 = (int)robot.forthValues.pop();
            int var2 = (int)robot.forthValues.pop();
            Boolean var3;
            if(var1 == var2){
                var3 = true;
            }else{
                var3 = false;
            }
            robot.forthValues.push(var3);
        });
        
        //This will deal with "<>", pushing a boolean on the stack
        ht.put("<>", (Execute) () -> {
           int var1 = (int)robot.forthValues.pop();
            int var2 = (int)robot.forthValues.pop();
            Boolean var3;
            if(var1 != var2){
                var3 = true;
            }else{
                var3 = false;
            }
            robot.forthValues.push(var3);
        });
        
        //This will deal with "=>", pushing a boolean on the stack
        ht.put("=>", (Execute) () -> {
           int var1 = (int)robot.forthValues.pop();
            int var2 = (int)robot.forthValues.pop();
            Boolean var3;
            if(var1 > var2){
                var3 = false;
            }else{
                var3 = true;
            }
            robot.forthValues.push(var3);
        });
        
        //This will deal with ">", pushing a boolean on the stack
        ht.put(">", (Execute) () -> {
           int var1 = (int)robot.forthValues.pop();
            int var2 = (int)robot.forthValues.pop();
            Boolean var3;
            if(var1 >= var2){
                var3 = false;
            }else{
                var3 = true;
            }
            robot.forthValues.push(var3);
        });
        
     
        
        //This will deal with "and", pushing a boolean on the stack
        ht.put("and", (Execute) () -> {
            Boolean var1 = (Boolean)robot.forthValues.pop();
            Boolean var2 = (Boolean)robot.forthValues.pop();
            Boolean var3;
            if((var1 == true )&& (var2 == true)){
                var3 = true;
            }else{
                var3 = false;
            }
            robot.forthValues.push(var3);
        });
        
        //This will deal with "or", pushing a boolean on the stack
        ht.put("or", (Execute) () -> {
            Boolean var1 = (Boolean)robot.forthValues.pop();
            Boolean var2 = (Boolean)robot.forthValues.pop();
            Boolean var3;
            if((var1 == false )&& (var2 == false)){
                var3 = false;
            }else{
                var3 = true;
            }
            robot.forthValues.push(var3);
        });
        
        //This will deal with "invert", pushing a new boolean opposite of the
        //one currently on the stack
        ht.put("invert", (Execute) () -> {
            Boolean var1 = (Boolean) robot.forthValues.pop();
            if(var1 == true ){
                var1 = false;
            }else{
                var1 = true;
            }
            robot.forthValues.push(var1);
        });
        
       
        
        //This will deal with "if", parsing through the if code when the top
        //of the stack has a true boolean and then deleting everything that
        //is associated with the else statement.  It will do the opposite if
        //the top of the stack is false
        ht.put("if", (Execute) () -> {
            BoolValue var1 = (BoolValue) robot.forthValues.pop();
            Stack ifCommands = new Stack();
            if(var1.b == true ){
                while(forthCommands.peek() != "else"){
                    ifCommands.push(forthCommands.pop());
                }
                Stack orderedIfCommands = new Stack();
                while(!ifCommands.empty()){
                    orderedIfCommands.push(ifCommands.pop());
                }
                try {
                    WordTranslator translateIf = new WordTranslator(robot, ifCommands);//Just have to execute somehow
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(WordTranslator.class.getName()).log(Level.SEVERE, null, ex);
                }
                while(forthCommands.peek() != "then"){
                    forthCommands.pop();
                }
                forthCommands.pop();
            }else{
                while(forthCommands.peek() != "else"){
                    forthCommands.pop();
                }
                forthCommands.pop();
                while(forthCommands.peek() != "then"){
                    ifCommands.push(forthCommands.pop());
                }
                Stack orderedIfCommands = new Stack();
                while(!ifCommands.empty()){
                    orderedIfCommands.push(ifCommands.pop());
                }
                try {
                    WordTranslator translateIf = new WordTranslator(robot, ifCommands);//Just have to execute somehow
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(WordTranslator.class.getName()).log(Level.SEVERE, null, ex);
                }
                forthCommands.pop();
            }
        });
        
        
        
        //Don't need these
        //ht.put("else", null);//These are dealt with with the if statement.
        //ht.put("then", null);
        //ht.put("loop", null);
        
        //ht.put(":", null);
        //ht.put(";", null);
        
        ht.put("variable", (Execute) () -> {
            robot.forthValues.pop();
        });
        
        //ht.put("?", null);
        //ht.put("!", null);
        //ht.put(".", null);
        //ht.put("random", null);
        
        //This will just delete the element at the top of the stack
        ht.put("drop", (Execute) () -> {
            robot.forthValues.pop();
        });
        
        //This will duplicate the value at the top of the stack.
        ht.put("dup", (Execute) () -> {
            Stack temp = new Stack();
            temp.push(robot.forthValues.peek());
            robot.forthValues.push(temp.pop());
        });
        
        //This will swap the top two values on the stack
        ht.put("swap", (Execute) () -> {
            Object temp1 = robot.forthValues.pop();
            Object temp2 = robot.forthValues.pop();
            robot.forthValues.push(temp1);
            robot.forthValues.push(temp2);
        });
        
        
        
        //ht.put("rot", null);
        
        
        //Status keys
        ht.put("health", (Execute) () -> {
            robot.forthValues.add(robot.getHealth());
        });
        
        ht.put("healthLeft", (Execute) () -> {
            robot.forthValues.add(robot.getHealthLeft());
        });
        
        ht.put("moves", (Execute) () -> {
            robot.forthValues.add(robot.getMovement());
        });
        
        ht.put("movesLeft", (Execute) () -> {
            robot.forthValues.add(robot.getMovementLeft());
        });
        
        ht.put("attack", (Execute) () -> {
            robot.forthValues.push(robot.getDamage());
        });
        
        ht.put("range", (Execute) () -> {
            robot.forthValues.push(robot.getRange());
        });
        
        ht.put("team", (Execute) () -> {
            robot.forthValues.push(robot.getGang());
        });
        
        ht.put("type", (Execute) () -> {
            robot.forthValues.push(robot.getType());
        });
        
        //Action Keys
        ht.put("turn!", (Execute) () -> {
            ScoutAI temp = (ScoutAI) robot;
            temp.turn((int)robot.forthValues.pop());
        });
        
        ht.put("move!", (Execute) () -> {
            ScoutAI temp = (ScoutAI) robot;
            temp.move();
        });
        
        
        
        //Need to fix the robotAI shoot function first
        //ht.put("shoot!", null);
        
        
        
        ht.put("check!", new Execute(){public void execute(){
            ScoutAI temp = (ScoutAI) robot;
            Exception x = null;
            try{
                if(temp.check((int)robot.forthValues.peek())){
                    robot.forthValues.pop();
                    robot.forthValues.push("EMPTY");
                }
                else{
                    robot.forthValues.pop();
                    robot.forthValues.push("OCCUPIED");
                }
            }
            catch(Exception e){
                x = e;
            }
            if( x != null){
                robot.forthValues.pop();
                robot.forthValues.push("OUT OF BOUNDS");
            }
        }});
        
        
        
        
        ht.put("scan!", (Execute) () -> {
            ScoutAI temp = (ScoutAI) robot;
            robot.forthValues.push(temp.scan());
        });
        
        
        ht.put("identify!", (Execute) () -> {
            int index = (int) robot.forthValues.pop();
            ScoutAI scaned = (ScoutAI)robot;
            robot.forthValues.push(scaned.scannedRobotsList.get(index).getGang());
            robot.forthValues.push(scaned.scannedRobotsList.get(index).getRange());
            //robot.forthValues.push(scaned.scannedRobotsList.get(index).getRelativeDirection());
            robot.forthValues.push(scaned.scannedRobotsList.get(index).getHealthLeft());
        });
        
        
        ht.put("send!", (Execute) () -> {
            Value send = (Value) robot.forthValues.pop();
            String target = (String) robot.forthValues.pop();
            // Iterator search = new Iterator(robot.board.)
        });
        
        
        //ht.put("mesg?", null);
        
        //ht.put("recv!", null);
        
}
    
    public HashMap<String, Execute> getHashMap(){
        return ht;
    }
    
    public void addStackValues(){
        
    }
    
    
    public static void main(String [] args) throws NoSuchMethodException {
        
        Stack forthWords = new Stack();
        //forthWords.push("1");
        //forthWords.push("1");
        forthWords.push("-");
        ScoutAI scout1 = new ScoutAI("scout");
        scout1.forthValues.push(1);
        scout1.forthValues.push(1);
       
        WordTranslator translate = new WordTranslator(scout1, forthWords);
        translate.getHashMap().get(forthWords.pop()).execute();
         System.out.println(scout1.forthValues.pop());
         
         
        
        /*
        Interpreter interpret = new Interpreter();
        JSONObject testRobot = new JSONObject();
        ScoutAI scout1 = new ScoutAI("scout");
        JSONArray list2 = new JSONArray();
        list2.add("+");
        testRobot.put("code", list2);
        JSONObject testScript = new JSONObject();
        testScript.put("script", testRobot);
        System.out.println(testScript);
        scout1.forthValues.push(1);
        scout1.forthValues.push(2);
        interpret.executeCode(testScript, scout1);
     
        System.out.println(scout1.forthValues.pop());
        */
        
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
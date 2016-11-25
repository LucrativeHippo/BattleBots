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
    Interpreter interpreter;
    
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
            Boolean var1 = (boolean)robot.forthValues.pop();
            Stack ifCommands = new Stack();
            System.out.println("begin if");
            if(var1 == true ){
                try {
                    if(forthCommands.isEmpty()){
                        System.out.println("empty");
                    }
                    while((forthCommands.peek().toString().compareTo("else")!=0)&&(forthCommands.peek().toString().compareTo("if")!=0)){
                        
                        
                        ifCommands.push(forthCommands.pop());
                        //
                        //Testing
                        System.out.println("Test 1:" + ifCommands.peek());
                        //
                        //
                        
                        
                        
                        
                       
                    }
                    if(forthCommands.peek().toString().compareTo("if")==0){
                            ifCommands.push(forthCommands.pop());
                            
                            System.out.println("is a " + ifCommands.peek());
                        }
                        
                    System.out.println("hey");
                    Stack orderedIfCommands = new Stack();
                    while(!ifCommands.empty()){
                        orderedIfCommands.push(ifCommands.pop());
                        
                    }
                   
                    Interpreter interpreter = new Interpreter();
                    WordTranslator translate;
                    translate = new WordTranslator(robot, commands);
                    while(!orderedIfCommands.empty()){
                        
                        if(interpreter.isInteger((String)orderedIfCommands.peek())){
                            robot.forthValues.push(Integer.parseInt((String)orderedIfCommands.pop()));
                        }else if(interpreter.isBoolean((String)orderedIfCommands.peek())){
                            robot.forthValues.push(Boolean.parseBoolean((String)orderedIfCommands.pop()));
                        }else{
                            System.out.println("recursion");
                            translate.getHashMap().get(orderedIfCommands.pop()).execute();
                        }
                        if(!orderedIfCommands.empty()){
                            //
                            //Testing
                            System.out.println("top of temp stack" + robot.forthValues.peek());
                            //
                            //
                        }}
                    while((forthCommands.peek().toString().compareTo("then")!=0)&&(forthCommands.peek().toString().compareTo("if")!=0)){
                        //
                        //Testing
                        System.out.println(forthCommands.peek());
                        //
                        //
                        forthCommands.pop();
                        
                    }
                    System.out.println("end then loop");
                    if(forthCommands.peek().toString().compareTo("then")==0){
                    forthCommands.pop();
                        Stack thenStack = new Stack();
                        //while((forthCommands.peek().toString().compareTo(";")!=0)&&forthCommands.peek().toString().compareTo("until")!=0){
                            
                        //}
                    
                            //
                            //
                            //  CHECK THEN IN ROBOTS
                            //
                            //
                            //
                    }
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(WordTranslator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    int numIf = 0;
                    while((forthCommands.peek().toString().compareTo("else") != 0)||(numIf!=0)){
                        if(forthCommands.peek().toString().compareTo("if")==0){numIf=numIf+1;}
                        if(forthCommands.peek().toString().compareTo("else")==0){numIf=numIf-1;}
                        forthCommands.pop();
                    }
                    forthCommands.pop();
                    while((forthCommands.peek().toString().compareTo("then") != 0)&&(forthCommands.peek().toString().compareTo("then") != 0)){
                        ifCommands.push(forthCommands.pop());
                    }
                    if(forthCommands.peek().toString().compareTo("if")==0){
                            ifCommands.push(forthCommands.pop());
                            
                            System.out.println("is a " + ifCommands.peek());
                        }
                    Stack orderedIfCommands = new Stack();
                    while(!ifCommands.empty()){
                        orderedIfCommands.push(ifCommands.pop());
                    }
                    Interpreter interpreter = new Interpreter();
                    WordTranslator translate;
                    translate = new WordTranslator(robot, orderedIfCommands);
                    while(!orderedIfCommands.empty()){
                        
                        if(interpreter.isInteger((String)orderedIfCommands.peek())){
                            robot.forthValues.push(Integer.parseInt((String)orderedIfCommands.pop()));
                        }else if(interpreter.isBoolean((String)orderedIfCommands.peek())){
                            robot.forthValues.push(Boolean.parseBoolean((String)orderedIfCommands.pop()));
                        }else{
                            translate.getHashMap().get(orderedIfCommands.pop()).execute();
                        }
                        if(!orderedIfCommands.empty()){
                            //
                            //Testing
                            System.out.println("The top of the if statement is" + robot.forthValues.peek());
                            //
                            //
                        }}
                    while(forthCommands.peek().toString().compareTo("then")!=0){
                        forthCommands.pop();
                    }
                    forthCommands.pop();
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(WordTranslator.class.getName()).log(Level.SEVERE, null, ex);
                }
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
            robot.forthValues.push(robot.getHealth());
        });
        
        ht.put("healthLeft", (Execute) () -> {
            robot.forthValues.push(robot.getHealthLeft());
        });
        
        ht.put("moves", (Execute) () -> {
            robot.forthValues.push(robot.getMovement());
        });
        
        ht.put("movesLeft", (Execute) () -> {
            robot.forthValues.push(robot.getMovementLeft());
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
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
import model.SniperAI;
import model.TankAI;
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
import java.util.Random;


/**
 *
 * @author mkp003
 */
public class WordTranslator implements Execute{
    HashMap <String, Execute> ht;
    Robot robot;
    Stack forthCommands;
    Interpreter interpreter;
    int loopCount;
    int loopMax;
    
    public WordTranslator(Robot robotAI, Stack commands) throws NoSuchMethodException{
    
        robot = robotAI;
        forthCommands = commands;
        interpreter = new Interpreter();
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
        

        ht.put("do", (Execute)  () -> {
            int temp1 = (int)robot.forthValues.pop();
            int temp2 = (int)robot.forthValues.pop();
            this.loopMax = temp2;
            this.loopCount = temp1;
             
            Stack backwardsForth = new Stack();
            while(forthCommands.peek().toString().compareTo("loop")!=0){
                backwardsForth.push(forthCommands.pop());
               
            }
            forthCommands.pop(); //pops the loop string
            forthCommands.pop(); //pops the ;
            
            Stack loopForth = new Stack();
            while(!backwardsForth.isEmpty()){
                loopForth.push(backwardsForth.pop());
            }
            Stack temp = new Stack(); 
            for(int i=temp1; i < temp2; i++){
                while(!loopForth.empty()){
                    
                    try {
                        WordTranslator translate;
                        translate = new WordTranslator(robot, commands);
                        if(interpreter.isInteger((String)loopForth.peek())){
                            robot.forthValues.push(Integer.parseInt((String)loopForth.peek()));
                            temp.push(loopForth.pop());
                        }else if(interpreter.isBoolean((String)loopForth.peek())){
                            robot.forthValues.push(Boolean.parseBoolean((String)loopForth.peek()));
                            temp.push(loopForth.pop());
                        }else{
                            translate.getHashMap().get((String)loopForth.peek()).execute();
                            temp.push(loopForth.pop());
                        }
                        if(!loopForth.empty()){
                            
                        }
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(WordTranslator.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
                while(!temp.isEmpty()){
                    loopForth.push(temp.pop());
                }
            }
            
            
        });
        
        ht.put("begin", (Execute)  () -> {
            boolean loopEnd = false;
            Stack backwardsForth = new Stack();
            while(forthCommands.peek().toString().compareTo("until")!=0){
                backwardsForth.push(forthCommands.pop());
               
            }
            forthCommands.pop(); //pops the loop string
            forthCommands.pop(); //pops the ;
            
            Stack loopForth = new Stack();
            while(!backwardsForth.isEmpty()){
                loopForth.push(backwardsForth.pop());
            }
            Stack temp = new Stack(); 
            while(loopEnd = false){
                while(!loopForth.empty()){
                    
                    try {
                        WordTranslator translate;
                        translate = new WordTranslator(robot, commands);
                        if(interpreter.isInteger((String)loopForth.peek())){
                            robot.forthValues.push(Integer.parseInt((String)loopForth.peek()));
                            temp.push(loopForth.pop());
                        }else if(interpreter.isBoolean((String)loopForth.peek())){
                            robot.forthValues.push(Boolean.parseBoolean((String)loopForth.peek()));
                            temp.push(loopForth.pop());
                        }else{
                            translate.getHashMap().get((String)loopForth.peek()).execute();
                            temp.push(loopForth.pop());
                        }
                        if(!loopForth.empty()){
                            
                        }
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(WordTranslator.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
                if(robot.forthValues.peek().toString().compareTo("true")==0){
                    loopEnd = true;
                }
                while(!temp.isEmpty()){
                    loopForth.push(temp.pop());
                }
            }
            
        });
        
        
        
        ht.put(":", (Execute) () -> {
            String key = (String)forthCommands.pop();
            //
            //
            System.out.println("beginning : dictionary");
            //
            //
            Stack backwardsForth = new Stack();
            while(forthCommands.peek().toString().compareTo(";")!=0){
                backwardsForth.push(forthCommands.pop());
               
            }
            forthCommands.pop();
            Stack variableForth = new Stack();
            while(!backwardsForth.isEmpty()){
                variableForth.push(backwardsForth.pop());
            }
            
            ht.put(key, (Execute)  () -> {
                 Stack temp = new Stack(); 
               
                if(variableForth.isEmpty()){
                    System.out.println("empty");
                    robot.forthValues.push(0);
                }else{
                while(!variableForth.empty()){
                    
                    try {
                        WordTranslator translate;
                        translate = new WordTranslator(robot, commands);
                        if(interpreter.isInteger((String)variableForth.peek())){
                            robot.forthValues.push(Integer.parseInt((String)variableForth.peek()));
                            temp.push(variableForth.pop());
                        }else if(interpreter.isBoolean((String)variableForth.peek())){
                            robot.forthValues.push(Boolean.parseBoolean((String)variableForth.peek()));
                            temp.push(variableForth.pop());
                        }else{
                            translate.getHashMap().get((String)variableForth.peek()).execute();
                            temp.push(variableForth.pop());
                        }
                        if(!variableForth.empty()){
                            //
                            //Testing
                            System.out.println("The top of the variable stack is" + robot.forthValues.peek());
                            //
                            //
                        }
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(WordTranslator.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
                while(!temp.isEmpty()){
                    variableForth.push(temp.pop());
                }
                }});
            
           
           
        
        
        
        });
        
        //Does nothing as the value is already pushed to the top of the stack
        ht.put("?", (Execute)  () -> {
            
        });
        




        //
        ht.put("!", (Execute)  () -> {
            
        });
        
        
        
        
        
        
        
        ht.put(".", (Execute)  () -> {
            String s = new String();
            if(forthCommands.peek().toString().compareTo("\"")!=0){
                System.out.println("There is no string");
            }else{
                forthCommands.pop();
            while(forthCommands.peek().toString().compareTo("\"")!=0){
            s = s.concat((String)forthCommands.pop());
            }
            System.out.println(s);
            }
        });
        
        //Generates a random number between 0 and the given integer
        ht.put("random", (Execute)  () -> {
            int temp1 = (int)robot.forthValues.pop();
            Random randomNum = new Random();
            int temp2 = randomNum.nextInt(temp1);
            robot.forthValues.push(temp2);
        });
        
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
        
        
        //Rotates the top 3 values of the stack
        ht.put("rot", (Execute) () -> {
            Object temp1 = robot.forthValues.pop();
            Object temp2 = robot.forthValues.pop();
            Object temp3 = robot.forthValues.pop();
            robot.forthValues.push(temp2);
            robot.forthValues.push(temp1);
            robot.forthValues.push(temp3);
            
            
        });
        
        
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
            if(robot.getType().compareTo("SNIPER")==0){
                SniperAI temp = (SniperAI) robot;
                temp.turn((int)robot.forthValues.pop());
            }
             if(robot.getType().compareTo("SCOUT")==0){
                ScoutAI temp = (ScoutAI) robot;
                temp.turn((int)robot.forthValues.pop());
            }
              if(robot.getType().compareTo("TANK")==0){
                TankAI temp = (TankAI) robot;
                temp.turn((int)robot.forthValues.pop());
            }
            
        });
        
        ht.put("move!", (Execute) () -> {
            if(robot.getType().compareTo("SNIPER")==0){
                SniperAI temp = (SniperAI) robot;
                temp.move();
            }
             if(robot.getType().compareTo("SCOUT")==0){
                ScoutAI temp = (ScoutAI) robot;
                temp.move();
             }
                
              if(robot.getType().compareTo("TANK")==0){
                TankAI temp = (TankAI) robot;
                temp.move();
              
              }
        });
        
        ht.put("shoot!", (Execute)  () -> {
            int range = (int)robot.forthValues.pop();
            int direction = (int)robot.forthValues.pop();
            
            if(robot.getType().compareTo("SNIPER")==0){
                SniperAI temp = (SniperAI) robot;
                //temp.shoot(direction, range);
            }
             if(robot.getType().compareTo("SCOUT")==0){
                ScoutAI temp = (ScoutAI) robot;
                //temp.shoot(direction, range);
             }
                
              if(robot.getType().compareTo("TANK")==0){
                TankAI temp = (TankAI) robot;
                //temp.shoot(direction, range);
              
              }
            
        });
        
        
        
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
        
        //Sends a message to a specified ally
        ht.put("send!", (Execute) () -> {
            Boolean sent = false;
            Object send = robot.forthValues.pop();
            String target = (String) robot.forthValues.pop();
            Iterator<Robot> search = robot.board.aliveList.iterator();
                while(search.hasNext()){
                    Robot temp = search.next();
                    if(temp.getName().compareTo(target)==0){
                        if(robot.getType().compareTo("SNIPER")==0){
                            if(temp.sniperMailBox.size()>6){
                            System.out.println("mailbox is full");
                            }else{
                        
                            temp.sniperMailBox.push(send);
                            sent = true;
                            }
                        }else
                        if(robot.getType().compareTo("SCOUT")==0){
                            if(temp.scoutMailBox.size()>6){
                            System.out.println("mailbox is full");
                            }else{
                        
                            temp.scoutMailBox.push(send);
                            sent = true;
                            }
                        }
                        else if(robot.getType().compareTo("TANK")==0){
                                if(temp.tankMailBox.size()>6){
                                System.out.println("mailbox is full");
                            }else{
                        
                                temp.tankMailBox.push(send);
                                sent = true;
                                }
                            
                            }
                        }
                }
                robot.forthValues.push(sent);
        });
        
        //Checks if the robot has mail from the a specific target and returns true if it does, false otherwise
        ht.put("mesg?", (Execute)  () -> {
            Boolean message = false; 
            String target = (String) robot.forthValues.pop();
            Iterator<Robot> search = robot.board.aliveList.iterator();
                while(search.hasNext()){
                    Robot temp = search.next();
                    if(temp.getName().compareTo(target)==0){
                        if(temp.getType().compareTo("SNIPER")==0){
                            if(robot.sniperMailBox.size()!=0){
                                message = true;
                            }
                        }
                        if(temp.getType().compareTo("TANK")!=0){
                            if(robot.tankMailBox.size()==0){
                                message = true;
                            }
                        }
                        if(temp.getType().compareTo("SCOUT")!=0){
                            if(robot.scoutMailBox.size()==0){
                                message = true;
                            }
                        }
                    }
                }
                robot.forthValues.push(message);
                
        });
        
        
        //Puts the top mail item from a specified robot onto the stack
        ht.put("recv!", (Execute)  () -> {
            String target = (String) robot.forthValues.pop();
            Iterator<Robot> search = robot.board.aliveList.iterator();
                while(search.hasNext()){
                    Robot temp = search.next();
                    if(temp.getName().compareTo(target)==0){
                        if(temp.getType().compareTo("SNIPER")==0){
                            robot.forthValues.push(robot.sniperMailBox.pop());
                        }
                        if(temp.getType().compareTo("TANK")!=0){
                            robot.forthValues.push(robot.tankMailBox.pop());
                        }
                        if(temp.getType().compareTo("SCOUT")!=0){
                            robot.forthValues.push(robot.scoutMailBox.pop());
                        }
                    }
                }
              
                
        });
        
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
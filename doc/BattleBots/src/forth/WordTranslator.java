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
            System.out.println("the subtraction value is" + var3);
            robot.forthValues.push(var3);
        });
        
        
        ht.put(";", (Execute) () -> {
            
        });
        
        ht.put("then", (Execute) () -> {
            
        });
        
        ht.put("loop", (Execute) () -> {
            
        });
        
        ht.put("until", (Execute) () -> {
            
        });
        
        //Removes comments from the code
        ht.put("(", (Execute) () -> {
            System.out.println("in the bracket");
            while(forthCommands.peek().toString().compareTo(")")!=0){
                
                
                forthCommands.pop();
            }
            forthCommands.pop();
        });
        
        
        //This will deal with "+" for addition
        ht.put("+", (Execute) () -> {
            int var1 = (int)robot.forthValues.pop();
            System.out.println(var1);
            int var2 = (int)robot.forthValues.pop();
            System.out.println(var2);
            int var3;
            var3 = var1 + var2;
            System.out.println("the addition value is" + var3);
            robot.forthValues.push(var3);
        });
      
        
        
        //This will deal with "*" for multiplication
        ht.put("*", (Execute) () -> {
            int var1 = (int)robot.forthValues.pop();
            int var2 = (int)robot.forthValues.pop();
            int var3;
            var3 = var1 * var2;
            System.out.println("the multiplication value is" + var2);
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
        
        
        ht.put(";", (Execute) () -> {
            
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
            Object var1 = robot.forthValues.pop();
            Object var2 = robot.forthValues.pop();
            Boolean var3;
            if(var1==var2){
                var3 = true;
            }else{
                var3 = false;
            }
            robot.forthValues.push(var3);
        });
        
        //This will deal with "<>", pushing a boolean on the stack
        ht.put("<>", (Execute) () -> {
            Object var1 = robot.forthValues.pop();
            Object var2 = robot.forthValues.pop();
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
            System.out.println(var1);
            Boolean var2 = (Boolean)robot.forthValues.pop();
            System.out.println(var2);
            Boolean var3;
            if((var1 == true )&& (var2 == true)){
                var3 = true;
            }else{
                var3 = false;
            }
            System.out.println(var3);
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
            System.out.println("in the if");
            Boolean var1 = (boolean)robot.forthValues.pop();
            Stack ifCommands = new Stack();
            System.out.println("begin if");
            if(var1 == true ){
               
                    
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
                    while(!ifCommands.empty()){
                        forthCommands.push(ifCommands.pop());
                        
                    }
                   
                    
                    while((forthCommands.peek().toString().compareTo("loop")!=0)&&(!forthCommands.isEmpty())&&(forthCommands.peek().toString().compareTo("else")!=0)){
                        
                        if(interpreter.isInteger((String)forthCommands.peek())){
                            robot.forthValues.push(Integer.parseInt((String)forthCommands.pop()));
                        }else if(interpreter.isBoolean((String)forthCommands.peek())){
                            robot.forthValues.push(Boolean.parseBoolean((String)forthCommands.pop()));
                        }else if(forthCommands.peek().toString().compareTo(".\"")==0){
                            System.out.println("there is a string");
                            forthCommands.pop();
                            this.getHashMap().get("string").execute();
                        }else if(robot.variables.containsKey(forthCommands.peek())){
                            robot.variableStack.push(forthCommands.pop());
                            
                        }else{
                            System.out.println("recursion");
                             System.out.println(forthCommands.peek());
                            this.getHashMap().get(forthCommands.pop()).execute();
                        }
                        if(!forthCommands.empty()){
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
                
                
            }else{
                
                    int numIf = 0;
                    while((forthCommands.peek().toString().compareTo("else") != 0)||(numIf!=0)){
                        System.out.println("begin else");
                        if(forthCommands.peek().toString().compareTo("if")==0){numIf=numIf+1;}
                        if(forthCommands.peek().toString().compareTo("else")==0){numIf=numIf-1;}
                        System.out.println(forthCommands.peek());
                        forthCommands.pop();
                    }
                    forthCommands.pop();
                    System.out.println("finished copying ontothe else stack");
                    while((forthCommands.peek().toString().compareTo("then") != 0)&&(forthCommands.peek().toString().compareTo("loop") != 0)){
                        ifCommands.push(forthCommands.pop());
                         System.out.println(ifCommands.peek());
                    }
                    if(forthCommands.peek().toString().compareTo("loop")==0){
                        ifCommands.push(forthCommands.pop());
                         System.out.println(ifCommands.peek());
                         
                    }
                    if(forthCommands.peek().toString().compareTo("if")==0){
                            ifCommands.push(forthCommands.pop());
                            
                            System.out.println("is a " + ifCommands.peek());
                        }
                    System.out.println(forthCommands.peek());
                    while(!ifCommands.empty()){
                        forthCommands.push(ifCommands.pop());
                        System.out.println(forthCommands.peek());
                    }
                    
                    
                    while((!forthCommands.empty())&&(forthCommands.peek().toString().compareTo("then")!=0)&&(forthCommands.peek().toString().compareTo("loop")!=0)){
                        
                        
                        if(interpreter.isInteger((String)forthCommands.peek())){
                            robot.forthValues.push(Integer.parseInt((String)forthCommands.pop()));
                        }else if(interpreter.isBoolean((String)forthCommands.peek())){
                            robot.forthValues.push(Boolean.parseBoolean((String)forthCommands.pop()));
                        }else if(forthCommands.peek().toString().compareTo(".\"")==0){
                            System.out.println("there is a string");
                            forthCommands.pop();
                            this.getHashMap().get("string").execute();
                        }else if(robot.variables.containsKey(forthCommands.peek())){
                            System.out.println(forthCommands.peek());
                            robot.variableStack.push(forthCommands.pop());
                            
                        }else{
                             
                            this.getHashMap().get(forthCommands.pop()).execute();
                        }
                        if(!forthCommands.empty()){
                            //
                            //Testing
                            //System.out.println("The top of the if statement is" + robot.forthValues.peek());
                            //
                            //
                        }}
                    while(forthCommands.peek().toString().compareTo("then")!=0){
                        System.out.println(forthCommands.peek());
                        forthCommands.pop();
                    }
                    forthCommands.pop();
                 
            }
        });
        

        ht.put("do", (Execute)  () -> {
            System.out.println("Starting the do loop");
            int temp1 = (int)robot.forthValues.pop();
            int temp2 = (int)robot.forthValues.pop();
            this.loopMax = temp2;
            this.loopCount = temp1;
             
            Stack backwardsForth = new Stack();
            System.out.println(forthCommands.peek());
            while(forthCommands.peek().toString().compareTo("loop")!=0){
                backwardsForth.push(forthCommands.pop());
                System.out.println(backwardsForth.peek());
               
            }
            backwardsForth.push(forthCommands.pop()); //pops the loop string
            
            Stack loopForth = new Stack();
            while(!backwardsForth.isEmpty()){
                loopForth.push(backwardsForth.pop());
            }
            Stack temp = new Stack(); 
            for(int i=temp1; i < temp2+1; i++){
                while(!loopForth.empty()){
                    
                  
                        
                        if(interpreter.isInteger((String)loopForth.peek())){
                            robot.forthValues.push(Integer.parseInt((String)loopForth.peek()));
                            temp.push(loopForth.pop());
                        }else if(interpreter.isBoolean((String)loopForth.peek())){
                            robot.forthValues.push(Boolean.parseBoolean((String)loopForth.peek()));
                            temp.push(loopForth.pop());
                        }else if(loopForth.peek().toString().compareTo(".\"")==0){
                            


                        //CANNOT handle strings properly
                            
                            
                            temp.push(loopForth.pop());
                            System.out.println("there is a string");
                            this.getHashMap().get("string").execute();
                            
                        }else if(robot.variables.containsKey(loopForth.peek())){
                            robot.variableStack.push(temp.peek());
                            temp.push(loopForth.pop());
                            
                        }else{
                            System.out.println(loopForth.peek());
                            this.getHashMap().get((String)loopForth.peek()).execute();
                            temp.push(loopForth.pop());
                        }
                        if(!loopForth.empty()){
                            
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
            backwardsForth.push(forthCommands.pop()); //pops the until string
            
            Stack loopForth = new Stack();
            while(!backwardsForth.isEmpty()){
                loopForth.push(backwardsForth.pop());
            }
            Stack temp = new Stack(); 
            while(loopEnd == false){
                while(!loopForth.empty()){
                    
                    
                        
                        if(interpreter.isInteger((String)loopForth.peek())){
                            robot.forthValues.push(Integer.parseInt((String)loopForth.peek()));
                            temp.push(loopForth.pop());
                        }else if(interpreter.isBoolean((String)loopForth.peek())){
                            robot.forthValues.push(Boolean.parseBoolean((String)loopForth.peek()));
                            temp.push(loopForth.pop());
                        }else if(forthCommands.peek().toString().compareTo(".\"")==0){
                            System.out.println("there is a string");
                            forthCommands.pop();
                            this.getHashMap().get("string").execute();
                           
                        }else if(robot.variables.containsKey(loopForth.peek())){
                            robot.variableStack.push(temp.peek());
                            temp.push(loopForth.pop());
                        }else{
                            this.getHashMap().get((String)loopForth.peek()).execute();
                            temp.push(loopForth.pop());
                        }
                        if(!loopForth.empty()){
                            
                        }
                    
            }
                while(!temp.isEmpty()){
                    loopForth.push(temp.pop());
                }
            if(robot.forthValues.peek().toString().compareTo("false")==0){
                robot.forthValues.pop();
                loopEnd = true;
            }
            }
            
            
        });
        
        
        
        ht.put(":", (Execute) () -> {
            String key = (String)forthCommands.pop();
            if(key.compareTo("play")==0){
                 System.out.println("beginning play");
            }else{

            //
            //
            System.out.println("beginning : dictionary");
            //
            //
            Stack backwardsForth = new Stack();
            while(forthCommands.peek().toString().compareTo(";")!=0){
                backwardsForth.push(forthCommands.pop());
               
            }
            backwardsForth.push(forthCommands.pop());
            
            robot.functions.put(key, backwardsForth);
            
            
            ht.put(key, (Execute)  () -> {
                
                System.out.println("EXECUTING A FUNCTION " + key);
                
                if(robot.functions.get(key).isEmpty()){
                    System.out.println("empty");
                }else{
                    Stack backwards = robot.functions.get(key);
                    Stack reusedStack = new Stack();
                    
                    while(!backwards.isEmpty()){
                        reusedStack.push(backwards.peek());
                        forthCommands.push(backwards.pop());
                        System.out.println(forthCommands.peek());
                    }
                 
                while(forthCommands.peek().toString().compareTo(";")!=0){
                    
                        System.out.println("hi");
                        
                        if(interpreter.isInteger((String)forthCommands.peek())){
                            System.out.println("integer added to robotvalue stack is " + forthCommands.peek());
                            robot.forthValues.push(Integer.parseInt((String)forthCommands.pop()));
                             
                        }else if(interpreter.isBoolean((String)forthCommands.peek())){
                            robot.forthValues.push(Boolean.parseBoolean((String)forthCommands.pop()));
                        }else if(robot.variables.containsKey((String)forthCommands.peek())){
                            System.out.println("hello2");
                            robot.variableStack.push(forthCommands.pop());
                            System.out.println(robot.variableStack.peek());
                            
                        }else if(forthCommands.peek().toString().compareTo(".\"")==0){
                            System.out.println("there is a string");
                            forthCommands.pop();
                            this.getHashMap().get("string").execute();
                        
                        }else if((!forthCommands.isEmpty()&&this.getHashMap().containsKey((String)forthCommands.peek()))){
                            {
                                this.getHashMap().get((String)forthCommands.pop()).execute();
                            }
                        }else{
                            System.out.println(forthCommands.peek());
                        }
                        if(!forthCommands.empty()){
                            
                            //
                            //Testing
                            System.out.println("The top of the function stack is" + forthCommands.peek());
                            //
                            //
                        }
                    
                    
            }forthCommands.pop(); // pops the ; off the end of the function
            
            //refills the backwards stack so it can be reused if the variable is called again
            while(!reusedStack.isEmpty()){ 
                backwards.push(reusedStack.pop());
            }    
            robot.functions.put(key, backwards);
                }});
            }});
        
        
        
        
        
        //Creates a new variable
        ht.put("variable", (Execute)  () -> {
            //key is the name of the variable and its "key" for its hashtable
            String key = (String)forthCommands.peek();
            System.out.println("creating new variable " + key);
            //Checks the hashtable see if the variable already exists
            if(!robot.variables.containsKey(key)){
                //puts the new variable into the hashtable, does not currently have a value
                robot.variables.put(key, new Object());
                //pops the varaible name onto the variableStack, which is checked when looking for the last used variable
                robot.variableStack.push(forthCommands.pop());
            
            }else{
                //if variable already exists discards the current variable
                forthCommands.pop();
            }
        });
        
        //Accesses the previous variable and returns its value onto the robot forth values stack
        ht.put("?", (Execute)  () -> {
            System.out.println("executing the ? dictionary");
            if(!robot.variableStack.isEmpty()){
               System.out.println("accessed variable"); 
               //accesses the last variable in a hashtable and pushes its value onto the robot values stack
               //robot.variableStack.pop is the last seen variable and the key for the hashtable
            robot.forthValues.push(robot.variables.get((String)robot.variableStack.pop()));
            System.out.println(robot.forthValues.peek()); 
            
            }else{
                System.out.println("There are no variable to be accessed");
            }
        });
        




        //Accesses the previous variable an inserts a new value 
        ht.put("!", (Execute)  () -> {
            System.out.println("executing the ! dictionary");
            if(!robot.variableStack.isEmpty()){
                System.out.println("accessed variable");
                //puts the new value into the hashtable containing all the variables
                //robot.variableStack.peek is the last seen variable and the key for the hashtable
                robot.variables.put((String)robot.variableStack.peek(), robot.forthValues.pop());
            
            }else{
                System.out.println("There are no variable to be accessed");
            }
        });
        
        
        
        
        
        
        //Pushes a string of words onto the robot values stack
        ht.put("string", (Execute)  () -> {
            System.out.println("inside the string function");
            String s = new String();
            while(forthCommands.peek().toString().compareTo("\"")!=0){
            s = s.concat((String)forthCommands.pop());
            s = s.concat(" ");
            }
            forthCommands.pop();
            System.out.println(s);
            robot.forthValues.push(s);
            
        });
        
        //Generates a random number between 0 and the given integer on the robot values stack
        ht.put("random", (Execute)  () -> {
            int temp1 = (int)robot.forthValues.pop();
            Random randomNum = new Random();
            int temp2 = randomNum.nextInt(temp1);
            robot.forthValues.push(temp2);
        });
        
        //This will just delete the element at the top of the robotvalues stack
        ht.put("drop", (Execute) () -> {
            System.out.println("DROP");
            robot.forthValues.pop();
        });
        
        //This will duplicate the value at the top of the robotvalues stack.
        ht.put("dup", (Execute) () -> {
            Stack temp = new Stack();
            temp.push(robot.forthValues.peek());
            robot.forthValues.push(temp.pop());
        });
        
        //This will swap the top two values on the robotvalues stack
        ht.put("swap", (Execute) () -> {
            Object temp1 = robot.forthValues.pop();
            Object temp2 = robot.forthValues.pop();
            robot.forthValues.push(temp1);
            robot.forthValues.push(temp2);
        });
        
        
        //Rotates the top 3 values of the robotvalues stack
        ht.put("rot", (Execute) () -> {
            Object temp1 = robot.forthValues.pop();
            Object temp2 = robot.forthValues.pop();
            Object temp3 = robot.forthValues.pop();
            robot.forthValues.push(temp2);
            robot.forthValues.push(temp1);
            robot.forthValues.push(temp3);
            
            
        });
        
        
        //Status keys
        
        //pushes the current robots health to the top of the forthvalues stack
        ht.put("health", (Execute) () -> {
            robot.forthValues.push(robot.getHealth());
        });
        
        //pushes the current robots healthleft to the top of the forthvalues stack
        ht.put("healthLeft", (Execute) () -> {
            robot.forthValues.push(robot.getHealthLeft());
        });
        
        //pushes the current robots movesleft to the top of the forthvalues stack
        ht.put("movesLeft", (Execute) () -> {
            System.out.println("the movement left is " + robot.getMovementLeft());
            robot.forthValues.push(robot.getMovementLeft());
        });
        
        //pushes the current robots attack to the top of the forthvalues stack
        ht.put("attack", (Execute) () -> {
            robot.forthValues.push(robot.getDamage());
        });
        
        //pushes the current robots range to the top of the forthvalues stack
        ht.put("range", (Execute) () -> {
            robot.forthValues.push(robot.getRange());
        });
        
        //pushes the current robots team to the top of the forthvalues stack
        ht.put("team", (Execute) () -> {
            robot.forthValues.push(robot.getGang());
        });
        
        //pushes the current robots type to the top of the forthvalues stack
        ht.put("type", (Execute) () -> {
            robot.forthValues.push(robot.getType());
        });
        
        //Action Keys
        ht.put("turn!", (Execute) () -> {
            //Checks if the robot is a sniper
            if(robot.getType().compareTo("SNIPER")==0){
                SniperAI temp = (SniperAI) robot;
                //Turns the integer amount on top of the robot value stack
                temp.turn((int)robot.forthValues.pop());
            }
            //Checks if the robot is a scout 
            if(robot.getType().compareTo("SCOUT")==0){
                //Turns the integer amount on top of the robot value stack
                ScoutAI temp = (ScoutAI) robot;
                temp.turn((int)robot.forthValues.pop());
            }
            //Checks if the robot is a tank  
            if(robot.getType().compareTo("TANK")==0){
                //Turns the integer amount on top of the robot value stack
                TankAI temp = (TankAI) robot;
                temp.turn((int)robot.forthValues.pop());
            }
              System.out.println("the curent direction is" + robot.getRelativeDirection());
            
        });
        
        //moves the current robot in the correct direction
        ht.put("move", (Execute) () -> {
            System.out.println(robot.getHorizontalLocation());
            System.out.println(robot.getVerticalLocation());
            System.out.println(robot.getRelativeDirection());
            //Checks if the robot is a sniper
            if(robot.getType().compareTo("SNIPER")==0){
                SniperAI temp = (SniperAI) robot; 
                try {
                    // creates a temporary robot with the corresponding types move value
                    temp.move();
                } catch (Exception ex) {
                    Logger.getLogger(WordTranslator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Checks if the robot is a scout
             if(robot.getType().compareTo("SCOUT")==0){
                ScoutAI temp = (ScoutAI) robot;
                try {
                    // creates a temporary robot with the corresponding types move value
                    temp.move();
                } catch (Exception ex) {
                    Logger.getLogger(WordTranslator.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(temp.getHorizontalLocation());
                System.out.println(temp.getVerticalLocation());
                System.out.println(temp.getRelativeDirection());
            
             }
              //Checks if the robot is a tank
              if(robot.getType().compareTo("TANK")==0){
                TankAI temp = (TankAI) robot;
                try {
                    // creates a temporary robot with the corresponding types move value
                    temp.move();
                } catch (Exception ex) {
                    Logger.getLogger(WordTranslator.class.getName()).log(Level.SEVERE, null, ex);
                }
              
              }
        });
        
        ht.put("shoot!", (Execute)  () -> {
            int range = (int)robot.forthValues.pop();
            int direction = (int)robot.forthValues.pop();
            
            //Checks if the robot is a sniper and uses a snipers range and damage on a hex
            if(robot.getType().compareTo("SNIPER")==0){
                SniperAI temp = (SniperAI) robot;
                temp.robotShooting(direction, range);
            }
            //Checks if the robot is a scout and uses a scouts range and damage on a hex
             if(robot.getType().compareTo("SCOUT")==0){
                ScoutAI temp = (ScoutAI) robot;
                temp.robotShooting(direction, range);
             }
             //Checks if the robot is a tank and uses a tanks range and damage on a hex
              if(robot.getType().compareTo("TANK")==0){
                TankAI temp = (TankAI) robot;
                temp.robotShooting(direction, range);
              
              }
            
        });
        
        
        
        ht.put("check!", new Execute(){public void execute(){
            System.out.println("inside the check function");
            if(robot.getType().compareTo("SCOUT")==0){
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
            }
            
            if(robot.getType().compareTo("SNIPER")==0){
            SniperAI temp = (SniperAI) robot;
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
            }
            if(robot.getType().compareTo("TANK")==0){
            TankAI temp = (TankAI) robot;
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
            }
        }});
        
        
        
        
        ht.put("scan!", (Execute) () -> {
            System.out.println("SCANNING");
            if(robot.getType().compareTo("SCOUT")==0){
            ScoutAI temp = (ScoutAI) robot;
            robot.forthValues.push(temp.scan());
            }
            if(robot.getType().compareTo("SNIPER")==0){
            SniperAI temp = (SniperAI) robot;
            robot.forthValues.push(temp.scan());
            }
            if(robot.getType().compareTo("TANK")==0){
            TankAI temp = (TankAI) robot;
            robot.forthValues.push(temp.scan());
            }
        });
        
        
        ht.put("identify!", (Execute) () -> {
            int index = this.loopCount;
            if(robot.getType().compareTo("SCOUT")==0){
            ScoutAI scanned = (ScoutAI)robot;
            System.out.println(index);
            
            robot.forthValues.push(scanned.scannedRobotsList.get(index).getHealthLeft());
            System.out.println("health left is " + robot.forthValues.peek());
            
            robot.forthValues.push(scanned.scannedRobotsList.get(index).getRelativeDirection());
            System.out.println("the relative direction is " + robot.forthValues.peek());
            
            robot.forthValues.push(scanned.scannedRobotsList.get(index).getRange());
            System.out.println("the range is " + robot.forthValues.peek());
            
            robot.forthValues.push(scanned.scannedRobotsList.get(index).getGang());
            System.out.println("the gang is " + robot.forthValues.peek());
            }
            if(robot.getType().compareTo("SNIPER")==0){
            SniperAI scanned = (SniperAI)robot;
            System.out.println(index);
            
            robot.forthValues.push(scanned.scannedRobotsList.get(index).getHealthLeft());
            System.out.println("health left is " + robot.forthValues.peek());
            
            robot.forthValues.push(scanned.scannedRobotsList.get(index).getRelativeDirection());
            System.out.println("the relative direction is " + robot.forthValues.peek());
            
            robot.forthValues.push(scanned.scannedRobotsList.get(index).getRange());
            System.out.println("the range is " + robot.forthValues.peek());
            
            robot.forthValues.push(scanned.scannedRobotsList.get(index).getGang());
            System.out.println("the gang is " + robot.forthValues.peek());
            }
            if(robot.getType().compareTo("TANK")==0){
            TankAI scanned = (TankAI)robot;
            System.out.println(index);
            
            robot.forthValues.push(scanned.scannedRobotsList.get(index).getHealthLeft());
            System.out.println("health left is " + robot.forthValues.peek());
            
            robot.forthValues.push(scanned.scannedRobotsList.get(index).getRelativeDirection());
            System.out.println("the relative direction is " + robot.forthValues.peek());
            
            robot.forthValues.push(scanned.scannedRobotsList.get(index).getRange());
            System.out.println("the range is " + robot.forthValues.peek());
            
            robot.forthValues.push(scanned.scannedRobotsList.get(index).getGang());
            System.out.println("the gang is " + robot.forthValues.peek());
            }
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

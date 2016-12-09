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
    
    
    /**
     * This function creates the word Translator dictionary for the Forth 
     * Language
     * @param robotAI a reference to a Robot object
     * @param commands a stack containing all the forth commands 
     */
    public WordTranslator(Robot robotAI, Stack commands) throws NoSuchMethodException{
    
        
        robot = robotAI;
        forthCommands = commands;
        interpreter = new Interpreter();
        ht= new HashMap<String, Execute>();
        
        
        //This will deal with "-" for subtraction
        ht.put("-", (Execute) () -> {
            //pops a value off the robot forthvalues stack
            int var1 = (int)robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            int var2 = (int)robot.forthValues.pop();
            int var3;
            var3 = var2 - var1;
            //pushes a value onto the robot forth values stack
            robot.forthValues.push(var3);
        });
        
        //does nothing if a ; is executed avoids null pointer exceptions
        ht.put(";", (Execute) () -> {
            
        });
        
         //does nothing if a then is executed avoids null pointer exceptions
        ht.put("then", (Execute) () -> {
            
        });
        
         //does nothing if a loop is executed avoids null pointer exceptions
        ht.put("loop", (Execute) () -> {
            
        });
        
         //does nothing if a until is executed avoids null pointer exceptions
        ht.put("until", (Execute) () -> {
            
        });
        
        //Removes comments from the code
        ht.put("(", (Execute) () -> {
            while(forthCommands.peek().toString().compareTo(")")!=0){
                //removes everything within the brackets
                
                forthCommands.pop();
            }
            forthCommands.pop(); // removes the ) bracket at the end
        });
        
        
        //This will deal with "+" for addition
        ht.put("+", (Execute) () -> {
            int var1 = (int)robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            int var2 = (int)robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            int var3;
            var3 = var1 + var2;
            robot.forthValues.push(var3);
            //pushes a value onto the robot forth values stack
        });
      
        
        
        //This will deal with "*" for multiplication
        ht.put("*", (Execute) () -> {
            int var1 = (int)robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            int var2 = (int)robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            int var3;
            var3 = var1 * var2;
            robot.forthValues.push(var3);
            //pushes a value onto the robot forth values stack
        });
        
        //This will deal with "/mod" for division, which will push the quotient
        //and remainder to the stack
        ht.put("/mod", (Execute) () -> {
            int var1 = (int)robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            int var2 = (int)robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            int var3;
            int var4;
            var3 = var2/var1;
            var4 = var2%var1;
            robot.forthValues.push(var4);
            //pushes a value onto the robot forth values stack
            robot.forthValues.push(var3);
            //pushes a value onto the robot forth values stack
        });
        
        //This will deal with "<", pushing a boolean on the stack
        ht.put("<", (Execute) () -> {
            int var1 = (int)robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            int var2 = (int)robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            Boolean var3;
            if(var1 > var2){
                var3 = true;
            }else{
                var3 = false;
            }
            robot.forthValues.push(var3);
            //pushes a value onto the robot forth values stack
        });
        
        //This will deal with "<=", pushing a boolean on the stack
        ht.put("<=", (Execute) () -> {
            int var1 = (int)robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            int var2 = (int)robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            Boolean var3;
            if(var1 >= var2){
                var3 = true;
            }else{
                var3 = false;
            }
            robot.forthValues.push(var3);
            //pushes a value onto the robot forth values stack
        });
        
        //This will deal with "=", pushing a boolean on the stack
        ht.put("=", (Execute) () -> {
            Object var1 = robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            Object var2 = robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            Boolean var3;
            
            if(var1.toString().compareTo((String)var2)==0){
                var3 = true;
            }else{
                var3 = false;
            }
            robot.forthValues.push(var3);
            //pushes a value onto the robot forth values stack
        });
        
        //This will deal with "<>", pushing a boolean on the stack
        ht.put("<>", (Execute) () -> {
            Object var1 = robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            Object var2 = robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            Boolean var3;
            if(var1 != var2){
                var3 = true;
            }else{
                var3 = false;
            }
            robot.forthValues.push(var3);
            //pushes a value onto the robot forth values stack
        });
        
        //This will deal with "=>", pushing a boolean on the stack
        ht.put("=>", (Execute) () -> {
           int var1 = (int)robot.forthValues.pop();
           //pops a value off the robot forthvalues stack
            int var2 = (int)robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            Boolean var3;
            if(var1 > var2){
                var3 = false;
            }else{
                var3 = true;
            }
            robot.forthValues.push(var3);
            //pushes a value onto the robot forth values stack
        });
        
        //This will deal with ">", pushing a boolean on the stack
        ht.put(">", (Execute) () -> {
           int var1 = (int)robot.forthValues.pop();
           //pops a value off the robot forthvalues stack
            int var2 = (int)robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            Boolean var3;
            if(var1 >= var2){
                var3 = false;
            }else{
                var3 = true;
            }
            robot.forthValues.push(var3);
            //pushes a value onto the robot forth values stack
        });
        
     
        
        //This will deal with "and", pushing a boolean on the stack
        ht.put("and", (Execute) () -> {
            Boolean var1 = (Boolean)robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            Boolean var2 = (Boolean)robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            Boolean var3;
            if((var1 == true )&& (var2 == true)){
                var3 = true;
            }else{
                var3 = false;
            }
            robot.forthValues.push(var3);
            //pushes a value onto the robot forth values stack
        });
        
        //This will deal with "or", pushing a boolean on the stack
        ht.put("or", (Execute) () -> {
            Boolean var1 = (Boolean)robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            Boolean var2 = (Boolean)robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            Boolean var3;
            if((var1 == false )&& (var2 == false)){
                var3 = false;
            }else{
                var3 = true;
            }
            robot.forthValues.push(var3);
            //pushes a value onto the robot forth values stack
        });
        
        //This will deal with "invert", pushing a new boolean opposite of the
        //one currently on the stack
        ht.put("invert", (Execute) () -> {
            Boolean var1 = (Boolean) robot.forthValues.pop();
            //pops a value off the robot forthvalues stack
            if(var1 == true ){
                var1 = false;
            }else{
                var1 = true;
            }
            robot.forthValues.push(var1);
            //pushes a value onto the robot forth values stack
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
                        //pushes strings onto the ifCommands stack until an else
                        //or if is reached
                    }
                    
                    if(forthCommands.peek().toString().compareTo("if")==0){
                            ifCommands.push(forthCommands.pop());
                            //pops the next string if an if onto ifCommands
                        }
                    while(!ifCommands.empty()){
                        forthCommands.push(ifCommands.pop());
                        //reverses the stack of commands to the correct order
                        
                    }
                   
                    
                    while((forthCommands.peek().toString().compareTo("loop")!=0)&&(!forthCommands.isEmpty())&&(forthCommands.peek().toString().compareTo("else")!=0)){
                        
                        if(interpreter.isInteger((String)forthCommands.peek())){
                            robot.forthValues.push(Integer.parseInt((String)forthCommands.pop()));
                            //check if the string is an integer and pops it onto the robot 
                            //stack
                        }else if(interpreter.isBoolean((String)forthCommands.peek())){
                            robot.forthValues.push(Boolean.parseBoolean((String)forthCommands.pop()));
                            //check if the string is a boolean and pops it onto the robot 
                            //stack
                        }else if(forthCommands.peek().toString().compareTo(".\"")==0){
                            //check if the string is the beginning of a comment and executes 
                            //the comment dictionary function
                            forthCommands.pop();
                            this.getHashMap().get("string").execute();
                        }else if(robot.variables.containsKey(forthCommands.peek())){
                            //checks if the string is an already created variable in the 
                            //variable dictionary then pushes it onto the variable stack
                            robot.variableStack.push(forthCommands.pop());
                            
                        }else{
                            this.getHashMap().get(forthCommands.pop()).execute();
                            //checks the translator hashmap for the string and executes it.
                        }
                        }
                    while((forthCommands.peek().toString().compareTo("then")!=0)&&(forthCommands.peek().toString().compareTo("if")!=0)){
                        //pops the values of the else statement off the stack
                        forthCommands.pop();
                        
                    }
                    if(forthCommands.peek().toString().compareTo("then")==0){
                    forthCommands.pop(); //pops then off the stack
                    }
                
                
            }else{
                
                    int numIf = 0;
                    while((forthCommands.peek().toString().compareTo("else") != 0)||(numIf!=0)){
                        //pops the non-else statement off the stack
                        if(forthCommands.peek().toString().compareTo("if")==0){numIf=numIf+1;}
                        if(forthCommands.peek().toString().compareTo("else")==0){numIf=numIf-1;}
                        //checks to make sure that the correct else statemetn is
                        //being accessed
                        forthCommands.pop();
                    }
                    forthCommands.pop();//pops the else off the stack
                    while((forthCommands.peek().toString().compareTo("then") != 0)&&(forthCommands.peek().toString().compareTo("loop") != 0)){
                        ifCommands.push(forthCommands.pop());
                        //pops the else statment onto the ifCommands stack
                    }
                    if(forthCommands.peek().toString().compareTo("loop")==0){
                        ifCommands.push(forthCommands.pop());
                        //pops the loop string onto the stack 
                    }
                    if(forthCommands.peek().toString().compareTo("if")==0){
                            ifCommands.push(forthCommands.pop());
                            //pops the if string onto the stack
                        }
                    System.out.println(forthCommands.peek());
                    while(!ifCommands.empty()){
                        forthCommands.push(ifCommands.pop());
                        //makes the stack into the correct order
                    }
                    
                    
                    while((!forthCommands.empty())&&(forthCommands.peek().toString().compareTo("then")!=0)&&(forthCommands.peek().toString().compareTo("loop")!=0)){
                        
                        
                        if(interpreter.isInteger((String)forthCommands.peek())){
                            robot.forthValues.push(Integer.parseInt((String)forthCommands.pop()));
                            //check if the string is an integer and pops it onto the robot 
                            //stack
                        }else if(interpreter.isBoolean((String)forthCommands.peek())){
                            robot.forthValues.push(Boolean.parseBoolean((String)forthCommands.pop()));
                            //check if the string is a boolean and pops it onto the robot 
                            //stack
                        }else if(forthCommands.peek().toString().compareTo(".\"")==0){
                            forthCommands.pop();
                            this.getHashMap().get("string").execute();
                            //check if the string is the beginning of a comment and executes 
                            //the comment dictionary function
                        }else if(robot.variables.containsKey(forthCommands.peek())){
                            robot.variableStack.push(forthCommands.pop());
                            //checks if the string is an already created variable in the 
                            //variable dictionary then pushes it onto the variable stack
                            
                        }else{
                             //checks the translator hashmap for the string and executes it.
                            this.getHashMap().get(forthCommands.pop()).execute();
                        }
                        }
                    while(forthCommands.peek().toString().compareTo("then")!=0){
                        forthCommands.pop();
                        //pops all unused values off thestack until the then
                    }
                    forthCommands.pop(); // pop then off the stack
                 
            }
        });
        
        //executes the do loop function
        ht.put("do", (Execute)  () -> {
            int temp1 = (int)robot.forthValues.pop();
            //pops loop starting value off the stack
            int temp2 = (int)robot.forthValues.pop();
            //pops the loop ending value off the stack
            this.loopMax = temp2;
            this.loopCount = temp1;
             
            Stack backwardsForth = new Stack();
            System.out.println(forthCommands.peek());
            while(forthCommands.peek().toString().compareTo("loop")!=0){
                backwardsForth.push(forthCommands.pop());
                //pops the loop off the stack and onto a new stack
               
            }
            backwardsForth.push(forthCommands.pop()); //pops the loop string
            
            Stack loopForth = new Stack();
            while(!backwardsForth.isEmpty()){
                loopForth.push(backwardsForth.pop());
                //reverses the stack
            }
            Stack temp = new Stack(); 
            for(int i=temp1; i < temp2+1; i++){
                //executes the loop a given number of times
                while(!loopForth.empty()){
                        if(interpreter.isInteger((String)loopForth.peek())){
                            robot.forthValues.push(Integer.parseInt((String)loopForth.peek()));
                            //check if the string is an integer and pops it onto the robot 
                            //stack
                            temp.push(loopForth.pop());
                        }else if(interpreter.isBoolean((String)loopForth.peek())){
                            robot.forthValues.push(Boolean.parseBoolean((String)loopForth.peek()));
                            //check if the string is a boolean and pops it onto the robot 
                            //stack
                            temp.push(loopForth.pop());
                        }else if(loopForth.peek().toString().compareTo(".\"")==0){
                            temp.push(loopForth.pop());
                            //check if the string is the beginning of a comment and executes 
                            //the comment dictionary function
                            this.getHashMap().get("string").execute();
                            
                        }else if(robot.variables.containsKey(loopForth.peek())){
                            robot.variableStack.push(temp.peek());
                            //checks if the string is an already created variable in the 
                            //variable dictionary then pushes it onto the variable stack
                            temp.push(loopForth.pop());
                            
                        }else{
                            this.getHashMap().get((String)loopForth.peek()).execute();
                            //checks the translator hashmap for the string and executes it.
                            temp.push(loopForth.pop());
                        }
            }
                while(!temp.isEmpty()){
                    loopForth.push(temp.pop()); 
                //pops the executed value back onto the loopForth stack to be
                //reused
                }
            }
            
            
        });
        
        ht.put("begin", (Execute)  () -> {
            boolean loopEnd = false;
            Stack backwardsForth = new Stack();
            while(forthCommands.peek().toString().compareTo("until")!=0){
                backwardsForth.push(forthCommands.pop());
                //pops values onto the backwardsforth stack until until is 
                //reached
               
            }
            backwardsForth.push(forthCommands.pop()); //pops the until string
            
            Stack loopForth = new Stack();
            while(!backwardsForth.isEmpty()){
                loopForth.push(backwardsForth.pop());
                //reverses the stack 
            }
            Stack temp = new Stack(); 
            while(loopEnd == false){
                //executes the loop until the condition is met
                while(!loopForth.empty()){
                        if(interpreter.isInteger((String)loopForth.peek())){
                            //check if the string is an integer and pops it onto the robot 
                            //stack
                            robot.forthValues.push(Integer.parseInt((String)loopForth.peek()));
                            temp.push(loopForth.pop());
                        }else if(interpreter.isBoolean((String)loopForth.peek())){
                            robot.forthValues.push(Boolean.parseBoolean((String)loopForth.peek()));
                             //check if the string is a boolean and pops it onto the robot 
                             //stack
                            temp.push(loopForth.pop());
                        }else if(forthCommands.peek().toString().compareTo(".\"")==0){
                            //check if the string is the beginning of a comment and executes 
                            //the comment dictionary function
                            forthCommands.pop();
                            this.getHashMap().get("string").execute();
                           
                        }else if(robot.variables.containsKey(loopForth.peek())){
                            //checks if the string is an already created variable in the 
                            //variable dictionary then pushes it onto the variable stack
                            robot.variableStack.push(temp.peek());
                            temp.push(loopForth.pop());
                        }else{
                            this.getHashMap().get((String)loopForth.peek()).execute();
                            //checks the translator hashmap for the string and executes it.
                            temp.push(loopForth.pop());
                        }
            }
                while(!temp.isEmpty()){
                    loopForth.push(temp.pop());
                    //pops the strings off the temp loop back onto loopForth
                }
            if(robot.forthValues.peek().toString().compareTo("false")==0){
                robot.forthValues.pop();
                loopEnd = true;
                //checks to see if the loop should end
            }
            }
            
            
        });
        
        
        //Creates a function of the preceding name in the dictionary
        ht.put(":", (Execute) () -> {
            String key = (String)forthCommands.pop();
            if(key.compareTo("play")==0){
                 System.out.println("beginning play");
            }else{
            Stack backwardsForth = new Stack();
            while(forthCommands.peek().toString().compareTo(";")!=0){
                backwardsForth.push(forthCommands.pop());
               //pops the functions commands onto a new stack
            }
            backwardsForth.push(forthCommands.pop());//poops ; onto the stack
            
            robot.functions.put(key, backwardsForth);
            //puts the function and its forth into the function hashTable
            
            //creates a new entry into the hashMap dictionary
            ht.put(key, (Execute)  () -> {
                
                if(robot.functions.get(key).isEmpty()){
                    System.out.println("empty");
                }else{
                    Stack backwards = robot.functions.get(key);
                    //gets the forth code stack from the functions hashtable
                    Stack reusedStack = new Stack();
                    
                    while(!backwards.isEmpty()){
                        reusedStack.push(backwards.peek());
                        forthCommands.push(backwards.pop());
                        //reverses the forth command stack
                    }
                 
                while(forthCommands.peek().toString().compareTo(";")!=0){
                    
                        
                        
                        if(interpreter.isInteger((String)forthCommands.peek())){
                            robot.forthValues.push(Integer.parseInt((String)forthCommands.pop()));
                            //check if the string is an integer and pops it onto the robot 
                            //stack
                        }else if(interpreter.isBoolean((String)forthCommands.peek())){
                            //check if the string is a boolean and pops it onto the robot 
                            //stack
                            robot.forthValues.push(Boolean.parseBoolean((String)forthCommands.pop()));
                        }else if(robot.variables.containsKey((String)forthCommands.peek())){
                            //checks if the string is an already created variable in the 
                            //variable dictionary then pushes it onto the variable stack
                            robot.variableStack.push(forthCommands.pop());
                            
                        }else if(forthCommands.peek().toString().compareTo(".\"")==0){
                            //check if the string is the beginning of a comment and executes 
                            //the comment dictionary function
                            forthCommands.pop();
                            this.getHashMap().get("string").execute();
                        
                        }else if((!forthCommands.isEmpty()&&this.getHashMap().containsKey((String)forthCommands.peek()))){
                            {
                                //checks the translator hashmap for the string and executes it.
                                this.getHashMap().get((String)forthCommands.pop()).execute();
                            }
                        }else{
                            System.out.println("the value cannot be executed");
                        }

                    
            }forthCommands.pop(); // pops the ; off the end of the function
            
            //refills the backwards stack so it can be reused if the variable is called again
            while(!reusedStack.isEmpty()){ 
                backwards.push(reusedStack.pop());
                //refills the used stack
            }    
            robot.functions.put(key, backwards);
            //puts the used stack back into the variable hashTable
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
            robot.forthValues.push(robot.variables.get((String)robot.variableStack.peek()));
            System.out.println("the value accessed is " + robot.forthValues.peek() + "from the variable " + robot.variableStack.peek()); 
            
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
                System.out.println("The value being put into the variable is " + robot.forthValues.peek() + "the variable is " + robot.variableStack.peek());
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
            //adds the next value of the stack onto the string
            s = s.concat(" "); //adds a space
            }
            forthCommands.pop();//pops the \"
            robot.forthValues.push(s);
            
        });
        
        //Generates a random number between 0 and the given integer on the robot values stack
        ht.put("random", (Execute)  () -> {
            int temp1 = (int)robot.forthValues.pop();
            Random randomNum = new Random();
            //creates a new random number within the specified range
            int temp2 = randomNum.nextInt(temp1);
            robot.forthValues.push(temp2);
        });
        
        //This will just delete the element at the top of the robotvalues stack
        ht.put("drop", (Execute) () -> {
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
            
        });
        
        //moves the current robot in the correct direction
        ht.put("move", (Execute) () -> {
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
        
        
        //checks the space infront of the robot
        ht.put("check!", new Execute(){public void execute(){
            //checks if the robot is a scout
            if(robot.getType().compareTo("SCOUT")==0){
            ScoutAI temp = (ScoutAI) robot;
            Exception x = null;
            try{
                if(temp.check((int)robot.forthValues.peek())){
                    robot.forthValues.pop();
                    robot.forthValues.push("EMPTY");
                    //pushes empty if the space exists
                }
                else{
                    robot.forthValues.pop();
                    robot.forthValues.push("OUT OF BOUNDS ");
                    //pushes out of bounds if the space is not part of the
                    //game board
                }
            }
            catch(Exception e){
                x = e;
            }
            if( x != null){
                robot.forthValues.pop();
                robot.forthValues.push("OUT OF BOUNDS ");
                //pushes out of bounds if the hex does not exist
            }
            }
            
            if(robot.getType().compareTo("SNIPER")==0){
            SniperAI temp = (SniperAI) robot;
            Exception x = null;
            try{
                if(temp.check((int)robot.forthValues.peek())){
                    robot.forthValues.pop();
                    robot.forthValues.push("EMPTY");
                    //pushes empty if the space exists
                }
                else{
                    robot.forthValues.pop();
                    robot.forthValues.push("OUT OF BOUNDS ");
                    //pushes out of bounds if the space is not part of the
                    //game board
                }
            }
            catch(Exception e){
                x = e;
            }
            
            if( x != null){
                robot.forthValues.pop();
                robot.forthValues.push("OUT OF BOUNDS ");
                //pushes out of bounds if the hex does not exist
            }
            }
            if(robot.getType().compareTo("TANK")==0){
            TankAI temp = (TankAI) robot;
            Exception x = null;
            try{
                if(temp.check((int)robot.forthValues.peek())){
                    robot.forthValues.pop();
                    robot.forthValues.push("EMPTY");
                    //pushes empty if the space exists
                }
                else{
                    robot.forthValues.pop();
                    robot.forthValues.push("OUT OF BOUNDS ");
                    //pushes out of bounds if the space is not part of the
                    //game board
                }
            }
            catch(Exception e){
                x = e;
            }
            if( x != null){
                robot.forthValues.pop();
                robot.forthValues.push("OUT OF BOUNDS ");
                //pushes out of bounds if the hex does not exist
            }
            }
        }});
        
        
        
        //scans the area around the current robot
        ht.put("scan!", (Execute) () -> {
            System.out.println("SCANNING");
            //checks if the robot is a scout
            if(robot.getType().compareTo("SCOUT")==0){
            ScoutAI temp = (ScoutAI) robot;
            robot.forthValues.push(temp.scan());
            //executes the scan function
            }
            //checks if the robot is a sniper
            if(robot.getType().compareTo("SNIPER")==0){
            SniperAI temp = (SniperAI) robot;
            robot.forthValues.push(temp.scan());
            //executes the scan function
            }
            //checks if the robot is a tank
            if(robot.getType().compareTo("TANK")==0){
            TankAI temp = (TankAI) robot;
            robot.forthValues.push(temp.scan());
            //executes the scan function
            }
        });
        
        //returns the important data on each robot scanned
        ht.put("identify!", (Execute) () -> {
            
            Robot scanned = robot;
            
            robot.forthValues.push(scanned.scannedRobotsList.get(0).getHealthLeft());
            //pushes the healthLeft
            
            robot.forthValues.push(scanned.getDirectionOfEnemy(0));
            //pushes the direction
            
            robot.forthValues.push(scanned.getRangeOfEnemy(0));
            //pushes the range
            
            robot.forthValues.push(scanned.scannedRobotsList.get(0).getGang());
            //pushes the gang colour
            
            scanned.scannedRobotsList.remove();
          
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
    /**
     * This function will return a hashMap
     * @return the mashMap of the current word Translator
     */
    public HashMap<String, Execute> getHashMap(){
        return ht;
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

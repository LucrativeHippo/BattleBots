/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forth;

import java.util.Hashtable;
import java.util.Stack;
import model.Robot;
import model.ScoutAI;

/**
 *
 * @author mkp003
 */
public class WordTranslator {
    Hashtable ht;
    Robot robot;
    Stack forthCommands;
    
    public WordTranslator(Robot robotAI, Stack commands){
    
        robot = robotAI;
        forthCommands = commands;
        ht = new Hashtable();
        
        ht.put("play", null);
        
        ht.put("begin", null);
        
        ht.put(("until"), null);
        
        ht.put("(", new Word(){void execute(){
            while((String)forthCommands.peek() != ")"){
                commands.pop();
            }
        commands.pop();
        }});
        
        //This will deal with "+" for addition
        ht.put("+", new Word(){void execute(Stack<Value> S){
           IntValue var1 = (IntValue) S.pop(); 
           IntValue var2 = (IntValue) S.pop();
           IntValue var3 = new IntValue();
           var3.i = var1.i + var2.i;
           S.push(var3);
        }});
        
        //This will deal with "-" for subtraction
        ht.put("-", new Word(){void execute(Stack<Value> S){
           IntValue var1 = (IntValue) S.pop(); 
           IntValue var2 = (IntValue) S.pop();
           IntValue var3 = new IntValue();
           var3.i = var2.i - var1.i;
           S.push(var3);
        }});
        
        //This will deal with "*" for multiplication
        ht.put("*", new Word(){void execute(Stack<Value> S){
           IntValue var1 = (IntValue) S.pop(); 
           IntValue var2 = (IntValue) S.pop();
           IntValue var3 = new IntValue();
           var3.i = var2.i * var1.i;
           S.push(var3);
        }});
        
        //This will deal with "/mod" for division, which will push the quotient
        //and remainder to the stack
        ht.put("/mod", new Word(){void execute(Stack<Value> S){
           IntValue var1 = (IntValue) S.pop(); 
           IntValue var2 = (IntValue) S.pop();
           IntValue var3 = new IntValue();
           IntValue var4 = new IntValue();
           var3.i = var2.i/var1.i;
           var4.i = var2.i%var1.i;
           S.push(var4);
           S.push(var3);
        }});
        
        //This will deal with "<", pushing a boolean on the stack
        ht.put("<", new Word(){void execute(Stack<Value> S){
           IntValue var1 = (IntValue) S.pop();
           IntValue var2 = (IntValue) S.pop();
           BoolValue var3 = new BoolValue();
           if(var1.i > var2.i){
               var3.b = true;
           }else{
               var3.b = false;
           }
           S.push(var3);
           
        }});
        
        //This will deal with "<=", pushing a boolean on the stack
        ht.put("<=", new Word(){void execute(Stack<Value> S){
           IntValue var1 = (IntValue) S.pop();
           IntValue var2 = (IntValue) S.pop();
           BoolValue var3 = new BoolValue();
           if(var1.i >= var2.i){
               var3.b = true;
           }else{
               var3.b = false;
           }
           S.push(var3);
           
        }});
        
        //This will deal with "=", pushing a boolean on the stack
        ht.put("=", new Word(){void execute(Stack<Value> S){
           IntValue var1 = (IntValue) S.pop();
           IntValue var2 = (IntValue) S.pop();
           BoolValue var3 = new BoolValue();
           if(var1.i == var2.i){
               var3.b = true;
           }else{
               var3.b = false;
           }
           S.push(var3);
           
        }});
        
        //This will deal with "<>", pushing a boolean on the stack
        ht.put("<>", new Word(){void execute(Stack<Value> S){
           IntValue var1 = (IntValue) S.pop();
           IntValue var2 = (IntValue) S.pop();
           BoolValue var3 = new BoolValue();
           if(var1.i != var2.i){
               var3.b = true;
           }else{
               var3.b = false;
           }
           S.push(var3);
           
        }});
        
        //This will deal with "=>", pushing a boolean on the stack
        ht.put("=>", new Word(){void execute(Stack<Value> S){
           IntValue var1 = (IntValue) S.pop();
           IntValue var2 = (IntValue) S.pop();
           BoolValue var3 = new BoolValue();
           if(var1.i > var2.i){
               var3.b = false;
           }else{
               var3.b = true;
           }
           S.push(var3);
           
        }});
        
        //This will deal with ">", pushing a boolean on the stack
        ht.put(">", new Word(){void execute(Stack<Value> S){
           IntValue var1 = (IntValue) S.pop();
           IntValue var2 = (IntValue) S.pop();
           BoolValue var3 = new BoolValue();
           if(var1.i >= var2.i){
               var3.b = false;
           }else{
               var3.b = true;
           }
           S.push(var3);
           
        }});
        
        //This will deal with "and", pushing a boolean on the stack
        ht.put("and", new Word(){void execute(Stack<Value> S){
           BoolValue var1 = (BoolValue) S.pop();
           BoolValue var2 = (BoolValue) S.pop();
           BoolValue var3 = new BoolValue();
           if((var1.b == true )&& (var2.b == true)){
               var3.b = true;
           }else{
               var3.b = false;
           }
           S.push(var3);
           
        }});
        
        //This will deal with "or", pushing a boolean on the stack
        ht.put("or", new Word(){void execute(Stack<Value> S){
           BoolValue var1 = (BoolValue) S.pop();
           BoolValue var2 = (BoolValue) S.pop();
           BoolValue var3 = new BoolValue();
           if((var1.b == false )&& (var2.b == false)){
               var3.b = false;
           }else{
               var3.b = true;
           }
           S.push(var3);
           
        }});
        
        //This will deal with "invert", pushing a new boolean opposite of the
        //one currently on the stack
        ht.put("invert", new Word(){void execute(Stack<Value> S){
           BoolValue var1 = (BoolValue) S.pop();
           if(var1.b == true ){
               var1.b = false;
           }else{
               var1.b = true;
           }
           S.push(var1);
           
        }});
        
        //This will deal with "if", parsing through the if code when the top
        //of the stack has a true boolean and then deleting everything that
        //is associated with the else statement.  It will do the opposite if
        //the top of the stack is false
        ht.put("if", new Word(){void execute(Stack<Value> S){
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
               WordTranslator translateIf = new WordTranslator(robot, ifCommands);//Just have to execute somehow
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
               WordTranslator translateIf = new WordTranslator(robot, ifCommands);//Just have to execute somehow
               forthCommands.pop();
           }
        }});
        
        //Don't need these
        //ht.put("else", null);//These are dealt with with the if statement.
        //ht.put("then", null);
        ht.put("loop", null);
        
        ht.put(":", null);
        ht.put(";", null);
        
        ht.put("variable", new Word(){void execute(Stack<Value> S){
        S.pop();
        }});
        
        ht.put("?", null);
        ht.put("!", null);
        ht.put(".", null);
        ht.put("random", null);
        
        //This will just delete the element at the top of the stack
        ht.put("drop", new Word(){void execute(Stack<Value> S){
        S.pop();
        }});
        
        //This will duplicate the value at the top of the stack.
        ht.put("dup", new Word(){void execute(Stack<Value> S){
        Stack temp = new Stack();
        temp.push(robot.forthValues.peek());
        robot.forthValues.push(temp.pop());
        }});
        
        //
        ht.put("swap", null);
        ht.put("rot", null);
        
        
        //Status keys
        ht.put("health", new Word(){void execute(Stack<Value> S){
        robot.forthValues.add(robot.getHealth());
        }});
        
        ht.put("healthLeft", new Word(){void execute(Stack<Value> S){
        robot.forthValues.add(robot.getHealthLeft());
        }});
        
        ht.put("moves", new Word(){void execute(Stack<Value> S){
        robot.forthValues.add(robot.getMovement());
        }});
        
        ht.put("movesLeft", new Word(){void execute(Stack<Value> S){
        robot.forthValues.add(robot.getMovementLeft());
        }});
        
        ht.put("attack", new Word(){void execute(Stack<Value> S){
        robot.forthValues.push(robot.getDamage());
        }});
        
        ht.put("range", new Word(){void execute(Stack<Value> S){
        robot.forthValues.push(robot.getRange());
        }});
        
        ht.put("team", new Word(){void execute(Stack<Value> S){
        robot.forthValues.push(robot.getGang());
        }});
        
        ht.put("type", new Word(){void execute(Stack<Value> S){
        robot.forthValues.push(robot.getType());
        }});
        
        //Action Keys
        ht.put("turn!", new Word(){void execute(Stack<Value> S){
            ScoutAI temp = (ScoutAI) robot;
        temp.turn((int)robot.forthValues.pop());
        }});
        
        ht.put("move!", new Word(){void execute(Stack<Value> S){
            ScoutAI temp = (ScoutAI) robot;
        temp.move();
        }});
        
        
        //Need to fix the robotAI shoot function first
        ht.put("shoot!", null);
        
        
        
        ht.put("check!", new Word(){void execute(Stack<Value> S){
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
        
        
        ht.put("scan!", new Word(){void execute(Stack<Value> S){
            ScoutAI temp = (ScoutAI) robot;
            robot.forthValues.push(temp.scan());
        }});
        
        
        ht.put("identify!", new Word(){void execute(Stack<Value> S){
            int index = (int) robot.forthValues.pop();
            ScoutAI scaned = (ScoutAI)robot;
            robot.forthValues.push(scaned.scannedRobotsList.get(index).getGang());
            robot.forthValues.push(scaned.scannedRobotsList.get(index).getRange());
            //robot.forthValues.push(scaned.scannedRobotsList.get(index).getRelativeDirection());
            robot.forthValues.push(scaned.scannedRobotsList.get(index).getHealthLeft());
        }});
        
        
        ht.put("send!", new Word(){void execute(Stack<Value> S){
            Value send = (Value) robot.forthValues.pop();
            String target = (String) robot.forthValues.pop();
           // Iterator search = new Iterator(robot.board.)
            
        }});
        
        
        ht.put("mesg?", null);
        
        ht.put("recv!", null);
        
}
    
    public Hashtable getHashTable(){
        return ht;
    }
    
    public void addStackValues(){
        
    }
    
    public static void main(String [] args) {
        
        
        
    }
}

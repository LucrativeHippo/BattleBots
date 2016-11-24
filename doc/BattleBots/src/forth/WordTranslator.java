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
        
        ht.put("+", new Word(){void execute(Stack<Value> S){
           IntValue var1 = (IntValue) S.pop(); 
           IntValue var2 = (IntValue) S.pop();
           IntValue var3 = new IntValue();
           var3.i = var1.i + var2.i;
           S.push(var3);
        }});
        ht.put("-", new Word(){void execute(Stack<Value> S){
           IntValue var1 = (IntValue) S.pop(); 
           IntValue var2 = (IntValue) S.pop();
           IntValue var3 = new IntValue();
           var3.i = var2.i - var1.i;
           S.push(var3);
        }});
        ht.put("*", new Word(){void execute(Stack<Value> S){
           IntValue var1 = (IntValue) S.pop(); 
           IntValue var2 = (IntValue) S.pop();
           IntValue var3 = new IntValue();
           var3.i = var2.i * var1.i;
           S.push(var3);
        }});
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
        ht.put("invert", new Word(){void execute(Stack<Value> S){
           BoolValue var1 = (BoolValue) S.pop();
           if(var1.b == true ){
               var1.b = false;
           }else{
               var1.b = true;
           }
           S.push(var1);
           
        }});
        
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
        
        ht.put("drop", new Word(){void execute(Stack<Value> S){
        S.pop();
        }});
        
        ht.put("dup", null);
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
        
        
        ht.put("send!", null);
        
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

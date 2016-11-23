/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forth;

import java.util.Hashtable;
import java.util.Stack;

/**
 *
 * @author mkp003
 */
public class WordTranslator {
    Hashtable ht;
    
    public WordTranslator(){
    
        ht = new Hashtable();
        
        ht.put("play", null);
        ht.put("begin", null);
        
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
           BoolValue var1 = (BoolValue) S.pop();
           if(var1.b == true ){
               
            //Add strings to a stringValue until else is reached
           }else{
               
           }
           
           
        }});
        ht.put("else", null);
        ht.put("then", null);
        ht.put("loop", null);
        
        ht.put(":", null);
        ht.put(";", null);
        
        ht.put("variable", null);
        ht.put("?", null);
        ht.put("!", null);
        ht.put(".", null);
        ht.put("random", null);
        
        ht.put("drop", null);
        ht.put("dup", null);
        ht.put("swap", null);
        ht.put("rot", null);
}
    
    public Hashtable getHashTable(){
        return ht;
    }
    
    public void addStackValues(){
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forth;

import java.util.Hashtable;

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
        
        ht.put("+", null);
        ht.put("-", null);
        ht.put("*", null);
        ht.put("/mod", null);
        
        ht.put("<", null);
        ht.put("<=", null);
        ht.put("=", null);
        ht.put("<>", null);
        ht.put("=>", null);
        ht.put(">", null);
        
        ht.put("and", null);
        ht.put("or", null);
        ht.put("invert", null);
        
        ht.put("if", null);
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

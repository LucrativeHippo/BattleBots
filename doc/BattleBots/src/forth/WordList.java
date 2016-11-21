/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forth;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author wjr992
 */
public class WordList extends Word{
    LinkedList<String> ListWord;
    
    void executeList(Stack s){
        for(String s: words){
            if(integer.isInteger()){
                
            }else
            {
             words.get(s).execute(rs);       
                    }
            
        }
        
        
    }
    
}

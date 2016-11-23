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
    
    
    @Override
    void execute(Stack rs){
        for(String s: ListWord){
           if(isInteger(s)){
                rs.push(s);
          }else
          {
           ListWord.get(ListWord.indexOf(s)).execute(rs);       
          }
            
        }
        
        
    }
    
    boolean isInteger(String s){
        int size = s.length();

    for (int i = 0; i < size; i++) {
        if (!Character.isDigit(s.charAt(i))) {
            return false;
        }
    }

    return size > 0;
    
    }
    
    
    
}

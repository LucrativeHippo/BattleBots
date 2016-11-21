/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forth;
import java.util.Stack;


/**
 *
 * @author wjr992
 */
public class WordInteger extends Word{
    int intWord;
    void executeInt(Stack<IntValue> S){
        S.push(IntValue intWord);
    }
    
}

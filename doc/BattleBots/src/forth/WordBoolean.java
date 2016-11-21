/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forth;
import java.util.Stack;
import forth.BoolValue;

/**
 *
 * @author wjr992
 */
public class WordBoolean extends Word{
    boolean boolWord;
    BoolValue boolValue = new BoolValue(boolWord);
    void executeBool(Stack<BoolValue> S){
        S.push(boolValue);
    }
}

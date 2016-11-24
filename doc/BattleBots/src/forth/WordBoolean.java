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
    @Override
    void execute(Stack S){
        BoolValue boolValue = new BoolValue();
        boolValue.b = boolWord;
        S.push(boolValue);
    }
}

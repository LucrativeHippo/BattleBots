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
public class WordString extends Word{
    String stringWord;
    @Override
    void execute(Stack S){
        StringValue stringValue = new StringValue();
        stringValue.s = stringWord;
        S.push(stringValue);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forth;
import java.util.Stack;
import model.Robot;


/**
 *
 * @author wjr992
 */
public class WordInteger extends Word{
    int intWord;
    Robot robot;
    
    void execute(){
        IntValue intValue = new IntValue();
        intValue.i = intWord;
        robot.forthValues.push(intValue);
    }
    
}

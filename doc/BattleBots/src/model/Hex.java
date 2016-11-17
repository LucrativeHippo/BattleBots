package model;

import java.util.LinkedList;

/**
 * 
 * @author mkp003
 */
public class Hex {
	public LinkedList<Robot> robotList;
        public boolean hexExists;
	
	public Hex(){
		robotList = new LinkedList();
                hexExists = true;
	}
	
        /**
         * This function will return a boolean value of true or false based 
         * on whether or not the current Hex space is empty of any robots or not
         * 
         * @return True if empty, false otherwise
         */
	public boolean isEmpty(){
		if(robotList == null){
			return true;
		}
		else{
			return false;
		}
	}
}

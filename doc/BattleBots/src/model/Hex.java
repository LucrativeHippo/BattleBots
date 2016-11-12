package model;

import java.util.LinkedList;

public class Hex {
	public LinkedList<Robot> robotList;
	
	public Hex(){
		robotList = null;
	}
	
	public boolean isEmpty(){
		if(robotList == null){
			return true;
		}
		else{
			return false;
		}
	}
}

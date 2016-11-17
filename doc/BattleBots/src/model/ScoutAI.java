package model;

import java.util.Iterator;
import java.util.LinkedList;
import org.json.simple.JSONObject;

public class ScoutAI extends Scout {
	
	private JSONObject instructionCode;
        private int totalMoves;
        private int robotsKilled;
        private int damageDealt;
        private int damageTaken;
        public LinkedList<Robot> scannedRobotsList;
	
	public ScoutAI(String name) {
            super(name);
            this.setMovement(3);
            this.setDamage(1);
            this.setRange(2);
            this.setHealth(1);
            
            this.setShotsLeft(1);
            this.setMovementLeft(3);
            this.setHealthLeft(1);
            
            //this.totalMoves = 
            
	}
	//boolean?

        public void move(){
            if(this.getMovementLeft()>0){
            if(this.getRelativeDirection()==0){
                if(this.board.spaces[this.getHorizontalLocation()+1][this.getVerticalLocation()]!=null){
                    this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].robotList.remove(this);
                    this.board.spaces[this.getHorizontalLocation()+1][this.getVerticalLocation()].robotList.add(this);
                    this.setHorizontalLocation(this.getHorizontalLocation()+1);
                    this.setVerticalLocation(this.getVerticalLocation());
                    this.setMovementLeft(this.getMovementLeft()-1);
                }
            }
            if(this.getRelativeDirection()==1){
                if(this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()+1]!=null){
                    this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].robotList.remove(this);
                    this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()+1].robotList.add(this);
                    this.setHorizontalLocation(this.getHorizontalLocation());
                    this.setVerticalLocation(this.getVerticalLocation()+1);
                    this.setMovementLeft(this.getMovementLeft()-1);
                }
            }
            if(this.getRelativeDirection()==2){
                if(this.board.spaces[this.getHorizontalLocation()-1][this.getVerticalLocation()+1]!=null){
                    this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].robotList.remove(this);
                    this.board.spaces[this.getHorizontalLocation()-1][this.getVerticalLocation()+1].robotList.add(this);
                    this.setHorizontalLocation(this.getHorizontalLocation()-1);
                    this.setVerticalLocation(this.getVerticalLocation()+1);
                    this.setMovementLeft(this.getMovementLeft()-1);
                }
            }
            if(this.getRelativeDirection()==3){
                if(this.board.spaces[this.getHorizontalLocation()-1][this.getVerticalLocation()]!=null){
                    this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].robotList.remove(this);
                    this.board.spaces[this.getHorizontalLocation()-1][this.getVerticalLocation()].robotList.add(this);
                    this.setHorizontalLocation(this.getHorizontalLocation()-1);
                    this.setVerticalLocation(this.getVerticalLocation());
                    this.setMovementLeft(this.getMovementLeft()-1);
                }
            }
            if(this.getRelativeDirection()==4){
                if(this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()-1]!=null){
                    this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].robotList.remove(this);
                    this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()-1].robotList.add(this);
                    this.setHorizontalLocation(this.getHorizontalLocation());
                    this.setVerticalLocation(this.getVerticalLocation()-1);
                    this.setMovementLeft(this.getMovementLeft()-1);
                }
            }
            if(this.getRelativeDirection()==5){
                if(this.board.spaces[this.getHorizontalLocation()+1][this.getVerticalLocation()-1]!=null){
                    this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].robotList.remove(this);
                    this.board.spaces[this.getHorizontalLocation()+1][this.getVerticalLocation()-1].robotList.add(this);
                    this.setHorizontalLocation(this.getHorizontalLocation()+1);
                    this.setVerticalLocation(this.getVerticalLocation()-1);
                    this.setMovementLeft(this.getMovementLeft()-1);
                }
            }
            }
        }
        
        
	public void scan(){
            int minRange = this.getRange()*-1;
            int hexModifier = 0;
            int scanRange = this.getRange();
            for(int i=minRange; i < this.getRange(); i++){
                for(int k=hexModifier; k < scanRange; k++){
                    if(this.board.spaces[this.getHorizontalLocation()+k][this.getVerticalLocation()+i].hexExists==true&&this.board.spaces[this.getHorizontalLocation()+k][this.getVerticalLocation()+i].isEmpty()==false){
                    Iterator<Robot> robotIterator = this.board.spaces[this.getHorizontalLocation()+k][this.getVerticalLocation()+i].robotList.iterator();
                        while(robotIterator.hasNext()){
                            Robot temp = robotIterator.next();
                            this.scannedRobotsList.add(temp);
                }
                    
                }
                if(hexModifier == minRange)
                {
                    scanRange--;
                }
                else{
                    hexModifier--;
                }
            }
        }
        }
	
	public void turn(int desiredDirection){
		if((desiredDirection<=5)&&(desiredDirection>=0)){
                    this.setRelativeDirection((this.getRelativeDirection()+desiredDirection)%6);
                }
	}

}

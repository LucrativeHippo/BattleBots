package model;

import static java.lang.Math.abs;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.simple.JSONObject;

public class TankAI extends Tank{

	private JSONObject instructionCode;
        private int totalMoves;
        private int robotsKilled;
        private int damageDealt;
        private int damageTaken;
        public LinkedList<Robot> scannedRobotsList;
	
	public TankAI(String name) {
            super(name);
            this.setMovement(1);
            this.setDamage(3);
            this.setRange(1);
            this.setHealth(3);
            
            this.setShotsLeft(1);
            this.setMovementLeft(1);
            this.setHealthLeft(3);
            scannedRobotsList = new LinkedList();
            
	}
	
	public void move(){
            if(this.getMovementLeft()>0){
            if(this.getRelativeDirection()==0){
                if(this.board.spaces[this.getHorizontalLocation()+1][this.getVerticalLocation()]!=null){
                    this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].robotList.remove(this);
                    this.board.spaces[this.getHorizontalLocation()+1][this.getVerticalLocation()].robotList.add(this);
                    this.setHorizontalLocation(this.getHorizontalLocation()+1);
                    this.setVerticalLocation(this.getVerticalLocation());
                    this.setMovementLeft(this.getMovementLeft()-1);
                    totalMoves = totalMoves +1;
                }
            }
            if(this.getRelativeDirection()==1){
                if(this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()+1]!=null){
                    this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].robotList.remove(this);
                    this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()+1].robotList.add(this);
                    this.setHorizontalLocation(this.getHorizontalLocation());
                    this.setVerticalLocation(this.getVerticalLocation()+1);
                    this.setMovementLeft(this.getMovementLeft()-1);
                    totalMoves = totalMoves +1;
                }
            }
            if(this.getRelativeDirection()==2){
                if(this.board.spaces[this.getHorizontalLocation()-1][this.getVerticalLocation()+1]!=null){
                    this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].robotList.remove(this);
                    this.board.spaces[this.getHorizontalLocation()-1][this.getVerticalLocation()+1].robotList.add(this);
                    this.setHorizontalLocation(this.getHorizontalLocation()-1);
                    this.setVerticalLocation(this.getVerticalLocation()+1);
                    this.setMovementLeft(this.getMovementLeft()-1);
                    totalMoves = totalMoves +1;
                }
            }
            if(this.getRelativeDirection()==3){
                if(this.board.spaces[this.getHorizontalLocation()-1][this.getVerticalLocation()]!=null){
                    this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].robotList.remove(this);
                    this.board.spaces[this.getHorizontalLocation()-1][this.getVerticalLocation()].robotList.add(this);
                    this.setHorizontalLocation(this.getHorizontalLocation()-1);
                    this.setVerticalLocation(this.getVerticalLocation());
                    this.setMovementLeft(this.getMovementLeft()-1);
                    totalMoves = totalMoves +1;
                }
            }
            if(this.getRelativeDirection()==4){
                if(this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()-1]!=null){
                    this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].robotList.remove(this);
                    this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()-1].robotList.add(this);
                    this.setHorizontalLocation(this.getHorizontalLocation());
                    this.setVerticalLocation(this.getVerticalLocation()-1);
                    this.setMovementLeft(this.getMovementLeft()-1);
                    totalMoves = totalMoves +1;
                }
            }
            if(this.getRelativeDirection()==5){
                if(this.board.spaces[this.getHorizontalLocation()+1][this.getVerticalLocation()-1]!=null){
                    this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].robotList.remove(this);
                    this.board.spaces[this.getHorizontalLocation()+1][this.getVerticalLocation()-1].robotList.add(this);
                    this.setHorizontalLocation(this.getHorizontalLocation()+1);
                    this.setVerticalLocation(this.getVerticalLocation()-1);
                    this.setMovementLeft(this.getMovementLeft()-1);
                    totalMoves = totalMoves +1;
                }
            }
            }
        }
        
	public int scan(){
            Exception x= null;
            int numRobots = 0;
            int minRange = this.getRange()*-1;
            int hexModifier = 0;
            int scanRange = this.getRange();
            for(int i=minRange; i <= this.getRange(); i++){
                for(int k=hexModifier; k <= scanRange; k++){
                    x=null;
                    try {
			Hex temp = this.board.spaces[this.getHorizontalLocation()+k][this.getVerticalLocation()+i];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e + " caught due to scanning out of bounds.");
                        x=e;
                          
		}
                    if(x==null){
                    System.out.println(this.getHorizontalLocation()+k);
                    System.out.println(this.getVerticalLocation()+i);
                    if(this.board.spaces[this.getHorizontalLocation()+k][this.getVerticalLocation()+i].hexExists==true&&this.board.spaces[this.getHorizontalLocation()+k][this.getVerticalLocation()+i].isEmpty()==false){
                        System.out.println("hex check successful");
                        Iterator<Robot> robotIterator = this.board.spaces[this.getHorizontalLocation()+k][this.getVerticalLocation()+i].robotList.iterator();
                        while(robotIterator.hasNext()){
                            numRobots = numRobots + 1;
                            Robot temp = robotIterator.next();
                            System.out.println(temp.getName());
                            this.scannedRobotsList.add(temp);
                        }
                    }
                   
                    }
                
            }
                if(hexModifier == minRange)
                {
                    scanRange--;
                }
                else{
                    hexModifier=hexModifier-1;
                } 
        }
            return numRobots;
        }
	
	public void turn(int desiredDirection){
		if((desiredDirection<=5)&&(desiredDirection>=0)){
                    this.setRelativeDirection((this.getRelativeDirection()+desiredDirection)%6);
                }
	}
        
        public int turnsRequired(int directionWanted){
            int turnsRequired = abs(this.getRelativeDirection() - directionWanted);
            
            return turnsRequired;
        }
        
        public Robot Identify(Robot robot){
            Robot robot1 = robot;
            return robot;
        }
        /**
         * This function inflicts damage to the robot by the given amount
         * @param damage Integer of damage inflicted
         */
	public void recieveDamage(int damage){
		if(this.getHealthLeft() <= damage){
                    this.setHealthLeft(0);
                }
                else{
                    this.setHealthLeft(this.getHealthLeft() - damage);
                    this.damageTaken = this.damageTaken + damage;
                }
	}
        
        /**
         * This function will take in a Hex space and deal damage to any robots
         * on that space by the amount of damage that the current robot is able
         * to do
         * @param space Hex space to be shot at
         */
        public void shoot(Hex space){
		if(space == null){
                    System.out.println("The space is out of range");
                    return;
                }
                else if(space.isEmpty() == true){
                    return;
                }
                else{
                    Iterator<Robot> robotIterator = space.robotList.iterator();
                        while(robotIterator.hasNext()){
                            Robot temp = robotIterator.next();
                            temp.recieveDamage(this.getDamage());
                            this.damageDealt = this.damageDealt + this.getDamage();
                            if(this.getDamage() >= temp.getHealthLeft()){
                                this.robotsKilled = this.robotsKilled+1;
                            }
                        }
                }
	}
}

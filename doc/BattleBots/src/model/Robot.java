package model;

import java.util.Iterator;
import java.util.Stack;

public class Robot {

	private int movement;
	private int damage;
	private int range;
	private int health;
	
	private int shotsLeft;
	private int movementLeft;
	private int healthLeft;
        
        public Stack forthValues;
	
	private int relativeDirection;
	private int directionDimension;
	private int horizontalLocation;
	private int verticalLocation;
	private int previousX;
        private int previousY; 
        
	private boolean isTurn;
	
	private String gang;
	private String name;
        private String type;
        private boolean isHuman;
	
	public GameBoard board;
	

	public Robot(String name) {
		super();
		this.name = name;
                this.isHuman = true;
	}
        
        public void setPreviousX(int previousX) {
            this.previousX = previousX;
        }

        public void setPreviousY(int previousY) {
            this.previousY = previousY;
        }

        public int getPreviousX() {
            return previousX;
        }

        public int getPreviousY() {
            return previousY;
        }

        /**
         * This function returns the distance a specific robot can travel
         * @return Integer of moves the robot can make. 
         */
	public int getMovement() {
		return movement;
	}
	
        /**
         * This function sets the moves a robot can make
         * @param movement Total moves a robot can make each turn
         */
	public void setMovement(int movement) {
		this.movement = movement;
	}
	
        /**
         * This function retrieves the total damage a robot can do
         * @return Integer of damage that can be dealt
         */
	public int getDamage() {
		return damage;
	}
	
        /**
         * This function sets the damage that a robot can do
         * @param damage Integer between 1 and 3 of damage a robot can do.
         */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
        /**
         * This function retrieves the range to which a robot can see and shoot
         * @return Integer of robot's range
         */
	public int getRange() {
		return range;
	}
	
        /**
         * This function sets the range to which a robot can see and shoot
         * @param range Integer of the robot's range
         */
	public void setRange(int range) {
		this.range = range;
	}
	
        /**
         * This function retrieves the max health that a robot starts with
         * @return Integer of the robot's starting health
         */
	public int getHealth() {
		return health;
	}
	
        /**
         * This function sets the starting health of a robot
         * @param health Integer of the health we want the robot to start with
         */
	public void setHealth(int health) {
		this.health = health;
	}
	
        /**
         * This function will retrieve how many shots a robot has left
         * @return Integer of shots left
         */
	public int getShotsLeft() {
		return shotsLeft;
	}
	
        /**
         * This function will set how many shots a robot has left to make
         * @param shotsLeft Integer of the number of shots we want to set
         */
	public void setShotsLeft(int shotsLeft) {
		this.shotsLeft = shotsLeft;
	}
	
        /**
         * This function will retrieve the number of moves that the robot has 
         * left
         * @return Integer of the number of moves left
         */
	public int getMovementLeft() {
		return movementLeft;
	}
	
        /**
         * This function will set the number of moves a robot has left
         * @param movementLeft Integer of the number of moves left
         */
	public void setMovementLeft(int movementLeft) {
		this.movementLeft = movementLeft;
	}
	
        /**
         * This function will retrieve the health value that the robot has left
         * @return Integer value of health left
         */
	public int getHealthLeft() {
		return healthLeft;
	}
	
        /**
         * This function will set how much health a robot has left
         * @param healthLeft 
         */
	public void setHealthLeft(int healthLeft) {
		this.healthLeft = healthLeft;
	}
	
        /**
         * *This function retrieves the relative direction that the robot is 
         * facing relative to the board
         * @return Integer value of 1 to 6 of the robots direction
         */
	public int getRelativeDirection() {
		return relativeDirection;
	}
	
        /**
         * This function will set the robot's relative direction it is facing
         * on the board
         * @param relativeDirection Integer from 1 to 6
         */
	public void setRelativeDirection(int relativeDirection) {
		this.relativeDirection = relativeDirection;
	}
	
        /**
         * This function will acquire the range dimension that the robot is
         * searching at
         * @return Integer for the range dimension (1 through 3)
         */
	public int getDirectionDimension() {
		return directionDimension;
	}
	
        /**
         * This function will set the range dimension that the robot is
         * searching at
         * @param directionDimension Integer for the range dimension (1 to 3)
         */
	public void setDirectionDimension(int directionDimension) {
		this.directionDimension = directionDimension;
	}
	
        /**
         * This function will return the horizontal x coordinate position of 
         * the robot on the gameBoard
         * @return Integer of x coordinate
         */
	public int getHorizontalLocation() {
		return horizontalLocation;
	}
	
        /**
         * This function will set the horizontal x coordinate position of 
         * the robot on the gameBoard
         * @param horizontalLocation Integer of x position to set
         */
	public void setHorizontalLocation(int horizontalLocation) {
		this.horizontalLocation = horizontalLocation;
	}
	
        /**
         * This function will get the vertical y coordinate position of the 
         * robot on the gameBoard
         * @return Integer of y coordinate
         */
	public int getVerticalLocation() {
		return verticalLocation;
	}
	
        /**
         * This function will get the vertical y coordinate position of the 
         * robot on the gameBoard
         * @param verticalLocation  Integer of y position to set
         */
	public void setVerticalLocation(int verticalLocation) {
		this.verticalLocation = verticalLocation;
	}
	
        /**
         * This function returns the value of isTurn to determine if it is a 
         * robot's turn
         * @return True if it is it's turn, false otherwise
         */
	public boolean isTurn() {
		return isTurn;
	}
	
        /**
         * This function sets isTurn if we want to change the robots current
         * turn status
         * @param isTurn True or False
         */
	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}
	
        /**
         * This function returns the string of the robot's current gang color
         * @return String of "Red", "Blue", "Orange", "Yellow", "Green, "Purple"
         */
	public String getGang() {
		return gang;
	}
	
        /**
         * This function sets the gang of the robot's current gang color
         * @param gang "Red", "Blue", "Orange", "Yellow", "Green, "Purple"
         */
	public void setGang(String gang) {
		this.gang = gang;
	}
	
        /**
         * This function returns a string of the robot's name
         * @return String name
         */
	public String getName() {
		return name;
	}
	
        /**
         * This function sets the robot's name
         * @param name String to set name to
         */
	public void setName(String name) {
		this.name = name;
	}	
	
        /**
         * This function inflicts damage to the robot by the given amount
         * @param damage Integer of damage inflicted
         */
	public void recieveDamage(int damage){
		if(this.healthLeft <= damage){
                    this.healthLeft = 0;
                }
                else{
                    this.healthLeft = this.healthLeft - damage;
                }
	}

        /**
         * This function returns a string of the robot's type
         * @return String of "SCOUT", "SNIPER", or "TANK"
         */
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
                            if(temp.isHuman == false){
                              //record damage dealt for AI and damage taken
                              //
                              //
                            }
                        }
                }
	}
	
	public void move(char relativeDirection ){
            if(this.getMovementLeft()>0){
                if(relativeDirection == 'd'){
                    if(this.board.spaces[this.getHorizontalLocation()+1][this.getVerticalLocation()]!=null){
                        this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].robotList.remove(this);
                        this.board.spaces[this.getHorizontalLocation()+1][this.getVerticalLocation()].robotList.add(this);
                        this.setHorizontalLocation(this.getHorizontalLocation()+1);
                        this.setVerticalLocation(this.getVerticalLocation());
                        this.setMovementLeft(this.getMovementLeft()-1);
                    }
                }
                if(relativeDirection == 'x'){
                    if(this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()+1]!=null){
                        this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].robotList.remove(this);
                        this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()+1].robotList.add(this);
                        this.setHorizontalLocation(this.getHorizontalLocation());
                        this.setVerticalLocation(this.getVerticalLocation()+1);
                        this.setMovementLeft(this.getMovementLeft()-1);
                    }
                }
                if(relativeDirection == 'z'){
                    if(this.board.spaces[this.getHorizontalLocation()-1][this.getVerticalLocation()+1]!=null){
                        this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].robotList.remove(this);
                        this.board.spaces[this.getHorizontalLocation()-1][this.getVerticalLocation()+1].robotList.add(this);
                        this.setHorizontalLocation(this.getHorizontalLocation()-1);
                        this.setVerticalLocation(this.getVerticalLocation()+1);
                        this.setMovementLeft(this.getMovementLeft()-1);
                    }
                }
                if(relativeDirection == 'a'){
                    if(this.board.spaces[this.getHorizontalLocation()-1][this.getVerticalLocation()]!=null){
                        this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].robotList.remove(this);
                        this.board.spaces[this.getHorizontalLocation()-1][this.getVerticalLocation()].robotList.add(this);
                        this.setHorizontalLocation(this.getHorizontalLocation()-1);
                        this.setVerticalLocation(this.getVerticalLocation());
                        this.setMovementLeft(this.getMovementLeft()-1);
                    }
                }
                if(relativeDirection == 'w'){
                    if(this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()-1]!=null){
                        this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].robotList.remove(this);
                        this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()-1].robotList.add(this);
                        this.setHorizontalLocation(this.getHorizontalLocation());
                        this.setVerticalLocation(this.getVerticalLocation()-1);
                        this.setMovementLeft(this.getMovementLeft()-1);
                    }
                }
                if(relativeDirection == 'e'){
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
}

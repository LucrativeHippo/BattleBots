package model;

public class Robot {

	private int movement;
	private int damage;
	private int range;
	private int health;
	
	private int shotsLeft;
	private int movementLeft;
	private int healthLeft;
	
	private int relativeDirection;
	private int directionDimension;
	private int horizontalLocation;
	private int verticalLocation;
	
	private boolean isTurn;
	
	private String gang;
	private String name;
	
	private GameBoard board;
	

	public Robot(String gang, String name) {
		super();
		this.gang = gang;
		this.name = name;
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
         * 
         * @return 
         */
	public int getShotsLeft() {
		return shotsLeft;
	}
	
	public void setShotsLeft(int shotsLeft) {
		this.shotsLeft = shotsLeft;
	}
	
	public int getMovementLeft() {
		return movementLeft;
	}
	
	public void setMovementLeft(int movementLeft) {
		this.movementLeft = movementLeft;
	}
	
	public int getHealthLeft() {
		return healthLeft;
	}
	
	public void setHealthLeft(int healthLeft) {
		this.healthLeft = healthLeft;
	}
	
	public int getRelativeDirection() {
		return relativeDirection;
	}
	
	public void setRelativeDirection(int relativeDirection) {
		this.relativeDirection = relativeDirection;
	}
	
	public int getDirectionDimension() {
		return directionDimension;
	}
	
	public void setDirectionDimension(int directionDimension) {
		this.directionDimension = directionDimension;
	}
	
	public int getHorizontalLocation() {
		return horizontalLocation;
	}
	
	public void setHorizontalLocation(int horizontalLocation) {
		this.horizontalLocation = horizontalLocation;
	}
	
	public int getVerticalLocation() {
		return verticalLocation;
	}
	
	public void setVerticalLocation(int verticalLocation) {
		this.verticalLocation = verticalLocation;
	}
	
	public boolean isTurn() {
		return isTurn;
	}
	
	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}
	
	public String getGang() {
		return gang;
	}
	
	public void setGang(String gang) {
		this.gang = gang;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}	
	
	public void recieveDamage(int damage){
		if(this.healthLeft <= damage){
                    this.healthLeft = 0;
                }
                else{
                    this.healthLeft = this.healthLeft - damage;
                }
	}
        
        public void shoot(){
		
	}
	
	public void move(){
		
	}
}

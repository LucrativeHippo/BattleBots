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

	public int getMovement() {
		return movement;
	}
	
	public void setMovement(int movement) {
		this.movement = movement;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public int getRange() {
		return range;
	}
	
	public void setRange(int range) {
		this.range = range;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
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
		
	}
}

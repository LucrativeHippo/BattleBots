package model;

public class Tank extends Robot{

	public Tank(String name) {
    super(name);
    this.setMovement(1);
    this.setDamage(3);
    this.setRange(1);
    this.setHealth(3);
    this.setType("TANK");
    this.isHuman = true;    
    this.setShotsLeft(1);
    this.setMovementLeft(1);
    this.setHealthLeft(3);
  }
}

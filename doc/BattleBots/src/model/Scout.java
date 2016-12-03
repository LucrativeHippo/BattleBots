package model;

public class Scout extends Robot {

	public Scout(String name) {
    super(name);
    this.setMovement(3);
    this.setDamage(1);
    this.setRange(2);
    this.setHealth(1);
    this.setType("SCOUT");
    this.isHuman = true;
            
    this.setShotsLeft(1);
    this.setMovementLeft(3);
    this.setHealthLeft(1);
  }
}

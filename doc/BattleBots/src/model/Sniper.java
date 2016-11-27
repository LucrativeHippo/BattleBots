package model;

public class Sniper extends Robot{

	public Sniper(String name) {
            super(name);
            this.setMovement(2);
            this.setDamage(2);
            this.setRange(3);
            this.setHealth(2);
            this.setType("SNIPER");
            this.isHuman = true;
            
            this.setShotsLeft(1);
            this.setMovementLeft(2);
            this.setHealthLeft(2);
            
	}

}

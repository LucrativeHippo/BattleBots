package model;

public class Sniper extends Robot{

	public Sniper(String gang, String name) {
            super(gang, name);
            this.setMovement(2);
            this.setDamage(2);
            this.setRange(3);
            this.setHealth(2);
            
            this.setShotsLeft(1);
            this.setMovementLeft(2);
            this.setHealthLeft(2);
            
	}

}

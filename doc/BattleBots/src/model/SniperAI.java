package model;

public class SniperAI extends Sniper{

	private String instructionCode;
	
	public SniperAI(String gang, String name) {
            super(gang, name);
            this.setMovement(2);
            this.setDamage(2);
            this.setRange(3);
            this.setHealth(2);
            
            this.setShotsLeft(1);
            this.setMovementLeft(2);
            this.setHealthLeft(2);
            
	}
	
	public void scan(){
		
	}
	
	public void shoot(){
		
	}
	
	public void move(){
		
	}
	
	public void turn(){
		
	}
	
}

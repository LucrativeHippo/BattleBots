package model;

import org.json.simple.JSONObject;

public class TankAI extends Tank{

	private JSONObject instructionCode;
	
	public TankAI(String gang, String name) {
            super(gang, name);
            this.setMovement(1);
            this.setDamage(3);
            this.setRange(1);
            this.setHealth(3);
            
            this.setShotsLeft(1);
            this.setMovementLeft(1);
            this.setHealthLeft(3);
            
	}
	
	public void scan(){
		
	}
	
	public void turn(){
		
	}
	
}

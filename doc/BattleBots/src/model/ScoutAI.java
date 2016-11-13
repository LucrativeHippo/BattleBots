package model;

import org.json.simple.JSONObject;

public class ScoutAI extends Scout {
	
	private JSONObject instructionCode;
	
	public ScoutAI(String gang, String name) {
            super(gang, name);
            this.setMovement(3);
            this.setDamage(1);
            this.setRange(2);
            this.setHealth(1);
            
            this.setShotsLeft(1);
            this.setMovementLeft(3);
            this.setHealthLeft(1);
            
	}
	//boolean?
        public void move(){
            if(this.getMovementLeft()>0){
            if(this.getRelativeDirection()==0){
                if(){
                    
                }
            }
            if(this.getRelativeDirection()==1){
                
            }
            if(this.getRelativeDirection()==2){
                
            }
            if(this.getRelativeDirection()==3){
                
            }
            if(this.getRelativeDirection()==4){
                
            }
            if(this.getRelativeDirection()==5){
                
            }
            }
        }
        
        
	public void scan(){
		
	}
	
	public void turn(){
		
	}

}

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
                if(this.board.spaces[this.getHorizontalLocation()+1][this.getVerticalLocation()]!=null){
                    //this.board.spaces[this.getHorizontalLocation()]][this.getVerticalLocation()].robotList.remove(this.name);
                    //this.board.spaces[this.getHorizontalLocation()+1]][this.getVerticalLocation()].robotList.add(this);
                    this.setHorizontalLocation(this.getHorizontalLocation()+1);
                    this.setVerticalLocation(this.getVerticalLocation());
                }
            }
            if(this.getRelativeDirection()==1){
                if(this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()+1]!=null){
                    //this.board.spaces[this.getHorizontalLocation()]][this.getVerticalLocation()].robotList.remove(this.name);
                    //this.board.spaces[this.getHorizontalLocation()]][this.getVerticalLocation()+1].robotList.add(this);
                    this.setHorizontalLocation(this.getHorizontalLocation());
                    this.setVerticalLocation(this.getVerticalLocation()+1);
                }
            }
            if(this.getRelativeDirection()==2){
                if(this.board.spaces[this.getHorizontalLocation()-1][this.getVerticalLocation()+1]!=null){
                    //this.board.spaces[this.getHorizontalLocation()]][this.getVerticalLocation()].robotList.remove(this.name);
                    //this.board.spaces[this.getHorizontalLocation()-1]][this.getVerticalLocation()+1].robotList.add(this);
                    this.setHorizontalLocation(this.getHorizontalLocation()-1);
                    this.setVerticalLocation(this.getVerticalLocation()+1);
                }
            }
            if(this.getRelativeDirection()==3){
                if(this.board.spaces[this.getHorizontalLocation()-1][this.getVerticalLocation()]!=null){
                    //this.board.spaces[this.getHorizontalLocation()]][this.getVerticalLocation()].robotList.remove(this.name);
                    //this.board.spaces[this.getHorizontalLocation()-1]][this.getVerticalLocation()].robotList.add(this);
                    this.setHorizontalLocation(this.getHorizontalLocation()-1);
                    this.setVerticalLocation(this.getVerticalLocation());
                }
            }
            if(this.getRelativeDirection()==4){
                if(this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()-1]!=null){
                    //this.board.spaces[this.getHorizontalLocation()]][this.getVerticalLocation()].robotList.remove(this.name);
                    //this.board.spaces[this.getHorizontalLocation()]][this.getVerticalLocation()-1].robotList.add(this);
                    this.setHorizontalLocation(this.getHorizontalLocation());
                    this.setVerticalLocation(this.getVerticalLocation()-1);
                }
            }
            if(this.getRelativeDirection()==5){
                if(this.board.spaces[this.getHorizontalLocation()+1][this.getVerticalLocation()-1]!=null){
                    //this.board.spaces[this.getHorizontalLocation()]][this.getVerticalLocation()].robotList.remove(this.name);
                    //this.board.spaces[this.getHorizontalLocation()+1]][this.getVerticalLocation()-1].robotList.add(this);
                    this.setHorizontalLocation(this.getHorizontalLocation()+1);
                    this.setVerticalLocation(this.getVerticalLocation()-1);
                }
            }
            }
        }
        
        
	public void scan(){
		
	}
	
	public void turn(int desiredDirection){
		if((desiredDirection<=5)&&(desiredDirection>=0)){
                    this.setRelativeDirection((this.getRelativeDirection()+desiredDirection)%6);
                }
	}

}

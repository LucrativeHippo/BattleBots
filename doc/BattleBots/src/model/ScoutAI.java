package model;


import java.io.IOException;
import static java.lang.Math.abs;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import javax.imageio.ImageIO;
import org.json.simple.JSONObject;

public class ScoutAI extends Scout {
	
	//private JSONObject instructionCode;
  private int totalMoves;
  private int robotsKilled;
  private int damageDealt;
  private int damageTaken;
  
  
        
	
	public ScoutAI(String name, JSONObject code) {
    super(name);
    instructionCode = code;
    this.forthValues = new Stack();
    this.sniperMailBox = new Stack();
    this.scoutMailBox = new Stack();
    this.tankMailBox = new Stack();
    this.setMovement(3);
    this.setDamage(1);
    this.setRange(2);
    this.setHealth(1);
    this.setType("SCOUT");
    this.isHuman = false;
            
    this.setShotsLeft(1);
    this.setMovementLeft(3);
    this.setHealthLeft(1);
            
    scannedRobotsList = new LinkedList();
  
            
    //this.totalMoves = 
            
	}
        
  /**
   * This function will return the JSON file of the instruction code
   * @return 
   */
  public JSONObject getCode(){
    return instructionCode;
  }
	
  @Override
  public void move(char relativeDirection){
    //Robot AIs should not be able to take move input!
  }
        
  public void move() throws Exception{
    Exception x = null;
    if(this.getMovementLeft()>0){
      if(this.getRelativeDirection() == 0){
        try{
          if(this.board.spaces[this.getHorizontalLocation()+1]
              [this.getVerticalLocation()].hexExists){
            this.board.spaces[this.getHorizontalLocation()+1]
                [this.getVerticalLocation()].robotList.add(this);
            this.board.spaces[this.getHorizontalLocation()]
                [this.getVerticalLocation()].robotList.remove(this);
            this.setHorizontalLocation(this.getHorizontalLocation()+1);
            this.setVerticalLocation(this.getVerticalLocation());
            this.setMovementLeft(this.getMovementLeft()-1);
            this.setMoves(this.getMoves() + 1);
          }
        }
        catch(Exception e){
          //do nothing
        }
      }
      if(this.getRelativeDirection() == 1){
        try{
          if(this.getVerticalLocation()%2 == 0){
            if(this.board.spaces[this.getHorizontalLocation()]
                [this.getVerticalLocation()+1].hexExists){
              this.board.spaces[this.getHorizontalLocation()][
                  this.getVerticalLocation()+1].robotList.add(this);
              this.board.spaces[this.getHorizontalLocation()]
                  [this.getVerticalLocation()].robotList.remove(this);
              this.setHorizontalLocation(this.getHorizontalLocation());
              this.setVerticalLocation(this.getVerticalLocation()+1);
              this.setMovementLeft(this.getMovementLeft()-1);
            }
          } else{
            if(this.board.spaces[this.getHorizontalLocation()+1]
                [this.getVerticalLocation()+1].hexExists){
              this.board.spaces[this.getHorizontalLocation()+1]
                  [this.getVerticalLocation()+1].robotList.add(this);
              this.board.spaces[this.getHorizontalLocation()]
                  [this.getVerticalLocation()].robotList.remove(this);
              this.setHorizontalLocation(this.getHorizontalLocation()+1);
              this.setVerticalLocation(this.getVerticalLocation()+1);
              this.setMovementLeft(this.getMovementLeft()-1);
            }
          }
          this.setMoves(this.getMoves() + 1);
        }
        catch(Exception e){                
        }
      }
      if(this.getRelativeDirection() == 2){
        try{
          if(this.getVerticalLocation()%2 == 0){
            if (this.board.spaces[this.getHorizontalLocation()-1]
                [this.getVerticalLocation()+1].hexExists){
              this.board.spaces[this.getHorizontalLocation()-1]
                  [this.getVerticalLocation()+1].robotList.add(this);
              this.board.spaces[this.getHorizontalLocation()]
                  [this.getVerticalLocation()].robotList.remove(this);
              this.setHorizontalLocation(this.getHorizontalLocation()-1);
              this.setVerticalLocation(this.getVerticalLocation()+1);
              this.setMovementLeft(this.getMovementLeft()-1);
            }
          } else{//different for odd rows
            if(this.board.spaces[this.getHorizontalLocation()]
                [this.getVerticalLocation()+1].hexExists){
              this.board.spaces[this.getHorizontalLocation()]
                  [this.getVerticalLocation()+1].robotList.add(this);
              this.board.spaces[this.getHorizontalLocation()]
                  [this.getVerticalLocation()].robotList.remove(this);
              this.setVerticalLocation(this.getVerticalLocation()+1);
              this.setMovementLeft(this.getMovementLeft()-1);
            }
          }
          this.setMoves(this.getMoves() + 1);
        }
        catch(Exception e){                   
        }
      }
      if(this.getRelativeDirection() == 3){
        try{
          if(this.board.spaces[this.getHorizontalLocation()-1]
              [this.getVerticalLocation()].hexExists){          
            this.board.spaces[this.getHorizontalLocation()-1]
              [this.getVerticalLocation()].robotList.add(this);
            this.board.spaces[this.getHorizontalLocation()]
                [this.getVerticalLocation()].robotList.remove(this);
            this.setHorizontalLocation(this.getHorizontalLocation()-1);
            this.setVerticalLocation(this.getVerticalLocation());
            this.setMovementLeft(this.getMovementLeft()-1);
           this.setMoves(this.getMoves() + 1);
          }
        }
        catch(Exception e){
        }
      }
      if(this.getRelativeDirection() == 4){
        try{
          if(this.getVerticalLocation()%2 == 1){
            if(this.board.spaces[this.getHorizontalLocation()]
                [this.getVerticalLocation()-1].hexExists){
              this.board.spaces[this.getHorizontalLocation()]
                [this.getVerticalLocation()-1].robotList.add(this);
              this.board.spaces[this.getHorizontalLocation()]
                  [this.getVerticalLocation()].robotList.remove(this);
              this.setHorizontalLocation(this.getHorizontalLocation());
              this.setVerticalLocation(this.getVerticalLocation()-1);
              this.setMovementLeft(this.getMovementLeft()-1);
            }
          } else{
            if (this.board.spaces[this.getHorizontalLocation()-1]
                [this.getVerticalLocation()-1].hexExists){
              this.board.spaces[this.getHorizontalLocation()-1]
                  [this.getVerticalLocation()-1].robotList.add(this);               
              this.board.spaces[this.getHorizontalLocation()]
                  [this.getVerticalLocation()].robotList.remove(this);
              this.setHorizontalLocation(this.getHorizontalLocation()-1);
              this.setVerticalLocation(this.getVerticalLocation()-1);
              this.setMovementLeft(this.getMovementLeft()-1);
            }
          }
          this.setMoves(this.getMoves() + 1);
        }
        catch(Exception e){
        }
      }
      if(this.getRelativeDirection() == 5){
        try{
          if(this.getVerticalLocation()%2 == 1){
            if (this.board.spaces[this.getHorizontalLocation()+1]
                [this.getVerticalLocation()-1].hexExists){
              this.board.spaces[this.getHorizontalLocation()+1]
                  [this.getVerticalLocation()-1].robotList.add(this);
              this.board.spaces[this.getHorizontalLocation()]
                  [this.getVerticalLocation()].robotList.remove(this);
              this.setHorizontalLocation(this.getHorizontalLocation()+1);
              this.setVerticalLocation(this.getVerticalLocation()-1);
              this.setMovementLeft(this.getMovementLeft()-1);
            }
          } else{
            if(this.board.spaces[this.getHorizontalLocation()]
                [this.getVerticalLocation()-1].hexExists){
              this.board.spaces[this.getHorizontalLocation()]
                  [this.getVerticalLocation()-1].robotList.add(this);
              this.board.spaces[this.getHorizontalLocation()]
                  [this.getVerticalLocation()].robotList.remove(this);
              this.setHorizontalLocation(this.getHorizontalLocation());
              this.setVerticalLocation(this.getVerticalLocation()-1);
              this.setMovementLeft(this.getMovementLeft()-1);
            }
          }
          this.setMoves(this.getMoves() + 1);
        }
        catch(Exception e){
        }
      }
    }
  }
        
        
	public int scan(){
    Exception x= null;
    int numRobots = 0;
    int minRange = this.getRange()*-1;
    int hexModifier = 0;
    int scanRange = this.getRange();
    for(int i=minRange; i <= this.getRange(); i++){
        int beginning = -1;
        int end = 1;
        if(abs(i)<= 1){
            beginning = -2;
        }
            if(abs(i)== 0){
                end = 2;
            }
      for(int k=beginning; k <= end; k++){
        x=null;
        try {
          Hex temp = this.board.spaces[this.getHorizontalLocation()+k]
              [this.getVerticalLocation()+i];
        } catch (ArrayIndexOutOfBoundsException e) {
          x=e;                   
        }
        if(x==null){
          if(this.board.spaces[this.getHorizontalLocation()+k]
              [this.getVerticalLocation()+i].hexExists==
              true&&this.board.spaces[this.getHorizontalLocation()+k]
              [this.getVerticalLocation()+i].isEmpty()==false){
            Iterator<Robot> robotIterator = this.board.spaces[this.getHorizontalLocation()+k]
                [this.getVerticalLocation()+i].robotList.iterator();
            while(robotIterator.hasNext()){
              numRobots = numRobots + 1;
              Robot temp = robotIterator.next();
              this.scannedRobotsList.add(temp);
            }
          }          
        }          
      }
    }
    return numRobots;
  }
        
	
	public void turn(int desiredDirection){
		if((desiredDirection<=5)&&(desiredDirection>=0)){
      this.setRelativeDirection((this.getRelativeDirection()+desiredDirection)%6);
    }
	}
  
  public Robot Identify(Robot robot){
    Robot robot1 = robot;
    return robot;
  }
        
  /**
   * This function inflicts damage to the robot by the given amount
   * @param damage Integer of damage inflicted
   */
    public void recieveDamage(int damage){
		if(this.getHealthLeft() <= damage){
      this.setHealthLeft(0);
      this.setDied(this.getDied() + 1);
      this.setAbsorbed(this.getAbsorbed() + damage);
      this.board.aliveList.remove(this);
    } else{
      this.setHealthLeft(this.getHealthLeft() - damage);
      this.setAbsorbed(this.getAbsorbed() + damage);
    }
	}
        
  public void robotShooting(int direction, int range){
    if(range > this.getRange()){      
    } else{
      if(this.getVerticalLocation()%2 == 0){
        if(range == 1){
            
          if(direction == 0){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()]);
          }else if(direction == 1){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()+1]);
          }else if(direction == 2){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()+1]);
          }else if(direction == 3){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()]);
          }else if(direction == 4){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()-1]);
          }else{
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()-1]);
          }
        }
        if(range == 2){
            
          if(direction == 0){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()+0]);
          }else if(direction == 1){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()+1]);
          }else if(direction == 2){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()+2]);
          }else if(direction == 3){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()+2]);
          }else if(direction == 4){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()+2]);
          }else if(direction == 5){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-2][this.getVerticalLocation()+1]);
          }else if(direction == 6){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-2][this.getVerticalLocation()]);
          }else if(direction == 7){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-2][this.getVerticalLocation()-1]);
          }else if(direction == 8){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()-2]);
          }else if(direction == 9){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()-2]);
          }else if(direction == 10){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()-2]);
          }else if(direction == 11){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()-1]);
          }
        } else{
            
          if(direction == 0){
              this.shoots(this.board.spaces
              [this.getHorizontalLocation()+3][this.getVerticalLocation()]);
          }else if(direction == 1){
              this.shoots(this.board.spaces
              [this.getHorizontalLocation()+2][this.getVerticalLocation()+1]);
          }else if(direction == 2){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()+2]);
          }else if(direction == 3){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()+3]);
          }else if(direction == 4){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()+3]);
          }else if(direction == 5){
             this.shoots(this.board.spaces
             [this.getHorizontalLocation()-1][this.getVerticalLocation()+3]);
          }else if(direction == 6){
             this.shoots(this.board.spaces
             [this.getHorizontalLocation()-2][this.getVerticalLocation()+3]);
          }else if(direction == 7){
             this.shoots(this.board.spaces
             [this.getHorizontalLocation()-2][this.getVerticalLocation()+2]);
          }else if(direction == 8){
              this.shoots(this.board.spaces
              [this.getHorizontalLocation()-3][this.getVerticalLocation()+1]);
          }else if(direction == 9){
              this.shoots(this.board.spaces
              [this.getHorizontalLocation()-3][this.getVerticalLocation()]);
          }else if(direction == 10){
              this.shoots(this.board.spaces
              [this.getHorizontalLocation()-3][this.getVerticalLocation()-1]);
          }else if(direction == 11){
              this.shoots(this.board.spaces
              [this.getHorizontalLocation()-2][this.getVerticalLocation()-2]);
          }else if(direction == 12){
              this.shoots(this.board.spaces
              [this.getHorizontalLocation()-2][this.getVerticalLocation()-3]);
          }else if(direction == 13){
              this.shoots(this.board.spaces
              [this.getHorizontalLocation()-1][this.getVerticalLocation()-3]);
          }else if(direction == 14){
              this.shoots(this.board.spaces
              [this.getHorizontalLocation()][this.getVerticalLocation()-3]);
          }else if(direction == 15){
              this.shoots(this.board.spaces
              [this.getHorizontalLocation()+1][this.getVerticalLocation()-3]);
          }else if(direction == 16){
              this.shoots(this.board.spaces
              [this.getHorizontalLocation()+2][this.getVerticalLocation()-2]);
          }else{
              this.shoots(this.board.spaces
              [this.getHorizontalLocation()+2][this.getVerticalLocation()-1]);
          }
        }
      } else{//We are on an odd vertical row, so we must compensate
        if(range == 1){
            
          if(direction == 0){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()]);
          }else if(direction == 1){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()+1]);
          }else if(direction == 2){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()+1]);
          }else if(direction == 3){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()]);
          }else if(direction == 4){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()-1]);
          }else{
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()-1]);
          }
        }
        if(range == 2){
            
          if(direction == 0){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()+0]);
          }else if(direction == 1){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()+1]);
          }else if(direction == 2){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()+2]);
          }else if(direction == 3){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()+2]);
          }else if(direction == 4){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()+2]);
          }else if(direction == 5){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()+1]);
          }else if(direction == 6){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-2][this.getVerticalLocation()]);
          }else if(direction == 7){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()-1]);
          }else if(direction == 8){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()-2]);
          }else if(direction == 9){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()-2]);
          }else if(direction == 10){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()-2]);
          }else if(direction == 11){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()-1]);
          }
        } else{
            
          if(direction == 0){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+3][this.getVerticalLocation()]);
          }else if(direction == 1){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+3][this.getVerticalLocation()+1]);
          }else if(direction == 2){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()+2]);
          }else if(direction == 3){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()+3]);
          }else if(direction == 4){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()+3]);
          }else if(direction == 5){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()+3]);
          }else if(direction == 6){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()+3]);
          }else if(direction == 7){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-2][this.getVerticalLocation()+2]);
          }else if(direction == 8){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-2][this.getVerticalLocation()+1]);
          }else if(direction == 9){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-3][this.getVerticalLocation()]);
          }else if(direction == 10){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-2][this.getVerticalLocation()-1]);
          }else if(direction == 11){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-2][this.getVerticalLocation()-2]);
          }else if(direction == 12){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()-3]);
          }else if(direction == 13){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()-3]);
          }else if(direction == 14){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()-3]);
          }else if(direction == 15){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()-3]);
          }else if(direction == 16){
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()-2]);
          }else{
            this.shoots(this.board.spaces
            [this.getHorizontalLocation()+3][this.getVerticalLocation()-1]);
          }
        }
      }
    }
  }
        
        /**
         * This function will take in a Hex space and deal damage to any robots
         * on that space by the amount of damage that the current robot is able
         * to do
         * @param space Hex space to be shot at
         */
        
  public void shoots(Hex space){
		if(space == null){
      System.out.println("The space is out of range");
        return;
    } else if(space.isEmpty() == true){
      return;
    } else{
      Iterator<Robot> robotIterator = space.robotList.iterator();
      while(robotIterator.hasNext()){
        Robot temp = robotIterator.next();
       temp.recieveDamage(this.getDamage());
        this.damageDealt = this.damageDealt + this.getDamage();
        if(this.getDamage() >= temp.getHealthLeft()){
          this.robotsKilled = this.robotsKilled+1;
        }
      }
    }
	}
        
  /**
   * This function will check to see the condition of a given space in the
   * relative direction beside it.  This is used for the forth check
   * function
   * @param direction
   * @return True if empty, false otherwise
   * @throws Exception if the direction given is not 0, 1, 2, 3, 4, or 5
   */
   public boolean check(int direction) throws Exception{
    int current = this.getRelativeDirection();
    int actual = current;
    if(this.getVerticalLocation()%2 == 0){
    if(actual == 0){
       return this.board.spaces[this.getHorizontalLocation()+1][this.getVerticalLocation()].hexExists;
    }
    if(actual == 1){
      return this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()+1].hexExists;
    }
    if(actual == 2){
      return this.board.spaces[this.getHorizontalLocation()-1][this.getVerticalLocation()+1].hexExists;
    }
    if(actual == 3){
      return this.board.spaces[this.getHorizontalLocation()-1][this.getVerticalLocation()].hexExists;
    }
    if(actual == 4){
      return this.board.spaces[this.getHorizontalLocation()-1][this.getVerticalLocation()-1].hexExists;
    }
    if(actual == 5){
      return this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()-1].hexExists;
    } else{
    
      throw new Exception("A robot AI is checking for a directional value not between 0 and 5");
    }
  }
    else{
        if(actual == 0){
       return this.board.spaces[this.getHorizontalLocation()+1][this.getVerticalLocation()].hexExists;
    }
    if(actual == 1){
      return this.board.spaces[this.getHorizontalLocation()+1][this.getVerticalLocation()+1].hexExists;
    }
    if(actual == 2){
      return this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()+1].hexExists;
    }
    if(actual == 3){
      return this.board.spaces[this.getHorizontalLocation()-1][this.getVerticalLocation()].hexExists;
    }
    if(actual == 4){
      return this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()-1].hexExists;
    }
    if(actual == 5){
      return this.board.spaces[this.getHorizontalLocation()+1][this.getVerticalLocation()-1].hexExists;
    } else{
    
      throw new Exception("A robot AI is checking for a directional value not between 0 and 5");
    }
    }
   }

}        
           
        
        
    

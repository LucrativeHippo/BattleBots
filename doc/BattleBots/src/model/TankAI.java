package model;

import static java.lang.Math.abs;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import org.json.simple.JSONObject;

public class TankAI extends Tank{
	//private JSONObject instructionCode;
  private int totalMoves;
  private int robotsKilled;
  private int damageDealt;
  private int damageTaken;
  public LinkedList<Robot> scannedRobotsList;
        
	
	public TankAI(String name, JSONObject code) {
    super(name);
    instructionCode = code;
    this.forthValues = new Stack();
    this.sniperMailBox = new Stack();
    this.scoutMailBox = new Stack();
    this.tankMailBox = new Stack();
    this.setMovement(1);
    this.setDamage(3);
    this.setRange(1);
    this.setHealth(3);
    this.setType("TANK");
    this.isHuman = false;
    this.setShotsLeft(1);
    this.setMovementLeft(1);
    this.setHealthLeft(3);
    scannedRobotsList = new LinkedList();
            
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
            totalMoves = totalMoves +1;
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
              this.board.spaces[this.getHorizontalLocation()]
                  [this.getVerticalLocation()+1].robotList.add(this);
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
          totalMoves = totalMoves +1;
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
              System.out.println("We were on an even horizontal position, now we have moved");
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
              System.out.println("We were on an odd horizontal position, now we have moved");
            }
          }
          totalMoves = totalMoves +1;
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
            totalMoves = totalMoves +1;
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
          totalMoves = totalMoves +1;
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
          totalMoves = totalMoves +1;
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
      for(int k=hexModifier; k <= scanRange; k++){
        x=null;
        try {
          Hex temp = this.board.spaces[this.getHorizontalLocation()+k]
              [this.getVerticalLocation()+i];
        } 
        catch (ArrayIndexOutOfBoundsException e){
          System.out.println(e + " caught due to scanning out of bounds.");
          x=e;                
        }
        if(x==null){
          System.out.println(this.getHorizontalLocation()+k);
          System.out.println(this.getVerticalLocation()+i);
          if(this.board.spaces[this.getHorizontalLocation()+k]
              [this.getVerticalLocation()+i].hexExists==
              true&&this.board.spaces[this.getHorizontalLocation()+k]
              [this.getVerticalLocation()+i].isEmpty()==false){
            System.out.println("hex check successful");
            Iterator<Robot> robotIterator = 
                this.board.spaces[this.getHorizontalLocation()+k]
                [this.getVerticalLocation()+i].robotList.iterator();
            while(robotIterator.hasNext()){
              numRobots = numRobots + 1;
              Robot temp = robotIterator.next();
              System.out.println(temp.getName());
              this.scannedRobotsList.add(temp);
            }
          }          
        }          
      }
      if(hexModifier == minRange){
        scanRange--;
      } else{
        hexModifier=hexModifier-1;
      } 
    }
    return numRobots;
  }
	
	public void turn(int desiredDirection){
		if((desiredDirection<=5)&&(desiredDirection>=0)){
      this.setRelativeDirection((this.getRelativeDirection()+desiredDirection)%6);
    }
	}
        
  public int turnsRequired(int directionWanted){
    int turnsRequired = abs(this.getRelativeDirection() - directionWanted);
    return turnsRequired;
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
    } else{
      this.setHealthLeft(this.getHealthLeft() - damage);
      this.damageTaken = this.damageTaken + damage;
    }
	}
        
  /**
   * This function will allow a robot to shoot at a given direction and 
   * @param direction
   * @param range 
   */
  public void robotShooting(int direction, int range){
    if(range > this.getRange()){
                
    } else{
      if(this.getVerticalLocation()%2 == 0){
        if(range == 1){
          if(direction == 0){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()]);
          }else if(direction == 1){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()+1]);
          }else if(direction == 2){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()+1]);
          }else if(direction == 3){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()]);
          }else if(direction == 4){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()-1]);
          }else{
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()-1]);
          }
        }
        if(range == 2){
          if(direction == 0){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()+0]);
          }else if(direction == 1){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()+1]);
          }else if(direction == 2){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()+2]);
          }else if(direction == 3){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()+2]);
          }else if(direction == 4){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()+2]);
          }else if(direction == 5){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-2][this.getVerticalLocation()+1]);
          }else if(direction == 6){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-2][this.getVerticalLocation()]);
          }else if(direction == 7){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-2][this.getVerticalLocation()-1]);
          }else if(direction == 8){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()-2]);
          }else if(direction == 9){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()-2]);
          }else if(direction == 10){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()-2]);
          }else if(direction == 11){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()-1]);
          }
        } else{
          if(direction == 0){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+3][this.getVerticalLocation()]);
          }else if(direction == 1){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()+1]);
          }else if(direction == 2){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()+2]);
          }else if(direction == 3){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()+3]);
          }else if(direction == 4){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()+3]);
          }else if(direction == 5){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()+3]);
          }else if(direction == 6){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-2][this.getVerticalLocation()+3]);
          }else if(direction == 7){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-2][this.getVerticalLocation()+2]);
          }else if(direction == 8){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-3][this.getVerticalLocation()+1]);
          }else if(direction == 9){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-3][this.getVerticalLocation()]);
          }else if(direction == 10){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-3][this.getVerticalLocation()-1]);
          }else if(direction == 11){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-2][this.getVerticalLocation()-2]);
          }else if(direction == 12){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-2][this.getVerticalLocation()-3]);
          }else if(direction == 13){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()-3]);
          }else if(direction == 14){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()-3]);
          }else if(direction == 15){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()-3]);
          }else if(direction == 16){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()-2]);
          }else{
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()-1]);
          }
        }
      } else{//We are on an odd vertical row, so we must compensate
        if(range == 1){
          if(direction == 0){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()]);
          } else if(direction == 1){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()+1]);
          } else if(direction == 2){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()+1]);
          } else if(direction == 3){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()]);
          } else if(direction == 4){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()-1]);
          } else{
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()-1]);
          }
        }
        if(range == 2){
          if(direction == 0){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()+0]);
          }else if(direction == 1){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()+1]);
          }else if(direction == 2){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()+2]);
          }else if(direction == 3){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()+2]);
          }else if(direction == 4){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()+2]);
          }else if(direction == 5){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()+1]);
          }else if(direction == 6){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-2][this.getVerticalLocation()]);
          }else if(direction == 7){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()-1]);
          }else if(direction == 8){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()-2]);
          }else if(direction == 9){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()-2]);
          }else if(direction == 10){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()-2]);///////check above
          }else if(direction == 11){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()-1]);
          }
        } else{
          if(direction == 0){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+3][this.getVerticalLocation()]);
          }else if(direction == 1){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+3][this.getVerticalLocation()+1]);
          }else if(direction == 2){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()+2]);
          }else if(direction == 3){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()+3]);
          }else if(direction == 4){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()+3]);
          }else if(direction == 5){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()+3]);
          }else if(direction == 6){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()+3]);
          }else if(direction == 7){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-2][this.getVerticalLocation()+2]);
          }else if(direction == 8){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-2][this.getVerticalLocation()+1]);
          }else if(direction == 9){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-3][this.getVerticalLocation()]);
          }else if(direction == 10){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-2][this.getVerticalLocation()-1]);
          }else if(direction == 11){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-2][this.getVerticalLocation()-2]);
          }else if(direction == 12){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()-1][this.getVerticalLocation()-3]);
          }else if(direction == 13){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()][this.getVerticalLocation()-3]);
          }else if(direction == 14){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+1][this.getVerticalLocation()-3]);
          }else if(direction == 15){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()-3]);
          }else if(direction == 16){
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+2][this.getVerticalLocation()-2]);
          }else{
            this.shoot(this.board.spaces
            [this.getHorizontalLocation()+3][this.getVerticalLocation()-1]);
          }
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
    int actual = current + direction;
    actual = actual%6;
    if(actual == 0){
      return this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].hexExists;
    }
    if(actual == 1){
      return this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].hexExists;
    }
    if(actual == 2){
      return this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].hexExists;
    }
    if(actual == 3){
      return this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].hexExists;
    }
    if(actual == 4){
      return this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].hexExists;
    }
    if(actual == 5){
      return this.board.spaces[this.getHorizontalLocation()][this.getVerticalLocation()].hexExists;
    } else{
      throw new Exception("A robot AI is checking for a directional value not between 0 and 5");
    }
  }
         
         
  public int getDirectionOfEnemy(int index){
            
    Robot tmp = this.scannedRobotsList.get(index);
    if(tmp != null){
      if((this.getHorizontalLocation() == this.scannedRobotsList.get(index).getHorizontalLocation())&&(this.getVerticalLocation()-1 ==this.scannedRobotsList.get(index).getVerticalLocation())){
        return 4;
      } else if((this.getHorizontalLocation()+1 == this.scannedRobotsList.get(index).getHorizontalLocation())&&(this.getVerticalLocation()-1 ==this.scannedRobotsList.get(index).getVerticalLocation())){
        return 5;
      } else if((this.getHorizontalLocation()-1 == this.scannedRobotsList.get(index).getHorizontalLocation())&&(this.getVerticalLocation() ==this.scannedRobotsList.get(index).getVerticalLocation())){
        return 3;
      } else if((this.getHorizontalLocation() == this.scannedRobotsList.get(index).getHorizontalLocation())&&(this.getVerticalLocation() ==this.scannedRobotsList.get(index).getVerticalLocation())){
        return 0;
      } else if((this.getHorizontalLocation()+1 == this.scannedRobotsList.get(index).getHorizontalLocation())&&(this.getVerticalLocation() ==this.scannedRobotsList.get(index).getVerticalLocation())){
        return 0;
      } else if((this.getHorizontalLocation()-1 == this.scannedRobotsList.get(index).getHorizontalLocation())&&(this.getVerticalLocation()+1 ==this.scannedRobotsList.get(index).getVerticalLocation())){
        return 2;
      } else{
        return 1;
      } 
    } else{
      System.out.println("there are no robots within your range.");    
      return 0;
    }
  }
         
         
         
  public int getRangeOfEnemy(int index){
    Robot tmp = this.scannedRobotsList.get(index);
    int distance = 0;
    if(tmp != null){
      if((this.getHorizontalLocation() == this.scannedRobotsList.get(index).getHorizontalLocation())&&(this.getVerticalLocation()-1 ==this.scannedRobotsList.get(index).getVerticalLocation())){
        distance = 1;
      } else if((this.getHorizontalLocation()+1 == this.scannedRobotsList.get(index).getHorizontalLocation())&&(this.getVerticalLocation()-1 ==this.scannedRobotsList.get(index).getVerticalLocation())){
        distance = 1;
      } else if((this.getHorizontalLocation()-1 == this.scannedRobotsList.get(index).getHorizontalLocation())&&(this.getVerticalLocation() ==this.scannedRobotsList.get(index).getVerticalLocation())){
        distance = 1;
      } else if((this.getHorizontalLocation() == this.scannedRobotsList.get(index).getHorizontalLocation())&&(this.getVerticalLocation() ==this.scannedRobotsList.get(index).getVerticalLocation())){
        distance = 0;
      } else if((this.getHorizontalLocation()+1 == this.scannedRobotsList.get(index).getHorizontalLocation())&&(this.getVerticalLocation() ==this.scannedRobotsList.get(index).getVerticalLocation())){
        distance = 1;
      } else if((this.getHorizontalLocation()-1 == this.scannedRobotsList.get(index).getHorizontalLocation())&&(this.getVerticalLocation()+1 ==this.scannedRobotsList.get(index).getVerticalLocation())){
        distance = 1;
      } else{
        distance = 1;
      }
    } else{
      System.out.println("there are no robots within your range.");
    }
    return distance;
    }
  }

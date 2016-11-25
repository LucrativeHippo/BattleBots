package model;

public class GangAI {
	private String team;
	private int numRobots;
	private ScoutAI scout;
	private SniperAI sniper;
	private TankAI tank;
	
	public GangAI(ScoutAI scout1, SniperAI sniper1, TankAI tank1) {
		scout = scout1;
		sniper = sniper1;
		tank = tank1;
		
	}
        
        /**
         * This function will return a string of the team color the gang is
         * @return String of "RED", "ORANGE", "YELLOW", "GREEN", "BLUE", "PURPLE"
         */
	public String getTeam() {
		return team;
	}
        
        /**
         * This function will set the team name to a string of the team color 
         * the gang will be. It can be "RED", "ORANGE", "YELLOW", "GREEN", 
         * "BLUE", "PURPLE"
         * @param team
         */
	public void setTeam(String team) {
		this.team = team;
	}
        
        /**
         * This function returns the number of robots on the team
         * @return 
         *//**
         * This function will set the team name to a string of the team color 
         * the gang will be. It can be "RED", "ORANGE", "YELLOW", "GREEN", 
         * "BLUE", "PURPLE"
         * @return 
         */
	public int getNumRobots() {
		return numRobots;
	}
        
        /**
         * This function sets the number of robots on the team
         * @param numRobots 
         */
	public void setNumRobots(int numRobots) {
		this.numRobots = numRobots;
	}

        /**
         * This function returns the scout robot on the team
         * @return 
         */
        public ScoutAI getScout() {
            return scout;
        }

        /**
         * This function returns the sniper robot on the team
         * @return 
         */
        public SniperAI getSniper() {
            return sniper;
        }

        /**
         * This function returns the tank robot on the team
         * @return 
         */
        public TankAI getTank() {
            return tank;
        }
	
		
	
}

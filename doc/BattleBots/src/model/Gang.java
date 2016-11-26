package model;

public class Gang {
	private String team;
	private int numRobots;
	private final Scout scoutRobot;
	private final Sniper sniperRobot;
	private final Tank tankRobot;
	
	public Gang(Scout scout, Sniper sniper, Tank tank) {
		scoutRobot = scout;
		sniperRobot = sniper;
		tankRobot = tank;
		
	}
        
        /**
         * This function will return a string of the team color the robot is on
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
                this.scoutRobot.setGang(team);
                this.sniperRobot.setGang(team);
                this.tankRobot.setGang(team);
	}
        
        /**
         * This function will return the number of robots that each team has
         * @return 
         */
	public int getNumRobots() {
		return numRobots;
	}
        
        /**
         * This function sets the number of robots a team has
         * @param numRobots 
         */
	public void setNumRobots(int numRobots) {
		this.numRobots = numRobots;
	}

        /**
         * This function returns the Scout robot on the team
         * @return 
         */
        public Scout getScoutRobot() {
            return scoutRobot;
        }

        /**
         * This function returns the Sniper robot on the  team
         * @return 
         */
        public Sniper getSniperRobot() {
            return sniperRobot;
        }

        /**
         * This function returns the tank robot on the team
         * @return 
         */
        public Tank getTankRobot() {
            return tankRobot;
        }
	
		
	
}

package model;

public class Gang {
	private String team;
	private int numRobots;
	private Scout scoutRobot;
	private Sniper sniperRobot;
	private Tank tankRobot;
	
	public Gang(Scout scout, Sniper sniper, Tank tank) {
		scoutRobot = scout;
		sniperRobot = sniper;
		tankRobot = tank;
		
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public int getNumRobots() {
		return numRobots;
	}
	public void setNumRobots(int numRobots) {
		this.numRobots = numRobots;
	}
	
		
	
}

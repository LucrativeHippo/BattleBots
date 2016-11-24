package model;

public class Gang {
	private String team;
	private int numRobots;
	private Scout scout;
	private Sniper sniper;
	private Tank tank;
	
	public Gang(String team, Scout scout, Sniper sniper, Tank tank) {
		this.team = team;
//		scout = new Scout();
//		sniper = new Sniper();
//		tank = new Tank();
		
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

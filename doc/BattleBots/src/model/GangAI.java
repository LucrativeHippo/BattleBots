package model;

public class GangAI {
	private String team;
	private int numRobots;
	private ScoutAI scout;
	private SniperAI sniper;
	private TankAI tank;
	
	public GangAI(String team, ScoutAI scout, SniperAI sniper, TankAI tank) {
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

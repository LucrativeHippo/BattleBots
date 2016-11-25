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

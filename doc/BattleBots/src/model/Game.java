package model;

import java.util.List;

public class Game implements RobotController, GameInfo {

	
	public Game(int width, int height){
		
	}
	
	@Override
	public void addObserver(GameObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Robot> getRobots() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPaused() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPlayerScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPlayersRobots() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void togglePaused() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Robot getRobot() {
		// TODO Auto-generated method stub
		return null;
	}

}

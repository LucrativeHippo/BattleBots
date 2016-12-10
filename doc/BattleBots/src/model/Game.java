package model;

import java.util.List;

public class Game implements RobotController, GameInfo {  
  public int boardSize;
  public int numPlayers;
  public int numHumans;
  public Robot currentRobot;
	
	public Game(int width, int height){
		
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

  @Override
  public int getBoardSize() {
    return boardSize;
  }

  @Override
  public int getNumHumans() {
    return numHumans;
  }

  @Override
  public int getNumPlayers() {
    return numPlayers;
  }

  @Override
  public void setBoardSize(int boardSize) {
    this.boardSize = boardSize;
  }

  @Override
  public void setNumHumans(int numHumans) {
    this.numHumans = numHumans;
  }

  @Override
  public void setNumPlayers(int numPlayers) {
    this.numPlayers = numPlayers;
  }

  @Override
  public Robot getCurrentRobot() {
    return this.currentRobot;
  }

  @Override
  public void setCurrentRobot(Robot currentRobot) {
    this.currentRobot = currentRobot;
  }

}

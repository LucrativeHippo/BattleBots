package model;

import java.util.List;

public interface GameInfo {
    
    public void addObserver(GameObserver observer);

    public List<Robot> getRobots();

    public boolean isOver();

    public boolean isPaused();
    
    public int getPlayerScore();

    public int getPlayersRobots();
    
    public int getBoardSize();
    
    public void setBoardSize(int boardSize);
    
    public int getNumHumans();
    
    public void setNumHumans(int numHumans);
    
    public int getNumPlayers();
    
    public void setNumPlayers(int numPlayers);
    
}

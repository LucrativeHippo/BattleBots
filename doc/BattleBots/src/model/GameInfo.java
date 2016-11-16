package model;

import java.util.List;

public interface GameInfo {
	
    public void addObserver(GameObserver observer);

    public List<Robot> getRobots();

    public boolean isOver();

    public boolean isPaused();
    
    public int getPlayerScore();

    public int getPlayerRobots();
    
}

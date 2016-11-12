package model;

/**
 * An observer of the game. The observer is registered with the game. Whenever the game changes, the
 * game will invoke gameChanged on all its observers so that each can take appropriate action.
 */
public interface GameObserver {
	
	/**
	 * call when the game has changed
	 */
    public void gameChanged();
    
}

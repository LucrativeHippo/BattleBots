package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.GameInfo;
import model.GameObserver;
import view.View;

public class GameController implements ActionListener, KeyListener, GameObserver{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
	
	private View view;
	
    private GameInfo gameInfo;
	
    public void start() {
    	 view = new View(WIDTH, HEIGHT);
         view.showMainMenu(this); 
    }
    
    
	@Override
	public void keyPressed(KeyEvent arg0) {
            
            int key = arg0.getKeyCode(); //Gets the key code value of the pressed key
            
            if(key == KeyEvent.VK_W){
                
            }
            if(key == KeyEvent.VK_E){
                
            }
            if(key == KeyEvent.VK_D){
                
            }   
            if(key == KeyEvent.VK_X){
                
            }
            if(key == KeyEvent.VK_Z){
                
            }
            if(key == KeyEvent.VK_A){
                
            }
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

        String actionCommand = arg0.getActionCommand();
		 if (actionCommand.equals("quit"))
	            System.exit(0);
	        else if (actionCommand.equals("help"))
	            view.showHelp(this);
	        else if (actionCommand.equals("begin"))
	            view.showGameProperties(this);
	        else if (actionCommand.equals("backGP"))
	            view.showMainMenu(this);
	        else if (actionCommand.equals("continueGP"))
	            view.showTeamSelection(this);
	        else
	        	throw new IllegalStateException("The event has action command " + actionCommand
	        			+ " that is invalid.");
		
	}

	@Override
	public void gameChanged() {
		// TODO Auto-generated method stub
		
	}

}

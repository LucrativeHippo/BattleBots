package view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import model.GameInfo;
import model.GameObserver;


public class GamePanel extends JPanel implements GameObserver{
	private static final long serialVersionUID = 1L;

	//	  static final int FONT_SIZE;
	//    static final int BUTTON_SPACER_SIZE;
	//    static final int BUTTON_WIDTH;
	//    static final int BUTTON_HEIGHT;

	public GamePanel(int width, int height, ActionListener alistener, KeyListener klistener, GameInfo gameinfo) {

	}

	@Override
	public void gameChanged() {
		// TODO Auto-generated method stub
		
	}
}

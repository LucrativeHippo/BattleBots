package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import model.GameBoard;
import model.GameInfo;
import model.GameObserver;
import model.Hex;


public class GamePanel extends JPanel implements GameObserver{
	private static final long serialVersionUID = 1L;

	//	  static final int FONT_SIZE;
	//    static final int BUTTON_SPACER_SIZE;
	//    static final int BUTTON_WIDTH;
	//    static final int BUTTON_HEIGHT;
	public static int size = 9;
	public GameBoard gameBoard;
	//int width, int height, ActionListener alistener, KeyListener klistener, GameInfo gameinfo
	public GamePanel() {
		gameBoard = new GameBoard(5);
		setBackground(Color.WHITE);
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) { 
				Point p = new Point( Hex.PointAtHex(e.getX(),e.getY()) );
				if (p.x < 0 || p.y < 0 || p.x >= size || p.y >= size) return;
				gameBoard.spaces[p.x][p.y] = null;
				repaint();
			}

		});
	}

	@Override
	public void gameChanged() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g; 
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g2);
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(gameBoard.spaces[i][j] != null && gameBoard.spaces[i][j].hexExists == true){
					gameBoard.spaces[i][j].drawHex(g2);
				}
			}
		}
	}
	public static void main(String [] args){
		GamePanel gg = new GamePanel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.add(gg);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(true);
		frame.setVisible(true);
	}
}

package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.GameBoard;
import model.GameInfo;
import model.GameObserver;
import model.Hex;
import model.Robot;
import model.Scout;
import model.Sniper;
import model.Tank;
import static controller.GameController.gameController;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GamePanel extends JPanel implements GameObserver{

	private static final long serialVersionUID = 1L;

	public GameBoard gameBoard;
	
	int size;
	
	public GamePanel(int width, int height, ActionListener alistener, KeyListener klistener, GameInfo gameinfo) {
		if(gameinfo.getBoardSize() == 5){
			size = 9;
			gameBoard = new GameBoard(5);
		}
		else if(gameinfo.getBoardSize() == 7){
			size = 13;
			gameBoard = new GameBoard(7);
		}
                
                gameBoard.robotList = gameController.createTeams(gameBoard);
                gameController.setTeamPositions(gameBoard);
                gameinfo.setCurrentRobot(gameBoard.spaces[0][6].robotList.get(1));
                gameBoard.setAliveList();
                gameController.gameBoard = gameBoard;
                
//                Scout sc = new Scout("team");
//                sc.setType("SCOUT");
//                sc.setGang("GREEN");
//                sc.setHorizontalLocation(5);
//                sc.setVerticalLocation(5);
//                sc.board = gameBoard;
//                gameController.gameBoard = gameBoard;
//                gameBoard.spaces[5][5].robotList.add(sc);
//                System.out.println("COORDINATES: " + sc.getHorizontalLocation() +" " + sc.getVerticalLocation());
//                
//                gameinfo.setCurrentRobot(sc);
//                
//                
//                  for(int i = 0; i < size; i ++){
//                    for(int j = 0; j < size; j++){
//                        Sniper sn = new Sniper("team");
//                         sn.setType("SNIPER");
//                         sn.setGang("PURPLE");
//                        sn.setHorizontalLocation(i);
//                        sn.setVerticalLocation(j);
//                        gameBoard.spaces[i][j].robotList.add(sn);
//                    }
//                }
//                Sniper sniper = new Sniper("team");
//                sniper.setType("SNIPER");
//                sniper.setGang("PURPLE");
//                sniper.setHorizontalLocation(0);
//                sniper.setVerticalLocation(5);
//                gameBoard.spaces[0][5].robotList.add(sniper);
//                
//                Tank t = new Tank("team");
//                t.setType("TANK");
//                t.setGang("ORANGE");
//                t.setHorizontalLocation(4);
//                t.setVerticalLocation(3);
//                gameBoard.spaces[4][3].robotList.add(t);
                
                
                
//                System.out.println("CurrentRobot: " + gameinfo.getCurrentRobot().getName());
//                System.out.println("scout damage: " + sc.getDamage());
//                System.out.println("scout Health: " + sc.getHealthLeft());
//                sc.recieveDamage(2);
//                System.out.println("scout Health after damage: " + sc.getHealthLeft());
                
		setBackground(Color.WHITE);
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
                            try {
                                gameinfo.getCurrentRobot().move('x');
                            } catch (Exception ex) {
                                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                              System.out.println("Coordinates: " + gameinfo.getCurrentRobot().getHorizontalLocation() + " " + gameinfo.getCurrentRobot().getVerticalLocation());
                              				repaint();

				Point p = new Point( Hex.PointAtHex(e.getX(),e.getY()) );
                                //System.out.println("MOUSE POSITION " + e.getX() + " "+ e.getY());
				if (p.x < 0 || p.y < 0 || p.x >= size || p.y >= size) return;
				//shoot instead of getting rid of space
                                if (!gameBoard.spaces[p.x][p.y].robotList.isEmpty()){
                                    gameinfo.getCurrentRobot().shoot(gameBoard.spaces[p.x][p.y]);
                                    
                                    Iterator<Robot> robots = gameBoard.spaces[p.x][p.y].robotList.iterator();
                                        while(robots.hasNext()){
                                            Robot temp = robots.next();
                                            if (temp.getHealthLeft()<= 0)
                                                gameBoard.spaces[p.x][p.y].robotList.remove(temp);
                                        }
				repaint();
                                }
                                gameController.isGameOver(gameBoard.aliveList);
			}
                   
		});
                addKeyListener(new KeyAdapter(){
                    @Override
                    public void keyPressed(KeyEvent ke){

                        if (ke.getKeyCode() == KeyEvent.VK_A){
                            try {
                                gameinfo.getCurrentRobot().move('a');
                            } catch (Exception ex) {
                                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        repaint();
                        }

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
					if(gameBoard.spaces[i][j].robotList.isEmpty()){
                                            gameBoard.spaces[i][j].drawHex(g2, Color.WHITE);
                                        }
                                        else{
                                            int offset = 0;         // offset drawing of robot if there are more than one on a hex
                                    Iterator<Robot> robots = gameBoard.spaces[i][j].robotList.iterator();
                                        while(robots.hasNext()){
                                            Robot temp = robots.next();
                                            Color c = Color.BLACK;
                                            String color = temp.getGang();
                                            if(temp.getGang() != null)
                                                switch (color){
                                                    case "GREEN":{
                                                        c = Color.GREEN;
                                                        break;
                                                    }
                                                    case "RED":{
                                                        c = Color.RED;
                                                        break;
                                                    }
                                                    case "YELLOW":{
                                                        c = Color.YELLOW;
                                                        break;
                                                    }
                                                    case "PURPLE":{
                                                        c = Color.MAGENTA;
                                                        break;
                                                    }
                                                    case "ORANGE":{
                                                        c = Color.ORANGE;
                                                        break;
                                                    }
                                                    case "BLUE":{
                                                        c = Color.BLUE;
                                                        break;
                                                    }
                                                    default:{
                                                        c = Color.BLACK;
                                                    }
                                                }
                                            if(j%2==0){
                                                gameBoard.spaces[i][j].drawHexWithRobot(temp.getType(),g2, c, i,j, true, offset);
                                            }
                                            else{
                                                gameBoard.spaces[i][j].drawHexWithRobot( temp.getType(),g2, c, i,j, false, offset);                                                
                                            }
                                            offset+=8;                                        }                                            
                                        }
				}
			}
		}
	}

        
}

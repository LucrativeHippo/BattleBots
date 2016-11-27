package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HelpPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	static final int FONT_SIZE = 72;
	static final int BUTTON_SPACER_SIZE = 70;
	static final int BUTTON_WIDTH = 200;
	static final int BUTTON_HEIGHT = 200;

	public HelpPanel(int width, int height, ActionListener actionlistener) {
		setSize(width, height);
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		add(Box.createRigidArea(new Dimension(0, height / 5)));
		JLabel title = new JLabel("Help" );
		title.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE));
		title.setForeground(Color.BLACK);
		add(title, BorderLayout.NORTH);

		JTextArea rules = new JTextArea(
                                " - With two players, red and green are used, and the board has 5 spaces on a side.  With three players, red and yellow \n" +
				"and blue are used, and the board can have 5 or 7 spaces on a side.  With six players, all colours are used, and the board \n" +
				"has 7 spaces on a side.  The colours are assigned randomly.\n" + "\n"+
				" - All robots enter in their home space, facing along the arrow.\n" + "\n"+
				" - Any number of robots, including ones from opposing teams, can occupy the same space.\n" + "\n"+
				" - A turn begins with Red team playing its robot with greatest movement; then orange plays its robot with greatest \n" +
				"movement, ..., finally blue plays its greatest-movement robot -- this completes one \n" +
				"round." +
				" Then a second round is played where Red plays its next-highest movement robot, then orange, ..., up to blue.  Then a third round where Red's \n" +
				"lowest-movement robot plays, ..., finally blue's lowest-movement robot plays.  Thus, every robot gets one play per \n" +
				"turn, and fastest robots move first.\n" +"\n"+
				" - Note that, Red's highest-movement robot might not be its scout, because that robot might be dead.\n" +"\n"+
				" - If a team does not have a robot to play during a round, skip to the next colour.\n" +"\n"+
				" - A play consists of moving, shooting, and moving again. It costs no movement point to turn to face any direction, \n" +
				"and one movement point to enter a space.  If a robot runs out of movement points, it cannot move any further during \n" +
				"that turn.  Different robots have different movement amounts.  At the end of every turn, robots regenerate their \n" +
				"movement points -- i.e. they can move again next turn.\n" +"\n"+
				" - Robots can only see and shoot at robots in range.  As they move, new spaces become visible.\n" +"\n"+
				" - Shooting at a space means selecting a range and direction causes the shooting-robot's attack-rating to be subtracted \n" +
				"from the health of all robots in that targeted space.  If a robot's health drops to (or below) zero, the robot is dead and \n" +
				"removed from play.  Until a robot is dead, its movement and damage ratings are unimpaired.  Note that range=0, \n" +
				"direction=0 is valid.\n" +"\n"+
				" - If more than one team's robots are still alive, another turn starts with Red's highest-movement robot.\n" +"\n"+
				" - If only one team's robots are still alive, that team wins the game" +"\n"+"\n"+
                                " - For controlling the robots to move: Keys A-left. D-right. W-top left. E-top right. Z-bottom left. X-bottom right."+"\n"+"\n"+
                                " - For shooting enemy robots, you just need to simply mouse click which robot you want to shoot within your robot's attacking range \n"+"\n"+
                                " - Be awared that your robots can deal damages to itself by clicking your own robots during its turn."
                                );
		rules.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE/4));
		rules.setForeground(Color.BLACK);
		JScrollPane scrollPane = new JScrollPane(rules);    
		add(scrollPane, BorderLayout.CENTER);


		//        JPanel southButtons = new JPanel();      
		//        southButtons.setBackground(Color.DARK_GRAY);
		//        southButtons.setLayout(new BoxLayout(southButtons, BoxLayout.LINE_AXIS));
		//        southButtons.add(Box.createRigidArea(new Dimension(BUTTON_SPACER_SIZE, 0)));
		//        
		//        JButton quitButton = new JButton("Quit");
		//        quitButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		//        quitButton.setBackground(Color.BLACK);
		//        quitButton.setForeground(Color.WHITE);
		//        quitButton.setActionCommand("quit");
		//        quitButton.addActionListener(actionlistener);
		//        quitButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		//        southButtons.add(quitButton);  
		//        southButtons.add(Box.createRigidArea(new Dimension(BUTTON_SPACER_SIZE, 0)));
		//        
		//        JButton backButton = new JButton("Back");
		//        backButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		//        backButton.setBackground(Color.BLACK);
		//        backButton.setForeground(Color.WHITE);
		//        backButton.setActionCommand("backGP");
		//        backButton.addActionListener(actionlistener);
		//        backButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		//        southButtons.add(backButton);
		//        southButtons.add(Box.createRigidArea(new Dimension(BUTTON_SPACER_SIZE*10, 0)));
		//                
		//        add(southButtons, BorderLayout.SOUTH);

	}
}

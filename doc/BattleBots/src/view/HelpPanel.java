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
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class HelpPanel extends JPanel{


	private static final long serialVersionUID = 1L;

	static final int FONT_SIZE = 72;
        static final int BUTTON_SPACER_SIZE = 70;
        static final int BUTTON_WIDTH = 200;
        static final int BUTTON_HEIGHT = 200;

	public HelpPanel(int width, int height, ActionListener actionlistener) {
        setSize(width, height);
	setBackground(Color.DARK_GRAY);
        setLayout(new BorderLayout());
        add(Box.createRigidArea(new Dimension(0, height / 5)));
        JLabel title = new JLabel("Help" );
        title.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        title.setForeground(Color.BLACK);
        add(title, BorderLayout.NORTH);
        
        JLabel rules = new JLabel("<html>•With two players, red and green are used, and the board has 5 spaces on a side.  With three players, red<br> and yellow \n" +
"and blue are used, and the board can have 5 or 7 spaces on a side.  With six players, all colours<br> are used, and the board \n" +
"has 7 spaces on a side.  The colours are assigned randomly.\n<br>" +
"•All robots enter in their home space, facing along the arrow.\n<br>" +
"•Any number of robots, including ones from opposing teams, can occupy the same space.\n<br>" +
"•A turn begins with Red team playing its robot with greatest movement; then orange plays its robot with<br> greatest \n" +
"movement, ..., finally blue plays its greatest-movement robot — this completes one \n" +
"round.<br>" +
" Then a second round is played where Red plays its next-highest movement robot, then orange, ..., up to<br> blue.  Then a third round where Red’s \n" +
"lowest-movement robot plays, ..., finally blue’s lowest-movement<br> robot plays.  Thus, every robot gets one play per \n" +
"turn, and fastest robots move first.\n<br>" +
"•Note that, Red’s highest-movement robot might not be its scout, because that robot might be dead.\n<br>" +
"•If a team does not have a robot to play during a round, skip to the next colour.\n<br>" +
"•A play consists of moving, shooting, and moving again. It costs no movement point to turn to face any<br> direction (0—5), \n" +
"and one movement point to enter a space.  If a robot runs out of movement points, it<br> cannot move any further during \n" +
"that turn.  Different robots have different movement amounts.  At the end<br> of every turn, robots regenerate their \n" +
"movement points—i.e. they can move again next turn.\n<br>" +
"•Robots can only see and shoot at robots in range.  As they move, new spaces become visible.\n<br>" +
"•Shooting at a space means selecting a range and direction causes the shooting-robot’s attack-rating<br> to be subtracted \n" +
"from the health of all robots in that targeted space.  If a robot’s health drops to (or below)<br> zero, the robot is dead and \n" +
"removed from play.  Until a robot is dead, its movement and damage ratings are<br> unimpaired.  Note that range=0, \n" +
"direction=0 is valid.\n<br>" +
"•If more than one team’s robots are still alive, another turn starts with Red’s highest-movement robot.\n<br>" +
"•If only one team’s robots are still alive, that team wins the game</html>");
        rules.setFont(new Font("Arial", Font.BOLD, FONT_SIZE/4));
        rules.setForeground(Color.BLACK);
        add(rules, BorderLayout.CENTER);

        
        JPanel southButtons = new JPanel();      
        southButtons.setBackground(Color.DARK_GRAY);
        southButtons.setLayout(new BoxLayout(southButtons, BoxLayout.LINE_AXIS));
        southButtons.add(Box.createRigidArea(new Dimension(BUTTON_SPACER_SIZE, 0)));
        
        JButton quitButton = new JButton("Quit");
        quitButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        quitButton.setBackground(Color.BLACK);
        quitButton.setForeground(Color.WHITE);
        quitButton.setActionCommand("quit");
        quitButton.addActionListener(actionlistener);
        quitButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        southButtons.add(quitButton);  
        southButtons.add(Box.createRigidArea(new Dimension(BUTTON_SPACER_SIZE, 0)));
        
        JButton backButton = new JButton("Back");
        backButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setActionCommand("backGP");
        backButton.addActionListener(actionlistener);
        backButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        southButtons.add(backButton);
        southButtons.add(Box.createRigidArea(new Dimension(BUTTON_SPACER_SIZE*10, 0)));
                
        add(southButtons, BorderLayout.SOUTH);

	}
}

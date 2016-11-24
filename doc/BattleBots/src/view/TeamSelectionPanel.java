package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TeamSelectionPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	static final int FONT_SIZE = 72;
	static final int CHECK_SIZE = 32;
	static final int BUTTON_SPACER_SIZE = 300;
	static final int VERTICAL_SPACER = 50;

	public TeamSelectionPanel(int width, int height, ActionListener listener) {

		setSize(width, height);
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		JPanel title = new JPanel();
		title.setBackground(Color.WHITE);
		title.setLayout(new BoxLayout(title, BoxLayout.PAGE_AXIS)); 

		JLabel label = new JLabel("Team Selection " );
		label.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE));
		label.setForeground(Color.BLACK);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER)));
		title.add(label);
		title.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER)));
		add(title, BorderLayout.NORTH);
		
		JPanel checkBoxPanel = new JPanel();
		checkBoxPanel.setBackground(Color.WHITE);
		checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.PAGE_AXIS));

		JCheckBox team1 = new JCheckBox(" Team 1");
    	team1.setFont(new Font("Rockwell",Font.PLAIN, CHECK_SIZE));
    	team1.setForeground(Color.RED);
    	team1.setBackground(Color.BLACK);
    	team1.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
		checkBoxPanel.add(team1);
		checkBoxPanel.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER)));
		
		JCheckBox team2 = new JCheckBox(" Team 2");
    	team2.setFont(new Font("Rockwell",Font.PLAIN, CHECK_SIZE));
    	team2.setForeground(Color.ORANGE);
    	team2.setBackground(Color.BLACK);
    	team2.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
		checkBoxPanel.add(team2);
		checkBoxPanel.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER)));
		
		JCheckBox team3 = new JCheckBox(" Team 3");
    	team3.setFont(new Font("Rockwell",Font.PLAIN, CHECK_SIZE));
    	team3.setForeground(Color.YELLOW);
    	team3.setBackground(Color.BLACK);
    	team3.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
		checkBoxPanel.add(team3);
		checkBoxPanel.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER)));
		
		JCheckBox team4 = new JCheckBox(" Team 4");
    	team4.setFont(new Font("Rockwell",Font.PLAIN, CHECK_SIZE));
    	team4.setForeground(Color.GREEN);
    	team4.setBackground(Color.BLACK);
    	team4.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
		checkBoxPanel.add(team4);
		checkBoxPanel.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER)));
		
		JCheckBox team5 = new JCheckBox(" Team 5");
    	team5.setFont(new Font("Rockwell",Font.PLAIN, CHECK_SIZE));
    	team5.setForeground(Color.BLUE);
    	team5.setBackground(Color.BLACK);
    	team5.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
		checkBoxPanel.add(team5);
		checkBoxPanel.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER)));
		
		JCheckBox team6 = new JCheckBox(" Team 6");
    	team6.setFont(new Font("Rockwell",Font.PLAIN, CHECK_SIZE));
    	team6.setForeground(Color.MAGENTA);
    	team6.setBackground(Color.BLACK);
    	team6.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
		checkBoxPanel.add(team6);
		checkBoxPanel.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER)));

		add(checkBoxPanel, BorderLayout.CENTER);
		
		
		JPanel southButtons = new JPanel();      
		southButtons.setBackground(Color.WHITE);
		southButtons.setLayout(new BoxLayout(southButtons, BoxLayout.X_AXIS));
		southButtons.add(Box.createHorizontalGlue());

    	JButton continueButton = new JButton("Continue");
    	continueButton.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE));
    	continueButton.setBackground(Color.BLACK);
    	continueButton.setForeground(Color.WHITE);
    	continueButton.setActionCommand("continueTS");
    	continueButton.addActionListener(listener);
    	continueButton.setAlignmentY(Component.CENTER_ALIGNMENT);
    	continueButton.setFocusPainted(false);
    	
    	southButtons.add(continueButton); 
    	southButtons.add(Box.createHorizontalGlue());      	
    	            
    	JButton backButton = new JButton(" Back ");
    	backButton.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE));
    	backButton.setBackground(Color.BLACK);
    	backButton.setForeground(Color.WHITE);
    	backButton.setActionCommand("backTS");
    	backButton.addActionListener(listener);
    	backButton.setAlignmentY(Component.CENTER_ALIGNMENT);
    	backButton.setFocusPainted(false);
    	
    	southButtons.add(backButton);
    	southButtons.add(Box.createHorizontalGlue());
    	
		JButton quitButton = new JButton(" Quit ");
		quitButton.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE));
		quitButton.setBackground(Color.BLACK);
		quitButton.setForeground(Color.WHITE);
		quitButton.setActionCommand("quit");
		quitButton.addActionListener(listener);
		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		quitButton.setFocusPainted(false);

		southButtons.add(quitButton);
		southButtons.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER*7)));  
		southButtons.add(Box.createHorizontalGlue());
		add(southButtons, BorderLayout.SOUTH);
	}
}

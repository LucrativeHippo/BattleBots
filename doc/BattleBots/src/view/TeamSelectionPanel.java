package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TeamSelectionPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	static final int FONT_SIZE = 72;
	static final int CHECK_SIZE = 32;
	static final int BUTTON_SPACER_SIZE = 300;
	static final int VERTICAL_SPACER = 50;

	public TeamSelectionPanel(int width, int height, ActionListener listener) {

		setSize(width, height);
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel title = new JPanel();
		title.setBackground(Color.WHITE);
		title.setLayout(new BoxLayout(title, BoxLayout.PAGE_AXIS)); 

		JLabel label = new JLabel("Team Selection " );
		label.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE));
		label.setForeground(Color.BLACK);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		//title.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER)));
		title.add(label);
		title.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER)));
		add(title);
                
                JPanel upperRow = new JPanel();
                upperRow.setBackground(Color.WHITE);
                upperRow.setLayout(new BoxLayout(upperRow, BoxLayout.X_AXIS)); 
		
                JPanel teamStats = new JPanel();
                teamStats.setBackground(Color.GRAY);
                teamStats.setLayout(new GridLayout(11,11));
                teamStats.setBorder(BorderFactory.createLineBorder(Color.black));
                
                JLabel team = new JLabel("   Team");
                team.setFont(new Font("Rockwell",Font.BOLD, FONT_SIZE/6));
                team.setForeground(Color.BLACK);
                team.setBorder(BorderFactory.createLineBorder(Color.black));
                teamStats.add(team);
              
                JLabel classLabel = new JLabel("   Class");
                classLabel.setFont(new Font("Rockwell",Font.BOLD, FONT_SIZE/6));
                classLabel.setForeground(Color.BLACK);
                classLabel.setBorder(BorderFactory.createLineBorder(Color.black));
                teamStats.add(classLabel);
                   
               
                JLabel name = new JLabel("   Name");
                name.setFont(new Font("Rockwell",Font.BOLD, FONT_SIZE/6));
                name.setForeground(Color.BLACK);
                name.setBorder(BorderFactory.createLineBorder(Color.black));
                teamStats.add(name);
                
                JLabel matches = new JLabel("   Matches");
                matches.setFont(new Font("Rockwell",Font.BOLD, FONT_SIZE/6));
                matches.setForeground(Color.BLACK);
                matches.setBorder(BorderFactory.createLineBorder(Color.black));
                teamStats.add(matches);
                
                JLabel wins = new JLabel("   Wins");
                wins.setFont(new Font("Rockwell",Font.BOLD, FONT_SIZE/6));
                wins.setForeground(Color.BLACK);
                wins.setBorder(BorderFactory.createLineBorder(Color.black));
                teamStats.add(wins);
                
                JLabel losses = new JLabel("   Losses");
                losses.setFont(new Font("Rockwell",Font.BOLD, FONT_SIZE/6));
                losses.setForeground(Color.BLACK);
                losses.setBorder(BorderFactory.createLineBorder(Color.black));
                teamStats.add(losses);
                
                JLabel executions = new JLabel("   Executions");
                executions.setFont(new Font("Rockwell",Font.BOLD, FONT_SIZE/6));
                executions.setForeground(Color.BLACK);
                executions.setBorder(BorderFactory.createLineBorder(Color.black));
                teamStats.add(executions);
                
                JLabel lived = new JLabel("   Lived");
                lived.setFont(new Font("Rockwell",Font.BOLD, FONT_SIZE/6));
                lived.setForeground(Color.BLACK);
                lived.setBorder(BorderFactory.createLineBorder(Color.black));
                teamStats.add(lived);
                
                JLabel died = new JLabel("   Died");
                died.setFont(new Font("Rockwell",Font.BOLD, FONT_SIZE/6));
                died.setForeground(Color.BLACK);
                died.setBorder(BorderFactory.createLineBorder(Color.black));
                teamStats.add(died);
                
                JLabel absorbed = new JLabel("   Absorbed");
                absorbed.setFont(new Font("Rockwell",Font.BOLD, FONT_SIZE/6));
                absorbed.setForeground(Color.BLACK);
                absorbed.setBorder(BorderFactory.createLineBorder(Color.black));
                teamStats.add(absorbed);
                
                JLabel killed = new JLabel("   Killed");
                killed.setFont(new Font("Rockwell",Font.BOLD, FONT_SIZE/6));
                killed.setForeground(Color.BLACK);
                killed.setBorder(BorderFactory.createLineBorder(Color.black));
                teamStats.add(killed);
                
                JLabel moved = new JLabel("   Moved");
                moved.setFont(new Font("Rockwell",Font.BOLD, FONT_SIZE/6));
                moved.setForeground(Color.BLACK);
                moved.setBorder(BorderFactory.createLineBorder(Color.black));
                teamStats.add(moved);
               
                for(int i = 0; i < 11; i++){
                    for(int j = 1; j<11;j++){
                        teamStats.add(new JLabel("        " + i));
                    }
                }
               
                
                upperRow.add(Box.createHorizontalGlue());
                upperRow.add(teamStats);
                upperRow.add(Box.createHorizontalGlue());
                upperRow.add(Box.createHorizontalGlue());
                
                JPanel upperRight = new JPanel();
                upperRight.setBackground(Color.GRAY);
                upperRight.setLayout(new BoxLayout(upperRight, BoxLayout.X_AXIS));
                upperRight.setBorder(BorderFactory.createLineBorder(Color.black));
                
                JPanel checkBoxPanel = new JPanel();
		checkBoxPanel.setBackground(Color.GRAY);
		checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.PAGE_AXIS));
                checkBoxPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                checkBoxPanel.add(Box.createVerticalGlue());
                
		JCheckBox team1 = new JCheckBox("New Team");
                team1.setFont(new Font("Rockwell",Font.PLAIN, CHECK_SIZE/2));
                team1.setForeground(Color.BLACK);
                team1.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
		checkBoxPanel.add(team1);
                checkBoxPanel.add(Box.createVerticalGlue());
                ScrollPane pane = new ScrollPane();
                pane.add(checkBoxPanel);
                
                JPanel lb = new JPanel();
		lb.setBackground(Color.GRAY);
		lb.setLayout(new BoxLayout(lb, BoxLayout.PAGE_AXIS));
                lb.setBorder(BorderFactory.createLineBorder(Color.black));
                lb.add(Box.createVerticalGlue());
                
                JPanel one = new JPanel();
		one.setBackground(Color.GRAY);
		one.setLayout(new FlowLayout());
                JTextField tn = new JTextField(10);
                tn.setBorder(BorderFactory.createLineBorder(Color.black));
                JLabel TN = new JLabel("Team name: ");
                TN.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE/6));
                TN.setForeground(Color.BLACK);
                one.add(TN);
                one.add(tn);
                
                JPanel two = new JPanel();
		two.setBackground(Color.GRAY);
		two.setLayout(new FlowLayout());
                JTextField tn1 = new JTextField(10);
                tn1.setBorder(BorderFactory.createLineBorder(Color.black));
                JLabel TN1 = new JLabel("Scout name: ");
                TN1.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE/6));
                TN1.setForeground(Color.BLACK);
                two.add(TN1);
                two.add(tn1);
                
                JPanel three = new JPanel();
		three.setBackground(Color.GRAY);
		three.setLayout(new FlowLayout());
                JTextField tn2 = new JTextField(10);
                tn2.setBorder(BorderFactory.createLineBorder(Color.black));
                JLabel TN2 = new JLabel("Sniper name: ");
                TN2.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE/6));
                TN2.setForeground(Color.BLACK);
                three.add(TN2);
                three.add(tn2);
                               
                JPanel four = new JPanel();
		four.setBackground(Color.GRAY);
		four.setLayout(new FlowLayout());
                JTextField tn3 = new JTextField(10);
                tn3.setBorder(BorderFactory.createLineBorder(Color.black));
                JLabel TN3 = new JLabel("Tank name: ");
                TN3.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE/6));
                TN3.setForeground(Color.BLACK);
                four.add(TN3);
                four.add(tn3);
                             
                JPanel lbButtons = new JPanel();      
		lbButtons.setBackground(Color.GRAY);
		lbButtons.setLayout(new FlowLayout());
		//lbButtons.add(Box.createHorizontalGlue());

    	JButton ATButton = new JButton("Add Team");
    	ATButton.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE/6));
    	ATButton.setBackground(Color.BLACK);
    	ATButton.setForeground(Color.WHITE);
    	ATButton.setActionCommand("AddTeam");
    	ATButton.addActionListener(listener);
    	//ATButton.setAlignmentY(Component.CENTER_ALIGNMENT);
    	ATButton.setFocusPainted(false);
    	
    	lbButtons.add(ATButton); 
    	//lbButtons.add(Box.createHorizontalGlue());      	
    	            
    	JButton updateButton = new JButton("Update");
    	updateButton.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE/6));
    	updateButton.setBackground(Color.BLACK);
    	updateButton.setForeground(Color.WHITE);
    	updateButton.setActionCommand("update");
    	updateButton.addActionListener(listener);
    	updateButton.setAlignmentY(Component.CENTER_ALIGNMENT);
    	updateButton.setFocusPainted(false);
    	
    	lbButtons.add(updateButton);
    	//lbButtons.add(Box.createHorizontalGlue());
    	
		JButton retireButton = new JButton("Retire");
		retireButton.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE/6));
		retireButton.setBackground(Color.BLACK);
		retireButton.setForeground(Color.WHITE);
		retireButton.setActionCommand("retire");
		retireButton.addActionListener(listener);
		retireButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		retireButton.setFocusPainted(false);
                
                lbButtons.add(retireButton);
                //lbButtons.add(Box.createHorizontalGlue());
                
                lb.add(one);
                lb.add(two);
                lb.add(three);
                lb.add(four);
                lb.add(lbButtons);               

                
                upperRight.add(pane);
                upperRight.add(lb);
                upperRow.add(upperRight);
                upperRow.add(Box.createHorizontalGlue());
                add(upperRow);
                		
                JPanel lowerRow = new JPanel();
                lowerRow.setBackground(Color.WHITE);
                lowerRow.setLayout(new BoxLayout(lowerRow, BoxLayout.X_AXIS));
		lowerRow.add(Box.createHorizontalGlue());

                JPanel teamsToFight = new JPanel();
                teamsToFight.setBackground(Color.GRAY);
                teamsToFight.setBorder(BorderFactory.createLineBorder(Color.black));
                teamsToFight.setLayout(new BoxLayout(teamsToFight, BoxLayout.PAGE_AXIS));
                
                JLabel TTF = new JLabel("Select teams to fight");
                TTF.setFont(new Font("Rockwell",Font.BOLD, FONT_SIZE/6));
                TTF.setForeground(Color.BLACK);
                teamsToFight.add(TTF);
                
                JPanel teams= new JPanel();
                teams.setBackground(Color.GRAY);
                teams.setLayout(new BoxLayout(teams, BoxLayout.PAGE_AXIS));
                
                JCheckBox sample = new JCheckBox("Sample Team");
            	sample.setFont(new Font("Rockwell",Font.PLAIN, CHECK_SIZE/2));
            	sample.setForeground(Color.BLACK);
        	sample.setAlignmentX(Component.CENTER_ALIGNMENT);
                teams.add(sample);
                
                ScrollPane pane1 = new ScrollPane();
                pane1.add(teams);
                
                teamsToFight.add(pane1);
                lowerRow.add(teamsToFight);
                lowerRow.add(Box.createHorizontalGlue());
                lowerRow.add(Box.createHorizontalGlue());
                lowerRow.add(Box.createHorizontalGlue());
                
		JPanel southButtons = new JPanel();      
		southButtons.setBackground(Color.WHITE);
		southButtons.setLayout(new GridLayout(2,2,10,10));
		

    	JButton continueButton = new JButton("Continue");
    	continueButton.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE/2));
    	continueButton.setBackground(Color.BLACK);
    	continueButton.setForeground(Color.WHITE);
    	continueButton.setActionCommand("continueTS");
    	continueButton.addActionListener(listener);
    	continueButton.setAlignmentY(Component.CENTER_ALIGNMENT);
    	continueButton.setFocusPainted(false);
    	
    	southButtons.add(continueButton); 
    	//southButtons.add(Box.createHorizontalGlue());      	
    	            
    	JButton backButton = new JButton(" Back ");
    	backButton.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE/2));
    	backButton.setBackground(Color.BLACK);
    	backButton.setForeground(Color.WHITE);
    	backButton.setActionCommand("backTS");
    	backButton.addActionListener(listener);
    	backButton.setAlignmentY(Component.CENTER_ALIGNMENT);
    	backButton.setFocusPainted(false);
    	
    	southButtons.add(backButton);
    	//southButtons.add(Box.createHorizontalGlue());
    	
		JButton quitButton = new JButton(" Quit ");
		quitButton.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE/2));
		quitButton.setBackground(Color.BLACK);
		quitButton.setForeground(Color.WHITE);
		quitButton.setActionCommand("quit");
		quitButton.addActionListener(listener);
		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		quitButton.setFocusPainted(false);

		southButtons.add(quitButton);
                lowerRow.add(southButtons);
                lowerRow.add(Box.createHorizontalGlue());
		//southButtons.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER*7)));  
		add(Box.createVerticalGlue());
		add(lowerRow);
                add(Box.createVerticalGlue());
	}
}

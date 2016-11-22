package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class GamePropertiesPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	static final int FONT_SIZE = 72;
	static final int LABEL_FONT_SIZE = 32;
	static final int BUTTON_SPACER_SIZE = 300;
	static final int VERTICAL_SPACER = 50;
	static final int HORIZONTAL_SPACER = 110;

	public GamePropertiesPanel(int width, int height, ActionListener actionListener){
			
			setSize(width, height);
			setBackground(Color.WHITE);
        	setLayout(new BorderLayout());;
        	
        	JPanel title = new JPanel();
    		title.setBackground(Color.WHITE);
    		title.setLayout(new BoxLayout(title, BoxLayout.PAGE_AXIS)); 

    		JLabel label = new JLabel("Game Properties " );
    		label.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE));
    		label.setForeground(Color.BLACK);
    		label.setAlignmentX(Component.CENTER_ALIGNMENT);
    		title.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER)));
    		title.add(label);
    		title.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER)));
    		add(title, BorderLayout.NORTH);
    		
    		JPanel centerPanel = new JPanel();
    		centerPanel.setBackground(Color.WHITE);
    		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
    		centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
    		centerPanel.add(Box.createHorizontalGlue());
    		
    		JPanel teamPanel = new JPanel();
    		teamPanel.setBackground(Color.WHITE);
    		teamPanel.setLayout(new BoxLayout(teamPanel, BoxLayout.PAGE_AXIS));
    		teamPanel.add(Box.createRigidArea(new Dimension(0, 20)));
    		
      		JLabel teamlabel = new JLabel("How many teams will be playing?" );
    		teamlabel.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
    		teamlabel.setForeground(Color.BLACK);
    		teamlabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    		
    		teamPanel.add(teamlabel);
    		teamPanel.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER))); 
    		
    	    JRadioButton twoTeamsButton = new JRadioButton("2");
    	    twoTeamsButton.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
    	    twoTeamsButton.setActionCommand("twoTeams");
    	    twoTeamsButton.addActionListener(actionListener);
    	    

    	    JRadioButton threeTeamsButton = new JRadioButton("3");
    	    threeTeamsButton.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
    	    threeTeamsButton.setActionCommand("threeTeams");
    	    threeTeamsButton.addActionListener(actionListener);
    	    
    	    JRadioButton sixTeamsButton = new JRadioButton("6");
    	    sixTeamsButton.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
    	    sixTeamsButton.setActionCommand("sixTeams");
    	    sixTeamsButton.addActionListener(actionListener);
    		sixTeamsButton.setSelected(true);
    		
    	    ButtonGroup group = new ButtonGroup();
    	    group.add(twoTeamsButton);
    	    group.add(threeTeamsButton);
    	    group.add(sixTeamsButton);
    	    
    	    
    	    teamPanel.add(twoTeamsButton);
    	    teamPanel.add(threeTeamsButton);
    	    teamPanel.add(sixTeamsButton);
    	    
    	    centerPanel.add(teamPanel);
    	    centerPanel.add(Box.createHorizontalGlue());
    	    
    	    ////////////////////////////////////////////////////////////////
    	    
    		JPanel humansPanel = new JPanel();
    		humansPanel.setBackground(Color.WHITE);
    		humansPanel.setLayout(new BoxLayout(humansPanel, BoxLayout.PAGE_AXIS ));
    		humansPanel.add(Box.createRigidArea(new Dimension(0, 20)));
    		
      		JLabel humanlabel = new JLabel("How many humans will be playing?" );
    		humanlabel.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
    		humanlabel.setForeground(Color.BLACK);
    		humanlabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    		
    		humansPanel.add(humanlabel);
    		humansPanel.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER))); 
    		
    		
    	    JRadioButton zeroHumansButton = new JRadioButton("0 ( AI simulation) ");
    	    zeroHumansButton.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
    	    zeroHumansButton.setActionCommand("zeroHumans");
    	    zeroHumansButton.addActionListener(actionListener);
    		zeroHumansButton.setSelected(true);
       		humansPanel.add(Box.createHorizontalGlue()); 
    		humansPanel.add(zeroHumansButton);
    		
    		
    		JRadioButton oneHumanButton = new JRadioButton("1");
    	    oneHumanButton.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
    	    oneHumanButton.setActionCommand("oneHuman");
    	    oneHumanButton.addActionListener(actionListener);   
    	       		
    		JRadioButton twoHumansButton = new JRadioButton("2");
    	    twoHumansButton.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
    	    twoHumansButton.setActionCommand("twoHumans");
    	    twoHumansButton.addActionListener(actionListener);

    	    JRadioButton threeHumansButton = new JRadioButton("3");
    	    threeHumansButton.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
    	    threeHumansButton.setActionCommand("threeHumans");
    	    threeHumansButton.addActionListener(actionListener);
    	    
    	    JRadioButton fourHumansButton = new JRadioButton("4");
    	    fourHumansButton.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
    	    fourHumansButton.setActionCommand("fourHumans");
    	    fourHumansButton.addActionListener(actionListener);
    	    
    	    JRadioButton fiveHumansButton = new JRadioButton("5");
    	    fiveHumansButton.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
    	    fiveHumansButton.setActionCommand("fiveHumans");
    	    fiveHumansButton.addActionListener(actionListener);
    	    
    	    JRadioButton sixHumansButton = new JRadioButton("6");
    	    sixHumansButton.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
    	    sixHumansButton.setActionCommand("sixHumans");
    	    sixHumansButton.addActionListener(actionListener);
    	    
    	    JPanel halfHumanButtons = new JPanel();
    		halfHumanButtons.setBackground(Color.WHITE);
    		halfHumanButtons.setLayout(new BoxLayout(halfHumanButtons, BoxLayout.X_AXIS ));
    		
    		JPanel halfHumanButtons1 = new JPanel();
    		halfHumanButtons1.setBackground(Color.WHITE);
    		halfHumanButtons1.setLayout(new BoxLayout(halfHumanButtons1, BoxLayout.PAGE_AXIS ));
    			
    		JPanel halfHumanButtons2 = new JPanel();
    		halfHumanButtons2.setBackground(Color.WHITE);
    		halfHumanButtons2.setLayout(new BoxLayout(halfHumanButtons2, BoxLayout.PAGE_AXIS ));
    	
    		
    		
    		
    	    ButtonGroup group1 = new ButtonGroup();
    	    group1.add(oneHumanButton);
    	    group1.add(threeHumansButton);
    	    group1.add(fiveHumansButton);
    	    group1.add(zeroHumansButton);
    	    group1.add(twoHumansButton);
    	    group1.add(fourHumansButton);
    	    group1.add(sixHumansButton);
    	     	    
    	    halfHumanButtons1.add(oneHumanButton);    	   
    	    halfHumanButtons1.add(threeHumansButton);    
    	    halfHumanButtons1.add(fiveHumansButton);
    	    
    	    halfHumanButtons2.add(twoHumansButton);
    	    halfHumanButtons2.add(fourHumansButton);
    	    halfHumanButtons2.add(sixHumansButton);
    	    
    	    halfHumanButtons.add(Box.createRigidArea(new Dimension(HORIZONTAL_SPACER, 0))); 
    	    halfHumanButtons.add(halfHumanButtons1);
    	    halfHumanButtons.add(halfHumanButtons2);
    	    halfHumanButtons.add(Box.createHorizontalGlue());
    	    
    	    humansPanel.add(halfHumanButtons);
    	    
    	    centerPanel.add(humansPanel);
    	    centerPanel.add(Box.createHorizontalGlue());
    	    
    	    
    	                

    		///////////////////////////////////////////////////////////////
    	    
    	    JPanel sizePanel = new JPanel();
    		sizePanel.setBackground(Color.WHITE);
    		sizePanel.setLayout(new BoxLayout(sizePanel, BoxLayout.PAGE_AXIS ));
    		sizePanel.add(Box.createRigidArea(new Dimension(0, 20)));
    		
      		JLabel sizelabel = new JLabel("Board length on each side?" );
    		sizelabel.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
    		sizelabel.setForeground(Color.BLACK);
    		sizelabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    		
    		sizePanel.add(sizelabel);
    		sizePanel.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER))); 
    		
    	    JRadioButton fiveButton = new JRadioButton("5");
    	    fiveButton.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
    	    fiveButton.setActionCommand("five");
    	    fiveButton.addActionListener(actionListener);
    	    fiveButton.setEnabled(false);
    		
    		
    		JRadioButton sevenButton = new JRadioButton("7");
    	    sevenButton.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
    	    sevenButton.setActionCommand("seven");
    	    sevenButton.addActionListener(actionListener);   
    	    sevenButton.setSelected(true);
    	    
    	    ButtonGroup group2 = new ButtonGroup();
    	    group2.add(fiveButton);
    	    group2.add(sevenButton);

    	    sizePanel.add(fiveButton);
    	    sizePanel.add(sevenButton);

    	    centerPanel.add(sizePanel);
    	    centerPanel.add(Box.createHorizontalGlue());

    	    // when user selects two players, the board size is automatically set to five 
    	    // hides options for more than 2 humans
    	    twoTeamsButton.addActionListener(new ActionListener() {

    	    	@Override
    	    	public void actionPerformed(ActionEvent ae) {
    	    		zeroHumansButton.setSelected(true);
    	    		threeHumansButton.setEnabled(false);
    	    		fourHumansButton.setEnabled(false);
    	    		fiveHumansButton.setEnabled(false);
    	    		sixHumansButton.setEnabled(false);
    	    		fiveButton.setEnabled(true);
    	    		fiveButton.setSelected(true);
    	    		sevenButton.setEnabled(false);
    	    	}
    	    });

    	    // when user selects three players, when user selects three players,  
    	    threeTeamsButton.addActionListener(new ActionListener() {

    	    	@Override
    	    	public void actionPerformed(ActionEvent ae) {
    	    		zeroHumansButton.setSelected(true);
    	    		threeHumansButton.setEnabled(true);
    	    		fourHumansButton.setEnabled(false);
    	    		fiveHumansButton.setEnabled(false);
    	    		sixHumansButton.setEnabled(false);
    	    		fiveButton.setEnabled(true);
    	    		fiveButton.setSelected(true);
                        sevenButton.setEnabled(true);
    	    	}
    	    });

    	    // when user selects six players, the board size is automatically set to seven 
    	    sixTeamsButton.addActionListener(new ActionListener() {

    	    	@Override
    	    	public void actionPerformed(ActionEvent ae) {
    	    		threeHumansButton.setEnabled(true);
    	    		fourHumansButton.setEnabled(true);
    	    		fiveHumansButton.setEnabled(true);
    	    		sixHumansButton.setEnabled(true);
    	    		fiveButton.setEnabled(false);
    	    		sevenButton.setEnabled(true);
    	    		sevenButton.setSelected(true);
    	    	}
    	    });
    	    //////////////////////////////////////////////////////////////
    	    add(centerPanel, BorderLayout.CENTER);

    		JPanel southButtons = new JPanel();      
    		southButtons.setBackground(Color.WHITE);
    		southButtons.setLayout(new BoxLayout(southButtons, BoxLayout.X_AXIS));
    		southButtons.add(Box.createHorizontalGlue());

        	JButton continueButton = new JButton("Continue");
        	continueButton.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE));
        	continueButton.setBackground(Color.BLACK);
        	continueButton.setForeground(Color.WHITE);
        	continueButton.setActionCommand("continueGP");
        	continueButton.addActionListener(actionListener);
        	continueButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        	continueButton.setFocusPainted(false);
        	
        	southButtons.add(continueButton); 
        	southButtons.add(Box.createHorizontalGlue());      	
        	            
        	JButton backButton = new JButton(" Back ");
        	backButton.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE));
        	backButton.setBackground(Color.BLACK);
        	backButton.setForeground(Color.WHITE);
        	backButton.setActionCommand("backGP");
        	backButton.addActionListener(actionListener);
        	backButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        	backButton.setFocusPainted(false);
        	
        	southButtons.add(backButton);
        	southButtons.add(Box.createHorizontalGlue());
        	
    		JButton quitButton = new JButton(" Quit ");
    		quitButton.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE));
    		quitButton.setBackground(Color.BLACK);
    		quitButton.setForeground(Color.WHITE);
    		quitButton.setActionCommand("quit");
    		quitButton.addActionListener(actionListener);
    		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    		quitButton.setFocusPainted(false);

    		southButtons.add(quitButton);
    		southButtons.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER*7)));  
    		southButtons.add(Box.createHorizontalGlue());
    		add(southButtons, BorderLayout.SOUTH);

	}
}
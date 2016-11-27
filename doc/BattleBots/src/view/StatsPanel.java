package view;

import static controller.GameController.gameBoard;
import controller.Interpreter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import model.GameInfo;

public class StatsPanel extends JPanel{

	private static final long serialVersionUID = 1L;

                static final int FONT_SIZE = 72;
                static final int LABEL_FONT_SIZE = 32;
                static final int BUTTON_SPACER_SIZE = 300;
                static final int VERTICAL_SPACER = 50;


	public StatsPanel(int width, int height, ActionListener listener) {
            
                setSize(width, height);
		setBackground(Color.WHITE);
        	setLayout(new BorderLayout());
                
                
                JPanel title = new JPanel();
    		title.setBackground(Color.WHITE);
    		title.setLayout(new BoxLayout(title, BoxLayout.PAGE_AXIS)); 

    		JLabel label = new JLabel("Final Statistics" );
    		label.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE));
    		label.setForeground(Color.BLACK);
    		label.setAlignmentX(Component.CENTER_ALIGNMENT);
    		title.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER)));
    		title.add(label);
    		title.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER)));
    		add(title, BorderLayout.NORTH);
                
                
                JPanel centerPanel = new JPanel();
    		centerPanel.setBackground(Color.WHITE);
                centerPanel.setLayout(new GridLayout(8,7));

                
                JLabel teamLabel = new JLabel("Team");
                teamLabel.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
                teamLabel.setForeground(Color.BLACK);
                
                JLabel wins = new JLabel("Wins");
                wins.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
                wins.setForeground(Color.BLACK);
                
                JLabel losses = new JLabel("Losses");
                losses.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
                losses.setForeground(Color.BLACK);
                
                JLabel ties = new JLabel("Ties");
                ties.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
                ties.setForeground(Color.BLACK);
                
                JLabel scoutKills = new JLabel("Scout Kills");
                scoutKills.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
                scoutKills.setForeground(Color.BLACK);
                
                JLabel sniperKills = new JLabel("Sniper Kills");
                sniperKills.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
                sniperKills.setForeground(Color.BLACK);
                
                JLabel tankKills = new JLabel("Tank Kills");
                tankKills.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
                tankKills.setForeground(Color.BLACK);

                centerPanel.add(teamLabel);
                centerPanel.add(wins);
                centerPanel.add(losses);
                centerPanel.add(ties);
                centerPanel.add(scoutKills);
                centerPanel.add(sniperKills);
                centerPanel.add(tankKills);
                
                centerPanel.setPreferredSize(new Dimension(1400,800));
                
                JPanel mainPanel = new JPanel();
                mainPanel.add(centerPanel, BorderLayout.CENTER);
                
                
                JButton playAgain = new JButton("Play Again");
        	playAgain.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE));
        	playAgain.setBackground(Color.BLACK);
        	playAgain.setForeground(Color.WHITE);
        	playAgain.setActionCommand("backGP");
        	playAgain.addActionListener(listener);
        	playAgain.setAlignmentY(Component.CENTER_ALIGNMENT);
        	playAgain.setFocusPainted(false);
                
                JPanel southPanel = new JPanel();
                southPanel.add(Box.createRigidArea(new Dimension(BUTTON_SPACER_SIZE, 0)));
                southPanel.add(playAgain);
                southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
    		southPanel.add(Box.createHorizontalGlue());
                add(southPanel, BorderLayout.SOUTH);
                
                
                JButton quit = new JButton("Quit");
                quit.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE));
        	quit.setBackground(Color.BLACK);
        	quit.setForeground(Color.WHITE);
        	quit.setActionCommand("quit");
        	quit.addActionListener(listener);
        	quit.setAlignmentY(Component.CENTER_ALIGNMENT);
        	quit.setFocusPainted(false);
                
                southPanel.add(quit);
                southPanel.add(Box.createRigidArea(new Dimension(BUTTON_SPACER_SIZE, 0)));
                southPanel.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER*7))); 

                Interpreter interpret = new Interpreter();
                while (!gameBoard.robotList.isEmpty()){
                    while(gameBoard.robotList.peek().isHuman()){
                        gameBoard.robotList.pop();
                    }
                    JLabel team = new JLabel("Robot");
                    team.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
                    team.setForeground(Color.BLACK);
                    
                    JLabel teamWins = new JLabel(Integer.toString(interpret.getRobotWins(gameBoard.robotList.peek().getCode())));
                    teamWins.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
                    teamWins.setForeground(Color.BLACK);
                    
                    JLabel teamLosses = new JLabel("0");
                    teamLosses.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
                    teamLosses.setForeground(Color.BLACK);
                    
                    JLabel teamTies = new JLabel("0");
                    teamTies.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
                    teamTies.setForeground(Color.BLACK);
                    
                    JLabel teamScoutKills = new JLabel("0");
                    teamScoutKills.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
                    teamScoutKills.setForeground(Color.BLACK);
                    
                    JLabel teamSniperKills = new JLabel("0");
                    teamSniperKills.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
                    teamSniperKills.setForeground(Color.BLACK);
                    
                    JLabel teamTankKills = new JLabel("0");
                    teamTankKills.setFont(new Font("Rockwell",Font.PLAIN, LABEL_FONT_SIZE));
                    teamTankKills.setForeground(Color.BLACK);
                    
                    centerPanel.add(team);
                    centerPanel.add(teamWins);
                    centerPanel.add(teamLosses);
                    centerPanel.add(teamTies);
                    centerPanel.add(teamScoutKills);
                    centerPanel.add(teamSniperKills);
                    centerPanel.add(teamTankKills);
                    
             
                }
                
           
                add(mainPanel);
                
                

	}
}

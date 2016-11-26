package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameInfo;

public class OverallGamePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	static final int FONT_SIZE = 72;
	static final int BUTTON_SPACER_SIZE = 300;
	public OverallGamePanel(int width, int height, ActionListener alistener, KeyListener klistener, GameInfo gameinfo){
		
		GamePanel gamepanel = new GamePanel(height, height, alistener, klistener, gameinfo);
		gamepanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		gamepanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		setSize(width, height);
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		
		JPanel southButtons = new JPanel();      
		southButtons.setBackground(Color.WHITE);
		southButtons.setLayout(new BoxLayout(southButtons, BoxLayout.PAGE_AXIS));
		southButtons.add(Box.createVerticalGlue());
		southButtons.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		/*JLabel labe = new JLabel("                         " );
		labe.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE));
		labe.setForeground(Color.BLACK);
		labe.setAlignmentX(Component.CENTER_ALIGNMENT);
		southButtons.add(labe);*/
		
		JButton helpButton = new JButton(" Help");
		helpButton.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE));
		helpButton.setBackground(Color.BLACK);
		helpButton.setForeground(Color.WHITE);
		helpButton.setActionCommand("help");
		helpButton.addActionListener(alistener);
		helpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		helpButton.setFocusPainted(false);
		southButtons.add(helpButton);
                
                JButton endTurnButton = new JButton(" End Turn");
                endTurnButton.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE));
                endTurnButton.setBackground(Color.BLACK);
		endTurnButton.setForeground(Color.WHITE);
		endTurnButton.setActionCommand("endTurn");
		endTurnButton.addActionListener(alistener);
		endTurnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		endTurnButton.setFocusPainted(false);
		southButtons.add(endTurnButton);
		
		JButton quitButton = new JButton(" Quit ");
		quitButton.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE));
		quitButton.setBackground(Color.BLACK);
		quitButton.setForeground(Color.WHITE);
		quitButton.setActionCommand("quit");
		quitButton.addActionListener(alistener);
		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		quitButton.setFocusPainted(false);
		southButtons.add(quitButton);
		southButtons.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel right = new JPanel();      
		right.setBackground(Color.WHITE);
		right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
		right.add(Box.createVerticalGlue());
		right.setAlignmentX(Component.RIGHT_ALIGNMENT);
		right.setBorder(BorderFactory.createLineBorder(Color.black));
		
		/*JLabel label = new JLabel("                         " );
		label.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE));
		label.setForeground(Color.BLACK);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		right.add(label);*/

		
		add(southButtons);
		add(Box.createRigidArea(new Dimension(BUTTON_SPACER_SIZE/4, 0)));
		add(gamepanel);
		add(right);
		
		
	}
}

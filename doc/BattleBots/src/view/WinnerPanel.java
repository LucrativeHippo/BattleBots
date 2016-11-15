package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WinnerPanel extends JPanel{
	private static final long serialVersionUID = 1L;

    static final int FONT_SIZE = 72;
    static final int BUTTON_SPACER_SIZE = 20;
    static final int BUTTON_WIDTH = 140;
    static final int BUTTON_HEIGHT = 30;

    //need actionlistener as parameter
	public WinnerPanel(int width, int height) {
		setSize(width, height);
        setBackground(Color.DARK_GRAY);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createRigidArea(new Dimension(0, height / 5)));
        JLabel label = new JLabel("VICTORY GOES TO PLAYER: " );
        label.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);
        add(Box.createRigidArea(new Dimension(0, height / 5)));
        
        add(Box.createRigidArea(new Dimension(0, height / 5)));
        JLabel label1 = new JLabel("CONGRATULATIONS!");
        label1.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        label1.setForeground(Color.BLACK);
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label1);
        add(Box.createRigidArea(new Dimension(0, height / 5)));
        
        JButton playAgainButton = new JButton("PLAY AGAIN");
        playAgainButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        playAgainButton.setBackground(Color.BLACK);
        playAgainButton.setForeground(Color.WHITE);
        playAgainButton.setActionCommand("playagain");
      //  playAgainButton.addActionListener(listener);
        playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(playAgainButton);

        add(Box.createRigidArea(new Dimension(0, BUTTON_SPACER_SIZE)));

        JButton statsButton = new JButton("STATS");
        statsButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        statsButton.setBackground(Color.BLACK);
        statsButton.setForeground(Color.WHITE);
        statsButton.setActionCommand("stats");
      //  statsButton.addActionListener(listener);
        statsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(statsButton);

        add(Box.createRigidArea(new Dimension(0, BUTTON_SPACER_SIZE)));

        JButton quitButton = new JButton("QUIT");
        quitButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        quitButton.setBackground(Color.BLACK);
        quitButton.setForeground(Color.WHITE);
        quitButton.setActionCommand("quit");
      //  quitButton.addActionListener(listener);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(quitButton);
	}
}

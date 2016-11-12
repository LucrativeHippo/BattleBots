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
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePropertiesPanel extends JPanel{
	private static final long serialVersionUID = 1L;
    static final int FONT_SIZE = 18;
    static final int BUTTON_SPACER_SIZE = 20;
    static final int BUTTON_WIDTH = 140;
    static final int BUTTON_HEIGHT = 30;

    //need actionlistener as parameter
	public GamePropertiesPanel(int width, int height) {
		setSize(width, height);
		setBackground(Color.DARK_GRAY);
        setLayout(new BorderLayout());
        add(Box.createRigidArea(new Dimension(0, height / 5)));
        JLabel label = new JLabel("Game Properties" );
        label.setFont(new Font("Arial", Font.BOLD, (int)(FONT_SIZE*2)));
        label.setForeground(Color.BLACK);
        add(label, BorderLayout.NORTH);

        
        JPanel southButtons = new JPanel();      
        southButtons.setBackground(Color.DARK_GRAY);
        southButtons.setLayout(new BoxLayout(southButtons, BoxLayout.LINE_AXIS));
        
        southButtons.add(Box.createRigidArea(new Dimension(BUTTON_SPACER_SIZE, 0)));
        
        JButton quitButton = new JButton("Quit");
        quitButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        quitButton.setBackground(Color.BLACK);
        quitButton.setForeground(Color.WHITE);
        quitButton.setActionCommand("quit");
        //quitButton.addActionListener(listener);
        quitButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        southButtons.add(quitButton);
        
        southButtons.add(Box.createRigidArea(new Dimension(BUTTON_SPACER_SIZE, 0)));
        
        JButton backButton = new JButton("Back");
        backButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setActionCommand("back");
      //  backButton.addActionListener(listener);
        backButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        southButtons.add(backButton);

        southButtons.add(Box.createRigidArea(new Dimension(BUTTON_SPACER_SIZE*10, 0)));
                
        JButton continueButton = new JButton("Continue");
        continueButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        continueButton.setBackground(Color.BLACK);
        continueButton.setForeground(Color.WHITE);
        continueButton.setActionCommand("continue");
      //  continueButton.addActionListener(listener);
        continueButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        southButtons.add(continueButton);
        
        southButtons.add(Box.createRigidArea(new Dimension(0, BUTTON_SPACER_SIZE*10)));
        
        add(southButtons, BorderLayout.SOUTH);

	}
}

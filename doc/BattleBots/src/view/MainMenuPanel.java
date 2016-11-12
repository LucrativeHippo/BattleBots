package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel{

	private static final long serialVersionUID = 1L;


    static final int FONT_SIZE = 72;
    static final int BUTTON_SPACER_SIZE = 30;
    static final int BUTTON_WIDTH = 140;
    static final int BUTTON_HEIGHT = 30;
    private BufferedImage image;
    
    //need actionlistener as parameter
	public MainMenuPanel(int width, int height) {
		try {
			image = ImageIO.read(new File("/student/mdk181/gameBoard.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setSize(width, height);
        setBackground(Color.DARK_GRAY);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createRigidArea(new Dimension(0, height / 5)));
        JLabel label = new JLabel("Welcome to Battle Bots! " );
        label.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);
        add(Box.createRigidArea(new Dimension(0, height / 10)));
        
        JLabel imageLabel = new JLabel(new ImageIcon(image.getScaledInstance(400, 300, Image.SCALE_SMOOTH)));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(imageLabel);
        add(Box.createRigidArea(new Dimension(0, height / 15)));
        
       
        JButton beginButton = new JButton("Begin");
        beginButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        beginButton.setBackground(Color.BLACK);
        beginButton.setForeground(Color.WHITE);
        beginButton.setActionCommand("begin");
      //  beginButton.addActionListener(listener);
        beginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(beginButton);

        add(Box.createRigidArea(new Dimension(0, BUTTON_SPACER_SIZE)));

        JButton helpButton = new JButton("Help");
        helpButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        helpButton.setBackground(Color.BLACK);
        helpButton.setForeground(Color.WHITE);
        helpButton.setActionCommand("help");
      //  helpButton.addActionListener(listener);
        helpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(helpButton);

        add(Box.createRigidArea(new Dimension(0, BUTTON_SPACER_SIZE)));

        JButton quitButton = new JButton("Quit");
        quitButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        quitButton.setBackground(Color.BLACK);
        quitButton.setForeground(Color.WHITE);
        quitButton.setActionCommand("quit");
      //  quitButton.addActionListener(listener);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(quitButton);
	}

}

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
    static final int BUTTON_SPACER_SIZE = 70;
    static final int BUTTON_WIDTH = 200;
    static final int BUTTON_HEIGHT = 200;
    private BufferedImage image;
    

	public MainMenuPanel(int width, int height, ActionListener actionlistener) {
		try {
			image = ImageIO.read(ClassLoader.getSystemResource("images/gameBoard.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setSize(width, height);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JPanel title = new JPanel();
        title.setBackground(Color.WHITE);
        title.setLayout(new BoxLayout(title, BoxLayout.PAGE_AXIS)); 
        
        JLabel label = new JLabel("Welcome to Battle Bots! " );
        label.setFont(new Font("Rockwell",Font.PLAIN, FONT_SIZE));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.add(Box.createRigidArea(new Dimension(0, height / 10)));
        title.add(label);
        title.add(Box.createRigidArea(new Dimension(0, height / 10)));
        add(title, BorderLayout.NORTH);
        
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.PAGE_AXIS));
        imagePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JLabel imageLabel = new JLabel(new ImageIcon(image.getScaledInstance(600, 500, Image.SCALE_SMOOTH)));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePanel.add(imageLabel);
        add(imagePanel, BorderLayout.CENTER);
        
        JPanel southButtons = new JPanel();      
        southButtons.setBackground(Color.WHITE);
        southButtons.setLayout(new BoxLayout(southButtons, BoxLayout.LINE_AXIS));
        southButtons.add(Box.createRigidArea(new Dimension(width, 0)));
        
        JButton beginButton = new JButton("Begin");
        beginButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        beginButton.setBackground(Color.BLACK);
        beginButton.setForeground(Color.WHITE);
        beginButton.setActionCommand("begin");
        beginButton.addActionListener(actionlistener);
        beginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        southButtons.add(beginButton);
        southButtons.add(Box.createRigidArea(new Dimension(BUTTON_SPACER_SIZE, 0)));

        JButton helpButton = new JButton("Help");
        helpButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        helpButton.setBackground(Color.BLACK);
        helpButton.setForeground(Color.WHITE);
        helpButton.setActionCommand("help");
        helpButton.addActionListener(actionlistener);
        helpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        southButtons.add(helpButton);
        southButtons.add(Box.createRigidArea(new Dimension(BUTTON_SPACER_SIZE, 0)));

        JButton quitButton = new JButton("Quit");
        quitButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        quitButton.setBackground(Color.BLACK);
        quitButton.setForeground(Color.WHITE);
        quitButton.setActionCommand("quit");
        quitButton.addActionListener(actionlistener);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        southButtons.add(quitButton);
        southButtons.add(Box.createRigidArea(new Dimension(0, BUTTON_SPACER_SIZE*5)));  
        add(southButtons, BorderLayout.SOUTH);
        
        

	}

}

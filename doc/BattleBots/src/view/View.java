package view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GameInfo;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int TITLE_BAR_HEIGHT = 32;
	public static final int BORDER_WIDTH = 6;




	public View(int width, int height) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width + BORDER_WIDTH, height + TITLE_BAR_HEIGHT);
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(true);
		getContentPane().setLayout(new BorderLayout());
	}

	public void switchDisplay(JPanel panel){
		getContentPane().removeAll();
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().repaint();
		setVisible(true);
	}

	public void showMainMenu(ActionListener actionlistener) {
		JPanel panel = new MainMenuPanel(getWidth(), getHeight(), actionlistener);
		switchDisplay(panel);
	}

	public void showHelp(ActionListener actionListener, int width, int height) {
		JFrame helpPopUp = new JFrame();
		JPanel panel = new HelpPanel(getWidth(), getHeight(), actionListener);
		helpPopUp.setSize(width + BORDER_WIDTH, height + TITLE_BAR_HEIGHT);
		helpPopUp. setResizable(true);
		helpPopUp.getContentPane().setLayout(new BorderLayout());
		helpPopUp.getContentPane().add(panel, BorderLayout.CENTER);
		helpPopUp.getContentPane().validate();
		helpPopUp.setVisible(true);
	}

	public void showGameProperties(ActionListener actionlistener) {
		JPanel panel = new GamePropertiesPanel(getWidth(), getHeight(),actionlistener);
		switchDisplay(panel);
	}

	public void showWinner(ActionListener actionlistener) {
		JPanel panel = new WinnerPanel(getWidth(), getHeight(), actionlistener);
		switchDisplay(panel);
	}

	public void showGame(ActionListener actionlistener, KeyListener keyListener, GameInfo gameInfo) {
		JPanel panel = new OverallGamePanel(getWidth(), getHeight(), actionlistener, keyListener, gameInfo);
		switchDisplay(panel);
	}

	public void showTeamSelection(ActionListener actionListener) {
		JPanel panel = new TeamSelectionPanel(getWidth(), getHeight(), actionListener);
		switchDisplay(panel);
	}

	public void showStats(ActionListener actionListener) {
		JPanel panel = new StatsPanel(getWidth(), getHeight(), actionListener);
		switchDisplay(panel);
	}



}

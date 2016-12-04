package view;

import static controller.GameController.gameBoard;
import static controller.GameController.gameController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameInfo;
import model.Robot;
import static view.TeamSelectionPanel.VERTICAL_SPACER;

public final class OverallGamePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    static final int FONT_SIZE = 72;
    static final int BUTTON_SPACER_SIZE = 300;
    static Iterator<Robot> turn;
    static Iterator<Robot> turn2;

    public OverallGamePanel(int width, int height, ActionListener alistener, KeyListener klistener, GameInfo gameinfo) throws NoSuchMethodException {
        GamePanel gamepanel = new GamePanel(height, height, alistener, klistener, gameinfo);
        gamepanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamepanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        setSize(width, height);
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel southButtons = new JPanel();
        southButtons.setBackground(Color.WHITE);
        southButtons.setLayout(new BoxLayout(southButtons, BoxLayout.PAGE_AXIS));
        southButtons.add(Box.createVerticalGlue());
        southButtons.setAlignmentX(Component.LEFT_ALIGNMENT);

//        Iterator<Robot> iterate = gameController.gameBoard.aliveList.iterator();
//        Robot temp = iterate.next();
//        Iterator<Robot> iterate2 = gameController.gameBoard.aliveList.iterator();
//        Robot temp2 = iterate2.next();
        
          turn = gameBoard.aliveList.iterator();
          turn.next();
          turn2 = gameBoard.aliveList.iterator();
          turn2.next();
          //Robot temp = turn.next();
          
          //Robot temp2 = turn.next();
          

        
        JLabel currentTurn = new JLabel("Current Turn: " + gameBoard.aliveList.getFirst().getGang() + " " + gameBoard.aliveList.getFirst().getType());
        southButtons.add(currentTurn);
        southButtons.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER)));  

        JButton helpButton = new JButton(" Help");
        helpButton.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE));
        helpButton.setBackground(Color.BLACK);
        helpButton.setForeground(Color.WHITE);
        helpButton.setActionCommand("help");
        helpButton.addActionListener(alistener);
        helpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        helpButton.setFocusPainted(false);
        southButtons.add(helpButton);

        JButton endTurnButton = new JButton(" End Turn");
        endTurnButton.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE));
        endTurnButton.setBackground(Color.BLACK);
        endTurnButton.setForeground(Color.WHITE);
        endTurnButton.setActionCommand("endTurn");
        endTurnButton.addActionListener(alistener);
        endTurnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        endTurnButton.setFocusPainted(false);

        southButtons.add(endTurnButton);

        JButton quitButton = new JButton(" Quit ");
        quitButton.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE));
        quitButton.setBackground(Color.BLACK);
        quitButton.setForeground(Color.WHITE);
        quitButton.setActionCommand("quit");
        quitButton.addActionListener(alistener);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setFocusPainted(false);
        southButtons.add(quitButton);
        southButtons.setBorder(BorderFactory.createLineBorder(Color.black));

        if (gameinfo.getNumPlayers() == 2) {
            System.out.println("team: " + gameBoard.robotList.get(0).getGang());
            System.out.println("Robot: " + gameBoard.robotList.get(0).getType());
            System.out.println("Health: " + gameBoard.robotList.get(0).getHealthLeft());

            JPanel allLeft = new JPanel();
            allLeft.setBackground(Color.WHITE);
            allLeft.setLayout(new BoxLayout(allLeft, BoxLayout.Y_AXIS));
            allLeft.setAlignmentX(Component.LEFT_ALIGNMENT);

            JPanel allRight = new JPanel();
            allRight.setBackground(Color.WHITE);
            allRight.setLayout(new BoxLayout(allRight, BoxLayout.Y_AXIS));
            allRight.setAlignmentX(Component.RIGHT_ALIGNMENT);
            allRight.setAlignmentY(Component.TOP_ALIGNMENT);

            JPanel left = new JPanel();
            left.setBackground(Color.WHITE);
            left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
            left.add(Box.createVerticalGlue());
            left.setAlignmentX(Component.LEFT_ALIGNMENT);
            left.setBorder(BorderFactory.createLineBorder(Color.black));

            JLabel health = new JLabel("Team " + "Red      ");
            health.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            health.setForeground(Color.BLACK);
            left.add(health, BorderLayout.NORTH);

            JLabel scout = new JLabel("Scout Health: " + gameBoard.robotList.get(0).getHealthLeft() + "          ");
            scout.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            scout.setForeground(Color.BLACK);
            left.add(scout, BorderLayout.NORTH);

            JLabel sniper = new JLabel("Sniper Health: " + gameBoard.robotList.get(0 + 2).getHealthLeft());
            sniper.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            sniper.setForeground(Color.BLACK);
            left.add(sniper, BorderLayout.NORTH);

            JLabel tank = new JLabel("Tank Health: " + gameBoard.robotList.get(0 + (2 * 2)).getHealthLeft());
            tank.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            tank.setForeground(Color.BLACK);
            left.add(tank, BorderLayout.NORTH);

            JPanel right = new JPanel();
            right.setBackground(Color.WHITE);
            right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
            right.add(Box.createVerticalGlue());
            right.setAlignmentX(Component.RIGHT_ALIGNMENT);
            right.setBorder(BorderFactory.createLineBorder(Color.black));

            JLabel health2 = new JLabel("Team " + "Green");
            health2.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            health2.setForeground(Color.BLACK);
            right.add(health2, BorderLayout.NORTH);

            JLabel scout2 = new JLabel("Scout Health: " + gameBoard.robotList.get(1).getHealthLeft() + "          ");
            scout2.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            scout2.setForeground(Color.BLACK);
            right.add(scout2, BorderLayout.NORTH);

            JLabel sniper2 = new JLabel("Sniper Health: " + gameBoard.robotList.get(1 + 2).getHealthLeft());
            sniper2.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            sniper2.setForeground(Color.BLACK);
            right.add(sniper2, BorderLayout.NORTH);

            JLabel tank2 = new JLabel("Tank Health: " + gameBoard.robotList.get(1 + (2 * 2)).getHealthLeft());
            tank2.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            tank2.setForeground(Color.BLACK);
            right.add(tank2, BorderLayout.NORTH);

            allRight.add(right);
            allLeft.add(left);
            allLeft.add(southButtons);
            add(allLeft);
            add(gamepanel);
            allRight.add(right);
            add(allRight);

            endTurnButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    try{
                    if(turn.hasNext()){
                        currentTurn.setText("Current Turn: " + turn.next().getGang() + " " + turn2.next().getType());
                    }
                    else{
                        restartIterate();
                        currentTurn.setText("Current Turn: " + turn.next().getGang() + " " + turn2.next().getType());
                    }
                    }
                    catch (ConcurrentModificationException c){
                        restartIterate();
                        while (turn.next() != gameinfo.getCurrentRobot()){
                        }
                        while(turn2.next() != gameinfo.getCurrentRobot()){
                        }
                        currentTurn.setText("Current Turn: " + turn.next().getGang() + " " + turn2.next().getType());
                    }

                    scout.setText("Scout Health: " + gameBoard.robotList.get(0).getHealthLeft() + "          ");
                    sniper.setText("Sniper Health: " + gameBoard.robotList.get(0 + 2).getHealthLeft());
                    tank.setText("Tank Health: " + gameBoard.robotList.get(0 + (2 * 2)).getHealthLeft());

                    scout2.setText("Scout Health: " + gameBoard.robotList.get(1).getHealthLeft() + "          ");
                    sniper2.setText("Sniper Health: " + gameBoard.robotList.get(1 + 2).getHealthLeft());
                    tank2.setText("Tank Health: " + gameBoard.robotList.get(1 + (2 * 2)).getHealthLeft());

                }

            });
        } else if (gameinfo.getNumPlayers() == 3) {

            JPanel allLeft = new JPanel();
            allLeft.setBackground(Color.WHITE);
            allLeft.setLayout(new BoxLayout(allLeft, BoxLayout.Y_AXIS));
            allLeft.setAlignmentX(Component.LEFT_ALIGNMENT);

            JPanel allRight = new JPanel();
            allRight.setBackground(Color.WHITE);
            allRight.setLayout(new BoxLayout(allRight, BoxLayout.Y_AXIS));
            allRight.setAlignmentX(Component.RIGHT_ALIGNMENT);

            JPanel left = new JPanel();
            left.setBackground(Color.WHITE);
            left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
            left.add(Box.createVerticalGlue());
            left.setAlignmentX(Component.LEFT_ALIGNMENT);
            left.setBorder(BorderFactory.createLineBorder(Color.black));

            JLabel health = new JLabel("Team " + "Red      ");
            health.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            health.setForeground(Color.BLACK);
            left.add(health, BorderLayout.NORTH);

            JLabel scout = new JLabel("Scout Health: " + gameBoard.robotList.get(0).getHealthLeft() + "          ");
            scout.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            scout.setForeground(Color.BLACK);
            left.add(scout, BorderLayout.NORTH);

            JLabel sniper = new JLabel("Sniper Health: " + gameBoard.robotList.get(0 + 3).getHealthLeft());
            sniper.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            sniper.setForeground(Color.BLACK);
            left.add(sniper, BorderLayout.NORTH);

            JLabel tank = new JLabel("Tank Health: " + gameBoard.robotList.get(0 + (3 * 2)).getHealthLeft());
            tank.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            tank.setForeground(Color.BLACK);
            left.add(tank, BorderLayout.NORTH);

            JPanel right = new JPanel();
            right.setBackground(Color.WHITE);
            right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
            right.add(Box.createVerticalGlue());
            right.setAlignmentX(Component.RIGHT_ALIGNMENT);
            right.setBorder(BorderFactory.createLineBorder(Color.black));

            JLabel health2 = new JLabel("Team " + "Yellow");
            health2.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            health2.setForeground(Color.BLACK);
            right.add(health2, BorderLayout.NORTH);

            JLabel scout2 = new JLabel("Scout Health: " + gameBoard.robotList.get(1).getHealthLeft() + "          ");
            scout2.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            scout2.setForeground(Color.BLACK);
            right.add(scout2, BorderLayout.NORTH);

            JLabel sniper2 = new JLabel("Sniper Health: " + gameBoard.robotList.get(1 + 3).getHealthLeft());
            sniper2.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            sniper2.setForeground(Color.BLACK);
            right.add(sniper2, BorderLayout.NORTH);

            JLabel tank2 = new JLabel("Tank Health: " + gameBoard.robotList.get(1 + (3 * 2)).getHealthLeft());
            tank2.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            tank2.setForeground(Color.BLACK);
            right.add(tank2, BorderLayout.NORTH);

            JPanel right2 = new JPanel();
            right2.setBackground(Color.WHITE);
            right2.setLayout(new BoxLayout(right2, BoxLayout.Y_AXIS));
            right2.add(Box.createVerticalGlue());
            right2.setAlignmentX(Component.RIGHT_ALIGNMENT);
            right2.setBorder(BorderFactory.createLineBorder(Color.black));

            JLabel health3 = new JLabel("Team " + "Blue");
            health3.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            health3.setForeground(Color.BLACK);
            right2.add(health3, BorderLayout.NORTH);

            JLabel scout3 = new JLabel("Scout Health: " + gameBoard.robotList.get(2).getHealthLeft() + "          ");
            scout3.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            scout3.setForeground(Color.BLACK);
            right2.add(scout3, BorderLayout.NORTH);

            JLabel sniper3 = new JLabel("Sniper Health: " + gameBoard.robotList.get(2 + 3).getHealthLeft());
            sniper3.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            sniper3.setForeground(Color.BLACK);
            right2.add(sniper3, BorderLayout.NORTH);

            JLabel tank3 = new JLabel("Tank Health: " + gameBoard.robotList.get(2 + (3 * 2)).getHealthLeft());
            tank3.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            tank3.setForeground(Color.BLACK);
            right2.add(tank3, BorderLayout.NORTH);

            allLeft.add(left);
            allLeft.add(southButtons);
            add(allLeft);
            add(gamepanel);
            allRight.add(right);
            allRight.add(right2);
            add(allRight);

            endTurnButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    try{
                    if(turn.hasNext()){
                        currentTurn.setText("Current Turn: " + turn.next().getGang() + " " + turn2.next().getType());
                    }
                    else{
                        restartIterate();
                        currentTurn.setText("Current Turn: " + turn.next().getGang() + " " + turn2.next().getType());
                    }
                    }
                    catch (ConcurrentModificationException c){
                        restartIterate();
                        while (turn.next() != gameinfo.getCurrentRobot()){
                        }
                        while(turn2.next() != gameinfo.getCurrentRobot()){
                        }
                        currentTurn.setText("Current Turn: " + turn.next().getGang() + " " + turn2.next().getType());
                    }
                    scout.setText("Scout Health: " + gameBoard.robotList.get(0).getHealthLeft() + "          ");
                    sniper.setText("Sniper Health: " + gameBoard.robotList.get(0 + 3).getHealthLeft());
                    tank.setText("Tank Health: " + gameBoard.robotList.get(0 + (3 * 2)).getHealthLeft());

                    scout2.setText("Scout Health: " + gameBoard.robotList.get(1).getHealthLeft() + "          ");
                    sniper2.setText("Sniper Health: " + gameBoard.robotList.get(1 + 3).getHealthLeft());
                    tank2.setText("Tank Health: " + gameBoard.robotList.get(1 + (3 * 2)).getHealthLeft());

                    scout3.setText("Scout Health: " + gameBoard.robotList.get(2).getHealthLeft() + "          ");
                    sniper3.setText("Sniper Health: " + gameBoard.robotList.get(2 + 3).getHealthLeft());
                    tank3.setText("Tank Health: " + gameBoard.robotList.get(2 + (3 * 2)).getHealthLeft());
                }
            });
        } else if (gameinfo.getNumPlayers() == 6) {

            JPanel allLeft = new JPanel();
            allLeft.setBackground(Color.WHITE);
            allLeft.setLayout(new BoxLayout(allLeft, BoxLayout.Y_AXIS));
            allLeft.setAlignmentX(Component.LEFT_ALIGNMENT);

            JPanel allRight = new JPanel();
            allRight.setBackground(Color.WHITE);
            allRight.setLayout(new BoxLayout(allRight, BoxLayout.Y_AXIS));
            allRight.setAlignmentX(Component.RIGHT_ALIGNMENT);

//JPanel left = createTeamLabels("Red      ", Component.LEFT_ALIGNMENT, 0, 6);
            JPanel left = new JPanel();
            left.setBackground(Color.WHITE);
            left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
            left.add(Box.createVerticalGlue());
            left.setAlignmentX(Component.LEFT_ALIGNMENT);
            left.setBorder(BorderFactory.createLineBorder(Color.black));

            JLabel health = new JLabel("Team " + "Red      ");
            health.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            health.setForeground(Color.BLACK);
            left.add(health, BorderLayout.NORTH);

            JLabel scout = new JLabel("Scout Health: " + gameBoard.robotList.get(0).getHealthLeft() + "          ");
            scout.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            scout.setForeground(Color.BLACK);
            left.add(scout, BorderLayout.NORTH);

            JLabel sniper = new JLabel("Sniper Health: " + gameBoard.robotList.get(0 + 6).getHealthLeft());
            sniper.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            sniper.setForeground(Color.BLACK);
            left.add(sniper, BorderLayout.NORTH);

            JLabel tank = new JLabel("Tank Health: " + gameBoard.robotList.get(0 + (6 * 2)).getHealthLeft());
            tank.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            tank.setForeground(Color.BLACK);
            left.add(tank, BorderLayout.NORTH);
//                
//                
//JPanel right = createTeamLabels("Orange", Component.RIGHT_ALIGNMENT, 1, 6);
            JPanel right = new JPanel();
            right.setBackground(Color.WHITE);
            right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
            right.add(Box.createVerticalGlue());
            right.setAlignmentX(Component.RIGHT_ALIGNMENT);
            right.setBorder(BorderFactory.createLineBorder(Color.black));

            JLabel health2 = new JLabel("Team " + "Orange");
            health2.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            health2.setForeground(Color.BLACK);
            right.add(health2, BorderLayout.NORTH);

            JLabel scout2 = new JLabel("Scout Health: " + gameBoard.robotList.get(1).getHealthLeft() + "          ");
            scout2.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            scout2.setForeground(Color.BLACK);
            right.add(scout2, BorderLayout.NORTH);

            JLabel sniper2 = new JLabel("Sniper Health: " + gameBoard.robotList.get(1 + 6).getHealthLeft());
            sniper2.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            sniper2.setForeground(Color.BLACK);
            right.add(sniper2, BorderLayout.NORTH);

            JLabel tank2 = new JLabel("Tank Health: " + gameBoard.robotList.get(1 + (6 * 2)).getHealthLeft());
            tank2.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            tank2.setForeground(Color.BLACK);
            right.add(tank2, BorderLayout.NORTH);
//                
//JPanel right2 = createTeamLabels("Yellow", Component.RIGHT_ALIGNMENT, 2, 6);
            JPanel right2 = new JPanel();
            right2.setBackground(Color.WHITE);
            right2.setLayout(new BoxLayout(right2, BoxLayout.Y_AXIS));
            right2.add(Box.createVerticalGlue());
            right2.setAlignmentX(Component.RIGHT_ALIGNMENT);
            right2.setBorder(BorderFactory.createLineBorder(Color.black));

            JLabel health3 = new JLabel("Team " + "Yellow");
            health3.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            health3.setForeground(Color.BLACK);
            right2.add(health3, BorderLayout.NORTH);

            JLabel scout3 = new JLabel("Scout Health: " + gameBoard.robotList.get(2).getHealthLeft() + "          ");
            scout3.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            scout3.setForeground(Color.BLACK);
            right2.add(scout3, BorderLayout.NORTH);

            JLabel sniper3 = new JLabel("Sniper Health: " + gameBoard.robotList.get(2 + 6).getHealthLeft());
            sniper3.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            sniper3.setForeground(Color.BLACK);
            right2.add(sniper3, BorderLayout.NORTH);

            JLabel tank3 = new JLabel("Tank Health: " + gameBoard.robotList.get(2 + (6 * 2)).getHealthLeft());
            tank3.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            tank3.setForeground(Color.BLACK);
            right2.add(tank3, BorderLayout.NORTH);
//                
//JPanel right3 = createTeamLabels("Green", Component.RIGHT_ALIGNMENT,3 , 6);
            JPanel right3 = new JPanel();
            right3.setBackground(Color.WHITE);
            right3.setLayout(new BoxLayout(right3, BoxLayout.Y_AXIS));
            right3.add(Box.createVerticalGlue());
            right3.setAlignmentX(Component.RIGHT_ALIGNMENT);
            right3.setBorder(BorderFactory.createLineBorder(Color.black));

            JLabel health4 = new JLabel("Team " + "Green");
            health4.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            health4.setForeground(Color.BLACK);
            right3.add(health4, BorderLayout.NORTH);

            JLabel scout4 = new JLabel("Scout Health: " + gameBoard.robotList.get(3).getHealthLeft() + "          ");
            scout4.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            scout4.setForeground(Color.BLACK);
            right3.add(scout4, BorderLayout.NORTH);

            JLabel sniper4 = new JLabel("Sniper Health: " + gameBoard.robotList.get(3 + 6).getHealthLeft());
            sniper4.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            sniper4.setForeground(Color.BLACK);
            right3.add(sniper4, BorderLayout.NORTH);

            JLabel tank4 = new JLabel("Tank Health: " + gameBoard.robotList.get(3 + (6 * 2)).getHealthLeft());
            tank4.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            tank4.setForeground(Color.BLACK);
            right3.add(tank4, BorderLayout.NORTH);
//                
//JPanel left2 = createTeamLabels("Blue", Component.LEFT_ALIGNMENT, 4, 6);
            JPanel left2 = new JPanel();
            left2.setBackground(Color.WHITE);
            left2.setLayout(new BoxLayout(left2, BoxLayout.Y_AXIS));
            left2.add(Box.createVerticalGlue());
            left2.setAlignmentX(Component.LEFT_ALIGNMENT);
            left2.setBorder(BorderFactory.createLineBorder(Color.black));

            JLabel health5 = new JLabel("Team " + "Blue");
            health5.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            health5.setForeground(Color.BLACK);
            left2.add(health5, BorderLayout.NORTH);

            JLabel scout5 = new JLabel("Scout Health: " + gameBoard.robotList.get(4).getHealthLeft() + "          ");
            scout5.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            scout5.setForeground(Color.BLACK);
            left2.add(scout5, BorderLayout.NORTH);

            JLabel sniper5 = new JLabel("Sniper Health: " + gameBoard.robotList.get(4 + 6).getHealthLeft());
            sniper5.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            sniper5.setForeground(Color.BLACK);
            left2.add(sniper5, BorderLayout.NORTH);

            JLabel tank5 = new JLabel("Tank Health: " + gameBoard.robotList.get(4 + (6 * 2)).getHealthLeft());
            tank5.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            tank5.setForeground(Color.BLACK);
            left2.add(tank5, BorderLayout.NORTH);
//                
//                
//JPanel left3 = createTeamLabels("Purple  ", Component.LEFT_ALIGNMENT, 5, 6);
            JPanel left3 = new JPanel();
            left3.setBackground(Color.WHITE);
            left3.setLayout(new BoxLayout(left3, BoxLayout.Y_AXIS));
            left3.add(Box.createVerticalGlue());
            left3.setAlignmentX(Component.LEFT_ALIGNMENT);
            left3.setBorder(BorderFactory.createLineBorder(Color.black));

            JLabel health6 = new JLabel("Team " + "Purple  ");
            health6.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            health6.setForeground(Color.BLACK);
            left3.add(health6, BorderLayout.NORTH);

            JLabel scout6 = new JLabel("Scout Health: " + gameBoard.robotList.get(5).getHealthLeft() + "          ");
            scout6.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            scout6.setForeground(Color.BLACK);
            left3.add(scout6, BorderLayout.NORTH);

            JLabel sniper6 = new JLabel("Sniper Health: " + gameBoard.robotList.get(5 + 6).getHealthLeft());
            sniper6.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            sniper6.setForeground(Color.BLACK);
            left3.add(sniper6, BorderLayout.NORTH);

            JLabel tank6 = new JLabel("Tank Health: " + gameBoard.robotList.get(5 + (6 * 2)).getHealthLeft());
            tank6.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
            tank6.setForeground(Color.BLACK);
            left3.add(tank6, BorderLayout.NORTH);

            allLeft.add(left);
            allLeft.add(left2);
            allLeft.add(left3);
            allLeft.add(southButtons);
            add(allLeft);
            add(gamepanel);
            allRight.add(right);
            allRight.add(right2);
            allRight.add(right3);
            add(allRight);

            endTurnButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    try{
                    if(turn.hasNext()){
                        currentTurn.setText("Current Turn: " + turn.next().getGang() + " " + turn2.next().getType());
                    }
                    else{
                        restartIterate();
                        currentTurn.setText("Current Turn: " + turn.next().getGang() + " " + turn2.next().getType());
                    }
                    }
                    catch (ConcurrentModificationException c){
                        restartIterate();
                        while (turn.next() != gameinfo.getCurrentRobot()){
                            
                        }
                        while(turn2.next() != gameinfo.getCurrentRobot()){
                        }
                        currentTurn.setText("Current Turn: " + turn.next().getGang() + " " + turn2.next().getType());
                    }


                    scout.setText("Scout Health: " + gameBoard.robotList.get(0).getHealthLeft() + "          ");
                    sniper.setText("Sniper Health: " + gameBoard.robotList.get(0 + 6).getHealthLeft());
                    tank.setText("Tank Health: " + gameBoard.robotList.get(0 + (6 * 2)).getHealthLeft());

                    scout2.setText("Scout Health: " + gameBoard.robotList.get(1).getHealthLeft() + "          ");
                    sniper2.setText("Sniper Health: " + gameBoard.robotList.get(1 + 6).getHealthLeft());
                    tank2.setText("Tank Health: " + gameBoard.robotList.get(1 + (6 * 2)).getHealthLeft());

                    scout3.setText("Scout Health: " + gameBoard.robotList.get(2).getHealthLeft() + "          ");
                    sniper3.setText("Sniper Health: " + gameBoard.robotList.get(2 + 6).getHealthLeft());
                    tank3.setText("Tank Health: " + gameBoard.robotList.get(2 + (6 * 2)).getHealthLeft());

                    scout4.setText("Scout Health: " + gameBoard.robotList.get(3).getHealthLeft() + "          ");
                    sniper4.setText("Sniper Health: " + gameBoard.robotList.get(3 + 6).getHealthLeft());
                    tank4.setText("Tank Health: " + gameBoard.robotList.get(3 + (6 * 2)).getHealthLeft());

                    scout5.setText("Scout Health: " + gameBoard.robotList.get(4).getHealthLeft() + "          ");
                    sniper5.setText("Sniper Health: " + gameBoard.robotList.get(4 + 6).getHealthLeft());
                    tank5.setText("Tank Health: " + gameBoard.robotList.get(4 + (6 * 2)).getHealthLeft());

                    scout6.setText("Scout Health: " + gameBoard.robotList.get(5).getHealthLeft() + "          ");
                    sniper6.setText("Sniper Health: " + gameBoard.robotList.get(5 + 6).getHealthLeft());
                    tank6.setText("Tank Health: " + gameBoard.robotList.get(5 + (6 * 2)).getHealthLeft());

                }

            });

        }

    }
  
    private void restartIterate(){
        turn = gameBoard.aliveList.iterator();
        turn2 = gameBoard.aliveList.iterator();
    }
    private JPanel createTeamLabels(String team, float alignment, int index, int numPlayers) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());
        panel.setAlignmentX(alignment);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel health = new JLabel("Team " + team);
        health.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
        health.setForeground(Color.BLACK);
        panel.add(health, BorderLayout.NORTH);

        JLabel scout = new JLabel("Scout Health: " + gameBoard.robotList.get(index).getHealthLeft() + "          ");
        scout.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
        scout.setForeground(Color.BLACK);
        panel.add(scout, BorderLayout.NORTH);

        JLabel sniper = new JLabel("Sniper Health: " + gameBoard.robotList.get(index + numPlayers).getHealthLeft());
        sniper.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
        sniper.setForeground(Color.BLACK);
        panel.add(sniper, BorderLayout.NORTH);

        JLabel tank = new JLabel("Tank Health: " + gameBoard.robotList.get(index + (numPlayers * 2)).getHealthLeft());
        tank.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
        tank.setForeground(Color.BLACK);
        panel.add(tank, BorderLayout.NORTH);

        return panel;

    }

}

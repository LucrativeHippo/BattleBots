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
import static controller.GameController.chosenRobotCodes;
import controller.Interpreter;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import org.json.simple.JSONObject;

public class TeamSelectionPanel extends JPanel {

  public static LinkedList<JSONObject> robotCodes;
  public static LinkedList<JSONObject> selectedRobots;
  private static final long serialVersionUID = 1L;

  static final int FONT_SIZE = 72;
  static final int CHECK_SIZE = 32;
  static final int BUTTON_SPACER_SIZE = 300;
  static final int VERTICAL_SPACER = 50;
  public boolean anyCheckBoxes = false;
  JCheckBox sample;
  JSONObject updateRobot;
  public TeamSelectionPanel(int width, int height, ActionListener listener) {

    selectedRobots = new LinkedList<>();
    robotCodes = new LinkedList<>();
    while (!chosenRobotCodes.isEmpty()) {
      robotCodes.add(chosenRobotCodes.pop());
    }
    Iterator iterate = robotCodes.iterator();
    while (iterate.hasNext()) {
      chosenRobotCodes.push((JSONObject) iterate.next());
    }

    setSize(width, height);
    setBackground(Color.WHITE);
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    JPanel title = new JPanel();
    title.setBackground(Color.WHITE);
    title.setLayout(new BoxLayout(title, BoxLayout.PAGE_AXIS));

    JLabel label = new JLabel("Team Selection ");
    label.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE));
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
    teamStats.setLayout(new BoxLayout(teamStats, BoxLayout.PAGE_AXIS));
    teamStats.setBorder(BorderFactory.createLineBorder(Color.black));
    //teamStats.setPreferredSize(new Dimension(800, 600));
    //teamStats.setMinimumSize(new Dimension(800, 600));
    //teamStats.setMaximumSize(new Dimension(800, 600));

    JPanel headers = new JPanel();
    headers.setBackground(Color.GRAY);
    headers.setLayout(new GridLayout(1, 12));
    headers.setBorder(BorderFactory.createLineBorder(Color.black));
    headers.setPreferredSize(new Dimension(1000, 20));
    headers.setMinimumSize(new Dimension(1000, 20));
    headers.setMaximumSize(new Dimension(1000, 20));

    JLabel team = new JLabel("   Team");
    team.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
    team.setForeground(Color.BLACK);
    team.setBorder(BorderFactory.createLineBorder(Color.black));
    headers.add(team);

    JLabel classLabel = new JLabel("   Class");
    classLabel.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
    classLabel.setForeground(Color.BLACK);
    classLabel.setBorder(BorderFactory.createLineBorder(Color.black));
    headers.add(classLabel);

    JLabel name = new JLabel("   Name");
    name.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
    name.setForeground(Color.BLACK);
    name.setBorder(BorderFactory.createLineBorder(Color.black));
    headers.add(name);

    JLabel matches = new JLabel("   Matches");
    matches.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
    matches.setForeground(Color.BLACK);
    matches.setBorder(BorderFactory.createLineBorder(Color.black));
    headers.add(matches);

    JLabel wins = new JLabel("   Wins");
    wins.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
    wins.setForeground(Color.BLACK);
    wins.setBorder(BorderFactory.createLineBorder(Color.black));
    headers.add(wins);

    JLabel losses = new JLabel("   Losses");
    losses.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
    losses.setForeground(Color.BLACK);
    losses.setBorder(BorderFactory.createLineBorder(Color.black));
    headers.add(losses);

    JLabel executions = new JLabel("   Executions");
    executions.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
    executions.setForeground(Color.BLACK);
    executions.setBorder(BorderFactory.createLineBorder(Color.black));
    headers.add(executions);

    JLabel lived = new JLabel("   Lived");
    lived.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
    lived.setForeground(Color.BLACK);
    lived.setBorder(BorderFactory.createLineBorder(Color.black));
    headers.add(lived);

    JLabel died = new JLabel("   Died");
    died.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
    died.setForeground(Color.BLACK);
    died.setBorder(BorderFactory.createLineBorder(Color.black));
    headers.add(died);

    JLabel absorbed = new JLabel("   Absorbed");
    absorbed.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
    absorbed.setForeground(Color.BLACK);
    absorbed.setBorder(BorderFactory.createLineBorder(Color.black));
    headers.add(absorbed);

    JLabel killed = new JLabel("   Killed");
    killed.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
    killed.setForeground(Color.BLACK);
    killed.setBorder(BorderFactory.createLineBorder(Color.black));
    headers.add(killed);

    JLabel moved = new JLabel("   Moved");
    moved.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
    moved.setForeground(Color.BLACK);
    moved.setBorder(BorderFactory.createLineBorder(Color.black));
    headers.add(moved);
    teamStats.add(headers);

    Interpreter interpret = new Interpreter();
    JSONObject robot = null;
    int size = chosenRobotCodes.size();
    JPanel teams = new JPanel();
    teams.setBackground(Color.GRAY);
    teams.setLayout(new BoxLayout(teams, BoxLayout.PAGE_AXIS));

    ScrollPane pane1 = new ScrollPane();
    pane1.add(teams);
    JPanel lowerRow = new JPanel();
    lowerRow.setBackground(Color.WHITE);
    lowerRow.setLayout(new BoxLayout(lowerRow, BoxLayout.X_AXIS));
    lowerRow.add(Box.createHorizontalGlue());

    JPanel teamsToFight = new JPanel();
    teamsToFight.setBackground(Color.GRAY);
    teamsToFight.setBorder(BorderFactory.createLineBorder(Color.black));
    teamsToFight.setLayout(new BoxLayout(teamsToFight, BoxLayout.PAGE_AXIS));
    teamsToFight.setPreferredSize(new Dimension(500, 200));
    teamsToFight.setMinimumSize(new Dimension(500, 200));
    teamsToFight.setMaximumSize(new Dimension(500, 200));

    JLabel TTF = new JLabel("Select teams to fight");
    TTF.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
    TTF.setForeground(Color.BLACK);
    teamsToFight.add(TTF);

    teamsToFight.add(pane1);
    lowerRow.add(teamsToFight);
    lowerRow.add(Box.createHorizontalGlue());
    lowerRow.add(Box.createHorizontalGlue());
    lowerRow.add(Box.createHorizontalGlue());
    JPanel upperRight = new JPanel();
    upperRight.setBackground(Color.GRAY);
    upperRight.setLayout(new BoxLayout(upperRight, BoxLayout.X_AXIS));
    upperRight.setBorder(BorderFactory.createLineBorder(Color.black));
    upperRight.setPreferredSize(new Dimension(400, 600));
    upperRight.setMinimumSize(new Dimension(400, 600));
    upperRight.setMaximumSize(new Dimension(400, 600));

    JPanel checkBoxPanel = new JPanel();
    checkBoxPanel.setBackground(Color.GRAY);
    checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.PAGE_AXIS));
    checkBoxPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    //checkBoxPanel.add(Box.createVerticalGlue());
    ButtonGroup group = new ButtonGroup();
    LinkedList<String> teamNames = new LinkedList();

    JRadioButton newChec = new JRadioButton("New Robot");
    newChec.setFont(new Font("Rockwell", Font.PLAIN, CHECK_SIZE / 2));
    newChec.setForeground(Color.BLACK);
    newChec.setAlignmentX(Component.CENTER_ALIGNMENT);
    group.add(newChec);
    newChec.addItemListener(new ItemListener() {

      @Override
      public void itemStateChanged(ItemEvent ie) {
        if (ie.getStateChange() == ItemEvent.SELECTED) {
          anyCheckBoxes = false;
            updateRobot = new JSONObject();
        } else {
          anyCheckBoxes = false;
        }
      }

    });
    checkBoxPanel.add(newChec);

    //This is the statistics for each robot in the list
    for (int i = 1; i <= size; i++) {
      JPanel rows = new JPanel();
      rows.setBackground(Color.GRAY);
      rows.setLayout(new GridLayout(1, 12));
      rows.setBorder(BorderFactory.createLineBorder(Color.black));
      rows.setPreferredSize(new Dimension(1000, 20));
      rows.setMinimumSize(new Dimension(1000, 20));
      rows.setMaximumSize(new Dimension(1000, 20));

      robot = chosenRobotCodes.pop();

      JLabel team1 = new JLabel(" " + interpret.getRobotTeam(robot));
      team1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
      team1.setForeground(Color.BLACK);
      team1.setBorder(BorderFactory.createLineBorder(Color.black));
      rows.add(team1);

      JLabel classLabel1 = new JLabel(" " + interpret.getRobotClass(robot));
      classLabel1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
      classLabel1.setForeground(Color.BLACK);
      classLabel1.setBorder(BorderFactory.createLineBorder(Color.black));
      rows.add(classLabel1);

      JLabel name1 = new JLabel(" " + interpret.getRobotName(robot));
      name1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
      name1.setForeground(Color.BLACK);
      name1.setBorder(BorderFactory.createLineBorder(Color.black));
      rows.add(name1);

      String matchNum = String.valueOf(" " + interpret.getRobotMatches(robot));
      JLabel matches1 = new JLabel(matchNum);
      matches1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
      matches1.setForeground(Color.BLACK);
      matches1.setBorder(BorderFactory.createLineBorder(Color.black));
      rows.add(matches1);

      String WinsNum = String.valueOf(" " + interpret.getRobotWins(robot));
      JLabel wins1 = new JLabel(WinsNum);
      wins1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
      wins1.setForeground(Color.BLACK);
      wins1.setBorder(BorderFactory.createLineBorder(Color.black));
      rows.add(wins1);

      String lostNum = String.valueOf(" " + interpret.getRobotLosses(robot));
      JLabel losses1 = new JLabel(lostNum);
      losses1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
      losses1.setForeground(Color.BLACK);
      losses1.setBorder(BorderFactory.createLineBorder(Color.black));
      rows.add(losses1);

      String exeNum = String.valueOf(" " + interpret.getRobotExecutions(robot));
      JLabel executions1 = new JLabel(exeNum);
      executions1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
      executions1.setForeground(Color.BLACK);
      executions1.setBorder(BorderFactory.createLineBorder(Color.black));
      rows.add(executions1);

      String livedNum = String.valueOf(" " + interpret.getRobotLived(robot));
      JLabel lived1 = new JLabel(livedNum);
      lived1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
      lived1.setForeground(Color.BLACK);
      lived1.setBorder(BorderFactory.createLineBorder(Color.black));
      rows.add(lived1);

      String diedNum = String.valueOf(" " + interpret.getRobotDied(robot));
      JLabel died1 = new JLabel(diedNum);
      died1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
      died1.setForeground(Color.BLACK);
      died1.setBorder(BorderFactory.createLineBorder(Color.black));
      rows.add(died1);

      String absNum = String.valueOf(" " + interpret.getRobotAbsorbed(robot));
      JLabel absorbed1 = new JLabel(absNum);
      absorbed1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
      absorbed1.setForeground(Color.BLACK);
      absorbed1.setBorder(BorderFactory.createLineBorder(Color.black));
      rows.add(absorbed1);

      String killedNum = String.valueOf(" " + interpret.getRobotKilled(robot));
      JLabel killed1 = new JLabel(killedNum);
      killed1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
      killed1.setForeground(Color.BLACK);
      killed1.setBorder(BorderFactory.createLineBorder(Color.black));
      rows.add(killed1);

      String moveNum = String.valueOf(" " + interpret.getRobotMoved(robot));
      JLabel moved1 = new JLabel(moveNum);
      moved1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
      moved1.setForeground(Color.BLACK);
      moved1.setBorder(BorderFactory.createLineBorder(Color.black));
      rows.add(moved1);
      teamStats.add(rows);
//                teamStats.add(Box.createVerticalGlue());
//                teamStats.add(Box.createVerticalGlue());
//                teamStats.add(Box.createVerticalGlue());
//                

      //Checkbox dealing with the Team selection
      if (!teamNames.contains(interpret.getRobotTeam(robot))) {
        teamNames.add(interpret.getRobotTeam(robot));
        sample = new JCheckBox("" + interpret.getRobotTeam(robot));
        sample.setFont(new Font("Rockwell", Font.PLAIN, CHECK_SIZE / 2));
        sample.setForeground(Color.BLACK);
        sample.setAlignmentX(Component.CENTER_ALIGNMENT);
        sample.addItemListener(new ItemListener() {

          @Override
          public void itemStateChanged(ItemEvent ie) {
            String theTeam = sample.getText();
            Interpreter interpret2 = new Interpreter();
            JSONObject storage = null;
            Iterator<JSONObject> iterate2 = robotCodes.iterator();
            if (ie.getStateChange() == ItemEvent.SELECTED) {
              while (iterate2.hasNext()) {
                storage = iterate2.next();

                if (theTeam.compareTo(interpret2.getRobotTeam(storage)) == 0) {
                  if ((selectedRobots == null) || !selectedRobots.contains(storage)) {
                    selectedRobots.add(storage);
                  }
                }
              }
            } else {
              while (iterate2.hasNext()) {
                storage = iterate2.next();

                if (theTeam.equals(interpret2.getRobotTeam(storage))) {
                  if (selectedRobots.contains(storage)) {
                    selectedRobots.remove(storage);
                  }
                }
              }
            }
          }

        });

        teams.add(sample);
      }

      JRadioButton newCheck = new JRadioButton(interpret.getRobotName(robot));
      newCheck.setFont(new Font("Rockwell", Font.PLAIN, CHECK_SIZE / 2));
      newCheck.setForeground(Color.BLACK);
      newCheck.setAlignmentX(Component.CENTER_ALIGNMENT);
      group.add(newCheck);
      newCheck.addItemListener(new ItemListener() {

        @Override
        public void itemStateChanged(ItemEvent ie) {
          if (ie.getStateChange() == ItemEvent.SELECTED) {
            
            updateRobot = new JSONObject();
            Iterator<JSONObject> iterate = robotCodes.iterator();
            Interpreter interpret = new Interpreter();
            JSONObject storage = new JSONObject();
            while(iterate.hasNext()){
              storage = iterate.next();
              if(interpret.getRobotName(storage).compareTo(newCheck.getText()) == 0){
              updateRobot = storage;
            }
            }
            anyCheckBoxes = true;
          } else {
            anyCheckBoxes = false;
          }
        }

      });
      checkBoxPanel.add(newCheck);
    }

    ScrollPane statsPane = new ScrollPane();
    //statsPane.setSize(800, 400);
    statsPane.add(teamStats);
    statsPane.setPreferredSize(new Dimension(1000, 600));
    statsPane.setMinimumSize(new Dimension(1000, 600));
    statsPane.setMaximumSize(new Dimension(1000, 600));

    upperRow.add(Box.createHorizontalGlue());
    upperRow.add(statsPane);
    upperRow.add(Box.createHorizontalGlue());
    upperRow.add(Box.createHorizontalGlue());

    JPanel lb = new JPanel();
    lb.setBackground(Color.GRAY);
    lb.setLayout(new BoxLayout(lb, BoxLayout.PAGE_AXIS));
    lb.setBorder(BorderFactory.createLineBorder(Color.black));
    lb.add(Box.createVerticalGlue());

    JPanel one = new JPanel();
    one.setBackground(Color.GRAY);
    one.setLayout(new FlowLayout());
    JTextField tn = new JTextField(12);
    tn.setBorder(BorderFactory.createLineBorder(Color.black));
    JLabel TN = new JLabel("Team name: ");
    TN.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 6));
    TN.setForeground(Color.BLACK);
    one.add(TN);
    one.add(tn);

    JPanel two = new JPanel();
    two.setBackground(Color.GRAY);
    two.setLayout(new FlowLayout());
    JTextField tn1 = new JTextField(12);
    tn1.setBorder(BorderFactory.createLineBorder(Color.black));
    JLabel TN1 = new JLabel("Robot name: ");
    TN1.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 6));
    TN1.setForeground(Color.BLACK);
    two.add(TN1);
    two.add(tn1);

    JPanel three = new JPanel();
    three.setBackground(Color.GRAY);
    three.setLayout(new FlowLayout());
    String[] robos = {"Scout", "Sniper", "Tank"};
    JComboBox tn2 = new JComboBox(robos);
    tn2.setSelectedIndex(0);
    tn2.setBorder(BorderFactory.createLineBorder(Color.black));
    JLabel TN2 = new JLabel("Class: ");
    TN2.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 6));
    TN2.setForeground(Color.BLACK);
    three.add(TN2);
    three.add(tn2);

    JPanel lbButtons = new JPanel();
    lbButtons.setBackground(Color.GRAY);
    lbButtons.setLayout(new FlowLayout());
    //lbButtons.add(Box.createHorizontalGlue());

    JButton ATButton = new JButton("Add Robot");
    ATButton.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 6));
    ATButton.setBackground(Color.BLACK);
    ATButton.setForeground(Color.WHITE);
    ATButton.setActionCommand("AddRobot");
    ATButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent ae) {
        String inTeam = tn.getText();
        String inRobot = tn1.getText();
        String inClass = (String) tn2.getSelectedItem();

        if (inTeam.equals("")) {
          tn.setText("Enter Team Name");
        }
        if (inRobot.equals("")) {
          tn1.setText("Enter Robot Name");
        }

        if (!inTeam.equals("") && !inRobot.equals("") && !anyCheckBoxes) {
          JSONObject newRobot = new JSONObject();
          newRobot.put("team", inTeam);
          newRobot.put("class", inClass);
          newRobot.put("name", inRobot);
          newRobot.put("matches", 0);
          newRobot.put("wins", 0);
          newRobot.put("losses", 0);
          newRobot.put("executions", 0);
          newRobot.put("lived", 0);
          newRobot.put("died", 0);
          newRobot.put("absorbed", 0);
          newRobot.put("killed", 0);
          newRobot.put("moved", 0);
          newRobot.put("code", null);
          JSONObject newRobot2 = new JSONObject();
          newRobot2.put("script", newRobot);
          //Create some Forth Code
          robotCodes.add(newRobot2);

          JRadioButton newCheck1 = new JRadioButton(inRobot);
          newCheck1.setFont(new Font("Rockwell", Font.PLAIN, CHECK_SIZE / 2));
          newCheck1.setForeground(Color.BLACK);
          newCheck1.setAlignmentX(Component.CENTER_ALIGNMENT);
          group.add(newCheck1);
          newCheck1.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent ie) {
              if (ie.getStateChange() == ItemEvent.SELECTED) {
                anyCheckBoxes = true;
                updateRobot = new JSONObject();
                updateRobot = newRobot;
              } else {
                anyCheckBoxes = false;
              }
            }

          });

          //Adding the new team to the list of teams
          if (!teamNames.contains(inTeam)) {

            JCheckBox newCheckteam1 = new JCheckBox(inTeam);
            newCheckteam1.setFont(new Font("Rockwell", Font.PLAIN, CHECK_SIZE / 2));
            newCheckteam1.setForeground(Color.BLACK);
            newCheckteam1.setAlignmentX(Component.CENTER_ALIGNMENT);
            teamNames.add(inTeam);
            newCheckteam1.addItemListener(new ItemListener() {

              @Override
              public void itemStateChanged(ItemEvent ie) {
                String theTeam = newCheckteam1.getText();
                Interpreter interpret2 = new Interpreter();
                JSONObject storage = null;
                Iterator<JSONObject> iterate2 = robotCodes.iterator();
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                  while (iterate2.hasNext()) {
                    storage = iterate2.next();

                    if (theTeam.compareTo(interpret2.getRobotTeam(storage)) == 0) {
                      if ((selectedRobots == null) || !selectedRobots.contains(storage)) {
                        selectedRobots.add(storage);
                      }
                    }
                  }
                } else {
                  while (iterate2.hasNext()) {
                    storage = iterate2.next();

                    if (theTeam.equals(interpret2.getRobotTeam(storage))) {
                      if (selectedRobots.contains(storage)) {
                        selectedRobots.remove(storage);
                      }
                    }
                  }
                }
              }

            });
            SwingUtilities.invokeLater(new Runnable() {

              @Override
              public void run() {
                teams.add(newCheckteam1);
                teams.revalidate();
                teams.repaint();
              }

            });
          }

          System.out.print("New Robot");
          JPanel rows = new JPanel();
          rows.setBackground(Color.GRAY);
          rows.setLayout(new GridLayout(1, 12));
          rows.setBorder(BorderFactory.createLineBorder(Color.black));
          rows.setPreferredSize(new Dimension(1000, 20));
          rows.setMinimumSize(new Dimension(1000, 20));
          rows.setMaximumSize(new Dimension(1000, 20));

          JLabel team1 = new JLabel(" " + inTeam);
          team1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
          team1.setForeground(Color.BLACK);
          team1.setBorder(BorderFactory.createLineBorder(Color.black));
          rows.add(team1);

          JLabel classLabel1 = new JLabel(" " + inClass);
          classLabel1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
          classLabel1.setForeground(Color.BLACK);
          classLabel1.setBorder(BorderFactory.createLineBorder(Color.black));
          rows.add(classLabel1);

          JLabel name1 = new JLabel(" " + inRobot);
          name1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
          name1.setForeground(Color.BLACK);
          name1.setBorder(BorderFactory.createLineBorder(Color.black));
          rows.add(name1);

          String matchNum = String.valueOf(" " + 0);
          JLabel matches1 = new JLabel(matchNum);
          matches1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
          matches1.setForeground(Color.BLACK);
          matches1.setBorder(BorderFactory.createLineBorder(Color.black));
          rows.add(matches1);

          String WinsNum = String.valueOf(" " + 0);
          JLabel wins1 = new JLabel(WinsNum);
          wins1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
          wins1.setForeground(Color.BLACK);
          wins1.setBorder(BorderFactory.createLineBorder(Color.black));
          rows.add(wins1);

          String lostNum = String.valueOf(" " + 0);
          JLabel losses1 = new JLabel(lostNum);
          losses1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
          losses1.setForeground(Color.BLACK);
          losses1.setBorder(BorderFactory.createLineBorder(Color.black));
          rows.add(losses1);

          String exeNum = String.valueOf(" " + 0);
          JLabel executions1 = new JLabel(exeNum);
          executions1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
          executions1.setForeground(Color.BLACK);
          executions1.setBorder(BorderFactory.createLineBorder(Color.black));
          rows.add(executions1);

          String livedNum = String.valueOf(" " + 0);
          JLabel lived1 = new JLabel(livedNum);
          lived1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
          lived1.setForeground(Color.BLACK);
          lived1.setBorder(BorderFactory.createLineBorder(Color.black));
          rows.add(lived1);

          String diedNum = String.valueOf(" " + 0);
          JLabel died1 = new JLabel(diedNum);
          died1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
          died1.setForeground(Color.BLACK);
          died1.setBorder(BorderFactory.createLineBorder(Color.black));
          rows.add(died1);

          String absNum = String.valueOf(" " + 0);
          JLabel absorbed1 = new JLabel(absNum);
          absorbed1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
          absorbed1.setForeground(Color.BLACK);
          absorbed1.setBorder(BorderFactory.createLineBorder(Color.black));
          rows.add(absorbed1);

          String killedNum = String.valueOf(" " + 0);
          JLabel killed1 = new JLabel(killedNum);
          killed1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
          killed1.setForeground(Color.BLACK);
          killed1.setBorder(BorderFactory.createLineBorder(Color.black));
          rows.add(killed1);

          String moveNum = String.valueOf(" " + 0);
          JLabel moved1 = new JLabel(moveNum);
          moved1.setFont(new Font("Rockwell", Font.BOLD, FONT_SIZE / 6));
          moved1.setForeground(Color.BLACK);
          moved1.setBorder(BorderFactory.createLineBorder(Color.black));
          rows.add(moved1);

          SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
              checkBoxPanel.add(newCheck1);
              checkBoxPanel.revalidate();
              checkBoxPanel.repaint();
              teamStats.add(rows);
              teamStats.revalidate();
              teamStats.repaint();

            }

          });

        }
      }

    });
    //ATButton.setAlignmentY(Component.CENTER_ALIGNMENT);
    ATButton.setFocusPainted(false);

    lbButtons.add(ATButton);
    //lbButtons.add(Box.createHorizontalGlue());      	

    JButton updateButton = new JButton("Update");
    updateButton.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 6));
    updateButton.setBackground(Color.BLACK);
    updateButton.setForeground(Color.WHITE);
    updateButton.setActionCommand("update");
    updateButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent ae) {
            String inTeam = tn.getText();
            String inRobot = tn1.getText();
            String inClass = (String) tn2.getSelectedItem();

            if (inTeam.equals("")) {
              tn.setText("Enter Team Name");
            }
            if (inRobot.equals("")) {
              tn1.setText("Enter Robot Name");
            }

            if (!inTeam.equals("") && !inRobot.equals("") && anyCheckBoxes) {
          JSONObject updateRobot = new JSONObject();
          updateRobot.put("team", inTeam);
          updateRobot.put("class", inClass);
          updateRobot.put("name", inRobot);
          updateRobot.put("matches", 0);
          updateRobot.put("wins", 0);
          updateRobot.put("losses", 0);
          updateRobot.put("executions", 0);
          updateRobot.put("lived", 0);
          updateRobot.put("died", 0);
          updateRobot.put("absorbed", 0);
          updateRobot.put("killed", 0);
          updateRobot.put("moved", 0);
          updateRobot.put("code", null);
          JSONObject updateRobot2 = new JSONObject();
          updateRobot2.put("script", updateRobot);
            }
          SwingUtilities.invokeLater(new Runnable() {

          @Override
          public void run() {
              teamStats.revalidate();
              teamStats.repaint();
          }
        });
      }
    });
    updateButton.setAlignmentY(Component.CENTER_ALIGNMENT);
    updateButton.setFocusPainted(false);

    lbButtons.add(updateButton);
    //lbButtons.add(Box.createHorizontalGlue());

    JButton retireButton = new JButton("Retire");
    retireButton.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 6));
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
    lb.add(lbButtons);

    ScrollPane pane = new ScrollPane();
    pane.add(checkBoxPanel);

    upperRight.add(pane);
    upperRight.add(lb);
    upperRow.add(upperRight);
    upperRow.add(Box.createHorizontalGlue());
    add(upperRow);

//                JPanel lowerRow = new JPanel();
//                lowerRow.setBackground(Color.WHITE);
//                lowerRow.setLayout(new BoxLayout(lowerRow, BoxLayout.X_AXIS));
//		lowerRow.add(Box.createHorizontalGlue());
//
//                JPanel teamsToFight = new JPanel();
//                teamsToFight.setBackground(Color.GRAY);
//                teamsToFight.setBorder(BorderFactory.createLineBorder(Color.black));
//                teamsToFight.setLayout(new BoxLayout(teamsToFight, BoxLayout.PAGE_AXIS));
//                
//                JLabel TTF = new JLabel("Select teams to fight");
//                TTF.setFont(new Font("Rockwell",Font.BOLD, FONT_SIZE/6));
//                TTF.setForeground(Color.BLACK);
//                teamsToFight.add(TTF);
//                
//                JPanel teams= new JPanel();
//                teams.setBackground(Color.GRAY);
//                teams.setLayout(new BoxLayout(teams, BoxLayout.PAGE_AXIS));
//                
//
//                teams.add(sample);
//                
//                ScrollPane pane1 = new ScrollPane();
//                pane1.add(teams);
//                
//                teamsToFight.add(pane1);
//                lowerRow.add(teamsToFight);
//                lowerRow.add(Box.createHorizontalGlue());
//                lowerRow.add(Box.createHorizontalGlue());
//                lowerRow.add(Box.createHorizontalGlue());
    JPanel southButtons = new JPanel();
    southButtons.setBackground(Color.WHITE);
    southButtons.setLayout(new GridLayout(2, 2, 10, 10));

    JButton continueButton = new JButton("Continue");
    continueButton.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
    continueButton.setBackground(Color.BLACK);
    continueButton.setForeground(Color.WHITE);
    continueButton.setActionCommand("continueTS");
    continueButton.addActionListener(listener);
    continueButton.setAlignmentY(Component.CENTER_ALIGNMENT);
    continueButton.setFocusPainted(false);

    southButtons.add(continueButton);
    //southButtons.add(Box.createHorizontalGlue());      	

    JButton backButton = new JButton(" Back ");
    backButton.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
    backButton.setBackground(Color.BLACK);
    backButton.setForeground(Color.WHITE);
    backButton.setActionCommand("backTS");
    backButton.addActionListener(listener);
    backButton.setAlignmentY(Component.CENTER_ALIGNMENT);
    backButton.setFocusPainted(false);

    southButtons.add(backButton);
    //southButtons.add(Box.createHorizontalGlue());

    JButton quitButton = new JButton(" Quit ");
    quitButton.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE / 2));
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

    Iterator<JSONObject> iterate1 = robotCodes.iterator();
    while (iterate1.hasNext()) {
      chosenRobotCodes.push(iterate1.next());
    }

  }

}

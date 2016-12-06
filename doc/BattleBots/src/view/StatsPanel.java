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
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import model.GameInfo;
import model.Robot;
import org.json.simple.JSONObject;
import static view.TeamSelectionPanel.FONT_SIZE;
import static view.TeamSelectionPanel.selectedRobots;

public class StatsPanel extends JPanel {

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

    JLabel label = new JLabel("Final Statistics");
    label.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE));
    label.setForeground(Color.BLACK);
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    title.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER)));
    title.add(label);
    title.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER)));
    add(title, BorderLayout.NORTH);

    
    JPanel centerPanel = new JPanel();
    centerPanel.setBackground(Color.WHITE);
    centerPanel.setLayout(new GridLayout(12, 4));
/*
    JLabel teamLabel = new JLabel("Team");
    teamLabel.setFont(new Font("Rockwell", Font.PLAIN, LABEL_FONT_SIZE));
    teamLabel.setForeground(Color.BLACK);

    JLabel wins = new JLabel("Wins");
    wins.setFont(new Font("Rockwell", Font.PLAIN, LABEL_FONT_SIZE));
    wins.setForeground(Color.BLACK);

    JLabel losses = new JLabel("Losses");
    losses.setFont(new Font("Rockwell", Font.PLAIN, LABEL_FONT_SIZE));
    losses.setForeground(Color.BLACK);

    JLabel ties = new JLabel("Ties");
    ties.setFont(new Font("Rockwell", Font.PLAIN, LABEL_FONT_SIZE));
    ties.setForeground(Color.BLACK);

    JLabel scoutKills = new JLabel("Scout Kills");
    scoutKills.setFont(new Font("Rockwell", Font.PLAIN, LABEL_FONT_SIZE));
    scoutKills.setForeground(Color.BLACK);

    JLabel sniperKills = new JLabel("Sniper Kills");
    sniperKills.setFont(new Font("Rockwell", Font.PLAIN, LABEL_FONT_SIZE));
    sniperKills.setForeground(Color.BLACK);

    JLabel tankKills = new JLabel("Tank Kills");
    tankKills.setFont(new Font("Rockwell", Font.PLAIN, LABEL_FONT_SIZE));
    tankKills.setForeground(Color.BLACK);

    centerPanel.add(teamLabel);
    centerPanel.add(wins);
    centerPanel.add(losses);
    centerPanel.add(ties);
    centerPanel.add(scoutKills);
    centerPanel.add(sniperKills);
    centerPanel.add(tankKills);

    centerPanel.setPreferredSize(new Dimension(1400, 800));

            
    JPanel mainPanel = new JPanel();
    mainPanel.add(centerPanel, BorderLayout.CENTER);
*/
    JButton playAgain = new JButton("Play Again");
    playAgain.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE));
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
    quit.setFont(new Font("Rockwell", Font.PLAIN, FONT_SIZE));
    quit.setBackground(Color.BLACK);
    quit.setForeground(Color.WHITE);
    quit.setActionCommand("quit");
    quit.addActionListener(listener);
    quit.setAlignmentY(Component.CENTER_ALIGNMENT);
    quit.setFocusPainted(false);

    southPanel.add(quit);
    southPanel.add(Box.createRigidArea(new Dimension(BUTTON_SPACER_SIZE, 0)));
    southPanel.add(Box.createRigidArea(new Dimension(0, VERTICAL_SPACER * 7)));

    JPanel upperRow = new JPanel();
    upperRow.setBackground(Color.WHITE);
    upperRow.setLayout(new BoxLayout(upperRow, BoxLayout.X_AXIS));

    JPanel teamStats = new JPanel();
    teamStats.setBackground(Color.GRAY);
    teamStats.setLayout(new BoxLayout(teamStats, BoxLayout.PAGE_AXIS));
    teamStats.setBorder(BorderFactory.createLineBorder(Color.black));
    teamStats.setPreferredSize(new Dimension(800, 600));
    //teamStats.setMinimumSize(new Dimension(800, 600));
    //teamStats.setMaximumSize(new Dimension(800, 600));

    JPanel headers = new JPanel();
    headers.setBackground(Color.GRAY);
    headers.setLayout(new GridLayout(1, 12));
    headers.setBorder(BorderFactory.createLineBorder(Color.black));
    headers.setPreferredSize(new Dimension(1500, 40));
    headers.setMinimumSize(new Dimension(1500, 40));
    headers.setMaximumSize(new Dimension(1500, 40));

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
    
   // centerPanel.add(headers);
    
    //For each AI robot we must show it's stats
    Interpreter interpret = new Interpreter();
    //boolean statsComplete = false;
    LinkedList<Robot> list = gameBoard.robotList;
    Iterator<Robot> iterate = gameBoard.robotList.iterator();
    while (iterate.hasNext()) {
      Robot temp = iterate.next();
      if(!temp.isHuman){
        Iterator<JSONObject> Jiterate = selectedRobots.iterator();
        JSONObject Jtemp = new JSONObject();
        boolean foundJSON = false;
        while(Jiterate.hasNext() && foundJSON == false){
          Jtemp = Jiterate.next();
          if(temp.getName().compareTo(interpret.getRobotName(Jtemp)) == 0){
            foundJSON = true;
          }
        }
        
        JPanel rows = new JPanel();
      rows.setBackground(Color.GRAY);
      rows.setLayout(new GridLayout(1, 12));
      rows.setBorder(BorderFactory.createLineBorder(Color.black));
      rows.setPreferredSize(new Dimension(1500, 40));
      rows.setMinimumSize(new Dimension(1500, 40));
      rows.setMaximumSize(new Dimension(1500, 40));
      
      
      JLabel team1 = new JLabel(interpret.getRobotName(Jtemp));
      team1.setFont(new Font("Rockwell", Font.PLAIN, LABEL_FONT_SIZE/3));
      team1.setForeground(Color.BLACK);
      rows.add(team1);
      
      JLabel classes = new JLabel((interpret.getRobotClass(Jtemp)));
      classes.setFont(new Font("Rockwell", Font.PLAIN, LABEL_FONT_SIZE/3));
      classes.setForeground(Color.BLACK);
      rows.add(classes);
      
      JLabel teams = new JLabel(interpret.getRobotTeam(Jtemp));
      teams.setFont(new Font("Rockwell", Font.PLAIN, LABEL_FONT_SIZE/3));
      teams.setForeground(Color.BLACK);
      rows.add(teams);
      
      //Jtemp.replace("matches", temp.)
      JLabel matches1 = new JLabel(Integer.toString(interpret.getRobotMatches(Jtemp)));
      matches1.setFont(new Font("Rockwell", Font.PLAIN, LABEL_FONT_SIZE/3));
      matches1.setForeground(Color.BLACK);
      rows.add(matches1);

      //Jtemp.replace("wins", temp.)
      JLabel teamWins = new JLabel(Integer.toString(interpret.getRobotWins(Jtemp)));
      teamWins.setFont(new Font("Rockwell", Font.PLAIN, LABEL_FONT_SIZE/3));
      teamWins.setForeground(Color.BLACK);
      rows.add(teamWins);

      //Jtemp.replace("losses", temp.)
      JLabel teamLosses = new JLabel(Integer.toString(interpret.getRobotLosses(Jtemp)));
      teamLosses.setFont(new Font("Rockwell", Font.PLAIN, LABEL_FONT_SIZE/3));
      teamLosses.setForeground(Color.BLACK);
      rows.add(teamLosses);

      //Jtemp.replace("executions", temp.)
      JLabel exec = new JLabel(Integer.toString(interpret.getRobotExecutions(Jtemp)));
      exec.setFont(new Font("Rockwell", Font.PLAIN, LABEL_FONT_SIZE/3));
      exec.setForeground(Color.BLACK);
      rows.add(exec);

      //Jtemp.replace("lived", temp.)
      JLabel livednum = new JLabel(Integer.toString(interpret.getRobotLived(Jtemp)));
      livednum.setFont(new Font("Rockwell", Font.PLAIN, LABEL_FONT_SIZE/3));
      livednum.setForeground(Color.BLACK);
      rows.add(livednum);

      //Jtemp.replace("died", temp.)
      JLabel dies = new JLabel(Integer.toString(interpret.getRobotDied(Jtemp)));
      dies.setFont(new Font("Rockwell", Font.PLAIN, LABEL_FONT_SIZE/3));
      dies.setForeground(Color.BLACK);
      rows.add(dies);
      
      //Jtemp.replace("absorbed", temp.)
      JLabel abs = new JLabel(Integer.toString(interpret.getRobotAbsorbed(Jtemp)));
      abs.setFont(new Font("Rockwell", Font.PLAIN, LABEL_FONT_SIZE/3));
      abs.setForeground(Color.BLACK);
      rows.add(abs);
      
      //Jtemp.replace("killed", temp.)
      JLabel kills = new JLabel(Integer.toString(interpret.getRobotKilled(Jtemp)));
      kills.setFont(new Font("Rockwell", Font.PLAIN, LABEL_FONT_SIZE/3));
      kills.setForeground(Color.BLACK);
      rows.add(kills);
      
      //Jtemp.replace("moved", temp.)
      JLabel moves = new JLabel(Integer.toString(interpret.getRobotMoved(Jtemp)));
      moves.setFont(new Font("Rockwell", Font.PLAIN, LABEL_FONT_SIZE/3));
      moves.setForeground(Color.BLACK);
      rows.add(moves);
      
      teamStats.add(rows);
      
      //add(centerPanel);
      }
    }
    add(teamStats);
    }

    
}

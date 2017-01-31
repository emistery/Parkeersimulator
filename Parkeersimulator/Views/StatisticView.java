/**
 * Created by Lenovo T420 on 27-1-2017.
 */
package Parkeersimulator.Views;
import Parkeersimulator.Simulator;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.KeyEvent;

public class StatisticView implements AbstrView{
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JLabel tickLabel = new JLabel("tick: ");
    private JLabel carLabel = new JLabel("amount of cars : ");
    private JLabel adhocLabel = new JLabel("free Ad Hoc Spots: ");
    private JLabel passLabel = new JLabel("free Pass Spots: ");
    private JLabel earningsLabel = new JLabel("Total earnings: ");
    public static String newline = System.getProperty("line.separator");
    private Simulator simulator;


  public StatisticView(Simulator simulator) {
      this.simulator = simulator;

      frame = new JFrame();
      //Container contentPane = frame.getContentPane();

      JPanel panel = createPanel();
      java.util.List<Integer> scores = new ArrayList<Integer>();
      DrawGraph mainPanel = new DrawGraph(scores);
      mainPanel.createAndShowGui(simulator);


      JTabbedPane tabbedPane = new JTabbedPane();
      frame.add(tabbedPane);

      tabbedPane.addTab("Statistics", panel);
      tabbedPane.addTab("Chart", mainPanel);

      GridLayout grid = new GridLayout(0,1);
      grid.setVgap(0);
      panel.setLayout(grid);
      panel.setBackground(Color.LIGHT_GRAY);
      panel.add(carLabel);
      panel.add(tickLabel);
      panel.add(adhocLabel);
      panel.add(passLabel);
      panel.add(earningsLabel);
      panel.repaint();

      frame.pack();
     // frame.setVisible(true);
  }
  public JPanel createPanel() {
      //Create a yellow label to put in the content pane.
      JPanel panel = new JPanel();

      FlowLayout layout = new FlowLayout(FlowLayout.LEADING);
      panel.setLayout(layout);
      panel.setComponentOrientation(
              ComponentOrientation.LEFT_TO_RIGHT);

      panel.setOpaque(true);
      panel.setBackground(new Color(248, 213, 131));
      panel.setPreferredSize(new Dimension(400, 360));
      return panel;
  }

  public void updateView(int tick, int adHocSpots, int passSpots, int cars, double earnings){
      if(!frame.isVisible()){
          frame.setVisible(true);
      }
      carLabel.setText("amount of open spots: " + cars + newline);
      tickLabel.setText("amount of ticks: " + tick);
      adhocLabel.setText("amount of open Ad Hoc spots: " + adHocSpots);
      passLabel.setText("amount of open Pass spots: " + passSpots);
      earningsLabel.setText("Total earnings : € " + earnings);
      frame.repaint();
  }
  public void disableView(){
      frame.setVisible(false);
  }


    protected Component makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }



}



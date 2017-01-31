/**
 * Created by Lenovo T420 on 27-1-2017.
 */
package Parkeersimulator.Views;
import Parkeersimulator.Simulator;
import Parkeersimulator.SimulatorController;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.awt.event.KeyEvent;

public class StatisticView implements AbstrView{
    private JFrame frame;
    private SimulatorController controller;
    private JTabbedPane tabbedPane;
    private JLabel tickLabel = new JLabel("tick: ");
    private JLabel carLabel = new JLabel("amount of cars : ");
    private JLabel adhocLabel = new JLabel("free Ad Hoc Spots: ");
    private JLabel passLabel = new JLabel("free Pass Spots: ");
    private JLabel earningsLabel = new JLabel("Total earnings: ");
    private JLabel missedEarningsLabel = new JLabel("Missed earnings: ");
    private JLabel missedCarsLabel = new JLabel("Missed Cars: ");
    public static String newline = System.getProperty("line.separator");
    private Simulator simulator;
    private DrawGraph mainPanel;
    private ChartPanel chartPanel;

    private ArrayList<Integer> scores;


  public StatisticView(Simulator simulator) {
      this.simulator = simulator;
      scores = new ArrayList<>();
      frame = new JFrame();
      //Container contentPane = frame.getContentPane();

      JPanel panel = createPanel();
      mainPanel = new DrawGraph(scores);
      mainPanel.createAndShowGui(scores);
      double[] values = new double[3];
      String[] names = new String[3];
      values[0] = 1;
      names[0] = "Item 1";

      values[1] = 2;
      names[1] = "Item 2";

      values[2] = 4;
      names[2] = "Item 3";

      chartPanel = new ChartPanel(values, names, "Ik wil kaas");


      JTabbedPane tabbedPane = new JTabbedPane();
      frame.add(tabbedPane);

      tabbedPane.addTab("Statistics", panel);
      tabbedPane.addTab("Chart", mainPanel);
      tabbedPane.addTab("BarChart", chartPanel);

      GridLayout grid = new GridLayout(0,1);
      grid.setVgap(0);
      panel.setLayout(grid);
      panel.setBackground(Color.LIGHT_GRAY);
      panel.add(carLabel);
      panel.add(tickLabel);
      panel.add(adhocLabel);
      panel.add(passLabel);
      panel.add(earningsLabel);
      panel.add(missedEarningsLabel);
      panel.add(missedCarsLabel);

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

  public void updateView(int tick, int adHocSpots, int passSpots, int cars, double earnings, double missedEarnings, int missedCars){
      carLabel.setText("amount of open spots: " + cars + newline);
      tickLabel.setText("amount of ticks: " + tick);
      adhocLabel.setText("amount of open Ad Hoc spots: " + adHocSpots);
      passLabel.setText("amount of open Pass spots: " + passSpots);
      earningsLabel.setText("Total earnings : € " + earnings);
      missedEarningsLabel.setText("Missed earnings : € " + simulator.calculateMissedEarnings());
      missedCarsLabel.setText("Missed cars: " + missedCars);
      if(scores.size()>100){
          scores.remove(0);
      }if((tick%25)==0) {
          scores.add(540 - cars);
      }
      mainPanel.createAndShowGui(scores);
      //mainPanel.addData();
      //mainPanel.repaint();
      frame.repaint();
  }
  public void disableView(){
      frame.setVisible(false);
  }
  public void enableView(){
      Point point = controller.getSimulatorView().getLocation();
      point.x = controller.getSimulatorView().getWidth();
      frame.setLocation(point);
      frame.setVisible(true);
  }


    protected Component makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
    public void setController(SimulatorController contr){
      controller = contr;
    }


}



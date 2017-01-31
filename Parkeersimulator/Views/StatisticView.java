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
    private JLabel dayLabel = new JLabel("Current day: ");
    public static String newline = System.getProperty("line.separator");
    private Simulator simulator;
    private DrawGraph mainPanel;
    private ChartPanel chartPanel;

    private ArrayList<Integer> adHocs;
    private ArrayList<Integer> pPass;


  public StatisticView(Simulator simulator) {
      this.simulator = simulator;
      adHocs = new ArrayList<>();
      pPass = new ArrayList<>();
      frame = new JFrame();
      //Container contentPane = frame.getContentPane();

      JPanel panel = createPanel();
      mainPanel = new DrawGraph(adHocs, pPass);
      mainPanel.createAndShowGui(adHocs, pPass);
      //for the bar chart
      double[] values = new double[7];
      String[] names = new String[7];
      values[0] = 0;
      names[0] = "Maandag";

      values[1] = 0;
      names[1] = "Dinsdag";

      values[2] = 0;
      names[2] = "Woensdag";

      values[3] = 0;
      names[3] = "Donderdag";

      values[4] = 0;
      names[4] = "Vrijdag";

      values[5] = 0;
      names[5] = "Zaterdag";

      values[6] = 0;
      names[6] = "Zondag";
      chartPanel = new ChartPanel(values, names, "Inkomen per dag");

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
      panel.add(dayLabel);

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

  public void updateView(int tick, int adHocSpots, int passSpots, int cars, double earnings, double missedEarnings, int missedCars, String displayTime){
      carLabel.setText("amount of open spots: " + cars + newline);
      tickLabel.setText("amount of ticks: " + tick);
      adhocLabel.setText("amount of open Ad Hoc spots: " + adHocSpots);
      passLabel.setText("amount of open Pass spots: " + passSpots);
      earningsLabel.setText("Total earnings : € " + earnings);
      missedEarningsLabel.setText("Missed earnings : € " + simulator.calculateMissedEarnings());
      missedCarsLabel.setText("Missed cars: " + missedCars);
      dayLabel.setText(simulator.displayDay());

      while(adHocs.size()>=100){
          adHocs.remove(0);
          pPass.remove(0);
      }
      if((tick%60)==0) {
          adHocs.add(controller.getAdHocCars());
          pPass.add(simulator.getPassCars());
      }
      mainPanel.createAndShowGui(adHocs, pPass);
<<<<<<< Updated upstream
=======


      //mainPanel.addData();
      //mainPanel.repaint();
>>>>>>> Stashed changes
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

//comment2push



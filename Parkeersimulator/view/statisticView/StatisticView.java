/**
 * Created by Lenovo T420 on 27-1-2017.
 */
package Parkeersimulator.view.statisticView;
import Parkeersimulator.Functions;
import Parkeersimulator.model.Simulator;
import Parkeersimulator.main.ParkeerSimulator;
import Parkeersimulator.view.abstractView.AbstractView;
import Parkeersimulator.view.statisticView.BarChart.BarChartQueue;
import Parkeersimulator.view.statisticView.BarChart.BarChartView;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class StatisticView extends AbstractView {
    private JFrame frame;
    private ParkeerSimulator parkeerSimulator;
    private JLabel tickLabel = new JLabel("Current tick: ");
    private JLabel carLabel = new JLabel("Amount of open spots: ");
    private JLabel adhocLabel = new JLabel("Free ad-hoc spots: ");
    private JLabel passLabel = new JLabel("Free pass spots: ");
    private JLabel earningsLabel = new JLabel("Total earnings: €");
    private JLabel missedEarningsLabel = new JLabel("Missed earnings: €");
    private JLabel missedCarsLabel = new JLabel("Missed cars: ");
    private JLabel missedPassCarsLabel = new JLabel("Missed pass cars: ");
    private JLabel dayLabel = new JLabel("Current day: ");

    public static String newline = System.getProperty("line.separator");

    private BarChartView activeBarChart;
    private JTabbedPane weekTabs;

    private ArrayList<Integer> adHocs;
    private ArrayList<Integer> pPass;
    private static final int GRAPH_POINTS = 25;
    private static final int GRAPH_UPDATE_FREQUENCY = 120;
    private DrawGraph mainPanel;

    private BarChartQueue queueBarChart;

    private boolean enabled = false;

  public StatisticView(Simulator simulator) {
      super(simulator);
      adHocs = new ArrayList<>();
      pPass = new ArrayList<>();
      frame = new JFrame();
      frame.setTitle("Statistics");

      JPanel panel = createPanel();
      mainPanel = new DrawGraph(adHocs, pPass, simulator);
      mainPanel.createAndShowGui(adHocs, pPass);

      JPanel chartMainPanel = new JPanel();
      GridLayout mainGrid = new GridLayout(0,1);
      mainGrid.setVgap(0);
      chartMainPanel.setLayout(mainGrid);

      queueBarChart = new BarChartQueue(simulator);

      activeBarChart = new BarChartView(simulator);
      weekTabs = new JTabbedPane();
      weekTabs.addTab("Week 1", activeBarChart);
      chartMainPanel.add(weekTabs);

      JTabbedPane tabbedPane = new JTabbedPane();
      tabbedPane.addTab("Statistics", panel);
      tabbedPane.addTab("Amount of cars", mainPanel);
      tabbedPane.addTab("Day earnings", chartMainPanel);
      tabbedPane.addTab("Queue length", queueBarChart);
      frame.add(tabbedPane);

      /* todo klasse maken*/
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
      panel.add(missedPassCarsLabel);
      panel.add(dayLabel);//todo
      panel.repaint();

      frame.pack();
  }
  public JPanel createPanel() {
      //Create a yellow label to put in the content pane.
      JPanel panel = new JPanel();

      FlowLayout layout = new FlowLayout(FlowLayout.LEADING);
      panel.setLayout(layout);
      panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      panel.setOpaque(true);
      panel.setBackground(new Color(248, 213, 131));
      panel.setPreferredSize(new Dimension(400, 360));
      return panel;
  }

  public void updateView(){
      int tick = simulator.getTick();
      carLabel.setText("Amount of open spots: " + simulator.getNumberOfOpenSpots() + newline);
      tickLabel.setText("Current tick: " + tick);
      adhocLabel.setText("Free ad-hoc spots: " + simulator.getOpenAdHocSpots());
      passLabel.setText("Free pass spots: " + simulator.getOpenPassSpots());
      earningsLabel.setText("Total earnings: € " + simulator.getEarnings());
      missedEarningsLabel.setText("Missed earnings: € " + simulator.calculateMissedEarnings());
      missedCarsLabel.setText("Missed cars: " + simulator.getTotalMissedCars());
      missedPassCarsLabel.setText("Missed pass cars: " + simulator.getMissedPassCars());
      dayLabel.setText(simulator.displayDay());

      //create new Bar Graph every week and add it to a new tab.
      int week = Functions.getWeek(tick)+1;
      if((tick%10080==0&&tick>10079)) {
          activeBarChart = new BarChartView(simulator);
          activeBarChart.setParkeerSimulator(parkeerSimulator);
          simulator.addView(activeBarChart);
      }
      if((tick%10081==0&&tick>10080)) {
          weekTabs.addTab("Week "+week, activeBarChart);
      }

      while(adHocs.size()>=GRAPH_POINTS){
          adHocs.remove(0);
          pPass.remove(0);
      }
      if((tick%GRAPH_UPDATE_FREQUENCY)==0 ||adHocs.size()==0) {

          adHocs.add(simulator.getAdHocCars());
          pPass.add(simulator.getPassCars());
      }
      mainPanel.createAndShowGui(adHocs, pPass);
      frame.repaint();
  }
  public void disableView(){
      frame.setVisible(false);
      enabled=false;
  }
  public void enableView(){
      enabled=true;
      Point point = parkeerSimulator.getSimulatorView().getFrame().getLocation();
      point.x = parkeerSimulator.getSimulatorView().getFrame().getWidth();
      frame.setLocation(point);
      frame.setVisible(true);
  }
    public boolean getEnabled() {
        return enabled;
    }

    protected Component makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    public void setParkeerSimulator(ParkeerSimulator contr){
      parkeerSimulator = contr;
      activeBarChart.setParkeerSimulator(parkeerSimulator);
      queueBarChart.setParkeerSimulator(parkeerSimulator);
      simulator.addView(activeBarChart);
      simulator.addView(queueBarChart);
    }
}



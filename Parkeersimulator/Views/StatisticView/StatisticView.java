/**
 * Created by Lenovo T420 on 27-1-2017.
 */
package Parkeersimulator.Views.StatisticView;
import Parkeersimulator.Simulator;
import Parkeersimulator.SimulatorController;
import Parkeersimulator.Views.AbstrView;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class StatisticView implements AbstrView {
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

    private ArrayList<JLabel> mondayLabel = new ArrayList<>();
    private ArrayList<JLabel> tuesdayLabel = new ArrayList<>();
    private ArrayList<JLabel> wednesdayLabel = new ArrayList<>();
    private ArrayList<JLabel> thursdayLabel = new ArrayList<>();
    private ArrayList<JLabel> fridayLabel = new ArrayList<>();
    private ArrayList<JLabel> saturdayLabel = new ArrayList<>();
    private ArrayList<JLabel> sundayLabel = new ArrayList<>();

    private JPanel textPanel[] = new JPanel[5];
    private JTabbedPane weekTabs;

    public static String newline = System.getProperty("line.separator");
    private Simulator simulator;
    private DrawGraph mainPanel;
    private ArrayList<ChartPanel> chartPanel;

    private ArrayList<Integer> adHocs;
    private ArrayList<Integer> pPass;
    private static final int GRAPH_POINTS = 25;
    private static final int GRAPH_UPDATE_FREQUENCY = 60;
    private double[][] values;
    private String[] names;


  public StatisticView(Simulator simulator) {
      this.simulator = simulator;
      adHocs = new ArrayList<>();
      pPass = new ArrayList<>();
      frame = new JFrame();
      //Container contentPane = frame.getContentPane();

      mondayLabel.add(new JLabel("€" + 0.00));
      tuesdayLabel.add(new JLabel("€" + 0.00));
      wednesdayLabel.add(new JLabel("€" + 0.00));
      thursdayLabel.add(new JLabel("€" + 0.00));
      fridayLabel.add(new JLabel("€" + 0.00));
      saturdayLabel.add(new JLabel("€" + 0.00));
      sundayLabel.add(new JLabel("€" + 0.00));


      JPanel panel = createPanel();
      mainPanel = new DrawGraph(adHocs, pPass);
      mainPanel.createAndShowGui(adHocs, pPass);
      //for the bar chart
      values = new double[][]{{0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}};

      names = new String[]{"Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag", "Zondag"};

      JPanel chartMainPanel = new JPanel();
      textPanel[0] = new JPanel();
      GridLayout textGrid = new GridLayout(1,0);
      textGrid.setHgap(9);
      textPanel[0].setLayout(textGrid);

      textPanel[0].add(mondayLabel.get(0));
      textPanel[0].add(tuesdayLabel.get(0));
      textPanel[0].add(wednesdayLabel.get(0));
      textPanel[0].add(thursdayLabel.get(0));
      textPanel[0].add(fridayLabel.get(0));
      textPanel[0].add(saturdayLabel.get(0));
      textPanel[0].add(sundayLabel.get(0));

      GridLayout mainGrid = new GridLayout(2,1);
      chartMainPanel.setLayout(mainGrid);
      chartPanel = new ArrayList<>();
        chartPanel.add(new ChartPanel(values[0], names, "Inkomen per dag"));
            weekTabs = new JTabbedPane();
                JPanel tab = new JPanel();
                    tab.setLayout(mainGrid);
                    tab.add(textPanel[0]);
                    tab.add(chartPanel.get(0));
             weekTabs.addTab("week 1", tab);
      chartMainPanel.add(weekTabs);

      JTabbedPane tabbedPane = new JTabbedPane();
      frame.add(tabbedPane);

      tabbedPane.addTab("Statistics", panel);
      tabbedPane.addTab("Chart", mainPanel);
      tabbedPane.addTab("BarChart", chartMainPanel);

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
  public void newChart(int index){
      textPanel[index] = new JPanel();
      GridLayout textGrid = new GridLayout(1,0);
      textGrid.setHgap(9);
      textPanel[index].setLayout(textGrid);

      mondayLabel.add(new JLabel("€" + 0.00));
      tuesdayLabel.add(new JLabel("€" + 0.00));
      wednesdayLabel.add(new JLabel("€" + 0.00));
      thursdayLabel.add(new JLabel("€" + 0.00));
      fridayLabel.add(new JLabel("€" + 0.00));
      saturdayLabel.add(new JLabel("€" + 0.00));
      sundayLabel.add(new JLabel("€" + 0.00));

      textPanel[index].add(mondayLabel.get(index));
      textPanel[index].add(tuesdayLabel.get(index));
      textPanel[index].add(wednesdayLabel.get(index));
      textPanel[index].add(thursdayLabel.get(index));
      textPanel[index].add(fridayLabel.get(index));
      textPanel[index].add(saturdayLabel.get(index));
      textPanel[index].add(sundayLabel.get(index));
      JPanel tab = new JPanel();
      GridLayout mainGrid = new GridLayout(2,1);
      chartPanel.add(new ChartPanel(values[index], names, "Inkomen per dag"));
      tab.setLayout(mainGrid);
      tab.add(textPanel[index]);
      tab.add(chartPanel.get(index));
      weekTabs.addTab(("week "+(index+1)),tab);
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
      int index = tick/10080;
      if(!(tick%10080==0&&tick>10079)) {
          int day = simulator.getDay();
          values[index][day] = simulator.getDayEarnings();
      }else{
          newChart(index);
      }

      mondayLabel.get(index).setText("€" + round(values[index][0],2));
      tuesdayLabel.get(index).setText("€" + round(values[index][1],2));
      wednesdayLabel.get(index).setText("€" + round(values[index][2],2));
      thursdayLabel.get(index).setText("€" + round(values[index][3],2));
      fridayLabel.get(index).setText("€" + round(values[index][4],2));
      saturdayLabel.get(index).setText("€" + round(values[index][5],2));
      sundayLabel.get(index).setText("€" + round(values[index][6],2));

      while(adHocs.size()>=GRAPH_POINTS){
          adHocs.remove(0);
          pPass.remove(0);
      }
      if((tick%GRAPH_UPDATE_FREQUENCY)==0 ||adHocs.size()==0) {
          adHocs.add(controller.getAdHocCars());
          pPass.add(simulator.getPassCars());
      }
      mainPanel.createAndShowGui(adHocs, pPass);


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

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}

//comment2push



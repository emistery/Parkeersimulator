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

    private JLabel mondayLabel[];
    private JLabel tuesdayLabel[];
    private JLabel wednesdayLabel[];
    private JLabel thursdayLabel[];
    private JLabel fridayLabel[];
    private JLabel saturdayLabel[];
    private JLabel sundayLabel[];

    private JPanel textPanel[] = new JPanel[5];
    private JTabbedPane weekTabs;

    public static String newline = System.getProperty("line.separator");
    private Simulator simulator;
    private DrawGraph2 mainPanel;
    private ArrayList<ChartPanel> chartPanel;

    private ArrayList<Integer> adHocs;
    private ArrayList<Integer> pPass;

    private double[][] values;

    private String[] names;


  public StatisticView(Simulator simulator) {
      this.simulator = simulator;
      adHocs = new ArrayList<>();
      pPass = new ArrayList<>();
      frame = new JFrame();
      //Container contentPane = frame.getContentPane();

      mondayLabel = new JLabel[]{new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00)};
      tuesdayLabel = new JLabel[]{new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00)};
      wednesdayLabel = new JLabel[]{new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00)};
      thursdayLabel = new JLabel[]{new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00)};
      fridayLabel = new JLabel[]{new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00)};
      saturdayLabel = new JLabel[]{new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00)};
      sundayLabel = new JLabel[]{new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00), new JLabel("€" + 0.00)};


      JPanel panel = createPanel();
      mainPanel = new DrawGraph2(adHocs, pPass);
      mainPanel.createAndShowGui(adHocs, pPass);
      //for the bar chart
      values = new double[][]{{0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}};
      for(double[] array : values){
          for(double doubl : array ){
              System.out.println(doubl);
          }
          System.out.println(" new array" );
      }
      System.out.println(values);

      names = new String[]{"Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag", "Zondag"};

      JPanel chartMainPanel = new JPanel();
      textPanel[0] = new JPanel();
      GridLayout textGrid = new GridLayout(1,0);
      textGrid.setHgap(9);
      textPanel[0].setLayout(textGrid);

      textPanel[0].add(mondayLabel[0]);
      textPanel[0].add(tuesdayLabel[0]);
      textPanel[0].add(wednesdayLabel[0]);
      textPanel[0].add(thursdayLabel[0]);
      textPanel[0].add(fridayLabel[0]);
      textPanel[0].add(saturdayLabel[0]);
      textPanel[0].add(sundayLabel[0]);

      GridLayout mainGrid = new GridLayout(2,1);
      chartMainPanel.setLayout(mainGrid);
      chartPanel = new ArrayList<>();
        chartPanel.add(new ChartPanel(values[0], names, "Inkomen per dag"));
            weekTabs = new JTabbedPane();
                JPanel tab = new JPanel();
                    tab.setLayout(mainGrid);
                    tab.add(textPanel[0]);
                    tab.add(chartPanel.get(0));
             weekTabs.add(tab);
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

      textPanel[index].add(mondayLabel[index]);
      textPanel[index].add(tuesdayLabel[index]);
      textPanel[index].add(wednesdayLabel[index]);
      textPanel[index].add(thursdayLabel[index]);
      textPanel[index].add(fridayLabel[index]);
      textPanel[index].add(saturdayLabel[index]);
      textPanel[index].add(sundayLabel[index]);
      JPanel tab = new JPanel();
      GridLayout mainGrid = new GridLayout(2,1);
      chartPanel.add(new ChartPanel(values[index], names, "Inkomen per dag"));
      tab.setLayout(mainGrid);
      tab.add(textPanel[index]);
      tab.add(chartPanel.get(index));
      weekTabs.add(tab);
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

      mondayLabel[index].setText("€" + round(values[index][0],2));
      tuesdayLabel[index].setText("€" + round(values[index][1],2));
      wednesdayLabel[index].setText("€" + round(values[index][2],2));
      thursdayLabel[index].setText("€" + round(values[index][3],2));
      fridayLabel[index].setText("€" + round(values[index][4],2));
      saturdayLabel[index].setText("€" + round(values[index][5],2));
      sundayLabel[index].setText("€" + round(values[index][6],2));

      while(adHocs.size()>=100){
          adHocs.remove(0);
          pPass.remove(0);
      }
      if((tick%10)==0 ||adHocs.size()==0) {
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



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

    private JLabel mondayLabel;
    private JLabel tuesdayLabel;
    private JLabel wednesdayLabel;
    private JLabel thursdayLabel;
    private JLabel fridayLabel;
    private JLabel saturdayLabel;
    private JLabel sundayLabel;

    public static String newline = System.getProperty("line.separator");
    private Simulator simulator;
    private DrawGraph2 mainPanel;
    private ChartPanel chartPanel;

    private ArrayList<Integer> adHocs;
    private ArrayList<Integer> pPass;

    private double[] values;
    private String[] names;


  public StatisticView(Simulator simulator) {
      this.simulator = simulator;
      adHocs = new ArrayList<>();
      pPass = new ArrayList<>();
      frame = new JFrame();
      //Container contentPane = frame.getContentPane();

      mondayLabel = new JLabel("€" + 0.00);
      tuesdayLabel = new JLabel("€" + 0.00);
      wednesdayLabel = new JLabel("€" + 0.00);
      thursdayLabel = new JLabel("€" + 0.00);
      fridayLabel = new JLabel("€" + 0.00);
      saturdayLabel = new JLabel("€" + 0.00);
      sundayLabel = new JLabel("€" + 0.00);


      JPanel panel = createPanel();
      mainPanel = new DrawGraph2(adHocs, pPass);
      mainPanel.createAndShowGui(adHocs, pPass);
      //for the bar chart
      values = new double[7];
      names = new String[7];
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
      JPanel chartMainPanel = new JPanel();
      JPanel textPanel = new JPanel();
      GridLayout textGrid = new GridLayout(1,0);
      textGrid.setHgap(9);
      textPanel.setLayout(textGrid);

      textPanel.add(mondayLabel);
      textPanel.add(tuesdayLabel);
      textPanel.add(wednesdayLabel);
      textPanel.add(thursdayLabel);
      textPanel.add(fridayLabel);
      textPanel.add(saturdayLabel);
      textPanel.add(sundayLabel);

      GridLayout mainGrid = new GridLayout(2,1);
      chartMainPanel.setLayout(mainGrid);
      chartPanel = new ChartPanel(values, names, "Inkomen per dag");
      chartMainPanel.add(textPanel);
      chartMainPanel.add(chartPanel);

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

  public void updateView(int tick, int adHocSpots, int passSpots, int cars, double earnings, double missedEarnings, int missedCars, String displayTime){
      carLabel.setText("amount of open spots: " + cars + newline);
      tickLabel.setText("amount of ticks: " + tick);
      adhocLabel.setText("amount of open Ad Hoc spots: " + adHocSpots);
      passLabel.setText("amount of open Pass spots: " + passSpots);
      earningsLabel.setText("Total earnings : € " + earnings);
      missedEarningsLabel.setText("Missed earnings : € " + simulator.calculateMissedEarnings());
      missedCarsLabel.setText("Missed cars: " + missedCars);
      dayLabel.setText(simulator.displayDay());
      int day = simulator.getDay();
      values[day] = simulator.getDayEarnings();

      mondayLabel.setText("€" + round(values[0],2));
      tuesdayLabel.setText("€" + round(values[1],2));
      wednesdayLabel.setText("€" + round(values[2],2));
      thursdayLabel.setText("€" + round(values[3],2));
      fridayLabel.setText("€" + round(values[4],2));
      saturdayLabel.setText("€" + round(values[5],2));
      sundayLabel.setText("€" + round(values[6],2));

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



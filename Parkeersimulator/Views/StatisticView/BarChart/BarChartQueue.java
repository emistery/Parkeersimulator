package Parkeersimulator.Views.StatisticView.BarChart;

import Parkeersimulator.SimulatorController;
import Parkeersimulator.Views.AbstrView;
import Parkeersimulator.Views.Time;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gebruiker on 2-2-2017.
 * BarCharts expire every first minute of the week(monday0:0) and stop updating.
 */
public class BarChartQueue extends JPanel implements AbstrView{
    private SimulatorController controller;

    private JPanel textPanel;

    private ChartPanel chartPanel;

    private double[] values;
    private String[] names;

    private JLabel adHocLabel;
    private JLabel passLabel;


    public BarChartQueue(){
        adHocLabel = new JLabel("lengte: " + 0);
        passLabel = new JLabel("lengte: " + 0);

        values = new double[]{0,0};
        names = new String[]{"AdHoc", "Pass"};

        textPanel = new JPanel();
        GridLayout textGrid = new GridLayout(1,0);
        textGrid.setHgap(9);
        textPanel.setLayout(textGrid);

        textPanel.add(adHocLabel);
        textPanel.add(passLabel);

        GridLayout mainGrid = new GridLayout(2,1);
        setLayout(mainGrid);
        chartPanel = new ChartPanel(values, names, "Wachtrijen",100);
        JPanel tab = new JPanel();
        setLayout(mainGrid);
        add(textPanel);
        add(chartPanel);
    }
    public void updateView(int tick, int adHocSpots, int passSpots, int cars, double earnings, double missedEarnings, int missedCars, String displayTime){
        values[0] = ((double)controller.getAdHocQueue());
        values[1] = ((double)controller.getPassQueue());

        adHocLabel.setText("lengte: " + values[0]);
        passLabel.setText("lengte: " + values[1]);
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
    public void disableView(){};
    public void enableView(){};
}

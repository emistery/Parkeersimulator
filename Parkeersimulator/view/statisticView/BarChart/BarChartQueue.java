package Parkeersimulator.view.statisticView.BarChart;

import Parkeersimulator.main.ParkeerSimulator;
import Parkeersimulator.model.Simulator;
import Parkeersimulator.view.statisticView.AbstractView.AbstractView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gebruiker on 2-2-2017.
 * BarCharts expire every first minute of the week(monday0:0) and stop updating.
 */
public class BarChartQueue extends AbstractView {
    private ParkeerSimulator parkeerSimulator;

    private JPanel textPanel;

    private ChartPanel chartPanel;

    private double[] values;
    private String[] names;

    private JLabel adHocLabel;
    private JLabel passLabel;


    public BarChartQueue(Simulator simulator){
        super(simulator);
        adHocLabel = new JLabel("lengte: " + 0);
        passLabel = new JLabel("lengte: " + 0);

        values = new double[]{0,0};
        names = new String[]{"AdHoc", "Pass"};

        textPanel = new JPanel();
        GridLayout textGrid = new GridLayout(1,0);
        textGrid.setHgap(9);
        textPanel.setLayout(textGrid);
        Dimension d = new Dimension(400,50);
        textPanel.setPreferredSize(d);

        textPanel.add(adHocLabel);
        textPanel.add(passLabel);

        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        setLayout(gridBag);
        chartPanel = new ChartPanel(values, names, "Wachtrijen",25);
        c.ipady = 0;
        c.ipadx = 400;
        c.gridx = 0;
        c.gridy = 0;
        add(textPanel,c);
        c.ipady = 500;
        c.ipadx = 400;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        add(chartPanel,c);
    }
    public void updateView(int tick, int adHocSpots, int passSpots, int cars, double earnings, double missedEarnings, int missedCars, String displayTime){
        values[0] = ((double)simulator.getAdHocQueueSize());
        values[1] = ((double)simulator.getPassQueueSize());

        adHocLabel.setText("lengte: " + values[0]);
        passLabel.setText("lengte: " + values[1]);
    }

    public void setParkeerSimulator(ParkeerSimulator contr){
        parkeerSimulator = contr;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}

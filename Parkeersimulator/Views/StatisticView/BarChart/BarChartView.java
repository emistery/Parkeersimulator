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
public class BarChartView extends JPanel implements AbstrView{
    private SimulatorController controller;

    private JPanel textPanel;
    private JTabbedPane weekTabs;

    private ChartPanel chartPanel;

    private double[] values;
    private String[] names;

    private JLabel mondayLabel;
    private JLabel tuesdayLabel;
    private JLabel wednesdayLabel;
    private JLabel thursdayLabel;
    private JLabel fridayLabel;
    private JLabel saturdayLabel;
    private JLabel sundayLabel;

    public BarChartView(){
        mondayLabel = new JLabel("€" + 0.00);
        tuesdayLabel = new JLabel("€" + 0.00);
        wednesdayLabel=new JLabel("€" + 0.00);
        thursdayLabel = new JLabel("€" + 0.00);
        fridayLabel = new JLabel("€" + 0.00);
        saturdayLabel = new JLabel("€" + 0.00);
        sundayLabel = new JLabel("€" + 0.00);

        values = new double[]{0,0,0,0,0,0,0};
        names = new String[]{"Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag", "Zondag"};

        textPanel = new JPanel();
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
        setLayout(mainGrid);
        chartPanel = new ChartPanel(values, names, "Inkomen per dag");
        JPanel tab = new JPanel();
        setLayout(mainGrid);
        add(textPanel);
        add(chartPanel);
        }
    public void updateView(int tick, int adHocSpots, int passSpots, int cars, double earnings, double missedEarnings, int missedCars, String displayTime){
        if(tick%10080!=0 && tick!=0) {
            int day = Time.getDayNumber(tick);
            if (controller != null) {
                values[day] = controller.getDayEarnings();

                mondayLabel.setText("€" + round(values[0], 2));
                tuesdayLabel.setText("€" + round(values[1], 2));
                wednesdayLabel.setText("€" + round(values[2], 2));
                thursdayLabel.setText("€" + round(values[3], 2));
                fridayLabel.setText("€" + round(values[4], 2));
                saturdayLabel.setText("€" + round(values[5], 2));
                sundayLabel.setText("€" + round(values[6], 2));
            }
        }else if(tick%10080==0 && tick!=0){
            controller = null;
        }
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

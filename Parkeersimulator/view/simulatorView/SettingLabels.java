package Parkeersimulator.view.simulatorView;

import Parkeersimulator.controller.SettingController;
import Parkeersimulator.model.Simulator;
import Parkeersimulator.view.statisticView.AbstractView.AbstractView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gebruiker on 7-2-2017.
 */
public class SettingLabels extends AbstractView
{
    private JLabel weekDayArrivals;
    private JLabel weekendArrivals;
    private JLabel weekDayPassArrivals;
    private JLabel weekendPassArrivals;
    private JLabel thursdayArrivals;
    private JLabel enterSpeed;
    private JLabel paymentSpeed;
    private JLabel exitSpeed;

    private SettingController settingController;
    SettingLabels(Simulator simulator){
        super(simulator);
        settingController = new SettingController(simulator, this);
        setBackground(new Color(43, 43, 43));

        GridLayout textGrid = new GridLayout(4,4);
        textGrid.setHgap(20);
        setLayout(textGrid);
        initializeLabels();

        add(weekDayArrivals);
        add(settingController.getSetWeekDayArrivals());
        add(weekendArrivals);
        add(settingController.getSetWeekendArrivals());
        add(weekDayPassArrivals);
        add(settingController.getSetWeekDayPassArrivals());
        add(weekendPassArrivals);
        add(settingController.getSetWeekendPassArrivals());

        add(enterSpeed);
        add(settingController.getSetEnterSpeed());
        add(paymentSpeed);
        add(settingController.getSetPaymentSpeed());
        add(exitSpeed);
        add(settingController.getSetExitSpeed());
        add(thursdayArrivals);
        add(settingController.getSetThursdayArrivals());


        setVisible(true);
    }

    public void updateView(){updateView(0, 0, 0, 0, 0, 0,0, "");}
    public void updateView(int tick, int adHocSpots, int passSpots, int cars, double earnings, double missedEarnings, int missedCars, String displayTime){
        weekDayArrivals.setText("weekDayArrivals "+ simulator.getWeekDayArrivals());
        weekendArrivals.setText("weekendArrivals "+ simulator.getWeekendArrivals());
        weekDayPassArrivals.setText("weekDayPassArrivals "+ simulator.getWeekDayPassArrivals());
        weekendPassArrivals.setText("weekendPassArrivals "+ simulator.getWeekendPassArrivals());
        thursdayArrivals.setText("thursdayArrivals "+ simulator.getThursdayArrivals());

        enterSpeed.setText("enterSpeed"+ simulator.getEnterSpeed());
        paymentSpeed.setText("paymentSpeed"+ simulator.getPaymentSpeed());
        exitSpeed.setText("exitSpeed"+ simulator.getExitSpeed());
    }
    private void initializeLabels(){
        weekDayArrivals = new JLabel();
        weekDayArrivals.setForeground(new Color(169, 183, 198));
        weekendArrivals = new JLabel();
        weekendArrivals.setForeground(new Color(169, 183, 198));
        weekDayPassArrivals = new JLabel();
        weekDayPassArrivals.setForeground(new Color(169, 183, 198));
        weekendPassArrivals = new JLabel();
        weekendPassArrivals.setForeground(new Color(169, 183, 198));
        thursdayArrivals = new JLabel();
        thursdayArrivals.setForeground(new Color(169, 183, 198));
        enterSpeed = new JLabel();
        enterSpeed.setForeground(new Color(169, 183, 198));
        paymentSpeed = new JLabel();
        paymentSpeed.setForeground(new Color(169, 183, 198));
        exitSpeed = new JLabel();
        exitSpeed.setForeground(new Color(169, 183, 198));
    }
}

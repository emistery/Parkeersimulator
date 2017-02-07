package Parkeersimulator.controller;
import Parkeersimulator.model.Simulator;
import Parkeersimulator.view.simulatorView.SettingLabels;
import javax.swing.*;

/**
 * Created by Lenovo T420 on 2-2-2017.
 */
public class SettingController extends AbstractController {
    private JTextField setWeekDayArrivals;
    private JTextField setWeekendArrivals;
    private JTextField setWeekDayPassArrivals;
    private JTextField setWeekendPassArrivals;
    private JTextField setThursdayArrivals;
    private JTextField setEnterSpeed;
    private JTextField setPaymentSpeed;
    private JTextField setExitSpeed;

    public SettingController(Simulator simulator, SettingLabels view) {
        super(simulator);
        setWeekDayArrivals = new JTextField(4);
        setWeekDayArrivals.addActionListener(e -> {
            if (simulator != null) {
                int value = Integer.parseInt(setWeekDayArrivals.getText());
                if (value > 0) {
                    simulator.setWeekDayArrivals(value);
                    view.updateView();
                }
            }
        });
        setWeekendArrivals = new JTextField(4);
        setWeekendArrivals.addActionListener(e -> {
            if (simulator != null) {
                int value = Integer.parseInt(setWeekendArrivals.getText());
                if (value > 0) {
                    simulator.setWeekendArrivals(value);
                    view.updateView();
                }
            }
        });
        setWeekDayPassArrivals = new JTextField(4);
        setWeekDayPassArrivals.addActionListener(e -> {
            if (simulator != null) {
                int value = Integer.parseInt(setWeekDayPassArrivals.getText());
                if (value > 0) {
                    simulator.setWeekDayPassArrivals(value);
                    view.updateView();
                }
            }
        });
        setWeekendPassArrivals = new JTextField(4);
        setWeekendPassArrivals.addActionListener(e -> {
            if (simulator != null) {
                int value = Integer.parseInt(setWeekendPassArrivals.getText());
                if (value > 0) {
                    simulator.setWeekendPassArrivals(value);
                    view.updateView();
                }
            }
        });
        setThursdayArrivals = new JTextField(4);
        setThursdayArrivals.addActionListener(e -> {
            if (simulator != null) {
                int value = Integer.parseInt(setThursdayArrivals.getText());
                if (value > 0) {
                    simulator.setThursdayArrivals(value);
                    view.updateView();
                }
            }
        });

        setEnterSpeed = new JTextField(4);
        setEnterSpeed.addActionListener(e -> {
            if (simulator != null) {
                int value = Integer.parseInt(setEnterSpeed.getText());
                if (value > 0) {
                    simulator.setEnterSpeed(value);
                    view.updateView();
                }
            }
        });
        setPaymentSpeed = new JTextField(4);
        setPaymentSpeed.addActionListener(e -> {
            if (simulator != null) {
                int value = Integer.parseInt(setPaymentSpeed.getText());
                if (value > 0) {
                    simulator.setPaymentSpeed(value);
                    view.updateView();
                }
            }
        });
        setExitSpeed = new JTextField(4);
        setExitSpeed.addActionListener(e -> {
            if (simulator != null) {
                int value = Integer.parseInt(setExitSpeed.getText());
                if (value > 0) {
                    simulator.setExitSpeed(value);
                    view.updateView();
                }
            }
        });
    }
    public JTextField getSetWeekDayArrivals() {
        return setWeekDayArrivals;
    }

    public JTextField getSetWeekendArrivals() {
        return setWeekendArrivals;
    }

    public JTextField getSetWeekDayPassArrivals() {
        return setWeekDayPassArrivals;
    }

    public JTextField getSetWeekendPassArrivals() {
        return setWeekendPassArrivals;
    }

    public JTextField getSetThursdayArrivals() {
        return setThursdayArrivals;
    }

    public JTextField getSetEnterSpeed() {
        return setEnterSpeed;
    }

    public JTextField getSetPaymentSpeed() {
        return setPaymentSpeed;
    }

    public JTextField getSetExitSpeed() {
        return setExitSpeed;
    }
}

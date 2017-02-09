package Parkeersimulator.controller;
import Parkeersimulator.Functions;
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
            String input = getSetWeekDayArrivals().getText();
            if (Functions.isInt(input)) {
                int value = Integer.parseInt(input);
                if (value >= 0) {
                    simulator.setWeekDayArrivals(value);
                    view.updateView();
                }
            } else {
                setWeekDayArrivals.setText("0 - 2147483647");
            }
        });

        setWeekendArrivals = new JTextField(4);
        setWeekendArrivals.addActionListener(e -> {
            String input = getSetWeekendArrivals().getText();
            if (Functions.isInt(input)) {
                int value = Integer.parseInt(input);
                if (value >= 0) {
                    simulator.setWeekendArrivals(value);
                    view.updateView();
                }
            } else {
                setWeekendArrivals.setText("0 - 2147483647");
            }
        });


        setWeekDayPassArrivals = new JTextField(4);
        setWeekDayPassArrivals.addActionListener(e -> {
            String input = setWeekDayPassArrivals.getText();
            if (Functions.isInt(input)) {
                int value = Integer.parseInt(input);
                if (value >= 0) {
                    simulator.setWeekDayPassArrivals(value);
                    view.updateView();
                }
            } else {
                setWeekDayPassArrivals.setText("0 - 2147483647");
            }
        });

        setWeekendPassArrivals = new JTextField(4);
        setWeekendPassArrivals.addActionListener(e -> {
            String input = setWeekendPassArrivals.getText();
            if (Functions.isInt(input)) {
                int value = Integer.parseInt(input);
                if (value >= 0) {
                    simulator.setWeekendPassArrivals(value);
                    view.updateView();
                }
            } else {
                setWeekendPassArrivals.setText("0 - 2147483647");
            }
        });

        setThursdayArrivals = new JTextField(4);
        setThursdayArrivals.addActionListener(e -> {
            String input = setThursdayArrivals.getText();
            if (Functions.isInt(input)) {
                int value = Integer.parseInt(input);
                if (value >= 0) {
                    simulator.setThursdayArrivals(value);
                    view.updateView();
                }
            } else {
                setThursdayArrivals.setText("0 - 2147483647");
            }
        });

        setEnterSpeed = new JTextField(4);
        setEnterSpeed.addActionListener(e -> {
            String input = setEnterSpeed.getText();
            if (Functions.isInt(input)) {
                int value = Integer.parseInt(input);
                if (value >= 0) {
                    simulator.setEnterSpeed(value);
                    view.updateView();
                }
            } else {
                setEnterSpeed.setText("0 - 2147483647");
            }
        });

        setPaymentSpeed = new JTextField(4);
        setPaymentSpeed.addActionListener(e -> {
            String input = setPaymentSpeed.getText();
            if (Functions.isInt(input)) {
                int value = Integer.parseInt(input);
                if (value >= 0) {
                    simulator.setPaymentSpeed(value);
                    view.updateView();
                }
            } else {
                setPaymentSpeed.setText("0 - 2147483647");
            }
        });

        setExitSpeed = new JTextField(4);
        setExitSpeed.addActionListener(e -> {
            String input = setExitSpeed.getText();
            if (Functions.isInt(input)) {
                int value = Integer.parseInt(input);
                if (value >= 0) {
                    simulator.setExitSpeed(value);
                    view.updateView();
                }
            } else {
                setExitSpeed.setText("0 - 2147483647");
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

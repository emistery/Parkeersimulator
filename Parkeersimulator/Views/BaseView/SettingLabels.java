package Parkeersimulator.Views.BaseView;
import Parkeersimulator.SimulatorController;
import Parkeersimulator.Views.AbstrView;
//import com.sun.webkit.ColorChooser;


import javax.swing.*;
import java.awt.*;

/**
 * Created by Lenovo T420 on 2-2-2017.
 */
public class SettingLabels extends JPanel implements AbstrView{
    private SimulatorController controller;
    private JLabel weekDayArrivals;
    private JLabel weekendArrivals;
    private JLabel weekDayPassArrivals;
    private JLabel weekendPassArrivals;
    private JLabel thursdayArrivals;

    private JLabel enterSpeed;
    private JLabel paymentSpeed;
    private JLabel exitSpeed;

    private JTextField setWeekDayArrivals;
    private JTextField setWeekendArrivals;
    private JTextField setWeekDayPassArrivals;
    private JTextField setWeekendPassArrivals;
    private JTextField setThursdayArrivals;
    private JTextField setEnterSpeed;
    private JTextField setPaymentSpeed;
    private JTextField setExitSpeed;

    public SettingLabels(){
        setBackground(new Color(43, 43, 43));

        GridLayout textGrid = new GridLayout(4,4);
        textGrid.setHgap(20);
        setLayout(textGrid);
        initializeLabels();
        initializeTextFields();

        add(weekDayArrivals);
        add(setWeekDayArrivals);
        add(weekendArrivals);
        add(setWeekendArrivals);
        add(weekDayPassArrivals);
        add(setWeekDayPassArrivals);
        add(weekendPassArrivals);
        add(setWeekendPassArrivals);

        add(enterSpeed);
        add(setEnterSpeed);
        add(paymentSpeed);
        add(setPaymentSpeed);
        add(exitSpeed);
        add(setExitSpeed);
        add(thursdayArrivals);
        add(setThursdayArrivals);


        setVisible(true);
    }
    public void setController(SimulatorController contr) {
            controller = contr;
            System.out.println(" added controller");
       updateView();
    }
    public Dimension getPreferredSize() {return new Dimension(800, 65);}

    public void updateView(){updateView(0, 0, 0, 0, 0, 0,0, "");}
    public void updateView(int tick, int adHocSpots, int passSpots, int cars, double earnings, double missedEarnings, int missedCars, String displayTime){
        weekDayArrivals.setText("weekDayArrivals "+controller.getWeekDayArrivals());
        weekendArrivals.setText("weekendArrivals "+controller.getWeekendArrivals());
        weekDayPassArrivals.setText("weekDayPassArrivals "+controller.getWeekDayPassArrivals());
        weekendPassArrivals.setText("weekendPassArrivals "+controller.getWeekendPassArrivals());
        thursdayArrivals.setText("thursdayArrivals "+controller.getThursdayArrivals());

        enterSpeed.setText("enterSpeed"+ controller.getEnterSpeed());
        paymentSpeed.setText("paymentSpeed"+ controller.getPaymentSpeed());
        exitSpeed.setText("exitSpeed"+ controller.getExitSpeed());
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
    private void initializeTextFields(){
        setWeekDayArrivals = new JTextField(4);
        setWeekDayArrivals.addActionListener(e -> {
            if(controller != null) {
                int value = Integer.parseInt(setWeekDayArrivals.getText());
                if (value > 0) {
                    controller.setWeekDayArrivals(value);
                    updateView();
                }
            }
        });
        setWeekendArrivals = new JTextField(4);
        setWeekendArrivals.addActionListener(e -> {
            if(controller != null) {
                int value = Integer.parseInt(setWeekendArrivals.getText());
                if (value > 0) {
                    controller.setWeekendArrivals(value);
                    updateView();
                }
            }
        });
        setWeekDayPassArrivals = new JTextField(4);
        setWeekDayPassArrivals.addActionListener(e -> {
            if(controller != null) {
                int value = Integer.parseInt(setWeekDayPassArrivals.getText());
                if (value > 0) {
                    controller.setWeekDayPassArrivals(value);
                    updateView();
                }
            }
        });
        setWeekendPassArrivals = new JTextField(4);
        setWeekendPassArrivals.addActionListener(e -> {
            if(controller != null) {
                int value = Integer.parseInt(setWeekendPassArrivals.getText());
                if (value > 0) {
                    controller.setWeekendPassArrivals(value);
                    updateView();
                }
            }
        });
        setThursdayArrivals = new JTextField(4);
        setThursdayArrivals.addActionListener(e -> {
            if(controller != null) {
                int value = Integer.parseInt(setThursdayArrivals.getText());
                if (value > 0) {
                    controller.setThursdayArrivals(value);
                    updateView();
                }
            }
        });


        setEnterSpeed = new JTextField(4);
        setEnterSpeed.addActionListener(e -> {
            if(controller != null) {
                int value = Integer.parseInt(setEnterSpeed.getText());
                if (value > 0) {
                    controller.setEnterSpeed(value);
                    updateView();
                }
            }
        });
        setPaymentSpeed = new JTextField(4);
        setPaymentSpeed.addActionListener(e -> {
            if(controller != null) {
                int value = Integer.parseInt(setPaymentSpeed.getText());
                if (value > 0) {
                    controller.setPaymentSpeed(value);
                    updateView();
                }
            }
        });
        setExitSpeed = new JTextField(4);
        setExitSpeed.addActionListener(e -> {
            if(controller != null) {
                int value = Integer.parseInt(setExitSpeed.getText());
                if (value > 0) {
                    controller.setExitSpeed(value);
                    updateView();
                }
            }
        });
    }

    public void disableView(){

    }
    public void enableView(){

    }
}

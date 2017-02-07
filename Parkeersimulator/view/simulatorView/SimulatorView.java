package Parkeersimulator.view.simulatorView;

import Parkeersimulator.controller.Controls;
import Parkeersimulator.controller.RunButtons;
import Parkeersimulator.controller.SettingLabels;
import Parkeersimulator.model.Simulator;
import Parkeersimulator.view.statisticView.AbstractView.AbstractView;
import Parkeersimulator.Time;

import javax.swing.*;
import java.awt.*;

public class SimulatorView extends AbstractView {
        private JFrame frame;
        private Controls controls;
        private JLabel tickLabel = new JLabel("0, Happy opening!");


    public SimulatorView(CarParkView carParkView, Simulator simulator) {
        super(simulator);
        frame = new JFrame();
        controls = new Controls(simulator);

        Container contentPane = frame.getContentPane();
        contentPane.add(tickLabel, BorderLayout.NORTH);
        contentPane.add(controls, BorderLayout.SOUTH);
        contentPane.add(carParkView, BorderLayout.CENTER);

        JPanel eastPanel = new JPanel();
        eastPanel.add(controls.getButtons().getTickPause());
        contentPane.add(eastPanel, BorderLayout.EAST);

        //automatically terminates jvm when closing window
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);

        carParkView.updateView();
        updateView(0, 0, 0, 0, 0.0, 0.0, 0, "");
    }
    public JFrame getFrame(){return  frame;}
    public RunButtons getButtons(){
        return controls.getButtons();
    }
    public SettingLabels getSettingLabels(){return controls.getSettingLabels();}

    public void updateView(int tick, int adHocSpots, int passSpots, int cars, double earnings, double missedEarnings, int missedCars, String displayTime) {
       String date = Time.getDate(tick);
        tickLabel.setText("Tick: "+tick + date);
    }
    public void disableView(){
        frame.setVisible(false);
    }
    public void enableView(){
        frame.setVisible(true);
    }

}

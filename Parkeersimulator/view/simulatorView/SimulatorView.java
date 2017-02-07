package Parkeersimulator.view.simulatorView;

import Parkeersimulator.controller.RunController;
import Parkeersimulator.model.Simulator;
import Parkeersimulator.view.statisticView.AbstractView.AbstractView;
import Parkeersimulator.Time;

import javax.swing.*;
import java.awt.*;

public class SimulatorView extends AbstractView {
        private JFrame frame;
        private RunController runController;
        private SettingLabels settingLabels;

        private JLabel tickLabel = new JLabel("0, Happy opening!");

    public SimulatorView(CarParkView carParkView, Simulator simulator) {
        super(simulator);
        frame = new JFrame();
        runController = new RunController(simulator);
        settingLabels = new SettingLabels(simulator);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(runController);
        panel.add(settingLabels);

        Container contentPane = frame.getContentPane();
        contentPane.add(tickLabel, BorderLayout.NORTH);
        contentPane.add(panel, BorderLayout.SOUTH);
        contentPane.add(carParkView, BorderLayout.CENTER);

        JPanel eastPanel = new JPanel();
        eastPanel.add(runController.getTickPause());
        contentPane.add(eastPanel, BorderLayout.EAST);

        //automatically terminates jvm when closing window
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);

        carParkView.updateView();
        updateView(0, 0, 0, 0, 0.0, 0.0, 0, "");
    }
    public JFrame getFrame(){return  frame;}
    public RunController getRunController(){
        return runController;
    }
    public SettingLabels getSettingLabels(){return settingLabels;}

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

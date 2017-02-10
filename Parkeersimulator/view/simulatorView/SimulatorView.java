package Parkeersimulator.view.simulatorView;

import Parkeersimulator.controller.MainController;
import Parkeersimulator.model.Simulator;
import Parkeersimulator.Functions;
import Parkeersimulator.view.abstractView.AbstractView;

import javax.swing.*;
import java.awt.*;

public class SimulatorView extends AbstractView {
        private JFrame frame;
        private MainController runController;
        private SettingLabels settingLabels;

        private JLabel tickLabel = new JLabel("0, Happy opening!");

    public SimulatorView(CarParkView carParkView, Simulator simulator) {
        super(simulator);
        frame = new JFrame();
        frame.setTitle("Parking Simulator");
        runController = new MainController(simulator);
        settingLabels = new SettingLabels(simulator);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(runController);
        panel.add(settingLabels);

        Container contentPane = frame.getContentPane();
        contentPane.add(tickLabel, BorderLayout.NORTH);
        contentPane.add(panel, BorderLayout.SOUTH);
        contentPane.add(carParkView, BorderLayout.CENTER);

        //automatically terminates jvm when closing window
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);

        carParkView.updateView();
        updateView();
    }
    public JFrame getFrame(){return  frame;}
    public MainController getRunController(){
        return runController;
    }
    public SettingLabels getSettingLabels(){return settingLabels;}

    public void updateView(){
        int tick = simulator.getTick();
       String date = Functions.getDate(tick);
        tickLabel.setText("Tick: "+tick + date + "          Red: Ad-hoc Cars    Blue: Parking pass cars     " +
                "Black: Reservation     Green: Car on reservated spot");
    }
    public void disableView(){
        frame.setVisible(false);
    }
    public void enableView(){
        frame.setVisible(true);
    }

}

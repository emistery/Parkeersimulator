package Parkeersimulator.Views.BaseView;

import Parkeersimulator.Views.AbstrView;
import Parkeersimulator.Views.Time;

import javax.swing.*;
import java.awt.*;

public class SimulatorView extends JFrame implements AbstrView {
        private CarParkView carParkView;
        private Controls controls;

    private JLabel tickLabel = new JLabel("0, Happy opening!");


    public SimulatorView(CarParkView carParkView) {
        controls = new Controls();
        this.carParkView = carParkView;
        Container contentPane = getContentPane();
        contentPane.add(tickLabel, BorderLayout.NORTH);
        contentPane.add(controls, BorderLayout.SOUTH);
        contentPane.add(carParkView, BorderLayout.CENTER);

        JPanel eastPanel = new JPanel();
        eastPanel.add(controls.getButtons().getTickPause());
        contentPane.add(eastPanel, BorderLayout.EAST);

        //automatically terminates jvm when closing window
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();

        setVisible(true);

        carParkView.updateView();
        updateView(0,0,0,0,0.0, 0.0, 0, "");
    }

    public void updateView(int tick, int adHocSpots, int passSpots, int cars, double earnings, double missedEarnings, int missedCars, String displayTime) {
       String date = Time.getDate(tick);
        tickLabel.setText("Tick: "+tick + date);
    }
    public void disableView(){
        setVisible(false);
    }
    public void enableView(){
        setVisible(true);
    }
    public RunButtons getButtons(){
        return controls.getButtons();
    }

}

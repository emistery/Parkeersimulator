package Parkeersimulator;

import javax.swing.*;
import java.awt.*;

public class SimulatorView extends JFrame implements AbstrView {
    private Buttons buttons;
    private CarParkView carParkView;
    private Simulator simulator;

    private JLabel tickLabel = new JLabel("0");

    public SimulatorView(Simulator simulator, CarParkView carParkView) {
        this.carParkView = carParkView;
        this.simulator = simulator;
        buttons = new Buttons(simulator);

        Container contentPane = getContentPane();
        contentPane.add(tickLabel, BorderLayout.NORTH);
        contentPane.add(buttons, BorderLayout.SOUTH);
        contentPane.add(carParkView, BorderLayout.CENTER);

        //automatically terminates jvm when closing window
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        carParkView.updateView();
        updateView(0,0,0,0,0.0);
    }

    public void updateView(int tick, int adHocSpots, int passSpots, int cars, double earnings) {
        tickLabel.repaint();
        tick(tick);
    }
    public void disableView(){
        setVisible(false);

    }
    public void tick(int tick) {
        tickLabel.setText("Tick: "+tick);
    }

    public Buttons getButtons(){
        return buttons;
    }

}

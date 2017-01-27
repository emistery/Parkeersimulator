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

        pack();
        setVisible(true);

        carParkView.updateView();
        updateView();
    }

    public void updateView() {
        tickLabel.repaint();
        tick(simulator.getTick());
    }
    public void tick(int tick) {
        tickLabel.setText("Tick: "+tick);
    }




}

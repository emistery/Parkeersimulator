package Parkeersimulator;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Runnable;

public class SimulatorView extends JFrame {

    private Buttons buttons;
    private CarParkView carParkView;



    private JLabel tickLabel = new JLabel("0");

    public SimulatorView(Simulator simulator, CarParkView carParkView) {

        this.carParkView = carParkView;
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

    }
    public void tick(int tick) {

        tickLabel.setText("Tick: "+tick);

    }




}

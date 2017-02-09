package Parkeersimulator.controller;

import Parkeersimulator.main.ParkeerSimulator;
import Parkeersimulator.model.Simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class MainController extends AbstractController implements ActionListener {
    private ParkeerSimulator parkeerSimulator;
    private JButton eenDag;
    private JButton eenWeek;
    private JButton eentick;

    private JButton stopSimulatie;
    private JButton addView;
    private JButton removeView;
    private JTextField textField;
    private JTextField tickPause;

    private Timer timer;

    public MainController(Simulator simulator) {
        super(simulator);
        eenDag = new JButton("1 day");
        eenDag.addActionListener(this);
        eenWeek = new JButton("1 week");
        eenWeek.addActionListener(this);
        eentick = new JButton("1 minute");
        eentick.addActionListener(this);

        stopSimulatie = new JButton("Stop simulation");
        stopSimulatie.addActionListener(this);
        textField = new JTextField(5);
        textField.addActionListener(this);
        tickPause = new JTextField(4);
        tickPause.addActionListener(this);

        addView = new JButton("More information");

        addView.addActionListener(e -> {
            if(parkeerSimulator != null){
                parkeerSimulator.getStatisticView().enableView();}
        });
        removeView = new JButton("Less information");
        removeView.addActionListener(e -> {
            if(parkeerSimulator != null){
                parkeerSimulator.getStatisticView().disableView();}
        });
        add(eentick);
        add(eenDag);
        add(eenWeek);
        add(textField);
        add(stopSimulatie);
        add(tickPause);
        add(addView);
        add(removeView);

        eenDag.setBounds(25, 10, 50, 30);
        eenWeek.setBounds(100, 10, 50, 30);
        eentick.setBounds(175, 10, 50, 30);

        setBackground(Color.black);
        setVisible(true);
    }
    public JTextField getTickPause(){
        return tickPause;
    }
    public Dimension getPreferredSize() {return new Dimension(235, 35);}

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == eenDag){
            simulator.doTicks(1440);
        }

        if(e.getSource() == eenWeek){
            simulator.doTicks(10080);
        }
        if(e.getSource() == eentick){
            simulator.doTicks(1);
        }
        if(e.getSource() == textField) {

            String text = textField.getText();
            int ticker = Integer.parseInt(text);
            simulator.doTicks(ticker);
        }
        if(e.getSource() == stopSimulatie) {
            simulator.setNumberOfTicks(0);
        }
        if(e.getSource() == tickPause) {
            int time = Integer.parseInt(tickPause.getText());
            if(time>=0) {
                simulator.setTickPause(time);
            }
        }
    }

    private void enableButtons(){
        eenDag.setEnabled(true);
        eenWeek.setEnabled(true);
        eentick.setEnabled(true);
        textField.setEnabled(true);
    }

    private void disableButtons(){
        eenDag.setEnabled(false);
        eenWeek.setEnabled(false);
        eentick.setEnabled(false);
        textField.setEnabled(false);
    }
    public void setParkeerSimulator(ParkeerSimulator parkSimulator){
        parkeerSimulator = parkSimulator;
    }

}
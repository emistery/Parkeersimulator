package Parkeersimulator.Views;

import Parkeersimulator.Simulator;
import Parkeersimulator.SimulatorController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Buttons extends JPanel implements ActionListener {
    private StatisticView statView;
    private SimulatorController controller;
    private Simulator simulator;
    private JButton eenDag;
    private JButton eenWeek;
    private JButton duizendStep;
    private JButton addView;
    private JButton removeView;
    private JTextField textField;
    private JTextField tickPause;

    private Timer timer;

    public Buttons(Simulator simulator) {
        this.simulator = simulator;

        eenDag = new JButton("1 dag");
        eenDag.addActionListener(this);
        eenWeek = new JButton("1week");
        eenWeek.addActionListener(this);
        duizendStep = new JButton("1000 minuten");
        duizendStep.addActionListener(this);

        textField = new JTextField(5);
        textField.addActionListener(this);
        tickPause = new JTextField(4);
        tickPause.addActionListener(this);

        addView = new JButton("Meer informatie");

        addView.addActionListener(e -> {
            if(controller != null){controller.addView(controller.getStatisticView());}
        });
        removeView = new JButton("Minder informatie");
        removeView.addActionListener(e -> {
            if(controller != null){controller.removeView(controller.getStatisticView());}
        });
        add(eenDag);
        add(eenWeek);
        add(duizendStep);
        add(textField);
        add(tickPause);
        add(addView);
        add(removeView);

        eenDag.setBounds(25, 10, 50, 30);
        eenWeek.setBounds(100, 10, 50, 30);
        duizendStep.setBounds(175, 10, 50, 30);

        setBackground(Color.black);
        setVisible(true);
    }
    public JTextField getTickPause(){
        return tickPause;
    }
    //public Dimension setPreferredSize() {return new Dimension(250, 250);}

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == eenDag){
            simulator.run(1440);
            disableButtons();
            timer = new Timer( simulator.getTickPause() * 1 + 200, new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    enableButtons();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
        if(e.getSource() == eenWeek){
            simulator.run(10080);
            disableButtons();
            timer = new Timer(simulator.getTickPause() * 100 + 200, new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    enableButtons();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
        if(e.getSource() == duizendStep){
            simulator.run(1000);
            disableButtons();
            timer = new Timer(simulator.getTickPause() * 1000 + 200, new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    enableButtons();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
        if(e.getSource() == textField) {

            String text = textField.getText();
            int ticker = Integer.parseInt(text);
            simulator.run(ticker);
            disableButtons();
            int delayFree = simulator.getTickPause() * ticker + 200;
            timer = new Timer(delayFree, new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    enableButtons();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
        if(e.getSource() == tickPause) {
            int time = Integer.parseInt(tickPause.getText());
            if(time>0) {
                simulator.setTickPause(time);
            }
        }
    }

    private void enableButtons(){
        eenDag.setEnabled(true);
        eenWeek.setEnabled(true);
        duizendStep.setEnabled(true);
        textField.setEnabled(true);
    }

    private void disableButtons(){
        eenDag.setEnabled(false);
        eenWeek.setEnabled(false);
        duizendStep.setEnabled(false);
        textField.setEnabled(false);
    }
    public void setController(SimulatorController contr){
        controller = contr;
    }

}
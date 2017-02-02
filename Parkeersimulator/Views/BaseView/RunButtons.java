package Parkeersimulator.Views.BaseView;

import Parkeersimulator.SimulatorController;
import Parkeersimulator.Views.StatisticView.StatisticView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class RunButtons extends JPanel implements ActionListener {
    private SimulatorController controller;
    private JButton eenDag;
    private JButton eenWeek;
    private JButton eentick;
    private JButton addView;
    private JButton removeView;
    private JTextField textField;
    private JTextField tickPause;

    private Timer timer;

    public RunButtons() {
        eenDag = new JButton("1 dag");
        eenDag.addActionListener(this);
        eenWeek = new JButton("1week");
        eenWeek.addActionListener(this);
        eentick = new JButton("1 minuut");
        eentick.addActionListener(this);

        textField = new JTextField(5);
        textField.addActionListener(this);
        tickPause = new JTextField(4);
        tickPause.addActionListener(this);

        addView = new JButton("Meer informatie");

        addView.addActionListener(e -> {
            if(controller != null){controller.enableView(controller.getStatisticView());}
        });
        removeView = new JButton("Minder informatie");
        removeView.addActionListener(e -> {
            if(controller != null){controller.disableView(controller.getStatisticView());}
        });
        add(eenDag);
        add(eenWeek);
        add(eentick);
        add(textField);
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
            controller.runSimulator(1440);
            disableButtons();
            timer = new Timer( controller.getTickPause() * 1440 + 200, new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    enableButtons();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
        if(e.getSource() == eenWeek){
            controller.runSimulator(10080);
            disableButtons();
            timer = new Timer(controller.getTickPause() * 10080 + 200, new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    enableButtons();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
        if(e.getSource() == eentick){
            controller.runSimulator(1);
            disableButtons();
            timer = new Timer(controller.getTickPause() * 1 + 200, new ActionListener(){
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
            controller.runSimulator(ticker);
            disableButtons();
            int delayFree = controller.getTickPause() * ticker + 200;
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
            if(time>=0) {
                controller.setTickPause(time);
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
    public void setController(SimulatorController contr){
        controller = contr;
    }

}
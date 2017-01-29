package Parkeersimulator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Buttons extends JPanel implements ActionListener {
    private Simulator simulator;
    private JButton eenStep;
    private JButton honderdStep;
    private JButton duizendStep;
    private JTextField textField;

    private Timer timer;
    private final int delayEen;
    private final int delayHonderd;
    private final int delayDuizend;

    public Buttons(Simulator simulator) {
        this.simulator = simulator;

        eenStep = new JButton("1 tick");
        eenStep.addActionListener(this);
        honderdStep = new JButton("100 ticks");
        honderdStep.addActionListener(this);
        duizendStep = new JButton("1000 ticks");
        duizendStep.addActionListener(this);
        textField = new JTextField(5);
        textField.addActionListener(this);

        add(eenStep);
        add(honderdStep);
        add(duizendStep);
        add(textField);

        eenStep.setBounds(25, 10, 50, 30);
        honderdStep.setBounds(100, 10, 50, 30);
        duizendStep.setBounds(175, 10, 50, 30);

        setVisible(true);

        delayEen = simulator.getTickPause() * 1 + 200;
        delayHonderd = simulator.getTickPause() * 100 + 200;
        delayDuizend = simulator.getTickPause() * 1000 + 200;
    }
    //public Dimension setPreferredSize() {return new Dimension(250, 250);}

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == eenStep){
            simulator.run(1);
            disableButtons();
            timer = new Timer(delayEen, new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    enableButtons();
                }
            });
            timer.start();
        }
        if(e.getSource() == honderdStep){
            simulator.run(100);
            disableButtons();
            timer = new Timer(delayHonderd, new ActionListener(){
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
            timer = new Timer(delayDuizend, new ActionListener(){
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
    }

    private void enableButtons(){
        eenStep.setEnabled(true);
        honderdStep.setEnabled(true);
        duizendStep.setEnabled(true);
        textField.setEnabled(true);
    }

    private void disableButtons(){
        eenStep.setEnabled(false);
        honderdStep.setEnabled(false);
        duizendStep.setEnabled(false);
        textField.setEnabled(false);
    }

}
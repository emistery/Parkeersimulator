package Parkeersimulator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends JPanel implements ActionListener {
    private Simulator simulator;
    private JButton eenStep;
    private JButton honderdStep;
    private JButton duizendStep;

    public Controller(Simulator simulator) {
        this.simulator = simulator;

        eenStep = new JButton("1 tick");
        eenStep.addActionListener(e -> simulator.run(1));
        honderdStep = new JButton("100 ticks");
        honderdStep.addActionListener(e ->  simulator.run(100));
        duizendStep = new JButton("1000 ticks");
        duizendStep.addActionListener(e ->  simulator.run(1000));

        add(eenStep);
        add(honderdStep);
        add(duizendStep);
        eenStep.setBounds(25, 10, 50, 30);
        honderdStep.setBounds(100, 10, 50, 30);
        duizendStep.setBounds(175, 10, 50, 30);
        setVisible(true);

    }
    //public Dimension setPreferredSize() {return new Dimension(250, 250);}

    public void actionPerformed(ActionEvent e) {
        //
    }


}
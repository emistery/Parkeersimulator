package Parkeersimulator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons extends JPanel implements ActionListener {
    private Simulator simulator;
    private JButton eenStep;
    private JButton honderdStep;
    private JButton duizendStep;
    private JTextField textField;





    public Buttons(Simulator simulator) {
        this.simulator = simulator;

        eenStep = new JButton("1 tick");
        eenStep.addActionListener(e -> simulator.run(1));
        honderdStep = new JButton("100 ticks");
        honderdStep.addActionListener(e ->  simulator.run(100));
        duizendStep = new JButton("1000 ticks");
        duizendStep.addActionListener(e ->  simulator.run(1000));
        textField = new JTextField(20);
        textField.addActionListener(this);




        add(eenStep);
        add(honderdStep);
        add(duizendStep);
        add(textField);
        eenStep.setBounds(25, 10, 50, 30);
        honderdStep.setBounds(100, 10, 50, 30);
        duizendStep.setBounds(175, 10, 50, 30);
        setVisible(true);

    }
    //public Dimension setPreferredSize() {return new Dimension(250, 250);}

    public void actionPerformed(ActionEvent e) {
        String text = textField.getText();
        int ticker = Integer.parseInt(text);

        simulator.run(ticker);
    }



}
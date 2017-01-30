package Parkeersimulator.Views;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Emiel on 30-1-2017.
 */
public class ChartView extends JPanel{

    public ChartView(){
        JPanel panel = new JPanel();

        FlowLayout layout = new FlowLayout(FlowLayout.LEADING);
        panel.setLayout(layout);
        panel.setComponentOrientation(
                ComponentOrientation.LEFT_TO_RIGHT);

        panel.setOpaque(true);
        panel.setBackground(new Color(248, 213, 131));
        panel.setPreferredSize(new Dimension(400, 360));

        //PieChart p = new PieChart();




    }
}

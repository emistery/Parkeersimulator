/**
 * Created by Lenovo T420 on 27-1-2017.
 */
package Parkeersimulator;
import javax.swing.*;
import java.awt.*;

public class StatisticView {
    private SimulatorController controller;

    private JFrame frame;
    private JTextPane textPane = new JTextPane();

    private JLabel tickLabel = new JLabel("tick: 0");

  public StatisticView() {
      //controller = contr;

      frame = new JFrame();
      Container contentPane = frame.getContentPane();
      contentPane.setLayout(new FlowLayout());
      contentPane.add(textPane);
      contentPane.add(tickLabel);

      frame.pack();
      frame.setVisible(true);
  }
    public static void main(String[] args)
    {
        StatisticView view = new StatisticView();
    }
}

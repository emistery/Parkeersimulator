/**
 * Created by Lenovo T420 on 27-1-2017.
 */
package Parkeersimulator;
import javax.swing.*;
import java.awt.*;

public class StatisticView extends JFrame {
    JTextPane textPane = new JTextPane();
    public static void main(String[] args)
    {
        StatisticView view = new StatisticView();
    }
  public StatisticView() {
      Container contentPane = getContentPane();
      contentPane.add(textPane);

      pack();
      setVisible(true);
  }

}

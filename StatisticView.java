/**
 * Created by Lenovo T420 on 27-1-2017.
 */
package Parkeersimulator;
import javax.swing.*;
import java.awt.*;

public class StatisticView implements AbstrView{
    private JFrame frame;

    private JLabel tickLabel = new JLabel("tick: ");
    private JLabel carLabel = new JLabel("amount of cars : ");
    private JLabel adhocLabel = new JLabel("free Ad Hoc Spots: ");
    private JLabel passLabel = new JLabel("free Pass Spots: ");
    private JLabel earningsLabel = new JLabel("Total earnings: ");

  public StatisticView() {
      frame = new JFrame();
      Container contentPane = frame.getContentPane();
      JPanel panel = createPanel();
      contentPane.add(panel, BorderLayout.CENTER);
      panel.setBackground(Color.LIGHT_GRAY);
      panel.add(carLabel);
      panel.add(tickLabel);
      panel.add(adhocLabel);
      panel.add(passLabel);
      panel.add(earningsLabel);
      panel.repaint();

      frame.pack();
     // frame.setVisible(true);
  }
  public JPanel createPanel() {
      //Create a yellow label to put in the content pane.
      JPanel panel = new JPanel();
      FlowLayout layout = new FlowLayout(FlowLayout.LEADING);
      panel.setLayout(layout);
      panel.setComponentOrientation(
              ComponentOrientation.LEFT_TO_RIGHT);

      panel.setOpaque(true);
      panel.setBackground(new Color(248, 213, 131));
      panel.setPreferredSize(new Dimension(200, 180));
      return panel;
  }

  public void updateView(int tick, int adHocSpots, int passSpots, int cars, double earnings){
      if(!frame.isVisible()){
          frame.setVisible(true);
      }
      carLabel.setText("amount of open spots: " + cars);
      tickLabel.setText("amount of ticks: " + tick);
      adhocLabel.setText("amount of open Ad Hoc spots: " + adHocSpots);
      passLabel.setText("amount of open Pass spots: " + passSpots);
      earningsLabel.setText("Total earnings :" + earnings);
      frame.repaint();
  }
  public void disableView(){
      frame.setVisible(false);
  }

}

package Parkeersimulator.Views.BaseView;
import javax.swing.*;
/**
 * Created by Lenovo T420 on 2-2-2017.
 */
public class SettingLabels extends JPanel{
    JLabel weekDayArrivals;
    JLabel weekendArrivals;
    JLabel weekDayPassArrivals;
    JLabel weekendPassArrivals;

    JLabel enterSpeed;
    JLabel paymentSpeed;
    JLabel exitSpeed;

    public SettingLabels(){
        weekDayArrivals = new JLabel();
        weekendArrivals = new JLabel();
        weekDayPassArrivals = new JLabel();
        weekendPassArrivals = new JLabel();

        enterSpeed = new JLabel();
        paymentSpeed = new JLabel();
        exitSpeed = new JLabel();
    }
}

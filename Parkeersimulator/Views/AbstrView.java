/**
 * Created by Lenovo T420 on 27-1-2017.
 */
package Parkeersimulator.Views;
public interface AbstrView {
    void updateView(int tick, int adHocSpots, int passSpots, int cars, double earnings, double missedEarnings, int missedCars, String displayTime);
    void disableView();
    void enableView();
}

/**
 * Created by Lenovo T420 on 27-1-2017.
 */
package Parkeersimulator;
public interface AbstrView {

    void updateView(int tick, int adHocSpots, int passSpots, int cars, double earnings);
    void disableView();
}

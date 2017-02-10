package Parkeersimulator.model;

import Parkeersimulator.view.abstractView.AbstractView;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Lenovo T420 on 10-2-2017.
 */
public abstract class AbstractModel {
    //set true while the simulator is updating views
    protected boolean updatingViews;
    protected ArrayList<AbstractView> views = new ArrayList<>();
    /**
     * notifies the subscribed view to state changes and calls their updateView method
     */
    public void updateViews() {
        updatingViews =true;
        for(AbstractView view : views){
            view.updateView();
        }
        updatingViews =false;
    }

    /**
     * subscribes a view to the simulation to be notified of updates
     * @param view the view to be subscribed
     */
    public void addView(AbstractView view)
    {
        (new Thread(() -> {
            while(updatingViews) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            views.add(view);

        })).start();
    }

    /**
     * removes a view from the list of views to be notified of updates
     * @param view the view to be removed
     */
    public void removeView(AbstractView view){
        (new Thread(() -> {
            while(updatingViews) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Iterator it = views.iterator();
            while (it.hasNext()) {
                AbstractView bla = (AbstractView) it.next();
                if (view == bla) {
                    it.remove();
                }
            }
        })).start();
    }
}


package Parkeersimulator;

import Parkeersimulator.Views.BaseView.RunButtons;
import Parkeersimulator.Views.BaseView.SettingLabels;

public class SimulatorRunner {
    public static void main(String[] args)
    {
        SimulatorController controller = new SimulatorController();
        RunButtons runButtons = controller.getSimulatorView().getButtons();
        SettingLabels settingLabels = controller.getSimulatorView().getSettingLabels();
        runButtons.setController(controller);
        settingLabels.setController(controller);

        controller.getStatisticView().setController(controller);
        controller.addView(settingLabels);

        controller.getSimulator().addView(controller.getStatisticView());
    }

}

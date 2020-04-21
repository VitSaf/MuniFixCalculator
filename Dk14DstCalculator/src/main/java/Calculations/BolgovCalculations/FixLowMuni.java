package Calculations.BolgovCalculations;

import Calculations.Calculations;
import Scenes.LowMuniScene;

public class FixLowMuni extends Calculations {
    /**
     * ищем добавление полимеризата
     * затем итоговое маслонаполнение
     * Муни и Итоговый Муни известны
     * @param o
     * @return новое маслонаполнение
     */
    @Override
    public double calculate(Object o) {
        LowMuniScene scene = (LowMuniScene) o;
        double V = (scene.getCurrentL() / 100) * Calculations.USREDNITEL_V;
        double addPolimer = scene.getCurrentL() * (scene.getTargetMuni() - scene.getCurrentMuni())/ (scene.getMuniBatarei() - scene.getTargetMuni());
        scene.setPolimer(addPolimer);
        double G23 = (V * (scene.getRoUsr() / 100) * (scene.getSuhoyOstUsr() / 100));//0.16632 , темповая ячейка в исходном ехселе
        double newMaslo = ((G23 * scene.getMaslo()) / ((100 * G23) + ((addPolimer * Calculations.USREDNITEL_V/100) * scene.getRoBat() * (scene.getSuhoyOstBat() / 100))))* 100;
        //scene.setNewMaslo(newMaslo);
        return newMaslo;
    }

}

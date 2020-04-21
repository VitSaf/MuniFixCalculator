package Calculations.BolgovCalculations;

import Calculations.Calculations;
import Scenes.HighMuniScene;

public class FixHighMuni extends Calculations {
    @Override
    /**
     * Считаем сколько добавить тонн масла
     * Итоговое маслонаполнение
     * return  новое маслонаполнение
     */
    public double calculate(Object o) {
        HighMuniScene scene = (HighMuniScene)o;
        //Считать с V или L?
        double V = (scene.getCurrentL()/100) * Calculations.USREDNITEL_V;
        double G23 = (V * (scene.getRoUsr() / 100) * (scene.getSuhoyOstUsr() / 100));//0.16632 , темповая ячейка в исходном ехселе
        double I23 = scene.getCurrentMuni() - scene.getTargetMuni();
        double H23 = (scene.getMuniBatarei() - scene.getCurrentMuni())/ scene.getMaslo();
        double addMaslo = I23/H23*G23;//H18

        scene.setAddMaslo(addMaslo);

        return (V*scene.getRoUsr()*scene.getSuhoyOstUsr()/100*scene.getMaslo()/100+addMaslo)/(V*scene.getSuhoyOstUsr()/100*scene.getRoUsr()+addMaslo)*100;
    }
}

package Calculations;

import Frames.MakeMuniHigherFrame;
import Scenes.LowMuniScene;

public class LowMuniCalculations extends Calculations {
    /**
     *
     * @param o
     * @return новый Муни
     * Сколько нужно добавить полимера, чтобы повысить уровень Муни
     */

    @Override
    public double calculate(Object o) {
        //MakeMuniHigherFrame frame = (MakeMuniHigherFrame) o;
        LowMuniScene frame = (LowMuniScene) o;
        //double currentV = (frame.getCurrentL()/100) * USREDNITEL_V;
        double polimerMax = frame.getMaslo() - MIN_MASLO_LVL;
        //System.out.println("Максимально доступное количество полимера, % : " + polimerMax);
        double x, y, newMuni = 0;
        double polimer = 0.5;
        while (polimer < polimerMax && newMuni < Double.parseDouble(frame.getInputTargetMuniField().getText())){
            x = frame.getCurrentL() * frame.getCurrentMuni();
            y = frame.getMuniBatarei() * polimer;
            newMuni = (x + y) / (frame.getCurrentL() + polimer);
            //System.out.println("Новый Муни при " + polimer + "% полимера: " + newMuni);
            polimer += 0.1;
            if(polimer > polimerMax) {//Если результат ошибочный, то ищи ошибку здесь
                polimer = polimerMax;
                y = frame.getMuniBatarei() * polimer;
                frame.setPolimer(polimer);
                frame.setNewMuni(newMuni);
                frame.setNewMaslo(frame.getMaslo() - polimer);
                return (x + y) / (frame.getCurrentL() + polimer);
            }
        }
        //System.out.println("Добавляется полимера % = " + polimer);

        frame.setPolimer(polimer);
        frame.setNewMuni(newMuni);
        frame.setNewMaslo(frame.getMaslo() - polimer);
        return newMuni; //возвращает новый Муни
    }
}

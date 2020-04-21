package Calculations;

import Frames.MakeMuniLowerFrame;
import Scenes.HighMuniScene;

import javax.swing.*;

public class HighMuniCalculations extends Calculations {
    @Override
    public double calculate(Object o) {
        //MakeMuniLowerFrame frame = (MakeMuniLowerFrame) o;
        HighMuniScene frame = (HighMuniScene)o;
        //Считать с V или L?
        double currentV = (frame.getCurrentL()/100) * USREDNITEL_V;
        double previshenieMuni = frame.getCurrentMuni() - Double.parseDouble(frame.getInputTargetMuniField().getText());
        /*
        Превышение/усадку от 1% и * на массу 1% т.е. кол-во % необходимых для нормализации Муни на массу в тоннах умножаем
         */
        double prosadka = (frame.getMuniBatarei() - frame.getFactMuni())/frame.getMaslo();
        //System.out.println("От 1% масла Муни уменьшится на(prosadka) " + prosadka);
        double percents = /*(int)*/(previshenieMuni/makeMuniLowerPercents(frame.getFactMuni(), frame.getMuniBatarei(), frame.getMaslo()));
        //System.out.println("В одном проценте тонн масла: " +  makeMuniLowerMassInOnePercent(currentV, frame.getSuhoyOstatok(), frame.getRo()));
        //System.out.println("Добавится процентов: " + percents);

        if(frame.getMaslo() + percents < MAX_MASLO_LVL){
            //System.out.println("Нет превышения, добавляется " + percents + "% масла");
            //System.out.println("Новый Муни = " + (frame.getCurrentMuni() - percents*prosadka));
            frame.setNewMuni(frame.getCurrentMuni() - percents*prosadka);
            frame.setNewMaslo(frame.getMaslo() + percents);
            //return percents * makeMuniLowerMassInOnePercent(frame.getCurrentL(), frame.getSuhoyOstatok(), frame.getRo());
            return percents * makeMuniLowerMassInOnePercent(currentV, frame.getSuhoyOstatok(), frame.getRo());
        } else {
            percents = MAX_MASLO_LVL - frame.getMaslo();
            //System.out.println("Превышение, процентов масла добавить = " + percents);
            if(percents <= 0) {
                JOptionPane.showMessageDialog(null,"Добавление масла приведет к превышению верхнего уровня (28.8). Брак!!!");
                frame.setNewMuni(frame.getCurrentMuni());
                return 0;
            }
            //System.out.println("Новый Муни = " + (frame.getCurrentMuni() - percents*prosadka));
            frame.setNewMaslo(frame.getMaslo() + percents);
            frame.setNewMuni(frame.getCurrentMuni()-percents*prosadka);
            return  percents * makeMuniLowerMassInOnePercent(currentV, frame.getSuhoyOstatok(), frame.getRo());
        }
    }
    /**
     *
     * @param currentV текущий объем в-ва в усреднителе
     * @param suhoyOstatok Сухой остаток
     * @param ro плотность. Примерно 0,776. Можно ли забить её в константы?
     * @return масса масла в 1 проценте
     */
    public static double makeMuniLowerMassInOnePercent(double currentV, double suhoyOstatok, double ro){
        //текущий объём в емкости
        //TODO check
        return (currentV * suhoyOstatok * ro) /*/10*/ ;
    }

    /**
     *
     * @param muniFact Текущий Муни в усреднителе
     * @param muniSBatarei Муни батареи
     * @param averageHourlyDozaMasla средне часовая доза масла
     * @return усадка Муни при добавлении 1% масла
     *
     */
    public static double makeMuniLowerPercents(double muniFact,double muniSBatarei, double averageHourlyDozaMasla){
        return (muniSBatarei - muniFact)/averageHourlyDozaMasla;//просадка
    }
}

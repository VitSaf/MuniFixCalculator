package Calculations;

import Frames.MakeMuniHigherFrame;
import Frames.MakeMuniLowerFrame;

import javax.swing.*;

import static Calculations.Calculations.*;
import static Calculations.HighMuniCalculations.makeMuniLowerMassInOnePercent;
import static Calculations.HighMuniCalculations.makeMuniLowerPercents;

@Deprecated
public class FixMuni {






    /**
     *
     * @param currentMuni текущий уровень Муни *
     * @param muniFact Муни факт?
     * @param muniSBatarei Муни на батарее
     * @param averageHourlyDozaMasla средне часова дозировка масла
     * @param currentL уровень в усреднителе
     * @param suhoyOstatok сухой остаток
     * @param ro плотность
     * @param polimer сколько добавиться полимера для увеличения Муни
     * @return сколько добавить тонн масла в усреднитель
     * Добавить ограничение по превышению масла от 26,2 до 28,8
     */
    @Deprecated
    public static double calculateFixMuni(double currentMuni, double muniFact,double muniSBatarei, double averageHourlyDozaMasla,
                                          double currentL, double suhoyOstatok, double ro, double polimer){
        double currentV = (currentL/100) * USREDNITEL_V;
        double previshenieMuni = currentMuni - PERFECT_MUNI_LVL;
        double result;
        if(previshenieMuni > 0){
            result = (int)(previshenieMuni/makeMuniLowerPercents(muniFact, muniSBatarei, averageHourlyDozaMasla)) * makeMuniLowerMassInOnePercent(currentV, suhoyOstatok, ro);//тестим
        }
        else if (previshenieMuni < 0){//Какое будет Муни, если добавить столько-то процентов polimer'a
            double x = currentV * currentMuni;
            double y = muniSBatarei * polimer;
            result = (x + y)/(currentV + polimer);
        }
            else {
                    JOptionPane.showMessageDialog(null,"Идеальный Муни");
                    return 0;
            }
         return result;
    }


    @Deprecated
    public static double makeMuniLowerCalculations(double muniFact,double muniSBatarei, double averageHourlyDozaMasla,//перепроверить и ограничить
                                                   double currentL, double suhoyOstatok, double ro, double currentMuni){
        double currentV = (currentL/100) * USREDNITEL_V;
        double previshenieMuni = currentMuni - PERFECT_MUNI_LVL;
        /*
        Превышение/усадку от 1% и * на массу 1% т.е. кол-во % необходимых для нормализации Муни на массу в тоннах умножаем
        Так?
         */
        double percents = (int)(previshenieMuni/makeMuniLowerPercents(muniFact, muniSBatarei, averageHourlyDozaMasla));
        System.out.println("В одном проценте: " +  makeMuniLowerMassInOnePercent(currentV, suhoyOstatok, ro));
        if(averageHourlyDozaMasla + percents < MAX_MASLO_LVL){
            System.out.println("Нет превышения, " + percents);
            return percents * makeMuniLowerMassInOnePercent(currentV, suhoyOstatok, ro);
        } else {
            while (averageHourlyDozaMasla + percents > MAX_MASLO_LVL){
                percents -= 0.5;
            }
            System.out.println("Превышение, процентов масла добавить = " + percents);
            return  percents * makeMuniLowerMassInOnePercent(currentV, suhoyOstatok, ro);
        }
    }




    //TODO аналогично ограничить масло из расчета, что 1% полимера съест 1% масла. Верен ли такой расчет?
    @Deprecated
    public static double makeMuniHigherCalculations(double currentL,double polimer, double muniBatarei, double currentMuni){
        double currentV = (currentL/100) * USREDNITEL_V;
        double x = currentV * currentMuni;
        double y = muniBatarei * polimer;
        return (x + y)/(currentV + polimer);
    }

    @Deprecated
    public static double makeMuniHigherCalculations(MakeMuniHigherFrame frame){
        double currentV = (frame.getCurrentL()/100) * USREDNITEL_V;
        //double x = currentV * frame.getCurrentMuni();
        double x = frame.getCurrentL() * frame.getCurrentMuni();
        double y = frame.getMuniBatarei() * frame.getPolimer();
        return (x + y)/(frame.getCurrentL()+ frame.getPolimer());
    }


    //TODO уточнить маслопоглощение. Пока тестовый вариант
    //Возможно, стоит добавить масло, если маслопоглощение такое большое
    /**
     *
     * @param currentL уровень в усреднителе
     * @param muniBatarei Муни на батарее
     * @param currentMuni текущий Муни в усреднителе
     * @param masloLvl текущий уровень масла в каучуке
     * @return
     * ограничение по маслу от 26,2 до 28,8
     * берем ТекущийУровеньМасла - МинимальныйУровеньМасла = СколькоПроцентовПолимераДоБракаМожноДобавить
     */
    @Deprecated
    public static double makeMuniHigherAutoPolimerCalculations(double currentL, double muniBatarei, double currentMuni, double masloLvl){
        double polimer = masloLvl - MIN_MASLO_LVL;
        System.out.println("polimer % = " + polimer);
        double currentV = (currentL/100) * USREDNITEL_V;
        double x = currentV * currentMuni;
        double y = muniBatarei * polimer;
        return (x + y)/(currentV + polimer);
    }



    public static double makeMuniLowerCalculations(MakeMuniLowerFrame frame){
        //double currentV = (frame.getCurrentL()/100) * USREDNITEL_V;
        double previshenieMuni = frame.getCurrentMuni() - PERFECT_MUNI_LVL;
        /*
        Превышение/усадку от 1% и * на массу 1% т.е. кол-во % необходимых для нормализации Муни на массу в тоннах умножаем
        Так?
         */
        double prosadka = (frame.getMuniBatarei() - frame.getFactMuni())/frame.getMaslo();
        System.out.println("От 1% масла Муни уменьшится на " + prosadka);
        double percents = /*(int)*/(previshenieMuni/makeMuniLowerPercents(frame.getFactMuni(), frame.getMuniBatarei(), frame.getMaslo()));
        System.out.println("В одном проценте тонн масла: " +  makeMuniLowerMassInOnePercent(frame.getCurrentL(), frame.getSuhoyOstatok(), frame.getRo()));
        System.out.println("Добавится процентов: " + percents);
        if(frame.getMaslo() + percents < MAX_MASLO_LVL){
            if(percents == 0 && frame.getCurrentMuni() > MAX_MUNI_LVL) percents = 1;//
            System.out.println("Нет превышения, добавляется " + percents + "% масла");
            System.out.println("Новый Муни = " + (frame.getCurrentMuni() - percents*prosadka));
            frame.setNewMuni(frame.getCurrentMuni() - percents*prosadka);
            return percents * makeMuniLowerMassInOnePercent(frame.getCurrentL(), frame.getSuhoyOstatok(), frame.getRo());
        } else {
            while (frame.getMaslo() + percents > MAX_MASLO_LVL){
                percents -= 0.5;
            }
            System.out.println("Превышение, процентов масла добавить = " + percents);
            if(percents == 0) {
                JOptionPane.showMessageDialog(null,"Добавление масла приведет к превышению верхнего уровня (28.8). Брак!!!");
                frame.setNewMuni(frame.getCurrentMuni());
                return 0;
            }
            System.out.println("Новый Муни = " + (frame.getCurrentMuni() - percents*prosadka));
            frame.setNewMaslo(frame.getMaslo() + percents);
            frame.setNewMuni(frame.getCurrentMuni()-percents*prosadka);
            return  percents * makeMuniLowerMassInOnePercent(frame.getCurrentL(), frame.getSuhoyOstatok(), frame.getRo());
        }
    }

    //TODO Избавиться от безумия в конце
    public static double makeMuniHigherAutoPolimerCalculations(MakeMuniHigherFrame frame){
        //double currentV = (frame.getCurrentL()/100) * USREDNITEL_V;
        double polimerMax = frame.getMaslo() - MIN_MASLO_LVL;
        System.out.println("Максимально доступное количество полимера, % : " + polimerMax);
        double x, y, newMuni = 0;
        double polimer = 0.5;
        while (polimer < polimerMax && newMuni < MIN_MUNI_LVL){
            x = frame.getCurrentL() * frame.getCurrentMuni();
            y = frame.getMuniBatarei() * polimer;
            newMuni = (x + y) / (frame.getCurrentL() + polimer);
            System.out.println("Новый Муни при " + polimer + "% полимера: " + newMuni);
            polimer += 0.5;
            if(polimer > polimerMax) {
                polimer = polimerMax;
                y = frame.getMuniBatarei() * polimer;
                frame.setPolimer(polimer);
                frame.setNewMuni(newMuni);
                frame.setNewMaslo(frame.getMaslo() - polimer);
                return (x + y) / (frame.getCurrentL() + polimer);
            }
        }
        System.out.println("Добавляется полимера % = " + polimer);

        frame.setPolimer(polimer);
        frame.setNewMuni(newMuni);
        frame.setNewMaslo(frame.getMaslo() - polimer);
        return newMuni; //возвращает новый Муни
    }



    public static void main(String[] args) {
        System.out.println(HighMuniCalculations.makeMuniLowerMassInOnePercent(50, 11, 0.77));
        System.out.println(HighMuniCalculations.makeMuniLowerPercents(48, 126, 27.5));
        System.out.println(FixMuni.calculateFixMuni(57,48, 126, 27.5, 50, 11, 0.77, 2));
        System.out.println((int)(2/2.6));
    }
}

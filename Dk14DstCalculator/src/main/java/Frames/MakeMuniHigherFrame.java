package Frames;

import Calculations.LowMuniCalculations;
import excelOperations.FixMuniWorkbook;
import excelOperations.WorkbookFixMuniOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MakeMuniHigherFrame extends JFrame {

    private JTextField currentLField, currentMuniField, muniBatareiField, masloField;
    private JLabel resultLabel, newMuniLabel, newMasloLabel, polimerLabel;
    private double currentMuni, result, currentL, polimer, muniBatarei, maslo ,newMuni, newMaslo;

    public MakeMuniHigherFrame getFrameState(){
        return this;
    }

    public MakeMuniHigherFrame(double currentMuni){
        super("ДК1-4 Корректировка Муни усреднителя");//как корректно назвать
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.currentMuni = currentMuni;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 5));

        currentLField = new JTextField("", 4);
        currentMuniField = new JTextField(String.valueOf(currentMuni), 4);
        polimerLabel = new JLabel();
        muniBatareiField = new JTextField("", 4);
        masloField = new JTextField("", 4);
        newMuniLabel = new JLabel("");
        newMasloLabel = new JLabel("");

        resultLabel = new JLabel("");

        JButton fixMuniButton = new JButton("Расчитать");
        fixMuniButton.addActionListener(new FixMuniButton());

        JButton saveToExcel = new JButton("Сохранить в Excel");
        saveToExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //WorkbookFixMuniOperations.writeInBookMakeMuniHigher("Увеличение Муни в усреднителе", getFrameState());
                new FixMuniWorkbook().write(getFrameState());
            }
        });

        JButton backButton = new JButton("Назад");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MuniFixMainFrame();            }
        });


        panel.add(new JLabel("Текущий уровень в усреднителе, %"));
        panel.add(new JLabel("Муни в батарее, у.е."));
        panel.add(new JLabel("Добавляемое количество полимера, %"));
        panel.add(new JLabel("Текущий Муни, у.е."));
        panel.add(new JLabel("Масло, %"));
        panel.add(currentLField);
        panel.add(muniBatareiField);
        panel.add(polimerLabel);
        panel.add(currentMuniField);
        panel.add(masloField);


        panel.add(newMuniLabel);
        panel.add(newMasloLabel);
        panel.add(fixMuniButton);
        panel.add(saveToExcel);
        panel.add(backButton);


        setContentPane(panel);
        setSizeAndPositionOfFrame();
        setVisible(true);
    }

    public class FixMuniButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentL = Double.parseDouble(currentLField.getText().replace(',', '.'));
            //polimer = Double.parseDouble(polimerLabel.getText());
            muniBatarei = Double.parseDouble(muniBatareiField.getText().replace(',', '.'));
            maslo = Double.parseDouble(masloField.getText().replace(',', '.'));

            result = new LowMuniCalculations().calculate(getFrameState());
            resultLabel.setText(String.valueOf(result));
            polimerLabel.setText(String.valueOf(polimer));
            newMasloLabel.setText("Новый уровень масла: " + String.format("%.2f", newMaslo));
            newMuniLabel.setText("Новый Муни: " + String.format("%.2f", newMuni));
        }
    }

    public void setSizeAndPositionOfFrame(){
        Dimension screenSZ = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSZ.width/2, screenSZ.height/8);
        //setLocationByPlatform(true);
        setLocation(screenSZ.width/2-100, screenSZ.height/2-100);
    }

    public double getCurrentMuni() {
        return currentMuni;
    }

    public double getResult() {
        return result;
    }

    public double getCurrentL() {
        return currentL;
    }

    public double getPolimer() {
        return polimer;
    }

    public double getMuniBatarei() {
        return muniBatarei;
    }

    public double getMaslo() {
        return maslo;
    }

    public void setCurrentMuni(double currentMuni) {
        this.currentMuni = currentMuni;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void setCurrentL(double currentL) {
        this.currentL = currentL;
    }

    public void setPolimer(double polimer) {
        this.polimer = polimer;
    }

    public void setMuniBatarei(double muniBatarei) {
        this.muniBatarei = muniBatarei;
    }

    public void setMaslo(double maslo) {
        this.maslo = maslo;
    }

    public double getNewMuni() {
        return newMuni;
    }

    public void setNewMuni(double newMuni) {
        this.newMuni = newMuni;
    }

    public double getNewMaslo() {
        return newMaslo;
    }

    public void setNewMaslo(double newMaslo) {
        this.newMaslo = newMaslo;
    }

   }
package Frames;

import Calculations.HighMuniCalculations;
import Db.DbOperations;
import Db.Record;
import excelOperations.FixMuniWorkbook;
import excelOperations.WorkbookFixMuniOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MakeMuniLowerFrame extends JFrame {
    private JTextField currentLField, suhoyOstatokField, roField, muniBatareiField, masloField;
    private JLabel resultLabel, newMuniLabel, newMasloLabel, currentMuniLabel;
    private double currentMuni, currentL, suhoyOstatok, ro, muniBatarei, factMuni, maslo, addMaslo, newMuni, newMaslo;

    public MakeMuniLowerFrame getFrameState(){
        return this;
    }

    public MakeMuniLowerFrame(double currentMuni){
        super("ДК1-4 Корректировка Муни усреднителя");//как корректно назвать
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.currentMuni = currentMuni;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 6));

        currentLField = new JTextField("", 4);
        suhoyOstatokField = new JTextField("", 4);
        roField = new JTextField("", 4);
        muniBatareiField = new JTextField("", 4);
        currentMuniLabel = new JLabel(String.valueOf(currentMuni));
        masloField = new JTextField("", 4);

        resultLabel = new JLabel("");
        newMuniLabel = new JLabel("");
        newMasloLabel = new JLabel("");


        JButton fixMuniButton = new JButton("Расчитать");
        fixMuniButton.addActionListener(new FixMuniButton());

        JButton saveToExcel = new JButton("Сохранить в Excel");
        saveToExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FixMuniWorkbook().write(getFrameState());
            }
        });

        JButton backButton = new JButton("Назад");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MuniFixMainFrame();
            }
        });


        panel.add(new JLabel("Текущий уровень в усреднителе, %"));
        panel.add(new JLabel("Сухой остаток, %"));
        panel.add(new JLabel("Плотность, кг/м³"));
        panel.add(new JLabel("Муни в батарее, у.е."));
        panel.add(new JLabel("Текущий Муни, у.е."));//уточнить!!!
        panel.add(new JLabel("Масло, %"));
        //panel.add(new JLabel(""));
        panel.add(currentLField);
        panel.add(suhoyOstatokField);
        panel.add(roField);
        panel.add(muniBatareiField);
        panel.add(currentMuniLabel);
        panel.add(masloField);

        //panel.add(new JLabel("Добавить "));
        panel.add(resultLabel);
        //panel.add(new JLabel("кг масла"));
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
            try {
                factMuni = Double.parseDouble(currentMuniLabel.getText().replace(',', '.'));
                muniBatarei =  Double.parseDouble(muniBatareiField.getText().replace(',', '.'));
                maslo = Double.parseDouble(masloField.getText().replace(',', '.'));
                currentL =  Double.parseDouble(currentLField.getText().replace(',', '.'));
                suhoyOstatok = Double.parseDouble(suhoyOstatokField.getText().replace(',', '.'));
                ro = Double.parseDouble(roField.getText().replace(',', '.'));
                //addMaslo = new HighMuniCalculations().calculate(getFrameState()) * 10;
                addMaslo = new HighMuniCalculations().calculate(getFrameState());
                resultLabel.setText("Добавить " + String.format("%.2f", addMaslo) + "кг масла");
                newMasloLabel.setText("Новый уровень масла: " + String.format("%.2f", newMaslo));
                newMuniLabel.setText("Новый Муни: " + String.format("%.2f", newMuni));

                DbOperations.insert(DbOperations.getConnection(), new Record((float)currentMuni, (float)newMuni, (float)muniBatarei, (float)maslo, (float)newMaslo,
                        (float)ro, (float)suhoyOstatok, (float)addMaslo, -1));
            }catch (NumberFormatException | SQLException el){
                el.printStackTrace();
               }

        }
    }


    public void setSizeAndPositionOfFrame(){
        Dimension screenSZ = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSZ.width/2+500, screenSZ.height/8);
        setLocationByPlatform(true);
        //setLocation(screenSZ.width/2-100, screenSZ.height/2-100);
    }

    public double getCurrentMuni() {
        return currentMuni;
    }

    public double getCurrentL() {
        return currentL;
    }

    public double getSuhoyOstatok() {
        return suhoyOstatok;
    }

    public double getRo() {
        return ro;
    }

    public double getMuniBatarei() {
        return muniBatarei;
    }

    public double getFactMuni() {
        return factMuni;
    }

    public double getMaslo() {
        return maslo;
    }

    public double getAddMaslo() {
        return addMaslo;
    }

    public void setCurrentMuni(double currentMuni) {
        this.currentMuni = currentMuni;
    }

    public void setCurrentL(double currentL) {
        this.currentL = currentL;
    }

    public void setSuhoyOstatok(double suhoyOstatok) {
        this.suhoyOstatok = suhoyOstatok;
    }

    public void setRo(double ro) {
        this.ro = ro;
    }

    public void setMuniBatarei(double muniBatarei) {
        this.muniBatarei = muniBatarei;
    }

    public void setFactMuni(double factMuni) {
        this.factMuni = factMuni;
    }

    public void setMaslo(double maslo) {
        this.maslo = maslo;
    }

    public void setAddMaslo(double addMaslo) {
        this.addMaslo = addMaslo;
    }

    public void setNewMuni(double newMuni) {
        this.newMuni = newMuni;
    }

    public double getNewMuni() {
        return newMuni;
    }

    public double getNewMaslo() {
        return newMaslo;
    }

    public void setNewMaslo(double newMaslo) {
        this.newMaslo = newMaslo;
    }
}

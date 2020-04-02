package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Первый
 */
@Deprecated
public class Calculator1Frame extends JFrame{
    private JTextField var1Field, var2Field, var3Field;//Переименовать на названия переменных
    private double var1, var2, var3, result;
    private JLabel resultLabel;

    @Override
    public String toString() {
        return "Calculator1Frame{" +
                "var1=" + var1 +
                ", var2=" + var2 +
                ", var3=" + var3 +
                ", result=" + result +
                '}';
    }

    public double getResult() {
        return result;
    }

    public double getVar1() {
        return var1;
    }

    public double getVar2() {
        return var2;
    }

    public double getVar3() {
        return var3;
    }

    public JLabel getResultLabel() {
        return resultLabel;
    }

    public Calculator1Frame getFrameState(){
        return this;
    }

    public Calculator1Frame(){
        super("ДК1-4 Корректировка Муни усреднителя");//как корректно назвать
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        var1Field = new JTextField("" ,5);
        var2Field = new JTextField("" ,5);//Название переменной 2
        var3Field = new JTextField("" ,5);//Название переменной 3

        JButton okButton = new JButton("Расчитать");
        okButton.addActionListener(new OkButtonListener());

        JButton backButton = new JButton("Назад");
       backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MainFrame();
            }
        });

        JButton helpButton = new JButton("?");
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInternalMessageDialog(null, " Расчёт по формуле: a+b+c");
                System.out.println("Объясняет как идет расчёт");
            }
        });

        JButton excelButton = new JButton("Save To Excel");
        excelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File f = new File("");
                //JOptionPane.showMessageDialog(null,"EXCEL " + f.getAbsolutePath());
                String filename = f.getAbsolutePath() + "\\testWorkBook";
                //WorkbookOperations.writeIntoBook(filename, getFrameState());//ввести название файла для сохранения
            }
        });

        resultLabel = new JLabel();

        JPanel panel = new JPanel();
        //panel.setLayout(new GridLayout(4, 3));
        panel.setLayout(new GridLayout(5, 3));

        panel.add(new JLabel("Переменная1"));
        panel.add(new JLabel("Переменная2"));
        panel.add(new JLabel("Переменная3"));
        panel.add(var1Field);
        panel.add(var2Field);
        panel.add(var3Field);
        panel.add(okButton);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        //panel.add(new JLabel(""));
        panel.add(backButton);
        panel.add(new JLabel("Результат: "));
        panel.add(resultLabel);

        panel.add(helpButton);
        panel.add(new JLabel(""));
        panel.add(excelButton);

        setContentPane(panel);
        setSizeAndPositionOfFrame();
        setVisible(true);
    }

    /**
     * Устанавливает размер окна в 0.2 от размера экрана, и местоположение примерно в центре
     */
    public void setSizeAndPositionOfFrame(){
        Dimension screenSZ = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSZ.width/5, screenSZ.height/5);
        //setLocationByPlatform(true);
        setLocation(screenSZ.width/2-100, screenSZ.height/2-100);
    }

    public class OkButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try{
                var1 = Double.parseDouble(var1Field.getText());
                var2 = Double.parseDouble(var2Field.getText());
                var3 = Double.parseDouble(var3Field.getText());
                result = var1 + var2 + var3;
                resultLabel.setText(String.valueOf(result));
            }
            catch (NumberFormatException el){
                JOptionPane.showMessageDialog(null,"Введите числа");
            }
            System.out.println("v1 = " + var1);
            System.out.println("v2 = " + var2);
            System.out.println("v3 = " + var3);
        }
    }
}
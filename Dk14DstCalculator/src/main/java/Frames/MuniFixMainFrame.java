package Frames;

import Calculations.Calculations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MuniFixMainFrame extends JFrame {


    public MuniFixMainFrame(){
        super("Исправление Муни в усреднителе");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JTextField currentMuniField = new JTextField("" ,5);
        JLabel muniFieldLabel = new JLabel("Введите текущий Муни в усреднителе");
        JButton enterMuniButton = new JButton("Далее");
        enterMuniButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double currentMuni = Double.parseDouble(currentMuniField.getText());
                if(currentMuni >= Calculations.MIN_MUNI_LVL && currentMuni <= Calculations.MAX_MUNI_LVL){
                    JOptionPane.showMessageDialog(null, "Муни в пределах нормы");
                } else {
                    if(currentMuni > Calculations.MAX_MUNI_LVL) {new MakeMuniLowerFrame(currentMuni); setVisible(false);}
                        else{new MakeMuniHigherFrame(currentMuni); setVisible(false);}
                }
            }
        });

        JButton backButton = new JButton("Назад");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MainFrame();            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        panel.add(muniFieldLabel);

        panel.add(currentMuniField);
        panel.add(enterMuniButton);
        panel.add(backButton);

        setContentPane(panel);
        setSizeAndPositionOfFrame();
        setVisible(true);
    }


    public void setSizeAndPositionOfFrame(){
        Dimension screenSZ = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSZ.width/8, screenSZ.height/8);
        //setLocationByPlatform(true);
        setLocation(screenSZ.width/2-100, screenSZ.height/2-100);
    }
}

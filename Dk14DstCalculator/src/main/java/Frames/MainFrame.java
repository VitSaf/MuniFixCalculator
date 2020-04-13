package Frames;

import Db.DbOperations;
import Mail.CreateMsg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Главное меню
 */
public class MainFrame extends JFrame{

    public MainFrame(){
        super("ДК 1-4. Виды рассчетов");//заголовок
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        JButton button1 = new JButton("Исправление Муни усреднителя");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MuniFixMainFrame();
            }
        });

        JButton button2 = new JButton("Расчет 2");
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, " Эта панель всё ещё в разработке");
            }
        });

        JButton helpButton = new JButton("?");
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Ошибки, вопросы и идеи присылать на safonovva@vsk.sibur.ru. Спасибо за помощь в тестировании!");
            }
        });

        JButton logButton = new JButton("Создать письмо с логами");
        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CreateMsg.createLogsMsg(DbOperations.selectAll(DbOperations.getConnection()));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });



        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JPanel smallPanel = new JPanel();
        smallPanel.setLayout(new GridLayout(1, 2));

        panel.add(button1);
        panel.add(button2);

        //panel.add(helpButton);
        //smallPanel.add(new JLabel(""));
        smallPanel.add(logButton);
        smallPanel.add(helpButton);
        panel.add(smallPanel);

        setContentPane(panel);
        autoSizeFrame();
        setVisible(true);
    }

    public void autoSizeFrame(){
        Dimension screenSZ = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSZ.width/6, screenSZ.height/6);
        //setLocationByPlatform(true);//Автоматическое расположение. В левом верхнем углу по-умолчанию
        setLocation(screenSZ.width/2-100, screenSZ.height/2-100);
    }
}


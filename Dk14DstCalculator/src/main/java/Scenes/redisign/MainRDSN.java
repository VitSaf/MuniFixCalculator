/*package Scenes.redisign;

import Calculations.BolgovCalculations.FixLowMuni;
import Db.DbOperations;
import Db.Record;
import Mail.CreateMsg;
import Scenes.HighMuniScene;
import Scenes.LowMuniScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.SQLException;

public class MainRDSN {
    private Scene mainScene;
    private TextField inputMuniField, inputTargetMuniField, currentLField, muniBatareiField, masloField, usrNField, suhoyOstBatField, roBatField, suhoyOstUsrField, roUsrField;
    private Label newMuniLabel,  newMasloLabel, addPolimer;
    private double currentMuni, currentL, muniBatarei, maslo, newMuni, newMaslo, result, polimer, targetMuni, suhoyOstBat, roBat, suhoyOstUsr, roUsr;


    public MainRDSN(Stage stage) {
        inputMuniField = new TextField("");
        inputTargetMuniField = new TextField("");

        //lowMuni
        currentMuni = Double.parseDouble(muni);
        this.targetMuni = Double.parseDouble(targetMuni);
        inputMuniField = new TextField(muni);
        inputTargetMuniField = new TextField(targetMuni);
        currentLField = new TextField("");
        muniBatareiField = new TextField("");
        masloField = new TextField("");
        usrNField = new TextField("");
        newMuniLabel = new Label("");
        newMasloLabel = new Label("");
        addPolimer = new Label("");
        suhoyOstBatField = new TextField("");
        roBatField =  new TextField("");
        suhoyOstUsrField = new TextField("");
        roUsrField =  new TextField("");


        Button calcLow = new Button("Расчитать");
        calcLow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(usrNField.getText().length() < 1){ JOptionPane.showMessageDialog(null, " Введите № усреднителя"); return;}
                newMasloLabel.setTextFill(Color.BLACK);
                currentL = Double.parseDouble(currentLField.getText().replace(',', '.'));
                //polimer = Double.parseDouble(polimerLabel.getText());
                muniBatarei = Double.parseDouble(muniBatareiField.getText().replace(',', '.'));
                maslo = Double.parseDouble(masloField.getText().replace(',', '.'));
                suhoyOstBat = Double.parseDouble(suhoyOstBatField.getText().replace(',', '.'));
                roBat = Double.parseDouble(roBatField.getText().replace(',', '.'));
                suhoyOstUsr = Double.parseDouble(suhoyOstUsrField.getText().replace(',', '.'));
                roUsr = Double.parseDouble(roUsrField.getText().replace(',', '.'));

                result = new FixLowMuni().calculate(getSceneState());
                setNewMaslo(result);
                addPolimer.setText(String.valueOf(polimer));
                if(newMaslo < 26.2) newMasloLabel.setTextFill(Color.RED);
                newMasloLabel.setText(String.format("%.2f", newMaslo));
                //newMuniLabel.setText("Расчетное Муни: " + String.format("%.2f", newMuni));
                newMuniLabel.setText(targetMuni);
                try {
                    DbOperations.insert(DbOperations.getConnection(), new Record((float)currentMuni, (float)newMuni, (float)muniBatarei, (float)maslo, (float)newMaslo,
                            -1, -1, -1, (float)polimer, usrNField.getText()));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });



        GridPane lowGrid = new GridPane();
        lowGrid.setHgap(10);
        lowGrid.setHgap(10);
        lowGrid.add(new Label("Текущий уровень в усреднителе"), 0, 0);
        lowGrid.add(new Label("Муни с 5 батареи"), 1, 0);
        lowGrid.add(new Label("Дозировка масла, %"), 2, 0);
        lowGrid.add(new Label("№ усреднителя"), 3, 0);
        lowGrid.add(new Label("Сухой остаток на батарее, %"), 4, 0);
        lowGrid.add(new Label("Плотность в усреднителе, г/мл"), 5, 0);
        lowGrid.add(new Label("Сухой остаток в усреднителе, %"), 6, 0);
        lowGrid.add(new Label("Плотность в усреднителе, г/мл"), 7, 0);
        lowGrid.add(new Label("Маслонаполнение после исправления"), 8, 0);
        lowGrid.add(currentLField, 0, 1);
        lowGrid.add(muniBatareiField, 1, 1);
        lowGrid.add(masloField, 2, 1);
        lowGrid.add(usrNField, 3, 1);
        lowGrid.add(suhoyOstBatField, 4, 1);
        lowGrid.add(roBatField, 5, 1);
        lowGrid.add(suhoyOstUsrField, 6, 1);
        lowGrid.add(roUsrField, 7, 1);
        //grid.add(newMuniLabel, 8, 1);
        lowGrid.add(newMasloLabel, 8, 1);
//grid.add(newMuniLabel, 8, 1);
        lowGrid.add(new Label(""), 8, 1);

        GridPane highGrid = new GridPane();
        highGrid.setHgap(10);
        highGrid.setHgap(10);
        highGrid.add(new Label("Текущий уровень в усреднителе"), 0, 0);
        highGrid.add(new Label("Средний Муни с 5 батареи"), 1, 0);
        highGrid.add(new Label("Средняя дозировка масла, %"), 2, 0);
        highGrid.add(new Label("№ усреднителя"), 3, 0);
        highGrid.add(new Label("Сухой остаток в усреднителе , %"), 4, 0);
        highGrid.add(new Label("Плотность в усреднителе , г/мл"), 5, 0);
        highGrid.add(new Label("Маслонаполнение после исправления, %"), 6, 0);
        highGrid.add(new Label(""), 0, 1);
        highGrid.add(new Label(""), 1, 1);
        highGrid.add(new Label(""), 2, 1);
        highGrid.add(new Label(""), 3, 1);
        highGrid.add(new Label(""), 4, 1);
        highGrid.add(new Label(""), 5, 1);
        highGrid.add(new Label(""), 6, 1);





        Button inputMuniButton = new Button("Далее");
        inputMuniButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (Double.parseDouble(inputMuniField.getText()) < Double.parseDouble(inputTargetMuniField.getText())) ;
                   //TODO
                //TODO else
            }
        });
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);

        Button helpButton = new Button("?");
        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JOptionPane.showMessageDialog(null, "Ошибки, вопросы и идеи присылать на safonovva@vsk.sibur.ru. Спасибо за помощь в тестировании!");
            }
        });


        Button logButton = new Button("Создать письмо с логами");
        logButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    CreateMsg.createLogsMsg(DbOperations.selectAll(DbOperations.getConnection()));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        vbox.getChildren().addAll(new Label("Введите текущий Муни"), inputMuniField, new Label("Введите целевое Муни"), inputTargetMuniField, inputMuniButton, new Label("Муни ниже целевого"), lowGrid,
               calcLow ,new Label("Муни выше целевого"), highGrid , helpButton, logButton);

        mainScene = new Scene(vbox);
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public TextField getInputMuniField() {
        return inputMuniField;
    }

    public void setInputMuniField(TextField inputMuniField) {
        this.inputMuniField = inputMuniField;
    }

    public TextField getInputTargetMuniField() {
        return inputTargetMuniField;
    }

    public void setInputTargetMuniField(TextField inputTargetMuniField) {
        this.inputTargetMuniField = inputTargetMuniField;
    }
}
*/
package Scenes;

import Calculations.BolgovCalculations.FixHighMuni;
import Calculations.HighMuniCalculations;
import Calculations.LowMuniCalculations;
import Db.DbOperations;
import Db.Record;
import excelOperations.FixMuniWorkbook;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.SQLException;


public class HighMuniScene {
    Scene highMuniScene;
    private TextField currentLField, suhoyOstBatField, roBatField, suhoyOstUsrField, roUsrField, muniBatareiField, masloField, usrNField, inputMuniField, inputTargetMuniField;
    private Label resultLabel, newMuniLabel, newMasloLabel, currentMuniLabel;
    private double currentMuni, currentL, suhoyOstatok, ro, muniBatarei, factMuni, maslo, addMaslo, newMuni, newMaslo, targetMuni, suhoyOstBat, roBat, suhoyOstUsr, roUsr;

    public HighMuniScene getSceneState(){return this;}

    public HighMuniScene(String muni, String targetMuni, Stage stage){
        currentMuni = Double.parseDouble(muni);
        this.targetMuni = Double.parseDouble(targetMuni);
        inputMuniField = new TextField(muni);
        inputTargetMuniField = new TextField(targetMuni);
        currentLField = new TextField("");
        suhoyOstBatField = new TextField("");
        roBatField = new TextField("");
        suhoyOstUsrField = new TextField("");
        roUsrField = new TextField("0.77");
        muniBatareiField = new TextField("");
        masloField = new TextField("");
        usrNField = new TextField("");
        newMuniLabel = new Label("");
        newMasloLabel = new Label("");
        resultLabel = new Label("");
        newMasloLabel.setFont(new Font("Arial", 24));
        Button inputMuniButton = new Button("Далее");
        inputMuniButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (Double.parseDouble(inputMuniField.getText()) < Double.parseDouble(inputTargetMuniField.getText()))
                    stage.setScene(new LowMuniScene(inputMuniField.getText(), inputTargetMuniField.getText(), stage).getLowMuniScene());
                else stage.setScene(new HighMuniScene(inputMuniField.getText(), inputTargetMuniField.getText(), stage).getHighMuniScene());
            }
        });
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setHgap(10);
        grid.add(new Label("Текущий уровень в усреднителе"), 0, 0);
        /*grid.add(new Label("Сухой остаток c 5 батареи, %"), 1, 0);
        grid.add(new Label("Плотность с 5 батареи , г/мл"), 2, 0);*/
        grid.add(new Label("Средний Муни с 5 батареи"), 1, 0);
        grid.add(new Label("Средняя дозировка масла, %"), 2, 0);
        grid.add(new Label("№ усреднителя"), 3, 0);
        grid.add(new Label("Сухой остаток в усреднителе , %"), 4, 0);
        grid.add(new Label("Плотность в усреднителе , г/мл"), 5, 0);
        //grid.add(new Label("Муни после исправления"), 6, 0);
        grid.add(new Label("Маслонаполнение после исправления, %"), 6, 0);
        grid.add(currentLField, 0, 1);
       /* grid.add(suhoyOstBatField, 1, 1);
        grid.add(roBatField, 2, 1);*/
        grid.add(muniBatareiField, 1, 1);
        grid.add(masloField, 2, 1);
        grid.add(usrNField, 3, 1);
        //grid.add(newMuniLabel, 6, 1);
        grid.add(suhoyOstUsrField, 4, 1);
        grid.add(roUsrField, 5, 1);
        grid.add(newMasloLabel, 6, 1);

        HBox bbox = new HBox();
        bbox.setPadding(new Insets(10, 10, 10, 0));
        bbox.setSpacing(10);
        Button calc = new Button("Расчитать");
        calc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(usrNField.getText().length() < 1){ JOptionPane.showMessageDialog(null, "Введите № усреднителя"); return;}
                newMasloLabel.setTextFill(Color.BLACK);
                try {
                    factMuni = currentMuni;
                    muniBatarei =  Double.parseDouble(muniBatareiField.getText().replace(',', '.'));
                    maslo = Double.parseDouble(masloField.getText().replace(',', '.'));
                    currentL =  Double.parseDouble(currentLField.getText().replace(',', '.'));
                    //suhoyOstBat = Double.parseDouble(suhoyOstBatField.getText().replace(',', '.'));
                    //roBat = Double.parseDouble(roBatField.getText().replace(',', '.'));
                    suhoyOstUsr = Double.parseDouble(suhoyOstUsrField.getText().replace(',', '.'));
                    roUsr = Double.parseDouble(roUsrField.getText().replace(',', '.'));

                    //addMaslo = new HighMuniCalculations().calculate(getSceneState());
                    newMaslo = new FixHighMuni().calculate(getSceneState());
                    if (newMaslo > 28.8) newMasloLabel.setTextFill(Color.RED);
                    resultLabel.setText("Добавить " + String.format("%.2f", addMaslo) + "кг масла");
                    newMasloLabel.setText(String.format("%.2f", newMaslo));
                    newMuniLabel.setText("Расчетное Муни: " + String.format("%.2f", newMuni));

                    DbOperations.insert(DbOperations.getConnection(), new Record((float)currentMuni, (float)newMuni, (float)muniBatarei, (float)maslo, (float)newMaslo,
                            (float)ro, (float)suhoyOstatok, (float)addMaslo, -1, usrNField.getText()));
                }catch (NumberFormatException | SQLException el){
                    el.printStackTrace();
                }
            }
        });
        Button saveToExcel = new Button("Сохранить в Excel");
        saveToExcel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new FixMuniWorkbook().write(getSceneState());
            }
        });
        bbox.getChildren().addAll(calc, saveToExcel);

        HBox rbox = new HBox();
        rbox.setPadding(new Insets(10, 10, 10, 0));
        rbox.setSpacing(10);
        rbox.getChildren().addAll(new Label("Добавить масла, кг :"), resultLabel);//resultLabel = addMasloLabel

        Button back = new Button("Новый расчет");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(new MainScene(stage).getMainScene());
            }
        });

        vbox.getChildren().addAll(new Label("Введите текущий Муни"), inputMuniField, new Label("Введите целевое Муни"), inputTargetMuniField, inputMuniButton, grid, bbox, rbox, back);

        highMuniScene = new Scene(vbox);
    }

    public Scene getHighMuniScene() {
        return highMuniScene;
    }

    public void setHighMuniScene(Scene highMuniScene) {
        this.highMuniScene = highMuniScene;
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

    public double getTargetMuni() {
        return targetMuni;
    }

    public void setTargetMuni(double targetMuni) {
        this.targetMuni = targetMuni;
    }

    public TextField getCurrentLField() {
        return currentLField;
    }

    public void setCurrentLField(TextField currentLField) {
        this.currentLField = currentLField;
    }

    public TextField getSuhoyOstBatField() {
        return suhoyOstBatField;
    }

    public void setSuhoyOstBatField(TextField suhoyOstBatField) {
        this.suhoyOstBatField = suhoyOstBatField;
    }

    public TextField getRoBatField() {
        return roBatField;
    }

    public void setRoBatField(TextField roBatField) {
        this.roBatField = roBatField;
    }

    public TextField getSuhoyOstUsrField() {
        return suhoyOstUsrField;
    }

    public void setSuhoyOstUsrField(TextField suhoyOstUsrField) {
        this.suhoyOstUsrField = suhoyOstUsrField;
    }

    public TextField getRoUsrField() {
        return roUsrField;
    }

    public void setRoUsrField(TextField roUsrField) {
        this.roUsrField = roUsrField;
    }

    public double getSuhoyOstBat() {
        return suhoyOstBat;
    }

    public void setSuhoyOstBat(double suhoyOstBat) {
        this.suhoyOstBat = suhoyOstBat;
    }

    public double getRoBat() {
        return roBat;
    }

    public void setRoBat(double roBat) {
        this.roBat = roBat;
    }

    public double getSuhoyOstUsr() {
        return suhoyOstUsr;
    }

    public void setSuhoyOstUsr(double suhoyOstUsr) {
        this.suhoyOstUsr = suhoyOstUsr;
    }

    public double getRoUsr() {
        return roUsr;
    }

    public void setRoUsr(double roUsr) {
        this.roUsr = roUsr;
    }

    public TextField getMuniBatareiField() {
        return muniBatareiField;
    }

    public void setMuniBatareiField(TextField muniBatareiField) {
        this.muniBatareiField = muniBatareiField;
    }

    public TextField getMasloField() {
        return masloField;
    }

    public void setMasloField(TextField masloField) {
        this.masloField = masloField;
    }

    public TextField getUsrNField() {
        return usrNField;
    }

    public void setUsrNField(TextField usrNField) {
        this.usrNField = usrNField;
    }

    public Label getResultLabel() {
        return resultLabel;
    }

    public void setResultLabel(Label resultLabel) {
        this.resultLabel = resultLabel;
    }

    public Label getNewMuniLabel() {
        return newMuniLabel;
    }

    public void setNewMuniLabel(Label newMuniLabel) {
        this.newMuniLabel = newMuniLabel;
    }

    public Label getNewMasloLabel() {
        return newMasloLabel;
    }

    public void setNewMasloLabel(Label newMasloLabel) {
        this.newMasloLabel = newMasloLabel;
    }

    public Label getCurrentMuniLabel() {
        return currentMuniLabel;
    }

    public void setCurrentMuniLabel(Label currentMuniLabel) {
        this.currentMuniLabel = currentMuniLabel;
    }

    public double getCurrentMuni() {
        return currentMuni;
    }

    public void setCurrentMuni(double currentMuni) {
        this.currentMuni = currentMuni;
    }

    public double getCurrentL() {
        return currentL;
    }

    public void setCurrentL(double currentL) {
        this.currentL = currentL;
    }

    public double getSuhoyOstatok() {
        return suhoyOstatok;
    }

    public void setSuhoyOstatok(double suhoyOstatok) {
        this.suhoyOstatok = suhoyOstatok;
    }

    public double getRo() {
        return ro;
    }

    public void setRo(double ro) {
        this.ro = ro;
    }

    public double getMuniBatarei() {
        return muniBatarei;
    }

    public void setMuniBatarei(double muniBatarei) {
        this.muniBatarei = muniBatarei;
    }

    public double getFactMuni() {
        return factMuni;
    }

    public void setFactMuni(double factMuni) {
        this.factMuni = factMuni;
    }

    public double getMaslo() {
        return maslo;
    }

    public void setMaslo(double maslo) {
        this.maslo = maslo;
    }

    public double getAddMaslo() {
        return addMaslo;
    }

    public void setAddMaslo(double addMaslo) {
        this.addMaslo = addMaslo;
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

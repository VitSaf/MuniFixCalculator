package Scenes;

import Calculations.BolgovCalculations.FixLowMuni;
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
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.SQLException;

public class LowMuniScene {
    private Scene lowMuniScene;
    private TextField inputMuniField, inputTargetMuniField, currentLField, muniBatareiField, masloField, usrNField, suhoyOstBatField, roBatField, suhoyOstUsrField, roUsrField;
    private Label newMuniLabel;
    private Label newMasloLabel;

    private Label addPolimer;
    private double currentMuni, currentL, muniBatarei, maslo, newMuni, newMaslo, result, polimer, targetMuni, suhoyOstBat, roBat, suhoyOstUsr, roUsr;

    public LowMuniScene getSceneState(){return this;}


    public LowMuniScene(String muni, String targetMuni, Stage stage) {
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
        Button inputMuniButton = new Button("Далее");
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setHgap(10);
        grid.add(new Label("Текущий уровень в усреднителе"), 0, 0);
        grid.add(new Label("Муни с 5 батареи"), 1, 0);
        grid.add(new Label("Дозировка масла, %"), 2, 0);
        grid.add(new Label("№ усреднителя"), 3, 0);
        grid.add(new Label("Сухой остаток на батарее, %"), 4, 0);
        grid.add(new Label("Плотность на батарее, г/мл"), 5, 0);
        grid.add(new Label("Сухой остаток в усреднителе, %"), 6, 0);
        grid.add(new Label("Плотность в усреднителе, г/мл"), 7, 0);
        //grid.add(new Label("Муни после исправления"), 8, 0);
        grid.add(new Label("Маслонаполнение после исправления"), 8, 0);
        grid.add(currentLField, 0, 1);
        grid.add(muniBatareiField, 1, 1);
        grid.add(masloField, 2, 1);
        grid.add(usrNField, 3, 1);
        grid.add(suhoyOstBatField, 4, 1);
        grid.add(roBatField, 5, 1);
        grid.add(suhoyOstUsrField, 6, 1);
        grid.add(roUsrField, 7, 1);
        //grid.add(newMuniLabel, 8, 1);
        grid.add(newMasloLabel, 8, 1);

        HBox bbox = new HBox();
        bbox.setPadding(new Insets(10, 10, 10, 0));
        bbox.setSpacing(10);
        Button calc = new Button("Расчитать");
        calc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(usrNField.getText().length() < 1){ JOptionPane.showMessageDialog(null, " Введите № усреднителя"); return;}
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
        rbox.getChildren().addAll(new Label("Добавить полимеризата, % :"), addPolimer);

        Button back = new Button("Новый расчет");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(new MainScene(stage).getMainScene());
            }
        });

        vbox.getChildren().addAll(new Label("Введите текущий Муни"), inputMuniField, new Label("Введите целевое Муни"), inputTargetMuniField, inputMuniButton, grid, bbox, rbox, back);

        lowMuniScene = new Scene(vbox);
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

    public double getMuniBatarei() {
        return muniBatarei;
    }

    public void setMuniBatarei(double muniBatarei) {
        this.muniBatarei = muniBatarei;
    }

    public double getPolimer() {
        return polimer;
    }

    public void setPolimer(double polimer) {
        this.polimer = polimer;
    }

    public double getMaslo() {
        return maslo;
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

    public double getTargetMuni() {
        return targetMuni;
    }

    public void setTargetMuni(double targetMuni) {
        this.targetMuni = targetMuni;
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

    public double getNewMaslo() {
        return newMaslo;
    }

    public void setNewMaslo(double newMaslo) {
        this.newMaslo = newMaslo;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void setInputTargetMuniField(TextField inputTargetMuniField) {
        this.inputTargetMuniField = inputTargetMuniField;
    }

    public TextField getCurrentLField() {
        return currentLField;
    }

    public void setCurrentLField(TextField currentLField) {
        this.currentLField = currentLField;
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

    public Label getAddPolimer() {
        return addPolimer;
    }

    public void setAddPolimer(Label addPolimer) {
        this.addPolimer = addPolimer;
    }

    public Scene getLowMuniScene() {
        return lowMuniScene;
    }

    public void setLowMuniScene(Scene lowMuniScene) {
        this.lowMuniScene = lowMuniScene;
    }
}

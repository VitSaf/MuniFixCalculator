package Scenes;

import Db.DbOperations;
import Mail.CreateMsg;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainScene {
    private Scene mainScene;
    private TextField inputMuniField;
    private TextField inputTargetMuniField;

    public MainScene(Stage stage) {
        inputMuniField = new TextField("");
        inputTargetMuniField = new TextField("");
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

        vbox.getChildren().addAll(new Label("Введите текущий Муни"), inputMuniField, new Label("Введите целевое Муни"), inputTargetMuniField, inputMuniButton, helpButton, logButton);

        mainScene = new Scene(vbox);
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

    public Scene getMainScene() {
        return mainScene;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }
}

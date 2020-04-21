package start;

import Scenes.MainScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Калькулятор исправления усреднителя ДССК");
        primaryStage.setWidth(1400);
        primaryStage.setHeight(500);
        primaryStage.setScene(new MainScene(primaryStage).getMainScene());
        //primaryStage.setScene(new LowMuniScene("45", "50", primaryStage).getLowMuniScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}

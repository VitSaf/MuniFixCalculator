package start;

import Scenes.MainScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Калькулятор исправления усреднителя ДССК");
        primaryStage.setWidth(1600);
        primaryStage.setHeight(800);
        primaryStage.setScene(new MainScene(primaryStage).getMainScene());
        //primaryStage.setScene(new MainRDSN(primaryStage).getMainScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}

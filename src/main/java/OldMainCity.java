package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.Utils.PrimaryStageControler;

import java.net.URL;

public class OldMainCity extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        //URL url = getClass().getResource("pelea.fxml");
        URL url = getClass().getResource("/main/java/juego/mapas/ciudad/ciudad.fxml");
        loader.setLocation(url);
        loader.setControllerFactory((Class<?> type) -> {// CREA PrimaryStageAware
            try {
                Object controller = type.newInstance();
                if (controller instanceof PrimaryStageControler) {
                    ((PrimaryStageControler) controller).setPrimaryStage(primaryStage);
                }
                return controller;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Parent root = loader.load();
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("main.resources/style/styles.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        //todo Leer desde la BD

        //
        /*
            FiN PRECARGA
        */
        //
        launch(args);
    }


}

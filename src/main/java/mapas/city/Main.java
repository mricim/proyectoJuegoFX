package main.java.mapas.city;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        //URL url = getClass().getResource("ciudad.fxml");
        URL url = getClass().getResource("/main/java/mapas/city/ciudad.fxml");
        loader.setLocation(url);
        loader.setControllerFactory((Class<?> type) -> {// CREA PrimaryStageAware
            try {
                Object controller = type.newInstance();
                if (controller instanceof PrimaryStageControlador) {
                    ((PrimaryStageControlador) controller).setPrimaryStage(primaryStage);
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
        primaryStage.setMaximized(true);//Pone el Stage en maximizado
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

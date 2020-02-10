package main.java.mapas.city;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.java.mapas.city.edificios.Edificios;
import main.java.mapas.city.edificios.EdificiosNoMejorable;
import main.java.mapas.city.edificios.Productores;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {
    static ArrayList<Posiciones> listaPosicionesServer = new ArrayList<>();
    static HashMap<Integer[], Posiciones> listaPosiciones = new HashMap<>();
    public static ArrayList edificios = new ArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Crear Edificios
        Edificios parcela=new EdificiosNoMejorable(0,"parcela Construible",false);
        Edificios edificio1=new Productores(1,"Centro cientifico",true,0,100,99,99,99,99,99,99,99,99,99,99,99,99);

        edificios.add(parcela);
        edificios.add(edificio1);

        listaPosicionesServer.add(new Posiciones(8, 12, 0));
        listaPosicionesServer.add(new Posiciones(8, 15, 1));//todos tendrian "parcela"


        //
        /*
            FiN PRECARGA
        */
        //
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

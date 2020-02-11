package main.java.mapas.city;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.java.mapas.CallImages;
import main.java.mapas.city.edificios.Edificio;
import main.java.mapas.city.edificios.quiza.*;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {
    public static final Image ERRORIMAGE= CallImages.getImage("icons/","error");

    static HashMap<Integer[], Posiciones> listaPosiciones = new HashMap<>();
    public static ArrayList<Edificio> listaEdificios = new ArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Crear Edificios
        Edificio parcela=new Edificio(0,"parcela Construible");//NO MEJORABLE
        Edificio castillo=new Edificio(1,"Intendencia",false,0,99,99,99,99,10);
        Edificio muralla=new Edificio(2,"Muralla",false,0,99,99,99,99,20);
        Edificio almacen=new Edificio(10,"Almacen",0,99,99,99,99,10,1000,1000,1000,1000);
        Edificio edificio1=new Edificio(11,"Centro cientifico",0,99,99,99,99,20,10,10,10,10,10,10,10);
        Edificio edificio2=new Edificio(12,"lupas",0,99,99,99,99,20,10,10,10,10,10,10,10);


        listaPosiciones.put(new Integer[]{8,15},new Posiciones(8, 15, castillo));//no tocar
        listaPosiciones.put(new Integer[]{8,14},new Posiciones(8, 14, muralla));//no tocar
        listaPosiciones.put(new Integer[]{8,13},new Posiciones(8, 13, muralla));//no tocar
        listaPosiciones.put(new Integer[]{8,12},new Posiciones(8, 12, parcela));
        listaPosiciones.put(new Integer[]{8,11},new Posiciones(8, 11, parcela));
        listaPosiciones.put(new Integer[]{7,12},new Posiciones(7, 12, almacen));//todos tendrian "parcela"
        listaPosiciones.put(new Integer[]{6,12},new Posiciones(6, 12, edificio1));//todos tendrian "parcela"
        listaPosiciones.put(new Integer[]{5,12},new Posiciones(5, 12, edificio2));//todos tendrian "parcela"


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

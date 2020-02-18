package main.java.juego;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.juego.mapas.city.Ciudad;

import java.io.IOException;
import java.net.URL;

public class PrimaryStageControler {
    private static Jugador jugador;
    public static Jugador getJugador() {
        return jugador;
    }
    public static void setJugador(Jugador jugador) {
        PrimaryStageControler.jugador = jugador;
    }

    private static Ciudad ciudad;
    public static Ciudad getCiudad() {
        return ciudad;
    }
    public static void setCiudad(Ciudad ciudad) {
        PrimaryStageControler.ciudad = ciudad;
    }


    protected Stage stage;
    public void setPrimaryStage(Stage primaryStage) {
        this.stage = primaryStage;
    }
    public Stage getStage() {
        return stage;
    }
    public void cambiarNombreStage(String nuevoNombre) {
        if (nuevoNombre == null) {
            stage.setTitle("Nombre Aplicación");
        } else {
            stage.setTitle(nuevoNombre);
        }

    }


    public void reload(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        //URL url = getClass().getResource("ciudad.fxml");
        URL url = getClass().getResource("/main/java/juego/mapas/city/ciudad.fxml");
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
        primaryStage.getScene().setRoot(root);
    }
}

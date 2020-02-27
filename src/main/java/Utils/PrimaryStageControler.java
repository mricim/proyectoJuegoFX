package main.java.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import main.java.Jugadores.Jugador;
import main.java.juego.mapas.Ciudad.Ciudad;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;

import static main.java.Jugadores.Jugador.listaJugadores;

public class PrimaryStageControler {
    private static HashMap<String, FXMLLoader> fxmlLoaderHashMap = new HashMap<>();

    private static final String RUTE = System.getProperty("user.dir") + "/src/main/java/";

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


    public static void reload(Stage primaryStage, String rute, boolean setMaximized) throws IOException {
        FXMLLoader loader;

        if (fxmlLoaderHashMap.containsKey(rute)) {
            loader = fxmlLoaderHashMap.get(rute);
        } else {
            loader = new FXMLLoader();
            //URL url = getClass().getResource("/main/java/juego/mapas/Ciudad/ciudad.fxml");
            loader.setLocation(Paths.get(RUTE + rute).toUri().toURL());
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
        }
        Parent root = loader.load();
        primaryStage.getScene().setRoot(root);
        if (primaryStage.isMaximized()) {
            setMaximized = true;
        }
        primaryStage.setMaximized(setMaximized);//Pone el Stage en maximizado
    }
}

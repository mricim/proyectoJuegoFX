package main.java.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import main.java.jugadores.Jugador;
import main.java.Main;
import main.java.juego.mapas.ciudad.Ciudad;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

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


    protected static Stage stage;

    public static void setPrimaryStage(Stage primaryStage) {
       stage = primaryStage;
    }

    public static Stage getStage() {
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
            System.out.println("TENEMOS");
            loader = fxmlLoaderHashMap.get(rute);
        } else {
            System.out.println("NO TENEMOS");
            loader = new FXMLLoader();
            //URL url = getClass().getResource("/main/java/juego/mapas/Ciudad/ciudad.fxml");
            //loader.setLocation(Paths.get(RUTE + rute).toUri().toURL());
            URI nada = null;
            try {
                nada= Main.class.getResource(rute).toURI();
            }catch (Exception e){}
//            System.out.println(nada.toString());
            loader.setLocation(nada.toURL());
            //Path path=Paths.get(RUTE + rute);
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
            //TODO fxmlLoaderHashMap.put(rute,loader);
        }
        Parent root = loader.load();
        primaryStage.getScene().setRoot(root);
        if (primaryStage.isMaximized()) {
            setMaximized = true;
        }

        primaryStage.setMaximized(setMaximized);//Pone el Stage en maximizado
    }
}

package main.java.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import main.java.jugadores.Clan;
import main.java.jugadores.Jugador;
import main.java.Main;
import main.java.juego.mapas.ciudad.Ciudad;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

public class PrimaryStageControler {
    private static HashMap<String, FXMLLoader> fxmlLoaderHashMap = new HashMap<>();

    private static final String RUTE = System.getProperty("user.dir") + "/src/main/java/";

    //VARIABLES PREDEFINIDAS

    private static Clan clanPrimaryStageController;

    public static Clan getClanPrimaryStageController() {
        return clanPrimaryStageController;
    }

    public static void setClanPrimaryStageController(Clan clanPrimaryStageController) {
        PrimaryStageControler.clanPrimaryStageController = clanPrimaryStageController;
    }

    private static Jugador jugadorPrimaryStageController;

    public static Jugador getJugadorPrimaryStageController() {
        return jugadorPrimaryStageController;
    }

    public static void setJugadorPrimaryStageController(Jugador jugadorPrimaryStageController) {
        PrimaryStageControler.jugadorPrimaryStageController = jugadorPrimaryStageController;
    }

    private static Ciudad ciudadPrimaryStageController;

    public static Ciudad getCiudadPrimaryStageController() {
        return ciudadPrimaryStageController;
    }

    public static void setCiudadPrimaryStageController(Ciudad ciudadPrimaryStageController) {
        PrimaryStageControler.ciudadPrimaryStageController = ciudadPrimaryStageController;
    }

    //FIN VARIALBES PREDEFINIDAS

    protected static Stage stagePrimaryStageController;

    public static void setPrimaryStage(Stage primaryStage) {
        stagePrimaryStageController = primaryStage;
    }

    public static Stage getStagePrimaryStageController() {
        return stagePrimaryStageController;
    }


    public void cambiarNombreStage(String nuevoNombre) {
        if (nuevoNombre == null) {
            stagePrimaryStageController.setTitle("Nombre Aplicación");
        } else {
            stagePrimaryStageController.setTitle(nuevoNombre);
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
                nada = Main.class.getResource(rute).toURI();
            } catch (Exception e) {
            }
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

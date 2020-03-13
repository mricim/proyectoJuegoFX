package main.java.Utils;

import com.google.api.client.util.ArrayMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.jugadores.Clan;
import main.java.jugadores.Jugador;
import main.java.Main;
import main.java.juego.mapas.ciudad.Ciudad;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PrimaryStageControler {
    public static String NAME_TEMA;
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

    public static void newStage(String rute, boolean setMaximized) throws IOException {
        final Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        reloadNewStage(null, stage, rute, setMaximized);
    }

    public static void newStageby(Stage oldStage, String rute, boolean setMaximized) {
        try {
            final Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            reloadNewStage(oldStage, stage, rute, setMaximized);
        } catch (Exception e) {
            System.err.println("Error al cargar la hoja de iniciar sessión");
        }
    }

    public static void reload(Class aClass) {
        reload(aClass, false);
    }

    public static void reload(Class aClass, boolean setMaximized) {//TODO ES POSIBLE QUE EN ESTE MAPA NO INTERESE TENER ESTO
        try {
            reload(stagePrimaryStageController, PrimaryStageControler.getPathToFXML(aClass), setMaximized);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void reload(Stage primaryStage, String rute, boolean setMaximized) throws IOException {
        reloadNewStage(null, primaryStage, rute, setMaximized);
    }

    public static void reloadNewStage(Stage oldStageOwner, Stage primaryStage, String rute, boolean setMaximized) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(rute));

        Parent root = loader.load();
        if (oldStageOwner != null) {
            primaryStage.initOwner(oldStageOwner);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } else {
            primaryStage.getScene().setRoot(root);
        }
        if (primaryStage.isMaximized()) {
            setMaximized = true;
        }

        primaryStage.setMaximized(setMaximized);//Pone el Stage en maximizado
        if (oldStageOwner != null) {
            primaryStage.showAndWait();
        }
    }

    private static Map<String, String> listControllersFXML = new ArrayMap<>();
    private static Map<String, String> listControllersa = new ArrayMap<>();

    //PARA EL RESTO
    //RETURN RUTES
    public static String getPathToFXML(Class ese) {
        String eseName = ese.getName();
        if (listControllersFXML.containsKey(eseName)) {
            return listControllersFXML.get(eseName);
        } else {
            String nameTemp = eseName.replace("Controller", "");
            char c[] = nameTemp.substring(nameTemp.lastIndexOf(".") + 1).toCharArray();
            c[0] = Character.toLowerCase(c[0]);
            String name = ese.getResource("").getPath() + new String(c) + ".fxml";
            String main = Main.class.getResource("").getPath();
            String salida = name.replace(main, "");
            listControllersFXML.put(eseName, salida);
            return salida;
        }
    }

    public static String getPath(Class ese) {
        String eseName = ese.getName();
        if (listControllersa.containsKey(eseName)) {
            return listControllersa.get(eseName);
        } else {
            String name = ese.getName();
            name = ese.getResource("").getPath() + name.substring(name.lastIndexOf(".") + 1);
            String main = Main.class.getResource("").getPath();
            String salida = name.replace(main, "");
            listControllersFXML.put(eseName, salida);
            return salida;
        }
    }
    //FIN RETURN RUTES


}

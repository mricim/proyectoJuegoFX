package main.java.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.jugadores.Clan;
import main.java.jugadores.Jugador;
import main.java.Main;
import main.java.juego.mapas.ciudad.Ciudad;
import main.java.jugadores.iniciarSession.IniciarSessionController;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

public class PrimaryStageControler {
    private static HashMap<String, FXMLLoader> fxmlLoaderHashMap = new HashMap<>();

    private static final String RUTE = System.getProperty("user.dir") + "/src/main/java/";

    //VARIABLES PREDEFINIDAS
    public static String NAME_TEMA;
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
        reload(stage, rute, setMaximized);
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
                e.printStackTrace();
            }
            loader.setLocation(nada.toURL());
            //TODO fxmlLoaderHashMap.put(rute,loader);
        }
        Parent root = loader.load();
        primaryStage.getScene().setRoot(root);
        if (primaryStage.isMaximized()) {
            setMaximized = true;
        }

        primaryStage.setMaximized(setMaximized);//Pone el Stage en maximizado
    }

/*
    //PARA EL RESTO
    public static String getRuteToFXML() throws URISyntaxException {
        String name = IniciarSessionController.class.getName().replace("Controller", "");
        char c[] = name.substring(name.lastIndexOf(".") + 1).toCharArray();
        c[0] = Character.toLowerCase(c[0]);
        return IniciarSessionController.class.getResource("").getPath() + new String(c) + ".fxml";
    }

    public static URI getRute() throws URISyntaxException {
        String name = IniciarSessionController.class.getName().replace("Controller", "");
        return new URI(IniciarSessionController.class.getResource("").toURI().toString() + name.substring(name.lastIndexOf(".") + 1));
    }
    public static URL getURLF() throws MalformedURLException {
        String name = IniciarSessionController.class.getName().replace("Controller", "");
        File DD=new File(String.valueOf(IniciarSessionController.class.getResource(name.substring(name.lastIndexOf(".") + 1)+".fxml")));
        if (DD.exists()){
            System.out.println("si");
        }
        System.out.println(DD.getAbsolutePath());
        return new URL(DD.getAbsolutePath());
    }

 */
}

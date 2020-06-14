package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.Inicio.PantallaInicialController;
import main.java.temas.Temas;
import main.java.utils.os.Os;
import main.java.utils.propietiesAndPreferences.PreferencesApp;
import main.java.utils.traductor.Traductor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import static main.java.temas.Temas.pathImagesExternal;
import static main.java.utils.PrimaryStageControler.*;
import static main.java.utils.traductor.Traductor.listaIdiomasPath;


public class Main extends Application {
    //SYSTEMA
    public static final String OS = Os.operativeSystem();
    public static final String PATH = System.getProperty("user.dir").replace("ProyectoJuegoFX", "");
    public static final String propFile = PATH + "/conf/config.properties";
    public static final String prefFile = PATH + "/pref/preferences.properties";
    //    String userDir = System.getProperty("user.dir");
    //    Path path = Paths.get(userDir);
    //    String project = path.getFileName();
    //CONFIGS
    public static final String NAME = "Armegis";
    public static final String VERSION = "0.-10.0.4.20200614.0";
    public static final String HOST = "http://armegis.tk/";
    public static final String PROJECT = "downloads/";
    public static final String FILELIST = "list.xml";
    //INTERNAS
    public static final String RUTEINTERNAL = System.getProperty("user.dir") + "/src/main/";
    public static String pathImagesInternal = RUTEINTERNAL + "resources/images/";


    public static void main(String[] args) throws IOException {
        System.out.println(RUTEINTERNAL);
        System.out.println();
        System.out.println("Executing Main.java");
        listaIdiomasPath.add("Català$##$flags/cat");
        listaIdiomasPath.add("Castellano$##$flags/es");
        listaIdiomasPath.add("English$##$flags/en");
        if (new File(pathImagesExternal).exists()) {
            Temas.PATH_USE = pathImagesExternal;
        } else {
            Temas.PATH_USE = pathImagesInternal;
        }
        //FIN INICIO
        if (PreferencesApp.createFilePrefereces()) {
            try {
                PreferencesApp.readFilePrefereces();
            } catch (NullPointerException ignore) {
            }
        }
        //ANTES DE IDIOMA
        if (LOCALE == null) {
            String nameLanguage = Traductor.getLanguageEquals(System.getProperty("user.language"), Main.class);
            if (nameLanguage != null) {
                LOCALE = new Locale(nameLanguage);
            } else {
                LOCALE = new Locale("en");
            }
        }
        TRADUCCIONES_GENERALES = ResourceBundle.getBundle("main.resources.traductions.UIResources", LOCALE);
        //IDIOMA FIN
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource(getPathToFXML(PantallaInicialController.class));
        loader.setLocation(url);
        loader.setControllerFactory((Class<?> type) -> {
            try {
                Object controller = type.newInstance();
                PantallaInicialController.setPrimaryStage(primaryStage);
                return controller;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        /*loader.setControllerFactory((Class<?> type) ->{
            try {
                Object controller = type.newInstance();
                System.out.println(controller.getClass());
                if (controller instanceof PrimaryStageControler) {
                    PrimaryStageControler.setPrimaryStage(primaryStage);
                } else if (controller instanceof IniciarSessionController) {
                }
                return controller;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });*/
        loader.setResources(TRADUCCIONES_GENERALES);
        Parent root = loader.load();
        primaryStage.setTitle(NAME);
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("main.resources/style/styles.css");
        primaryStage.setScene(scene);
        //primaryStage.setMaximized(true);//Pone el Stage en maximizado
        primaryStage.show();
    }


}

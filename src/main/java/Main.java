package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.Inicio.PantallaInicialController;
import main.java.temas.Temas;
import main.java.utils.os.Os;
import main.java.utils.traductor.Traductor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.jar.Manifest;

import static main.java.temas.Temas.pathImagesExternal;
import static main.java.utils.PrimaryStageControler.*;
import static main.java.utils.propietiesAndPreferences.PropertiesApp.createPropieties;
import static main.java.utils.traductor.Traductor.listaIdiomasPath;


public class Main extends Application {
    //SYSTEMA
    public static final String OS = Os.operativeSystem();
    public static final String PATH = System.getProperty("user.dir").replace("proyectoJuegoFX", "");
    public static final String propFileName = PATH + "/conf/config.properties";
    //    String userDir = System.getProperty("user.dir");
    //    Path path = Paths.get(userDir);
    //    String project = path.getFileName();
    //CONFIGS
    public static final String NAME = "nombreDelJuego";
    public static final String VERSION = "0.-13.0.4.20200417.0";
    public static final String HOST = "http://armegis.tk/";
    public static final String PROJECT = "downloads/";
    public static final String FILELIST = "list.xml";
    //INTERNAS
    public static final String RUTEINTERNAL = System.getProperty("user.dir") + "/src/main/";
    public static String pathImagesInternal = RUTEINTERNAL + "resources/images/";

    /*
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            ourSessionFactory = new Configuration().
                    configure("hibernate.cfg.xml").
                    buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }
    */

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
        loader.setResources(TRADUCCIONES);
        Parent root = loader.load();
        primaryStage.setTitle(NAME);
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("main.resources/style/styles.css");
        primaryStage.setScene(scene);
        //primaryStage.setMaximized(true);//Pone el Stage en maximizado
        primaryStage.show();
    }

    public static void main(String[] args) {
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


        String nameLanguage = Traductor.getLanguageEquals(System.getProperty("user.language"), Main.class);
        if (nameLanguage != null) {
            LOCALE = new Locale(nameLanguage);
        } else {
            LOCALE = new Locale("en");
        }
        TRADUCCIONES = ResourceBundle.getBundle("main.resources.traductions.UIResources", LOCALE);

        /*
        final Session session = getSession();
        try {
            System.out.println("querying all the managed entities...");
            final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
            for (Object key : metadataMap.keySet()) {
                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
                final String entityName = classMetadata.getEntityName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {
            session.close();
        }
*/
        launch(args);
    }


}

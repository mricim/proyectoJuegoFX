package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.Inicio.PantallaInicialController;
import main.java.utils.PrimaryStageControler;

import java.net.URL;

public class Main extends Application {
    public static final String RUTEINTERNAL = System.getProperty("user.dir") + "/src/main/";

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
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource(PrimaryStageControler.getPathToFXML(PantallaInicialController.class));
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
        Parent root = loader.load();
        primaryStage.setTitle("Nombre del juego");
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("main.resources/style/styles.css");
        primaryStage.setScene(scene);
        //primaryStage.setMaximized(true);//Pone el Stage en maximizado
        primaryStage.show();
    }

    public static void main(String[] args) {
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

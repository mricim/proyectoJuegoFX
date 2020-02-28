package main.java.juego.mapas.Mundo;

import com.sun.rowset.internal.Row;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class GridPaneRegrowTest extends Application {

    private static String RUTE = "src/main/resources/images/mapas/mundo/";

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //https://stackoverflow.com/questions/54187886/dynamically-sizing-the-contents-of-a-gridpane-to-the-properties-of-their-parent


        int numCiudades = 8;
        int tamaño = 15;
        int capacidadCiudades = 0;
        double casillasTotales = 0;
        do {

            System.out.println("XXXXXXXXXXX");
            casillasTotales = (tamaño * tamaño);
            capacidadCiudades = (int) (casillasTotales * 8) / 25;
            tamaño += 5;

        } while (numCiudades > capacidadCiudades - 1);

        ScrollPane scrollPane = new ScrollPane();
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setGridLinesVisible(true);
        scrollPane.setContent(root);
        scrollPane.setPannable(true);

        Image agua = new Image(new FileInputStream(RUTE + "agua.png"));
        Image aguaBarco = new Image(new FileInputStream(RUTE + "aguabarquito.png"));
        Image isla1 = new Image(new FileInputStream(RUTE + "isla1.png"));
        Image isla2 = new Image(new FileInputStream(RUTE + "isla2.png"));
        Image isla3 = new Image(new FileInputStream(RUTE + "isla3.png"));
        Image isla4 = new Image(new FileInputStream(RUTE + "isla4.png"));
        Image isla5 = new Image(new FileInputStream(RUTE + "isla5.png"));
        Image isla6 = new Image(new FileInputStream(RUTE + "isla6.png"));
        Image isla7 = new Image(new FileInputStream(RUTE + "isla7.png"));
        Image isla8 = new Image(new FileInputStream(RUTE + "isla8.png"));
        Image isla9 = new Image(new FileInputStream(RUTE + "isla9.png"));

        ImageView aguaImg = new ImageView(agua);


//        for (int celdaFila = 0; celdaFila < tamaño; celdaFila++) {
//            for (int celdaColumna = 0; celdaColumna < tamaño; celdaColumna++) {
//                for (int fila = 0; fila < 5; fila++) {
//                    ColumnConstraints col = new ColumnConstraints(agua.getWidth());
//                    root.getColumnConstraints().add(col);
//                    for (int columna = 0; columna < 5; columna++) {
//                        if (fila == 0) {
//                            RowConstraints row = new RowConstraints(agua.getHeight());
//                            root.getRowConstraints().add(row);
//                        }
//
//                        if ((fila == 0 || root.getRowConstraints().size() == tamaño)||(columna == 0 || root.getColumnConstraints().size() == tamaño)){
//
//                            root.add(new ImageView(new Image(new FileInputStream(RUTE+"agua.png")) ),columna,fila);
//                        }else{
//                            root.add(new ImageView(new Image(new FileInputStream(RUTE+"isla1.png")) ),columna,fila);
//
//                        }
//
//
//
//                    }
//                }
//            }
//        }
        for (int fila = 0; fila < tamaño; fila++) {
            ColumnConstraints col = new ColumnConstraints(agua.getWidth());
            root.getColumnConstraints().add(col);
            for (int columna = 0; columna < tamaño; columna++) {
                if (fila == 0) {
                    RowConstraints row = new RowConstraints(agua.getHeight());
                    root.getRowConstraints().add(row);
                }

                if ((fila == 0 || fila == 4) || (columna == 0 || columna == 4)) {

                    root.add(new ImageView(new Image(new FileInputStream(RUTE + "agua.png"))), columna, fila);
                } else {
                    root.add(new ImageView(new Image(new FileInputStream(RUTE + "isla1.png"))), columna, fila);

                }


            }
        }


        //TODO PARA SABER SI TENGO QUE PONER TIERRA O AGUA EN EL GRID, BUSCAR EN LA LISTA DE BATALLON O LA LISTA DE CIUDADES, PARA TENER LA POSICION (X,Y) DE DICHA CIUDAD
        //TODO O BATALLON.


        primaryStage.setScene(new Scene(scrollPane, 1920, 1080));
        primaryStage.show();

//        FXMLLoader loader = new FXMLLoader();
//        //URL url = getClass().getResource("pelea.fxml");
//        URL url = getClass().getResource("/main/java/juego/mapas/Mundo/mundo.fxml");
//        loader.setLocation(url);
//        loader.setControllerFactory((Class<?> type) -> {// CREA PrimaryStageAware
//            try {
//                Object controller = type.newInstance();
//                if (controller instanceof PrimaryStageControler) {
//                    ((PrimaryStageControler) controller).setPrimaryStage(primaryStage);
//                }
//                return controller;
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        });
//        Parent root = loader.load();
//        primaryStage.setTitle("Hello World");
//        Scene scene = new Scene(root);
//        //scene.getStylesheets().add("main.resources/style/styles.css");
//        primaryStage.setScene(scene);
//        primaryStage.setMaximized(true);//Pone el Stage en maximizado
//        primaryStage.show();
    }
}

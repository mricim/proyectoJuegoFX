package main.java.juego.mapas.Mundo;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class GridPaneRegrowTest extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //https://stackoverflow.com/questions/54187886/dynamically-sizing-the-contents-of-a-gridpane-to-the-properties-of-their-parent
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setGridLinesVisible(true);

        int numCiudades = 127;
        int tamaño = 15;
        int capacidadCiudades = 0;
        double casillasTotales = 0;
        do {

            System.out.println("XXXXXXXXXXX");
            casillasTotales = (tamaño * tamaño);
            capacidadCiudades  = (int)(casillasTotales * 8) / 25;
            tamaño += 5;

        }while (numCiudades > capacidadCiudades-1);





        for (int col = 0; col < tamaño; col++) {
            root.getColumnConstraints()
                    .add(new ColumnConstraints(-1, -1, -1, Priority.ALWAYS, HPos.CENTER, false));
            for (int row = 0; row < tamaño; row++) {
                if (col == 0) {
                    root.getRowConstraints()
                            .add(new RowConstraints(-1, -1, -1, Priority.ALWAYS, VPos.CENTER, false));
                }
                root.add(new Label(String.format("(%d,%d)", row, col)), col, row);
            }
        }
        //TODO PARA SABER SI TENGO QUE PONER TIERRA O AGUA EN EL GRID, BUSCAR EN LA LISTA DE BATALLON O LA LISTA DE CIUDADES, PARA TENER LA POSICION (X,Y) DE DICHA CIUDAD
        //TODO O BATALLON.

/*
        for (int i = 0; i <= tamaño; i++) {
            for (int j = 0; j <= tamaño; j++) {
                root.add(new Label("XX"));
                System.out.println(i + " " + j);
            }
        }
*/

        primaryStage.setScene(new Scene(root, 1920, 1080));
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

package main.java.login;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import main.java.utils.CallImages;

import static main.java.jugadores.Jugador.listaCiudades;

public class GridPaneRegrowTest extends Application {

    private static String RUTE = "mapas/mundo/";

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //https://stackoverflow.com/questions/54187886/dynamically-sizing-the-contents-of-a-gridpane-to-the-properties-of-their-parent
//        Ciudad ciudad1 = new Ciudad(1, "ciudad 1", 1,1, 1, 300, 20, 50, 70, 90, 40, 50);
//        Ciudad ciudad2 = new Ciudad(2, "ciudad 2", 2, 2,1, Integer.MAX_VALUE, 5000, 60000, 3000, 756123, 6584110, 53);

        int numCiudades = 12000;
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

        Image agua = CallImages.getImage(RUTE, "agua");
        Image aguaBarco = CallImages.getImage(RUTE, "aguabarquito");
        Image ciudad = CallImages.getImage(RUTE, "ciudad");
        Image isla1 = CallImages.getImage(RUTE, "isla1");
        Image isla2 = CallImages.getImage(RUTE, "isla2");
        Image isla3 = CallImages.getImage(RUTE, "isla3");
        Image isla4 = CallImages.getImage(RUTE, "isla4");
        Image isla5 = CallImages.getImage(RUTE, "isla5");
        Image isla6 = CallImages.getImage(RUTE, "isla6");
        Image isla7 = CallImages.getImage(RUTE, "isla7");
        Image isla8 = CallImages.getImage(RUTE, "isla8");
        Image isla9 = CallImages.getImage(RUTE, "isla9");



        for (int fila = 0; fila < tamaño; fila++) {
            ColumnConstraints col = new ColumnConstraints(100);
            root.getColumnConstraints().add(col);
            for (int columna = 0; columna < tamaño; columna++) {
                ImageView imageView = new ImageView();
                if (fila == 0) {
                    RowConstraints row = new RowConstraints(100);
                    root.getRowConstraints().add(row);
                }

                if (fila % 5 == 0 || fila % 5 == 4 || columna % 5 == 0 || columna % 5 == 4) {
                    imageView.setImage(agua);
//                    root.add(new ImageView(CallImages.getImage(RUTE, "agua"))), columna, fila);

                } else {
                    boolean containsCity = false;
                    if (listaCiudades.containsKey(fila+"-"+columna)){
                        containsCity = true;
                    }
                    if(fila%5 == 1 && columna % 5 == 1){

                        if (containsCity){
                            imageView.setImage(ciudad);
//                            root.add(new ImageView(CallImages.getImage(RUTE, "ciudad"))), columna, fila);

                        }else{
                            imageView.setImage(isla1);
//                            root.add(new ImageView(CallImages.getImage(RUTE, "isla1"))), columna, fila);

                        }
                    }else{
                        if (containsCity){
                            imageView.setImage(ciudad);

//                            root.add(new ImageView(CallImages.getImage(RUTE, "ciudad"))), columna, fila);

                        }else{
                            imageView.setImage(isla2);
//                            root.add(new ImageView(CallImages.getImage(RUTE, "isla2"))), columna, fila);
                        }

                    }

                    imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                        imageView.setCursor(Cursor.HAND);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                    imageView.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }

                root.add(imageView, columna, fila);
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

package main.java.mapas.city;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CiudadController extends PrimaryStageControlador implements Initializable {
    private static String RUTE = "../../../resources/icons/";
    @FXML
    BorderPane borderPane;
    @FXML
    GridPane gridPaneMap;
    @FXML
    ImageView imagenDeFondo;
    private static ArrayList<Posiciones> listaPosicionesServer = new ArrayList<>();
    private static HashMap<Integer[], ImageView> listaPosicionesMapa = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        int colum = gridPaneMap.getColumnConstraints().size();
        int rows = gridPaneMap.getRowConstraints().size();
/*
        for (int i = 0; i <= colum; i++) {
            for (int j = 0; j <= rows; j++) {
                Image imageLimpia = new Image(getClass().getResource(RUTE + "limpio4.png").toExternalForm(), 100, 100, false, true);
                ImageView imageViewLimpia = new ImageView(imageLimpia);
                imageViewLimpia.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    System.out.println("Limpiar borde");
                    borderPane.setLeft(null);
                });
                gridPaneMap.add(imageViewLimpia, i, j);
                listaPosicionesMapa.put(new Integer[]{i, j}, imageViewLimpia);
                System.out.println(i + " " + j);
            }
        }
*/

/* NO FUNCIONA
        imagenDeFondo.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            borderPane.setLeft(null);
        });
*/

        listaPosicionesServer.add(new Posiciones(8, 12, "example_empty.png", "example_academy.png"));
        listaPosicionesServer.add(new Posiciones(8, 15, "example_empty.png", "example_academy.png"));
        for (Posiciones posiciones : listaPosicionesServer) {
            Image image = new Image(getClass().getResource(RUTE + posiciones.getPathImage()).toExternalForm(), 100, 100, false, true);
            ImageView imageView = new ImageView(image);
            imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                imageView.setCursor(Cursor.HAND);
                imageView.setImage(new Image(getClass().getResource(RUTE + posiciones.getPathImageOnMouseOver()).toExternalForm(), 100, 100, false, true));
            });
            imageView.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                imageView.setImage(new Image(getClass().getResource(RUTE + posiciones.getPathImage()).toExternalForm(), 100, 100, false, true));
            });
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                System.out.println("Imagen empty clicada");//TODO
            });

            Integer[] remover = new Integer[]{posiciones.getX(), posiciones.getY()};
            gridPaneMap.getChildren().remove(listaPosicionesMapa.get(remover));
            listaPosicionesMapa.remove(remover);
            listaPosicionesMapa.put(remover, imageView);
            gridPaneMap.add(imageView, posiciones.getX(), posiciones.getY());


        }

        //TODO LEER DE LA BASE DE DATOS

        //TODO VOLVER A CARGAR LAS IMAGENES

        /*
        final Image image = new Image(getClass().getResource("cross_red.jpg").toExternalForm(), 20, 20, true, true);
        MenuButton menuButton = new MenuButton("Don't touch this");
        menuButton.setGraphic(new ImageView(image));
        menuButton.getItems().addAll(new MenuItem("Really"), new MenuItem("Do not"));
        toolbar.getItems().addAll(menuButton);
         */


        Image image = new Image(getClass().getResource(RUTE + "example_casa.png").toExternalForm(), 100, 100, false, true);
        ImageView imageView = new ImageView(image);
        gridPaneMap.add(imageView, 7, 12);
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            borderPane.setLeft(null);
        });

        Image image2 = new Image(getClass().getResource(RUTE + "example_academy.png").toExternalForm(), 100, 100, false, true);
        ImageView imageView2 = new ImageView(image2);
        gridPaneMap.add(imageView2, 12, 7);

        Image image3 = new Image(getClass().getResource(RUTE + "example_empty.png").toExternalForm(), 100, 100, false, true);
        ImageView imageView3 = new ImageView(image3);
        gridPaneMap.add(imageView3, 9, 5);

    }

    @FXML
    public void botton2(ActionEvent actionEvent) {
        cambiarNombreStage("El nombre que queramos");
        System.out.println("COSASA");
    }


}

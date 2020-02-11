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
import java.util.ResourceBundle;

import static main.java.mapas.city.Main.listaPosiciones;


public class CiudadController extends PrimaryStageControlador implements Initializable {
    private static String RUTE = "../../../resources/mapas/city/";
    @FXML
    BorderPane borderPane;
    @FXML
    GridPane gridPaneMap;
    /*
    @FXML
    ImageView imagenDeFondo;
     */



    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Integer[] posicion1= new Integer[]{8,12};
//        listaPosicionesMapa.put(posicion1,new Posiciones(posicion1,"0.png","0.png"));




        gridPaneMap.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Limpiar menu izquierda");
            borderPane.setLeft(null);
        });
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


        for (Posiciones posiciones : listaPosiciones.values()) {
            Image image = posiciones.getImage();
            ImageView imageView = new ImageView(image);
            imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                imageView.setCursor(Cursor.HAND);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                imageView.setImage(posiciones.getImageOnMouseOver());
            });
            imageView.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                imageView.setImage(posiciones.getImage());
            });
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                (new Thread() {
                    public void run() {
                        try {
                            Thread.sleep(20);
                            System.out.println("Imagen empty clicada");//TODO
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            });
/*
            Integer[] remover = new Integer[]{posiciones.getX(), posiciones.getY()};
            gridPaneMap.getChildren().remove(listaPosicionesMapa.get(remover));
            listaPosicionesMapa.remove(remover);
            listaPosicionesMapa.put(remover, imageView);

 */
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

/*
        Image image = new Image(getClass().getResource(RUTE + "1_0-clic.png").toExternalForm(), 100, 100, false, true);
        ImageView imageView = new ImageView(image);
        gridPaneMap.add(imageView, 7, 12);
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            borderPane.setLeft(null);
        });

        Image image2 = new Image(getClass().getResource(RUTE + "1_0.png").toExternalForm(), 100, 100, false, true);
        ImageView imageView2 = new ImageView(image2);
        gridPaneMap.add(imageView2, 12, 7);

        Image image3 = new Image(getClass().getResource(RUTE + "0.png").toExternalForm(), 100, 100, false, true);
        ImageView imageView3 = new ImageView(image3);
        gridPaneMap.add(imageView3, 9, 5);
*/
    }

    @FXML
    public void botton2(ActionEvent actionEvent) {
        cambiarNombreStage("El nombre que queramos");
        System.out.println("COSASA");
    }


}

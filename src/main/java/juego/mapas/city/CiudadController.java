package main.java.juego.mapas.city;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import main.java.juego.Main;
import main.java.juego.PrimaryStageControler;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import static main.java.juego.Jugador.listaCiudades;


public class CiudadController extends PrimaryStageControler implements Initializable {
    private static String RUTE = "../../../resources/mapas/city/";
    String nameThisCity;
    @FXML
    BorderPane borderPane;
    @FXML
    GridPane gridPaneMap;
    /*
    @FXML
    ImageView imagenDeFondo;
     */
    @FXML
    SplitMenuButton selectorCiudad;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameThisCity = getCiudad().getNameCity();

        gridPaneMap.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {//Cerrar el menu
            System.out.println("Limpiar menu izquierda");
            borderPane.setLeft(null);
        });

        selectorCiudad.setText(nameThisCity);//Seleccionar otra ciudad
        for (Ciudad ciudad : listaCiudades.values()) {
            String nameCity=ciudad.getNameCity();
            if (nameThisCity != nameCity) {
                MenuItem menuItem = new MenuItem();
                menuItem.setText(nameCity);
                menuItem.setOnAction((e) -> {
                    System.out.println("clicado " + nameCity);//TODO CAMBIAR ESTO POR LA NUEVA CIUDAD
                    setCiudad(ciudad);
                    try {
                        new PrimaryStageControler().reload(getStage());;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                selectorCiudad.getItems().add(menuItem);
            }
        }

/*
        int colum = gridPaneMap.getColumnConstraints().size();
        int rows = gridPaneMap.getRowConstraints().size();
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

        Collection<PosicionEdificio> edificioArrayList = getCiudad().listaPosicionesEdificios.values();
        for (PosicionEdificio posicionEdificio : edificioArrayList) {
            Image image = posicionEdificio.getImage();
            ImageView imageView = new ImageView(image);
            imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                imageView.setCursor(Cursor.HAND);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                imageView.setImage(posicionEdificio.getImageOnMouseOver());
            });
            imageView.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                imageView.setImage(posicionEdificio.getImage());
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
            gridPaneMap.add(imageView, posicionEdificio.getX(), posicionEdificio.getY());


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

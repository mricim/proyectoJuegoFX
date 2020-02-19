package main.java.juego.mapas.Mundo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import main.java.juego.Jugador;
import main.java.juego.PrimaryStageControler;

import java.net.URL;
import java.util.*;


public class MundoController extends PrimaryStageControler implements Initializable {
    private static String RUTE = "../../../resources/mapas/city/";
    static Jugador jugador;
    static boolean basura = true;

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
    @FXML
    Label oro, madera;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jugador = getJugador();
/*
        int oroDisponible = mundo.getOro();
        oro.setText(String.valueOf(oroDisponible));
*/

        gridPaneMap.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {//Cerrar el menu
            queClicas(null);
        });

/*
        Collection<PosicionEdificio> posicionEdificios = getCiudad().getListaPosicionesEdificios().values();
        for (PosicionEdificio posicionEdificio : posicionEdificios) {
//            Edificio edificio = posicionEdificio.getEdificio();
//            int idEdificio = edificio.getId();
//            int nivelEdificio = edificio.getNivel();

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
            imageView.setOnMouseClicked(e -> {
                queClicas(posicionEdificio);//System.out.println("Imagen Edificio clicada: " + listaEdificios.get(idEdificio + "_" + nivelEdificio));
            });
            gridPaneMap.add(imageView, posicionEdificio.getColumnas(), posicionEdificio.getFilas());
        }

 */
    }


    @FXML
    public void botton2(ActionEvent actionEvent) {
        cambiarNombreStage("El nombre que queramos");
        System.out.println("COSASA");
    }

    private void queClicas(PosicionCasilla posicionCasilla) {
        if (basura) {
            if (posicionCasilla == null) {
                borderPane.setLeft(null);//System.out.println("Limpiar menu izquierda");
            } else {
                basura = false;
                (new Thread() {
                    public void run() {
                        try {
                            Thread.sleep(200);
                            basura = true;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                createMenuLeft(borderPane, posicionCasilla);
            }
        }
    }

    private static void createMenuLeft(BorderPane borderPane, PosicionCasilla posicionCasilla) {

    }
}

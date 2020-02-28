package main.java.juego.mapas.Mundo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import main.java.Jugadores.Jugador;
import main.java.Utils.CallImages;
import main.java.Utils.PrimaryStageControler;

import java.net.URL;
import java.util.*;

import static main.java.Jugadores.Jugador.listaCiudades;


public class MundoController extends PrimaryStageControler implements Initializable {
    final private static String RUTE = "mapas/mundo/";
    public static final String THIS_RUTE ="juego/mapas/Mundo/mundo.fxml";
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

        gridPaneMap.setAlignment(Pos.CENTER);
        gridPaneMap.setGridLinesVisible(true);

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
            gridPaneMap.getColumnConstraints().add(col);
            for (int columna = 0; columna < tamaño; columna++) {
                ImageView imageView = new ImageView();
                if (fila == 0) {
                    RowConstraints row = new RowConstraints(100);
                    gridPaneMap.getRowConstraints().add(row);
                }

                if (fila % 5 == 0 || fila % 5 == 4 || columna % 5 == 0 || columna % 5 == 4) {
                    imageView.setImage(agua);
//                    root.add(new ImageView(CallImages.getImage(RUTE, "agua"))), columna, fila);

                } else {
                    boolean containsCity = false;
                    if (listaCiudades.containsKey(fila+"_"+columna)){
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


                gridPaneMap.add(imageView, columna, fila);
            }
        }


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

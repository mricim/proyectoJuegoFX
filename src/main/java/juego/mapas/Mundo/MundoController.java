package main.java.juego.mapas.Mundo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import main.java.Jugadores.Jugador;
import main.java.Utils.CallImages;
import main.java.Utils.PrimaryStageControler;
import main.java.juego.mapas.Ciudad.Ciudad;
import main.java.juego.mapas.Ciudad.CiudadController;
import main.java.juego.mapas.Ciudad.ContentCity.Edificio;
import main.java.juego.mapas.Recursos;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static main.java.Jugadores.Jugador.listaBatallones;
import static main.java.Jugadores.Jugador.listaCiudades;


public class MundoController extends PrimaryStageControler implements Initializable {
    final private static String RUTE = "mapas/mundo/";
    public static final String THIS_RUTE = "juego/mapas/Mundo/mundo.fxml";
    static Jugador jugador;
    static boolean basura = true;
    public FlowPane recuros;
    static Ciudad ciudad;

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
        ciudad = getCiudad();
        nameThisCity = ciudad.getNameCity();
/*
        int oroDisponible = mundo.getOro();
        oro.setText(String.valueOf(oroDisponible));
*/
        ObservableList<Node> observableList= recuros.getChildren();
        for (Recursos recursos : ciudad.getRecursosTreeMap().values()) {
            Label label = new Label(String.valueOf(recursos.getCantidad()));
            ImageView imageView= new ImageView(recursos.getImage());
            imageView.setFitWidth(30.0);
            imageView.setFitHeight(30.0);
            HBox hBox = new HBox(imageView,label);
            hBox.setId(String.valueOf(recursos.getId()));
            hBox.setAlignment(Pos.CENTER);
            hBox.setPrefHeight(20.0);
            hBox.setPrefWidth(70.0);
            hBox.setSpacing(5.0);

            observableList.add(hBox);
        }
        gridPaneMap.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {//Cerrar el menu
            queClicas(null,null);
        });
        selectorCiudad.setText(nameThisCity);//Seleccionar otra ciudad
        for (Ciudad ciudadTemp : jugador.listaCiudadesPropias.values()) {
            String nameCity = ciudadTemp.getNameCity();
            if (nameThisCity != nameCity) {
                MenuItem menuItem = new MenuItem();
                menuItem.setText(nameCity);
                menuItem.setOnAction((e) -> {
                    setCiudad(ciudadTemp);
                    try {
                        new PrimaryStageControler().reload(getStage(), THIS_RUTE, false);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                selectorCiudad.getItems().add(menuItem);
            }
        }


        int numCiudades = 1200;
        int tamaño = 15;
        int capacidadCiudades = 0;
        double casillasTotales = 0;
        do {
            casillasTotales = (tamaño * tamaño);
            capacidadCiudades = (int) (casillasTotales * 8) / 25;
            tamaño += 5;

        } while (numCiudades > capacidadCiudades - 1);

        gridPaneMap.setAlignment(Pos.CENTER);
        gridPaneMap.setGridLinesVisible(true);
        /*
        Image error = CallImages.getImage(RUTE, "error");

        Image agua = CallImages.getImage(RUTE, "a");

        Image aguaBarco = CallImages.getImage(RUTE, "ab");
        Image aguaBarcoHover = CallImages.getImage(RUTE, "ab_H");
        Image aguaBarcoPropio = CallImages.getImage(RUTE, "ab_P");
        Image aguaBarcoPropioHover = CallImages.getImage(RUTE, "ab_P_H");

        Image isla1 = CallImages.getImage(RUTE, "i1");
        Image isla1Ciudad = CallImages.getImage(RUTE, "i1_c");
        Image isla1CiudadPropia = CallImages.getImage(RUTE, "i1_c_P");
        Image isla1CiudadHover = CallImages.getImage(RUTE, "i1_c_H");
        Image isla1CiudadPropiaHover = CallImages.getImage(RUTE, "i1c_P_H");
        Image isla2 = CallImages.getImage(RUTE, "i2");
        Image isla2Ciudad = CallImages.getImage(RUTE, "i2c");
        Image isla2CiudadPropia = CallImages.getImage(RUTE, "i2cP");
        Image isla2CiudadHover = CallImages.getImage(RUTE, "i2cH");
        Image isla2CiudadPropiaHover = CallImages.getImage(RUTE, "i2cPH");
        Image isla3 = CallImages.getImage(RUTE, "i3");
        Image isla3Ciudad = CallImages.getImage(RUTE, "i3c");
        Image isla3CiudadPropia = CallImages.getImage(RUTE, "i3cP");
        Image isla3CiudadHover = CallImages.getImage(RUTE, "i3cH");
        Image isla3CiudadPropiaHover = CallImages.getImage(RUTE, "i3cPH");
        Image isla4 = CallImages.getImage(RUTE, "i4");
        Image isla4Ciudad = CallImages.getImage(RUTE, "i4c");
        Image isla4CiudadPropia = CallImages.getImage(RUTE, "i4cP");
        Image isla4CiudadHover = CallImages.getImage(RUTE, "i4cH");
        Image isla4CiudadPropiaHover = CallImages.getImage(RUTE, "i4cPH");
        Image isla5 = CallImages.getImage(RUTE, "i5");
        Image isla5Ciudad = CallImages.getImage(RUTE, "i5c");
        Image isla5CiudadPropia = CallImages.getImage(RUTE, "i5cP");
        Image isla5CiudadHover = CallImages.getImage(RUTE, "i5cH");
        Image isla5CiudadPropiaHover = CallImages.getImage(RUTE, "i5cPH");
        Image isla6 = CallImages.getImage(RUTE, "i6");
        Image isla6Ciudad = CallImages.getImage(RUTE, "i6c");
        Image isla6CiudadPropia = CallImages.getImage(RUTE, "i6cP");
        Image isla6CiudadHover = CallImages.getImage(RUTE, "i6cH");
        Image isla6CiudadPropiaHover = CallImages.getImage(RUTE, "i6cPH");
        Image isla7 = CallImages.getImage(RUTE, "i7");
        Image isla7Ciudad = CallImages.getImage(RUTE, "i7c");
        Image isla7CiudadPropia = CallImages.getImage(RUTE, "i7cP");
        Image isla7CiudadHover = CallImages.getImage(RUTE, "i7cH");
        Image isla7CiudadPropiaHover = CallImages.getImage(RUTE, "i7cPH");
        Image isla8 = CallImages.getImage(RUTE, "i8");
        Image isla8Ciudad = CallImages.getImage(RUTE, "i8c");
        Image isla8CiudadPropia = CallImages.getImage(RUTE, "i8cP");
        Image isla8CiudadHover = CallImages.getImage(RUTE, "i8cH");
        Image isla8CiudadPropiaHover = CallImages.getImage(RUTE, "i8cPH");

        Image isla9r1 = CallImages.getImage(RUTE, "i9_0");
        Image isla9r2 = CallImages.getImage(RUTE, "i9_1");
        Image isla9r3 = CallImages.getImage(RUTE, "i9_2");
        Image isla9Ciudad = CallImages.getImage(RUTE, "i9c");
        Image isla9CiudadPropia = CallImages.getImage(RUTE, "i9cP");
        Image isla9CiudadHover = CallImages.getImage(RUTE, "i9cH");
        Image isla9CiudadPropiaHover = CallImages.getImage(RUTE, "i9cPH");
*/

        for (int fila = 0; fila < tamaño; fila++) {
            ColumnConstraints col = new ColumnConstraints(100);
            gridPaneMap.getColumnConstraints().add(col);
            for (int columna = 0; columna < tamaño; columna++) {

                //TODO XXXXXXXXXXXXXX
                ImageView imageView = new ImageView();
                if (fila == 0) {
                    RowConstraints row = new RowConstraints(100);
                    gridPaneMap.getRowConstraints().add(row);
                }
/*
                boolean containsBatallon = false;
                boolean containsCity = false;
                 if (listaBatallones.containsKey(fila + "_" + columna)) {
                 if (listaCiudades.containsKey(fila + "_" + columna)) {
 */
                String contieneAguaIsla = "";
                String contieneAgua = "";
                String contieneBarco = "";
                String islaPosicion = "";
                String contieneCiudad = "";
                String randomCiudad9 = "";

                if (fila % 5 == 0 || fila % 5 == 4 || columna % 5 == 0 || columna % 5 == 4) {
                    contieneAguaIsla = "a";
                    if (listaBatallones.containsKey(fila + "_" + columna)) {
                        if (jugador.listaBatallonesPropios.containsKey(fila + "_" + columna)) {
                            contieneBarco = "b_P";
                        } else {
                            contieneBarco = "b";
                        }
                    }
                } else {
                    contieneAguaIsla = "i";

                    if (listaCiudades.containsKey(fila + "_" + columna)) {
                        if (jugador.listaCiudadesPropias.containsKey(fila + "_" + columna)) {
                            contieneCiudad = "_cP";
                        } else {
                            contieneCiudad = "_c";
                        }
                    }
                    islaPosicion = (fila % 5) + "-" + (columna % 5);
                    if (fila % 5 == 3 && columna % 5 == 3) {//TODO RANDOMIZADOR PARA LA POSICION 3-3
                        switch (new Random().nextInt((3 - 0) + 1) + 0) {
                            case 1:
                                randomCiudad9 = "_r0";
                                break;
                            case 2:
                                randomCiudad9 = "_r1";
                                break;
                            case 3:
                                randomCiudad9 = "_r2";
                                break;
                            default:
                                randomCiudad9 = "_r0";
                                break;
                        }
                    }

                }
                String nameCaller = contieneAguaIsla + contieneAgua + contieneBarco + islaPosicion + contieneCiudad + randomCiudad9;
                String nameCallerHover = contieneAguaIsla + contieneAgua + contieneBarco + islaPosicion + contieneCiudad + randomCiudad9+"_H";
                //System.out.println(nameCaller);
                imageView.setImage(CallImages.getImage(RUTE, nameCaller));

                if (contieneBarco == "b" || contieneBarco == "b_P" || contieneCiudad == "_c" || contieneCiudad == "_cP") {//he pensado que el maus solo se ponga* en las ciudades y los barcos
                    imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                        imageView.setCursor(Cursor.HAND);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        imageView.setImage(CallImages.getImage(RUTE, nameCallerHover));
                    });
                    imageView.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        imageView.setImage(CallImages.getImage(RUTE, nameCaller));
                    });
                } else {
                    imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {//Cerrar el menu
                        queClicas(null, null);
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

    private void queClicas(PosicionCasilla Casilla, ImageView imageView) {
        if (basura) {//BLOQUEA UN MOMENTO EL SISTEMA DE CLIC PARA QUE CARGE EL MENU Y NO LO BORRE
            if (Casilla == null) {
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
                createMenuLeft(borderPane, Casilla, imageView);
            }
        }
    }

    private static void createMenuLeft(BorderPane borderPane, PosicionCasilla Casilla, ImageView imageView) {

    }

    public void toMundo(MouseEvent mouseEvent) {//TODO ES POSIBLE QUE EN ESTE MAPA NO INTERESE TENER ESTO
        try {
            new PrimaryStageControler().reload(getStage(), CiudadController.THIS_RUTE, false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

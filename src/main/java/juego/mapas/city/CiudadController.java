package main.java.juego.mapas.city;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.java.juego.PrimaryStageControler;
import main.java.juego.mapas.EdificiosPreCargados;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import static javafx.geometry.Pos.TOP_CENTER;
import static javafx.scene.text.TextAlignment.CENTER;
import static jdk.nashorn.internal.objects.Global.Infinity;
import static main.java.juego.Jugador.listaCiudades;
import static main.java.juego.Jugador.listaEdificiosPreCargada;


public class CiudadController extends PrimaryStageControler implements Initializable {
    private static String RUTE = "../../../resources/mapas/city/";
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
    Label oro, madera, piedra, hierro, comida, poblacion, felicidad;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Ciudad ciudad = getCiudad();
        nameThisCity = ciudad.getNameCity();

        int oroDisponible = ciudad.getOro();
        String oroStrignDisponible = String.valueOf(oroDisponible);
        oro.setText(oroStrignDisponible);
        int maderaDisponible = ciudad.getMadera();
        String maderaStrignDisponible = String.valueOf(maderaDisponible);
        madera.setText(maderaStrignDisponible);
        int piedraDisponible = ciudad.getPiedra();
        String piedraStrignDisponible = String.valueOf(piedraDisponible);
        piedra.setText(piedraStrignDisponible);
        int hierroDisponible = ciudad.getHierro();
        String hierroStrignDisponible = String.valueOf(hierroDisponible);
        hierro.setText(hierroStrignDisponible);
        int comidaDisponible = ciudad.getComida();
        String comidaStrignDisponible = String.valueOf(comidaDisponible);
        comida.setText(comidaStrignDisponible);
        int poblacionDisponible = ciudad.getPoblacion();
        String poblacionStrignDisponible = String.valueOf(poblacionDisponible);
        poblacion.setText(poblacionStrignDisponible);
        int felicidadDisponible = ciudad.getFelicidad();
        String felicidadStrignDisponible = String.valueOf(felicidadDisponible);
        felicidad.setText(felicidadStrignDisponible);


        gridPaneMap.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {//Cerrar el menu
//            System.out.println("Limpiar menu izquierda");
//            borderPane.setLeft(null);
            queClicas(null);
        });


        selectorCiudad.setText(nameThisCity);//Seleccionar otra ciudad
        for (Ciudad ciudadTemp : listaCiudades.values()) {
            String nameCity = ciudadTemp.getNameCity();
            if (nameThisCity != nameCity) {
                MenuItem menuItem = new MenuItem();
                menuItem.setText(nameCity);
                menuItem.setOnAction((e) -> {
                    System.out.println("clicado " + nameCity);//TODO CAMBIAR ESTO POR LA NUEVA CIUDAD
                    setCiudad(ciudadTemp);
                    try {
                        new PrimaryStageControler().reload(getStage());
                        ;
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

            gridPaneMap.add(imageView, posicionEdificio.getX(), posicionEdificio.getY());
        }
    }


    @FXML
    public void botton2(ActionEvent actionEvent) {
        cambiarNombreStage("El nombre que queramos");
        System.out.println("COSASA");
    }

    private void queClicas(PosicionEdificio posicionEdificio) {
        if (basura) {
            if (posicionEdificio==null) {
                borderPane.setLeft(null);//System.out.println("Limpiar menu izquierda");
            } else {
                basura=false;
                (new Thread() {
                    public void run() {
                        try {
                            Thread.sleep(200);
                            basura=true;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                createMenuLeft(borderPane,posicionEdificio);
            }
        }
    }

    private static void createMenuLeft(BorderPane borderPane,PosicionEdificio posicionEdificio) {
        System.out.println(posicionEdificio.getEdificio());
        Edificio edificio = posicionEdificio.getEdificio();
        EdificiosPreCargados edificiosPreCargados=listaEdificiosPreCargada.get(edificio.getId() + "_" + edificio.getNivel());

        //BLOQUE
        Label nombreEdificio = new Label(edificiosPreCargados.getNombre());
        nombreEdificio.setTextAlignment(CENTER);
        nombreEdificio.setWrapText(true);

        //Image image = new Image(edificio.getImage());
        ImageView imageView = new ImageView(edificio.getImage());
        imageView.setFitWidth(150.0);
        imageView.setFitHeight(200.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        Label descripcionEdificio = new Label(edificiosPreCargados.getDescripcion());
        descripcionEdificio.setTextAlignment(CENTER);
        descripcionEdificio.setWrapText(true);

        VBox vBoxBloque = new VBox(nombreEdificio, imageView, descripcionEdificio);
        vBoxBloque.setAlignment(TOP_CENTER);
        //FIN BLOQUE

        //BLOQUE
        Label nombreEdificio2 = new Label("nombre");
        nombreEdificio2.setTextAlignment(CENTER);
        nombreEdificio2.setWrapText(true);

        //Image image = new Image();
        ImageView imageView2 = new ImageView();
        imageView2.setFitWidth(150.0);
        imageView2.setFitHeight(200.0);
        imageView2.setPickOnBounds(true);
        imageView2.setPreserveRatio(true);

        Label descripcionEdificio2 = new Label("Descripción");
        descripcionEdificio2.setTextAlignment(CENTER);
        descripcionEdificio2.setWrapText(true);

        VBox vBoxBloque2 = new VBox(nombreEdificio2, imageView2, descripcionEdificio2);
        vBoxBloque2.setAlignment(TOP_CENTER);
        //FIN BLOQUE

        VBox vBox = new VBox(vBoxBloque,vBoxBloque2);
        vBox.setSpacing(10);
        vBox.maxWidth(200.0);
        vBox.setAlignment(TOP_CENTER);

        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.maxWidth(-Infinity);
        scrollPane.prefWidth(200.0);
        //scrollPane.()BorderPane.alignment="CENTER"

        borderPane.setLeft(scrollPane);
        /*


                        <children>
                            <Label text="Nombre del edificio" textAlignment="CENTER" wrapText="true" />
                            <ImageView fitHeight="150.0" fitWidth="200.0" pickOnBounds="true" preserveRatio="true" />
                            <Label text="Descripcion del edificio que sera mas larga que el nombre del edificio" textAlignment="CENTER" wrapText="true" />
                        </children>

                    </VBox>
                    <Separator prefWidth="200.0" />
                    <VBox alignment="TOP_CENTER">
                        <children>
                            <Label text="Nombre del edificios" textAlignment="CENTER" wrapText="true" />
                            <ImageView fitHeight="150.0" fitWidth="200.0" pickOnBounds="true" preserveRatio="true" />
                            <Label text="Descripcion del edificio que sera mas larga que el nombre del edificio" textAlignment="CENTER" wrapText="true" />
                        </children>
                    </VBox> <Separator prefWidth="200.0" />
                    <VBox alignment="TOP_CENTER">
                        <children>
                            <Label text="Nombre del edificios" textAlignment="CENTER" wrapText="true" />
                            <ImageView fitHeight="150.0" fitWidth="200.0" pickOnBounds="true" preserveRatio="true" />
                            <Label text="Descripcion del edificio que sera mas larga que el nombre del edificio" textAlignment="CENTER" wrapText="true" />
                        </children>
                    </VBox>
                    <Separator prefWidth="200.0" />
                    <VBox alignment="TOP_CENTER">
                        <children>
                            <Label text="Nombre del edificios" textAlignment="CENTER" wrapText="true" />
                            <ImageView fitHeight="150.0" fitWidth="200.0" pickOnBounds="true" preserveRatio="true" />
                            <Label text="Descripcion del edificio que sera mas larga que el nombre del edificio" textAlignment="CENTER" wrapText="true" />
                        </children>
                    </VBox><Separator prefWidth="200.0" />
                    <VBox alignment="TOP_CENTER">
                        <children>
                            <Label text="Nombre del edificios" textAlignment="CENTER" wrapText="true" />
                            <ImageView fitHeight="150.0" fitWidth="200.0" pickOnBounds="true" preserveRatio="true" />
                            <Label text="Descripcion del edificio que sera mas larga que el nombre del edificio" textAlignment="CENTER" wrapText="true" />
                        </children>
                    </VBox><Separator prefWidth="200.0" />
                    <VBox alignment="TOP_CENTER">
                        <children>
                            <Label text="Nombre del edificios" textAlignment="CENTER" wrapText="true" />
                            <ImageView fitHeight="150.0" fitWidth="200.0" pickOnBounds="true" preserveRatio="true" />
                            <Label text="Descripcion del edificio que sera mas larga que el nombre del edificio" textAlignment="CENTER" wrapText="true" />
                        </children>
                    </VBox>
                </VBox>
            </content>
        </ScrollPane>
        */
    }
}

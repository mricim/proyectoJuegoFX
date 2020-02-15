package main.java.juego.mapas.city;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.java.juego.PrimaryStageControler;
import main.java.juego.mapas.EdificiosPreCargados;
import main.java.juego.mapas.city.ContentCity.Edificio;
import main.java.juego.mapas.city.ContentCity.PosicionEdificio;
import main.java.juego.mapas.city.ContentCity.Recursos;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static javafx.geometry.Pos.TOP_CENTER;
import static javafx.scene.text.TextAlignment.CENTER;
import static jdk.nashorn.internal.objects.Global.Infinity;
import static main.java.juego.Jugador.listaCiudades;


public class CiudadController extends PrimaryStageControler implements Initializable {
    private static String RUTE = "../../../resources/mapas/city/";
    static boolean basura = true;
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
    Label oro, madera, piedra, hierro, comida, poblacion, felicidad,investigacion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ciudad = getCiudad();
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
            if (posicionEdificio == null) {
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
                createMenuLeft(borderPane, posicionEdificio);
            }
        }
    }

    private static void createMenuLeft(BorderPane borderPane, PosicionEdificio posicionEdificio) {
        Edificio edificio = posicionEdificio.getEdificio();
        //EdificiosPreCargados edificiosPreCargados = listaEdificiosPreCargada.get(edificio.getId() + "_" + edificio.getNivel());
        EdificiosPreCargados edificiosPreCargado = edificio.getEdificiosPreCargados();

        List<VBox> vBoxList = new ArrayList<>();


        //BLOQUE
        VBox vBoxBloquePropio = new VBox();
        vBoxBloquePropio.setMaxWidth(200.0);
        vBoxBloquePropio.setAlignment(TOP_CENTER);
        ObservableList<Node> childrenVBox = vBoxBloquePropio.getChildren();

        Label nombreEdificioPropio = new Label(edificiosPreCargado.getNombre());
        nombreEdificioPropio.setTextAlignment(CENTER);
        nombreEdificioPropio.setAlignment(Pos.CENTER);
        nombreEdificioPropio.setWrapText(true);
        childrenVBox.add(nombreEdificioPropio);

        ImageView imageViewPropio = new ImageView(edificio.getImage());
        imageViewPropio.setPickOnBounds(true);
        imageViewPropio.setPreserveRatio(true);
        childrenVBox.add(imageViewPropio);

        Label nivelEdificioPropio = new Label("Nivel: " + edificio.getNivel());
        nivelEdificioPropio.setTextAlignment(CENTER);
        nivelEdificioPropio.setAlignment(Pos.CENTER);
        nivelEdificioPropio.setWrapText(true);
        childrenVBox.add(nivelEdificioPropio);

        Label descripcionEdificioPropio = new Label(edificiosPreCargado.getDescripcion());
        descripcionEdificioPropio.setTextAlignment(CENTER);
        descripcionEdificioPropio.setAlignment(Pos.CENTER);
        descripcionEdificioPropio.setWrapText(true);
        childrenVBox.add(descripcionEdificioPropio);

        if (edificiosPreCargado.getId() != 0) {
            FlowPane flowPane = new FlowPane();
            flowPane.setHgap(10);
            flowPane.setVgap(10);
            ObservableList<Node> childrenFlowPane = flowPane.getChildren();

            for (Map.Entry<Integer, Recursos> recurso : edificiosPreCargado.getRecursosProductores().entrySet()) {
                Recursos recursoValor = recurso.getValue();
                int produce = recursoValor.getCantidad();
                if (produce != 0) {
                    ImageView imageView = new ImageView(recursoValor.getImage());
                    imageView.setFitWidth(25);
                    imageView.setFitHeight(25);
                    Label label = new Label();
                    if (produce > 0) {
                        label.setText("+" + produce);
                        label.setTextFill(Color.GREEN);
                    } else if (produce < 0) {
                        label.setText(String.valueOf(produce));
                        label.setTextFill(Color.RED);
                    }
                    label.setGraphic(imageView);
                    label.setTextAlignment(CENTER);
                    label.setAlignment(Pos.CENTER);
                    label.setWrapText(true);
                    childrenFlowPane.add(label);
                }
            }
            for (Map.Entry<Integer, Recursos> recurso : edificiosPreCargado.getRecursosAlmacen().entrySet()) {
                Recursos recursoValor = recurso.getValue();
                int almacena = recursoValor.getCantidad();
                if (almacena != 0) {
                    ImageView imageView = new ImageView(recursoValor.getImage());
                    imageView.setFitWidth(25);
                    imageView.setFitHeight(25);
                    Label label = new Label();
                    label.setText("+"+almacena);
                    label.setGraphic(imageView);
                    label.setTextAlignment(CENTER);
                    label.setAlignment(Pos.CENTER);
                    label.setWrapText(true);
                    childrenFlowPane.add(label);
                }
            }
            /*
            int oroXMin = ciudad.getOro();
            if (oroXMin > 0) {
                ImageView imageOro = new ImageView(ciudad.getOroImage());
                imageOro.setFitWidth(50);
                imageOro.setFitHeight(50);
                Label oroXMinLabel = new Label(String.valueOf(oroXMin));
                oroXMinLabel.setGraphic(imageOro);
                oroXMinLabel.setTextAlignment(CENTER);
                oroXMinLabel.setAlignment(Pos.CENTER);
                oroXMinLabel.setWrapText(true);
                childrenVBox.add(oroXMinLabel);
            }
            */
            /*
            for (Map.Entry<Integer, Recursos> recursos : ciudad.getRecursosTreeMap().entrySet()) {
                int maderaXMin = ciudad.getMadera();
                if (maderaXMin > 0) {
                    ImageView image = new ImageView(ciudad.getMaderaImage());
                    image.setFitWidth(50);
                    image.setFitHeight(50);
                    Label oroXMinLabel = new Label(String.valueOf(maderaXMin));
                    oroXMinLabel.setGraphic(image);
                    oroXMinLabel.setTextAlignment(CENTER);
                    oroXMinLabel.setAlignment(Pos.CENTER);
                    oroXMinLabel.setWrapText(true);
                    childrenVBox.add(oroXMinLabel);
                }
            }
*/
            childrenVBox.add(flowPane);
        }
        vBoxList.add(vBoxBloquePropio);
        //FIN BLOQUE
/*
        //BLOQUE
        Label nombreEdificio = new Label(edificiosPreCargado.getNombre());
        nombreEdificio.setTextAlignment(CENTER);
        nombreEdificio.setAlignment(Pos.CENTER);
        nombreEdificio.setWrapText(true);

        ImageView imageView = new ImageView(edificio.getImage());
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        Label descripcionEdificio = new Label(edificiosPreCargado.getDescripcion());
        descripcionEdificio.setTextAlignment(CENTER);
        descripcionEdificio.setAlignment(Pos.CENTER);
        descripcionEdificio.setWrapText(true);

        VBox vBoxBloque = new VBox(nombreEdificio, imageView, descripcionEdificio);
        vBoxBloque.setMaxWidth(200.0);
        vBoxBloque.setAlignment(TOP_CENTER);
        vBoxList.add(vBoxBloque);
        //FIN BLOQUE
*/

        VBox vBox = new VBox();
        for (VBox box : vBoxList) {
            vBox.getChildren().add(box);
            Separator separator = new Separator();
            separator.setPrefWidth(200);
            vBox.getChildren().add(separator);
        }

        vBox.setSpacing(10);
        vBox.setMaxWidth(200.0);
        vBox.setAlignment(TOP_CENTER);

        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.maxWidth(-Infinity);
        scrollPane.prefWidth(200.0);
        //scrollPane.()BorderPane.alignment="CENTER"

        borderPane.setLeft(scrollPane);
        /*
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

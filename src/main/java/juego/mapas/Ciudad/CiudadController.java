package main.java.juego.mapas.Ciudad;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import main.java.Jugadores.Jugador;
import main.java.Utils.PrimaryStageControler;
import main.java.juego.mapas.Mundo.MundoController;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.Ciudad.ContentCity.Edificio;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

import static javafx.geometry.Pos.TOP_CENTER;
import static javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER;
import static javafx.scene.text.TextAlignment.CENTER;
import static jdk.nashorn.internal.objects.Global.Infinity;
import static main.java.Jugadores.Jugador.*;


public class CiudadController extends PrimaryStageControler implements Initializable {
    private static String RUTE = "../../../resources/mapas/city/";
    public static final String THIS_RUTE = "juego/mapas/Ciudad/ciudad.fxml";
    static Jugador jugador;
    static boolean controladorDeClic = true;
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
    public FlowPane recuros;
    @FXML
    SplitMenuButton selectorCiudad;
   // @FXML
    //Label oro, madera, piedra, hierro, comida, poblacion, felicidad, investigacion;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jugador = getJugador();
        ciudad = getCiudad();
        nameThisCity = ciudad.getNameCity();

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

        /*
                       <HBox alignment="CENTER" prefHeight="20.0" prefWidth="70.0" spacing="5.0">
                     <children>
                              <Label fx:id="oro" text="load" />
                                <ImageView fitHeight="30.0" fitWidth="30.0" pickOnBounds="true" preserveRatio="true">
                                    <image>
                                        <Image url="@../../../../resources/images/icons/recursos/0.png" />
                                    </image>
                                </ImageView>
                     </children>
                  </HBox>
         */
/*
        int oroDisponible = ciudad.getOro();
        oro.setText(String.valueOf(oroDisponible));
        int maderaDisponible = ciudad.getMadera();
        madera.setText(String.valueOf(maderaDisponible));
        int piedraDisponible = ciudad.getPiedra();
        piedra.setText(String.valueOf(piedraDisponible));
        int hierroDisponible = ciudad.getHierro();
        hierro.setText(String.valueOf(hierroDisponible));
        int comidaDisponible = ciudad.getComida();
        comida.setText(String.valueOf(comidaDisponible));
        int poblacionDisponible = ciudad.getPoblacion();
        poblacion.setText(String.valueOf(poblacionDisponible));
        int felicidadDisponible = ciudad.getFelicidad();
        felicidad.setText(String.valueOf(felicidadDisponible));
        int investigacionDisponible = jugador.getInvestigacion();
        investigacion.setText(String.valueOf(investigacionDisponible));
*/

        gridPaneMap.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {//Cerrar el menu
//            System.out.println("Limpiar menu izquierda");
//            borderPane.setLeft(null);
            queClicas(null, null);
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

        Collection<Edificio> posicionEdificios = getCiudad().getListaPosicionesEdificios().values();
        for (Edificio edificio : posicionEdificios) {
            Image image = edificio.getImage();
            ImageView imageView = new ImageView(image);
            imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                imageView.setCursor(Cursor.HAND);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                imageView.setImage(edificio.getImageClicable());
            });
            imageView.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                imageView.setImage(edificio.getImage());
            });
            imageView.setOnMouseClicked(e -> {
                queClicas(edificio, imageView);//System.out.println("Imagen Edificio clicada: " + listaEdificios.get(idEdificio + "_" + nivelEdificio));
            });
            gridPaneMap.add(imageView, edificio.getColumnas(), edificio.getFilas());
        }
    }


    @FXML
    public void botton2(ActionEvent actionEvent) {
        cambiarNombreStage("El nombre que queramos");
        System.out.println("COSASA");
    }

    private void queClicas(Edificio posicionEdificio, ImageView imageView) {
        if (controladorDeClic) {//BLOQUEA UN MOMENTO EL SISTEMA DE CLIC PARA QUE CARGE EL MENU Y NO LO BORRE
            if (posicionEdificio == null) {
                borderPane.setLeft(null);//System.out.println("Limpiar menu izquierda");
            } else {
                controladorDeClic = false;
                (new Thread() {
                    public void run() {
                        try {
                            Thread.sleep(200);
                            controladorDeClic = true;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                createMenuLeft(borderPane, posicionEdificio, imageView);
            }
        }
    }

    private void createMenuLeft(BorderPane borderPane, Edificio edificio, ImageView imageView) {
        //EdificiosPreCargados edificiosPreCargados = listaEdificiosPreCargada.get(edificio.getId() + "_" + edificio.getNivel());
//        EdificiosPreCargados edificiosPreCargado = edificio.getEdificiosPreCargados();
        List<VBox> vBoxList = new ArrayList<>();

        int edificioId = edificio.getId();
        int edificioNivel = edificio.getNivel();
        if (edificioId != 0) {// NO PARCELA
            vBoxList.add(cajaEdificio(edificio, imageView, false, edificio.getEdificiosPreCargado(), 0));
            if (edificioNivel > 0) {
                try {
                    vBoxList.add(cajaEdificio(edificio, imageView, false, listaEdificiosPreCargados.get(edificioId + "_" + (edificioNivel + 1)), 2));
                } catch (Exception e) {
                }
                vBoxList.add(cajaEdificio(edificio, imageView, false, listaEdificiosPreCargados.get(edificioId + "_" + (edificioNivel - 1)), 3));
            } else {
                try {
                    vBoxList.add(cajaEdificio(edificio, imageView, false, listaEdificiosPreCargados.get(edificioId + "_" + (edificioNivel + 1)), 2));
                } catch (Exception e) {
                }
                if (edificio.getEdificiosPreCargado().isDestruible()) {
                    vBoxList.add(cajaEdificio(edificio, imageView, false, listaEdificiosPreCargados.get(0 + "_" + 0), 4));
                }
            }


        } else {//PARCELAS
            Collection<Edificio> posicionEdificios = getCiudad().getListaPosicionesEdificios().values();
            TreeMap<Integer, Integer> counterTiposDeEdificioEnLaCiudad = new TreeMap<>();
            for (Edificio edificio1 : posicionEdificios) {
                int id = edificio1.getId();
                if (counterTiposDeEdificioEnLaCiudad.containsKey(id)) {
                    counterTiposDeEdificioEnLaCiudad.put(id, counterTiposDeEdificioEnLaCiudad.get(id) + 1);
                } else {
                    counterTiposDeEdificioEnLaCiudad.put(id, 1);
                }
            }
            vBoxList.add(cajaEdificio(edificio, imageView, true, edificio.getEdificiosPreCargado(), 0));
            for (Map.Entry<String, EdificiosPreCargados> preCargadosEntry : listaEdificiosPreCargados.entrySet()) {
                EdificiosPreCargados temp = preCargadosEntry.getValue();
                if (temp.getNivel() == 0 && temp.isConstruible() && ciudad.getNivelCiudad() >= temp.getNivelCastilloNecesario()) {
//                    System.out.println(temp.getNombre() + " " + temp.getNivel() + " " + temp.isConstruible() + " " + ciudad.getNivelCiudad() + " " + temp.getNivelCastilloNecesario());
//                    System.out.println(temp.getNombre() + " " + counterTiposDeEdificioEnLaCiudad.get(temp.getId()) + " " + counterTiposDeEdificioEnLaCiudad.get(temp.getId()) + ">" + temp.getMaximoEdificiosDelMismoTipo());
                    if (counterTiposDeEdificioEnLaCiudad.get(temp.getId()) == null || counterTiposDeEdificioEnLaCiudad.get(temp.getId()) < temp.getMaximoEdificiosDelMismoTipo()) {
                        vBoxList.add(cajaEdificio(edificio, imageView, false, temp, 1));
                    }
                }
            }
        }


        VBox vBox = new VBox();
        for (VBox box : vBoxList) {
            vBox.getChildren().add(box);
            Separator separator = new Separator();
            separator.setVisible(false);
            vBox.getChildren().add(separator);
        }
        vBox.setSpacing(5);
        vBox.setAlignment(TOP_CENTER);

        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.maxWidth(-Infinity);
        scrollPane.prefWidth(200);
        scrollPane.setHbarPolicy(NEVER);
        //scrollPane.()BorderPane.alignment="CENTER"

        borderPane.setLeft(scrollPane);
    }

    private VBox cajaEdificio(Edificio edificioQueEstaEnElMapa, ImageView imageView, boolean parcela, EdificiosPreCargados edificioQueSaleEnMenu, int tipoDeBoton) {

        //BLOQUE
        VBox vBoxBloquePropio = new VBox();
        vBoxBloquePropio.setMinWidth(200);
        vBoxBloquePropio.setMaxWidth(200);
        vBoxBloquePropio.setAlignment(TOP_CENTER);
        vBoxBloquePropio.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        ObservableList<Node> childrenVBox = vBoxBloquePropio.getChildren();

        Label nombreEdificioPropio = new Label(edificioQueSaleEnMenu.getNombre());
        nombreEdificioPropio.setTextAlignment(CENTER);
        nombreEdificioPropio.setAlignment(Pos.CENTER);
        nombreEdificioPropio.setWrapText(true);
        childrenVBox.add(nombreEdificioPropio);

        ImageView imageViewPropio = new ImageView(edificioQueSaleEnMenu.getImage());
        imageViewPropio.setPickOnBounds(true);
        imageViewPropio.setPreserveRatio(true);
        childrenVBox.add(imageViewPropio);

        Label nivelEdificioPropio = new Label("Nivel: " + edificioQueSaleEnMenu.getNivel());
        nivelEdificioPropio.setTextAlignment(CENTER);
        nivelEdificioPropio.setAlignment(Pos.CENTER);
        nivelEdificioPropio.setWrapText(true);
        childrenVBox.add(nivelEdificioPropio);

        Label descripcionEdificioPropio = new Label(edificioQueSaleEnMenu.getDescripcion());
        descripcionEdificioPropio.setTextAlignment(CENTER);
        descripcionEdificioPropio.setAlignment(Pos.CENTER);
        descripcionEdificioPropio.setWrapText(true);
        childrenVBox.add(descripcionEdificioPropio);
        vBoxBloquePropio.setMargin(descripcionEdificioPropio, new Insets(0, 15, 0, 15));
        if (!parcela) {
            FlowPane flowPane = new FlowPane();
            flowPane.setHgap(10);
            flowPane.setVgap(10);
            flowPane.setAlignment(Pos.CENTER);
            ObservableList<Node> childrenFlowPane = flowPane.getChildren();


            printRecursos(childrenFlowPane, edificioQueSaleEnMenu.getRecursosProductores().entrySet(), 1);
            printRecursos(childrenFlowPane, edificioQueSaleEnMenu.getRecursosAlmacen().entrySet(), 2);
            if (tipoDeBoton == 1 || tipoDeBoton == 2) {
                printRecursos(childrenFlowPane, edificioQueSaleEnMenu.getRecursosCostes().entrySet(), 3);
            }

            vBoxBloquePropio.setMargin(flowPane, new Insets(0, 15, 0, 15));
            childrenVBox.add(flowPane);
            if (tipoDeBoton != 0) {
                String text = null;
                switch (tipoDeBoton) {
                    case 1:
                        text = "Construir";
                        break;
                    case 2:
                        text = "Update";
                        break;
                    case 3:
                        text = "Downgrade";
                        break;
                    case 4:
                        text = "Destruir";
                        break;
                }

                Button button = new Button(text);
                if (tipoDeBoton == 1 || tipoDeBoton == 2) {//CALCULAR RECURSOS
                    for (Recursos edificioCostes : edificioQueSaleEnMenu.getRecursosCostes().values()) {
                        int id = edificioCostes.getId();
                        if ((!(id == 5)) && edificioCostes.getCantidad() > ciudad.getRecursosTreeMap().get(edificioCostes.getId()).getCantidad()) {
                            button.setDisable(true);
                            break;
                        }
                    }

                }//FIN CALCULAR RECURSOS
                button.setCursor(Cursor.HAND);
                button.setOnMouseClicked(e -> {
                    edificioQueEstaEnElMapa.setEdificiosPreCargado(edificioQueSaleEnMenu);
                    imageView.setImage(edificioQueEstaEnElMapa.getImage());
                    borderPane.setLeft(null);
//                    try {
//                        new PrimaryStageControler().reload(getStage(), THIS_RUTE, false);
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
                });

                childrenVBox.add(button);
                Separator separator = new Separator();
                separator.setPrefWidth(200);
                childrenVBox.add(separator);

            }

        }
        //FIN BLOQUE
        return vBoxBloquePropio;
    }

    private void printRecursos(ObservableList<Node> childrenFlowPane, Set<Map.Entry<Integer, Recursos>> a, int produceAlmacenaCuesta) {
        boolean paso1 = false;
        for (Map.Entry<Integer, Recursos> recurso : a) {
            Recursos recursoValor = recurso.getValue();
            int numero = recursoValor.getCantidad();
            if (numero != 0) {
                ImageView imageView = new ImageView(recursoValor.getImage());
                imageView.setFitWidth(25);
                imageView.setFitHeight(25);
                Label label = new Label();
                switch (produceAlmacenaCuesta) {
                    case 1:
                        if (numero > 0) {
                            label.setText("+" + numero + "/h");
                            label.setTextFill(Color.GREEN);
                        } else if (numero < 0) {
                            label.setText(numero + "/h");
                            label.setTextFill(Color.RED);
                        }
                        break;
                    case 2:
                        label.setText(String.valueOf(numero));
                        break;
                    case 3:
                        label.setText("-" + numero);
                        label.setTextFill(Color.RED);
                        break;
                }
                label.setGraphic(imageView);
                label.setTextAlignment(CENTER);
                label.setAlignment(Pos.CENTER);
                label.setWrapText(true);
                childrenFlowPane.add(label);
                paso1 = true;
            }
        }
        if (paso1) {
            Separator separator = new Separator();
            separator.setPrefWidth(200);
            childrenFlowPane.add(separator);
        }
    }


    public void toMundo(MouseEvent mouseEvent) {
        try {
            new PrimaryStageControler().reload(getStage(), MundoController.THIS_RUTE, false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

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
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.Ciudad.ContentCity.Edificio;
import main.java.juego.mapas.Ciudad.ContentCity.PosicionEdificio;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static javafx.geometry.Pos.TOP_CENTER;
import static javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER;
import static javafx.scene.text.TextAlignment.CENTER;
import static jdk.nashorn.internal.objects.Global.Infinity;
import static main.java.Jugadores.Jugador.*;


public class CiudadController extends PrimaryStageControler implements Initializable {
    private static String RUTE = "../../../resources/mapas/city/";
    static Jugador jugador;
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
    Label oro, madera, piedra, hierro, comida, poblacion, felicidad, investigacion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jugador = getJugador();
        ciudad = getCiudad();
        nameThisCity = ciudad.getNameCity();

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


        gridPaneMap.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {//Cerrar el menu
//            System.out.println("Limpiar menu izquierda");
//            borderPane.setLeft(null);
            queClicas(null);
        });


        selectorCiudad.setText(nameThisCity);//Seleccionar otra ciudad
        for (Ciudad ciudadTemp : jugador.listaCiudadesPropias.values()) {
            String nameCity = ciudadTemp.getNameCity();
            if (nameThisCity != nameCity) {
                MenuItem menuItem = new MenuItem();
                menuItem.setText(nameCity);
                menuItem.setOnAction((e) -> {
                    System.out.println("clicado " + nameCity);//TODO CAMBIAR ESTO POR LA NUEVA CIUDAD
                    setCiudad(ciudadTemp);
                    try {
                        new PrimaryStageControler().reload(getStage(),"juego/mapas/Ciudad/ciudad.fxml",false);
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
            gridPaneMap.add(imageView, posicionEdificio.getColumnas(), posicionEdificio.getFilas());
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
        //EdificiosPreCargados edificiosPreCargados = listaEdificiosPreCargada.get(edificio.getId() + "_" + edificio.getNivel());
//        EdificiosPreCargados edificiosPreCargado = edificio.getEdificiosPreCargados();

        List<VBox> vBoxList = new ArrayList<>();

        Edificio edificio = posicionEdificio.getEdificio();
        int id = edificio.getId();
        boolean listarTodosLosEdificios = false;
        int maximos = 0;
        int counterMaximos = 9;
        boolean whilex = true;
        if (id == 0) {
            listarTodosLosEdificios = true;
            maximos = listaEdificiosKeys.get(listaEdificiosKeys.size() - 1);
        }
        int nivel = edificio.getNivel();
        int sumator = 0;

        boolean nocargo = false;
        do {
            String nameBuild = id + "_" + (nivel + sumator);
            EdificiosPreCargados edificiosPreCargado = EdificiosPreCargada.get(nameBuild);
            if (edificiosPreCargado != null) {
                //BLOQUE
                VBox vBoxBloquePropio = new VBox();
                vBoxBloquePropio.setMinWidth(200);
                vBoxBloquePropio.setMaxWidth(200);
                vBoxBloquePropio.setAlignment(TOP_CENTER);
                vBoxBloquePropio.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                ObservableList<Node> childrenVBox = vBoxBloquePropio.getChildren();

                Label nombreEdificioPropio = new Label(edificiosPreCargado.getNombre());
                nombreEdificioPropio.setTextAlignment(CENTER);
                nombreEdificioPropio.setAlignment(Pos.CENTER);
                nombreEdificioPropio.setWrapText(true);
                childrenVBox.add(nombreEdificioPropio);

                ImageView imageViewPropio = new ImageView(edificiosPreCargado.getImage());
                imageViewPropio.setPickOnBounds(true);
                imageViewPropio.setPreserveRatio(true);
                childrenVBox.add(imageViewPropio);

                Label nivelEdificioPropio = new Label("Nivel: " + nivel);
                nivelEdificioPropio.setTextAlignment(CENTER);
                nivelEdificioPropio.setAlignment(Pos.CENTER);
                nivelEdificioPropio.setWrapText(true);
                childrenVBox.add(nivelEdificioPropio);

                Label descripcionEdificioPropio = new Label(edificiosPreCargado.getDescripcion());
                descripcionEdificioPropio.setTextAlignment(CENTER);
                descripcionEdificioPropio.setAlignment(Pos.CENTER);
                descripcionEdificioPropio.setWrapText(true);
                childrenVBox.add(descripcionEdificioPropio);
                vBoxBloquePropio.setMargin(descripcionEdificioPropio,new Insets(0,15,0,15));
                if (edificiosPreCargado.getId() != 0) {
                    FlowPane flowPane = new FlowPane();
                    flowPane.setHgap(10);
                    flowPane.setVgap(10);
                    flowPane.setAlignment(Pos.CENTER);
                    ObservableList<Node> childrenFlowPane = flowPane.getChildren();

                    boolean paso1 = false;
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
                            paso1 = true;
                        }
                    }
                    if (paso1) {
                        Separator separator = new Separator();
                        separator.setPrefWidth(200);
                        childrenFlowPane.add(separator);
                    }
                    boolean paso2 = false;
                    for (Map.Entry<Integer, Recursos> recurso : edificiosPreCargado.getRecursosAlmacen().entrySet()) {
                        Recursos recursoValor = recurso.getValue();
                        int almacena = recursoValor.getCantidad();
                        if (almacena != 0) {
                            ImageView imageView = new ImageView(recursoValor.getImage());
                            imageView.setFitWidth(25);
                            imageView.setFitHeight(25);
                            Label label = new Label();
                            label.setText(String.valueOf(almacena));
                            label.setGraphic(imageView);
                            label.setTextAlignment(CENTER);
                            label.setAlignment(Pos.CENTER);
                            label.setWrapText(true);
                            childrenFlowPane.add(label);
                            paso2 = true;
                        }
                    }
                    if (paso2) {
                        Separator separator = new Separator();
                        separator.setPrefWidth(200);
                        childrenFlowPane.add(separator);
                    }
                    vBoxBloquePropio.setMargin(flowPane,new Insets(0,15,0,15));
                    childrenVBox.add(flowPane);
                    boolean active = false;
                    if (sumator == 1) {
                        Button button = new Button("Update");
                        childrenVBox.add(button);
                        active = true;
                    } else if (sumator == -1) {
                        Button button = new Button("Downgrade");
                        childrenVBox.add(button);
                        active = true;
                    }
                    if (active) {
                        Separator separator = new Separator();
                        separator.setPrefWidth(200);
                        childrenVBox.add(separator);
                    }
                    vBoxList.add(vBoxBloquePropio);
                } else {

                    vBoxList.add(vBoxBloquePropio);
                }

            } else {
                nocargo = true;
            }
            if (!listarTodosLosEdificios) {
                if (sumator == 0) {
                    sumator = 1;
                } else if (sumator == 1 && nivel > 0 || nocargo && sumator != -1) {
                    sumator = -1;
                } else if (nocargo && sumator == -1 && id > 2) {
                    id = 0;
                    sumator = 0;
                    nivel = 0;
                } else {
                    break;
                }
            } else {
                if (listaEdificiosKeys.contains(++counterMaximos)) {
                    id = counterMaximos;
                } else {
                    whilex = false;
                }
            }
        } while (whilex);
        //FIN BLOQUE


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

    public void toMundo(MouseEvent mouseEvent) {
        try {
            new PrimaryStageControler().reload(getStage(),"juego/mapas/Mundo/mundo.fxml",false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

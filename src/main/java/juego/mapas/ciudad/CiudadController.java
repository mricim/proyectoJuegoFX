package main.java.juego.mapas.ciudad;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import main.java.juego.MapasController;
import main.java.juego.mapas.RecursosPrecargados;
import main.java.juego.mapas.mundo.MundoController;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.ciudad.contenidoCiudad.Edificio;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static javafx.geometry.Pos.TOP_CENTER;
import static javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER;
import static javafx.scene.text.TextAlignment.CENTER;
import static jdk.nashorn.internal.objects.Global.Infinity;
import static main.java.jugadores.Jugador.*;


public class CiudadController extends MapasController implements Initializable {
    private static String RUTE = "../../../resources/mapas/city/";
    public static final String THIS_RUTE = "juego/mapas/ciudad/ciudad.fxml";
    static boolean controladorDeClic = true;
/*
    static Jugador jugador;
    static Ciudad ciudad;
    String nameThisCity;
*/

    @FXML
    BorderPane borderPane;
    @FXML
    GridPane gridPaneMap;
    /*
    @FXML
    ImageView imagenDeFondo;
     */
    public FlowPane flowPaneRecuros;
    @FXML
    SplitMenuButton selectorCiudad;
    // @FXML
    //Label oro, madera, piedra, hierro, comida, poblacion, felicidad, investigacion;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicialiceController();
        recursosMenu(flowPaneRecuros, getCiudadPrimaryStageController().getRecursosTreeMap().values());
        selectorDeCiudad(THIS_RUTE, selectorCiudad);
        gridPaneMap.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {//Cerrar el menu
            queClicas(null, null);
        });
        //<-- Controlado por MapasController


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

        Collection<Edificio> posicionEdificios = getCiudadPrimaryStageController().getListaPosicionesEdificios().values();
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
            gridPaneMap.add(imageView, edificio.getColumna(), edificio.getFila());
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
                createMenuLeft(borderPane, imageView, posicionEdificio);
            }
        }
    }

    private void createMenuLeft(BorderPane borderPane, ImageView imageView, Edificio edificio) {
        //EdificiosPreCargados edificiosPreCargados = listaEdificiosPreCargada.get(edificio.getId() + "_" + edificio.getNivel());
//        EdificiosPreCargados edificiosPreCargado = edificio.getEdificiosPreCargados();
        List<VBox> vBoxList = new ArrayList<>();

        int edificioId = edificio.getId();
        int edificioNivel = edificio.getNivel();
        if (edificioId != 0) {// NO PARCELA
            vBoxList.add(cajaEdificio(edificio, imageView, false, edificio.getEdificiosPreCargado(), 0));//El que tenemos puesto
            if (edificioNivel > 0) {
                try {
                    vBoxList.add(cajaEdificio(edificio, imageView, false, listaEdificiosPreCargados.get(edificioId + "-" + (edificioNivel + 1)), 2));
                } catch (Exception e) {
                }
                vBoxList.add(cajaEdificio(edificio, imageView, false, listaEdificiosPreCargados.get(edificioId + "-" + (edificioNivel - 1)), 3));
            } else {
                try {
                    vBoxList.add(cajaEdificio(edificio, imageView, false, listaEdificiosPreCargados.get(edificioId + "-" + (edificioNivel + 1)), 2));
                } catch (Exception e) {
                }
                if (edificio.getEdificiosPreCargado().isDestruible()) {
                    vBoxList.add(cajaEdificio(edificio, imageView, false, listaEdificiosPreCargados.get(0 + "-" + 0), 4));
                }
            }


        } else {//PARCELAS
            Collection<Edificio> posicionEdificios = getCiudadPrimaryStageController().getListaPosicionesEdificios().values();
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
                if (temp.getNivel() == 0 && temp.isConstruible() && getCiudadPrimaryStageController().getNivelCiudad() >= temp.getNivelCastilloNecesario()) {
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
        vBoxBloquePropio.setSpacing(10);
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
        VBox.setMargin(descripcionEdificioPropio, new Insets(0, 15, 0, 15));
        if (!parcela) {
            FlowPane flowPane = new FlowPane();
            flowPane.setHgap(10);
            flowPane.setVgap(10);
            flowPane.setAlignment(Pos.CENTER);
            ObservableList<Node> childrenFlowPane = flowPane.getChildren();

            if (tipoDeBoton == 0) {//Si es el edificio que tenemos en el mapa
                printRecursos(childrenFlowPane, edificioQueSaleEnMenu.getRecursosProductores().entrySet(), 1, edificioQueEstaEnElMapa);//Ponemos las barras de los selectores
            } else {
                printRecursos(childrenFlowPane, edificioQueSaleEnMenu.getRecursosProductores().entrySet(), 1, null);
            }
            printRecursos(childrenFlowPane, edificioQueSaleEnMenu.getRecursosAlmacen().entrySet(), 2, null);
            if (tipoDeBoton == 1 || tipoDeBoton == 2) {
                printRecursos(childrenFlowPane, edificioQueSaleEnMenu.getRecursosCostes().entrySet(), 3, null);
            }


            VBox.setMargin(flowPane, new Insets(0, 15, 0, 15));
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
                        if ((!(id == 5)) && edificioCostes.getCantidad() > getCiudadPrimaryStageController().getRecursosTreeMap().get(edificioCostes.getId()).getCantidad()) {
                            button.setDisable(true);
                            break;
                        }
                    }

                }//FIN CALCULAR RECURSOS
                button.setCursor(Cursor.HAND);
                button.setOnMouseClicked(e -> {
                    if (tipoDeBoton == 1 || tipoDeBoton == 2) {
                        for (Recursos value : edificioQueSaleEnMenu.getRecursosCostes().values()) {
                            Recursos recursos = getCiudadPrimaryStageController().getRecursosTreeMap().get(value.getId());
                            int cantidad = recursos.getCantidad();
                            recursos.setCantidad(cantidad - value.getCantidad());
                        }
                    } else {
                        EdificiosPreCargados edificiosPreCargadoEnElMapa = edificioQueEstaEnElMapa.getEdificiosPreCargado();
                        for (Recursos value : edificiosPreCargadoEnElMapa.getRecursosCostes().values()) {
                            Recursos recursosCity = getCiudadPrimaryStageController().getRecursosTreeMap().get(value.getId());
                            int cantidad = recursosCity.getCantidad();
                            recursosCity.setCantidad(cantidad + ((value.getCantidad() * 80) / 100));
                        }
                    }
                    reloadMenuRecursos();
                    borderPane.setLeft(null);
                    edificioQueEstaEnElMapa.setEdificiosPreCargado(edificioQueSaleEnMenu);
                    imageView.setImage(edificioQueEstaEnElMapa.getImage());
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

    private void reloadMenuRecursos() {
        recursosMenu(flowPaneRecuros, getCiudadPrimaryStageController().getRecursosTreeMap().values());
    }

    private void printRecursos(ObservableList<Node> childrenFlowPane, Set<Map.Entry<Integer, Recursos>> recursosEdificio, int produceAlmacenaCuesta, Edificio edificioSlider) {
        boolean paso0 = false;
        for (Map.Entry<Integer, Recursos> recurso : recursosEdificio) {
            Recursos recursoValor = recurso.getValue();
            if (recursoValor.getCantidad() > 0) {
                paso0 = true;
            }
        }
        if (paso0) {
            if (produceAlmacenaCuesta == 1) {
                Separator separator = new Separator();
                separator.setPrefWidth(200);
                childrenFlowPane.add(separator);
            }
            Label label = new Label();
            label.setBackground(new Background(new BackgroundFill(Color.rgb(238, 174, 160), CornerRadii.EMPTY, Insets.EMPTY)));
            switch (produceAlmacenaCuesta) {
                case 1:
                    label.setText("Produce (max):");
                    break;
                case 2:
                    label.setText("Almacena:");
                    break;
                case 3:
                    label.setText("Cuesta:");
                    break;
                default:
                    label.setText("Error");
                    break;
            }
            childrenFlowPane.add(label);
            Separator separator2 = new Separator();
            separator2.setPrefWidth(200);
            separator2.setVisible(false);
            childrenFlowPane.add(separator2);
        }
        boolean paso1 = false;
        for (Map.Entry<Integer, Recursos> recursoEdificio : recursosEdificio) {

            Recursos recursoValor = recursoEdificio.getValue();
            int numero = recursoValor.getCantidad();
            if (numero != 0) {
                ImageView imageView = new ImageView(recursoValor.getImage());
                imageView.setFitWidth(25);
                imageView.setFitHeight(25);
                RecursosPrecargados recursosPrecargados = recursoValor.getRecursosPrecargados();
                if (edificioSlider != null && recursosPrecargados.isEsSelectable()) {//SLIDER
                    Recursos recursoAQuitar = getCiudadPrimaryStageController().getRecursosTreeMap().get(recursosPrecargados.getElSeletableSera());
                    int pobacionCiudad = recursoAQuitar.getCantidad();
                    int poblacionMaximaQueSePuedePoner = recursoValor.getCantidad();
                    int poblacionPuesta = edificioSlider.getTrabajadoresPuestos();
                    HBox hBox = new HBox();
                    Label label = new Label(String.valueOf(poblacionPuesta));
                    Label labelMax = new Label(" /" + poblacionMaximaQueSePuedePoner);
                    Slider slider = new Slider(0, Math.min(poblacionMaximaQueSePuedePoner, pobacionCiudad + poblacionPuesta), poblacionPuesta);
                    slider.setShowTickMarks(true);
                    slider.setMajorTickUnit(4);
                    slider.setSnapToTicks(true);
                    slider.valueProperty().addListener(new ChangeListener<Number>() {
                        @Override
                        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                            int a = newValue.intValue();
                            int number = oldValue.intValue() - a;
                            if (number != 0) {
                                label.textProperty().setValue(String.valueOf(a));
                                edificioSlider.setTrabajadoresPuestos(a);
                                recursoAQuitar.setCantidad(recursoAQuitar.getCantidad() + number);
                                reloadMenuRecursos();
                            }
                        }
                    });
                    hBox.getChildren().addAll(imageView, slider, label, labelMax);
                    childrenFlowPane.add(hBox);
                } else {
                    Label label = new Label();
                    switch (produceAlmacenaCuesta) {
                        case 1:
                            if (numero > 0) {
                                label.setText("+" + numero + "/h");
                                label.setTextFill(Color.GREEN);
                            } else {//if (numero != 0)
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
                }
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
            reload(getStagePrimaryStageController(), MundoController.THIS_RUTE,false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

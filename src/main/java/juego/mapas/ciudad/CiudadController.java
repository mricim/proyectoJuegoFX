package main.java.juego.mapas.ciudad;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
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
import main.java.Utils.CallImages;
import main.java.juego.MapasController;
import main.java.juego.mapas.RecursosPrecargados;
import main.java.juego.mapas.mundo.MundoController;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.ciudad.contenidoCiudad.Edificio;
import main.java.juego.mapas.pelea.AsedioPreCargadas;
import main.java.juego.mapas.pelea.SoldadosPreCargados;
import main.java.juego.mapas.pelea.UnidadesPreCargadas;

import java.net.URL;
import java.util.*;

import static javafx.geometry.Pos.TOP_CENTER;
import static javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER;
import static javafx.scene.text.TextAlignment.CENTER;
import static jdk.nashorn.internal.objects.Global.Infinity;
import static main.java.jugadores.Jugador.*;


public class CiudadController extends MapasController implements Initializable {
    public static final String THIS_RUTE_IMAGES = "mapas/city/";
    static boolean controladorDeClic = true;
    public ImageView imagenDeFondo;
    public StackPane dondeVaLaImagen;
/*
    static Jugador jugador;
    static Ciudad ciudad;
    String nameThisCity;
*/

    @FXML
    BorderPane borderPane;
    @FXML
    GridPane gridPaneMap;
    public FlowPane flowPaneRecuros;
    @FXML
    SplitMenuButton selectorCiudad;
    // @FXML
    //Label oro, madera, piedra, hierro, comida, poblacion, felicidad, investigacion;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicialiceController();
        recursosMenu(flowPaneRecuros);
        selectorDeCiudad(selectorCiudad, true);
        gridPaneMap.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {//Cerrar el menu
            queClicas(null, null);
        });
        //<-- Controlado por MapasController
        ObservableList<Node> x = dondeVaLaImagen.getChildren();
        List<Node> asd = new ArrayList<>();
        ImageView imageViewBase = new ImageView();
        imageViewBase.setImage(CallImages.getImage(THIS_RUTE_IMAGES, "fondoCiudad", 2244, 1692));
        asd.add(imageViewBase);
        asd.addAll(x);
        ObservableList<Node> f = dondeVaLaImagen.getChildren();
        f.clear();
        f.addAll(asd);
        //<--CARGAR IMAGEN DE FONDO

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
        if (edificioId != 0) {// EL EDIFICIO NO ES UNA PARCELA ASI QUE PUEDE TENER NIVELES
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
                    if (counterTiposDeEdificioEnLaCiudad.get(temp.getId()) == null || counterTiposDeEdificioEnLaCiudad.get(temp.getId()) < temp.getMaximoEdificiosDelMismoTipo()) {
                        vBoxList.add(cajaEdificio(edificio, imageView, false, temp, 1));
                    }
                }
            }
        }


        rellenador(borderPane, vBoxList);
    }

    private static void rellenador(BorderPane borderPane, List<VBox> vBoxList) {
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
        vBoxBloquePropio.setMinWidth(250);
        vBoxBloquePropio.setMaxWidth(250);
        vBoxBloquePropio.setAlignment(TOP_CENTER);
        vBoxBloquePropio.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        vBoxBloquePropio.setSpacing(10);
        ObservableList<Node> childrenVBox = vBoxBloquePropio.getChildren();

        Separator separator2 = new Separator();
        separator2.setPrefWidth(200);
        separator2.setVisible(false);
        childrenVBox.add(separator2);

        Label nombreEdificioPropio = new Label(edificioQueSaleEnMenu.getNombre());
        nombreEdificioPropio.setTextAlignment(CENTER);
        nombreEdificioPropio.setAlignment(Pos.CENTER);
        nombreEdificioPropio.setWrapText(true);
        childrenVBox.add(nombreEdificioPropio);

        ImageView imageViewPropio = new ImageView(edificioQueSaleEnMenu.getImage());
        imageViewPropio.setPickOnBounds(true);
        imageViewPropio.setPreserveRatio(true);
        childrenVBox.add(imageViewPropio);
        if (edificioQueSaleEnMenu.getId() == 0) {
            Label nivelEdificioPropio = new Label("Nivel: " + edificioQueSaleEnMenu.getNivel());
            nivelEdificioPropio.setTextAlignment(CENTER);
            nivelEdificioPropio.setAlignment(Pos.CENTER);
            nivelEdificioPropio.setWrapText(true);
            childrenVBox.add(nivelEdificioPropio);
        }
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
                int tipo = edificioQueEstaEnElMapa.getEdificiosPreCargado().getTipo();
                if (tipo != 0) {// System.out.println("NO GENERADOR: " + edificioQueEstaEnElMapa.getId());
                    printBoton(childrenFlowPane, tipo);// NO GENERA RECURSOS
                } else {//System.out.println("GENERADOR: " + edificioQueEstaEnElMapa.getId());
                    printRecursos(childrenFlowPane, edificioQueSaleEnMenu.getRecursosProductores().entrySet(), 1, edificioQueEstaEnElMapa);//Ponemos las barras de los selectores
                }
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
                    recursosMenu(flowPaneRecuros);
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

    private void printBoton(ObservableList<Node> childrenFlowPane, int tipoEdificio) {
        Button button = new Button();
        switch (tipoEdificio) {
            case 1:
                button.setText("New Ciudad");
                int total = getJugadorPrimaryStageController().listaCiudadesPropias.size();
                int counter = 1;
                for (Ciudad ciudad : getJugadorPrimaryStageController().listaCiudadesPropias.values()) {
                    for (Edificio edificio : ciudad.getListaPosicionesEdificios().values()) {
                        if (edificio.getEdificiosPreCargado().getTipo() == 1) {
                            counter += edificio.getNivel();
                        }
                    }
                }
                if (total < counter) {
                    button.setOnMouseClicked(e -> {
                        MapasController.newCiudad = true;
                        reload(MundoController.class);
                    });
                } else {
                    button.setDisable(true);
                }
                break;
            case 2:
                button.setText("Entrenar nuevos Soldados");
                button.setOnMouseClicked(e -> {
                    createMenuLeftSpecial(borderPane, 0);
                });
                break;
            case 3:
                button.setText("Construir maquinaria de asedio");
                button.setOnMouseClicked(e -> {

                });
                break;
        }
        childrenFlowPane.add(button);
        Separator separator = new Separator();
        separator.setPrefWidth(200);
        separator.setVisible(false);
        childrenFlowPane.add(separator);
    }

    private static void createMenuLeftSpecial(BorderPane borderPane, int i) {
        List<VBox> vBoxList = new ArrayList<>();
        switch (i) {
            case 0:
                vBoxList.add(cajaCrearUnidades(listaSoldadosPreCargada));//El que tenemos puesto
                break;
            case 1:
                vBoxList.add(cajaCrearUnidades(listaAsedioPreCargada));//El que tenemos puesto
                break;
        }
        rellenador(borderPane, vBoxList);
    }

    private static VBox cajaCrearUnidades(TreeMap<Integer, ? extends UnidadesPreCargadas> listaUnidades) {
        //BLOQUE
        VBox vBoxBloquePropio = new VBox();
        vBoxBloquePropio.setMinWidth(250);
        vBoxBloquePropio.setMaxWidth(250);
        vBoxBloquePropio.setAlignment(TOP_CENTER);
        vBoxBloquePropio.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        vBoxBloquePropio.setSpacing(10);
        ObservableList<Node> childrenVBox = vBoxBloquePropio.getChildren();

        Separator separator2 = new Separator();
        separator2.setPrefWidth(200);
        separator2.setVisible(false);
        childrenVBox.add(separator2);

        Label nombreEdificioPropio = new Label("name");
        nombreEdificioPropio.setTextAlignment(CENTER);
        nombreEdificioPropio.setAlignment(Pos.CENTER);
        nombreEdificioPropio.setWrapText(true);
        childrenVBox.add(nombreEdificioPropio);
/*
        ImageView imageViewPropio = new ImageView(edificioQueSaleEnMenu.getImage());
        imageViewPropio.setPickOnBounds(true);
        imageViewPropio.setPreserveRatio(true);
        childrenVBox.add(imageViewPropio);

 */

        Label descripcionEdificioPropio = new Label("NONE");
        descripcionEdificioPropio.setTextAlignment(CENTER);
        descripcionEdificioPropio.setAlignment(Pos.CENTER);
        descripcionEdificioPropio.setWrapText(true);
        childrenVBox.add(descripcionEdificioPropio);
        VBox.setMargin(descripcionEdificioPropio, new Insets(0, 15, 0, 15));

        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setAlignment(Pos.CENTER);
        ObservableList<Node> childrenFlowPane = flowPane.getChildren();


        for (UnidadesPreCargadas recursoEdificio : listaUnidades.values()) {
/*
            UnidadesPreCargadas recursoValor = recursoEdificio.getValue();
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
                                recursosMenu(flowPaneRecuros);
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
            */
        }


        VBox.setMargin(flowPane, new Insets(0, 15, 0, 15));
        childrenVBox.add(flowPane);

        Separator separator = new Separator();
        separator.setPrefWidth(200);
        childrenVBox.add(separator);


        //FIN BLOQUE
        return vBoxBloquePropio;
    }


    private void printRecursos
            (ObservableList<Node> childrenFlowPane, Set<Map.Entry<Integer, Recursos>> recursosEdificio,
             int produceAlmacenaCuesta, Edificio edificioSlider) {
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
                                recursosMenu(flowPaneRecuros);
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
        reload(MundoController.class);
    }
}

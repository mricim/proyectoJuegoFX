package main.java.juego.mapas.ciudad;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
                            Thread.sleep(220);
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
        scrollPane.prefWidth(220);
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
        separator2.setPrefWidth(220);
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
        //VBox.setMargin(descripcionEdificioPropio, new Insets(0, 15, 0, 15));
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
                    printRecursosEdificios(childrenFlowPane, edificioQueSaleEnMenu.getRecursosProductores().entrySet(), 1, edificioQueEstaEnElMapa, flowPaneRecuros);//Ponemos las barras de los selectores
                }
            } else {
                printRecursosEdificios(childrenFlowPane, edificioQueSaleEnMenu.getRecursosProductores().entrySet(), 1, null, flowPaneRecuros);
            }
            printRecursosEdificios(childrenFlowPane, edificioQueSaleEnMenu.getRecursosAlmacen().entrySet(), 2, null, flowPaneRecuros);
            if (tipoDeBoton == 1 || tipoDeBoton == 2) {
                printRecursosEdificios(childrenFlowPane, edificioQueSaleEnMenu.getRecursosCostes().entrySet(), 3, null, flowPaneRecuros);
            }


            //VBox.setMargin(flowPane, new Insets(0, 15, 0, 15));
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
                separator.setPrefWidth(220);
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
                    createMenuLeftSpecial(borderPane, 1);
                });
                break;
        }
        childrenFlowPane.add(button);
        Separator separator = new Separator();
        separator.setPrefWidth(220);
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
        separator2.setPrefWidth(220);
        separator2.setVisible(false);
        childrenVBox.add(separator2);

        Label nombreEdificioPropio = new Label("Entrenar unidades");
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

        Label descripcionEdificioPropio = new Label("Señor! Todo preparado! Si, Señor!");
        descripcionEdificioPropio.setTextAlignment(CENTER);
        descripcionEdificioPropio.setAlignment(Pos.CENTER);
        descripcionEdificioPropio.setWrapText(true);
        childrenVBox.add(descripcionEdificioPropio);
        //vBox.setMargin(descripcionEdificioPropio, new Insets(0, 15, 0, 15));

        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setAlignment(Pos.CENTER);
        ObservableList<Node> childrenFlowPane = flowPane.getChildren();

        TreeMap<Integer, Recursos> recursosCiudad = getCiudadPrimaryStageController().getRecursosTreeMap();
        TreeMap<Integer, Recursos> resta = new TreeMap<>();
        TreeMap<Integer, Recursos> comoQuedariaCiudad = recursosCiudad;
        for (Integer integer : recursosCiudad.keySet()) {
            resta.put(integer, new Recursos(integer, 0));
        }

        //ESTO ES PARA QUE ENTRE EN EL SISTEMA
        FlowPane flowPane2 = new FlowPane();
        flowPane2.setHgap(15);
        flowPane2.setVgap(10);
        flowPane2.setAlignment(Pos.CENTER);
        FlowPane flowPane3 = new FlowPane();
        flowPane3.setHgap(15);
        flowPane3.setVgap(10);
        flowPane3.setAlignment(Pos.CENTER);
        printAllRecursos(flowPane2.getChildren(), resta.entrySet(), 3, 3);
        printAllRecursos(flowPane3.getChildren(), comoQuedariaCiudad.entrySet(), 4, 2);
        Button button = new Button("Entrenar");
        //TODO
        //FIN ESTO ES PARA QUE ENTRE EN EL SISTEMA
        for (UnidadesPreCargadas unidadesPreCargadas : listaUnidades.values()) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            ImageView imageView = new ImageView(unidadesPreCargadas.getImageIcon());
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);

            List<Recursos> costesRecursos = unidadesPreCargadas.getCostes();
            int maxSoldados = 1001;
            for (Recursos costesRecurso : costesRecursos) {
                int tempo = recursosCiudad.get(costesRecurso.getId()).getCantidad();
                if (costesRecurso.getCantidad() != 0) {
                    int x;
                    if (tempo == 0 || maxSoldados == 0) {
                        maxSoldados = 0;
                        break;
                    } else if (maxSoldados > (x = tempo / costesRecurso.getCantidad())) {
                        maxSoldados = x;
                    }
                }
            }


            Label label = new Label("0");
            Slider slider = new Slider(0, maxSoldados, 0);
            slider.setShowTickMarks(true);
            slider.setMajorTickUnit(4);
            slider.setSnapToTicks(true);
            //label.textProperty().bind(new SimpleIntegerProperty(integer).asString());

            slider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    int a = newValue.intValue();
                    int number = oldValue.intValue() - a;
                    if (number != 0) {
                        boolean controladora=false;
                        label.textProperty().setValue(String.valueOf(a));
                        for (Recursos recursos : costesRecursos) {
                            Recursos recursoARestar = resta.get(recursos.getId());
                            Recursos recursosCityTemp = comoQuedariaCiudad.get(recursos.getId());
                            if (number < 0) {
                                recursoARestar.setCantidad(recursoARestar.getCantidad() + recursos.getCantidad());
                                int numerico=recursosCityTemp.getCantidad() - recursos.getCantidad();
                                recursosCityTemp.setCantidad(numerico);
                                if (numerico<0){
                                    controladora=true;
                                }
                            } else {
                                recursoARestar.setCantidad(recursoARestar.getCantidad() - recursos.getCantidad());
                                int numerico=recursosCityTemp.getCantidad() + recursos.getCantidad();
                                recursosCityTemp.setCantidad(numerico);
                                if (numerico<0){
                                    controladora=true;
                                }
                            }

                        }
                        if (controladora){
                            button.setDisable(true);
                        }else {
                            button.setDisable(false);
                        }

                        flowPane2.getChildren().clear();
                        flowPane3.getChildren().clear();
                        printAllRecursos(flowPane2.getChildren(), resta.entrySet(), 3, 3);
                        printAllRecursos(flowPane3.getChildren(), comoQuedariaCiudad.entrySet(), 4, 2);
                    }
                }
            });
            hBox.getChildren().addAll(imageView, slider, label);
            childrenFlowPane.add(hBox);


        }

        //VBox.setMargin(flowPane, new Insets(0, 15, 0, 15));
        childrenVBox.add(flowPane);


        childrenVBox.add(flowPane2);
        childrenVBox.add(flowPane3);


        childrenVBox.add(button);

        Separator separator = new Separator();
        separator.setPrefWidth(220);
        childrenVBox.add(separator);


        //FIN BLOQUE
        return vBoxBloquePropio;
    }


    private static void printRecursosEdificios(ObservableList<Node> childrenFlowPane, Set<Map.Entry<Integer, Recursos>> recursosEdificio, int produce_Almacena_Cuesta_Resto, Edificio edificioSlider, FlowPane flowPaneRecuros) {
        boolean paso0 = false;
        for (Map.Entry<Integer, Recursos> recurso : recursosEdificio) {
            Recursos recursoValor = recurso.getValue();
            if (recursoValor.getCantidad() > 0) {
                paso0 = true;
            }
        }
        if (paso0) {
            if (produce_Almacena_Cuesta_Resto == 1) {
                Separator separator = new Separator();
                separator.setPrefWidth(220);
                childrenFlowPane.add(separator);
            }
            Label label = new Label();
            label.setBackground(new Background(new BackgroundFill(Color.rgb(238, 174, 160), CornerRadii.EMPTY, Insets.EMPTY)));
            nombreEtiquetas(produce_Almacena_Cuesta_Resto, label);
            childrenFlowPane.add(label);
            Separator separator2 = new Separator();
            separator2.setPrefWidth(220);
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
                    switch (produce_Almacena_Cuesta_Resto) {
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
            separator.setPrefWidth(220);
            childrenFlowPane.add(separator);
        }
    }

    private static void printAllRecursos(ObservableList<Node> childrenFlowPane, Set<Map.Entry<Integer, Recursos>> recursosEdificio, int produce_Almacena_Cuesta_Resto, int formatPrint) {

        Separator separator = new Separator();
        separator.setPrefWidth(220);
        childrenFlowPane.add(separator);

        Label etiqueta = new Label();
        etiqueta.setBackground(new Background(new BackgroundFill(Color.rgb(238, 174, 160), CornerRadii.EMPTY, Insets.EMPTY)));
        nombreEtiquetas(produce_Almacena_Cuesta_Resto, etiqueta);
        childrenFlowPane.add(etiqueta);
        Separator separator2 = new Separator();
        separator2.setPrefWidth(220);
        separator2.setVisible(false);
        childrenFlowPane.add(separator2);


        for (Map.Entry<Integer, Recursos> recursoEdificio : recursosEdificio) {

            Recursos recursoValor = recursoEdificio.getValue();
            int numero = recursoValor.getCantidad();

            ImageView imageView = new ImageView(recursoValor.getImage());
            imageView.setFitWidth(25);
            imageView.setFitHeight(25);
            Label label2 = new Label();
            switch (formatPrint) {
                case 1:
                    if (numero > 0) {
                        label2.setText("+" + numero + "/h");
                        label2.setTextFill(Color.GREEN);
                    } else {//if (numero != 0)
                        label2.setText(numero + "/h");
                        label2.setTextFill(Color.RED);
                    }
                    break;
                case 2:
                    label2.setText(String.valueOf(numero));
                    if (numero<0){
                        label2.setTextFill(Color.RED);
                    }else {
                        label2.setTextFill(Color.BLACK);
                    }
                    break;
                case 3:
                    label2.setText("-" + numero);
                    label2.setTextFill(Color.RED);
                    break;
            }
            label2.setGraphic(imageView);
            label2.setTextAlignment(CENTER);
            label2.setAlignment(Pos.CENTER);
            label2.setWrapText(true);
            childrenFlowPane.add(label2);

        }


        Separator separator3 = new Separator();
        separator3.setPrefWidth(220);
        separator.setVisible(false);
        childrenFlowPane.add(separator3);

    }

    private static void nombreEtiquetas(int produce_Almacena_Cuesta_Resto, Label label) {
        switch (produce_Almacena_Cuesta_Resto) {
            case 1:
                label.setText("Produce (max):");
                break;
            case 2:
                label.setText("Almacena:");
                break;
            case 3:
                label.setText("Cuesta:");
                break;
            case 4:
                label.setText("Resto:");
                break;
            default:
                label.setText("Error");
                break;
        }
    }

    public void toMundo(MouseEvent mouseEvent) {
        reload(MundoController.class);
    }
}

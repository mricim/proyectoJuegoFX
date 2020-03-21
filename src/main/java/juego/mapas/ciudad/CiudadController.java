package main.java.juego.mapas.ciudad;

import javafx.application.Platform;
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
import main.java.utils.CallImages;
import main.java.juego.MapasController;
import main.java.juego.mapas.RecursosPrecargados;
import main.java.juego.mapas.mundo.MundoController;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.ciudad.contenidoCiudad.Edificio;
import main.java.juego.mapas.pelea.*;
import main.java.utils.tagsFXML.CustomTextField;
import main.java.utils.tagsFXML.SliderCustom;

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
                    printRecursosEdificios(borderPane,childrenFlowPane, edificioQueSaleEnMenu.getRecursosProductores().entrySet(), 1, edificioQueEstaEnElMapa, flowPaneRecuros);//Ponemos las barras de los selectores
                }
            } else {
                printRecursosEdificios(borderPane,childrenFlowPane, edificioQueSaleEnMenu.getRecursosProductores().entrySet(), 1, null, flowPaneRecuros);
            }
            printRecursosEdificios(borderPane,childrenFlowPane, edificioQueSaleEnMenu.getRecursosAlmacen().entrySet(), 2, null, flowPaneRecuros);
            if (tipoDeBoton == 1 || tipoDeBoton == 2) {
                printRecursosEdificios(borderPane,childrenFlowPane, edificioQueSaleEnMenu.getRecursosBuild().entrySet(), 3, null, flowPaneRecuros);
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
                    for (Recursos edificioCostes : edificioQueSaleEnMenu.getRecursosBuild().values()) {
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
                        for (Recursos value : edificioQueSaleEnMenu.getRecursosBuild().values()) {
                            Recursos recursos = getCiudadPrimaryStageController().getRecursosTreeMap().get(value.getId());
                            recursos.removeCantidad(value.getCantidad());
                        }
                    } else {
                        EdificiosPreCargados edificiosPreCargadoEnElMapa = edificioQueEstaEnElMapa.getEdificiosPreCargado();
                        for (Recursos value : edificiosPreCargadoEnElMapa.getRecursosBuild().values()) {
                            Recursos recursosCity = getCiudadPrimaryStageController().getRecursosTreeMap().get(value.getId());
                            recursosCity.addCantidad(((value.getCantidad() * 80) / 100));
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
                button.setText("Entrenar nuevos Unidades");
                button.setOnMouseClicked(e -> {
                    createMenuLeftSpecial(borderPane, flowPaneRecuros, 0);
                });
                break;
            case 3:
                button.setText("Construir maquinaria de asedio");
                button.setOnMouseClicked(e -> {
                    createMenuLeftSpecial(borderPane, flowPaneRecuros, 1);
                });
                break;
        }
        childrenFlowPane.add(button);
        Separator separator = new Separator();
        separator.setPrefWidth(220);
        separator.setVisible(false);
        childrenFlowPane.add(separator);
    }

    private static void createMenuLeftSpecial(BorderPane borderPane, FlowPane flowPaneRecuros, int i) {
        List<VBox> vBoxList = new ArrayList<>();
        switch (i) {
            case 0:
                vBoxList.add(cajaCrearUnidades(listaSoldadosPreCargada, 0, borderPane, flowPaneRecuros));//El que tenemos puesto
                break;
            case 1:
                vBoxList.add(cajaCrearUnidades(listaSoldadosPreCargada, 5, borderPane, flowPaneRecuros));//El que tenemos puesto
                break;
        }
        rellenador(borderPane, vBoxList);
    }

    private static VBox cajaCrearUnidades(TreeMap<Integer, UnidadesPreCargadas> listaUnidades, int tipoDeUnidades, BorderPane borderPane, FlowPane flowPaneRecuros) {
        //BLOQUE
        VBox vBoxBloquePropio = new VBox();
        vBoxBloquePropio.setMinWidth(300);
        vBoxBloquePropio.setMaxWidth(300);
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

        TreeMap<Integer, Recursos> recursosCiudadTemp = new TreeMap<>();
        TreeMap<Integer, Recursos> resta = new TreeMap<>();

        TreeMap<Integer, Unidades> soldadesca = new TreeMap<>();
        for (Unidades unidades : getCiudadPrimaryStageController().getListSoldadosCity().values()) {
            soldadesca.put(unidades.getTipeUnit(), new Unidades(unidades.getUnidadesPreCargadas(), 0, 0, 0, 0));
        }


        for (Recursos integer : getCiudadPrimaryStageController().getRecursosTreeMap().values()) {
            int id = integer.getId();
            resta.put(id, new Recursos(id, 0));
            recursosCiudadTemp.put(id, new Recursos(id, integer.getCantidad()));
        }

        //ESTO ES PARA QUE ENTRE EN EL SISTEMA
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        VBox vBox1 = new VBox();
        vBox1.setAlignment(Pos.CENTER);

        printRecursosRestando(vBox.getChildren(), getCiudadPrimaryStageController().getRecursosTreeMap(), resta, recursosCiudadTemp, 3);

        Button button = new Button("Entrenar");
        button.setOnMouseClicked(e -> {
            getCiudadPrimaryStageController().addSoldados(soldadesca);
            for (Recursos recursos : getCiudadPrimaryStageController().getRecursosTreeMap().values()) {
                recursos.removeCantidad(resta.get(recursos.getId()).getCantidad());
            }

            borderPane.setLeft(null);
            recursosMenu(flowPaneRecuros);
        });
        //FIN ESTO ES PARA QUE ENTRE EN EL SISTEMA
        for (UnidadesPreCargadas unidadesPreCargadas : listaUnidades.values()) {
            if (unidadesPreCargadas.getTipoLucha() == tipoDeUnidades) {
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER);
                hBox.setSpacing(10);
                ImageView imageView = new ImageView(unidadesPreCargadas.getImageIcon());
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);

                List<Recursos> costesRecursos = unidadesPreCargadas.getCostes();
                int maxSoldados = 1001;
                for (Recursos costesRecurso : costesRecursos) {
                    int tempo = recursosCiudadTemp.get(costesRecurso.getId()).getCantidad();
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
                CustomTextField textField = new CustomTextField("0", true, maxSoldados);
                //textField.textProperty().bind(slider.valueProperty().asString("%.0f"));

                int conters = getCiudadPrimaryStageController().getListSoldadosCity().get(unidadesPreCargadas.getIdType()).getCantidad();
                Label label2 = new Label("+ " + conters);
                if (conters > 0) {
                    label2.setTextFill(Color.GREEN);
                }
                SliderCustom slider = new SliderCustom(0, maxSoldados, 0);
                slider.setmargin(25, 0, 0, 0);
                slider.valueProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        int seleccionado = newValue.intValue();
                        int number = oldValue.intValue() - seleccionado;
                        if (number != 0) {
                            textField.textProperty().setValue(String.valueOf(seleccionado));
                            Platform.runLater(() -> controllerSlider(number, unidadesPreCargadas, soldadesca, costesRecursos, resta, recursosCiudadTemp, button, vBox, vBox1));
                        }
                    }
                });
                textField.setBindSlider(slider);
                hBox.getChildren().addAll(imageView, slider, textField, label2);
                childrenFlowPane.add(hBox);


            }
        }

        //VBox.setMargin(flowPane, new Insets(0, 15, 0, 15));
        childrenVBox.add(flowPane);


        childrenVBox.add(vBox);
        childrenVBox.add(vBox1);


        childrenVBox.add(button);

        Separator separator = new Separator();
        separator.setPrefWidth(220);
        childrenVBox.add(separator);


        //FIN BLOQUE
        return vBoxBloquePropio;
    }


    private static synchronized void controllerSlider(int seleccionadoNumber, UnidadesPreCargadas unidadesPreCargadas, TreeMap<Integer, Unidades> soldadesca, List<Recursos> costesRecursosUnidades, TreeMap<Integer, Recursos> resta, TreeMap<Integer, Recursos> recursosCiudadTemp, Button button, VBox flowPane2, VBox flowPane3) {
        boolean controladora = false;
        boolean controladoraToFor = false;
        int conversorAPositivo = (seleccionadoNumber < 0 ? -seleccionadoNumber : seleccionadoNumber);
        if (unidadesPreCargadas != null) {
            int idQueEs = unidadesPreCargadas.getIdType();
            Unidades unidades = soldadesca.get(idQueEs);
            if (seleccionadoNumber < 0) {
                unidades.setCantidad(unidades.getCantidad() + conversorAPositivo);
                controladoraToFor = true;
            } else {
                unidades.setCantidad(unidades.getCantidad() - conversorAPositivo);
            }
        }

        for (Recursos recursosUnits : costesRecursosUnidades) {
            int idUnits = recursosUnits.getId();
            int costeUnidades = recursosUnits.getCantidad() * conversorAPositivo;
            if (controladoraToFor) {
                resta.get(idUnits).addCantidad(costeUnidades);
                Recursos g = recursosCiudadTemp.get(idUnits);
                g.removeCantidad(costeUnidades);
                if (g.getCantidad() < 0) {
                    controladora = true;
                }
            } else {
                resta.get(idUnits).removeCantidad(costeUnidades);
                Recursos g = recursosCiudadTemp.get(idUnits);
                g.addCantidad(costeUnidades);
                if (g.getCantidad() < 0) {
                    controladora = true;
                }
            }
        }

        button.setDisable(controladora);

        flowPane2.getChildren().clear();
        flowPane3.getChildren().clear();
        printRecursosRestando(flowPane2.getChildren(), getCiudadPrimaryStageController().getRecursosTreeMap(), resta, recursosCiudadTemp, 3);
    }


    private synchronized static void printRecursosRestando(ObservableList<Node> childrenFlowPane, TreeMap<Integer, Recursos> recursosEnLaCIty, TreeMap<Integer, Recursos> recursosResta, TreeMap<Integer, Recursos> recursosCiudadTemp, int produce_Almacena_Cuesta_Resto) {

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


        for (Recursos recursoValor : recursosEnLaCIty.values()) {
            HBox hBox = new HBox();
            hBox.setMaxWidth(250);//TODO
            hBox.setAlignment(Pos.CENTER);
            int id = recursoValor.getId();


            ImageView imageView = new ImageView(recursoValor.getImage());
            imageView.setFitWidth(30);
            imageView.setFitHeight(30);
            Pane pane = new Pane(imageView);
            pane.setPadding(new Insets(0, 5, 0, 0));

            int numero = recursoValor.getCantidad();
            Label enCity = new Label(String.valueOf(numero));
            int numero2 = recursosResta.get(id).getCantidad();
            Label label2 = new Label(String.valueOf(numero2));
            if (numero2 < 1) {
                label2.setTextFill(Color.GREEN);
            } else {
                label2.setTextFill(Color.RED);
            }
            int resultado = recursosCiudadTemp.get(id).getCantidad();
            Label label3 = new Label(String.valueOf(resultado));
            label3.setStyle("-fx-font-weight: bold");
            if (resultado > -1) {
                label3.setTextFill(Color.BLACK);
            } else {
                label3.setTextFill(Color.RED);

            }
            Region region = new Region();
            region.setMinWidth(5);
            HBox.setHgrow(region, Priority.ALWAYS);
            hBox.getChildren().addAll(pane, region, enCity, new Label("-"), label2, new Label("="), label3);
            childrenFlowPane.add(hBox);
        }
        Separator separator3 = new Separator();
        separator3.setPrefWidth(220);
        separator.setVisible(false);
        childrenFlowPane.add(separator3);
    }

    private static void printRecursosEdificios(BorderPane borderPane,ObservableList<Node> childrenFlowPane, Set<Map.Entry<Integer, Recursos>> recursosEdificio, int produce_Almacena_Cuesta_Resto, Edificio edificioSlider, FlowPane flowPaneRecuros) {
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
                    int poblacionPuesta =
                            poblacionMaximaQueSePuedePoner - edificioSlider.getTrabajadoresNecesarios().get(recursoValor.getId()).getCantidad();
                    HBox hBox = new HBox();
                    int maxPoblacion = Math.min(poblacionMaximaQueSePuedePoner, pobacionCiudad + poblacionPuesta);
                    CustomTextField customTextField = new CustomTextField(String.valueOf(poblacionPuesta), true, maxPoblacion);
                    Label labelMax = new Label(" /" + poblacionMaximaQueSePuedePoner);
                    SliderCustom slider = new SliderCustom(0, maxPoblacion, poblacionPuesta);
                    slider.setPadding(new Insets(25, 5, 0, 0));
                    slider.valueProperty().addListener(new ChangeListener<Number>() {
                        @Override
                        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                            int a = newValue.intValue();
                            int number = oldValue.intValue() - a;
                            if (number != 0) {
                                if (recursoAQuitar.getCantidad() + number >= 0) {
                                    customTextField.textProperty().setValue(String.valueOf(a));
                                    recursoAQuitar.addCantidad(number);
                                    edificioSlider.getTrabajadoresNecesarios().get(recursoValor.getId()).addCantidad(number);
                                    recursosMenu(flowPaneRecuros);
                                }else {
                                    borderPane.setLeft(null);
                                }
                            }
                        }
                    });
                    customTextField.setBindSlider(slider);
                    hBox.getChildren().addAll(imageView, slider, customTextField, labelMax);
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

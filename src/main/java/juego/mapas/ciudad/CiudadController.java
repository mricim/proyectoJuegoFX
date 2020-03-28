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
import main.java.juego.mapas.RecursosPrecargados;
import main.java.utils.CallImages;
import main.java.juego.MapasController;
import main.java.juego.mapas.mundo.MundoController;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.ciudad.contenidoCiudad.Edificio;
import main.java.juego.mapas.pelea.*;
import main.java.utils.tagsFXML.CustomSeparator;
import main.java.utils.tagsFXML.CustomTextField;
import main.java.utils.tagsFXML.CustomSlider;

import java.net.URL;
import java.util.*;

import static javafx.geometry.Pos.TOP_CENTER;
import static javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER;
import static javafx.scene.text.TextAlignment.CENTER;
import static jdk.nashorn.internal.objects.Global.Infinity;
import static main.java.jugadores.Jugador.*;


public class CiudadController extends MapasController implements Initializable {
    public static final String THIS_RUTE_IMAGES = "mapas/city/";
    private static final int PORCENTAGE_A_DEVOLVER = 80;
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
                createMenuLeftPrimary(borderPane, imageView, posicionEdificio);
            }
        }
    }

    private void createMenuLeftPrimary(BorderPane borderPane, ImageView imageView, Edificio edificioPuesto) {
        List<VBox> vBoxList = new ArrayList<>();

        int edificioId = edificioPuesto.getId();
        int edificioNivel = edificioPuesto.getNivel();
        if (edificioId != 0) {// NO PARCELA
            vBoxList.add(cajaEdificio(edificioPuesto, imageView, false, edificioPuesto.getEdificiosPreCargado(), 0));//El que tenemos puesto
            if (edificioNivel > 0) {
                try {
                    vBoxList.add(cajaEdificio(edificioPuesto, imageView, false, listaEdificiosPreCargados.get(edificioId + "_" + (edificioNivel + 1)), 2));
                } catch (Exception e) {
                }
                vBoxList.add(cajaEdificio(edificioPuesto, imageView, false, listaEdificiosPreCargados.get(edificioId + "_" + (edificioNivel - 1)), 3));
            } else {
                try {
                    vBoxList.add(cajaEdificio(edificioPuesto, imageView, false, listaEdificiosPreCargados.get(edificioId + "_" + (edificioNivel + 1)), 2));
                } catch (Exception e) {
                }
                if (edificioPuesto.getEdificiosPreCargado().isDestruible()) {
                    vBoxList.add(cajaEdificio(edificioPuesto, imageView, false, listaEdificiosPreCargados.get(0 + "_" + 0), 4));
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
            vBoxList.add(cajaEdificio(edificioPuesto, imageView, true, edificioPuesto.getEdificiosPreCargado(), 0));
            for (Map.Entry<String, EdificiosPreCargados> preCargadosEntry : listaEdificiosPreCargados.entrySet()) {
                EdificiosPreCargados temp = preCargadosEntry.getValue();
                if (temp.getNivel() == 0 && temp.isConstruible() && getCiudadPrimaryStageController().getNivelCiudad() >= temp.getNivelCastilloNecesario()) {
                    if (counterTiposDeEdificioEnLaCiudad.get(temp.getId()) == null || counterTiposDeEdificioEnLaCiudad.get(temp.getId()) < temp.getMaximoEdificiosDelMismoTipo()) {
                        vBoxList.add(cajaEdificio(edificioPuesto, imageView, false, temp, 1));
                    }
                }
            }
        }


        rellenador(borderPane, vBoxList);
    }

    private static void rellenador(BorderPane borderPane, List<VBox> vBoxList) {
        VBox vBox = new VBox();
        vBox.setMinWidth(300);
        vBox.setMaxWidth(300);
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



        scrollPane.setHbarPolicy(NEVER);
        //scrollPane.()BorderPane.alignment="CENTER"

        borderPane.setLeft(scrollPane);
    }

    private VBox cajaEdificio(Edificio edificioPuesto, ImageView imageView, boolean parcela, EdificiosPreCargados edificioPosiblesConstrucciones, int construir_Update_Dowgrade_Destruir) {

        //BLOQUE
        VBox vBoxBloquePropio = new VBox();
        vBoxBloquePropio.setMinWidth(295);
        vBoxBloquePropio.setMaxWidth(295);
        vBoxBloquePropio.setAlignment(TOP_CENTER);
        vBoxBloquePropio.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        vBoxBloquePropio.setSpacing(10);
        ObservableList<Node> childrenVBox = vBoxBloquePropio.getChildren();

        Separator separator2 = new Separator();
        separator2.setPrefWidth(220);
        separator2.setVisible(false);
        childrenVBox.add(separator2);

        Label nombreEdificioPropio = new Label(edificioPosiblesConstrucciones.getNombre());
        nombreEdificioPropio.setTextAlignment(CENTER);
        nombreEdificioPropio.setAlignment(Pos.CENTER);
        nombreEdificioPropio.setWrapText(true);
        childrenVBox.add(nombreEdificioPropio);

        ImageView imageViewPropio = new ImageView(edificioPosiblesConstrucciones.getImage());
        imageViewPropio.setPickOnBounds(true);
        imageViewPropio.setPreserveRatio(true);
        childrenVBox.add(imageViewPropio);
        if (edificioPosiblesConstrucciones.getId() != 0) {
            Label nivelEdificioPropio = new Label("Nivel: " + edificioPosiblesConstrucciones.getNivel());
            nivelEdificioPropio.setTextAlignment(CENTER);
            nivelEdificioPropio.setAlignment(Pos.CENTER);
            nivelEdificioPropio.setWrapText(true);
            childrenVBox.add(nivelEdificioPropio);
        }
        Label descripcionEdificioPropio = new Label(edificioPosiblesConstrucciones.getDescripcion());
        descripcionEdificioPropio.setTextAlignment(CENTER);
        descripcionEdificioPropio.setAlignment(Pos.CENTER);
        descripcionEdificioPropio.setWrapText(true);
        childrenVBox.add(descripcionEdificioPropio);
        if (!parcela) {
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);
            ObservableList<Node> vBoxChildren = vBox.getChildren();

            if (construir_Update_Dowgrade_Destruir == 0) {//Si es el edificio que tenemos en el mapa
                int tipo = edificioPuesto.getEdificiosPreCargado().getTipo();
                if (tipo != 0) {// Tiene menu especial?
                    printEdificioEspecial(vBoxChildren, tipo);
                } else {//Edificio generico
                    printEdificioRecursos(vBoxChildren, edificioPuesto, borderPane, flowPaneRecuros);//Ponemos las barras de los selectores o lo que cuesta manterner el edificio
                }
            } else {//EDIFICIO A CONSTRUIR
                printEdificioRecursos(vBoxChildren, edificioPosiblesConstrucciones, construir_Update_Dowgrade_Destruir);
            }

            //VBox.setMargin(flowPane, new Insets(0, 15, 0, 15));
            childrenVBox.add(vBox);
            if (construir_Update_Dowgrade_Destruir != 0) {

                Button button = new Button(nameButton(construir_Update_Dowgrade_Destruir));
                if (construir_Update_Dowgrade_Destruir == 1 || construir_Update_Dowgrade_Destruir == 2) {//CALCULAR RECURSOS
                    for (Recursos edificioCostes : edificioPosiblesConstrucciones.getRecursosBuild().values()) {
                        if (edificioCostes.getCantidad() > getCiudadPrimaryStageController().getRecursosTreeMap().get(edificioCostes.getId()).getCantidad()) {
                            button.setDisable(true);
                            break;
                        }
                    }

                }//FIN CALCULAR RECURSOS
                button.setCursor(Cursor.HAND);
                button.setOnMouseClicked(e -> {//CAMBIO EDIFICIO
                    if (construir_Update_Dowgrade_Destruir == 1 || construir_Update_Dowgrade_Destruir == 2) {
                        for (Recursos value : edificioPosiblesConstrucciones.getRecursosBuild().values()) {
                            Recursos recursos = getCiudadPrimaryStageController().getRecursosTreeMap().get(value.getId());
                            recursos.removeCantidad(value.getCantidad());
                        }
                    } else {
                        EdificiosPreCargados edificiosPreCargadoEnElMapa = edificioPuesto.getEdificiosPreCargado();
                        for (Recursos value : edificiosPreCargadoEnElMapa.getRecursosBuild().values()) {
                            Recursos recursosCity = getCiudadPrimaryStageController().getRecursosTreeMap().get(value.getId());
                            recursosCity.addCantidad(((value.getCantidad() * PORCENTAGE_A_DEVOLVER) / 100));
                        }
                    }
                    recursosMenu(flowPaneRecuros);
                    borderPane.setLeft(null);
                    //devolvemos los trabajadores a la ciudad
                    for (Recursos recursos : edificioPuesto.getTrabajadoresNecesarios().values()) {
                        if (recursos.getRecursosPrecargados().isEsTrabajadores()){
                            getCiudadPrimaryStageController().getRecursosTreeMap().get(recursos.getId()).addCantidad(recursos.getCantidad());
                        }
                    }
                    //ponemos el nuevo edificio
                    new Edificio(edificioPosiblesConstrucciones,edificioPuesto.getFila(),edificioPuesto.getColumna(),getCiudadPrimaryStageController());
                    //edificioPuesto.setEdificiosPreCargado(edificioPosiblesConstrucciones);
                    //imageView.setImage(edificioPuesto.getImage());
                    reload(this.getClass());
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

    private static String nameButton(int construir_Update_Dowgrade_Destruir) {
        String text = null;
        switch (construir_Update_Dowgrade_Destruir) {
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
        return text;
    }

    private void printEdificioEspecial(ObservableList<Node> childrenFlowPane, int tipoEdificio) {
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
                button.setText(TRADUCCIONES.getString("city.button.entrenarNuevasUnidades"));
                button.setOnMouseClicked(e -> {
                    createMenuLeftSecond(borderPane, flowPaneRecuros, 2);
                });
                break;
            case 3:
                button.setText(TRADUCCIONES.getString("city.button.construirMaquinariaAsedio"));
                button.setOnMouseClicked(e -> {
                    createMenuLeftSecond(borderPane, flowPaneRecuros, 3);
                });
                break;
            case 4:
                button.setText("Comerciar");
                button.setOnMouseClicked(e -> {
                    createMenuLeftSecond(borderPane, flowPaneRecuros, 4);
                });
                break;
        }
        childrenFlowPane.add(button);
        Separator separator = new Separator();
        separator.setPrefWidth(220);
        separator.setVisible(false);
        childrenFlowPane.add(separator);
    }

    private static void createMenuLeftSecond(BorderPane borderPane, FlowPane flowPaneRecuros, int i) {
        List<VBox> vBoxList = new ArrayList<>();
        switch (i) {
            case 2:
                vBoxList.add(cajaCrearUnidades(listaSoldadosPreCargada, 0, borderPane, flowPaneRecuros));//El que tenemos puesto
                break;
            case 3:
                vBoxList.add(cajaCrearUnidades(listaSoldadosPreCargada, 5, borderPane, flowPaneRecuros));//El que tenemos puesto
                break;
            case 4:
                vBoxList.add(cajaCrearUnidades(listaSoldadosPreCargada, 4, borderPane, flowPaneRecuros));//El que tenemos puesto//TODO // PUERTO , COMERCIAR
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
                CustomSlider slider = new CustomSlider(0, maxSoldados, 0);
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


    private synchronized static void printRecursosRestando(ObservableList<Node> childrenFlowPane, TreeMap<Integer, Recursos> recursosEnLaCIty, TreeMap<Integer, Recursos> recursosResta, TreeMap<Integer, Recursos> recursosCiudadTemp, int produce_Almacena_Cuesta_Devolucion_Resto_cambio) {

        Separator separator = new Separator();
        separator.setPrefWidth(220);
        childrenFlowPane.add(separator);

        Label etiqueta = new Label();
        etiqueta.setBackground(new Background(new BackgroundFill(Color.rgb(238, 174, 160), CornerRadii.EMPTY, Insets.EMPTY)));
        etiqueta.setText(nombreEtiquetas(produce_Almacena_Cuesta_Devolucion_Resto_cambio));
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

        childrenFlowPane.add(new CustomSeparator(220, false));
    }

    private static void printEdificioRecursos(ObservableList<Node> vBox, Edificio edificioSlider, BorderPane borderPane, FlowPane flowPaneRecuros) {
        EdificiosPreCargados edificioprecargado = edificioSlider.getEdificiosPreCargado();

        TreeMap<Integer, Recursos> recursosAProducir = edificioprecargado.getRecursosProductores();


        if (recursosAProducir != null) {
            vBox.add(new CustomSeparator(220, true));
            TreeMap<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXmin = edificioprecargado.getRecursosCosteXmin();
            if (recursosCosteXmin == null) {
                int produce_Almacena_Cuesta_Devolucion_Resto_cambio = 1;
                printTodosLosRecursosEdificioSegunTipo(vBox, recursosAProducir, produce_Almacena_Cuesta_Devolucion_Resto_cambio, null);
            } else {
                vBox.add(new Label(nombreEtiquetas(1)));
                TreeMap<Integer, Recursos> trabajadoresEnEdificio = edificioSlider.getTrabajadoresNecesarios();
                FlowPane flowPane = new FlowPane();
                flowPane.setHgap(10);
                flowPane.setVgap(10);
                flowPane.setAlignment(Pos.CENTER);
                for (Recursos recursosPoducidosMax : recursosAProducir.values()) {//SLIDER
                    RecursosPrecargados poducidoMax = recursosPoducidosMax.getRecursosPrecargados();
                    if (recursosCosteXmin.containsKey(poducidoMax)) {
                        HBox hBox = new HBox();
                        hBox.setAlignment(Pos.CENTER);
                        ImageView imageView = new ImageView(poducidoMax.getImage());
                        imageView.setFitWidth(25);
                        imageView.setFitHeight(25);
                        hBox.getChildren().add(imageView);
                        //ArrayList<Recursos> costesPorProducido=recursosCosteXmin.get(i);
                        //String variableDondeGuardamosCantidad=recursosCosteXmin.get(recursosPoducidosMax);

                        //recursosCosteXmin.get(i)
                        System.out.println("FFFFFFFFFFFFFF");
                        System.out.println(poducidoMax);
                        System.out.println(trabajadoresEnEdificio.size());
                        System.out.println("DDDDDDDDDDDDDDDDDDDD");
                        System.out.println(trabajadoresEnEdificio.get(poducidoMax.getId()));
                        System.out.println(trabajadoresEnEdificio.get(poducidoMax.getId()).getCantidad());
                        System.out.println();
                        CustomSlider customSlider = new CustomSlider(0, recursosPoducidosMax.getCantidad(), trabajadoresEnEdificio.get(poducidoMax.getId()).getCantidad());
                        hBox.getChildren().add(customSlider);
                        ArrayList<Recursos> recursosXMin=recursosCosteXmin.get(poducidoMax);
                        int f=recursosXMin.size();
                        System.out.println(f);
                        for (int i = 0; i < f; i++) {
                            ImageView imageView2 = new ImageView(recursosXMin.get(i).getImage());
                            imageView2.setFitWidth(25);
                            imageView2.setFitHeight(25);
                            hBox.getChildren().add(imageView2);
                        }
                        vBox.add(hBox);
                    } else {

                        Recursos recursos = recursosPoducidosMax;
                        ImageView imageView = new ImageView(recursos.getImage());
                        imageView.setFitWidth(25);
                        imageView.setFitHeight(25);
                        Label label46 = new Label();
                        label46.setText("+" + recursos.getCantidad() + "/h");
                        label46.setTextFill(Color.GREEN);
                        HBox hBox = new HBox(imageView, label46);
                        hBox.setAlignment(Pos.CENTER);
                        hBox.setSpacing(5);
                        flowPane.getChildren().add(hBox);// *1 estaconectado
                    }

                }
                if (flowPane.getChildren().size() > 0) {// *1 estaconectado
                    vBox.add(new CustomSeparator(220,false));
                    vBox.add(flowPane);
                }
                //recursosAProducir
                //TODO CustomSlider customSlider = new CustomSlider()
            }

        }
        TreeMap<Integer, Recursos> d = edificioprecargado.getRecursosAlmacen();
        if (d != null) {
            int produce_Almacena_Cuesta_Devolucion_Resto_cambio = 2;
            vBox.add(new CustomSeparator(220, true));
            printTodosLosRecursosEdificioSegunTipo(vBox, d, produce_Almacena_Cuesta_Devolucion_Resto_cambio, null);
        }
/*
                if (edificioprecargado.getRecursosCosteXmin()!=null) {

                }
                ArrayList<Recursos> recursosAQuitar = edificioprecargado.getRecursosCosteXmin().get(recursoValor.getRecursosPrecargados());//recursoValor.getRecursosPrecargados();
                if (edificioSlider != null && recursosAQuitar != null) {//SLIDER
                    ArrayList<Recursos> recursosAQuitarCiudad = new ArrayList<>();
                    int maximos=Integer.MAX_VALUE;
                    Recursos noSeGasta=null;
                    Recursos noSeGastaCiudad=null;
                    for (Recursos recursoAQuitar : recursosAQuitar) {
                        Recursos recursoCiudad=getCiudadPrimaryStageController().getRecursosTreeMap().get(recursoAQuitar.getId());
                        recursosAQuitarCiudad.add(recursoCiudad);
                        int num=recursoCiudad.getCantidad()/recursoAQuitar.getCantidad();
                        if (num<maximos){
                            maximos=num;
                        }
                        if (!recursoAQuitar.getRecursosPrecargados().isSeGasta()){
                            noSeGasta=recursoAQuitar;
                            noSeGastaCiudad=recursoCiudad;
                        }
                    }

                    HBox hBox = new HBox();
                    //int maxPoblacion2 = Math.min(poblacionMaximaQueSePuedePoner, pobacionCiudad + poblacionPuesta);
                    CustomTextField customTextField = new CustomTextField(String.valueOf(noSeGastaCiudad.getCantidad()), true, maximos);
                    Label labelMax = new Label(" /" + noSeGasta.getCantidad());
                    CustomSlider slider = new CustomSlider(0, noSeGasta.getCantidad(), noSeGasta.getCantidad());
                    slider.setPadding(new Insets(25, 5, 0, 0));
                    /*
                    slider.valueProperty().addListener(new ChangeListener<Number>() {
                        @Override
                        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                            int a = newValue.intValue();
                            int number = oldValue.intValue() - a;
                            if (number != 0) {
                                if (recursosAQuitarCiudad.getCantidad() + number >= 0) {
                                    customTextField.textProperty().setValue(String.valueOf(a));
                                    recursosAQuitarCiudad.addCantidad(number);
                                    edificioSlider.getTrabajadoresNecesarios().get(recursoValor.getId()).addCantidad(number);
                                    recursosMenu(flowPaneRecuros);
                                } else {
                                    borderPane.setLeft(null);
                                }
                            }
                        }
                    });

                     *//*
                    customTextField.setBindSlider(slider);
                    hBox.getChildren().addAll(imageView, slider, customTextField, labelMax);
                    childrenFlowPane.add(hBox);
                    */


        vBox.add(new CustomSeparator(220, true));

    }

    private static void printEdificioRecursos(ObservableList<Node> vBox, EdificiosPreCargados edificioprecargado, int construir_Update_Dowgrade_Destruir) {
        TreeMap<Integer, Recursos> b = edificioprecargado.getRecursosProductores();
        TreeMap<RecursosPrecargados, ArrayList<Recursos>> c = edificioprecargado.getRecursosCosteXmin();
        TreeMap<Integer, Recursos> d = edificioprecargado.getRecursosAlmacen();
        TreeMap<Integer, Recursos> a = edificioprecargado.getRecursosBuild();

        if (b != null) {
            int produce_Almacena_Cuesta_Devolucion_Resto_cambio = 1;
            vBox.add(new CustomSeparator(220, true));
            printTodosLosRecursosEdificioSegunTipo(vBox, b, produce_Almacena_Cuesta_Devolucion_Resto_cambio, null);
        }
        if (c != null) {
            int produce_Almacena_Cuesta_Devolucion_Resto_cambio = 6;
            vBox.add(new CustomSeparator(220, true));
            printTodosLosRecursosEdificioSegunTipo(vBox, c, produce_Almacena_Cuesta_Devolucion_Resto_cambio, true);
        }
        if (d != null) {
            int produce_Almacena_Cuesta_Devolucion_Resto_cambio = 2;
            vBox.add(new CustomSeparator(220, true));
            printTodosLosRecursosEdificioSegunTipo(vBox, d, produce_Almacena_Cuesta_Devolucion_Resto_cambio, null);
        }
        if (a != null) {
            int produce_Almacena_Cuesta_Devolucion_Resto_cambio = 3;
            vBox.add(new CustomSeparator(220, true));
            printTodosLosRecursosEdificioSegunTipo(vBox, a, produce_Almacena_Cuesta_Devolucion_Resto_cambio, construir_Update_Dowgrade_Destruir);
        }
        vBox.add(new CustomSeparator(220, true));
    }

    private static void printTodosLosRecursosEdificioSegunTipo(ObservableList<Node> vBox, TreeMap<RecursosPrecargados, ArrayList<Recursos>> a, int produce_Almacena_Cuesta_Devolucion_Resto_cambio, boolean daIgualLoQuePongas) {
        Label label = new Label(nombreEtiquetas(produce_Almacena_Cuesta_Devolucion_Resto_cambio));
        label.setBackground(new Background(new BackgroundFill(Color.rgb(238, 174, 160), CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.add(label);
        for (Map.Entry<RecursosPrecargados, ArrayList<Recursos>> listEntry : a.entrySet()) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(5);
            ObservableList<Node> i = hBox.getChildren();
            ImageView imageView = new ImageView(listEntry.getKey().getImage());
            imageView.setFitWidth(25);
            imageView.setFitHeight(25);
            i.add(imageView);
            i.add(new Label("="));
            ArrayList<Recursos> recursosList = listEntry.getValue();
            int recursosListcount = recursosList.size();
            for (int j = 0; j < recursosListcount; j++) {
                Recursos recursos = recursosList.get(j);
                ImageView imageView2 = new ImageView(recursos.getImage());
                imageView2.setFitWidth(25);
                imageView2.setFitHeight(25);
                Label label465 = new Label(String.valueOf(recursos.getCantidad()));
                label465.setGraphic(imageView2);
                i.add(label465);
                if (recursosListcount - 1 > j) {
                    i.add(new Label("+"));
                }
            }
            vBox.add(hBox);
        }
    }

    private static void printTodosLosRecursosEdificioSegunTipo(ObservableList<Node> vBox, TreeMap<Integer, Recursos> a, int produce_Almacena_Cuesta_Devolucion_Resto_cambio, Integer construir_Update_Dowgrade_Destruir) {
        Label label = new Label(nombreEtiquetas(produce_Almacena_Cuesta_Devolucion_Resto_cambio));
        label.setBackground(new Background(new BackgroundFill(Color.rgb(238, 174, 160), CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.add(label);

        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setAlignment(Pos.CENTER);
        ObservableList<Node> childrenFlowPane = flowPane.getChildren();

        for (Recursos recursoEdificio : a.values()) {

            int numero = recursoEdificio.getCantidad();
            if (numero != 0) {
                ImageView imageView = new ImageView(recursoEdificio.getImage());
                imageView.setFitWidth(25);
                imageView.setFitHeight(25);
                //TODO AQUI IRIA EL SISTEMA DE COBRO

                Label label46 = new Label();
                switch (produce_Almacena_Cuesta_Devolucion_Resto_cambio) {
                    case 1:
                        if (numero > 0) {
                            label46.setText("+" + numero + "/h");
                            label46.setTextFill(Color.GREEN);
                        } else {//if (numero != 0)
                            label46.setText(numero + "/h");
                            label46.setTextFill(Color.RED);
                        }
                        break;
                    case 2:
                        label46.setText(String.valueOf(numero));
                        break;
                    case 3:
                        if (construir_Update_Dowgrade_Destruir != null && construir_Update_Dowgrade_Destruir > 2) {
                            label46.setText("+" + (numero * PORCENTAGE_A_DEVOLVER / 100));
                        } else {
                            label46.setText("-" + numero);
                            label46.setTextFill(Color.RED);
                        }
                        break;
                }
                label46.setGraphic(imageView);
                label46.setTextAlignment(CENTER);
                label46.setAlignment(Pos.CENTER);
                label46.setWrapText(true);
                childrenFlowPane.add(label46);
            }

        }
        vBox.add(flowPane);
    }

    private static String nombreEtiquetas(int produce_Almacena_Cuesta_Devolucion_Resto_cambio) {
        switch (produce_Almacena_Cuesta_Devolucion_Resto_cambio) {
            case 1:
                return "Produce:";
            case 2:
                return "Almacena:";
            case 3:
                return "Coste de construcción:";
            case 4:
                return "Devolucion:";
            case 5:
                return "Resto:";
            case 6:
                return "Cambio:";
            default:
                return "Error";
        }
    }

    public void toMundo(MouseEvent mouseEvent) {
        reload(MundoController.class);
    }
}

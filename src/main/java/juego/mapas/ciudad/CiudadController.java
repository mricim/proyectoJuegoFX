package main.java.juego.mapas.ciudad;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import main.java.Inicio.PantallaInicialController;
import main.java.juego.comercio.Comercio;
import main.java.juego.mapas.RecursosPrecargados;
import main.java.jugadores.Clan;
import main.java.jugadores.Jugador;
import main.java.utils.CallImages;
import main.java.juego.MapasController;
import main.java.juego.mapas.mundo.MundoController;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.ciudad.contenidoCiudad.Edificio;
import main.java.juego.mapas.pelea.*;
import main.java.utils.Time;
import main.java.utils.tagsFX.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

import static javafx.geometry.Pos.TOP_CENTER;
import static javafx.scene.text.TextAlignment.CENTER;


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
        recursosMenu(flowPaneRecuros, this.getClass());
        selectorDeCiudad(selectorCiudad, this.getClass());
        gridPaneMap.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {//Cerrar el menu
            queClicas(null, null);
        });
        //<-- Controlado por MapasController
        ObservableList<Node> x = dondeVaLaImagen.getChildren();
        List<Node> asd = new ArrayList<>();
        ImageView imageViewBase = new ImageView();
        imageViewBase.setImage(CallImages.getImage(THIS_RUTE_IMAGES, "fondoCiudad", 3000, 1700));
        asd.add(imageViewBase);
        asd.addAll(x);
        x.clear();
        x.addAll(asd);
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
                    vBoxList.add(cajaEdificio(edificioPuesto, imageView, false, PantallaInicialController.elTemaSeleccionado.listaEdificiosPreCargados.get(edificioId + "_" + (edificioNivel + 1)), 2));
                } catch (Exception e) {
                }
                vBoxList.add(cajaEdificio(edificioPuesto, imageView, false, PantallaInicialController.elTemaSeleccionado.listaEdificiosPreCargados.get(edificioId + "_" + (edificioNivel - 1)), 3));
            } else {
                try {
                    vBoxList.add(cajaEdificio(edificioPuesto, imageView, false, PantallaInicialController.elTemaSeleccionado.listaEdificiosPreCargados.get(edificioId + "_" + (edificioNivel + 1)), 2));
                } catch (Exception e) {
                }
                if (edificioPuesto.getEdificiosPreCargado().isDestruible()) {
                    vBoxList.add(cajaEdificio(edificioPuesto, imageView, false, PantallaInicialController.elTemaSeleccionado.listaEdificiosPreCargados.get(0 + "_" + 0), 4));
                }
            }
        } else {//PARCELAS
            Collection<Edificio> posicionEdificios = getCiudadPrimaryStageController().getListaPosicionesEdificios().values();
            Map<Integer, Integer> counterTiposDeEdificioEnLaCiudad = new TreeMap<>();
            for (Edificio edificio1 : posicionEdificios) {
                int id = edificio1.getId();
                if (counterTiposDeEdificioEnLaCiudad.containsKey(id)) {
                    counterTiposDeEdificioEnLaCiudad.put(id, counterTiposDeEdificioEnLaCiudad.get(id) + 1);
                } else {
                    counterTiposDeEdificioEnLaCiudad.put(id, 1);
                }
            }
            vBoxList.add(cajaEdificio(edificioPuesto, imageView, true, edificioPuesto.getEdificiosPreCargado(), 0));
            for (Map.Entry<String, EdificiosPreCargados> preCargadosEntry : PantallaInicialController.elTemaSeleccionado.listaEdificiosPreCargados.entrySet()) {
                EdificiosPreCargados temp = preCargadosEntry.getValue();
                if (temp.getNivel() == 0 && temp.isConstruible() && getCiudadPrimaryStageController().getNivelCiudad() >= temp.getNivelCastilloNecesario()) {
                    if (counterTiposDeEdificioEnLaCiudad.get(temp.getId()) == null || counterTiposDeEdificioEnLaCiudad.get(temp.getId()) < temp.getMaximoEdificiosDelMismoTipo()) {
                        vBoxList.add(cajaEdificio(edificioPuesto, imageView, false, temp, 1));
                    }
                }
            }
        }


        rellenador(borderPane, vBoxList, 300);
    }

    /**
     * @param edificioPuesto
     * @param imageView
     * @param parcela
     * @param edificioPosiblesConstrucciones
     * @param construir_Update_Dowgrade_Destruir
     * @return
     */
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
                if (construir_Update_Dowgrade_Destruir < 3) {
                    printEdificioRecursos(vBoxChildren, edificioPosiblesConstrucciones, construir_Update_Dowgrade_Destruir, true);
                } else {
                    printEdificioRecursos(vBoxChildren, edificioPosiblesConstrucciones, construir_Update_Dowgrade_Destruir, false);

                }
            }

            //VBox.setMargin(flowPane, new Insets(0, 15, 0, 15));
            childrenVBox.add(vBox);
            if (construir_Update_Dowgrade_Destruir != 0) {
                if (construir_Update_Dowgrade_Destruir == 4) {//Si es el edificio que tenemos en el mapa
                    Label label = new Label("Se te devolvera un " + PORCENTAGE_A_DEVOLVER + "% de los recursos de la construcción");
                    label.setWrapText(true);
                    label.setAlignment(Pos.CENTER);
                    childrenVBox.add(label);
                    childrenVBox.add(new CustomSeparator(220, true));
                }


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
                    recursosMenu(flowPaneRecuros, this.getClass());
                    borderPane.setLeft(null);
                    //devolvemos los trabajadores a la ciudad
                    Map<Integer, Recursos> i = edificioPuesto.getTrabajadoresNecesarios();
                    if (i != null) {
                        Map<RecursosPrecargados, ArrayList<Recursos>> xmin = edificioPuesto.getEdificiosPreCargado().getRecursosCosteXmin();
                        for (Recursos recursos : i.values()) {
                            for (Recursos recursos1 : xmin.get(recursos.getRecursosPrecargados())) {
                                RecursosPrecargados peepe = recursos1.getRecursosPrecargados();
                                if (!peepe.isSeConsumeEnEdificios()) {
                                    getCiudadPrimaryStageController().getRecursosTreeMap().get(recursos1.getId()).addCantidad((recursos.getCantidad() * recursos1.getCantidad()));
                                    break;
                                }
                            }
                        }
                    }
                    //ponemos el nuevo edificio
                    new Edificio(edificioPosiblesConstrucciones, edificioPuesto.getFila(), edificioPuesto.getColumna(), getCiudadPrimaryStageController());
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
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        switch (tipoEdificio) {
            case 1://PALACIO
                Button buttonCase1 = new Button();
                buttonCase1.setText("New Ciudad");
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
                    buttonCase1.setOnMouseClicked(e -> {
                        MapasController.newCiudad = true;
                        reload(MundoController.class);
                    });
                } else {
                    buttonCase1.setDisable(true);
                }
                vBox.getChildren().add(buttonCase1);
                break;
            case 2://CUARTEL (SOLDADOS)
                Button buttonCase2 = new Button();
                buttonCase2.setText(TRADUCCIONES_GENERALES.getString("city.button.entrenarNuevasUnidades"));
                buttonCase2.setOnMouseClicked(e -> {
                    createMenuLeftSecond(borderPane, flowPaneRecuros, 2);
                });
                vBox.getChildren().add(buttonCase2);
                break;
            case 3://ARMAS DE ASEDIO (SOLDADOS)
                Button buttonCase3 = new Button();
                buttonCase3.setText(TRADUCCIONES_GENERALES.getString("city.button.construirMaquinariaAsedio"));
                buttonCase3.setOnMouseClicked(e -> {
                    createMenuLeftSecond(borderPane, flowPaneRecuros, 3);
                });
                vBox.getChildren().add(buttonCase3);
                break;
            case 4://PUERTO
                Button buttonCase4 = new Button();
                buttonCase4.setText("Comerciar");
                buttonCase4.setOnMouseClicked(e -> {
                    createMenuLeftSecond(borderPane, flowPaneRecuros, 4);
                });
                vBox.getChildren().add(buttonCase4);
                break;
        }
        childrenFlowPane.add(vBox);
        Separator separator = new Separator();
        separator.setPrefWidth(220);
        separator.setVisible(false);
        childrenFlowPane.add(separator);
    }

    private static void createMenuLeftSecond(BorderPane borderPane, FlowPane flowPaneRecuros, int i) {
        List<VBox> vBoxList = new ArrayList<>();
        int tamanoMenu = 0;
        switch (i) {
            case 2:
                tamanoMenu = 300;
                vBoxList.add(cajaCrearUnidades(PantallaInicialController.elTemaSeleccionado.listaSoldadosPreCargada, 0, borderPane, flowPaneRecuros, tamanoMenu));
                break;
            case 3:
                tamanoMenu = 300;
                vBoxList.add(cajaCrearUnidades(PantallaInicialController.elTemaSeleccionado.listaSoldadosPreCargada, 5, borderPane, flowPaneRecuros, tamanoMenu));
                break;
            case 4:
                tamanoMenu = 650;
                vBoxList.add(cajaCrearComercio(borderPane, flowPaneRecuros, tamanoMenu));
                break;
        }
        rellenador(borderPane, vBoxList, tamanoMenu);
    }

    private static VBox cajaCrearComercio(BorderPane borderPane, FlowPane flowPaneRecuros, int tamanoMenu) {
        VBox vBoxBloquePropio = new VBox();
        vBoxBloquePropio.setMinWidth(tamanoMenu);
        vBoxBloquePropio.setMaxWidth(tamanoMenu);
        vBoxBloquePropio.setAlignment(TOP_CENTER);
        vBoxBloquePropio.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        vBoxBloquePropio.setSpacing(10);
        ObservableList<Node> childrenVBox = vBoxBloquePropio.getChildren();
        //BLOQUE
        Separator separator2 = new Separator();
        separator2.setPrefWidth(220);
        separator2.setVisible(false);
        childrenVBox.add(separator2);

        Label nombreEdificioPropio = new Label("Comercio");
        nombreEdificioPropio.setTextAlignment(CENTER);
        nombreEdificioPropio.setAlignment(Pos.CENTER);
        nombreEdificioPropio.setWrapText(true);
        childrenVBox.add(nombreEdificioPropio);

        childrenVBox.add(new CustomSeparator((int) (flowPaneRecuros.getHeight() * 0.8), false));
//AÑADIR
        Label oferta = new Label("Ofrezco");
        oferta.setTextAlignment(CENTER);
        oferta.setAlignment(Pos.CENTER);
        oferta.setWrapText(true);
        childrenVBox.add(oferta);

        HBox hBoxAddToTableView = new HBox();
        hBoxAddToTableView.setAlignment(Pos.CENTER);
        hBoxAddToTableView.setSpacing(flowPaneRecuros.getHeight() * 0.8);
        ObservableList<Node> hBoxAddToTableViewChildren = hBoxAddToTableView.getChildren();

        ObservableList<TextImage> recursosObservableList = FXCollections.observableArrayList();
        for (Recursos recursos : getCiudadPrimaryStageController().getRecursosTreeMap().values()) {
            recursosObservableList.add(new TextImage(String.valueOf(recursos.getId()), recursos.getImage()));
        }
        ComboBox<TextImage> combo = CustomComboBoxLabel.createCombox(recursosObservableList);
        int maximoQueSePuedePedir = getCiudadPrimaryStageController().getRecursosTreeMap().get(Integer.valueOf(combo.getValue().getString())).getCantidad();
        hBoxAddToTableViewChildren.add(combo);

        CustomTextField textField = new CustomTextField("0", true, maximoQueSePuedePedir);
        CustomSlider slider = new CustomSlider(0, maximoQueSePuedePedir, 0);
        slider.setmargin(25, 0, 0, 0);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int seleccionado = newValue.intValue();
                int number = oldValue.intValue() - seleccionado;
                if (number != 0) {
                    textField.textProperty().setValue(String.valueOf(seleccionado));
                }
            }
        });
        textField.setBindSlider(slider);
        combo.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    System.out.println(newValue);
                    int x = (getCiudadPrimaryStageController().getRecursosTreeMap().get(Integer.valueOf(newValue.getString())).getCantidad());
                    slider.setMax(x);
                }
        );
        hBoxAddToTableViewChildren.add(slider);
        hBoxAddToTableViewChildren.add(textField);
        childrenVBox.add(hBoxAddToTableView);
//AÑADIR 2
        Label demanda = new Label("Pido");
        demanda.setTextAlignment(CENTER);
        demanda.setAlignment(Pos.CENTER);
        demanda.setWrapText(true);
        childrenVBox.add(demanda);

        HBox hBoxAddToTableView2 = new HBox();
        hBoxAddToTableView2.setSpacing(flowPaneRecuros.getHeight() * 0.8);
        hBoxAddToTableView2.setAlignment(Pos.CENTER);
        ObservableList<Node> hBoxAddToTableViewChildren2 = hBoxAddToTableView2.getChildren();

        ObservableList<TextImage> recursosObservableList2 = FXCollections.observableArrayList();
        for (Recursos recursos : getCiudadPrimaryStageController().getRecursosTreeMap().values()) {
            recursosObservableList2.add(new TextImage(String.valueOf(recursos.getId()), recursos.getImage()));
        }
        ComboBox<TextImage> combo2 = CustomComboBoxLabel.createCombox(recursosObservableList2);
        int maximoQueSePuedePedir2 = getCiudadPrimaryStageController().getRecursosTreeMap().get(Integer.valueOf(combo2.getValue().getString())).getCantidad();
        hBoxAddToTableViewChildren2.add(combo2);

        CustomTextField textField2 = new CustomTextField("0", true, maximoQueSePuedePedir2);
        CustomSlider slider2 = new CustomSlider(0, maximoQueSePuedePedir2, 0);
        slider2.setmargin(25, 0, 0, 0);
        slider2.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int seleccionado = newValue.intValue();
                int number = oldValue.intValue() - seleccionado;
                if (number != 0) {
                    textField2.textProperty().setValue(String.valueOf(seleccionado));
                }
            }
        });
        textField2.setBindSlider(slider2);
        combo2.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    System.out.println(newValue);
                    int x = (getCiudadPrimaryStageController().getRecursosTreeMap().get(Integer.valueOf(newValue.getString())).getCantidad());
                    slider2.setMax(x);
                }
        );
        hBoxAddToTableViewChildren2.add(slider2);
        hBoxAddToTableViewChildren2.add(textField2);
        childrenVBox.add(hBoxAddToTableView2);
//AÑADIR BUTTON
        final Button addButton = new Button("Crear oferta");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int cantidad = (int) slider.getValue();
                int cantidad2 = (int) slider2.getValue();
                if (cantidad > 0 && cantidad2 > 0) {
                    RecursosPrecargados recursosPrecargados = PantallaInicialController.elTemaSeleccionado.listaRecursosPreCargada.get(Integer.valueOf(combo.getSelectionModel().getSelectedItem().getString()));
                    RecursosPrecargados recursosPrecargados2 = PantallaInicialController.elTemaSeleccionado.listaRecursosPreCargada.get(Integer.valueOf(combo2.getSelectionModel().getSelectedItem().getString()));
                    if (recursosPrecargados.getId() != recursosPrecargados2.getId()) {
                        getCiudadPrimaryStageController().getRecursosTreeMap().get(recursosPrecargados.getId()).removeCantidad(cantidad);
                        Comercio.data.add(new Comercio(new Recursos(recursosPrecargados, cantidad), new Recursos(recursosPrecargados2, cantidad2), getJugadorPrimaryStageController()));
                        recursosMenu(flowPaneRecuros, CiudadController.class);
                        CustomAlert alert = new CustomAlert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Oferta creada!");
                        alert.showAndWait();
                    }
                }
                slider.setValue(0);
                slider2.setValue(0);
            }
        });
        childrenVBox.add(addButton);

        childrenVBox.add(new CustomSeparator((int) (flowPaneRecuros.getHeight() * 0.8), false));
//TABLA
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPannable(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        TableView<Comercio> comercioTableView = new TableView<>();
        comercioTableView.setPrefHeight(500);
        comercioTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
         /*
        TableColumn<Comercio, Integer> idCol = new TableColumn("Id");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
         */
        //columna
        TableColumn<Comercio, Jugador> clan = new TableColumn("User");
        clan.setMinWidth(120);
        clan.setCellFactory(param -> {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            TableCell<Comercio, Jugador> cell = new TableCell<Comercio, Jugador>() {
                @Override
                protected void updateItem(Jugador item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        vBox.getChildren().clear();
                        Label label1 = new Label();
                        Clan clan = Clan.jugadoresQueEstanEnUnClan.get(item);
                        if (clan != null) {
                            Label label2 = new Label();
                            label1.setText(item.getNombre());
                            label2.setText("(" + clan.getName() + " )");
                            vBox.getChildren().addAll(label1, label2);
                            if (clan == Clan.jugadoresQueEstanEnUnClan.get(getJugadorPrimaryStageController())) {
                                setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                            } else {
                                setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                            }
                        } else {
                            label1.setText(item.getNombre());
                            vBox.getChildren().add(label1);
                        }
                        comercioTableView.refresh();
                    }
                }
            };
            hBox.getChildren().add(vBox);
            cell.setGraphic(hBox);
            return cell;
        });
        clan.setCellValueFactory(new PropertyValueFactory<Comercio, Jugador>("jugador"));
//columna
        TableColumn<Comercio, Recursos> recursoOferta = new TableColumn("Oferta");
        recursoOferta.setMinWidth(120);
        recursoOferta.setCellFactory(param -> {
            HBox contenidoCelda = new HBox();
            contenidoCelda.setAlignment(Pos.CENTER);
            contenidoCelda.setSpacing(recursoOferta.getWidth() * 0.1);
            final Label cantidad = new Label();
            final ImageView recurso = new ImageView();
            recurso.setFitHeight(50);
            recurso.setFitWidth(50);
            TableCell<Comercio, Recursos> cell = new TableCell<Comercio, Recursos>() {
                @Override
                protected void updateItem(Recursos item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        recurso.setImage(item.getRecursosPrecargados().getImage());
                        cantidad.setText(String.valueOf(item.getCantidad()));
                        comercioTableView.refresh();
                    }
                }
            };
            contenidoCelda.getChildren().addAll(cantidad, recurso);
            cell.setGraphic(contenidoCelda);
            return cell;
        });
        recursoOferta.setCellValueFactory(new PropertyValueFactory<Comercio, Recursos>("queSeOfrece"));
//columna
        TableColumn<Comercio, Recursos> recursoDemanda = new TableColumn("Requiere");
        recursoDemanda.setMinWidth(120);
        recursoDemanda.setCellFactory(param -> {
            HBox contenidoCelda = new HBox();
            contenidoCelda.setAlignment(Pos.CENTER);
            contenidoCelda.setSpacing(recursoDemanda.getWidth() * 0.1);
            final Label cantidad = new Label();
            final ImageView recurso = new ImageView();
            recurso.setFitHeight(50);
            recurso.setFitWidth(50);
            TableCell<Comercio, Recursos> cell = new TableCell<Comercio, Recursos>() {
                @Override
                protected void updateItem(Recursos item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        recurso.setImage(item.getRecursosPrecargados().getImage());
                        cantidad.setText(String.valueOf(item.getCantidad()));
                        comercioTableView.refresh();
                    }
                }
            };
            contenidoCelda.getChildren().addAll(cantidad, recurso);
            cell.setGraphic(contenidoCelda);
            return cell;
        });
        recursoDemanda.setCellValueFactory(new PropertyValueFactory<Comercio, Recursos>("queSePide"));

//columna
        TableColumn<Comercio, LocalDateTime> hora = new TableColumn("Finaliza");
        hora.setMinWidth(100);
        hora.setCellFactory(param -> {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            final Label label = new Label();
            TableCell<Comercio, LocalDateTime> cell = new TableCell<Comercio, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        label.setText(Time.getChronomether(item));
                        comercioTableView.refresh();
                    }
                }
            };
            hBox.getChildren().add(label);
            cell.setGraphic(hBox);
            return cell;
        });
        hora.setCellValueFactory(new PropertyValueFactory<Comercio, LocalDateTime>("horafin"));
//columna
        //columna
        TableColumn<Comercio, Integer> boton = new TableColumn("Options");
        boton.setMinWidth(100);
        boton.setCellFactory(param -> {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            Button btn = new Button();
            TableCell<Comercio, Integer> cell = new TableCell<Comercio, Integer>() {
                {
                    btn.setOnAction((ActionEvent event) -> {
                        Comercio data = getTableView().getItems().get(getIndex());
                        Map<Integer, Recursos> a = getCiudadPrimaryStageController().getRecursosTreeMap();
                        System.out.println("selectedData: " + data);
                        Recursos b = data.getQueSePide();
                        Recursos c = data.getQueSeOfrece();
                        if (data.getJugador().getId() == getJugadorPrimaryStageController().getId()) {//borrar
                            a.get(c.getId()).addCantidad(c.getCantidad());
                        } else {//comprar
                            a.get(c.getId()).removeCantidad(c.getCantidad());
                            a.get(b.getId()).addCantidad(b.getCantidad());
                        }
                        Comercio.data.remove(data);
                        recursosMenu(flowPaneRecuros, CiudadController.class);
                    });
                }

                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && !empty) {
                        hBox.getChildren().clear();
                        Comercio comercio = getTableView().getItems().get(getIndex());
                        if (comercio.getJugador() != getJugadorPrimaryStageController()) {
                            Recursos sePide = comercio.getQueSePide();
                            if (getCiudadPrimaryStageController().getRecursosTreeMap().get(sePide.getId()).getCantidad() > sePide.getCantidad()) {
                                btn.setText("Comprar");
                            } else {
                                btn.setText("insuficiente");
                                btn.setDisable(true);
                            }
                        } else {
                            btn.setText("Borrar");
                            btn.setStyle("-fx-border-color:red; -fx-border-width: 2px;-fx-text-fill:red;-fx-border-radius: 2pt;-fx-background-radius: 2pt;");
                        }
                        hBox.getChildren().add(btn);

                    }
                }
            };
            cell.setGraphic(hBox);
            return cell;
        });
        boton.setCellValueFactory(new PropertyValueFactory<Comercio, Integer>("id"));

//FIN COLUMNAS
        comercioTableView.getColumns().addAll(clan, recursoOferta, recursoDemanda, hora, boton);
        comercioTableView.setItems(Comercio.data);

//FIN TABLA
        scrollPane.setContent(comercioTableView);
        childrenVBox.add(scrollPane);
        Separator separator = new Separator();
        separator.setPrefWidth(220);
        childrenVBox.add(separator);
        //FIN BLOQUE
        return vBoxBloquePropio;
    }

    private static VBox cajaCrearUnidades(Map<Integer, UnidadesPreCargadas> listaUnidades, int tipoDeUnidades, BorderPane borderPane, FlowPane flowPaneRecuros, int tamanoMenu) {
        //BLOQUE
        VBox vBoxBloquePropio = new VBox();
        vBoxBloquePropio.setMinWidth(tamanoMenu);
        vBoxBloquePropio.setMaxWidth(tamanoMenu);
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
        //vBox.setMargin(descripcionEdificioPropio, new Insets(0, 15, 0, 15));

        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setAlignment(Pos.CENTER);
        ObservableList<Node> childrenFlowPane = flowPane.getChildren();

        Map<Integer, Recursos> recursosCiudadTemp = new TreeMap<>();
        Map<Integer, Recursos> resta = new TreeMap<>();

        Map<Integer, Unidades> soldadesca = new TreeMap<>();
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

        printRecursosRestando(vBox.getChildren(), getCiudadPrimaryStageController().getRecursosTreeMap(), resta, recursosCiudadTemp, 7);

        Button button = new Button("Entrenar");
        button.setOnMouseClicked(e -> {

            Ciudad esta = getCiudadPrimaryStageController();
            Batallon batallon = new Batallon("pepito", esta.getFila(), esta.getColumna(), 0, getJugadorPrimaryStageController(), esta);
            for (Unidades unidadesEntry : soldadesca.values()) {
                if (unidadesEntry.getCantidad() > 0) {
                    batallon.setSoldadoHashMap(unidadesEntry);
                }
            }

            for (Recursos recursos : getCiudadPrimaryStageController().getRecursosTreeMap().values()) {
                recursos.removeCantidad(resta.get(recursos.getId()).getCantidad());
            }

            borderPane.setLeft(null);
            recursosMenu(flowPaneRecuros, CiudadController.class);

            CustomAlert alert = new CustomAlert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Unidades preparadas/entrenadas!");
            alert.showAndWait();
        });
        //FIN ESTO ES PARA QUE ENTRE EN EL SISTEMA
        //Buscar batallones en esta posicion
        String positionCiudad = getCiudadPrimaryStageController().getPosition();
        Map<Integer, Integer> listaBatallonesSumandoSoldados = new TreeMap<>();
        for (Batallon batallon : getJugadorPrimaryStageController().listaBatallonesPropios.values()) {
            if (batallon.getPosition().equals(positionCiudad)) {
                for (Unidades s : batallon.getSoldadoHashMap().values()) {
                    if (listaBatallonesSumandoSoldados.get(s.getTipeUnit()) == null) {
                        listaBatallonesSumandoSoldados.put(s.getTipeUnit(), s.getCantidad());
                    } else {
                        listaBatallonesSumandoSoldados.put(s.getTipeUnit(), (listaBatallonesSumandoSoldados.get(s.getTipeUnit()) + s.getCantidad()));
                    }
                }
            }
        }
        //FIN BUSCAR BATALLONES
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


                int conters;
                Integer x = listaBatallonesSumandoSoldados.get(unidadesPreCargadas.getIdType());
                if (x == null) {
                    conters = 0;
                } else {
                    conters = x;
                }
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

                FlowPane flowPaneCostes = new FlowPane();
                flowPaneCostes.setMaxWidth(280);
                //flowPaneCostes.setPadding(new Insets(20));
                flowPaneCostes.setHgap(15);
                flowPaneCostes.setAlignment(Pos.CENTER);
                ObservableList<Node> a = flowPaneCostes.getChildren();
                for (Recursos coste : unidadesPreCargadas.getCostes()) {
                    Label label = new Label();
                    label.setText(String.valueOf(coste.getCantidad()));
                    ImageView imageView1 = new ImageView(coste.getImage());
                    imageView1.setFitWidth(25);
                    imageView1.setFitHeight(25);
                    label.setGraphic(imageView1);
                    a.add(label);
                }
                childrenFlowPane.add(flowPaneCostes);
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


    private static synchronized void controllerSlider(int seleccionadoNumber, UnidadesPreCargadas unidadesPreCargadas, Map<Integer, Unidades> soldadesca, List<Recursos> costesRecursosUnidades, Map<Integer, Recursos> resta, Map<Integer, Recursos> recursosCiudadTemp, Button button, VBox flowPane2, VBox flowPane3) {
        boolean controladora = false;
        boolean controladoraToFor = false;
        int conversorAPositivo = (seleccionadoNumber < 0 ? -seleccionadoNumber : seleccionadoNumber);
        if (unidadesPreCargadas != null) {
            int idQueEs = unidadesPreCargadas.getIdType();
            Unidades unidades = soldadesca.get(idQueEs);
            if (seleccionadoNumber < 0) {
                unidades.addCantidad(seleccionadoNumber);
                controladoraToFor = true;
            } else {
                unidades.removeCantidad(seleccionadoNumber);
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
        printRecursosRestando(flowPane2.getChildren(), getCiudadPrimaryStageController().getRecursosTreeMap(), resta, recursosCiudadTemp, 8);
    }


    private synchronized static void printRecursosRestando(ObservableList<Node> childrenFlowPane, Map<Integer, Recursos> recursosEnLaCIty, Map<Integer, Recursos> recursosResta, Map<Integer, Recursos> recursosCiudadTemp, int produce_Almacena_Cuesta_Devolucion_Resto_cambio) {

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

        Map<Integer, Recursos> recursosAProducir = edificioprecargado.getRecursosProductores();


        if (recursosAProducir != null) {
            vBox.add(new CustomSeparator(220, true));
            Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXmin = edificioprecargado.getRecursosCosteXmin();
            if (recursosCosteXmin == null) {
                int produce_Almacena_Cuesta_Devolucion_Resto_cambio = 1;
                printTodosLosRecursosEdificioSegunTipo(vBox, recursosAProducir, produce_Almacena_Cuesta_Devolucion_Resto_cambio, null);
            } else {
                vBox.add(new Label(nombreEtiquetas(1)));
                Map<Integer, Recursos> trabajadoresEnEdificio = edificioSlider.getTrabajadoresNecesarios();
                FlowPane flowPane = new FlowPane();
                flowPane.setHgap(10);
                flowPane.setVgap(10);
                flowPane.setAlignment(Pos.CENTER);
                for (Recursos recursosPoducidosMax : recursosAProducir.values()) {
                    RecursosPrecargados poducidoMax = recursosPoducidosMax.getRecursosPrecargados();
                    if (recursosCosteXmin.containsKey(poducidoMax)) {
                        VBox vBox1 = new VBox();
                        HBox hBox = new HBox();
                        hBox.setSpacing(10);
                        hBox.setAlignment(Pos.CENTER);
                        ImageView imageView = new ImageView(poducidoMax.getImage());
                        imageView.setFitWidth(25);
                        imageView.setFitHeight(25);
                        hBox.getChildren().add(imageView);
                        //ArrayList<Recursos> costesPorProducido=recursosCosteXmin.get(i);
                        //String variableDondeGuardamosCantidad=recursosCosteXmin.get(recursosPoducidosMax);

                        //recursosCosteXmin.get(i)
                        FlowPane flowPane1 = new FlowPane();
                        int tope = trabajadoresEnEdificio.get(poducidoMax.getId()).getCantidad();

                        int maxDivisor = Integer.MAX_VALUE;
                        for (Recursos recursos : recursosCosteXmin.get(poducidoMax)) {
                            int enCiudadR = getCiudadPrimaryStageController().getRecursosTreeMap().get(recursos.getId()).getCantidad() / recursos.getCantidad();
                            if (maxDivisor > enCiudadR) {
                                maxDivisor = enCiudadR;
                            }
                        }
                        int maximosQueSePuedenPoner = recursosPoducidosMax.getCantidad();
                        if (maximosQueSePuedenPoner > maxDivisor) {
                            maximosQueSePuedenPoner = maxDivisor;
                        }

                        CustomTextField textField = new CustomTextField(String.valueOf(tope), true, maximosQueSePuedenPoner);

                        CustomSlider customSlider = new CustomSlider(0, recursosPoducidosMax.getCantidad(), tope);
                        customSlider.setmargin(25, 0, 0, 0);
                        customSlider.valueProperty().addListener(new ChangeListener<Number>() {
                            @Override
                            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                                int seleccionado = newValue.intValue();
                                int number = oldValue.intValue() - seleccionado;
                                if (number != 0) {
                                    textField.textProperty().setValue(String.valueOf(seleccionado));
                                    Platform.runLater(() -> delSliderEdificiosConRecursos(recursosCosteXmin, poducidoMax, flowPane1, number, edificioSlider, flowPaneRecuros, getCiudadPrimaryStageController().getRecursosTreeMap(), borderPane));
                                }
                            }
                        });
                        textField.setBindSlider(customSlider);
                        hBox.getChildren().add(customSlider);
                        hBox.getChildren().add(textField);
                        vBox1.getChildren().add(hBox);
                        //METODO
                        delSliderEdificiosConRecursos(recursosCosteXmin, poducidoMax, flowPane1, 0, edificioSlider, flowPaneRecuros, getCiudadPrimaryStageController().getRecursosTreeMap(), borderPane);
                        vBox1.getChildren().add(flowPane1);
                        //METODO
                        vBox.add(vBox1);
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
                    vBox.add(new CustomSeparator(220, false));
                    vBox.add(flowPane);
                }
            }

        }
        Map<Integer, Recursos> d = edificioprecargado.getRecursosAlmacen();
        if (d != null) {
            int produce_Almacena_Cuesta_Devolucion_Resto_cambio = 2;
            vBox.add(new CustomSeparator(220, true));
            printTodosLosRecursosEdificioSegunTipo(vBox, d, produce_Almacena_Cuesta_Devolucion_Resto_cambio, null);
        }

        vBox.add(new CustomSeparator(220, true));

    }

    private static void delSliderEdificiosConRecursos(Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXmin, RecursosPrecargados poducidoMax, FlowPane flowPane, int number, Edificio edificio, FlowPane flowPaneRecuros, Map<Integer, Recursos> recursosDeLaCiudad, BorderPane borderPane) {
        Recursos multiplicador = edificio.getTrabajadoresNecesarios().get(poducidoMax.getId());
        ArrayList<Recursos> recursosXMin = recursosCosteXmin.get(poducidoMax);

        if (number < 0) {
            int elnumerito = Math.abs(number);
            multiplicador.addCantidad(elnumerito);
            for (Recursos recursoxMin : recursosXMin) {
                if (!recursoxMin.getRecursosPrecargados().isSeConsumeEnEdificios()) {
                    int aBuscar = recursoxMin.getId();
                    int calculo = elnumerito * recursoxMin.getCantidad();
                    if (recursosDeLaCiudad.get(aBuscar).getCantidad() - calculo < 0) {
                        System.err.println("No se puede tener menos de 0 -> " + recursosDeLaCiudad.get(aBuscar));
                        multiplicador.removeCantidad(elnumerito);
                        borderPane.setLeft(null);
                        break;
                    }
                    recursosDeLaCiudad.get(aBuscar).removeCantidad(calculo);
                }
            }
        } else if (number > 0) {
            multiplicador.removeCantidad(number);
            for (Recursos recursoxMin : recursosXMin) {
                if (!recursoxMin.getRecursosPrecargados().isSeConsumeEnEdificios()) {
                    recursosDeLaCiudad.get(recursoxMin.getId()).addCantidad(number * recursoxMin.getCantidad());
                }
            }
        }
        int multi = multiplicador.getCantidad();
        if (multi == 0) {
            multi = 1;
        }

        //FIN CALCULOS
        flowPane.getChildren().clear();
        int f = recursosXMin.size();
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setHgap(15);
        flowPane.setHgap(15);
        for (int i = 0; i < f; i++) {
            Recursos ff = recursosXMin.get(i);
            ImageView imageView2 = new ImageView(ff.getImage());
            imageView2.setFitWidth(25);
            imageView2.setFitHeight(25);
            Label label = new Label("-" + (ff.getCantidad() * multi));
            label.setTextFill(Color.RED);
            label.setGraphic(imageView2);
            flowPane.getChildren().add(label);
        }
        recursosMenu(flowPaneRecuros, CiudadController.class);
    }

    private static void printEdificioRecursos(ObservableList<Node> vBox, EdificiosPreCargados edificioprecargado, int construir_Update_Dowgrade_Destruir, boolean construirTrueDestruirFalse) {
        Map<Integer, Recursos> b = edificioprecargado.getRecursosProductores();
        Map<RecursosPrecargados, ArrayList<Recursos>> c = edificioprecargado.getRecursosCosteXmin();
        Map<Integer, Recursos> d = edificioprecargado.getRecursosAlmacen();
        Map<Integer, Recursos> a = edificioprecargado.getRecursosBuild();

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
            int produce_Almacena_Cuesta_Devolucion_Resto_cambio;
            if (construirTrueDestruirFalse) {
                produce_Almacena_Cuesta_Devolucion_Resto_cambio = 3;
                System.out.println("3");
            } else {
                produce_Almacena_Cuesta_Devolucion_Resto_cambio = 9;
                System.out.println("9");
            }
            vBox.add(new CustomSeparator(220, true));
            printTodosLosRecursosEdificioSegunTipo(vBox, a, produce_Almacena_Cuesta_Devolucion_Resto_cambio, construir_Update_Dowgrade_Destruir);
        }
        vBox.add(new CustomSeparator(220, true));
    }

    private static void printTodosLosRecursosEdificioSegunTipo(ObservableList<Node> vBox, Map<RecursosPrecargados, ArrayList<Recursos>> a, int produce_Almacena_Cuesta_Devolucion_Resto_cambio, boolean daIgualLoQuePongas) {
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

    private static void printTodosLosRecursosEdificioSegunTipo(ObservableList<Node> vBox, Map<Integer, Recursos> a, int produce_Almacena_Cuesta_Devolucion_Resto_cambio, Integer construir_Update_Dowgrade_Destruir) {
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
                    case 9:
                        if (construir_Update_Dowgrade_Destruir != null && construir_Update_Dowgrade_Destruir > 2) {
                            label46.setText("+" + (numero * PORCENTAGE_A_DEVOLVER / 100));
                            label46.setTextFill(Color.GREEN);
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
            case 7:
                return "Entrenar:";
            case 8:
                return "Costes del entrenamiento:";
            case 9:
                return "Coste de destrucción:";
            default:
                return "Error";
        }
    }

    public void toMundo(MouseEvent mouseEvent) {
        reload(MundoController.class);
    }
}

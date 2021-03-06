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
import javafx.util.Callback;
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
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.*;

import static javafx.geometry.Pos.TOP_CENTER;
import static javafx.scene.text.TextAlignment.CENTER;
import static main.java.Inicio.PantallaInicialController.elTemaSeleccionado;
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
                    vBoxList.add(cajaEdificio(edificioPuesto, imageView, false, elTemaSeleccionado.listaEdificiosPreCargados.get(edificioId + "_" + (edificioNivel + 1)), 2));
                } catch (Exception e) {
                }
                vBoxList.add(cajaEdificio(edificioPuesto, imageView, false, elTemaSeleccionado.listaEdificiosPreCargados.get(edificioId + "_" + (edificioNivel - 1)), 3));
            } else {
                try {
                    vBoxList.add(cajaEdificio(edificioPuesto, imageView, false, elTemaSeleccionado.listaEdificiosPreCargados.get(edificioId + "_" + (edificioNivel + 1)), 2));
                } catch (Exception e) {
                }
                if (edificioPuesto.getEdificiosPreCargado().isDestruible()) {
                    vBoxList.add(cajaEdificio(edificioPuesto, imageView, false, elTemaSeleccionado.listaEdificiosPreCargados.get(0 + "_" + 0), 4));
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
            for (Map.Entry<String, EdificiosPreCargados> preCargadosEntry : elTemaSeleccionado.listaEdificiosPreCargados.entrySet()) {
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
            Label nivelEdificioPropio = new Label(TRADUCCIONES_THEMA.getString("ciudad.nivelEdificio") + " " + edificioPosiblesConstrucciones.getNivel());
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
                printEdificioRecursos(vBoxChildren, edificioPosiblesConstrucciones, construir_Update_Dowgrade_Destruir, construir_Update_Dowgrade_Destruir < 3);
            }

            //VBox.setMargin(flowPane, new Insets(0, 15, 0, 15));
            childrenVBox.add(vBox);
            if (construir_Update_Dowgrade_Destruir != 0) {
                if (construir_Update_Dowgrade_Destruir == 4) {//Si es el edificio que tenemos en el mapa
                    Label label = new Label(TRADUCCIONES_THEMA.getString("ciudad.seDevolvera.Parte1") + " " + PORCENTAGE_A_DEVOLVER + TRADUCCIONES_THEMA.getString("ciudad.seDevolvera.Parte2"));
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
                text = TRADUCCIONES_THEMA.getString("ciudad.construir");
                break;
            case 2:
                text = TRADUCCIONES_THEMA.getString("ciudad.update");
                break;
            case 3:
                text = TRADUCCIONES_THEMA.getString("ciudad.downgrade");
                break;
            case 4:
                text = TRADUCCIONES_THEMA.getString("ciudad.destruir");
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
                buttonCase1.setText(TRADUCCIONES_THEMA.getString("ciudad.new.ciudad"));
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

                vBox.getChildren().add(new CustomSeparator(20, false, 5));

                Button buttonCase1Clan = new Button();
                buttonCase1Clan.setText(TRADUCCIONES_THEMA.getString("city.button.clan"));
                buttonCase1Clan.setOnMouseClicked(e -> {
                    createMenuLeftSecond(borderPane, flowPaneRecuros, 1);
                });
                vBox.getChildren().add(buttonCase1Clan);
                break;
            case 2://CUARTEL (SOLDADOS)
                Button buttonCase2 = new Button();
                buttonCase2.setText(TRADUCCIONES_THEMA.getString("city.button.entrenarNuevasUnidades"));
                buttonCase2.setOnMouseClicked(e -> {
                    createMenuLeftSecond(borderPane, flowPaneRecuros, 2);
                });
                vBox.getChildren().add(buttonCase2);
                break;
            case 3://ARMAS DE ASEDIO (SOLDADOS)
                Button buttonCase3 = new Button();
                buttonCase3.setText(TRADUCCIONES_THEMA.getString("city.button.construirMaquinariaAsedio"));
                buttonCase3.setOnMouseClicked(e -> {
                    createMenuLeftSecond(borderPane, flowPaneRecuros, 3);
                });
                vBox.getChildren().add(buttonCase3);
                break;
            case 4://PUERTO
                Button buttonCase4 = new Button();
                buttonCase4.setText(TRADUCCIONES_THEMA.getString("ciudad.comercio.comerciar"));
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
            case 1:
                tamanoMenu = 500;
                vBoxList.add(cajaClanes(borderPane, flowPaneRecuros, tamanoMenu));
                if (getClanPrimaryStageController() != null) {
                    vBoxList.add(cajaTuClan(borderPane, flowPaneRecuros, tamanoMenu));
                }
                break;
            case 2:
                tamanoMenu = 300;
                vBoxList.add(cajaCrearUnidades(elTemaSeleccionado.listaSoldadosPreCargada, 0, borderPane, flowPaneRecuros, tamanoMenu));
                break;
            case 3:
                tamanoMenu = 300;
                vBoxList.add(cajaCrearUnidades(elTemaSeleccionado.listaSoldadosPreCargada, 5, borderPane, flowPaneRecuros, tamanoMenu));
                break;
            case 4:
                tamanoMenu = 650;
                vBoxList.add(cajaCrearComercio(borderPane, flowPaneRecuros, tamanoMenu));
                break;
        }
        rellenador(borderPane, vBoxList, tamanoMenu);
    }

    private static VBox cajaClanes(BorderPane borderPane, FlowPane flowPaneRecuros, int tamanoMenu) {
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

        Label nombreEdificioPropio = new Label(TRADUCCIONES_THEMA.getString("clan.label.clan"));
        nombreEdificioPropio.setTextAlignment(CENTER);
        nombreEdificioPropio.setAlignment(Pos.CENTER);
        nombreEdificioPropio.setWrapText(true);
        childrenVBox.add(nombreEdificioPropio);

        childrenVBox.add(new CustomSeparator((int) (flowPaneRecuros.getHeight() * 0.8), false));
//AÑADIR
        TextField textField = new TextField();
        textField.setPromptText(TRADUCCIONES_THEMA.getString("clan.label.nombre"));
        textField.setMaxWidth(150);
        textField.setAlignment(Pos.CENTER);
        CustomSeparator separator3 = new CustomSeparator(20, false);
        TextField textFieldPassword = new TextField();
        textFieldPassword.setPromptText(TRADUCCIONES_THEMA.getString("clan.label.password"));
        textFieldPassword.setMaxWidth(150);
        textFieldPassword.setAlignment(Pos.CENTER);
        //
        CustomTextField textFieldSlider = new CustomTextField("0", true, 1000);
        CustomSlider slider = new CustomSlider(0, 1000, 0);
        slider.setmargin(25, 0, 0, 0);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int seleccionado = newValue.intValue();
                int number = oldValue.intValue() - seleccionado;
                if (number != 0) {
                    textFieldSlider.textProperty().setValue(String.valueOf(seleccionado));
                }
            }
        });
        textFieldSlider.setBindSlider(slider);
        CustomSeparator separator4 = new CustomSeparator(20, false);
        Button button = new Button(TRADUCCIONES_THEMA.getString("clan.label.fundar"));
        button.setDisable(true);
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String newValueTrim = newValue.trim();
            String newValueAltered = newValueTrim.toLowerCase();
            if (newValueTrim.length() < 5) {
                button.setDisable(true);
            } else {
                Recursos recursos = getCiudadPrimaryStageController().getRecursosTreeMap().get(0);
                if (recursos.getCantidad() >= Clan.costeCrear) {
                    boolean existeElClan = false;
                    for (Clan clan : Clan.clanArrayList) {
                        if (newValueAltered.equals(clan.getNameString().toLowerCase())) {
                            existeElClan = true;
                            break;
                        }
                    }
                    if (!existeElClan) {
                        button.setDisable(false);
                        button.setOnMouseClicked(e -> {
                            Clan clan = new Clan(newValueTrim, (int) slider.getValue());
                            clan.setCreador(getJugadorPrimaryStageController());
                            String contrasenya = textFieldPassword.getText().trim();
                            if (contrasenya.length() > 0) {
                                clan.setContrasenya(contrasenya);
                            }
                            unirseClan(recursos, clan, Clan.costeCrear);
                            reload(CiudadController.class);
                        });
                    } else {
                        button.setDisable(true);
                    }
                } else {
                    button.setDisable(true);
                }
            }
        });
        HBox hBox = new HBox(textField, separator3, textFieldPassword);
        hBox.setAlignment(Pos.CENTER);
        childrenVBox.add(hBox);
        HBox hBoxSlider = new HBox(slider, textFieldSlider, separator4, button);
        hBoxSlider.setAlignment(Pos.CENTER);
        childrenVBox.add(hBoxSlider);
        childrenVBox.add(new CustomSeparator((int) (flowPaneRecuros.getHeight() * 0.8), false));
//INICIO TABLA
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPannable(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//TABLA
        TableView<Clan> clanesTableView = new TableView<>();
        clanesTableView.setPrefHeight(300);
        clanesTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//INCIO COLUMNAS
        //columna
        TableColumn clanName = new TableColumn();
        clanName.setMinWidth(120);
        clanName.setStyle("-fx-alignment: CENTER;");
        clanName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Clan, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Clan, String> param) {
                return param.getValue().getName();
            }
        });
        //columna
        TableColumn clanJugadores = new TableColumn(TRADUCCIONES_THEMA.getString("clan.tableView.miembros"));
        clanJugadores.setMinWidth(80);
        clanJugadores.setStyle("-fx-alignment: CENTER;");
        clanJugadores.setCellFactory(param -> {
            Label labeJugador = new Label();
            TableCell<Clan, Integer> cell = new TableCell<Clan, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && !empty) {
                        Clan comercio = getTableView().getItems().get(getIndex());
                        labeJugador.setText(String.valueOf(comercio.getJugadoresDelClan().size()));
                    }
                }
            };
            cell.setGraphic(labeJugador);
            return cell;
        });
        clanJugadores.setCellValueFactory(new PropertyValueFactory<Clan, Integer>("id"));
        //columna
        TableColumn clanCiudades = new TableColumn(TRADUCCIONES_THEMA.getString("clan.tableView.ciudades"));
        clanCiudades.setMinWidth(80);
        clanCiudades.setStyle("-fx-alignment: CENTER;");
        clanCiudades.setCellFactory(param -> {
            Label labelname = new Label();
            TableCell<Clan, Integer> cell = new TableCell<Clan, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && !empty) {
                        Clan comercio = getTableView().getItems().get(getIndex());
                        labelname.setText(String.valueOf(comercio.getCiudadesDelClan().size()));
                    }
                }
            };
            cell.setGraphic(labelname);
            return cell;
        });
        clanCiudades.setCellValueFactory(new PropertyValueFactory<Clan, Integer>("id"));
        //columna
        TableColumn clanBatallones = new TableColumn(TRADUCCIONES_THEMA.getString("clan.tableView.batallones"));
        clanBatallones.setMinWidth(80);
        clanBatallones.setStyle("-fx-alignment: CENTER;");
        clanBatallones.setCellFactory(param -> {
            Label labelBatallon = new Label();
            TableCell<Clan, Integer> cell = new TableCell<Clan, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && !empty) {
                        Clan comercio = getTableView().getItems().get(getIndex());
                        labelBatallon.setText(String.valueOf(comercio.getBatallonesDelClan().size()));
                    }
                }
            };
            cell.setGraphic(labelBatallon);
            return cell;
        });
        clanBatallones.setCellValueFactory(new PropertyValueFactory<Clan, Integer>("id"));
        //columna
        TableColumn clanboton = new TableColumn(TRADUCCIONES_THEMA.getString("clan.tableView.column.opciones"));
        clanboton.setMinWidth(Clan.costeCrear);
        clanboton.setCellFactory(param -> {
            HBox hBox2 = new HBox();
            hBox2.setAlignment(Pos.CENTER);
            Button btn = new Button();
            TableCell<Clan, Integer> cell = new TableCell<Clan, Integer>() {
                {
                    btn.setOnAction((ActionEvent event) -> {
                        Clan unirseAunClan = getTableView().getItems().get(getIndex());
                        try {
                            unirseAunClan.getContrasenya();
                            TextInputDialog dialog = new TextInputDialog();
                            dialog.setTitle(TRADUCCIONES_THEMA.getString("clan.label.alertDialog.contrasena"));
                            dialog.setHeaderText(TRADUCCIONES_THEMA.getString("clan.label.alertDialog.contrasena.header"));
                            dialog.setContentText(TRADUCCIONES_THEMA.getString("clan.label.alertDialog.contrasena.text"));
                            // Traditional way to get the response value.
                            Optional<String> result = dialog.showAndWait();
                            if (result.isPresent()) {
                                if (unirseAunClan.getContrasenya().equals(result.get())) {
                                    unirseClan(clanesTableView, getCiudadPrimaryStageController().getRecursosTreeMap().get(0), unirseAunClan, (unirseAunClan.getCoste() + Clan.costeBaseUnirse));
                                    for (Ciudad value : unirseAunClan.getCreador().listaCiudadesPropias.values()) {
                                        value.getRecursosTreeMap().get(0).addCantidad(unirseAunClan.getCoste());
                                        break;
                                    }
                                } else {
                                    CustomAlert customAlert = new CustomAlert(Alert.AlertType.ERROR, TRADUCCIONES_THEMA.getString("clan.label.alert.error"));
                                    customAlert.showAndWait();
                                }
                            }
                        } catch (NullPointerException ignore) {
                            unirseClan(clanesTableView, getCiudadPrimaryStageController().getRecursosTreeMap().get(0), unirseAunClan, (unirseAunClan.getCoste() + Clan.costeBaseUnirse));
                            try {
                                for (Ciudad value : unirseAunClan.getCreador().listaCiudadesPropias.values()) {
                                    value.getRecursosTreeMap().get(0).addCantidad(unirseAunClan.getCoste());
                                    break;
                                }
                            } catch (NullPointerException ignore2) {
                            }
                            reload(CiudadController.class);
                        }
                    });
                }

                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && !empty) {
                        hBox2.getChildren().clear();
                        Clan comercio = getTableView().getItems().get(getIndex());

                        btn.setDisable(getCiudadPrimaryStageController().getRecursosTreeMap().get(0).getCantidad() <= Clan.costeBaseUnirse);
                        if (comercio.equals(getClanPrimaryStageController())) {
                            btn.setDisable(true);
                            btn.setText("-" + comercio.getCoste());
                        } else {
                            btn.setText("-" + (comercio.getCoste() + Clan.costeBaseUnirse));
                        }
                        btn.setGraphic(new CustomImageView(elTemaSeleccionado.listaRecursosPreCargada.get(0).getImage(), 20, 20));
                        hBox2.getChildren().add(btn);

                    }
                }
            };
            cell.setGraphic(hBox2);
            return cell;
        });
        clanboton.setCellValueFactory(new PropertyValueFactory<Clan, Integer>("id"));
//FIN COLUMNAS
        clanesTableView.getColumns().addAll(clanName, clanJugadores, clanCiudades, clanBatallones, clanboton);
        clanesTableView.setItems(Clan.clanArrayList);
        scrollPane.setContent(clanesTableView);
//FIN TABLA
        Separator separator = new Separator();
        separator.setPrefWidth(220);
        childrenVBox.add(scrollPane);
        childrenVBox.add(separator);
        //FIN BLOQUE
        return vBoxBloquePropio;
    }

    private static void unirseClan(Recursos recursos, Clan clan, int costeEnOro) {
        unirseClan2(recursos, clan, costeEnOro);
        reload(CiudadController.class);
    }

    private static void unirseClan2(Recursos recursos, Clan clan, int costeEnOro) {
        Clan clanOld = getClanPrimaryStageController();
        if (clanOld != null) {
            clanOld.removeJugadorClan(getJugadorPrimaryStageController());
            if (clanOld.getJugadoresDelClan().size() < 1) {
                Clan.clanArrayList.remove(clanOld);
            }
        }
        clan.addJugadorClan(getJugadorPrimaryStageController());
        setClanPrimaryStageController(clan);
        recursos.removeCantidad(costeEnOro);
        CustomAlert customAlert = new CustomAlert(Alert.AlertType.INFORMATION, TRADUCCIONES_THEMA.getString("clan.alert.seUnioAunClan") + " \"" + clan.getNameString() + "\"");
        customAlert.showAndWait();
    }

    private static void unirseClan(TableView<Clan> clanesTableView, Recursos recursos, Clan clan, int costeEnOro) {
        unirseClan2(recursos, clan, costeEnOro);
        clanesTableView.refresh();
    }

    private static VBox cajaTuClan(BorderPane borderPane, FlowPane flowPaneRecuros, int tamanoMenu) {
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

        Label nombreEdificioPropio = new Label(TRADUCCIONES_THEMA.getString("clan.tableView.miembros.label") + " " + getClanPrimaryStageController().getNameString());
        nombreEdificioPropio.setTextAlignment(CENTER);
        nombreEdificioPropio.setAlignment(Pos.CENTER);
        nombreEdificioPropio.setWrapText(true);
        childrenVBox.add(nombreEdificioPropio);

        childrenVBox.add(new CustomSeparator((int) (flowPaneRecuros.getHeight() * 0.8), false));
//INICIO TABLA
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPannable(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//TABLA
        TableView<Jugador> jugadorDelClanTableView = new TableView<>();
        jugadorDelClanTableView.setPrefHeight(300);
        jugadorDelClanTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//INCIO COLUMNAS
        //columna
        TableColumn jugadorClanName = new TableColumn(TRADUCCIONES_THEMA.getString("clan.tableView.miembros.jugador"));
        jugadorClanName.setMinWidth(120);
        jugadorClanName.setStyle("-fx-alignment: CENTER;");
        jugadorClanName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Jugador, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Jugador, String> param) {
                return param.getValue().nombreProperty();
            }
        });
        //columna
        TableColumn jugadorClanCiudades = new TableColumn(TRADUCCIONES_THEMA.getString("clan.tableView.ciudades"));
        jugadorClanCiudades.setMinWidth(80);
        jugadorClanCiudades.setStyle("-fx-alignment: CENTER;");
        jugadorClanCiudades.setCellFactory(param -> {
            Label labeJugador = new Label();
            TableCell<Jugador, Long> cell = new TableCell<Jugador, Long>() {
                @Override
                protected void updateItem(Long item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && !empty) {
                        Jugador comercio = getTableView().getItems().get(getIndex());
                        labeJugador.setText(String.valueOf(comercio.listaCiudadesPropias.size()));
                    }
                }
            };
            cell.setGraphic(labeJugador);
            return cell;
        });
        jugadorClanCiudades.setCellValueFactory(new PropertyValueFactory<Clan, Integer>("id"));
        //columna
        TableColumn jugadorClanBatallones = new TableColumn(TRADUCCIONES_THEMA.getString("clan.tableView.batallones"));
        jugadorClanBatallones.setMinWidth(80);
        jugadorClanBatallones.setStyle("-fx-alignment: CENTER;");
        jugadorClanBatallones.setCellFactory(param -> {
            Label labelBatallon = new Label();
            TableCell<Jugador, Long> cell = new TableCell<Jugador, Long>() {
                @Override
                protected void updateItem(Long item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && !empty) {
                        Jugador comercio = getTableView().getItems().get(getIndex());
                        labelBatallon.setText(String.valueOf(comercio.listaBatallonesPropios.size()));
                    }
                }
            };
            cell.setGraphic(labelBatallon);
            return cell;
        });
        jugadorClanBatallones.setCellValueFactory(new PropertyValueFactory<Clan, Integer>("id"));
        //columna
        TableColumn jugadorClanboton = new TableColumn(TRADUCCIONES_THEMA.getString("clan.tableView.column.opciones"));
        jugadorClanboton.setMinWidth(100);
        jugadorClanboton.setCellFactory(param -> {
            HBox hBox2 = new HBox();
            hBox2.setAlignment(Pos.CENTER);
            Button btn = new Button();
            TableCell<Jugador, Long> cell = new TableCell<Jugador, Long>() {
                {
                    btn.setOnAction((ActionEvent event) -> {

                            Jugador unirseAunClan = getTableView().getItems().get(getIndex());
                            getClanPrimaryStageController().removeJugadorClan(unirseAunClan);
                            reload(this.getClass());
                            jugadorDelClanTableView.refresh();
                    });
                }

                @Override
                protected void updateItem(Long item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && !empty) {
                        hBox2.getChildren().clear();
                        if (getClanPrimaryStageController().getCreador().equals(getJugadorPrimaryStageController())) {
                            Jugador comercio = getTableView().getItems().get(getIndex());
                            if (getJugadorPrimaryStageController().equals(comercio)) {
                                btn.setDisable(true);
                                btn.setText(TRADUCCIONES_THEMA.getString("clan.tableView.jugador.button.noPuedes"));
                            } else {
                                btn.setDisable(false);
                                btn.setText(TRADUCCIONES_THEMA.getString("clan.tableView.jugador.button.hechar"));
                            }
                        } else {
                            btn.setDisable(true);
                            btn.setText(TRADUCCIONES_THEMA.getString("clan.tableView.jugador.button.options"));
                        }
                        hBox2.getChildren().add(btn);

                    }
                }
            };
            cell.setGraphic(hBox2);
            return cell;
        });
        jugadorClanboton.setCellValueFactory(new PropertyValueFactory<Clan, Integer>("id"));
//FIN COLUMNAS
        jugadorDelClanTableView.getColumns().addAll(jugadorClanName, jugadorClanCiudades, jugadorClanBatallones, jugadorClanboton);
        jugadorDelClanTableView.setItems(FXCollections.observableArrayList(new ArrayList<Jugador>(getClanPrimaryStageController().getJugadoresDelClan().values())));
        scrollPane.setContent(jugadorDelClanTableView);
//FIN TABLA
        Separator separator = new Separator();
        separator.setPrefWidth(220);
        childrenVBox.add(scrollPane);
        childrenVBox.add(separator);
        //FIN BLOQUE
        return vBoxBloquePropio;
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

        Label nombreEdificioPropio = new Label(TRADUCCIONES_THEMA.getString("ciudad.comercio.comercio"));
        nombreEdificioPropio.setTextAlignment(CENTER);
        nombreEdificioPropio.setAlignment(Pos.CENTER);
        nombreEdificioPropio.setWrapText(true);
        childrenVBox.add(nombreEdificioPropio);

        childrenVBox.add(new CustomSeparator((int) (flowPaneRecuros.getHeight() * 0.8), false));
//AÑADIR
        Label oferta = new Label(TRADUCCIONES_THEMA.getString("ciudad.comercio.ofrezco"));
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
        CustomSlider slider = new CustomSlider(0, maximoQueSePuedePedir, getClanPrimaryStageController().getCoste());
        slider.setmargin(25, 0, 0, 0);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int seleccionado = newValue.intValue();
                int number = oldValue.intValue() - seleccionado;
                if (number != 0) {
                    textField.textProperty().setValue(String.valueOf(seleccionado));
                    if (getClanPrimaryStageController() != null) {
                        Clan clan = getClanPrimaryStageController();
                        if (clan.getCreador().equals(getJugadorPrimaryStageController())) {
                            clan.setCoste(seleccionado);
                        }
                    }
                }
            }
        });
        textField.setBindSlider(slider);
        combo.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    int x = (getCiudadPrimaryStageController().getRecursosTreeMap().get(Integer.valueOf(newValue.getString())).getCantidad());
                    slider.setMax(x);
                }
        );
        hBoxAddToTableViewChildren.add(slider);
        hBoxAddToTableViewChildren.add(textField);
        childrenVBox.add(hBoxAddToTableView);
//AÑADIR 2
        Label demanda = new Label(TRADUCCIONES_THEMA.getString("ciudad.comercio.pido"));
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

        CustomTextField textField2 = new CustomTextField("0", true, maximoQueSePuedePedir2 + 1000);
        CustomSlider slider2 = new CustomSlider(0, maximoQueSePuedePedir2 + 1000, 0);
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
                    int x = (getCiudadPrimaryStageController().getRecursosTreeMap().get(Integer.valueOf(newValue.getString())).getCantidad());
                    slider2.setMax(x);
                }
        );
        hBoxAddToTableViewChildren2.add(slider2);
        hBoxAddToTableViewChildren2.add(textField2);
        childrenVBox.add(hBoxAddToTableView2);
//AÑADIR BUTTON
        final Button addButton = new Button(TRADUCCIONES_THEMA.getString("ciudad.comercio.crearOferta"));
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int cantidad = (int) slider.getValue();
                int cantidad2 = (int) slider2.getValue();
                if (cantidad > 0 && cantidad2 > 0) {
                    RecursosPrecargados recursosPrecargados = elTemaSeleccionado.listaRecursosPreCargada.get(Integer.valueOf(combo.getSelectionModel().getSelectedItem().getString()));
                    RecursosPrecargados recursosPrecargados2 = elTemaSeleccionado.listaRecursosPreCargada.get(Integer.valueOf(combo2.getSelectionModel().getSelectedItem().getString()));
                    if (recursosPrecargados.getId() != recursosPrecargados2.getId()) {
                        getCiudadPrimaryStageController().getRecursosTreeMap().get(recursosPrecargados.getId()).removeCantidad(cantidad);
                        Comercio.data.add(new Comercio(new Recursos(recursosPrecargados, cantidad), new Recursos(recursosPrecargados2, cantidad2), getJugadorPrimaryStageController()));
                        recursosMenu(flowPaneRecuros, CiudadController.class);
                        CustomAlert alert = new CustomAlert(Alert.AlertType.INFORMATION);
                        alert.setTitle(TRADUCCIONES_GENERALES.getString("information.dialog"));
                        alert.setHeaderText(null);
                        alert.setContentText(TRADUCCIONES_THEMA.getString("ciudad.comercio.ofertaCreada"));
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
        TableColumn<Comercio, Jugador> clan = new TableColumn(TRADUCCIONES_THEMA.getString("ciudad.comercio.usuario"));
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
                            label1.setText(item.getNombreString());
                            label2.setText("(" + clan.getNameString() + " )");
                            vBox.getChildren().addAll(label1, label2);
                            if (clan == Clan.jugadoresQueEstanEnUnClan.get(getJugadorPrimaryStageController())) {
                                setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                            } else {
                                setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                            }
                        } else {
                            label1.setText(item.getNombreString());
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
        TableColumn<Comercio, Recursos> recursoOferta = new TableColumn(TRADUCCIONES_THEMA.getString("ciudad.comercio.oferta"));
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
        TableColumn<Comercio, Recursos> recursoDemanda = new TableColumn(TRADUCCIONES_THEMA.getString("ciudad.comercio.requiere"));
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
        TableColumn<Comercio, LocalDateTime> hora = new TableColumn(TRADUCCIONES_THEMA.getString("ciudad.comercio.finaliza"));
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
        TableColumn<Comercio, Integer> boton = new TableColumn(TRADUCCIONES_THEMA.getString("ciudad.comercio.options"));
        boton.setMinWidth(100);
        boton.setCellFactory(param -> {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            Button btn = new Button();
            TableCell<Comercio, Integer> cell = new TableCell<Comercio, Integer>() {
                {
                    btn.setOnAction((ActionEvent event) -> {
                        Comercio data = getTableView().getItems().get(getIndex());
                        Map<Integer, Recursos> recursosTreeMap = getCiudadPrimaryStageController().getRecursosTreeMap();
                        Recursos queSePide = data.getQueSePide();
                        Recursos loQueTeDan = data.getQueSeOfrece();

                        if (data.getJugador().getId() == getJugadorPrimaryStageController().getId()) {//borrar

                            recursosTreeMap.get(loQueTeDan.getId()).addCantidad(loQueTeDan.getCantidad());

                        } else {//comprar
                            int almacenado = 0;
                            int loQueTeDanId = loQueTeDan.getId();
                            for (Edificio edificio : getCiudadPrimaryStageController().getListaPosicionesEdificios().values()) {
                                try {
                                    almacenado += edificio.getEdificiosPreCargado().getRecursosAlmacen().get(loQueTeDanId).getCantidad();
                                } catch (NullPointerException ignore) {
                                }
                            }
                            Recursos recursosDeLaCiudad = recursosTreeMap.get(loQueTeDanId);
                            int restado = almacenado - recursosDeLaCiudad.getCantidad();
                            int aSumar = loQueTeDan.getCantidad();
                            //System.out.println("R" + restado + " S" + aSumar);
                            if (restado > aSumar) {
                                recursosDeLaCiudad.addCantidad(aSumar);
                            } else {
                                if (restado > 0) {
                                    recursosDeLaCiudad.addCantidad(restado);
                                }
                            }
                            //recursosTreeMap.get(loQueTeDan.getId()).addCantidad(loQueTeDan.getCantidad());
                            recursosTreeMap.get(queSePide.getId()).removeCantidad(queSePide.getCantidad());
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
                                btn.setText(TRADUCCIONES_THEMA.getString("ciudad.comercio.button.comprar"));
                            } else {
                                btn.setText(TRADUCCIONES_THEMA.getString("ciudad.comercio.button.insuficiente"));
                                btn.setDisable(true);
                            }
                        } else {
                            btn.setText(TRADUCCIONES_THEMA.getString("ciudad.comercio.button.borrar"));
                            btn.setStyle("-fx-border-color:red; -fx-border-width: 2px;-fx-text-background-color:red;-fx-border-radius: 2pt;-fx-background-radius: 2pt;");
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
        scrollPane.setContent(comercioTableView);
//FIN TABLA
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

        Label nombreEdificioPropio = new Label(TRADUCCIONES_THEMA.getString("ciudad.unidades.entrenarUnidades"));
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

        Button button = new Button(TRADUCCIONES_THEMA.getString("ciudad.unidades.entrenar"));
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
            alert.setTitle(TRADUCCIONES_GENERALES.getString("information.dialog"));
            alert.setHeaderText(null);
            alert.setContentText(TRADUCCIONES_THEMA.getString("ciudad.unidades.entrenadas"));
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
            hBox.setMaxWidth(250);
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
            } else {
                produce_Almacena_Cuesta_Devolucion_Resto_cambio = 9;
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
                return TRADUCCIONES_THEMA.getString("ciudad.etiquetas.produce");
            case 2:
                return TRADUCCIONES_THEMA.getString("ciudad.etiquetas.almacena");
            case 3:
                return TRADUCCIONES_THEMA.getString("ciudad.etiquetas.costeDeConstruccion");
            case 4:
                return TRADUCCIONES_THEMA.getString("ciudad.etiquetas.devolucion");
            case 5:
                return TRADUCCIONES_THEMA.getString("ciudad.etiquetas.resto");
            case 6:
                return TRADUCCIONES_THEMA.getString("ciudad.etiquetas.cambio");
            case 7:
                return TRADUCCIONES_THEMA.getString("ciudad.etiquetas.entrenar");
            case 8:
                return TRADUCCIONES_THEMA.getString("ciudad.etiquetas.costesDelEntrenamiento");
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

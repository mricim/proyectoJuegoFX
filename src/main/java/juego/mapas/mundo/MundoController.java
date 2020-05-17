package main.java.juego.mapas.mundo;

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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import main.java.juego.mapas.pelea.Unidades;
import main.java.juego.mapas.pelea.UnidadesPreCargadas;
import main.java.utils.CallImages;
import main.java.utils.PrimaryStageControler;
import main.java.juego.MapasController;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.ciudad.Ciudad;
import main.java.juego.mapas.ciudad.CiudadController;
import main.java.juego.mapas.pelea.Batallon;
import main.java.utils.tagsFX.*;
import sun.reflect.generics.tree.Tree;

import java.net.URL;
import java.text.MessageFormat;
import java.util.*;

import static java.util.stream.Collectors.toCollection;
import static javafx.geometry.Pos.TOP_CENTER;
import static javafx.scene.text.TextAlignment.CENTER;
import static main.java.Inicio.PantallaInicialController.elTemaSeleccionado;


public class MundoController extends MapasController implements Initializable {
    final private static String RUTE_IMAGES = "mapas/mundo/";
    public static final String THIS_RUTE = "juego/mapas/mundo/mundo.fxml";
    private static final String REGEX_SPLIT_PATTERN = "c";
    private static final int tamanoBaseMenu = 300;
    static boolean basura = true;
    public FlowPane recuros;

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

    final static Character letter_guion = '-';
    final static Character letter_guionBajo = '_';

    final static Character letter_agua = 'a';
    final static Character letter_isla = 'i';
    final static Character letter_city = 'c';
    final static Character letter_batallon = 'b';

    static Character letter_esNuestro = 'P';
    final static Character letter_Clan = 'Z';

    final static String letter_random = "$r";
    final static String letter_hover = "@h";
    private static Integer fila = 0;
    private static Integer columna = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!primeraCiudad) {
            inicialiceController();
            recursosMenu(recuros, this.getClass());
            selectorDeCiudad(selectorCiudad, this.getClass());
        } else {
            createMenuLeft(borderPane, null, null, null, null, false);
        }

        recargaGripPane();
    }

    private void recargaGripPane() {
        //<-- Controlado por MapasController


        gridPaneMap.getColumnConstraints().clear();
        gridPaneMap.getRowConstraints().clear();
        gridPaneMap.getChildren().clear();


        int numCiudades = elTemaSeleccionado.listaCiudades.size();
        //int numCiudades = 12000;
        int tamano = 15;
        int capacidadCiudades = 0;
        double casillasTotales = 0;
        do {
            casillasTotales = (tamano * tamano);
            capacidadCiudades = (int) (casillasTotales * 8) / 25;
            tamano += 5;

        } while (numCiudades > capacidadCiudades - 1);

        gridPaneMap.setAlignment(Pos.CENTER);
        gridPaneMap.setGridLinesVisible(true);


        for (int fila = 0; fila < tamano; fila++) {
            ColumnConstraints col = new ColumnConstraints(100);
            gridPaneMap.getColumnConstraints().add(col);
            for (int columna = 0; columna < tamano; columna++) {
                Ciudad ciudadToGrid = null;
                ArrayList<Batallon> batallonesToGrid = null;//creamos las variables por si nos hacen falta
                ImageView imageView = new ImageView();
                if (fila == 0) {
                    RowConstraints row = new RowConstraints(100);
                    gridPaneMap.getRowConstraints().add(row);
                }

                StringBuilder stringBuilder = new StringBuilder(32);

                String position = String.valueOf(fila) + letter_guion + columna;
                int filaModule = fila % 5;
                int columnaModule = columna % 5;
                StringBuilder rando = new StringBuilder();
                if (filaModule == 0 || filaModule == 4 || columnaModule == 0 || columnaModule == 4) {
                    stringBuilder.append(letter_agua);
                } else {
                    stringBuilder.append(letter_isla).append(letter_guionBajo).append(filaModule).append(letter_guion).append(columnaModule);
                    ciudadToGrid = elTemaSeleccionado.listaCiudades.get(position);
                    if (ciudadToGrid != null) {
                        stringBuilder.append(letter_guionBajo).append(letter_city);
                        if (getJugadorPrimaryStageController().listaCiudadesPropias.containsKey(position)) {
                            stringBuilder.append(letter_esNuestro);
                        } else if (getClanPrimaryStageController().getCiudadesDelClan().get(position) != null) {
                            stringBuilder.append(letter_Clan);
                        }
                    }
                    if (filaModule == 3 && columnaModule == 3) {//TODO RANDOMIZADOR PARA LA POSICION 3-3
                        rando.append(letter_random);
                        if (ciudadToGrid != null) {
                            rando.append(ciudadToGrid.getIdCiudad() % 3);
                        } else if (columna % 2 == 0) {
                            rando.append((fila + columna + 2) % 3);
                        } else {
                            rando.append((fila + columna) % 3);
                        }
                    }
                }
                batallonesToGrid = elTemaSeleccionado.listaPosicionesBatallones.get(position);
                if (batallonesToGrid != null) {
                    boolean batallonEnemigo = false;
                    boolean batallonNuestro = false;
                    boolean batallonAliado = false;
                    for (Batallon batallon : batallonesToGrid) {
                        if (getClanPrimaryStageController().getBatallonesDelClan().contains(batallon)) {
                            if (getJugadorPrimaryStageController().listaBatallonesPropios.containsValue(batallon)) {
                                batallonNuestro = true;
                            } else {
                                batallonAliado = true;
                            }
                        } else {
                            batallonEnemigo = true;
                        }
                        if (batallonNuestro && batallonAliado && batallonEnemigo) {//acorta la busqueda a cambio de un if mas (pueden darse casos de 100 batallones en la misma posicion)
                            break;
                        }
                    }
                    if (batallonNuestro) {
                        stringBuilder.append(letter_guionBajo).append(letter_batallon).append(letter_esNuestro);
                    }
                    if (batallonAliado) {
                        stringBuilder.append(letter_guionBajo).append(letter_batallon).append(letter_Clan);
                    }
                    if (batallonEnemigo) {
                        stringBuilder.append(letter_guionBajo).append(letter_batallon);
                    }
                }
                stringBuilder.append(rando);
                String nameImage = stringBuilder.toString();
                imageView.setImage(CallImages.getImage(RUTE_IMAGES, nameImage));
                imageView.setId(position);

                if (stringBuilder.indexOf(String.valueOf(letter_batallon)) != -1 || stringBuilder.indexOf(String.valueOf(letter_city)) != -1) {//he pensado que el maus solo se ponga* en las ciudades y los barcos
                    imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                        imageView.setCursor(Cursor.HAND);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        imageView.setImage(CallImages.getImage(RUTE_IMAGES, nameImage + letter_hover));
                    });
                    imageView.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        imageView.setImage(CallImages.getImage(RUTE_IMAGES, nameImage));
                    });
                    Ciudad finalCiudadToGrid = ciudadToGrid;
                    ArrayList<Batallon> finalBatallonesToGrid = batallonesToGrid;
                    imageView.setOnMouseClicked(e -> {
                        queClicas(imageView, false, finalCiudadToGrid, finalBatallonesToGrid, nameImage);
                    });
                } else {
                    if (nameImage.indexOf(letter_isla) != -1) {//es agua?
                        //no es agua
                        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                            queClicas(imageView, false, null, null, null);
                        });
                    } else {
                        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {//Cerrar el menu
                            queClicas(imageView, true, null, null, null);
                        });
                    }
                }
                gridPaneMap.add(imageView, columna, fila);
            }
        }


        gridPaneMap.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {//Cerrar el menu
            switch (event.getButton()) {
                case PRIMARY:
                    gridPaneMap.getChildren().forEach(item -> {
                        item.setOnMouseEntered(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                Node node = (Node) event.getSource();
                                columna = GridPane.getColumnIndex(node);
                                fila = GridPane.getRowIndex(node);
                            }
                        });
                    });

                    break;
                case SECONDARY:
                    queClicas(null, true, null, null, null);
                    break;
                default:
                    break;

            }

        });
    }

    private void queClicas(ImageView imageView, boolean agua, Ciudad ciudad, ArrayList<Batallon> batallones, String imageName) {
        if (basura) {//BLOQUEA UN MOMENTO EL SISTEMA DE CLIC PARA QUE CARGE EL MENU Y NO LO BORRE
            if (agua) {
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

                createMenuLeft(borderPane, imageView, ciudad, batallones, imageName, true);
            }
        }
    }

    private void createMenuLeft(BorderPane borderPane, ImageView imageView, Ciudad ciudad, ArrayList<Batallon> batallones, String imageName, boolean pasoPorClicas) {
        System.out.println("imageName: " + imageName);
        List<VBox> vBoxList = new ArrayList<>();
        boolean controllerParaVerSiestaVacio = false;
        if (ciudad != null) {
            vBoxList.add(cajaCiudadMundo(ciudad, imageName));
            controllerParaVerSiestaVacio = true;
        } else if (newCiudad && pasoPorClicas) {
            vBoxList.add(cajaNewCity(imageView, imageName));
            controllerParaVerSiestaVacio = true;
        } else if (primeraCiudad) {
            VBox vBox = new VBox();
            Label label = new Label(TRADUCCIONES_THEMA.getString("mundo.FundarNuevaCiudad"));
            vBox.getChildren().add(label);
            vBoxList.add(vBox);
            controllerParaVerSiestaVacio = true;
        }
        if (batallones != null) {
            vBoxList.add(cajaBatallon(batallones, imageView, imageName));
            controllerParaVerSiestaVacio = true;
        }
        if (controllerParaVerSiestaVacio) {
            rellenador(borderPane, vBoxList, tamanoBaseMenu);
        }
    }


    private VBox cajaCiudadMundo(Ciudad ciudadMapa, String imageName) {
        //BLOQUE
        VBox vBoxBloquePropio = new VBox();
        vBoxBloquePropio.setPrefWidth(tamanoBaseMenu);
        vBoxBloquePropio.setMaxWidth(tamanoBaseMenu);
        vBoxBloquePropio.setAlignment(TOP_CENTER);

        BackgroundFill backgroundFill = null;

        ObservableList<Node> childrenVBox = vBoxBloquePropio.getChildren();

//        //nombre
//        nombreCiudad = new Label(ciudadMapa.getNameCity());
//        nombreCiudad.setTextAlignment(CENTER);
//        nombreCiudad.setAlignment(Pos.CENTER);
//        nombreCiudad.setWrapText(true);
//        childrenVBox.add(nombreCiudad);
        //Imagen ciudad

        String[] image = imageName.split(REGEX_SPLIT_PATTERN);
        //System.out.println("Image1 index 0: " + image[0]);
        String image2;
        try {
            //System.out.println("XXXXXXX " + image[0]);
            //System.out.println("XXXXXXX " + image[1]);
            //System.out.println("XXXXXXX " + image[1].split("_")[0]);
            image2 = image[0] + 'c' + image[1].split("_")[0];
            //System.out.println("Image1 index1 : " + image[1]);
        } catch (Exception e) {
            image2 = image[0] + "c";
        }
        childrenVBox.add(new CustomSeparator((int) (tamanoBaseMenu * 0.8), false, 10));
        //System.out.println("Image 2 : " + image2);
        ImageView imgViewCiudad = new ImageView(CallImages.getImage(RUTE_IMAGES, image2));
        imgViewCiudad.setPickOnBounds(true);
        imgViewCiudad.setPreserveRatio(true);
        childrenVBox.add(imgViewCiudad);
        childrenVBox.add(new CustomSeparator((int) (tamanoBaseMenu * 0.8), false, 10));
        //Name ciudad
        Label nombreCiudad = new Label(ciudadMapa.getNameCity());
        nombreCiudad.setTextAlignment(CENTER);
        nombreCiudad.setAlignment(Pos.CENTER);
        nombreCiudad.setWrapText(true);
        childrenVBox.add(nombreCiudad);
        //Nivel ciudad
        Label nivelCiudadPropia = new Label(MessageFormat.format(TRADUCCIONES_THEMA.getString("mundo.nivelCiudad"), ciudadMapa.getNivelCiudad()));
        nivelCiudadPropia.setTextAlignment(CENTER);
        nivelCiudadPropia.setAlignment(Pos.CENTER);
        nivelCiudadPropia.setWrapText(true);
        childrenVBox.add(nivelCiudadPropia);
        //Ciudad propia
        if (getJugadorPrimaryStageController().listaCiudadesPropias.containsKey(ciudadMapa.getPosition())) {
            backgroundFill = new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY);
            printRecursos(childrenVBox, ciudadMapa.getRecursosTreeMap().entrySet());
        } else {//Ciudad enemiga o aliada
            if (getClanPrimaryStageController().getCiudadesDelClan().containsKey(ciudadMapa.getPosition())) {
                backgroundFill = new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY);
            } else {
                backgroundFill = new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY);
            }
            childrenVBox.add(new CustomSeparator((int) (tamanoBaseMenu * 0.8), true, 10));
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            //bloque a borrar
            Label label = new Label(TRADUCCIONES_THEMA.getString("mundo.ciudades.espia.enviar"));
            FlowPane flowPane = new FlowPane();
            flowPane.setHgap(5);
            flowPane.setVgap(5);
            flowPane.setAlignment(Pos.CENTER);
            ObservableList<Node> flowPaneChildren = flowPane.getChildren();
            for (Map.Entry<Integer, Integer> espia : elTemaSeleccionado.espias.entrySet()) {
                int coste = espia.getKey();
                int porcentage = espia.getValue();
                Button button = new Button("-" + coste + " (" + porcentage + "%)");
                button.setGraphic(new CustomImageView(elTemaSeleccionado.listaRecursosPreCargada.get(0).getImage(), 30, 30));
                button.setOnMouseClicked(event -> {
                    Platform.runLater(() -> {
                        getCiudadPrimaryStageController().getRecursosTreeMap().get(0).removeCantidad(coste);
                        espiaDeRecursos(vBox, ciudadMapa, porcentage);
                        recursosMenu(recuros, this.getClass());
                    });
                });
                if (getCiudadPrimaryStageController().getRecursosTreeMap().get(0).getCantidad() < coste) {
                    button.setDisable(true);
                }
                flowPaneChildren.add(button);
            }
            vBox.getChildren().addAll(label, new CustomSeparator(200, false, 5), flowPane);
            //fin bloque a borrar
            childrenVBox.add(vBox);
            //TODO printRecursos(childrenVBox, ciudadMapa.getRecursosTreeMap().entrySet());
            childrenVBox.add(new CustomSeparator((int) (tamanoBaseMenu * 0.8), false, 10));
        }

        vBoxBloquePropio.setBackground(new Background(backgroundFill));
        childrenVBox.add(new CustomSeparator((int) (tamanoBaseMenu * 0.8), false, 5));


        //FIN BLOQUE
        return vBoxBloquePropio;
    }

    private void espiaDeRecursos(VBox vBox, Ciudad ciudadMapa, int porcentage) {
        vBox.getChildren().clear();
        vBox.setMaxWidth((int) (tamanoBaseMenu * 0.6));
        ObservableList<Node> a = vBox.getChildren();
        a.add(new Label(TRADUCCIONES_THEMA.getString("mundo.ciudades.espia.resultados")));
        double porcentageToMax = ((100 - porcentage) * 0.01) + 1;
        double porcentageToMin = porcentage * 0.01;
        for (Recursos recurso : ciudadMapa.getRecursosTreeMap().values()) {
            int cantidad = recurso.getCantidad();
            int max = (int) ((cantidad + 10) * porcentageToMax);
            int min = (int) ((cantidad + 1) * porcentageToMin);

            Label maximo = new Label(String.valueOf(new Random().nextInt(max + 1 - cantidad) + cantidad));
            CustomImageView imageView = new CustomImageView(recurso.getImage(), 25, 25);
            Label minimo = new Label(String.valueOf(new Random().nextInt(cantidad + 1 - min) + min));
            //System.out.println(porcentageToMax + " " + maximo.getText() + " - " + cantidad + " - " + minimo.getText() + " " + porcentageToMin);
            Region filler = new Region();
            Region filler2 = new Region();
            filler.setMinWidth(5);
            filler2.setMinWidth(5);
            HBox.setHgrow(filler, Priority.ALWAYS);
            HBox.setHgrow(filler2, Priority.ALWAYS);
            HBox hBox = new HBox(maximo, filler, imageView, filler2, minimo);
            hBox.setAlignment(Pos.CENTER);
            a.add(hBox);
        }

    }

    private VBox cajaBatallon(ArrayList<Batallon> listaBatallones, ImageView imageView, String imageName) {
        //BLOQUE
        VBox vBoxBloquePropio = new VBox();
        vBoxBloquePropio.setPrefWidth(300);
        vBoxBloquePropio.setMaxWidth(tamanoBaseMenu);
        vBoxBloquePropio.setAlignment(TOP_CENTER);

        ObservableList<Node> childrenVBox = vBoxBloquePropio.getChildren();


        for (Batallon batallon : listaBatallones) {
            BackgroundFill backgroundFill = new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY);
            VBox vBoxBatallon = new VBox();
            vBoxBatallon.setAlignment(Pos.CENTER);
            vBoxBatallon.setSpacing(10);
            ObservableList<Node> vBoxBatallonChildren = vBoxBatallon.getChildren();

            Label nombreBatallon = new Label(batallon.getNombre());
            nombreBatallon.setTextAlignment(CENTER);
            nombreBatallon.setAlignment(Pos.CENTER);
            nombreBatallon.setWrapText(true);
            Separator separator2 = new Separator();
            separator2.setPrefWidth(100);
            nombreBatallon.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    nombreBatallon.setTextFill(Color.RED);
                }
            });

            nombreBatallon.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    nombreBatallon.setTextFill(Color.BLACK);
                }
            });
            vBoxBatallonChildren.addAll(separator2, nombreBatallon);


            for (Unidades unidades : batallon.getSoldadoHashMap().values()) {
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER);
                hBox.setSpacing(10);

                //Imagenes unidades
                CustomImageView imgUnidadesBatallon = new CustomImageView(unidades.getUnidadesPreCargadas().getImageIcon(), 60, 60);
                imgUnidadesBatallon.setPickOnBounds(true);
                imgUnidadesBatallon.setPreserveRatio(true);
                imgUnidadesBatallon.setSmooth(true);
                imgUnidadesBatallon.setCache(true);

                //Cantidad unidades
                Label numeroUnidades = new Label("Nº " + unidades.getUnidadesPreCargadas().getNombre() + ": " + unidades.getCantidad());
                numeroUnidades.setVisible(true);
                numeroUnidades.setTextAlignment(CENTER);
                numeroUnidades.setAlignment(Pos.CENTER);
                numeroUnidades.setWrapText(true);
                numeroUnidades.setFont(Font.font("Verdana", FontWeight.BOLD, 11));

                numeroUnidades.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        numeroUnidades.setScaleX(1.2);
                        numeroUnidades.setScaleY(1.2);
                    }
                });

                numeroUnidades.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        numeroUnidades.setScaleX(1.0);
                        numeroUnidades.setScaleY(1.0);
                    }
                });

                if (getJugadorPrimaryStageController().listaBatallonesPropios.containsKey(batallon.getIdBatallon())) {
                    hBox.getChildren().addAll(imgUnidadesBatallon, numeroUnidades);
                } else {
                    hBox.getChildren().addAll(imgUnidadesBatallon, numeroUnidades);
                }


                vBoxBatallonChildren.add(hBox);
            }

            //vBoxBloquePropio.setMargin(new Insets(0, 15, 0, 15));
            VBox vBox = new VBox();
            Label label1 = new Label(TRADUCCIONES_THEMA.getString("mundo.destino"));
            String city = "-";
            if (batallon.getCiudadDestino() != null) {
                city = batallon.getCiudadDestino().getNameCity();
            }
            Label label2 = new Label(city);
            HBox hBox = new HBox(label1, label2);
            hBox.setAlignment(Pos.CENTER);
            Label label3 = new Label(TRADUCCIONES_THEMA.getString("mundo.origen"));
            String city2 = "-";
            if (batallon.getCiudadVolver() != null) {
                city2 = batallon.getCiudadVolver().getNameCity();
            }
            Label label4 = new Label(city2);
            HBox hBox2 = new HBox(label3, label4);
            hBox2.setAlignment(Pos.CENTER);
            vBox.getChildren().addAll(hBox, hBox2);
            vBoxBatallonChildren.add(vBox);
            if (getJugadorPrimaryStageController().listaBatallonesPropios.containsKey(batallon.getIdBatallon())) {

                vBoxBatallonChildren.add(buttonMoverBatallon(batallon));//BOTON PARA MOVER UN BATALLON
                vBoxBatallonChildren.add(buttonSplitBatallon(batallon));//BOTON PARA dividir BATALLON


                boolean ponerBoton = false;
                ObservableList<String> strings = FXCollections.observableArrayList();
                for (Batallon batallon1 : listaBatallones) {
                    if (getJugadorPrimaryStageController().listaBatallonesPropios.containsKey(batallon1.getIdBatallon())) {
                        if (batallon1 != batallon) {
                            ponerBoton = true;
                            strings.add(batallon1.getNombre());
                        }
                    }
                }
                if (ponerBoton) {
                    ComboBox<String> unirBatallones = new ComboBox<>(strings);
                    unirBatallones.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                                for (Batallon batallon1 : getJugadorPrimaryStageController().listaBatallonesPropios.values()) {
                                    //System.out.println(batallon1.getNombre() + " - " + newValue);
                                    if (batallon1.getNombre().equals(newValue)) {
                                        batallon.addSoldados(batallon1.getSoldadoHashMap());
                                        batallon1.remove(getJugadorPrimaryStageController());
                                        break;
                                    }
                                }
                                reload(this.getClass());
                            }
                    );
                    vBoxBatallonChildren.add(unirBatallones);
                }
            } else {
                if (getClanPrimaryStageController().getBatallonesDelClan().contains(batallon)) {
                    backgroundFill = new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY);
                } else {
                    backgroundFill = new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY);
                }
            }
            vBoxBatallon.setBackground(new Background(backgroundFill));
            vBoxBatallonChildren.add(new CustomSeparator((int) (tamanoBaseMenu * 0.8), true));
            childrenVBox.add(vBoxBatallon);
        }

        childrenVBox.add(new CustomSeparator((int) (tamanoBaseMenu * 0.8), true));


        //FIN BLOQUE
        return vBoxBloquePropio;
    }

    private Node buttonSplitBatallon(Batallon batallon) {
        Button btnSplit = new Button(TRADUCCIONES_THEMA.getString("mundo.splitEjercito"));
        btnSplit.setAlignment(Pos.CENTER);
        btnSplit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<VBox> vBoxList = new ArrayList<>();

                VBox vBox = new VBox();
                vBox.setPrefWidth(400);
                vBox.setMaxWidth(400);
                vBox.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                vBox.setAlignment(TOP_CENTER);
                ObservableList<Node> vBoxChildren = vBox.getChildren();

                Map<Integer, Unidades> temp = new HashMap<>();
                Button btnSplit = new Button(TRADUCCIONES_THEMA.getString("mundo.button.separarEjercito"));
                btnSplit.setDisable(true);
                for (Unidades soldados : batallon.getSoldadoHashMap().values()) {
                    UnidadesPreCargadas unidadesPreCargadas = soldados.getUnidadesPreCargadas();
                    temp.put(unidadesPreCargadas.getIdType(), soldados.clone());
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER);
                    hBox.setSpacing(10);
                    CustomImageView imageView = new CustomImageView(unidadesPreCargadas.getImageIcon(), 60, 60);

                    int maxSoldados = soldados.getCantidad();
                    CustomTextField textField = new CustomTextField("0", true, maxSoldados);
                    //textField.textProperty().bind(slider.valueProperty().asString("%.0f"));

                    CustomSlider slider = new CustomSlider(0, maxSoldados, 0);
                    slider.setmargin(25, 0, 0, 0);
                    slider.valueProperty().addListener(new ChangeListener<Number>() {
                        @Override
                        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                            int seleccionado = newValue.intValue();
                            int number = oldValue.intValue() - seleccionado;
                            if (number != 0) {
                                textField.textProperty().setValue(String.valueOf(seleccionado));
                                Platform.runLater(() -> {
                                    int id = unidadesPreCargadas.getIdType();
                                    Unidades unidades = temp.get(id);
                                    unidades.setCantidad(seleccionado);
                                    temp.put(id, unidades);

                                    boolean sePuedeSeparar = true;
                                    for (Unidades value : temp.values()) {
                                        if (value.getCantidad() < 5) {
                                            sePuedeSeparar = false;
                                        } else if (soldados.getCantidad() - value.getCantidad() < 5) {
                                            sePuedeSeparar = false;
                                        }
                                    }
                                    btnSplit.setDisable(!sePuedeSeparar);
                                });
                            }
                        }
                    });
                    textField.setBindSlider(slider);
                    hBox.getChildren().addAll(imageView, slider, textField);

                    vBoxChildren.add(hBox);
                }
                vBoxChildren.add(new CustomSeparator(20, false, 10));
                TextField textField = new TextField();
                textField.setPromptText(TRADUCCIONES_THEMA.getString("mundo.textfield.batallonName"));
                textField.setAlignment(Pos.CENTER);
                textField.setMaxWidth((int) (tamanoBaseMenu * 0.8));
                vBoxChildren.add(textField);
                vBoxChildren.add(new CustomSeparator(20, false, 10));
                btnSplit.setAlignment(Pos.CENTER);
                btnSplit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String name = textField.getText();
                        if (name.equals("")) {
                            name = MessageFormat.format(TRADUCCIONES_THEMA.getString("mundo.batallonDefaultName"), getJugadorPrimaryStageController().listaBatallonesPropios.size());
                        }
                        Batallon elNuevo = new Batallon(name, batallon.getFila(), batallon.getColumna(), 0, getJugadorPrimaryStageController(), batallon.getCiudadVolver());
                        for (Unidades value : batallon.getSoldadoHashMap().values()) {
                            Unidades unidadesTemp = temp.get(value.getUnidadesPreCargadas().getIdType());
                            elNuevo.setSoldadoHashMap(unidadesTemp);
                            value.removeCantidad(unidadesTemp.getCantidad());
                        }
                        reload(MundoController.class);
                    }
                });
                vBoxChildren.add(btnSplit);
                vBoxChildren.add(new CustomSeparator(20, false, 10));

                vBoxList.add(vBox);
                rellenador(borderPane, vBoxList, 400);
            }
        });
        return btnSplit;
    }

    private Button buttonMoverBatallon(Batallon batallon) {
        Button btnMove = new Button(TRADUCCIONES_THEMA.getString("mundo.moverUnidades"));
        btnMove.setAlignment(Pos.CENTER);
        btnMove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event1) {
                //System.out.println("Entro button event handler");
                /*
                final String[] position = new String[1];
                vBoxBloquePropio.getChildren()
                        .stream()
                        .filter(Label.class::isInstance)
                        .map(Label.class::cast)
                        .forEach(label -> position[0] = label.getText());
                System.out.println("Posición Batallon " + position[0]);
                String posicionBatallon = position[0].substring(position[0].indexOf('-') - 1, position[0].indexOf('-') + 2);
                System.out.println("Posición en grid de batallon " + posicionBatallon);
*/
                getStagePrimaryStageController().getScene().setCursor(Cursor.CROSSHAIR);
                getStagePrimaryStageController().getScene().setOnMouseClicked(
                        new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event2) {
                                //basura=true;
                                //basura=true; TODO XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
                                //System.out.println("Entro scene handler");
                                if (event2.getButton().equals(MouseButton.PRIMARY)) {

                                    //
                                    String position = fila + "-" + columna;
                                    Ciudad ciudad = elTemaSeleccionado.listaCiudades.get(position);
                                    if (ciudad != null) {
                                        if (getClanPrimaryStageController().getCiudadesDelClan().containsKey(position)) {
                                            batallon.setCiudadVolver(ciudad);
                                            batallon.setCiudadDestino(ciudad);
                                            reload(MundoController.class);
                                        } else {
                                            batallon.setCiudadDestino(ciudad);
                                            reload(MundoController.class);
                                        }
                                        CustomAlert alert = new CustomAlert(Alert.AlertType.INFORMATION);
                                        alert.setTitle(TRADUCCIONES_GENERALES.getString("information.dialog"));
                                        alert.setHeaderText(TRADUCCIONES_THEMA.getString("mundo.dialog.header.moverTropas"));
                                        alert.setContentText(MessageFormat.format(TRADUCCIONES_THEMA.getString("mundo.dialog.text.moverTropas") + " ", ciudad.getNameCity()));
                                        alert.showAndWait();
                                    } else {
                                        CustomAlert alert = new CustomAlert(Alert.AlertType.WARNING);
                                        alert.setTitle(TRADUCCIONES_GENERALES.getString("warning.dialog"));
                                        alert.setHeaderText(TRADUCCIONES_THEMA.getString("mundo.batallon.dialog.header.posicionNoPermitida"));
                                        alert.setContentText(TRADUCCIONES_THEMA.getString("mundo.batallon.dialog.text.PosicionNoPermitida"));
                                        alert.showAndWait();
                                    }
                                    //

                                    event2.consume();
                                    event1.consume();
                                    getStagePrimaryStageController().getScene().setCursor(Cursor.DEFAULT);
                                    getStagePrimaryStageController().getScene().setOnMouseClicked(null);
                                    //System.out.println("Posición de Batallon " + batallon.getNombre() + " " + batallon.getPosition() + " después de cambio.");

//                                    event2.consume();
//                                    event1.consume();
//                                    getStagePrimaryStageController().getScene().setCursor(Cursor.DEFAULT);
                                    //getStagePrimaryStageController().getScene().setOnMouseClicked(null);

                                    recargaGripPane();
                                    //reload(MundoController.class);
                                } else {
                                    getStagePrimaryStageController().getScene().setCursor(Cursor.DEFAULT);
                                    event2.consume();
                                }
                            }
                        });
            }
        });
        return btnMove;
    }

    private boolean isSePuedeMover(int batallonFilaTemp, int batallonColumTemp, Batallon batallon) {
        String posicion = batallonFilaTemp + "-" + batallonColumTemp;
        boolean sePuedeMover = true;
        int filaModule = batallonFilaTemp % 5;
        int columnaModule = batallonColumTemp % 5;
        if (elTemaSeleccionado.listaCiudades.get(posicion) == null) {
            if (filaModule == 0 || filaModule == 4 || columnaModule == 0 || columnaModule == 4) {
                try {
                    ArrayList<Batallon> a = elTemaSeleccionado.listaPosicionesBatallones.get(posicion);
                    for (Batallon batallon1 : a) {
                        if (!getClanPrimaryStageController().getBatallonesDelClan().contains(batallon1)) {
                            sePuedeMover = false;
                        }
                    }
                } catch (Exception ignore) {
                }
            } else {
                sePuedeMover = false;
            }
        }
        if (sePuedeMover) {
            batallon.setFilaColumna(batallonFilaTemp, batallonColumTemp);
            return true;
        }
        return false;
    }

    private VBox cajaNewCity(ImageView imageView, String imageName) {
        //BLOQUE
        VBox vBoxBloquePropio = new VBox();
        vBoxBloquePropio.setPrefWidth(tamanoBaseMenu);
        vBoxBloquePropio.setMaxWidth(tamanoBaseMenu);
        vBoxBloquePropio.setAlignment(TOP_CENTER);

        ObservableList<Node> childrenVBox = vBoxBloquePropio.getChildren();

        BackgroundFill backgroundFill = null;
        String[] asd = imageView.getId().split("-");
        int fila = Integer.parseInt(asd[0]);
        int columna = Integer.parseInt(asd[1]);
        Button botonNuevaCity = new Button(MessageFormat.format(TRADUCCIONES_THEMA.getString("mundo.fundarNuevaCiudad") + " ", imageView.getId()));
        botonNuevaCity.setTextAlignment(CENTER);
        botonNuevaCity.setAlignment(Pos.CENTER);
        botonNuevaCity.setOnMouseClicked(e -> {
            //System.out.println();
            System.out.println(getJugadorPrimaryStageController().getId());
            System.out.println(fila + " " + columna);
            ArrayList<Recursos> city2 = new ArrayList<>();
            city2.add(new Recursos(0, 3000));
            city2.add(new Recursos(1, 3000));
            city2.add(new Recursos(2, 3000));
            city2.add(new Recursos(3, 3000));
            city2.add(new Recursos(4, 3000));
            city2.add(new Recursos(5, 3000));
            city2.add(new Recursos(6, 3000));
            new Ciudad(getJugadorPrimaryStageController(), MessageFormat.format(TRADUCCIONES_THEMA.getString("mundo.newCity") + " ", imageView.getId()), fila, columna, 0, city2);
            newCiudad = false;
            if (primeraCiudad) {
                PrimaryStageControler.setCiudadPrimaryStageController(getJugadorPrimaryStageController().listaCiudadesPropias.values().iterator().next());
                primeraCiudad = false;
            }
            reload(MundoController.class);
        });
        childrenVBox.add(botonNuevaCity);

        vBoxBloquePropio.setBackground(new Background(backgroundFill));

        childrenVBox.add(new CustomSeparator((int) (tamanoBaseMenu * 0.8), true, 5));

        //FIN BLOQUE
        return vBoxBloquePropio;
    }

    private void printRecursos(ObservableList<Node> childrenFlowPane, Set<Map.Entry<Integer, Recursos>> recursos) {
        childrenFlowPane.add(new CustomSeparator((int) (tamanoBaseMenu * 0.8), false, 5));
        for (Map.Entry<Integer, Recursos> recurso : recursos) {
            Recursos recursoValor = recurso.getValue();
            int numero = recursoValor.getCantidad();
            CustomImageView imageView = new CustomImageView(recursoValor.getImage(), 25, 25);
            Label label = new Label(String.valueOf(numero));
            label.setGraphic(imageView);
            label.setTextAlignment(CENTER);
            label.setAlignment(Pos.CENTER);
            label.setWrapText(true);
            childrenFlowPane.add(label);
        }
        Separator separator = new Separator();
        separator.setPrefWidth((int) (tamanoBaseMenu * 0.8));
        childrenFlowPane.add(separator);

    }


    public void toMundo(MouseEvent mouseEvent) {
        reload(CiudadController.class);
    }
}

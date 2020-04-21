package main.java.juego.mapas.mundo;

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
import main.java.utils.CallImages;
import main.java.utils.PrimaryStageControler;
import main.java.juego.MapasController;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.ciudad.Ciudad;
import main.java.juego.mapas.ciudad.CiudadController;
import main.java.juego.mapas.pelea.Batallon;
import main.java.jugadores.Jugador;

import javax.crypto.spec.PSource;
import java.net.URL;
import java.util.*;

import static javafx.geometry.Pos.TOP_CENTER;
import static javafx.scene.text.TextAlignment.CENTER;
import static main.java.jugadores.Jugador.listaCiudades;
import static main.java.jugadores.Jugador.listaPosicionesBatallones;


public class MundoController extends MapasController implements Initializable {
    final private static String RUTE_IMAGES = "mapas/mundo/";
    public static final String THIS_RUTE = "juego/mapas/mundo/mundo.fxml";
    private static final String REGEX_SPLIT_PATTERN = "c";
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
            gridPaneMap.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {//Cerrar el menu
                switch (event.getButton()){
                    case PRIMARY:
                        gridPaneMap.getChildren().forEach(item ->{
                            item.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    Node node = (Node)event.getSource();
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
        } else {
            createMenuLeft(borderPane, null, null, null, null, false);
        }
        //<-- Controlado por MapasController


        int numCiudades = listaCiudades.size();
        //int numCiudades = 12000;
        int tamaño = 15;
        int capacidadCiudades = 0;
        double casillasTotales = 0;
        do {
            casillasTotales = (tamaño * tamaño);
            capacidadCiudades = (int) (casillasTotales * 8) / 25;
            tamaño += 5;

        } while (numCiudades > capacidadCiudades - 1);

        gridPaneMap.setAlignment(Pos.CENTER);
        gridPaneMap.setGridLinesVisible(true);


        for (int fila = 0; fila < tamaño; fila++) {
            ColumnConstraints col = new ColumnConstraints(100);
            gridPaneMap.getColumnConstraints().add(col);
            for (int columna = 0; columna < tamaño; columna++) {
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
                if (filaModule == 0 || filaModule == 4 || columnaModule == 0 || columnaModule == 4) {
                    stringBuilder.append(letter_agua);
                } else {
                    stringBuilder.append(letter_isla).append(letter_guionBajo).append(filaModule).append(letter_guion).append(columnaModule);
                    ciudadToGrid = listaCiudades.get(position);
                    if (ciudadToGrid != null) {
                        stringBuilder.append(letter_guionBajo).append(letter_city);
                        if (getClanPrimaryStageController().getCiudadesDelClan().contains(new Ciudad(position))) {
                            if (getJugadorPrimaryStageController().listaCiudadesPropias.containsKey(position)) {
                                stringBuilder.append(letter_esNuestro);
                            } else {
                                stringBuilder.append(letter_Clan);
                            }

                        }
                    }
                    if (filaModule == 3 && columnaModule == 3) {//TODO RANDOMIZADOR PARA LA POSICION 3-3
                        stringBuilder.append(letter_random);
                        if (ciudadToGrid != null) {
                            stringBuilder.append(ciudadToGrid.getIdCiudad() % 9);
                        } else if (columna % 2 == 0) {
                            stringBuilder.append((fila + columna + 2) % 9);
                        } else {
                            stringBuilder.append((fila + columna) % 9);
                        }
                    }

                }
                batallonesToGrid = listaPosicionesBatallones.get(position);
                if (batallonesToGrid != null) {
                    boolean batallonEnemigo = false;
                    boolean batallonNuestro = false;
                    boolean batallonAliado = false;
                    for (Batallon batallon : batallonesToGrid) {
                        if (getClanPrimaryStageController().getBatallonesDelClan().contains(batallon)) {//TODO new Batallon(position)
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


    private static VBox cajaCiudadMundo(Ciudad ciudadMapa, ImageView imageView, String imageName) {


        //Objetos de ciudad
        Label nombreCiudad = null;
        Label nivelCiudadPropia = null;
        ImageView imgViewCiudad = null;
        Label descripcionCiudad = null;


        //BLOQUE
        VBox vBoxBloquePropio = new VBox();
        vBoxBloquePropio.setMinWidth(200);
        vBoxBloquePropio.setMaxWidth(200);
        vBoxBloquePropio.setAlignment(TOP_CENTER);

        System.out.println("Imagename = " + imageName);
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
        System.out.println("Image1 index 0: " + image[0]);
        String image2;
        try {
            System.out.println("XXXXXXX " + image[0]);
            System.out.println("XXXXXXX " + image[1]);
            System.out.println("XXXXXXX " + image[1].split("_")[0]);
            image2 = image[0] + 'c' + image[1].split("_")[0];
            System.out.println("Image1 index1 : " + image[1]);

        } catch (Exception e) {
            image2 = image[0] + "c";
        }

        System.out.println("Image 2 : " + image2);
        imgViewCiudad = new ImageView(CallImages.getImage(RUTE_IMAGES, image2));
        imgViewCiudad.setPickOnBounds(true);
        imgViewCiudad.setPreserveRatio(true);
        childrenVBox.add(imgViewCiudad);
        //Nivel ciudad
        nivelCiudadPropia = new Label("Nivel: " + ciudadMapa.getNivelCiudad());
        nivelCiudadPropia.setTextAlignment(CENTER);
        nivelCiudadPropia.setAlignment(Pos.CENTER);
        nivelCiudadPropia.setWrapText(true);
        childrenVBox.add(nivelCiudadPropia);
        //Descripción ciudad
        descripcionCiudad = new Label("Descripción ciudad");
        descripcionCiudad.setTextAlignment(CENTER);
        descripcionCiudad.setAlignment(Pos.CENTER);
        descripcionCiudad.setWrapText(true);
        childrenVBox.add(descripcionCiudad);
        //Ciudad propia
        if (getJugadorPrimaryStageController().listaCiudadesPropias.containsKey(ciudadMapa.getPosition())) {
            backgroundFill = new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY);
            printRecursos(childrenVBox, ciudadMapa.getRecursosTreeMap().entrySet(), 2);
            vBoxBloquePropio.setMargin(descripcionCiudad, new Insets(0, 15, 0, 15));
        } else {//Ciudad enemiga o aliada
            if (getClanPrimaryStageController().getCiudadesDelClan().contains(ciudadMapa)) {
                backgroundFill = new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY);
            } else {
                backgroundFill = new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY);
            }
            nombreCiudad = new Label(ciudadMapa.getNameCity());
            nombreCiudad.setTextAlignment(CENTER);
            nombreCiudad.setAlignment(Pos.CENTER);
            nombreCiudad.setWrapText(true);
            childrenVBox.add(nombreCiudad);
        }

        vBoxBloquePropio.setBackground(new Background(backgroundFill));
        Separator separator = new Separator();
        separator.setPrefWidth(200);
        childrenVBox.add(separator);


        //FIN BLOQUE
        return vBoxBloquePropio;
    }

    private static VBox cajaBatallon(ArrayList<Batallon> listaBatallones, ImageView imageView, String imageName) {
        //Objetos de ciudad
        Label nombreBatallon = null;
        ImageView imgViewBatallon = null;
        Label numeroUnidades = null;
        ImageView imgUnidadesBatallon = null;


        Button btnMove = new Button();
        btnMove.setText("Mover a");
        btnMove.setAlignment(Pos.CENTER);
        btnMove.setMaxWidth(Double.MAX_VALUE);


        //BLOQUE
        VBox vBoxBloquePropio = new VBox();
        vBoxBloquePropio.setMinWidth(200);
        vBoxBloquePropio.setMaxWidth(200);
        vBoxBloquePropio.setAlignment(TOP_CENTER);

        ObservableList<Node> childrenVBox = vBoxBloquePropio.getChildren();


        BackgroundFill backgroundFill = null;
        Separator separator2 = null;



        btnMove.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Entro button event handler");
                final String[] position = new String[1];
                vBoxBloquePropio.getChildren()
                        .stream()
                        .filter(Label.class::isInstance)
                        .map(Label.class::cast)
                        .forEach(label -> position[0] = label.getText());
                System.out.println("Posición Batallon "+position[0]);

                String posicionBatallon = position[0].substring(position[0].indexOf('-')-1,position[0].indexOf('-')+2);
                System.out.println("Posición en grid de batallon "+posicionBatallon);
                getStagePrimaryStageController().getScene().setCursor(Cursor.CROSSHAIR);
                getStagePrimaryStageController().getScene().setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("Entro scene handler");
                        if (event.getButton().equals(MouseButton.PRIMARY)) {
                            final String position = posicionBatallon;
                            getStagePrimaryStageController().getScene().setCursor(Cursor.DEFAULT);
                            //TODO pendiente mover batallon de una posicion a otra, a partir de la variable position. Falta saber que lista usar.
                            //ya que aunque cambie la posición, no se refresca en la pantalla
                            for (Batallon batallon : getJugadorPrimaryStageController().listaBatallonesPropios.values()) {
                                if (batallon.getPosition().equals(position)) {
                                    System.out.println("Posición de Batallon "+batallon.getNombre()+" "+batallon.getPosition()+" antes de cambio.");
                                    batallon.setFilaColumna(fila,columna);
                                    System.out.println("Posición de Batallon "+batallon.getNombre()+" "+batallon.getPosition()+" después de cambio.");
                                    event.consume();
                                }
                            }

                        } else {
                            getStagePrimaryStageController().getScene().setCursor(Cursor.DEFAULT);
                            event.consume();
                        }
                    }

                });
            }
        });


        for (Batallon batallon : listaBatallones) {
            nombreBatallon = new Label(batallon.getNombre());
            nombreBatallon.setTextAlignment(CENTER);
            nombreBatallon.setAlignment(Pos.CENTER);
            nombreBatallon.setWrapText(true);
            separator2 = new Separator();
            separator2.setPrefWidth(100);
            Label finalNombreBatallon = nombreBatallon;
            nombreBatallon.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    finalNombreBatallon.setTextFill(Color.RED);
                }
            });

            nombreBatallon.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    finalNombreBatallon.setTextFill(Color.BLACK);
                }
            });
            childrenVBox.addAll(separator2, finalNombreBatallon);




            for (Unidades unidades : batallon.getSoldadoHashMap().values()) {
                GridPane gridPaneBatallon = new GridPane();
                gridPaneBatallon.setAlignment(Pos.CENTER);

                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER);
                hBox.setSpacing(10);

                //Imagenes unidades
                imgUnidadesBatallon = new ImageView(unidades.getUnidadesPreCargadas().getImageIcon());
                imgUnidadesBatallon.setPickOnBounds(true);
                imgUnidadesBatallon.setPreserveRatio(true);
                imgUnidadesBatallon.setFitWidth(60);
                imgUnidadesBatallon.setFitHeight(60);
                imgUnidadesBatallon.setSmooth(true);
                imgUnidadesBatallon.setCache(true);

                //Cantidad unidades
                numeroUnidades = new Label("Nº " + unidades.getUnidadesPreCargadas().getNombre() + ": " + unidades.getCantidad());
                numeroUnidades.setVisible(true);
                numeroUnidades.setTextAlignment(CENTER);
                numeroUnidades.setAlignment(Pos.CENTER);
                numeroUnidades.setWrapText(true);
                numeroUnidades.setFont(Font.font("Verdana", FontWeight.BOLD, 11));

                Label finalNumeroUnidades = numeroUnidades;
                numeroUnidades.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        finalNumeroUnidades.setScaleX(1.2);
                        finalNumeroUnidades.setScaleY(1.2);
                    }
                });

                numeroUnidades.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        finalNumeroUnidades.setScaleX(1.0);
                        finalNumeroUnidades.setScaleY(1.0);
                    }
                });

                if (getJugadorPrimaryStageController().listaBatallonesPropios.containsKey(batallon.getIdBatallon())) {
                    hBox.getChildren().addAll(imgUnidadesBatallon, finalNumeroUnidades, btnMove);
                    gridPaneBatallon.add(hBox, 0, 0);
                    gridPaneBatallon.add(btnMove, 0, 1);

                } else {
                    hBox.getChildren().addAll(imgUnidadesBatallon, finalNumeroUnidades);
                    gridPaneBatallon.add(hBox, 0, 0);

                }

                childrenVBox.add(gridPaneBatallon);


            }

            //vBoxBloquePropio.setMargin(new Insets(0, 15, 0, 15));
            if (getJugadorPrimaryStageController().listaBatallonesPropios.containsKey(batallon.getIdBatallon())) {
                backgroundFill = new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY);

            } else {
                boolean xi = false;
                for (Jugador jugador : getClanPrimaryStageController().getJugadoresDelClan().values()) {
                    if (jugador.listaBatallonesPropios.containsKey(batallon.getIdBatallon())) {
                        xi = true;
                        break;
                    }
                }
                if (xi) {
                    backgroundFill = new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY);
                } else {
                    backgroundFill = new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY);
                }
            }

        }
        vBoxBloquePropio.setBackground(new Background(backgroundFill));

        Separator separator = new Separator();
        separator.setPrefWidth(200);
        childrenVBox.add(separator);


        //FIN BLOQUE
        return vBoxBloquePropio;
    }

    public static void showMessage() {
        Stage newStage = new Stage();
        VBox comp = new VBox();
        TextField message = new TextField("No puedes moverte a esta posición, intentalo de nuevo.");
        comp.getChildren().add(message);

        Scene stageScene = new Scene(comp, 300, 300);
        newStage.setScene(stageScene);
        newStage.show();
    }

    private static VBox cajaNewCity(ImageView imageView, String imageName) {
        //Objetos de ciudad
        Button botonNuevaCity = null;
        ImageView imgViewCity = null;


        //BLOQUE
        VBox vBoxBloquePropio = new VBox();
        vBoxBloquePropio.setMinWidth(200);
        vBoxBloquePropio.setMaxWidth(200);
        vBoxBloquePropio.setAlignment(TOP_CENTER);

        ObservableList<Node> childrenVBox = vBoxBloquePropio.getChildren();

        BackgroundFill backgroundFill = null;
        String[] asd = imageView.getId().split("-");
        int fila = Integer.parseInt(asd[0]);
        int columna = Integer.parseInt(asd[1]);
        botonNuevaCity = new Button("Fundar una nueva ciudad " + imageView.getId());
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
            new Ciudad(getJugadorPrimaryStageController(), "New city " + imageView.getId(), fila, columna, 0, city2);
            newCiudad = false;
            if (primeraCiudad) {
                PrimaryStageControler.setCiudadPrimaryStageController(getJugadorPrimaryStageController().listaCiudadesPropias.values().iterator().next());
                primeraCiudad = false;
            }
            reload(MundoController.class);
        });
        childrenVBox.add(botonNuevaCity);

        vBoxBloquePropio.setBackground(new Background(backgroundFill));

        Separator separator = new Separator();
        separator.setPrefWidth(200);
        childrenVBox.add(separator);

        //FIN BLOQUE
        return vBoxBloquePropio;
    }

    private static void printRecursos(ObservableList<Node> childrenFlowPane, Set<Map.Entry<Integer, Recursos>> recursos, int produceAlmacenaCuesta) {
        boolean paso0 = false;
        for (Map.Entry<Integer, Recursos> recurso : recursos) {
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
                    label.setText("Produce:");
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
        for (Map.Entry<Integer, Recursos> recurso : recursos) {
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


    private static void createMenuLeft(BorderPane borderPane, ImageView imageView, Ciudad ciudad, ArrayList<Batallon> batallones, String imageName, boolean pasoPorClicas) {
        List<VBox> vBoxList = new ArrayList<>();
        boolean controllerParaVerSiestaVacio = false;
        if (ciudad != null) {
            vBoxList.add(cajaCiudadMundo(ciudad, imageView, imageName));
            controllerParaVerSiestaVacio = true;
        } else if (newCiudad && pasoPorClicas) {
            vBoxList.add(cajaNewCity(imageView, imageName));
            controllerParaVerSiestaVacio = true;
        } else if (primeraCiudad) {
            VBox vBox = new VBox();
            Label label = new Label("Selecciona una isla y funda una ciudad");
            vBox.getChildren().add(label);
            vBoxList.add(vBox);
            controllerParaVerSiestaVacio = true;
        }
        if (batallones != null) {
            vBoxList.add(cajaBatallon(batallones, imageView, imageName));
            controllerParaVerSiestaVacio = true;
        }
        if (controllerParaVerSiestaVacio) {
            rellenador(borderPane, vBoxList, 200);
        }
    }


    public void toMundo(MouseEvent mouseEvent) {
        reload(CiudadController.class);
    }
}

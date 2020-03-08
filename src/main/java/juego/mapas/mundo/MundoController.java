package main.java.juego.mapas.mundo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import main.java.Utils.CallImages;
import main.java.Utils.PrimaryStageControler;
import main.java.juego.MapasController;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.ciudad.Ciudad;
import main.java.juego.mapas.ciudad.CiudadController;
import main.java.juego.mapas.pelea.Batallon;
import main.java.jugadores.Jugador;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static javafx.geometry.Pos.TOP_CENTER;
import static javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER;
import static javafx.scene.text.TextAlignment.CENTER;
import static jdk.nashorn.internal.objects.Global.Infinity;
import static main.java.jugadores.Jugador.listaCiudades;
import static main.java.jugadores.Jugador.listaPosicionesBatallones;


public class MundoController extends MapasController implements Initializable {
    final private static String RUTE = "temas/demo/mapas/mundo/";
    public static final String THIS_RUTE = "juego/mapas/mundo/mundo.fxml";
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
    @FXML
    Label oro, madera;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicialiceController();
        recursosMenu(recuros, getCiudadPrimaryStageController().getRecursosTreeMap().values());
        selectorDeCiudad(THIS_RUTE, selectorCiudad);
        gridPaneMap.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {//Cerrar el menu
            queClicas(null, true, null, null);
        });
        //<-- Controlado por MapasController


        int numCiudades = 1200;
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


        String letter_guion = "-";
        String letter_hover = "@h";

        Character letter_agua = 'a';
        String letter_batallon = "_b";
        String letter_batallonPersonal = "_bP";
        String letter_batallonClan = "_bZ";

        String letter_isla = "i_";
        String letter_city = "_c";
        Character letter_Clan = 'Z';
        String letter_random = "_r";

        Character letter_propio_esNuestro = 'P';

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


                String position = fila + letter_guion + columna;
                int filaModule = fila % 5;
                int columnaModule = columna % 5;
                if (filaModule == 0 || filaModule == 4 || columnaModule == 0 || columnaModule == 4) {
                    stringBuilder.append(letter_agua);
                } else {
                    stringBuilder.append(letter_isla).append(filaModule).append(letter_guion).append(columnaModule);
                    ciudadToGrid = listaCiudades.get(position);
                    if (ciudadToGrid != null) {
                        stringBuilder.append(letter_city);
                        if (getJugadorPrimaryStageController().listaCiudadesPropias.containsKey(position)) {
                            stringBuilder.append(letter_propio_esNuestro);
                        } else {
                            if (getClanPrimaryStageController().getCiudadesDelClan().contains(new Ciudad(position))) {
                                stringBuilder.append(letter_Clan);
                            }
                        }
                    }
                    if (filaModule == 3 && columnaModule == 3) {//TODO RANDOMIZADOR PARA LA POSICION 3-3
                        stringBuilder.append(letter_random);
                        switch (new Random().nextInt(2)) {
                            case 0:
                                stringBuilder.append(0);
                                break;
                            case 1:
                                stringBuilder.append(1);
                                break;
                            case 2:
                                stringBuilder.append(2);
                                break;
                            default:
                                stringBuilder.append(3);
                                break;
                        }
                    }

                }
                batallonesToGrid = listaPosicionesBatallones.get(position);
                if (batallonesToGrid != null) {
                    boolean batallonEnemigo = false;
                    boolean batallonNuestro = false;
                    boolean batallonAliado = false;
                    for (Batallon batallon : batallonesToGrid) {
                        if (getJugadorPrimaryStageController().listaBatallonesPropios.containsValue(batallon)) {
                            batallonNuestro = true;
                        } else if (getClanPrimaryStageController().getBatallonesDelClan().contains(new Batallon(position))) {
                            batallonAliado = true;
                        } else {
                            batallonEnemigo = true;
                        }
                        if (batallonNuestro && batallonAliado && batallonEnemigo) {//acorta la busqueda a cambio de un if mas (pueden darse casos de 100 batallones en la misma posicion)
                            break;
                        }
                    }
                    if (batallonNuestro) {
                        stringBuilder.append(letter_batallonPersonal);
                    }
                    if (batallonAliado) {
                        stringBuilder.append(letter_batallonClan);
                    }
                    if (batallonEnemigo) {
                        stringBuilder.append(letter_batallon);
                    }
                }
                String nameImage = stringBuilder.toString();

                imageView.setImage(CallImages.getImage(RUTE, nameImage));

                if (stringBuilder.indexOf(letter_batallon) != -1 || stringBuilder.indexOf(letter_city) != -1) {//he pensado que el maus solo se ponga* en las ciudades y los barcos
                    imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                        imageView.setCursor(Cursor.HAND);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        imageView.setImage(CallImages.getImage(RUTE, nameImage + letter_hover));
                    });
                    imageView.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        imageView.setImage(CallImages.getImage(RUTE, nameImage));
                    });
                    Ciudad finalCiudadToGrid = ciudadToGrid;
                    ArrayList<Batallon> finalBatallonesToGrid = batallonesToGrid;
                    imageView.setOnMouseClicked(e -> {
                        queClicas(imageView, false, finalCiudadToGrid, finalBatallonesToGrid);
                    });
                } else {
                    if (nameImage.indexOf(letter_isla) != -1) {
                        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                            queClicas(imageView, false, null, null);
                        });
                    } else {
                        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {//Cerrar el menu
                            queClicas(imageView, true, null, null);
                        });
                    }
                }


                gridPaneMap.add(imageView, columna, fila);
            }
        }

    }

    private void queClicas(ImageView imageView, boolean agua, Ciudad ciudad, ArrayList<Batallon> batallones) {
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
                createMenuLeft(borderPane, imageView, ciudad, batallones);
            }
        }
    }


    private static VBox cajaCiudad(Ciudad ciudadMapa, ImageView imageView) {


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


        BackgroundFill backgroundFill = null;

        ObservableList<Node> childrenVBox = vBoxBloquePropio.getChildren();

        /*TODO crear metodo para crear la cajaBatallon , reutilizar código de aquí para no repetir código, ver si se puede reutilizar el printRecursos()
           para printear recursos donde haga falta      
         */
        //Ciudad propia
        if (getJugadorPrimaryStageController().listaCiudadesPropias.containsKey(ciudadMapa.getPosition())) {


            //nombre
            nombreCiudad = new Label(ciudadMapa.getNameCity());
            nombreCiudad.setTextAlignment(CENTER);
            nombreCiudad.setAlignment(Pos.CENTER);
            nombreCiudad.setWrapText(true);
            childrenVBox.add(nombreCiudad);
            //Imagen ciudad
            imgViewCiudad = new ImageView(imageView.getImage());
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

            boolean esAliado = false;
            for (Jugador jugador : getClanPrimaryStageController().getJugadoresDelClan().values()) {
                //TODO esta condición hace que también se pinten mis ciudades con background GREEN en vez del ANTIQUEWHITE, hay que revisarlo
                if (jugador.listaCiudadesPropias.containsKey(ciudadMapa.getPosition())) {
                    esAliado = true;
                    break;
                }
            }
            if (esAliado) {
                backgroundFill = new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY);
            } else {
                backgroundFill = new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY);
            }

            printRecursos(childrenVBox, ciudadMapa.getRecursosTreeMap().entrySet(), 2);

            vBoxBloquePropio.setMargin(descripcionCiudad, new Insets(0, 15, 0, 15));

        }
        //Ciudad enemiga o aliada
        else {

            backgroundFill = new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY);
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

    private static VBox cajaBatallon(ArrayList<Batallon> listaBatallones, ImageView imageView) {


        //Objetos de ciudad
        Label nombreBatallon = null;
        ImageView imgViewBatallon = null;


        //BLOQUE
        VBox vBoxBloquePropio = new VBox();
        vBoxBloquePropio.setMinWidth(200);
        vBoxBloquePropio.setMaxWidth(200);
        vBoxBloquePropio.setAlignment(TOP_CENTER);

        ObservableList<Node> childrenVBox = vBoxBloquePropio.getChildren();

        /*TODO crear metodo para crear la cajaBatallon , reutilizar código de aquí para no repetir código, ver si se puede reutilizar el printRecursos()
           para printear recursos donde haga falta,
           problema!! --> como puedo printear los distintos tipos de soldados, si ahora la clase Soldados no tiene tipo de soldado(tienen id tipo int)
         */


        BackgroundFill backgroundFill = null;

        for (Batallon batallon :
                listaBatallones) {
            nombreBatallon = new Label(batallon.getNombre());
            nombreBatallon.setTextAlignment(CENTER);
            nombreBatallon.setAlignment(Pos.CENTER);
            nombreBatallon.setWrapText(true);
            childrenVBox.add(nombreBatallon);
            //Imagen ciudad
            imgViewBatallon = new ImageView(imageView.getImage());
            imgViewBatallon.setPickOnBounds(true);
            imgViewBatallon.setPreserveRatio(true);
            childrenVBox.add(imgViewBatallon);

            vBoxBloquePropio.setMargin(imgViewBatallon, new Insets(0, 15, 0, 15));
            if (getJugadorPrimaryStageController().listaBatallonesPropios.containsKey(batallon.getPosition())) {
                backgroundFill = new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY);

            } else {
                boolean xi = false;
                for (Jugador jugador : getClanPrimaryStageController().getJugadoresDelClan().values()) {
                    if (jugador.listaBatallonesPropios.containsKey(batallon.getPosition())) {
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


    private static void createMenuLeft(BorderPane borderPane, ImageView imageView, Ciudad ciudad, ArrayList<Batallon> batallones) {
        List<VBox> vBoxList = new ArrayList<>();

        String text = "";
        if (ciudad != null) {
            text = ciudad.getNameCity();
            vBoxList.add(cajaCiudad(ciudad, imageView));
        }
        if (batallones != null) {
            //TODO hacer metodo de cajaBatallon
            for (Batallon batallon : batallones) {
                text = text + " " + batallon.getNombre();
                vBoxList.add(cajaBatallon(batallones, imageView));
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

    public void toMundo(MouseEvent mouseEvent) {//TODO ES POSIBLE QUE EN ESTE MAPA NO INTERESE TENER ESTO
        try {
            new PrimaryStageControler().reload(getStagePrimaryStageController(), CiudadController.THIS_RUTE, false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

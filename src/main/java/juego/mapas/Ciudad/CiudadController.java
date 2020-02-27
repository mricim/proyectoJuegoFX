package main.java.juego.mapas.Ciudad;

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
import main.java.Jugadores.Jugador;
import main.java.Utils.PrimaryStageControler;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.Ciudad.ContentCity.Edificio;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static javafx.geometry.Pos.TOP_CENTER;
import static javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER;
import static javafx.scene.text.TextAlignment.CENTER;
import static jdk.nashorn.internal.objects.Global.Infinity;
import static main.java.Jugadores.Jugador.*;


public class CiudadController extends PrimaryStageControler implements Initializable {
    private static String RUTE = "../../../resources/mapas/city/";
    static Jugador jugador;
    static boolean basura = true;
    static Ciudad ciudad;

    String nameThisCity;
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
    Label oro, madera, piedra, hierro, comida, poblacion, felicidad, investigacion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jugador = getJugador();
        ciudad = getCiudad();
        nameThisCity = ciudad.getNameCity();

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


        gridPaneMap.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {//Cerrar el menu
//            System.out.println("Limpiar menu izquierda");
//            borderPane.setLeft(null);
            queClicas(null);
        });


        selectorCiudad.setText(nameThisCity);//Seleccionar otra ciudad
        for (Ciudad ciudadTemp : jugador.listaCiudadesPropias.values()) {
            String nameCity = ciudadTemp.getNameCity();
            if (nameThisCity != nameCity) {
                MenuItem menuItem = new MenuItem();
                menuItem.setText(nameCity);
                menuItem.setOnAction((e) -> {
                    System.out.println("clicado " + nameCity);//TODO CAMBIAR ESTO POR LA NUEVA CIUDAD
                    setCiudad(ciudadTemp);
                    try {
                        new PrimaryStageControler().reload(getStage(), "juego/mapas/Ciudad/ciudad.fxml", false);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                selectorCiudad.getItems().add(menuItem);
            }
        }

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

        Collection<Edificio> posicionEdificios = getCiudad().getListaPosicionesEdificios().values();
        for (Edificio posicionEdificio : posicionEdificios) {
//            Edificio edificio = posicionEdificio.getEdificio();
//            int idEdificio = edificio.getId();
//            int nivelEdificio = edificio.getNivel();

            Image image = posicionEdificio.getImage();
            ImageView imageView = new ImageView(image);
            imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                imageView.setCursor(Cursor.HAND);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                imageView.setImage(posicionEdificio.getImageClicable());
            });
            imageView.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                imageView.setImage(posicionEdificio.getImage());
            });
            imageView.setOnMouseClicked(e -> {
                queClicas(posicionEdificio);//System.out.println("Imagen Edificio clicada: " + listaEdificios.get(idEdificio + "_" + nivelEdificio));
            });
            gridPaneMap.add(imageView, posicionEdificio.getColumnas(), posicionEdificio.getFilas());
        }
    }


    @FXML
    public void botton2(ActionEvent actionEvent) {
        cambiarNombreStage("El nombre que queramos");
        System.out.println("COSASA");
    }

    private void queClicas(Edificio posicionEdificio) {
        if (basura) {
            if (posicionEdificio == null) {
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
                createMenuLeft(borderPane, posicionEdificio);
            }
        }
    }

    private static void createMenuLeft(BorderPane borderPane, Edificio edificio) {
        //EdificiosPreCargados edificiosPreCargados = listaEdificiosPreCargada.get(edificio.getId() + "_" + edificio.getNivel());
//        EdificiosPreCargados edificiosPreCargado = edificio.getEdificiosPreCargados();
        List<VBox> vBoxList = new ArrayList<>();

        int edificioId = edificio.getId();
        int edificioNivel = edificio.getNivel();
        if (edificioId != 0) {// NO PARCELA
            vBoxList.add(cajaEdificio(false, edificio, 0));
            if (edificioNivel > 0) {
                try {
                    vBoxList.add(cajaEdificio(false, listaEdificiosPreCargados.get(edificioId + "_" + (edificioNivel + 1)), 2));
                } catch (Exception e) {
                }
                vBoxList.add(cajaEdificio(false, listaEdificiosPreCargados.get(edificioId + "_" + (edificioNivel - 1)), 3));
            } else {
                try {
                    vBoxList.add(cajaEdificio(false, listaEdificiosPreCargados.get(edificioId + "_" + (edificioNivel + 1)), 2));
                } catch (Exception e) {
                }
                if (edificio.getEdificiosPreCargado().isDestruible()){
                vBoxList.add(cajaEdificio(false, listaEdificiosPreCargados.get(0 + "_" + 0), 4));
            }}


        } else {//PARCELAS
            Collection<Edificio> posicionEdificios = getCiudad().getListaPosicionesEdificios().values();
            TreeMap<Integer, Integer> counterTiposDeEdificioEnLaCiudad = new TreeMap<>();
            for (Edificio edificio1 : posicionEdificios) {
                int id = edificio1.getId();
                if (counterTiposDeEdificioEnLaCiudad.containsKey(id)) {
                    counterTiposDeEdificioEnLaCiudad.put(id, counterTiposDeEdificioEnLaCiudad.get(id) + 1);
                } else {
                    counterTiposDeEdificioEnLaCiudad.put(id, 1);
                }
            }
            vBoxList.add(cajaEdificio(true, edificio, 0));
            for (Map.Entry<String, EdificiosPreCargados> preCargadosEntry : listaEdificiosPreCargados.entrySet()) {
                EdificiosPreCargados temp = preCargadosEntry.getValue();
                if (temp.getNivel() == 0 && temp.isConstruible() && ciudad.getNivelCiudad() >= temp.getNivelCastilloNecesario()) {
                    System.out.println(temp.getNombre()+" "+temp.getNivel()+" "+temp.isConstruible() +" "+ ciudad.getNivelCiudad() +" "+ temp.getNivelCastilloNecesario());
                    System.out.println(temp.getNombre()+" "+counterTiposDeEdificioEnLaCiudad.get(temp.getId())+" "+counterTiposDeEdificioEnLaCiudad.get(temp.getId())+">"+temp.getMaximoEdificiosDelMismoTipo());
                    if (counterTiposDeEdificioEnLaCiudad.get(temp.getId()) == null || counterTiposDeEdificioEnLaCiudad.get(temp.getId()) < temp.getMaximoEdificiosDelMismoTipo()) {
                        vBoxList.add(cajaEdificio(false, temp, 1));
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

    private static VBox cajaEdificio(boolean parcela, Edificio edificio, int tipoDeBoton) {
        return cajaEdificio(parcela, edificio.getEdificiosPreCargado(), tipoDeBoton);
    }

    private static VBox cajaEdificio(boolean parcela, EdificiosPreCargados edificio, int tipoDeBoton) {

        //BLOQUE
        VBox vBoxBloquePropio = new VBox();
        vBoxBloquePropio.setMinWidth(200);
        vBoxBloquePropio.setMaxWidth(200);
        vBoxBloquePropio.setAlignment(TOP_CENTER);
        vBoxBloquePropio.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        ObservableList<Node> childrenVBox = vBoxBloquePropio.getChildren();

        Label nombreEdificioPropio = new Label(edificio.getNombre());
        nombreEdificioPropio.setTextAlignment(CENTER);
        nombreEdificioPropio.setAlignment(Pos.CENTER);
        nombreEdificioPropio.setWrapText(true);
        childrenVBox.add(nombreEdificioPropio);

        ImageView imageViewPropio = new ImageView(edificio.getImage());
        imageViewPropio.setPickOnBounds(true);
        imageViewPropio.setPreserveRatio(true);
        childrenVBox.add(imageViewPropio);

        Label nivelEdificioPropio = new Label("Nivel: " + edificio.getNivel());
        nivelEdificioPropio.setTextAlignment(CENTER);
        nivelEdificioPropio.setAlignment(Pos.CENTER);
        nivelEdificioPropio.setWrapText(true);
        childrenVBox.add(nivelEdificioPropio);

        Label descripcionEdificioPropio = new Label(edificio.getDescripcion());
        descripcionEdificioPropio.setTextAlignment(CENTER);
        descripcionEdificioPropio.setAlignment(Pos.CENTER);
        descripcionEdificioPropio.setWrapText(true);
        childrenVBox.add(descripcionEdificioPropio);
        vBoxBloquePropio.setMargin(descripcionEdificioPropio, new Insets(0, 15, 0, 15));
        if (!parcela) {
            FlowPane flowPane = new FlowPane();
            flowPane.setHgap(10);
            flowPane.setVgap(10);
            flowPane.setAlignment(Pos.CENTER);
            ObservableList<Node> childrenFlowPane = flowPane.getChildren();


            boolean paso1 = false;
            for (Map.Entry<Integer, Recursos> recurso : edificio.getRecursosProductores().entrySet()) {
                Recursos recursoValor = recurso.getValue();
                int produce = recursoValor.getCantidad();
                if (produce != 0) {
                    ImageView imageView = new ImageView(recursoValor.getImage());
                    imageView.setFitWidth(25);
                    imageView.setFitHeight(25);
                    Label label = new Label();
                    if (produce > 0) {
                        label.setText("+" + produce+"/h");
                        label.setTextFill(Color.GREEN);
                    } else if (produce < 0) {
                        label.setText(String.valueOf(produce));
                        label.setTextFill(Color.RED);
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
            boolean paso2 = false;
            for (Map.Entry<Integer, Recursos> recurso : edificio.getRecursosAlmacen().entrySet()) {
                Recursos recursoValor = recurso.getValue();
                int almacena = recursoValor.getCantidad();
                if (almacena != 0) {
                    ImageView imageView = new ImageView(recursoValor.getImage());
                    imageView.setFitWidth(25);
                    imageView.setFitHeight(25);
                    Label label = new Label();
                    label.setText(String.valueOf(almacena));
                    label.setGraphic(imageView);
                    label.setTextAlignment(CENTER);
                    label.setAlignment(Pos.CENTER);
                    label.setWrapText(true);
                    childrenFlowPane.add(label);
                    paso2 = true;
                }
            }
            if (paso2) {
                Separator separator = new Separator();
                separator.setPrefWidth(200);
                childrenFlowPane.add(separator);
            }
            vBoxBloquePropio.setMargin(flowPane, new Insets(0, 15, 0, 15));
            childrenVBox.add(flowPane);
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
            if (tipoDeBoton != 0) {
                Button button = new Button(text);
                childrenVBox.add(button);
                Separator separator = new Separator();
                separator.setPrefWidth(200);
                childrenVBox.add(separator);
            }

        }
        //FIN BLOQUE
        return vBoxBloquePropio;
    }


    public void toMundo(MouseEvent mouseEvent) {
        try {
            new PrimaryStageControler().reload(getStage(), "juego/mapas/Mundo/mundo.fxml", false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

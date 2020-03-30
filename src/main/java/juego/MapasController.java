package main.java.juego;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.juego.mapas.ciudad.CiudadController;
import main.java.juego.mapas.mundo.MundoController;
import main.java.juego.mapas.pelea.PeleaController;
import main.java.utils.PrimaryStageControler;
import main.java.juego.mapas.ciudad.Ciudad;
import main.java.juego.mapas.Recursos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

import static javafx.geometry.Pos.TOP_CENTER;
import static javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER;
import static jdk.nashorn.internal.objects.Global.Infinity;

abstract public class MapasController extends PrimaryStageControler {
    public static boolean newCiudad;
    public static boolean primeraCiudad;
    public static String nameThisCityController;




    public void inicialiceController() {
        nameThisCityController = getCiudadPrimaryStageController().getNameCity();
    }

    public static void recursosMenu(FlowPane flowPaneRecuros, Class<? extends MapasController> aClass) {
        ArrayList<Recursos> listaRecursosEnMenu = new ArrayList<>();
        listaRecursosEnMenu.addAll(getCiudadPrimaryStageController().getRecursosTreeMap().values());
        listaRecursosEnMenu.addAll(getJugadorPrimaryStageController().getRecursosJugador().values());

        recursosMenu(flowPaneRecuros, listaRecursosEnMenu, aClass);
    }

    public static void recursosMenu(FlowPane flowPane, ArrayList<Recursos> recursos2, Class<? extends MapasController> aClass) {
        ObservableList<Node> observableList = flowPane.getChildren();
        observableList.clear();
        int numero = 0;
        if (CiudadController.class.equals(aClass)) {
            numero=1;
        } else if (MundoController.class.equals(aClass)) {
            numero=2;
        } else if (PeleaController.class.equals(aClass)) {
            numero=3;
        }
        for (Recursos recursos : recursos2) {
            boolean estaEnesteMenu=false;
            if (numero==1&&recursos.getRecursosPrecargados().isMenuCiudad()) {
                estaEnesteMenu=true;
            }else if (numero==2&&recursos.getRecursosPrecargados().isMenuMundo()) {
                estaEnesteMenu=true;
            }else if (numero==3&&recursos.getRecursosPrecargados().isMenupelea()) {
                estaEnesteMenu=true;
            }
            if (estaEnesteMenu){
                Label label = new Label(String.valueOf(recursos.getCantidad()));
                ImageView imageView = new ImageView(recursos.getImage());
                imageView.setFitWidth(30);
                imageView.setFitHeight(30);
                HBox hBox = new HBox(imageView, label);
                hBox.setId(String.valueOf(recursos.getId()));
                hBox.setAlignment(Pos.CENTER);
                hBox.setPrefHeight(20.0);
                hBox.setMinWidth(70.0);
                hBox.setSpacing(5.0);

                observableList.add(hBox);
            }
        }
    }

    public static void selectorDeCiudad(SplitMenuButton selectorCiudad, Class<? extends MapasController> toCity) {
        selectorCiudad.setText(nameThisCityController);//Seleccionar otra ciudad
        for (Ciudad ciudadTemp : getJugadorPrimaryStageController().listaCiudadesPropias.values()) {
            String nameCity = ciudadTemp.getNameCity();
            if (nameThisCityController != nameCity) {
                MenuItem menuItem = new MenuItem();
                menuItem.setText(nameCity);
                menuItem.setOnAction((e) -> {
                    setCiudadPrimaryStageController(ciudadTemp);
                    reload(toCity);
                });
                selectorCiudad.getItems().add(menuItem);
            }
        }
    }

    protected static void rellenador(BorderPane borderPane, List<VBox> vBoxList,int tamaño) {
        VBox vBox = new VBox();
        vBox.setMinWidth(tamaño);
        vBox.setMaxWidth(tamaño);
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

        scrollPane.setPannable(true);
        scrollPane.setHbarPolicy(NEVER);
        scrollPane.setVbarPolicy(NEVER);
        //scrollPane.()BorderPane.alignment="CENTER"

        borderPane.setLeft(scrollPane);
    }
}

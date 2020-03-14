package main.java.juego;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import main.java.Utils.PrimaryStageControler;
import main.java.juego.mapas.ciudad.Ciudad;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.ciudad.CiudadController;

import java.io.IOException;
import java.util.Collection;

abstract public class MapasController extends PrimaryStageControler {
    public static boolean newCiudad;
    public static boolean primeraCiudad;
    public static String nameThisCityController;


    public void inicialiceController() {
            nameThisCityController = getCiudadPrimaryStageController().getNameCity();
    }

    public void recursosMenu(FlowPane flowPaneRecuros) {
        recursosMenu(flowPaneRecuros, getCiudadPrimaryStageController().getRecursosTreeMap().values());
    }

    public static void recursosMenu(FlowPane flowPane, Collection<Recursos> recursos2) {
        ObservableList<Node> observableList = flowPane.getChildren();
        observableList.clear();
        for (Recursos recursos : recursos2) {
            Label label = new Label(String.valueOf(recursos.getCantidad()));
            ImageView imageView = new ImageView(recursos.getImage());
            imageView.setFitWidth(30.0);
            imageView.setFitHeight(30.0);
            HBox hBox = new HBox(imageView, label);
            hBox.setId(String.valueOf(recursos.getId()));
            hBox.setAlignment(Pos.CENTER);
            hBox.setPrefHeight(20.0);
            hBox.setMinWidth(70.0);
            hBox.setSpacing(5.0);

            observableList.add(hBox);
        }
    }

    public static void selectorDeCiudad(SplitMenuButton selectorCiudad, boolean toCity) {
        selectorCiudad.setText(nameThisCityController);//Seleccionar otra ciudad
        for (Ciudad ciudadTemp : getJugadorPrimaryStageController().listaCiudadesPropias.values()) {
            String nameCity = ciudadTemp.getNameCity();
            if (nameThisCityController != nameCity) {
                MenuItem menuItem = new MenuItem();
                menuItem.setText(nameCity);
                menuItem.setOnAction((e) -> {
                    setCiudadPrimaryStageController(ciudadTemp);
                    if (toCity) {
                        reload(CiudadController.class);
                    }
                });
                selectorCiudad.getItems().add(menuItem);
            }
        }
    }

}

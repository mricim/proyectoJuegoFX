package main.java.juego;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import main.java.Jugadores.Jugador;
import main.java.Utils.PrimaryStageControler;
import main.java.juego.mapas.Ciudad.Ciudad;
import main.java.juego.mapas.Recursos;

import java.io.IOException;
import java.util.Collection;

public class MapasController extends PrimaryStageControler {
    public static Jugador jugadorController;
    public static Ciudad ciudadController;
    public static String nameThisCityController;


    public void inicialiceController() {
        jugadorController = getJugador();
        ciudadController = getCiudad();
        nameThisCityController = ciudadController.getNameCity();

    }

    public void recursosMenu(FlowPane flowPane, Collection<Recursos> recursos2) {
        ObservableList<Node> observableList = flowPane.getChildren();
        for (Recursos recursos : recursos2) {
            Label label = new Label(String.valueOf(recursos.getCantidad()));
            ImageView imageView = new ImageView(recursos.getImage());
            imageView.setFitWidth(30.0);
            imageView.setFitHeight(30.0);
            HBox hBox = new HBox(imageView, label);
            hBox.setId(String.valueOf(recursos.getId()));
            hBox.setAlignment(Pos.CENTER);
            hBox.setPrefHeight(20.0);
            hBox.setPrefWidth(70.0);
            hBox.setSpacing(5.0);

            observableList.add(hBox);
        }
    }

    public void selectorDeCiudad(String ruteController, SplitMenuButton selectorCiudad) {
        selectorCiudad.setText(nameThisCityController);//Seleccionar otra ciudad
        for (Ciudad ciudadTemp : jugadorController.listaCiudadesPropias.values()) {
            String nameCity = ciudadTemp.getNameCity();
            if (nameThisCityController != nameCity) {
                MenuItem menuItem = new MenuItem();
                menuItem.setText(nameCity);
                menuItem.setOnAction((e) -> {
                    setCiudad(ciudadTemp);
                    try {
                        new PrimaryStageControler().reload(getStage(), ruteController, false);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                selectorCiudad.getItems().add(menuItem);
            }
        }
    }

}

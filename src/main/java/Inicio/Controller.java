package main.java.Inicio;

import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import main.java.Jugadores.Jugador;
import main.java.Utils.PrimaryStageControler;
import main.java.juego.mapas.Ciudad.CiudadController;
import main.java.juego.mapas.Ciudad.EdificiosPreCargados;
import main.java.juego.mapas.Pelea.SoldadosPreCargados;
import main.java.login.LoginHiperFalso;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Controller extends PrimaryStageControler implements Initializable {
    private static String RUTE = "../../../resources/Inicio/";
    public ProgressBar progresBar;

    int idJugador;

    public javafx.scene.control.Button iniciarJuego;
    public javafx.scene.control.Button loadSesion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void iniciarSession() {
        loadSesion.setDisable(true);
        idJugador = LoginHiperFalso.devuelveElIdDeLaCuenta();
        iniciarJuego.setDisable(false);

        progresBar.setDisable(false);
    }

    public void iniciarJuego() {
        iniciarJuego.setDisable(true);
        progresBar.setProgress(10);//TODO NO FUNCIONA // https://stackoverflow.com/questions/44398611/running-a-process-in-a-separate-thread-so-rest-of-java-fx-application-is-usable
        callbd();
        progresBar.setProgress(100);
        try {
            new PrimaryStageControler().reload(getStage(), CiudadController.THIS_RUTE, true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void callbd() {
        //TODO LEER DESDE LA BD
        new EdificiosPreCargados(0, "parcela-Construible", "Descripción del edificio que sera mas larga que el nombre del edificio", false, false, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);//NO MEJORABLE
        new EdificiosPreCargados(1, "Castillo", "Descripción del edificio que sera mas larga que el nombre del edificio", false, false, 1, 0, -1, 99, 99, 99, 99, 10, 1000, 1000, 1000, 1000, 0, 10, 1, 1000, 1000, 1000, 1000, 100);
        new EdificiosPreCargados(1, "Castillo", "Descripción 1_1", false, false, 1, 1, 0, 99, 99, 99, 99, 10, 1000, 1000, 1000, 1000, 0, 10, 1, 1000, 1000, 1000, 1000, 200);
        new EdificiosPreCargados(1, "Castillo", "X", false, false, 1, 2, 1, 99, 99, 99, 99, 10, 1000, 1000, 1000, 1000, 0, 10, 1, 1000, 1000, 1000, 1000, 300);
        new EdificiosPreCargados(2, "Muralla", "XXXXXXXXXXX", false, false, 2, 0, 0, 99, 99, 99, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        new EdificiosPreCargados(2, "Muralla", "XXXXXXXXXXX", false, false, 2, 1, 0, 99, 99, 99, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        progresBar.setProgress(20);
        new EdificiosPreCargados(10, "Almacen", "XXXXXXXXXXX", true, true, 2, 0, -1, 99, 19, 25, 30, 10, 0, 0, 0, 0, 0, 0, 0, 1000, 1000, 1000, 1000, 1000);
        new EdificiosPreCargados(11, "Centro cientifico", "NONE", true, true, 1, 0, 0, 99, 99, 99, 20, 10, 10, 10, 10, 10, 10, 10, 50, 0, 0, 0, 0, 0);
        new EdificiosPreCargados(12, "Lupas", "XXXXXXXXXXX", true, true, 2, 0, 0, 99, 99, 99, 20, 10, 10, 10, 10, 10, 10, 10, -50, 0, 0, 0, 0, 0);
        new EdificiosPreCargados(12, "Lupas", "XXXXXXXXXXX", true, true, 2, 1, 0, 99, 99, 99, 20, 10, 10, 10, 10, 10, 10, 10, -50, 0, 0, 0, 0, 0);
        new EdificiosPreCargados(13, "Palacio", "NONE", true, true, 1, 0, 0, 99, 99, 99, 20, 10, 10, 10, 10, 10, 10, 10, 50, 0, 0, 0, 0, 0);
        new EdificiosPreCargados(14, "Maquinas de guerra", "NONE", true, true, 1, 0, 0, 99, 99, 99, 20, 10, 10, 10, 10, 10, 10, 10, 50, 0, 0, 0, 0, 0);
        progresBar.setProgress(30);

        new SoldadosPreCargados(0, "Espadachines", 0);//se podria poner comida
        new SoldadosPreCargados(1, "Lanceros", 0);
        new SoldadosPreCargados(2, "Arqueros", 100);
        new SoldadosPreCargados(3, "Caballeros", 50);
        progresBar.setProgress(50);
        //TODO DESDE LA BD
        setJugador(new Jugador(idJugador, "pepito", 500));
        new Jugador(2, "juan", 300);
        progresBar.setProgress(70);
        progresBar.setProgress(80);
    }
}

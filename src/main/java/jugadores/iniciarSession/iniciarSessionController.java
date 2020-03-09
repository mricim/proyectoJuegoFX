package main.java.jugadores.iniciarSession;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.java.Utils.PrimaryStageControler;
import main.java.juego.mapas.RecursosPrecargados;
import main.java.juego.mapas.ciudad.CiudadController;
import main.java.juego.mapas.ciudad.EdificiosPreCargados;
import main.java.juego.mapas.pelea.SoldadosPreCargados;
import main.java.jugadores.Clan;
import main.java.jugadores.Jugador;
import main.java.login.LoginHiperFalso;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class iniciarSessionController extends PrimaryStageControler implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void iniciarSession(MouseEvent mouseEvent) {
        Node source = (Node) mouseEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}

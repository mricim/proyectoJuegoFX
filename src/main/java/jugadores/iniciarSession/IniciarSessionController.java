package main.java.jugadores.iniciarSession;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.java.Inicio.PantallaInicialController;
import main.java.utils.PrimaryStageControler;

import java.net.URL;
import java.util.ResourceBundle;


public class IniciarSessionController extends PrimaryStageControler implements Initializable {

    public static final String RUTE_FXML = "jugadores/iniciarSession/iniciarSession.fxml";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void iniciarSession(MouseEvent mouseEvent) {
        Node source = (Node) mouseEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        PantallaInicialController.idJugadorTemp = 1;
        PantallaInicialController.nameJugadorTemp = "tu nombre";
        PantallaInicialController.emailJugadorTemp = "unemail@gmail.com";
        stage.close();
    }


}

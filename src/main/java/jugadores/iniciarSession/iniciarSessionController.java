package main.java.jugadores.iniciarSession;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.java.Utils.PrimaryStageControler;

import java.net.URL;
import java.util.ResourceBundle;


public class iniciarSessionController extends PrimaryStageControler implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void iniciarSession(MouseEvent mouseEvent) {
        Node source = (Node) mouseEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        idJugadorTemp = 1;
        nameJugadorTemp = "tu nombre";
        emailJugadorTemp = "unemail@gmail.com";
        stage.close();
    }
}

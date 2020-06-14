package main.java.jugadores.iniciarSession;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.java.Inicio.PantallaInicialController;
import main.java.mysql.JDBC;
import main.java.mysql.PersonSQL;
import main.java.utils.Encriptacio;
import main.java.utils.PrimaryStageControler;
import main.java.utils.tagsFX.CustomAlert;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;


public class IniciarSessionController extends PrimaryStageControler implements Initializable {
    //https://stackoverflow.com/questions/44234719/encryption-between-php-java
    public static final String RUTE_FXML = "jugadores/iniciarSession/iniciarSession.fxml";


    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void iniciarSession(MouseEvent mouseEvent) {
        Node source = (Node) mouseEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        //
        String email = emailField.getText();
        String encriptedMail = Encriptacio.encrypt(email);
        PersonSQL encripteuser = JDBC.emailToUser(encriptedMail);
//        System.out.println("FFFFFFFFFFFF");
//        System.out.println(encripteuser.getId());
//        System.out.println(encripteuser.getName());
//        System.out.println(encripteuser.getNameEncripted());
//        System.out.println(encripteuser.getEmail());
//        System.out.println(encripteuser.getEmailEncripted());
//        System.out.println("FFFFFFFFFFFF");
        try {
            if (checkPass(encripteuser.getEmail(), passwordField.getText(), encripteuser.getName(), encripteuser.getPassword())) {
                System.out.println(email);
                //
                PantallaInicialController.idJugadorTemp = encripteuser.getId();
                PantallaInicialController.nameJugadorTemp = encripteuser.getName();
                PantallaInicialController.emailJugadorTemp = encripteuser.getEmail();
            }
        } catch (NullPointerException ignore) {
            CustomAlert alert = new CustomAlert(Alert.AlertType.WARNING);
            alert.setTitle(TRADUCCIONES_GENERALES.getString("warning.dialog"));
            alert.setHeaderText(TRADUCCIONES_GENERALES.getString("bad.login"));
            alert.setContentText(TRADUCCIONES_GENERALES.getString("login.incorrect"));
            alert.showAndWait();
        }
        stage.close();
    }

    private boolean checkPass(String email, String password, String name, String db) {

        return (Encriptacio.sha256(email + password + name) + email.length()).equals(db);
    }

    public void openBrowser(MouseEvent mouseEvent) {
        try {
            Desktop.getDesktop().browse(new URL("http://www.armegis.tk").toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}

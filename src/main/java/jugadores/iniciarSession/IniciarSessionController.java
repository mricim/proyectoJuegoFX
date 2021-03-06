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
import main.java.dataBase.Mariadb;
import main.java.dataBase.Mysql;
import main.java.dataBase.PersonSQL;
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


    public void dataToIniciarSession(MouseEvent mouseEvent) {
        Node source = (Node) mouseEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        //
        String email = emailField.getText();
        String encriptedMail = Encriptacio.encrypt(email);
        PersonSQL personSQL = Mariadb.emailToUser(encriptedMail);
//        System.out.println("FFFFFFFFFFFF");
//        System.out.println(encripteuser.getId());
//        System.out.println(encripteuser.getName());
//        System.out.println(encripteuser.getNameEncripted());
//        System.out.println(encripteuser.getEmail());
//        System.out.println(encripteuser.getEmailEncripted());
//        System.out.println("FFFFFFFFFFFF");
        PantallaInicialController.iniciarSession(personSQL,passwordField.getText());
        stage.close();
    }

    public static boolean checkPass(String email, String password, String name, String db) {

        return (Encriptacio.sha256(email + password + name) + email.length()).equals(db);
    }

    public void openBrowser(MouseEvent mouseEvent) {
        try {
            System.out.println();
            Desktop.getDesktop().browse(new URL(TRADUCCIONES_GENERALES.getString("url")).toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            CustomAlert alert = new CustomAlert(Alert.AlertType.WARNING);
            alert.setTitle(TRADUCCIONES_GENERALES.getString("warning.dialog"));
            alert.setHeaderText("Error");
            alert.setContentText("This button has had a problem. Go to "+TRADUCCIONES_GENERALES.getString("url"));
            alert.showAndWait();
        }
    }
}

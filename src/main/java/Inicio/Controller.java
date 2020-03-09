package main.java.Inicio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.java.Main;
import main.java.juego.mapas.RecursosPrecargados;
import main.java.jugadores.Clan;
import main.java.jugadores.Jugador;
import main.java.Utils.PrimaryStageControler;
import main.java.juego.mapas.ciudad.CiudadController;
import main.java.juego.mapas.ciudad.EdificiosPreCargados;
import main.java.juego.mapas.pelea.SoldadosPreCargados;
import main.java.login.LoginHiperFalso;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Controller extends PrimaryStageControler implements Initializable {
    private static String RUTE = "../../../resources/Inicio/";
    public ProgressBar progresBar;
    public ComboBox<String> seleccionarMundo;

    public javafx.scene.control.Button iniciarJuego;
    public javafx.scene.control.Button loadSesion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> a = new ArrayList<>();
        File rutes = new File(Main.RUTEINTERNAL + "resources/images/temas/");


        File[] f = rutes.listFiles();
        int x = Objects.requireNonNull(f).length;
        for (int i = 0; i < x; i++) {
            String str = f[i].getName().replaceAll("[0-9]", "").replaceAll("(.)([A-Z])", "$1 $2");
            a.add((str.substring(0, 1).toUpperCase() + str.substring(1)));
        }
        seleccionarMundo.getItems().addAll(a);


    }

    boolean sesioniniciada = false;
    boolean mundoSeleccionado = false;
    int idJugador;
    String mundoSeleccionadoName = null;

    public void iniciarSession() {
        try {
            showDialog(stagePrimaryStageController, Modality.APPLICATION_MODAL);
        } catch (IOException e) {
            e.printStackTrace();
        }


        loadSesion.setDisable(true);
        idJugador = LoginHiperFalso.devuelveElIdDeLaCuenta();

        sesioniniciada = true;
        iniciarJuegoEnable();
    }

    public void selecotorMundo(ActionEvent actionEvent) {
        mundoSeleccionadoName = seleccionarMundo.getValue();

        mundoSeleccionado = true;
        iniciarJuegoEnable();
    }

    private void iniciarJuegoEnable() {
        if (sesioniniciada && mundoSeleccionado) {
            iniciarJuego.setDisable(false);
            progresBar.setDisable(false);
        }
    }

    public void iniciarJuego() {
        iniciarJuego.setDisable(true);
        NAME_TEMA = mundoSeleccionadoName;
        progresBar.setProgress(10);//TODO NO FUNCIONA // https://stackoverflow.com/questions/44398611/running-a-process-in-a-separate-thread-so-rest-of-java-fx-application-is-usable
        callbd();
        progresBar.setProgress(100);
        try {
            reload(getStagePrimaryStageController(), CiudadController.THIS_RUTE, true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private void callbd() {
        //TODO LEER DESDE LA BD
        new RecursosPrecargados(0, "Oro",false);
        new RecursosPrecargados(1, "Madera",false);
        new RecursosPrecargados(2, "Piedra",false);
        new RecursosPrecargados(3, "Comida",false);
        new RecursosPrecargados(4, "Hierro",false);
        new RecursosPrecargados(5, "Poblacion",true);
        new RecursosPrecargados(6, "Felicidad",false);
        new RecursosPrecargados(7, "investigacion",false);
        progresBar.setProgress(15);
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
        Clan clan = new Clan(1, "Los mejores");
        Jugador jugador = new Jugador(idJugador, "pepito", 500);
        setJugadorPrimaryStageController(jugador);
        setClanPrimaryStageController(clan);
        progresBar.setProgress(60);
        new Jugador(2, "juan", 300);
        new Jugador(3, "pedro", 8000);
        progresBar.setProgress(70);
        clan.addJugadorClan(jugador);
        clan.addJugadorClan(3);
        progresBar.setProgress(80);
    }

    private void showDialog(Window owner, Modality modality) throws IOException {
        // Create a Stage with specified owner and modality
        final Stage stage = new Stage();
        stage.initOwner(owner);
        stage.initModality(modality);
        //TODO
        FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource("/main/java/jugadores/iniciarSession/iniciarSession.fxml");
        loader.setLocation(url);
        Parent root = loader.load();
        stage.setTitle("Nombre del juego");
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("main.resources/style/styles.css");
        stage.setScene(scene);
        //primaryStage.setMaximized(true);//Pone el Stage en maximizado
        stage.show();


        //TODO

/*
        // Create the Label
        Label modalityLabel = new Label(modality.toString());
        // Create the Button
        Button closeButton = new Button("Close");
        // Add the EventHandler to the Button
        closeButton.setOnAction(new EventHandler <ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                stage.close();
            }
        });

        // Create the VBox
        VBox root1 = new VBox();
        // Add the Children to the VBox
        root1.getChildren().addAll(modalityLabel, closeButton);

        // Create the Scene
        Scene scene5 = new Scene(root1, 200, 100);
        // Add the Scene to the Stage
        stage.setScene(scene5);
        // Set the Title of the Stage
        stage.setTitle("A Dialog Box");
        // Display the Stage

 */
        stage.show();
    }
}

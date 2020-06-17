package main.java.Inicio;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.java.juego.MapasController;
import main.java.juego.mapas.mundo.MundoController;
import main.java.mysql.PersonSQL;
import main.java.utils.PrimaryStageControler;
import main.java.juego.mapas.ciudad.CiudadController;
import main.java.jugadores.iniciarSession.IniciarSessionController;
import main.java.temas.Temas;
import main.java.utils.propietiesAndPreferences.PreferencesApp;
import main.java.utils.tagsFX.CustomAlert;
import main.java.utils.tagsFX.CustomSeparator;
import main.java.utils.traductor.Traductor;

import java.net.URL;
import java.util.*;

import static main.java.temas.Temas.arrayListTemas;
import static main.java.utils.traductor.Traductor.listaIdiomasPath;


public class PantallaInicialController extends PrimaryStageControler implements Initializable {
    //TEMAS
    public static Temas elTemaSeleccionado;
    //

    @FXML
    public ProgressBar progresBar;
    public HBox seleccionarIdioma;
    public ComboBox<String> seleccionarMundo;
    public Button iniciarJuego;
    public static Button loadSesion;
    public VBox aCambiar;

    public static void iniciarSession(PersonSQL personSQL) {
        iniciarSession(personSQL, null);
    }

    public static void iniciarSession(PersonSQL personSQLm, String passwordField) {
        System.out.println("iniciarSession()");
        if (personSQLm != null) {
            try {
                String password;
                if (passwordField == null) {
                    password = personSQLm.getPassword();
                } else {
                    password = passwordField;
                }
                System.out.println(IniciarSessionController.checkPass(personSQLm.getEmail(), password, personSQLm.getName(), personSQLm.getPassword()));
                if (IniciarSessionController.checkPass(personSQLm.getEmail(), password, personSQLm.getName(), personSQLm.getPassword())) {
                    personSQL = personSQLm;
                    if (passwordField != null) {
                        PreferencesApp.writeFilePrefereces(personSQLm);
                    }
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//https://stackoverflow.com/questions/32362802/javafx-combobox-cells-disappear-when-clicked
        ComboBox<String> comboBox = Traductor.traduccciones(listaIdiomasPath, PantallaInicialController.class);
        comboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    seleccionarIdioma(newValue);
                }
        );
        seleccionarIdioma.getChildren().add(comboBox);
//
        seleccionarMundo.getItems().addAll(arrayListTemas);

        //sesioniniciada = true;
        //idJugadorTemp = 1;

        //iniciarSession(null, null);
        if (personSQL != null) {
            sesionIniciada();
        } else {
            Button button = new Button(TRADUCCIONES_GENERALES.getString("iniciarSession"));
            button.setOnMouseClicked(e -> {
                iniciarSession();
            });
            aCambiar.getChildren().add(button);
        }
    }

    private void seleccionarIdioma(String imageName) {
        try {
            String localeString = imageName.substring(1 + imageName.lastIndexOf("/"));
            Locale locale = new Locale(localeString);
            Locale.setDefault(locale);
            System.setProperty("user.language", localeString);
            reloadLanguage(null, stagePrimaryStageController, PantallaInicialController.getPathToFXML(PantallaInicialController.class), false, locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static boolean sesioniniciada = false;
    boolean mundoSeleccionado = false;

    String mundoSeleccionadoName = null;

    public void iniciarSession() {
        try {
            newStageby(getStagePrimaryStageController(), PrimaryStageControler.getPathToFXML(IniciarSessionController.class), false);
            if (personSQL.getId() != 0 && personSQL.getName().length() > 5 && personSQL.getEmail().length() > 5) {
                sesionIniciada();
            } else {
                throw new Exception("No se puede iniciar session");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.err.println("No se ha iniciado sessión");
            CustomAlert alert = new CustomAlert(Alert.AlertType.WARNING);
            alert.setTitle(TRADUCCIONES_GENERALES.getString("warning.dialog"));
            alert.setHeaderText(TRADUCCIONES_GENERALES.getString("bad.login"));
            alert.setContentText(TRADUCCIONES_GENERALES.getString("login.incorrect"));
            alert.showAndWait();
            //
            Button button = new Button(TRADUCCIONES_GENERALES.getString("iniciarSession"));
            button.setOnMouseClicked(f -> {
                iniciarSession();
            });
            aCambiar.getChildren().add(button);
        }


        iniciarJuegoEnable();
    }

    private void sesionIniciada() {
        try {
            loadSesion.setDisable(true);
        } catch (NullPointerException ignore) {
        }
        sesioniniciada = true;
        //CAMBIAR POR EL LOAD TEXTO
        System.out.println("Sesion iniciada");
        aCambiar.getChildren().clear();
        //
        Text text1=new Text(TRADUCCIONES_GENERALES.getString("session.hello")+" ");
        Text text2=new Text(personSQL.getName());
        text2.setStyle("-fx-font-weight: bold");
        Text text3=new Text("!");
        FlowPane flowPane = new FlowPane(text1,text2,text3);
        flowPane.setMaxWidth(150);
        flowPane.setAlignment(Pos.CENTER);
        Button button=new Button(TRADUCCIONES_GENERALES.getString("session.close.session"));
        button.setOnMouseClicked(e -> {
            personSQL=null;
            PreferencesApp.writeFilePrefereces(null);
            reload(this.getClass());
        });
        aCambiar.getChildren().addAll(flowPane, new Label(personSQL.getEmail()),new CustomSeparator(40,true,10),button);
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
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() {
                iniciarJuego.setDisable(true);

                NAME_TEMA = mundoSeleccionadoName;
                char[] a = NAME_TEMA.replaceAll(" ", "").toCharArray();
                a[0] = Character.toLowerCase(a[0]);
                NAME_TEMA_PATH = String.valueOf(a);
                Temas.ruteUse(NAME_TEMA_PATH + "/");
                System.out.println();

                //updateProgress(10, 100);

                TRADUCCIONES_THEMA = ResourceBundle.getBundle("main.resources.traductions.temas." + NAME_TEMA_PATH + ".UIResources", TRADUCCIONES_GENERALES.getLocale());
                for (Temas temas : Temas.listaDeTemas) {
                    if (temas.getName().equals(NAME_TEMA)) {
                        //System.out.println("Tema seleccionado: "+NAME_TEMA);
                        System.out.println("Tema seleccionado: " + temas.getName());
                        elTemaSeleccionado = temas;
                        break;
                    }
                }
                DB.callbdAccordingTema(elTemaSeleccionado);//call DB
                updateProgress(100, 100);
                if (getJugadorPrimaryStageController().listaCiudadesPropias.size() == 0) {//NUEVO EN EL JUEGO
                    MapasController.newCiudad = true;
                    MapasController.primeraCiudad = true;
                    reload(MundoController.class, true);
                } else {
                    PrimaryStageControler.setCiudadPrimaryStageController(getJugadorPrimaryStageController().cargarCiudadPrincipal);//CON ESTO CONTROLAS QUE CIUDAD ESTAS VIENDO!
                    reload(CiudadController.class, true);
                }
                return null;
            }
        };
        progresBar.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
    }
}
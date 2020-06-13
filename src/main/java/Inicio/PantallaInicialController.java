package main.java.Inicio;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.juego.MapasController;
import main.java.juego.mapas.mundo.MundoController;
import main.java.utils.PrimaryStageControler;
import main.java.juego.mapas.ciudad.CiudadController;
import main.java.jugadores.iniciarSession.IniciarSessionController;
import main.java.temas.Temas;
import main.java.utils.traductor.Traductor;

import java.net.URL;
import java.util.*;

import static main.java.temas.Temas.arrayListTemas;
import static main.java.utils.traductor.Traductor.listaIdiomasPath;


public class PantallaInicialController extends PrimaryStageControler implements Initializable {
    //TEMAS
    public static Temas elTemaSeleccionado;
    //
    public static int idJugadorTemp;
    public static String nameJugadorTemp;
    public static String emailJugadorTemp;


    public ProgressBar progresBar;
    public HBox seleccionarIdioma;
    public ComboBox<String> seleccionarMundo;
    public javafx.scene.control.Button iniciarJuego;
    public javafx.scene.control.Button loadSesion;
    public VBox aCambiar;


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

        //sesioniniciada = true;//TODO BORRAR
        //idJugadorTemp = 1;//TODO BORRAR
    }

    private void seleccionarIdioma(String imageName) {
        try {
            String localeString=imageName.substring(1 + imageName.lastIndexOf("/"));
            Locale locale=new Locale(localeString);
            Locale.setDefault(locale);
            System.setProperty("user.language",localeString);
            reloadLanguage(null, stagePrimaryStageController, PantallaInicialController.getPathToFXML(PantallaInicialController.class), false, locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean sesioniniciada = false;
    boolean mundoSeleccionado = false;

    String mundoSeleccionadoName = null;

    public void iniciarSession() {
        try {
            newStageby(getStagePrimaryStageController(), PrimaryStageControler.getPathToFXML(IniciarSessionController.class), false);
            if (idJugadorTemp != 0 && nameJugadorTemp.length() > 5 && emailJugadorTemp.length() > 5) {
                loadSesion.setDisable(true);
                sesioniniciada = true;
                //CAMBIAR POR EL LOAD TEXTO
                System.out.println("PASO");
                aCambiar.getChildren().clear();
                aCambiar.getChildren().addAll(new Label(nameJugadorTemp), new Label(emailJugadorTemp));

            } else {
                throw new Exception("No se puede iniciar session");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.err.println("No se ha iniciado sessión");
        }


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
        char[] a = NAME_TEMA.replaceAll(" ", "").toCharArray();
        a[0] = Character.toLowerCase(a[0]);
        NAME_TEMA_PATH = String.valueOf(a);
        Temas.ruteUse(NAME_TEMA_PATH + "/");
        System.out.println();

        progresBar.setProgress(10);//TODO NO FUNCIONA

        TRADUCCIONES_THEMA = ResourceBundle.getBundle("main.resources.traductions.temas." + NAME_TEMA_PATH + ".UIResources", TRADUCCIONES_GENERALES.getLocale());
        for (Temas temas : Temas.listaDeTemas) {
            if (temas.getName().equals(NAME_TEMA)){
                //System.out.println("Tema seleccionado: "+NAME_TEMA);
                System.out.println("Tema seleccionado: "+temas.getName());
                elTemaSeleccionado=temas;
                break;
            }
        }

        DB.callbdAccordingTema(progresBar,elTemaSeleccionado);
        progresBar.setProgress(100);
        //Jugador borrar=new Jugador("nombre",0);//TODO DESCOMENTAR PARA INICIAR COMO UN NUEVO JUGADOR
        //setJugadorPrimaryStageController(borrar);//TODO DESCOMENTAR PARA INICIAR COMO UN NUEVO JUGADOR
        if (getJugadorPrimaryStageController().listaCiudadesPropias.size() == 0) {//NUEVO EN EL JUEGO
            MapasController.newCiudad = true;
            MapasController.primeraCiudad = true;
            reload(MundoController.class, true);
        } else {
            PrimaryStageControler.setCiudadPrimaryStageController(getJugadorPrimaryStageController().cargarCiudadPrincipal);//CON ESTO CONTROLAS QUE CIUDAD ESTAS VIENDO!
            reload(CiudadController.class, true);
        }
    }
}
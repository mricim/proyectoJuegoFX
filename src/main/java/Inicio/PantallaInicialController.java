package main.java.Inicio;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import main.java.juego.MapasController;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.RecursosPrecargados;
import main.java.juego.mapas.mundo.MundoController;
import main.java.juego.mapas.pelea.UnidadesPreCargadas;
import main.java.jugadores.Clan;
import main.java.jugadores.Jugador;
import main.java.utils.PrimaryStageControler;
import main.java.juego.mapas.ciudad.CiudadController;
import main.java.juego.mapas.ciudad.EdificiosPreCargados;
import main.java.jugadores.iniciarSession.IniciarSessionController;
import main.java.temas.Temas;

import java.net.URL;
import java.util.*;

import static main.java.temas.Temas.arrayListTemas;


public class PantallaInicialController extends PrimaryStageControler implements Initializable {
    public static int idJugadorTemp;
    public static String nameJugadorTemp;
    public static String emailJugadorTemp;


    public ProgressBar progresBar;
    public ComboBox<String> seleccionarMundo;
    public javafx.scene.control.Button iniciarJuego;
    public javafx.scene.control.Button loadSesion;
    public VBox aCambiar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        seleccionarMundo.getItems().addAll(arrayListTemas);

        sesioniniciada = true;//TODO BORRAR
        idJugadorTemp = 1;//TODO BORRAR
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
        Temas.ruteUse(String.valueOf(a) + "/");

        progresBar.setProgress(10);//TODO NO FUNCIONA // https://stackoverflow.com/questions/44398611/running-a-process-in-a-separate-thread-so-rest-of-java-fx-application-is-usable
        callbd();
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


    private void callbd() {
        //TODO LEER DESDE LA BD
        new RecursosPrecargados(0, "Oro", false, null);
        new RecursosPrecargados(1, "Madera", true, 5);
        new RecursosPrecargados(2, "Piedra", true, 5);
        new RecursosPrecargados(3, "Comida", true, 5);
        new RecursosPrecargados(4, "Hierro", true, 5);
        new RecursosPrecargados(5, "Poblacion", false, null);
        new RecursosPrecargados(6, "Felicidad", false, null);
        new RecursosPrecargados(7, "investigacion", false, null);
        progresBar.setProgress(15);
        //-1=noDanNiGeneranNada
        //0=EdificioNormal
        //1=NewCiudad
        //2=CreaSoldados
        //3=CreaMaquinas
        new EdificiosPreCargados(0, false, false, 1, 0, 0, -1, "parcela-Construible", "Descripción del edificio que sera mas larga que el nombre del edificio", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);//NO MEJORABLE
        new EdificiosPreCargados(1, false, false, 1, 0, -1, 0, "Castillo", "Descripción del edificio que sera mas larga que el nombre del edificio", 99, 99, 99, 99, 0, 0, 0, 0, 0, 0, 10, 1, 1000, 1000, 1000, 1000, 100);
        new EdificiosPreCargados(1, false, false, 1, 1, 0, 0, "Castillo", "Descripción 1_1", 99, 99, 99, 99, 10, 0, 1000, 500, 0, 0, 10, 1, 1000, 1000, 1000, 1000, 200);
        new EdificiosPreCargados(1, false, false, 1, 2, 1, 0, "Castillo", "X", 99, 99, 99, 99, 10, 1000, 1000, 1000, 1000, 0, 10, 1, 1000, 1000, 1000, 1000, 300);
        new EdificiosPreCargados(2, false, false, 2, 0, 0, -1, "Muralla", "XXXXXXXXXXX", 99, 99, 99, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        new EdificiosPreCargados(2, false, false, 2, 1, 0, -1, "Muralla", "XXXXXXXXXXX", 99, 99, 99, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        progresBar.setProgress(20);
        new EdificiosPreCargados(10, true, true, 2, 0, -1, 0, "Almacen", "XXXXXXXXXXX", 99, 19, 25, 30, 10, 0, 0, 0, 0, 0, 0, 0, 1000, 1000, 1000, 1000, 1000);
        new EdificiosPreCargados(11, true, true, 1, 0, 0, 0, "Centro cientifico", "NONE", 99, 99, 99, 20, 10, 0, 10, 0, 0, 0, 0, 50, 0, 0, 0, 0, 0);
        new EdificiosPreCargados(12, true, true, 2, 0, 0, 0, "Lupas", "XXXXXXXXXXX", 99, 99, 99, 20, 3000, 0, 0, 10, 0, 0, 0, -50, 0, 0, 0, 0, 0);
        new EdificiosPreCargados(12, true, true, 2, 1, 0, 0, "Lupas", "XXXXXXXXXXX 1", 99, 99, 99, 20, 2000, 200, 10, 10, 10, 10, 10, -50, 0, 0, 0, 0, 0);
        new EdificiosPreCargados(13, true, true, 1, 0, 0, 1, "Palacio", "NONE", 99, 99, 99, 20, 10, 10, 10, 10, 10, 10, 10, 50, 0, 0, 0, 0, 0);
        new EdificiosPreCargados(14, true, true, 1, 0, 1, 2, "Cuartel", "Crea soldados", 99, 99, 99, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        new EdificiosPreCargados(15, true, true, 1, 0, 2, 3, "Maquinas de guerra", "Crear maquinas de guerra", 99, 99, 99, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        progresBar.setProgress(30);
        List<Recursos> recursosList = new ArrayList<>();
        recursosList.add(new Recursos(0, 0));
        recursosList.add(new Recursos(1, 5));
        recursosList.add(new Recursos(2, 15));
        recursosList.add(new Recursos(3, 0));
        recursosList.add(new Recursos(4, 3));
        recursosList.add(new Recursos(5, 1));
        recursosList.add(new Recursos(6, 0));
        List<Recursos> recursosList2 = new ArrayList<>();
        recursosList2.add(new Recursos(0, 0));
        recursosList2.add(new Recursos(1, 5));
        recursosList2.add(new Recursos(2, 15));
        recursosList2.add(new Recursos(3, 60));
        recursosList2.add(new Recursos(4, 3));
        recursosList2.add(new Recursos(5, 1));
        recursosList2.add(new Recursos(6, 0));
        new UnidadesPreCargadas(0, "Espadachines", 0, recursosList,0);//se podria poner comida
        new UnidadesPreCargadas(1, "Lanceros", 0, recursosList,0);
        new UnidadesPreCargadas(2, "Arqueros", 100, recursosList,0);
        new UnidadesPreCargadas(3, "Caballeros", 50, recursosList2,0);
        new UnidadesPreCargadas(4, "Catapultas", 50, recursosList2,5);
        progresBar.setProgress(50);
        //TODO FIN LEER DESDE LA BD
        Clan clan = new Clan(1, "Los mejores");
        Jugador jugador = new Jugador("pepito", 500);
        setJugadorPrimaryStageController(jugador);
        setClanPrimaryStageController(clan);
        progresBar.setProgress(60);
        new Jugador("juan", 300);
        new Jugador("pedro", 8000);
        progresBar.setProgress(70);
        clan.addJugadorClan(jugador);
        clan.addJugadorClan(3);
        progresBar.setProgress(80);
    }
}
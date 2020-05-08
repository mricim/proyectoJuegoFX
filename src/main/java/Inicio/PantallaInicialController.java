package main.java.Inicio;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.juego.MapasController;
import main.java.juego.comercio.Comercio;
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

        sesioniniciada = true;//TODO BORRAR
        idJugadorTemp = 1;//TODO BORRAR
    }

    private void seleccionarIdioma(String imageName) {
        try {
            reloadLanguage(null, stagePrimaryStageController, PantallaInicialController.getPathToFXML(PantallaInicialController.class), false, new Locale(imageName.substring(1 + imageName.lastIndexOf("/"))));
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
        NAME_TEMA_PATH=String.valueOf(a);
        Temas.ruteUse(NAME_TEMA_PATH + "/");
        System.out.println();

        progresBar.setProgress(10);//TODO NO FUNCIONA // https://stackoverflow.com/questions/44398611/running-a-process-in-a-separate-thread-so-rest-of-java-fx-application-is-usable
        TRADUCCIONES_THEMA = ResourceBundle.getBundle("main.resources.traductions.temas."+NAME_TEMA_PATH+".UIResources", TRADUCCIONES_GENERALES.getLocale());
        for (Temas temas : Temas.listaDeTemas) {
            if (temas.getName().equals(NAME_TEMA)){
                elTemaSeleccionado=temas;
                break;
            }
        }

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
        RecursosPrecargados oroPre = new RecursosPrecargados(0, TRADUCCIONES_THEMA.getString("recurso.oro"), true, true, false, true);
        RecursosPrecargados maderaPre = new RecursosPrecargados(1, TRADUCCIONES_THEMA.getString("recurso.madera"), true, true, false, true);
        RecursosPrecargados piedraPre = new RecursosPrecargados(2, TRADUCCIONES_THEMA.getString("recurso.piedra"), true, true, false, true);
        RecursosPrecargados comidaPre = new RecursosPrecargados(3, TRADUCCIONES_THEMA.getString("recurso.comida"), true, true, false, true);
        RecursosPrecargados hierroPre = new RecursosPrecargados(4, TRADUCCIONES_THEMA.getString("recurso.hierro"), true, true, false, true);
        RecursosPrecargados poblacionPre = new RecursosPrecargados(5, TRADUCCIONES_THEMA.getString("recurso.poblacion"), true, false, false, false);
        RecursosPrecargados felicidadPre = new RecursosPrecargados(6, TRADUCCIONES_THEMA.getString("recurso.felicidad"), true, false, true, true);
        RecursosPrecargados investigacionPre = new RecursosPrecargados(7, TRADUCCIONES_THEMA.getString("recurso.investigacion"), false, true, false, true);
        RecursosPrecargados municionPre = new RecursosPrecargados(8, "municion", true, true, true, true);
        progresBar.setProgress(15);
        //TODO MENUS ESPECIALES
        //-1=noDanNiGeneranNada
        //0=EdificioNormal
        //1=NewCiudad
        //2=CreaSoldados
        //3=CreaMaquinas

        //RECURSOS - CONSTRUIR
        Map<Integer, Recursos> recursosBuild = new TreeMap<>();
        recursosBuild.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild.put(maderaPre.getId(), new Recursos(maderaPre, 5));
        recursosBuild.put(piedraPre.getId(), new Recursos(piedraPre, 5));
        recursosBuild.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild.put(poblacionPre.getId(), new Recursos(poblacionPre, 5));
        //RECURSOS -Productores
        Map<Integer, Recursos> recursosProductores = new TreeMap<>();
        recursosProductores.put(oroPre.getId(), new Recursos(oroPre, 10));
        recursosProductores.put(maderaPre.getId(), new Recursos(maderaPre, 10));
        recursosProductores.put(piedraPre.getId(), new Recursos(piedraPre, 10));
        recursosProductores.put(comidaPre.getId(), new Recursos(comidaPre, 10));
        recursosProductores.put(hierroPre.getId(), new Recursos(hierroPre, 10));
        recursosProductores.put(poblacionPre.getId(), new Recursos(poblacionPre, 10));
        recursosProductores.put(felicidadPre.getId(), new Recursos(felicidadPre, 10));
        recursosProductores.put(investigacionPre.getId(), new Recursos(investigacionPre, 10));
        //RECURSOS - COSTE X MIN
        ArrayList<Recursos> recursosCosteXmin5 = new ArrayList<>();
        recursosCosteXmin5.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXmin = new TreeMap<>();
        recursosCosteXmin.put(maderaPre, recursosCosteXmin5);
        recursosCosteXmin.put(piedraPre, recursosCosteXmin5);
        recursosCosteXmin.put(comidaPre, recursosCosteXmin5);
        recursosCosteXmin.put(hierroPre, recursosCosteXmin5);

        ArrayList<Recursos> recursosCosteXmin5V2 = new ArrayList<>();
        recursosCosteXmin5V2.add(new Recursos(poblacionPre, 20));
        recursosCosteXmin5V2.add(new Recursos(comidaPre, 15));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXminV2 = new TreeMap<>();
        recursosCosteXminV2.put(maderaPre, recursosCosteXmin5V2);
        recursosCosteXminV2.put(piedraPre, recursosCosteXmin5V2);
        recursosCosteXminV2.put(comidaPre, recursosCosteXmin5V2);
        recursosCosteXminV2.put(hierroPre, recursosCosteXmin5V2);
        //RECURSOS -ALMACEN
        Map<Integer, Recursos> recursosAlmacen = new TreeMap<>();
        recursosAlmacen.put(oroPre.getId(), new Recursos(oroPre, 31));
        recursosAlmacen.put(maderaPre.getId(), new Recursos(maderaPre, 31));
        recursosAlmacen.put(piedraPre.getId(), new Recursos(piedraPre, 31));
        recursosAlmacen.put(comidaPre.getId(), new Recursos(comidaPre, 31));
        recursosAlmacen.put(hierroPre.getId(), new Recursos(hierroPre, 31));
        recursosAlmacen.put(poblacionPre.getId(), new Recursos(poblacionPre, 31));
        recursosAlmacen.put(felicidadPre.getId(), new Recursos(felicidadPre, 31));
        recursosAlmacen.put(investigacionPre.getId(), new Recursos(investigacionPre, 31));

        new EdificiosPreCargados(0, 0, false, false, 0, 0, 0, "parcela-Construible", "Descripción del edificio que sera mas larga que el nombre del edificio", null, null, null, null);//NO MEJORABLE

        new EdificiosPreCargados(1, 0, false, false, 1, 0, 0, "Castillo", "Descripción del edificio que sera mas larga que el nombre del edificio", recursosBuild, recursosProductores, null, recursosAlmacen);
        new EdificiosPreCargados(1, 1, false, true, 1, 1, 0, "Castillo", "Descripción 1_1", recursosBuild, recursosProductores, null, recursosAlmacen);
        new EdificiosPreCargados(1, 2, false, true, 1, 2, 0, "Castillo", "X", recursosBuild, recursosProductores, null, recursosAlmacen);

        new EdificiosPreCargados(2, 0, false, false, 2, 0, 0, TRADUCCIONES_THEMA.getString("muralla"), "XXXXXXXXXXX", recursosBuild, null, null, null);
        new EdificiosPreCargados(2, 1, true, true, 2, 1, 0, TRADUCCIONES_THEMA.getString("muralla"), "XXXXXXXXXXX", recursosBuild, null, null, null);
        new EdificiosPreCargados(2, 2, true, true, 2, 2, 0, TRADUCCIONES_THEMA.getString("muralla"), "XXXXXXXXXXX", recursosBuild, null, null, null);

        new EdificiosPreCargados(10, 0, true, true, 0, -1, 0, "Almacen", "XXXXXXXXXXX", recursosBuild, null, null, recursosAlmacen);
        new EdificiosPreCargados(10, 1, true, true, 0, 0, 0, "Almacen", "XXXXXXXXXXX", recursosBuild, null, null, recursosAlmacen);
        new EdificiosPreCargados(10, 2, true, true, 0, 1, 0, "Almacen", "XXXXXXXXXXX", recursosBuild, null, null, recursosAlmacen);

        new EdificiosPreCargados(11, 0, true, true, 0, 1, 1, "Palacio", "NONE", recursosBuild, null, null, null);
        new EdificiosPreCargados(11, 1, true, true, 0, 2, 1, "Palacio", "NONE", recursosBuild, null, null, null);
        new EdificiosPreCargados(11, 2, true, true, 0, 2, 1, "Palacio", "NONE", recursosBuild, null, null, null);
        progresBar.setProgress(20);
        new EdificiosPreCargados(12, 0, true, true, 0, 0, 0, "Centro Cientifico", "NONE", recursosBuild, recursosProductores, recursosCosteXmin, recursosAlmacen);
        new EdificiosPreCargados(12, 1, true, true, 0, 0, 0, "Centro Cientifico", "NONE", recursosBuild, recursosProductores, recursosCosteXmin, recursosAlmacen);
        new EdificiosPreCargados(12, 2, true, true, 0, 0, 0, "Centro Cientifico", "NONE", recursosBuild, recursosProductores, recursosCosteXmin, recursosAlmacen);

        new EdificiosPreCargados(13, 0, true, true, 0, 0, 0, "Cantera", "NONE", recursosBuild, recursosProductores, recursosCosteXmin, recursosAlmacen);
        new EdificiosPreCargados(13, 1, true, true, 0, 0, 0, "Cantera", "NONE", recursosBuild, recursosProductores, recursosCosteXmin, recursosAlmacen);
        new EdificiosPreCargados(13, 2, true, true, 0, 0, 0, "Cantera", "NONE", recursosBuild, recursosProductores, recursosCosteXmin, recursosAlmacen);

        new EdificiosPreCargados(14, 0, true, true, 0, 0, 0, "Aserradero", "NONE", recursosBuild, recursosProductores, recursosCosteXmin, recursosAlmacen);
        new EdificiosPreCargados(14, 1, true, true, 0, 0, 0, "Aserradero", "NONE", recursosBuild, recursosProductores, recursosCosteXmin, recursosAlmacen);
        new EdificiosPreCargados(14, 2, true, true, 0, 0, 0, "Aserradero", "NONE", recursosBuild, recursosProductores, recursosCosteXmin, recursosAlmacen);

        new EdificiosPreCargados(15, 0, true, true, 0, 0, 0, "Fundicion", "NONE", recursosBuild, recursosProductores, recursosCosteXmin, recursosAlmacen);
        new EdificiosPreCargados(15, 1, true, true, 0, 0, 0, "Fundicion", "NONE", recursosBuild, recursosProductores, recursosCosteXmin, recursosAlmacen);
        new EdificiosPreCargados(15, 2, true, true, 0, 0, 0, "Fundicion", "NONE", recursosBuild, recursosProductores, recursosCosteXmin, recursosAlmacen);

        new EdificiosPreCargados(16, 0, true, true, 0, 0, 0, "Granja", "NONE", recursosBuild, recursosProductores, recursosCosteXmin, recursosAlmacen);
        new EdificiosPreCargados(16, 1, true, true, 0, 0, 0, "Granja", "NONE", recursosBuild, recursosProductores, recursosCosteXmin, recursosAlmacen);
        new EdificiosPreCargados(16, 2, true, true, 0, 0, 0, "Granja", "NONE", recursosBuild, recursosProductores, recursosCosteXmin, recursosAlmacen);

        new EdificiosPreCargados(17, 0, true, true, 0, 0, 0, "Taberna", "NONE", recursosBuild, recursosProductores, recursosCosteXminV2, recursosAlmacen);
        new EdificiosPreCargados(17, 1, true, true, 0, 0, 0, "Taberna", "NONE", recursosBuild, recursosProductores, recursosCosteXminV2, recursosAlmacen);
        new EdificiosPreCargados(17, 2, true, true, 0, 0, 0, "Taberna", "NONE", recursosBuild, recursosProductores, recursosCosteXminV2, recursosAlmacen);
        progresBar.setProgress(30);
        new EdificiosPreCargados(18, 0, false, false, 0, 0, 4, "Puerto", "NONE", recursosBuild, null, null, null);
        new EdificiosPreCargados(18, 1, true, true, 0, 0, 4, "Puerto", "NONE", recursosBuild, null, null, null);
        new EdificiosPreCargados(18, 2, true, true, 0, 0, 4, "Puerto", "NONE", recursosBuild, null, null, null);

        new EdificiosPreCargados(19, 0, true, true, 0, 0, 2, "Cuartel", "NONE", recursosBuild, null, null, null);
        new EdificiosPreCargados(19, 1, true, true, 0, 0, 2, "Cuartel", "NONE", recursosBuild, null, null, null);
        new EdificiosPreCargados(19, 2, true, true, 0, 0, 2, "Cuartel", "NONE", recursosBuild, null, null, null);

        new EdificiosPreCargados(20, 0, true, true, 0, 0, 3, "Taller armas de asedio", "NONE", recursosBuild, null, null, null);
        new EdificiosPreCargados(20, 1, true, true, 0, 0, 3, "Taller armas de asedio", "NONE", recursosBuild, null, null, null);
        new EdificiosPreCargados(20, 2, true, true, 0, 0, 3, "Taller armas de asedio", "NONE", recursosBuild, null, null, null);

        progresBar.setProgress(40);
        List<Recursos> recursosList = new ArrayList<>();
        recursosList.add(new Recursos(oroPre, 0));
        recursosList.add(new Recursos(maderaPre, 5));
        recursosList.add(new Recursos(piedraPre, 15));
        recursosList.add(new Recursos(comidaPre, 0));
        recursosList.add(new Recursos(hierroPre, 3));
        recursosList.add(new Recursos(felicidadPre, 1));
        recursosList.add(new Recursos(municionPre, 0));
        List<Recursos> recursosList2 = new ArrayList<>();
        recursosList2.add(new Recursos(oroPre, 3));
        recursosList2.add(new Recursos(maderaPre, 6));
        recursosList2.add(new Recursos(piedraPre, 65));
        recursosList2.add(new Recursos(comidaPre, 7));
        recursosList2.add(new Recursos(hierroPre, 8));
        recursosList2.add(new Recursos(felicidadPre, 3));
        recursosList2.add(new Recursos(municionPre, 5));
        new UnidadesPreCargadas(0, "Espadachines", 0, recursosList, 0);//se podria poner comida
        new UnidadesPreCargadas(1, "Lanceros", 0, recursosList, 0);
        new UnidadesPreCargadas(2, "Arqueros", 100, recursosList, 0);
        new UnidadesPreCargadas(3, "Caballeros", 50, recursosList2, 0);
        new UnidadesPreCargadas(4, "Catapultas", 50, recursosList2, 5);
        progresBar.setProgress(50);
        //TODO FIN LEER DESDE LA BD
        Clan clan = new Clan( "Los mejores");
        Clan clan2 = new Clan( "Los peores");
        ArrayList<Recursos> jugador1ListaRecuros = new ArrayList<>();
        jugador1ListaRecuros.add(new Recursos(investigacionPre, 5));
        Jugador jugador = new Jugador("pepito", jugador1ListaRecuros);
        setJugadorPrimaryStageController(jugador);
        setClanPrimaryStageController(clan);
        progresBar.setProgress(60);
        ArrayList<Recursos> jugador2ListaRecuros = new ArrayList<>();
        jugador2ListaRecuros.add(new Recursos(investigacionPre, 25));
        Jugador jugador2 =new Jugador("juan", jugador2ListaRecuros);
        ArrayList<Recursos> jugador3ListaRecuros = new ArrayList<>();
        jugador3ListaRecuros.add(new Recursos(investigacionPre, 80));
        Jugador jugador3 =new Jugador("pedro", jugador3ListaRecuros);
        Jugador jugador4 =new Jugador("julito", jugador3ListaRecuros);
        clan2.addJugadorClan(jugador4);
        progresBar.setProgress(70);
        clan.addJugadorClan(jugador);
        clan.addJugadorClan(3);
        progresBar.setProgress(80);

        //COSAS DE TEST
        Comercio comercio0 = new Comercio( new Recursos(oroPre, 5), new Recursos(maderaPre, 300), jugador);
        Comercio.data.add(comercio0);
        Comercio comercio1 = new Comercio( new Recursos(hierroPre, 6), new Recursos(comidaPre, 11), jugador2);
        Comercio.data.add(comercio1);
        Comercio comercio2 = new Comercio( new Recursos(oroPre, 7), new Recursos(maderaPre, 12), jugador3);
        Comercio.data.add(comercio2);
        Comercio comercio3 = new Comercio( new Recursos(hierroPre, 7000000), new Recursos(maderaPre, 15), jugador4);
        Comercio.data.add(comercio3);

/*
        Timeline thirtySeconds = new Timeline(new KeyFrame(Duration.seconds(30), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Thread Running --> PANTALLA INICIAL CONTROLLER: ADD Comercio");
                Comercio comercio5 = new Comercio( new Recursos(maderaPre, 7), new Recursos(maderaPre, 15), jugador);
                Comercio.data.add(comercio5);
                Comercio comercio6 = new Comercio( new Recursos(maderaPre, 7), new Recursos(maderaPre, 15), jugador2);
                Comercio.data.add(comercio6);
            }
        }));
        thirtySeconds.setCycleCount(Timeline.INDEFINITE);
        thirtySeconds.play();
*/

        //TODO TEST
        Thread thread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread Running --> PANTALLA INICIAL CONTROLLER: REMOVE Comercio");
                Comercio.data.remove(comercio1);
                Comercio.data.remove(comercio2);
                Comercio comercio4 = new Comercio( new Recursos(piedraPre, 7), new Recursos(maderaPre, 15), jugador3);
                Comercio.data.add(comercio4);
            }
        };
        thread.start();
    }
}
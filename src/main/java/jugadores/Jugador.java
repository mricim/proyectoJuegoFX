package main.java.jugadores;

import javafx.scene.image.Image;
import main.java.Utils.CallImages;
import main.java.Utils.PrimaryStageControler;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.pelea.Batallon;
import main.java.juego.mapas.pelea.Soldados;
import main.java.juego.mapas.pelea.SoldadosPreCargados;
import main.java.juego.mapas.ciudad.EdificiosPreCargados;
import main.java.juego.mapas.ciudad.Ciudad;

import java.util.*;

public class Jugador {
    public static final Image ERRORIMAGE = CallImages.getImage("", "error");//TODO LLEVAR ESTO LO MAS ALTO POSIBLE
    public static TreeMap<Integer, Jugador> listaJugadores = new TreeMap<>();


    public static TreeMap<Integer, SoldadosPreCargados> SoldadosPreCargada = new TreeMap();
    public static TreeMap<String, EdificiosPreCargados> listaEdificiosPreCargados = new TreeMap<>();

    public static TreeMap<String, Batallon> listaBatallones = new TreeMap<>();
    public static TreeMap<String, Ciudad> listaCiudades = new TreeMap<>();
    public static List<Integer> listaEdificiosKeys = new ArrayList<>();


    public TreeMap<String, Batallon> listaBatallonesPropios = new TreeMap<>();
    public TreeMap<String, Ciudad> listaCiudadesPropias = new TreeMap<>();

    int idJugador;
    String nombre;
    private Recursos investigacion;

    public Jugador(int idJugador, String nombre, int investigacion) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.investigacion = new Recursos(7, investigacion);

        Ciudad cargarCiudad = null;
        if (idJugador == 1) {
            //todo Se le desde la BD
            Ciudad ciudad1 = new Ciudad(1, "ciudad 1 1-1", 1, 1, 1, 300, 20, 50, 70, 90, 40, 50);
            Ciudad ciudad2 = new Ciudad(2, "ciudad 2 2-2", 2, 2, 1, Integer.MAX_VALUE, 5000, 60000, 3000, 756123, 6584110, 53);
            listaCiudadesPropias.put(ciudad1.getPosition(), ciudad1);
            listaCiudadesPropias.put(ciudad2.getPosition(), ciudad2);//Carga la ciudad con el id mas bajo (la mas antigua)


            int numCiudad = Integer.MAX_VALUE;
            for (Ciudad ciudad : listaCiudadesPropias.values()) {
                int idCiudad = ciudad.getIdCiudad();
                if (numCiudad > idCiudad) {
                    numCiudad = idCiudad;
                    cargarCiudad = ciudad;
                }
            }

            //TODO DESDE LA BD
            Soldados soldados1 = new Soldados(1, SoldadosPreCargada.get(0), 100, 5, 0, 0);
            Soldados soldados2 = new Soldados(2, SoldadosPreCargada.get(0), 100, 0, 0, 0);
            Batallon batallon1 = new Batallon(1, "Batallon 1 5-5", 5, 5, 400, this);
            batallon1.setSoldadoHashMap(soldados1);
            batallon1.setSoldadoHashMap(soldados2);

            Batallon batallon2 = new Batallon(2, "Batallon 2-6", 4, 6, 20, this);
            Soldados soldados3 = new Soldados(3, SoldadosPreCargada.get(3), 100, 100, 0, 0);
            Soldados soldados4 = new Soldados(4, SoldadosPreCargada.get(0), 100, 5, 0, 0);
            batallon2.setSoldadoHashMap(soldados3);
            batallon2.setSoldadoHashMap(soldados4);


            PrimaryStageControler.setCiudad(cargarCiudad);//CON ESTO CONTROLAS QUE CIUDAD ESTAS VIENDO!
            PrimaryStageControler.setJugador(this);//CON ESTO CONTROLAS QUE CIUDAD ESTAS VIENDO!
        } else {
            new Ciudad(3, "ciudad 3 1-6", 1, 6, 1, 300, 20, 50, 70, 90, 40, 50);
            new Ciudad(4, "ciudad 4 3-8", 3, 8, 1, Integer.MAX_VALUE, 5000, 60000, 3000, 756123, 6584110, 53);
            new Ciudad(5, "ciudad 5 8-3", 8, 3, 1, Integer.MAX_VALUE, 5000, 60000, 3000, 756123, 6584110, 53);
            Batallon batallon1 = new Batallon(3, "Batallon 3 5-8", 5, 8, 400, this);
            Soldados soldados1 = new Soldados(5, SoldadosPreCargada.get(0), 100, 5, 0, 0);
            Soldados soldados2 = new Soldados(6, SoldadosPreCargada.get(0), 100, 0, 0, 0);
            batallon1.setSoldadoHashMap(soldados1);
            batallon1.setSoldadoHashMap(soldados2);
        }

        Collections.sort(listaEdificiosKeys);
        //TODO Collections.sort(listaBatallonesPropios);
        //TODO Collections.sort(listaCiudadesPropias);
        listaJugadores.put(idJugador, this);
    }

    public int getInvestigacion() {
        return investigacion.getCantidad();
    }
}

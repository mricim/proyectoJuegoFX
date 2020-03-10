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
    public static TreeMap<Integer, Jugador> listaTodosLosJugadores = new TreeMap<>();


    public static TreeMap<Integer, SoldadosPreCargados> SoldadosPreCargada = new TreeMap();
    public static TreeMap<String, EdificiosPreCargados> listaEdificiosPreCargados = new TreeMap<>();

    public static TreeMap<String, ArrayList<Batallon>> listaPosicionesBatallones = new TreeMap<>();
    //public static TreeMap<int, Batallon> listaBatallones = new TreeMap<>();
    public static TreeMap<String, Ciudad> listaCiudades = new TreeMap<>();
    public static List<Integer> listaEdificiosKeys = new ArrayList<>();


    public TreeMap<String, Batallon> listaBatallonesPropios = new TreeMap<>();
    public TreeMap<String, Ciudad> listaCiudadesPropias = new TreeMap<>();

    private int id;
    private String nombre;
    private Recursos investigacion;

    public Jugador(int id, String nombre, int investigacion) {
        this.id = id;
        this.nombre = nombre;
        this.investigacion = new Recursos(7, investigacion);

        Ciudad cargarCiudad = null;
        if (id == 1) {
            //todo Se le desde la BD
            Ciudad ciudad1 = new Ciudad(this,1, "ciudad 1 1-1", 1, 1, 1, 300, 20, 50, 70, 90, 3, 50);
            Ciudad ciudad2 = new Ciudad(this,2, "ciudad 2 2-2", 2, 2, 1, Integer.MAX_VALUE, 5000, 60000, 3000, 756123, 6584110, 53);


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

            Batallon batallon2 = new Batallon(2, "Batallon 2 8-7", 8, 7, 20, this);
            Soldados soldados3 = new Soldados(3, SoldadosPreCargada.get(3), 100, 100, 0, 0);
            Soldados soldados4 = new Soldados(4, SoldadosPreCargada.get(0), 100, 5, 0, 0);
            batallon2.setSoldadoHashMap(soldados3);
            batallon2.setSoldadoHashMap(soldados4);


            PrimaryStageControler.setCiudadPrimaryStageController(cargarCiudad);//CON ESTO CONTROLAS QUE CIUDAD ESTAS VIENDO!
            PrimaryStageControler.setJugadorPrimaryStageController(this);//CON ESTO CONTROLAS QUE CIUDAD ESTAS VIENDO!
        } else if (id == 2) {
            Ciudad ciudad1 = new Ciudad(this,3, "ciudad 3 1-6", 1, 6, 1, 300, 20, 50, 70, 90, 40, 50);
            Ciudad ciudad2 = new Ciudad(this,4, "ciudad 4 2-7", 2, 7, 1, Integer.MAX_VALUE, 5000, 60000, 3000, 756123, 6584110, 53);
            Ciudad ciudad3 = new Ciudad(this,5, "ciudad 5 3-8", 3, 8, 1, Integer.MAX_VALUE, 5000, 60000, 3000, 756123, 6584110, 53);
            Ciudad ciudad4 = new Ciudad(this,6, "ciudad 6 8-3", 8, 3, 1, Integer.MAX_VALUE, 5000, 60000, 3000, 756123, 6584110, 53);

            Batallon batallon1 = new Batallon(3, "Batallon 3 5-8", 5, 8, 400, this);
            Soldados soldados1 = new Soldados(5, SoldadosPreCargada.get(0), 100, 5, 0, 0);
            Soldados soldados2 = new Soldados(6, SoldadosPreCargada.get(0), 100, 0, 0, 0);
            batallon1.setSoldadoHashMap(soldados1);
            batallon1.setSoldadoHashMap(soldados2);

            Batallon batallon2 = new Batallon(4, "Batallon 4 2-2", 2, 2, 400, this);
            Soldados soldados3 = new Soldados(7, SoldadosPreCargada.get(0), 100, 5, 0, 0);
            Soldados soldados4 = new Soldados(8, SoldadosPreCargada.get(0), 100, 0, 0, 0);
            batallon2.setSoldadoHashMap(soldados3);
            batallon2.setSoldadoHashMap(soldados4);

            Batallon batallon3 = new Batallon(5, "Batallon 5 2-7", 2, 7, 400, this);
            Soldados soldados5 = new Soldados(9, SoldadosPreCargada.get(0), 100, 5, 0, 0);
            Soldados soldados6 = new Soldados(10, SoldadosPreCargada.get(0), 100, 0, 0, 0);
            batallon3.setSoldadoHashMap(soldados5);
            batallon3.setSoldadoHashMap(soldados6);

            Batallon batallon4 = new Batallon(9, "Batallon 9 8-7", 8, 7, 400, this);
            Soldados soldados7 = new Soldados(11, SoldadosPreCargada.get(0), 100, 5, 0, 0);
            Soldados soldados8 = new Soldados(12, SoldadosPreCargada.get(0), 100, 0, 0, 0);
            batallon4.setSoldadoHashMap(soldados7);
            batallon4.setSoldadoHashMap(soldados8);
        } else {
            Ciudad ciudad1 = new Ciudad(this,7, "ciudad 7 6-6", 6, 6, 1, 300, 20, 50, 70, 90, 40, 50);
            Ciudad ciudad2 = new Ciudad(this,8, "ciudad 8 6-11", 6, 11, 1, Integer.MAX_VALUE, 5000, 60000, 3000, 756123, 6584110, 53);
            Ciudad ciudad3 = new Ciudad(this,9, "ciudad 9 8-7", 8, 7, 1, Integer.MAX_VALUE, 5000, 60000, 3000, 756123, 6584110, 53);
            Ciudad ciudad4 = new Ciudad(this,10, "ciudad 10 7-5", 7, 5, 1, Integer.MAX_VALUE, 5000, 60000, 3000, 756123, 6584110, 53);

            Batallon batallon1 = new Batallon(6, "Batallon 6 5-8", 7, 7, 400, this);
            Soldados soldados1 = new Soldados(11, SoldadosPreCargada.get(0), 100, 5, 0, 0);
            Soldados soldados2 = new Soldados(12, SoldadosPreCargada.get(0), 100, 0, 0, 0);
            batallon1.setSoldadoHashMap(soldados1);
            batallon1.setSoldadoHashMap(soldados2);

            Batallon batallon2 = new Batallon(7, "Batallon 7 2-2", 8, 10, 400, this);
            Soldados soldados3 = new Soldados(13, SoldadosPreCargada.get(0), 100, 5, 0, 0);
            Soldados soldados4 = new Soldados(14, SoldadosPreCargada.get(0), 100, 0, 0, 0);
            batallon2.setSoldadoHashMap(soldados3);
            batallon2.setSoldadoHashMap(soldados4);

            Batallon batallon3 = new Batallon(8, "Batallon 8 2-7", 12, 7, 400, this);
            Soldados soldados5 = new Soldados(15, SoldadosPreCargada.get(0), 100, 5, 0, 0);
            Soldados soldados6 = new Soldados(16, SoldadosPreCargada.get(0), 100, 0, 0, 0);
            batallon3.setSoldadoHashMap(soldados5);
            batallon3.setSoldadoHashMap(soldados6);

            Batallon batallon4 = new Batallon(10, "Batallon 10 8-7", 8, 7, 400, this);
            Soldados soldados7 = new Soldados(17, SoldadosPreCargada.get(0), 100, 5, 0, 0);
            Soldados soldados8 = new Soldados(18, SoldadosPreCargada.get(0), 100, 0, 0, 0);
            batallon4.setSoldadoHashMap(soldados7);
            batallon4.setSoldadoHashMap(soldados8);
        }

        Collections.sort(listaEdificiosKeys);
        //TODO Collections.sort(listaBatallonesPropios);
        //TODO Collections.sort(listaCiudadesPropias);
        listaTodosLosJugadores.put(id, this);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getInvestigacion() {
        return investigacion.getCantidad();
    }

    public static Jugador returnJugador(int jugadorId) {
        return Jugador.listaTodosLosJugadores.get(jugadorId);
    }
}

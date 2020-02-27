package main.java.Jugadores;

import javafx.scene.image.Image;
import main.java.Utils.CallImages;
import main.java.Utils.PrimaryStageControler;
import main.java.juego.mapas.Ciudad.EdificiosPreCargados;
import main.java.juego.mapas.Pelea.Batallon;
import main.java.juego.mapas.Pelea.Soldados;
import main.java.juego.mapas.Pelea.SoldadosPreCargados;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.Ciudad.Ciudad;

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
            Ciudad ciudad1 = new Ciudad(1, "ciudad 1", 1,1, 1, 300, 20, 50, 70, 90, 40, 50);
            Ciudad ciudad2 = new Ciudad(2, "ciudad 2", 2, 2,1, Integer.MAX_VALUE, 5, 4, 6, 7, 8, 9);
            listaCiudadesPropias.put(ciudad1.getPosition(), ciudad1);
            listaCiudadesPropias.put(ciudad2.getPosition(), ciudad2);
            //Carga la ciudad con el id mas bajo (la mas antigua)

            int numCiudad = Integer.MAX_VALUE;
            for (Ciudad ciudad : listaCiudadesPropias.values()) {
                int idCiudad = ciudad.getIdCiudad();
                if (numCiudad > idCiudad) {
                    numCiudad = idCiudad;
                    cargarCiudad = ciudad;
                }
            }

            //TODO DESDE LA BD
            Soldados soldados1=new Soldados(1, SoldadosPreCargada.get(0), 100, 5, 0, 0);
            Soldados soldados2=new Soldados(2, SoldadosPreCargada.get(0), 100, 0, 0, 0);
            Batallon batallon1=new Batallon(1,"nope",1,1,400);
            batallon1.setSoldadoHashMap(soldados1);
            batallon1.setSoldadoHashMap(soldados2);

            Batallon batallon2=new Batallon(2,"nope",5,5,20);
            Soldados soldados3=new Soldados(3, SoldadosPreCargada.get(3), 100, 100, 0, 0);
            Soldados soldados4=new Soldados(4, SoldadosPreCargada.get(0), 100, 5, 0, 0);
            batallon2.setSoldadoHashMap(soldados3);
            batallon2.setSoldadoHashMap(soldados4);

            listaBatallonesPropios.put(batallon1.getPosition(),batallon1);
            listaBatallonesPropios.put(batallon2.getPosition(),batallon2);


            PrimaryStageControler.setCiudad(cargarCiudad);//CON ESTO CONTROLAS QUE CIUDAD ESTAS VIENDO!
            PrimaryStageControler.setJugador(this);//CON ESTO CONTROLAS QUE CIUDAD ESTAS VIENDO!
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

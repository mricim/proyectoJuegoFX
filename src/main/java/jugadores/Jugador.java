package main.java.jugadores;

import javafx.scene.image.Image;
import main.java.utils.CallImages;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.pelea.*;
import main.java.juego.mapas.ciudad.EdificiosPreCargados;
import main.java.juego.mapas.ciudad.Ciudad;

import java.util.*;

public class Jugador {
    public static final Image ERRORIMAGE = CallImages.getImageNoTema("", "error");//TODO LLEVAR ESTO LO MAS ALTO POSIBLE
    public static TreeMap<Integer, Jugador> listaTodosLosJugadores = new TreeMap<>();


    public static TreeMap<String, EdificiosPreCargados> listaEdificiosPreCargados = new TreeMap<>();
    public static TreeMap<Integer, UnidadesPreCargadas> listaSoldadosPreCargada = new TreeMap();

    public static TreeMap<String, ArrayList<Batallon>> listaPosicionesBatallones = new TreeMap<>();
    //public static TreeMap<int, Batallon> listaBatallones = new TreeMap<>();
    public static TreeMap<String, Ciudad> listaCiudades = new TreeMap<>();
    public static List<Integer> listaEdificiosKeys = new ArrayList<>();


    public TreeMap<Integer, Batallon> listaBatallonesPropios = new TreeMap<>();
    public TreeMap<String, Ciudad> listaCiudadesPropias = new TreeMap<>();

    private static int lastId=1;
    private int id;
    private String nombre;
    private TreeMap<Integer, Recursos> recursosJugador =new TreeMap<>();
    public Ciudad cargarCiudadPrincipal = null;

    public Jugador( String nombre, ArrayList<Recursos> recursosJugador) {
        this.id = lastId++;
        this.nombre = nombre;
        for (Recursos recursos : recursosJugador) {
            this.recursosJugador.put(recursos.getId(),recursos);
        }


//TODO LEER DESDE LA BD
        ArrayList<Recursos> paraCity1=new ArrayList<>();
        paraCity1.add(new Recursos(0,300));
        paraCity1.add(new Recursos(1,20));
        paraCity1.add(new Recursos(2,50));
        paraCity1.add(new Recursos(3,70));
        paraCity1.add(new Recursos(4,90));
        paraCity1.add(new Recursos(5,3));
        paraCity1.add(new Recursos(6,50));
        ArrayList<Recursos> paraCity2=new ArrayList<>();
        paraCity2.add(new Recursos(0,3000));
        paraCity2.add(new Recursos(1,3000));
        paraCity2.add(new Recursos(2,3000));
        paraCity2.add(new Recursos(3,3000));
        paraCity2.add(new Recursos(4,3000));
        paraCity2.add(new Recursos(5,3000));
        paraCity2.add(new Recursos(6,3000));
        if (id == 1) {

            Ciudad ciudad1=new Ciudad(this, "ciudad P 1 1-1", 1, 1, 1,paraCity1);
            Ciudad ciudad2=new Ciudad(this, "ciudad P 2 2-2", 2, 2, 1, paraCity2);


            int numCiudad = Integer.MAX_VALUE;
            for (Ciudad ciudad : listaCiudadesPropias.values()) {
                int idCiudad = ciudad.getIdCiudad();
                if (numCiudad > idCiudad) {
                    numCiudad = idCiudad;
                    cargarCiudadPrincipal = ciudad;
                }
            }

            Unidades soldados1 = new Unidades(listaSoldadosPreCargada.get(3), 50, 5, 0, 0);
            Unidades soldados2 = new Unidades(listaSoldadosPreCargada.get(1), 30, 0, 0, 0);
            Batallon batallon1 = new Batallon("Batallon P 1 5-5", 5, 5, 400, this,ciudad1);
            batallon1.setSoldadoHashMap(soldados1);
            batallon1.setSoldadoHashMap(soldados2);

            Batallon batallon2 = new Batallon("Batallon P 2 8-7", 8, 7, 20, this,ciudad2);
            Unidades soldados3 = new Unidades(listaSoldadosPreCargada.get(3), 98, 100, 0, 0);
            Unidades soldados4 = new Unidades(listaSoldadosPreCargada.get(1), 80, 5, 0, 0);
            batallon2.setSoldadoHashMap(soldados3);
            batallon2.setSoldadoHashMap(soldados4);
        } else if (id == 2) {
            Ciudad ciudad1=new Ciudad(this, "ciudad E 3 1-6", 1, 6, 1, paraCity1);
            Ciudad ciudad2=new Ciudad(this, "ciudad E 4 2-7", 2, 7, 1, paraCity1);
            Ciudad ciudad3=new Ciudad(this, "ciudad E 5 3-8", 3, 8, 1, paraCity1);
            Ciudad ciudad4=new Ciudad(this, "ciudad E 6 8-3", 8, 3, 1, paraCity2);

            Batallon batallon1 = new Batallon("Batallon E 3 5-8", 5, 8, 400, this,ciudad1);
            Unidades soldados1 = new Unidades(listaSoldadosPreCargada.get(1), 100, 5, 0, 0);
            Unidades soldados2 = new Unidades(listaSoldadosPreCargada.get(2), 50, 0, 0, 0);
            batallon1.setSoldadoHashMap(soldados1);
            batallon1.setSoldadoHashMap(soldados2);

            Batallon batallon2 = new Batallon("Batallon E 4 2-2", 2, 2, 400, this,ciudad2);
            Unidades soldados3 = new Unidades(listaSoldadosPreCargada.get(1), 100, 5, 0, 0);
            Unidades soldados4 = new Unidades(listaSoldadosPreCargada.get(2), 100, 0, 0, 0);
            batallon2.setSoldadoHashMap(soldados3);
            batallon2.setSoldadoHashMap(soldados4);

            Batallon batallon3 = new Batallon("Batallon E 5 2-7", 2, 7, 400, this,ciudad3);
            Unidades soldados5 = new Unidades(listaSoldadosPreCargada.get(1), 100, 5, 0, 0);
            Unidades soldados6 = new Unidades(listaSoldadosPreCargada.get(2), 100, 0, 0, 0);
            batallon3.setSoldadoHashMap(soldados5);
            batallon3.setSoldadoHashMap(soldados6);

            Batallon batallon4 = new Batallon("Batallon E 9 8-7", 8, 7, 400, this,ciudad4);
            Unidades soldados7 = new Unidades(listaSoldadosPreCargada.get(1), 100, 5, 0, 0);
            Unidades soldados8 = new Unidades(listaSoldadosPreCargada.get(2), 100, 0, 0, 0);
            batallon4.setSoldadoHashMap(soldados7);
            batallon4.setSoldadoHashMap(soldados8);
        } else if (id == 3) {
            Ciudad ciudad1=new Ciudad(this, "ciudad Z 7 6-6", 6, 6, 1, paraCity1);
            Ciudad ciudad2=new Ciudad(this, "ciudad Z 8 6-11", 6, 11, 1,paraCity1);
            Ciudad ciudad3=new Ciudad(this, "ciudad Z 9 8-7", 8, 7, 1, paraCity1);
            Ciudad ciudad4=new Ciudad(this, "ciudad Z 10 7-5", 7, 5, 1, paraCity2);

            Batallon batallon1 = new Batallon("Batallon Z 6 5-8", 7, 7, 400, this,ciudad1);
            Unidades soldados1 = new Unidades(listaSoldadosPreCargada.get(1), 100, 5, 0, 0);
            Unidades soldados2 = new Unidades(listaSoldadosPreCargada.get(2), 100, 0, 0, 0);
            batallon1.setSoldadoHashMap(soldados1);
            batallon1.setSoldadoHashMap(soldados2);

            Batallon batallon2 = new Batallon("Batallon Z 7 2-2", 8, 10, 400, this,ciudad2);
            Unidades soldados3 = new Unidades(listaSoldadosPreCargada.get(1), 100, 5, 0, 0);
            Unidades soldados4 = new Unidades(listaSoldadosPreCargada.get(2), 100, 0, 0, 0);
            batallon2.setSoldadoHashMap(soldados3);
            batallon2.setSoldadoHashMap(soldados4);

            Batallon batallon3 = new Batallon("Batallon Z 8 2-7", 12, 7, 400, this,ciudad3);
            Unidades soldados5 = new Unidades(listaSoldadosPreCargada.get(1), 100, 5, 0, 0);
            Unidades soldados6 = new Unidades(listaSoldadosPreCargada.get(2), 100, 0, 0, 0);
            batallon3.setSoldadoHashMap(soldados5);
            batallon3.setSoldadoHashMap(soldados6);

            Batallon batallon4 = new Batallon("Batallon Z 10 8-7", 8, 7, 400, this,ciudad4);
            Unidades soldados7 = new Unidades(listaSoldadosPreCargada.get(1), 100, 5, 0, 0);
            Unidades soldados8 = new Unidades(listaSoldadosPreCargada.get(2), 100, 0, 0, 0);
            batallon4.setSoldadoHashMap(soldados7);
            batallon4.setSoldadoHashMap(soldados8);
        }
//TODO FIN LEER DESDE LA BD

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

    public TreeMap<Integer, Recursos> getRecursosJugador() {
        return recursosJugador;
    }

    public static Jugador returnJugador(int jugadorId) {
        return Jugador.listaTodosLosJugadores.get(jugadorId);
    }
/*
    public static void addBatallon(Batallon batallon) {//TODO QUEDA HACER ESTO PARA LOS BATALLONES IGUAL QUE ESTA EN CIUDAD
        //return //Jugador.listaTodosLosJugadores.get(jugadorId);
    }

 */
}

package main.java.juego;

import main.java.juego.mapas.EdificiosPreCargados;
import main.java.juego.mapas.PosicionesCiudades;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.city.Ciudad;

import java.util.*;

public class Jugador {
    public static HashMap<String, Ciudad> listaCiudades = new HashMap<>();

    public static NavigableMap<String, EdificiosPreCargados> listaEdificiosPreCargada = new TreeMap<>();
    public static List<Integer> listaEdificiosKeys = new ArrayList<>();


    int idJugador;
    private Recursos investigacion;

    Jugador(int idJugador,int investigacion) {
        this.idJugador=idJugador;
        this.investigacion=new Recursos(7,investigacion);
        //todo Se le desde la BD
        new EdificiosPreCargados(0, "parcela Construible","Descripción del edificio que sera mas larga que el nombre del edificio", false, true, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0);//NO MEJORABLE
        new EdificiosPreCargados(1, "Castillo","Descripción del edificio que sera mas larga que el nombre del edificio", false, false, 1, 0, -1, 99, 99, 99, 99, 10, 1000, 1000, 1000, 1000, 0, 10, 1, 1000, 1000, 1000, 1000,100);
        new EdificiosPreCargados(1, "Castillo","Descripción 1_1", false, false, 1, 1, 0, 99, 99, 99, 99, 10, 1000, 1000, 1000, 1000, 0, 10, 1, 1000, 1000, 1000, 1000,200);
        new EdificiosPreCargados(1, "Castillo","X", false, false, 1, 2, 1, 99, 99, 99, 99, 10, 1000, 1000, 1000, 1000, 0, 10, 1, 1000, 1000, 1000, 1000,300);
        new EdificiosPreCargados(2, "Muralla","XXXXXXXXXXX", false, false, 2, 0, 0, 99, 99, 99, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0);
        new EdificiosPreCargados(2, "Muralla","XXXXXXXXXXX", false, false, 2, 1, 0, 99, 99, 99, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0);

        new EdificiosPreCargados(10, "Almacen","XXXXXXXXXXX", true, true, 2, 0, 0, 99, 99, 99, 99, 10, 0, 0, 0, 0, 0, 0, 0, 1000, 1000, 1000, 1000,1000);
        new EdificiosPreCargados(11, "Centro cientifico", "NONE",true, true, 1, 0, 0, 99, 99, 99, 20, 10, 10, 10, 10, 10, 10, 10, 50, 0, 0, 0, 0,0);
        new EdificiosPreCargados(12, "Lupas","XXXXXXXXXXX", true, true, 3, 0, 0, 99, 99, 99, 20, 10, 10, 10, 10, 10, 10, 10, -50, 0, 0, 0, 0,0);

        //todo Se le desde la BD
        new Ciudad(1, "ciudad 1",1,1,300,20,50,70,90,40,50);
        new Ciudad(2, "ciudad 2",2,2,Integer.MAX_VALUE,5,4,6,7,8,9);


        //Carga la ciudad con el id mas bajo (la mas antigua)
        Ciudad cargarCiudad=null;
        int numCiudad = Integer.MAX_VALUE;
        for (Ciudad ciudad : listaCiudades.values()) {
            int idCiudad = ciudad.getIdCiudad();
            if (numCiudad > idCiudad) {
                numCiudad = idCiudad;
                cargarCiudad=ciudad;
            }
        }
        PrimaryStageControler.setJugador(this);//CON ESTO CONTROLAS QUE CIUDAD ESTAS VIENDO!
        PrimaryStageControler.setCiudad(cargarCiudad);//CON ESTO CONTROLAS QUE CIUDAD ESTAS VIENDO!


        Collections.sort(listaEdificiosKeys);
    }

    public int getInvestigacion() {
        return investigacion.getCantidad();
    }
}

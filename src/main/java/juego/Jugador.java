package main.java.juego;

import main.java.juego.mapas.EdificiosPreCargados;
import main.java.juego.mapas.PosicionesCiudades;
import main.java.juego.mapas.city.Ciudad;

import java.util.HashMap;

public class Jugador {
    public static HashMap<Integer, EdificiosPreCargados> listaEdificiosPreCargada = new HashMap<>();
    public static HashMap<Integer[], Ciudad> listaCiudades = new HashMap<>();

    Jugador() {
        //todo Se le desde la BD
        new EdificiosPreCargados(0, "parcela Construible", false, true, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);//NO MEJORABLE
        new EdificiosPreCargados(1, "Castillo", false, false, 1, 0, 0, 99, 99, 99, 99, 10, 1000, 1000, 1000, 1000, 0, 0, 0, 0, 0, 0, 0);
        new EdificiosPreCargados(2, "Muralla", false, false, 2, 99, 0, 99, 99, 99, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

        new EdificiosPreCargados(10, "Almacen", true, true, 2, 0, 0, 99, 99, 99, 99, 10, 1000, 1000, 1000, 1000, 0, 0, 0, 0, 0, 0, 0);
        new EdificiosPreCargados(11, "Centro cientifico", true, true, 1, 99, 0, 99, 99, 99, 20, 10, 10, 10, 10, 10, 10, 10, 0, 0, 0, 0, 0);
        new EdificiosPreCargados(12, "Lupas", true, true, 3, 99, 0, 99, 99, 99, 20, 10, 10, 10, 10, 10, 10, 10, 0, 0, 0, 0, 0);

        //todo Se le desde la BD
        new PosicionesCiudades(0, 1, new Ciudad(1, "ciudad 1"));
        new PosicionesCiudades(1, 1, new Ciudad(2, "ciudad 2"));


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
        PrimaryStageControler.setCiudad(cargarCiudad);//CON ESTO CONTROLAS QUE CIUDAD ESTAS VIENDO!

    }
}

package main.java.juego;

import main.java.juego.mapas.EdificiosPreCargados;
import main.java.juego.mapas.PosicionesCiudades;
import main.java.juego.mapas.city.Ciudad;

import java.util.HashMap;

public class Jugador {
    public static HashMap<String, EdificiosPreCargados> listaEdificiosPreCargada = new HashMap<>();
    public static HashMap<String, Ciudad> listaCiudades = new HashMap<>();

    Jugador() {
        //todo Se le desde la BD
        new EdificiosPreCargados(0, "parcela Construible","Descripcion del edificio que sera mas larga que el nombre del edificio", false, true, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0);//NO MEJORABLE
        new EdificiosPreCargados(1, "Castillo",">", false, false, 1, 0, 0, 99, 99, 99, 99, 10, 1000, 1000, 1000, 1000, 0, 0, 0, 0, 0, 0, 0,100);
        new EdificiosPreCargados(2, "Muralla","XXXXXXXXXXX", false, false, 2, 0, 0, 99, 99, 99, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0);

        new EdificiosPreCargados(10, "Almacen","XXXXXXXXXXX", true, true, 2, 0, 0, 99, 99, 99, 99, 10, 0, 0, 0, 0, 0, 0, 0, 1000, 1000, 1000, 1000,1000);
        new EdificiosPreCargados(11, "Centro cientifico", "NONE",true, true, 1, 0, 0, 99, 99, 99, 20, 10, 10, 10, 10, 10, 10, 10, 50, 0, 0, 0, 0,0);
        new EdificiosPreCargados(12, "Lupas","XXXXXXXXXXX", true, true, 3, 0, 0, 99, 99, 99, 20, 10, 10, 10, 10, 10, 10, 10, -50, 0, 0, 0, 0,0);

        //todo Se le desde la BD
        new PosicionesCiudades(0, 1, new Ciudad(1, "ciudad 1",300,20,50,70,90,40,50));
        new PosicionesCiudades(1, 1, new Ciudad(2, "ciudad 2",Integer.MAX_VALUE,5,4,6,7,8,9));


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

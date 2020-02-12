package main.java.mapas;

import main.java.mapas.city.Ciudad;
import main.java.mapas.city.Edificio;

import java.util.ArrayList;
import java.util.HashMap;

public class Jugador {
    public static HashMap<Integer, Edificio> listaEdificiosPreCargada = new HashMap<>();
    public static ArrayList<Ciudad> listaCiudades = new ArrayList();

    Jugador() {
        //Crear Edificios //Se le desde la BD
        Edificio parcela = new Edificio(0, "parcela Construible");//NO MEJORABLE
        Edificio castillo = new Edificio(1, "Castillo", false, 0, 99, 99, 99, 99, 10, 1000, 1000, 1000, 1000);
        Edificio muralla = new Edificio(2, "Muralla", 0, 99, 99, 99, 99, 20);
        Edificio almacen = new Edificio(10, "Almacen", true, 0, 99, 99, 99, 99, 10, 1000, 1000, 1000, 1000);
        Edificio edificio1 = new Edificio(11, "Centro cientifico", 0, 99, 99, 99, 99, 20, 10, 10, 10, 10, 10, 10, 10);
        Edificio edificio2 = new Edificio(12, "Lupas", 0, 99, 99, 99, 99, 20, 10, 10, 10, 10, 10, 10, 10);
        listaEdificiosPreCargada.put(0, parcela);
        listaEdificiosPreCargada.put(1, castillo);
        listaEdificiosPreCargada.put(2, muralla);
        listaEdificiosPreCargada.put(3, almacen);
        listaEdificiosPreCargada.put(4, edificio1);
        listaEdificiosPreCargada.put(5, edificio2);

        //Crear las ciudades que tenga el usuario
        new Ciudad(1);
        new Ciudad(2);

    }
}

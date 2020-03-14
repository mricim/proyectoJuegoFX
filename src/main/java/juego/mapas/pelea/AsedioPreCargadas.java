package main.java.juego.mapas.pelea;


import main.java.Utils.ImageGetter;

import static main.java.jugadores.Jugador.listaAsedioPreCargada;


public class AsedioPreCargadas extends UnidadesPreCargadas implements ImageGetter {

    public AsedioPreCargadas(int id, String nombre, int maxMunicion) {
        super(id, nombre, maxMunicion);
        listaAsedioPreCargada.put(id, this);
    }
}

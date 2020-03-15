package main.java.juego.mapas.pelea;


import main.java.Utils.ImageGetter;
import main.java.juego.mapas.Recursos;

import java.util.List;

import static main.java.jugadores.Jugador.listaAsedioPreCargada;


public class MaquinasAsedioPreCargadas extends UnidadesPreCargadas {

    public MaquinasAsedioPreCargadas(int id, String nombre, int maxMunicion, List<Recursos> costes) {
        super(id, nombre, maxMunicion, costes);
        listaAsedioPreCargada.put(id, this);
    }
}

package main.java.juego.mapas.pelea;


import main.java.Utils.ImageGetter;
import main.java.juego.mapas.Recursos;

import java.util.List;

import static main.java.jugadores.Jugador.listaSoldadosPreCargada;


public class SoldadosPreCargados extends UnidadesPreCargadas {

    public SoldadosPreCargados(int id, String nombre, int maxMunicion, List<Recursos> costes) {
        super(id, nombre, maxMunicion, costes);
        listaSoldadosPreCargada.put(id,this);
    }
}

package main.java.juego.mapas.pelea;


import main.java.Utils.ImageGetter;

import static main.java.jugadores.Jugador.listaSoldadosPreCargada;


public class SoldadosPreCargados extends UnidadesPreCargadas implements ImageGetter {

    public SoldadosPreCargados(int id, String nombre, int maxMunicion) {
        super(id, nombre, maxMunicion);
        listaSoldadosPreCargada.put(id,this);
    }
}

package main.java.juego.mapas;

import main.java.juego.Posiciones;
import main.java.juego.mapas.Pelea.Batallon;

import static main.java.juego.Jugador.listaBatallones;

public class PosicionesBatallones extends Posiciones {
    private Batallon batallon;
    public PosicionesBatallones(int fila, int columna, Batallon batallon) {
        super(fila, columna);
        this.batallon = batallon;
        listaBatallones.put(fila+"_"+columna, this);
    }

    public Batallon getBatallon() {
        return batallon;
    }
}

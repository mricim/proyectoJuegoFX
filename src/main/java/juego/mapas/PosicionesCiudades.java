package main.java.juego.mapas;

import main.java.juego.Posiciones;
import main.java.juego.mapas.city.Ciudad;

import static main.java.juego.Jugador.listaCiudades;

public class PosicionesCiudades extends Posiciones {
    private Ciudad ciudad;
    public PosicionesCiudades(int fila, int columna,Ciudad ciudad) {
        super(fila, columna);
        this.ciudad = ciudad;
        listaCiudades.put(fila+"_"+columna, ciudad);
    }

    public Ciudad getCiudad() {
        return ciudad;
    }
}

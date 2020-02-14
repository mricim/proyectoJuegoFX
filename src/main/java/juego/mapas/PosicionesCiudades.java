package main.java.juego.mapas;

import main.java.juego.Posiciones;
import main.java.juego.mapas.city.Ciudad;

import static main.java.juego.Jugador.listaCiudades;

public class PosicionesCiudades extends Posiciones {
    private Ciudad ciudad;
    public PosicionesCiudades(int y, int x,Ciudad ciudad) {
        super(y, x);
        this.ciudad = ciudad;
        listaCiudades.put(y+"_"+x, ciudad);
    }

    public Ciudad getCiudad() {
        return ciudad;
    }
}

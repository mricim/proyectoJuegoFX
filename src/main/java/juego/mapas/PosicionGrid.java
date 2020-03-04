package main.java.juego.mapas;

import main.java.Utils.Posicion;

public class PosicionGrid extends Posicion {
    Object object;
    int tipeObject;
    public PosicionGrid(int filas, int columnas,Object object,int tipeObject) {
        super(filas, columnas);
        this.object=object;
        this.tipeObject=tipeObject;
    }
    public void returnObject(){

    }
}

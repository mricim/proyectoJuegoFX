package main.java.utils;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

import static main.java.jugadores.Jugador.listaPosicionesBatallones;
@Embeddable
@MappedSuperclass
abstract public class Posicion {
    private int fila;
    private int columna;
    private String position;

    public Posicion() {
    }

    public Posicion(int fila, int columna) {
        setFilaColumna(fila, columna);
    }

    public Posicion(String posicion) {
        this.position = posicion;
        String[] a = posicion.split("-");
        this.fila = Integer.parseInt(a[0]);
        this.columna = Integer.parseInt(a[1]);
    }

    @Column(name = "fila")
    public int getFila() {
        return fila;
    }

    @Column(name = "columna")
    public int getColumna() {
        return columna;
    }

    @Column(name = "posicion")
    public String getPosition() {
        return position;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setFilaColumna(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.position = fila + "-" + columna;
    }
}

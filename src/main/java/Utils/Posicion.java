package main.java.Utils;


import java.util.Objects;

abstract public class Posicion {
    private int fila;
    private int columna;
    private String position;

    public Posicion(int fila, int columna) {
        setFilaColumna(fila, columna);
    }

    public Posicion(String posicion) {
        this.position = posicion;
        String[] a = posicion.split("-");
        this.fila = Integer.parseInt(a[0]);
        this.columna = Integer.parseInt(a[1]);
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public String getPosition() {
        return position;
    }

    public void setFilaColumna(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.position = fila + "-" + columna;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Posicion)) return false;
        Posicion posicion = (Posicion) o;
        return getPosition().equals(posicion.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition());
    }

    @Override
    public String toString() {
        return "Posiciones{" +
                "fila=" + fila +
                ", columna=" + columna +
                '}';
    }
}

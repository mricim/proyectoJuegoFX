package main.java.juego;


public abstract class Posiciones {
    private int filas;
    private int columnas;
//Create objects contain

    public Posiciones(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        //this.object=object
        //add list objects *
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    @Override
    public String toString() {
        return "Posiciones{" +
                "y=" + filas +
                ", x=" + columnas +
                '}';
    }
}

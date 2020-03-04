package main.java.Utils;


public abstract class Posicion {
    private int fila;
    private int columna;
    private String position;
//Create objects contain

    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        position= fila +"_"+ columna;
        //this.object=object
        //add list objects *
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

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "Posiciones{" +
                "y=" + fila +
                ", x=" + columna +
                '}';
    }
}

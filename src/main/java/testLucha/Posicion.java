package main.java.testLucha;


import static main.java.testLucha.MainLucha2.*;

public class Posicion extends main.java.Utils.Posicion {
    private Soldados soldados;
    private int ejercito;

    public Posicion(int y, int x, int ejercito, int cantidad, Soldados soldados) {
        super(y, x);
        int total=soldados.getCantidad();
        soldados.setCantidad(total-cantidad);

        this.soldados = new Soldados(soldados.getTipoSoldado(),cantidad);
        this.ejercito=ejercito;

        if (ejercito == 1) {
            listaPosicionesSoldados1.put(y + "_" + x, this);
        } else {
            listaPosicionesSoldados2.put(y + "_" + x, this);
        }
    }

    public Soldados getSoldados() {
        return soldados;
    }

    @Override
    public String toString() {
        return "Posiciones{" +
                "soldados=" + soldados +
                ", y=" + super.getFila() +
                ", x=" + super.getColumna() +
                '}';
    }
}
package main.java.testLucha;


import static main.java.testLucha.MainLucha2.*;

public class Posicion extends main.java.Utils.Posicion {
    private SoldadosXX soldadosXX;
    private int ejercito;

    public Posicion(int y, int x, int ejercito, int cantidad, SoldadosXX soldadosXX) {
        super(y, x);
        int total= soldadosXX.getCantidad();
        soldadosXX.setCantidad(total-cantidad);

        this.soldadosXX = new SoldadosXX(soldadosXX.getTipoSoldado(),cantidad);
        this.ejercito=ejercito;

        if (ejercito == 1) {
            listaPosicionesSoldados1.put(y + "_" + x, this);
        } else {
            listaPosicionesSoldados2.put(y + "_" + x, this);
        }
    }

    public SoldadosXX getSoldadosXX() {
        return soldadosXX;
    }

    @Override
    public String toString() {
        return "Posiciones{" +
                "soldados=" + soldadosXX +
                ", y=" + super.getFila() +
                ", x=" + super.getColumna() +
                '}';
    }
}

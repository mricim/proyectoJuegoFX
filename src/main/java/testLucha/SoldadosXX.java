package main.java.testLucha;

import static main.java.testLucha.MainLucha1.tipoSoldadosTreeMap;

public class SoldadosXX {
    private static int lastId=0;
    private int id;
    private int TipoSoldado;
    private int cantidad;

    public SoldadosXX(int TipoSoldado, int cantidad) {
        id=lastId++;
        this.TipoSoldado = TipoSoldado;
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getTipoSoldado() {
        return TipoSoldado;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return id+"_"+tipoSoldadosTreeMap.get(TipoSoldado).getNombre()+"=" + cantidad;
    }
}

package main.java.testLucha;

import java.util.Objects;

import static main.java.testLucha.MainLucha.tipoSoldadosTreeMap;

public class Soldados {
    private static int lastId=0;
    private int id;
    private int TipoSoldado;
    private int cantidad;

    public Soldados(int TipoSoldado, int cantidad) {
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
        return tipoSoldadosTreeMap.get(TipoSoldado).getNombre()+"=" + cantidad;
    }
}

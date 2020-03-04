package main.java.juego.mapas.pelea;

import javafx.scene.image.Image;
import main.java.Utils.Posicion;

import static main.java.testLucha.MainLucha1.tipoSoldadosTreeMap;

public class Soldados extends Posicion {
    private int id;
    private SoldadosPreCargados soldadosPreCargados;
    private int cantidad;
    int municion;

    public Soldados(int id, SoldadosPreCargados soldadosPreCargados, int cantidad, int municion, int filas, int columnas) {
        super(filas, columnas);
        this.id = id;
        this.soldadosPreCargados = soldadosPreCargados;
        this.cantidad = cantidad;
        this.municion = municion;
    }

    public int getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public SoldadosPreCargados getSoldadosPreCargados() {
        return soldadosPreCargados;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Image getImage() {
        return soldadosPreCargados.getImage();
    }

    public Image getImageClicable() {
        return soldadosPreCargados.getImageClicable();
    }

    @Override
    public String toString() {
        return id + "_" + tipoSoldadosTreeMap.get(soldadosPreCargados).getNombre() + "=" + cantidad;
    }
}

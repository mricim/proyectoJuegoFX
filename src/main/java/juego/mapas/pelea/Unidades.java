package main.java.juego.mapas.pelea;

import javafx.scene.image.Image;
import main.java.utils.Posicion;


public class Unidades extends Posicion {
    private static int lastId = 1;
    private int id;
    private int cantidad;
    private UnidadesPreCargadas unidadesPreCargadas;
    int municion;


    public Unidades(UnidadesPreCargadas unidadesPreCargadas, int cantidad, int municion, int filas, int columnas) {
        super(filas, columnas);
        this.id = lastId++;
        this.unidadesPreCargadas = unidadesPreCargadas;
        this.cantidad = cantidad;
        this.municion = municion;
    }

    public int getId() {
        return id;
    }

    public int getTipeUnit() {
        return unidadesPreCargadas.getIdType();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void addCantidad(int cantidad) {
        int conversorAPositivo = (cantidad < 0 ? -cantidad : cantidad);
        this.cantidad += conversorAPositivo;
    }

    public void removeCantidad(int cantidad) {
        int conversorAPositivo = (cantidad < 0 ? -cantidad : cantidad);
        this.cantidad -= conversorAPositivo;
    }

    public UnidadesPreCargadas getUnidadesPreCargadas() {
        return unidadesPreCargadas;
    }

    public Image getImage() {
        return unidadesPreCargadas.getImage();
    }

    public Image getImageClicable() {
        return unidadesPreCargadas.getImageClicable();
    }

    public Image getImageIcon() {
        return unidadesPreCargadas.getImageIcon();
    }
}

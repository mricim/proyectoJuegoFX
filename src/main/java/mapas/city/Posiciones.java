package main.java.mapas.city;

import javafx.scene.image.Image;
import main.java.mapas.city.edificios.Edificio;
import main.java.mapas.city.edificios.quiza.BuscadorEdificios;
import main.java.mapas.city.edificios.quiza.Edificios;

import static main.java.mapas.city.Main.listaPosiciones;

class Posiciones {
    private int y;
    private int x;
    private Edificio edificio;


    public Posiciones(int y, int x, Edificio edificioId) {
        this.y = y;
        this.x = x;
        this.edificio = edificioId;
        listaPosiciones.put(new Integer[]{y, x}, this);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public Image getImage() {
        return edificio.getImage();
    }

    public Image getImageOnMouseOver() {
        return edificio.getImageClicable();
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setEdificios(Edificio edificios) {
        this.edificio = edificios;
    }
}

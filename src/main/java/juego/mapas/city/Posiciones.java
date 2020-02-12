package main.java.juego.mapas.city;

import javafx.scene.image.Image;

import static main.java.juego.mapas.city.Ciudad.listaPosiciones;

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

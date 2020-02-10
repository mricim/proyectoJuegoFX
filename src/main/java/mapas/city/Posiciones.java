package main.java.mapas.city;

import main.java.mapas.city.edificios.Edificios;

import static main.java.mapas.city.Main.listaPosiciones;

class Posiciones {
    private int y;
    private int x;
    private int edificioId;


    public Posiciones(int y, int x, int edificio) {
        this.y = y;
        this.x = x;
        this.edificioId = edificio;
        listaPosiciones.put(new Integer[]{y,x},this);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getEdificio() {
        return edificioId;
    }

    public String getPathImage() {
        return Edificios.returnEdificio(edificioId).getImage();
    }

    public String getPathImageOnMouseOver() {
        return Edificios.returnEdificio(edificioId).getImageClicable();
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setEdificios(int edificios) {
        this.edificioId = edificios;
    }
}

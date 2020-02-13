package main.java.juego.mapas.city;

import javafx.scene.image.Image;
import main.java.juego.Posiciones;


class PosicionEdificio extends Posiciones {
    private Edificio edificio;

    public PosicionEdificio(int y, int x,Ciudad ciudad, Edificio edificioId) {
        super(y, x);
            this.edificio = edificioId;
            ciudad.listaPosicionesEdificios.put(new Integer[]{y, x}, this);
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

    public void setEdificios(Edificio edificios) {
        this.edificio = edificios;
    }
}

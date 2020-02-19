package main.java.juego.mapas.Pelea;

import javafx.scene.image.Image;
import main.java.juego.Posiciones;


public class PosicionSoldado extends Posiciones {
    private Soldados soldado;

    public PosicionSoldado(int y, int x, Batallon batallon, Soldados soldadoId) {
        super(y, x);
            this.soldado = soldadoId;
            batallon.addListaPosicionesSoldados(y+"_"+x, this);
    }



    public Soldados getSoldado() {
        return soldado;
    }

    public Image getImage() {
        return soldado.getImage();
    }

    public Image getImageOnMouseOver() {
        return soldado.getImageClicable();
    }

    public void setSoldado(Soldados soldado) {
        this.soldado = soldado;
    }
}

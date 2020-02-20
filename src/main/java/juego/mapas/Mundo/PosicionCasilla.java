package main.java.juego.mapas.Mundo;

import javafx.scene.image.Image;
import main.java.Utils.Posiciones;


public class PosicionCasilla extends Posiciones {
    private Casilla casilla;

    public PosicionCasilla(int y, int x, Casilla casillaId) {
        super(y, x);
            this.casilla = casillaId;
//            .addListaX(y+"_"+x, this);
    }



    public Casilla getCasilla() {
        return casilla;
    }

    public Image getImage() {
        return casilla.getImage();
    }

    public Image getImageOnMouseOver() {
        return casilla.getImageClicable();
    }

    public void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }
}

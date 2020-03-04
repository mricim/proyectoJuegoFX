package main.java.juego.mapas.mundo;

import javafx.scene.image.Image;


public class Casilla {
    private int id;//GUARDAR
    private TiposDeCasillas tiposDeCasillas;


    public Casilla(TiposDeCasillas tiposDeCasillas) {
        this.id = tiposDeCasillas.getId();
        this.tiposDeCasillas = tiposDeCasillas;

        //TODO //POSIBLEMENTE ESTO LLENE LA RAM
        getImage();
        getImageClicable();
    }


    public int getId() {
        return id;
    }


    public TiposDeCasillas getTiposDeCasillas() {
        return tiposDeCasillas;
    }

    public Image getImage() {
        return tiposDeCasillas.getImage();
    }
    public Image getImageClicable() {
        return tiposDeCasillas.getImageClicable();
    }
}

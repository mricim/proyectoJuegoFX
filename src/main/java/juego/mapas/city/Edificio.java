package main.java.juego.mapas.city;

import javafx.scene.image.Image;
import main.java.juego.mapas.CallImages;
import main.java.juego.mapas.EdificiosPreCargados;
import main.java.juego.mapas.ImageGetter;

import java.util.Objects;


public class Edificio implements ImageGetter, Cloneable {
    static String RUTEIMAGES = "mapas/city/";

    private int id;//GUARDAR
    private int nivel;//GUARDAR
    private int necesitaTrabajadoresXmin;//GUARDAR

    private String image;
    private String imageClicable;

    public Edificio(EdificiosPreCargados edificioPreCargado) {
        this.id = edificioPreCargado.getId();


        this.image = getPathNameImage();
        this.imageClicable = getPathNameImageClicable();
        //TODO //POSIBLEMENTE ESTO LLENE LA RAM
        /*
        new CallImages(RUTEIMAGES, image);
        new CallImages(RUTEIMAGES, imageClicable);
        */
    }

    public Object clone() throws CloneNotSupportedException {//https://stackoverflow.com/questions/869033/how-do-i-copy-an-object-in-java
        return super.clone();
    }


    public int getId() {
        return id;
    }

    public int getNivel() {
        return nivel;
    }

    public int getNecesitaTrabajadoresXmin() {
        return necesitaTrabajadoresXmin;
    }

    @Override
    public Image getImage() {
        return CallImages.getImage(RUTEIMAGES, image);
    }

    @Override
    public Image getImageClicable() {
        return CallImages.getImage(RUTEIMAGES, imageClicable);
    }

    private String getPathNameImage() {
        return getId() + "_" + nivel;
    }

    private String getPathNameImageClicable() {
        return getId() + "_" + nivel + "@clic";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edificio)) return false;
        Edificio edificio = (Edificio) o;
        return getId() == edificio.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}

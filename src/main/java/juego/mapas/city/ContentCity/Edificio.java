package main.java.juego.mapas.city.ContentCity;

import javafx.scene.image.Image;
import main.java.juego.mapas.EdificiosPreCargados;
import main.java.juego.mapas.ImageGetter;

import java.util.Objects;


public class Edificio implements ImageGetter, Cloneable {
    static String RUTEIMAGES = "mapas/city/";
    private String imagePath;
    private String imageClicablePath;

    private int id;//GUARDAR
    private int nivel;//GUARDAR
    private int necesitaTrabajadoresXmin;//GUARDAR
    private EdificiosPreCargados edificiosPreCargados;


    public Edificio(EdificiosPreCargados edificioPreCargado) {
        this.id = edificioPreCargado.getId();
        this.nivel = edificioPreCargado.getNivel();
        this.edificiosPreCargados = edificioPreCargado;

        int id = edificiosPreCargados.getId();
        int nivel = edificiosPreCargados.getNivel();
        String resultado = id + "_" + nivel;
        this.imagePath = resultado;
        this.imageClicablePath = resultado + "@clic";
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

    public void setNecesitaTrabajadoresXmin(int necesitaTrabajadoresXmin) {
        this.necesitaTrabajadoresXmin = necesitaTrabajadoresXmin;
    }

    public EdificiosPreCargados getEdificiosPreCargados() {
        return edificiosPreCargados;
    }

    @Override
    public Image getImage() {
        return getImage(RUTEIMAGES, imagePath);
        //return CallImages.getImage(RUTEIMAGES, imagePath);
    }

    @Override
    public Image getImageClicable() {
        return getImageClicable(RUTEIMAGES, imageClicablePath);
        //return CallImages.getImage(RUTEIMAGES, imageClicablePath);
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

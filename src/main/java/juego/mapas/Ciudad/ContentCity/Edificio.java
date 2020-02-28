package main.java.juego.mapas.Ciudad.ContentCity;

import javafx.scene.image.Image;
import main.java.Utils.Posiciones;
import main.java.juego.mapas.Ciudad.Ciudad;
import main.java.juego.mapas.Ciudad.EdificiosPreCargados;

import java.util.Objects;


public class Edificio extends Posiciones implements Cloneable {
    private int id;//GUARDAR
    private int nivel;//GUARDAR
    private int necesitaTrabajadoresXmin;//GUARDAR
    private EdificiosPreCargados edificiosPreCargado;


    public Edificio(EdificiosPreCargados edificioPreCargado, int filas, int columnas, Ciudad ciudad) {
        super(filas,columnas);
        this.id = edificioPreCargado.getId();
        this.nivel = edificioPreCargado.getNivel();
        this.edificiosPreCargado = edificioPreCargado;


        //TODO //POSIBLEMENTE ESTO LLENE LA RAM
        getImage();
        getImageClicable();



        ciudad.addListaPosicionesEdificios(filas+"_"+columnas, this);
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

    public EdificiosPreCargados getEdificiosPreCargado() {
        return edificiosPreCargado;
    }

    public void setEdificiosPreCargado(EdificiosPreCargados edificiosPreCargado) {
        this.id = edificiosPreCargado.getId();
        this.nivel = edificiosPreCargado.getNivel();
        this.edificiosPreCargado = edificiosPreCargado;
    }

    public Image getImage() {
        return edificiosPreCargado.getImage();
    }
    public Image getImageClicable() {
        return edificiosPreCargado.getImageClicable();
    }
}

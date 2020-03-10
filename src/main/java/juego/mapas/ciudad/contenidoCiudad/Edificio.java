package main.java.juego.mapas.ciudad.contenidoCiudad;

import javafx.scene.image.Image;
import main.java.Utils.Posicion;
import main.java.juego.mapas.ciudad.Ciudad;
import main.java.juego.mapas.ciudad.EdificiosPreCargados;


public class Edificio extends Posicion implements Cloneable {
    private int id;//GUARDAR
    private int nivel;//GUARDAR
    private int trabajadoresPuestos;//GUARDAR
    private EdificiosPreCargados edificiosPreCargado;


    public Edificio(EdificiosPreCargados edificioPreCargado, int filas, int columnas, Ciudad ciudad) {
        super(filas,columnas);
        this.id = edificioPreCargado.getId();
        this.nivel = edificioPreCargado.getNivel();
        this.edificiosPreCargado = edificioPreCargado;


        //TODO //POSIBLEMENTE ESTO LLENE LA RAM
        getImage();
        getImageClicable();



        ciudad.addListaPosicionesEdificios(getPosition(), this);
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

    public int getTrabajadoresPuestos() {
        return trabajadoresPuestos;
    }

    public void setTrabajadoresPuestos(int trabajadoresPuestos) {
        this.trabajadoresPuestos = trabajadoresPuestos;
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

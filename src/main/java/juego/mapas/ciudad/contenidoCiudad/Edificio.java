package main.java.juego.mapas.ciudad.contenidoCiudad;

import javafx.scene.image.Image;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.RecursosPrecargados;
import main.java.jugadores.Jugador;
import main.java.utils.Posicion;
import main.java.juego.mapas.ciudad.Ciudad;
import main.java.juego.mapas.ciudad.EdificiosPreCargados;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Representa un Edificio creado por un {@link Jugador} en
 * una {@link Ciudad}.
 * @author Eric Casanova y Alejandro Riera
 * @version 1.0
 * @since 1.0
 */
public class Edificio extends Posicion implements Cloneable {
    private int id;//GUARDAR
    private int nivel;//GUARDAR
    Map<Integer, Recursos> trabajadoresNecesarios = new TreeMap<>();//GUARDAR
    private EdificiosPreCargados edificiosPreCargado;


    /**
     * Crea un Edificio en una {@link Ciudad} en una posición  x,y basándose en los argumentos filas
     * y columnas, los datos del edificio serán cargados mediante el argumento edificioPrecargado.
     * @param edificioPreCargado Un objeto de tipo EdificiosPrecargados que contiene los datos del edificio a crear.
     * @param filas Un integer que indica la fila en la que construir el edificio.
     * @param columnas Un integer que indica la columna en la que construir el edificio.
     * @param ciudad Un objeto de tipo Ciudad que indica en que ciudad se crea el edificio.
     */
    public Edificio(EdificiosPreCargados edificioPreCargado, int filas, int columnas, Ciudad ciudad) {
        super(filas, columnas);
        this.id = edificioPreCargado.getId();
        this.nivel = edificioPreCargado.getNivel();
        this.edificiosPreCargado = edificioPreCargado;

        Map<RecursosPrecargados, ArrayList<Recursos>> i = edificioPreCargado.getRecursosCosteXmin();
        if (i != null) {
            for (RecursosPrecargados recursos : i.keySet()) {
                boolean temporal = false;
                for (Recursos recursos1 : i.get(recursos)) {
                    if (!recursos1.getRecursosPrecargados().isSeConsumeEnEdificios()) {
                        temporal = true;
                        break;
                    }
                }
                if (temporal) {
                    trabajadoresNecesarios.put(recursos.getId(), new Recursos(recursos, 0));
                }
            }
        }


        //TODO //POSIBLEMENTE ESTO LLENE LA RAM
        getImage();
        getImageClicable();


        ciudad.addListaPosicionesEdificios(getPosition(), this);
    }

    /**
     * Devuelve un objeto clonado.
     * @return El objeto clonado.
     * @throws CloneNotSupportedException Lanza una exception en caso de no poder clonar el objeto.
     */
    public Object clone() throws CloneNotSupportedException {//https://stackoverflow.com/questions/869033/how-do-i-copy-an-object-in-java
        return super.clone();
    }


    /**
     * Devuelve el id del edificio.
     * @return Un integer que contiene el valor del id del edificio.
     */
    public int getId() {
        return id;
    }

    /**
     * Devuelve el nivel del edificio.
     * @return Un integer que contiene el valor del nivel del edificio.
     */
    public int getNivel() {
        return nivel;
    }


    /**
     * Devuelve un {@code TreeMap} del edificio con el id del recurso como {@code key} y
     * un Recurso como {@code valor}.
     * @return Un {@code TreeMap<Integer,Recursos>} que contiene el valor de los trabajadores necesarios para producir x Recursos en ese edificio.
     */
    public Map<Integer, Recursos> getTrabajadoresNecesarios() {
        return trabajadoresNecesarios;
    }

    /**
     * Devuelve un objeto de tipo EdificioPrecargados.
     * @return Un EdificioPrecargados que contiene los valores para las variables {@code id} y {@code nivel}.
     */
    public EdificiosPreCargados getEdificiosPreCargado() {
        return edificiosPreCargado;
    }

    /**
     * Asigna el valor del EdificioPrecargados.
     * @param edificiosPreCargado Un objeto de tipo EdificiosPrecargados que contiene los valores de {@code id} y {@code nivel},
     *                            los cuáles serán asignados a dichas variables del Edificio.
     */
    public void setEdificiosPreCargado(EdificiosPreCargados edificiosPreCargado) {
        this.id = edificiosPreCargado.getId();
        this.nivel = edificiosPreCargado.getNivel();
        this.edificiosPreCargado = edificiosPreCargado;
    }

    /**
     * Devuelve un objeto de tipo Image.
     * @return Un objeto de tipo Image que equivale al archivo de la imagen del Edificio, en formato <b>png</b> o <b>jpeg</b>.
     */
    public Image getImage() {
        return edificiosPreCargado.getImage();
    }
    /**
     * Devuelve un objeto de tipo Image.
     * @return Un objeto de tipo Image que equivale al archivo de la imagen clickable del Edificio, en formato <b>png</b> o <b>jpeg</b>.
     */
    public Image getImageClicable() {
        return edificiosPreCargado.getImageClicable();
    }
}

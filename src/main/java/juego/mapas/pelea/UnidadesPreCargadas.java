package main.java.juego.mapas.pelea;


import javafx.scene.image.Image;
import main.java.Utils.ImageGetter;
import main.java.juego.mapas.Recursos;

import java.util.List;

import static main.java.jugadores.Jugador.listaSoldadosPreCargada;


abstract public class UnidadesPreCargadas implements ImageGetter {

    private int id;
    private String nombre;
    private int maxMunicion;

    static String RUTEIMAGES = "mapas/pelea/unidades/";
    static String RUTEICON = "icons/soldados/";
    private String imagePath;
    private List<Recursos> costes;
    private String imageClicablePath;
    private String imageIconPath;

    public UnidadesPreCargadas(int id, String nombre, int maxMunicion,List<Recursos>costes) {
        this.id = id;
        this.nombre = nombre;
        this.maxMunicion=maxMunicion;
        this.costes=costes;
        this.imagePath = String.valueOf(id);
        this.imageClicablePath = id + "@H";
        this.imageIconPath = String.valueOf(id);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Recursos> getCostes() {
        return costes;
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
    public Image getImageIcon() {
        return getImageClicable(RUTEICON, imageIconPath);
        //return CallImages.getImage(RUTEIMAGES, imageClicablePath);
    }
}

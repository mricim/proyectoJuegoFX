package main.java.juego.mapas.pelea;


import javafx.scene.image.Image;
import main.java.Utils.ImageGetter;

import static main.java.jugadores.Jugador.listaSoldadosPreCargada;


abstract public class UnidadesPreCargadas implements ImageGetter {

    private int id;
    private String nombre;
    private int maxMunicion;

    static String RUTEIMAGES = "mapas/pelea/unidades/";
    private String imagePath;
    private String imageClicablePath;

    public UnidadesPreCargadas(int id, String nombre, int maxMunicion) {
        this.id = id;
        this.nombre = nombre;
        this.maxMunicion=maxMunicion;

        this.imagePath = String.valueOf(id);
        this.imageClicablePath = id + "@clic";
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
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
}

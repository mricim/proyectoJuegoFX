package main.java.juego.mapas.pelea;


import javafx.scene.image.Image;
import main.java.utils.ImageGetter;
import main.java.juego.mapas.Recursos;

import java.util.List;

import static main.java.Inicio.PantallaInicialController.elTemaSeleccionado;


public class UnidadesPreCargadas implements ImageGetter {

    private final int idType;
    private final String nombre;
    private final int maxMunicion;

    static String RUTEIMAGES = "mapas/pelea/unidades/";
    static String RUTEICON = "icons/soldados/";
    private final String imagePath;
    private final List<Recursos> costes;
    private final String imageClicablePath;
    private final String imageIconPath;
    private final int tipoLucha;

    public UnidadesPreCargadas(int idType, String nombre, int maxMunicion, List<Recursos>costes, int tipoLucha) {
        this.idType = idType;
        this.nombre = nombre;
        this.maxMunicion=maxMunicion;
        this.costes=costes;
        String imagePath= String.valueOf(idType);
        this.imagePath =imagePath;
        this.imageClicablePath = imagePath + "@H";
        this.imageIconPath = imagePath;
        this.tipoLucha = tipoLucha;
        elTemaSeleccionado.listaSoldadosPreCargada.put(idType,this);
    }

    public int getIdType() {
        return idType;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Recursos> getCostes() {
        return costes;
    }

    public int getTipoLucha() {
        return tipoLucha;
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

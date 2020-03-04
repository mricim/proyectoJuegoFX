package main.java.juego.mapas.mundo;


import javafx.scene.image.Image;
import main.java.Utils.ImageGetter;

public class TiposDeCasillas implements ImageGetter {
    private int id;
    private String nombre;

    static String RUTEIMAGES = "mapas/city/";
    private String imagePath;
    private String imageClicablePath;

    public TiposDeCasillas(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;

//        treemap.put(id,this);
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

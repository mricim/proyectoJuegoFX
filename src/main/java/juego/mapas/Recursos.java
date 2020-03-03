package main.java.juego.mapas;

import javafx.scene.image.Image;
import main.java.Utils.ImageGetter;

public class Recursos implements ImageGetter {
    static String RUTEIMAGES = "icons/recursos/";
    //oro=0;
    //madera=1;
    //piedra=2;
    //comida=3;
    //hierro=4;
    //poblacion=5;
    //felicidad=6;
    //investigacion=7
    private int id;
    private int cantidad;

    public Recursos(int id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
        getImage();
    }

    public int getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public static String getName(int id) {
        String text;
        switch (id) {
            case 0:
                text = "oro";
                break;
            case 1:
                text = "madera";
                break;
            case 2:
                text = "piedra";
                break;
            case 3:
                text = "comida";
                break;
            case 4:
                text = "hierro";
                break;
            case 5:
                text = "poblacion";
                break;
            case 6:
                text = "felicidad";
                break;
            case 7:
                text = "investigacion";
                break;

            default:
                text = "error";
                break;
        }
        return text;

    }

    @Override
    public Image getImage() {
        return getImage(RUTEIMAGES, String.valueOf(id));
    }

    @Override
    public Image getImageClicable() {
        System.err.println("getImageClicable() en Recursos no funciona!");
        return null;
    }
}

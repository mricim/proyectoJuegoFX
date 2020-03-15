package main.java.juego.mapas;

import javafx.scene.image.Image;
import main.java.Utils.ImageGetter;

import java.util.HashMap;
import java.util.Objects;

public class RecursosPrecargados implements ImageGetter {
    static String RUTEIMAGES = "icons/recursos/";
    static HashMap<Integer,RecursosPrecargados> recursosPrecargadosList=new HashMap<>();
    //oro=0;
    //madera=1;
    //piedra=2;
    //comida=3;
    //hierro=4;
    //poblacion=5;
    //felicidad=6;
    //investigacion=7
    private int id;
    private String name;
    private boolean esSelectable;
    private Integer elSeletableSera;//apuntan a poblacion

    public RecursosPrecargados(int id, String name, boolean esSelectable, Integer elSeletableSera) {
        this.id = id;
        this.name = name;
        this.esSelectable = esSelectable;
        this.elSeletableSera = elSeletableSera;
        getImage();
        recursosPrecargadosList.put(id,this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isEsSelectable() {
        return esSelectable;
    }

    public Integer getElSeletableSera() {
        return elSeletableSera;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecursosPrecargados)) return false;
        RecursosPrecargados that = (RecursosPrecargados) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
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

    @Override
    public Image getImageIcon() {
        return null;
    }
}

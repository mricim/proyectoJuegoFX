package main.java.mapas.city.edificios;

import com.sun.istack.internal.NotNull;

import java.util.Objects;

import static main.java.mapas.city.Main.edificios;

public class Edificios implements ImageGetter {
    @NotNull
    private int id;
    @NotNull
    private String nombre;
    @NotNull
    private boolean destruible;

    private Edificios(int id) {
        this.id = id;
    }

    public Edificios(int id, String nombre, boolean destruible) {
        this.id = id;
        this.nombre = nombre;
        this.destruible = destruible;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isDestruible() {
        return destruible;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edificios)) return false;
        Edificios edificios = (Edificios) o;
        return getId() == edificios.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    static public Edificios returnEdificio(int edificio){
        return(Edificios) edificios.get(edificios.indexOf(new Edificios(edificio)));
    }

    @Override
    public String getImage() {
        return null;
    }

    @Override
    public String getImageClicable() {
        return null;
    }
}

interface ImageGetter {
    abstract public String getImage();
    abstract public String getImageClicable();
}
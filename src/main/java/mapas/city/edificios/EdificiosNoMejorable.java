package main.java.mapas.city.edificios;


public class EdificiosNoMejorable extends Edificios {

    public EdificiosNoMejorable(int id, String nombre, boolean destruible) {
        super(id, nombre, destruible);
    }

    @Override
    public String getImage() {
        return String.valueOf(getId());
    }

    @Override
    public String getImageClicable() {
        return String.valueOf(getId() + "-clic");
    }

}

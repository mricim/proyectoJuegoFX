package main.java.mapas.city.edificios;

import com.sun.istack.internal.NotNull;

abstract class EdificiosMejorables extends Edificios {
    @NotNull
    private int nivel;
    @NotNull
    private int nivelMax;
    @NotNull
    private int costeOro;
    @NotNull
    private int costeMadera;
    @NotNull
    private int costePiedra;
    @NotNull
    private int costehierro;

    public EdificiosMejorables(int id, String nombre, boolean destruible, int nivel, int nivelMax, int costeOro, int costeMadera, int costePiedra, int costehierro) {
        super(id, nombre, destruible);
        this.nivel = nivel;
        this.nivelMax = nivelMax;
        this.costeOro = costeOro;
        this.costeMadera = costeMadera;
        this.costePiedra = costePiedra;
        this.costehierro = costehierro;
    }

    public String getImage() {
        return String.valueOf(getId() + "_" + nivel);
    }

    @Override
    public String getImageClicable() {
        return String.valueOf(getId() + "_" + nivel + "-clic");
    }
}

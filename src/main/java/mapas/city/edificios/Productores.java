package main.java.mapas.city.edificios;

public class Productores extends EdificiosMejorables{
    private int necesitaTrabajadoresXmin;
    private int produceMaderaXmin;
    private int producePiedraXmin;
    private int produceHierroXmin;
    private int produceComidaXmin;
    private int costeOroXmin;
    private int produceFelicidadXmin;
    private int produceInvestigacionXmin;

    public Productores(int id, String nombre, boolean destruible, int nivel, int nivelMax, int costeOro, int costeMadera, int costePiedra, int costehierro, int necesitaTrabajadoresXmin, int produceMaderaXmin, int producePiedraXmin, int produceHierroXmin, int produceComidaXmin, int costeOroXmin, int produceFelicidadXmin, int produceInvestigacionXmin) {
        super(id, nombre, destruible, nivel, nivelMax, costeOro, costeMadera, costePiedra, costehierro);
        this.necesitaTrabajadoresXmin = necesitaTrabajadoresXmin;
        this.produceMaderaXmin = produceMaderaXmin;
        this.producePiedraXmin = producePiedraXmin;
        this.produceHierroXmin = produceHierroXmin;
        this.produceComidaXmin = produceComidaXmin;
        this.costeOroXmin = costeOroXmin;
        this.produceFelicidadXmin = produceFelicidadXmin;
        this.produceInvestigacionXmin = produceInvestigacionXmin;
    }

}

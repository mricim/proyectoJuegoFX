package main.java.mapas.city.edificios;

public class Almacen extends EdificiosMejorables{
    private int madera;
    private int piedra;
    private int hierro;
    private int comida;

    public Almacen(int id, String nombre, boolean destruible, int nivel, int nivelMax, int costeOro, int costeMadera, int costePiedra, int costehierro, int madera, int piedra, int hierro, int comida) {
        super(id, nombre, destruible, nivel, nivelMax, costeOro, costeMadera, costePiedra, costehierro);
        this.madera = madera;
        this.piedra = piedra;
        this.hierro = hierro;
        this.comida = comida;
    }
}

package main.java.juego.mapas;

import static main.java.juego.Jugador.listaEdificiosPreCargada;

//https://github.com/k33ptoo/JavaFX-MySQL-Login
public class EdificiosPreCargados{
    private int id;
    private String nombre;
    private boolean destruible;
    private boolean construible;
    private int maximoEdificiosDelMismoTipo;
    //Mejorables
    private int nivelMaximo;
    private int nivelCastilloNecesario;
    private int costeOro;
    private int costeMadera;
    private int costePiedra;
    private int costehierro;
    private int TrabajadoresXminMaximos;
    //Productores
    private int produceMaderaXmin;
    private int producePiedraXmin;
    private int produceHierroXmin;
    private int produceComidaXmin;
    private int costeOroXmin;
    private int produceFelicidadXmin;
    private int produceInvestigacionXmin;
    //ALMACEN
    private int maderaAlmacen;
    private int piedraAlmacen;
    private int hierroAlmacen;
    private int comidaAlmacen;


    public EdificiosPreCargados(int id, String nombre, boolean destruible, boolean construible, int maximoEdificiosDelMismoTipo, int nivelMaximo, int nivelCastilloNecesario, int costeOro, int costeMadera, int costePiedra, int costehierro, int TrabajadoresXminMaximos, int produceMaderaXmin, int producePiedraXmin, int produceHierroXmin, int produceComidaXmin, int costeOroXmin, int produceFelicidadXmin, int produceInvestigacionXmin, int maderaAlmacen, int piedraAlmacen, int hierroAlmacen, int comidaAlmacen) {
        this.id = id;
        this.nombre = nombre;
        this.destruible = destruible;
        this.construible = construible;
        this.maximoEdificiosDelMismoTipo=maximoEdificiosDelMismoTipo;
        //MEJORABLES
        this.nivelMaximo = nivelMaximo;
        this.nivelCastilloNecesario=nivelCastilloNecesario;
        this.costeOro = costeOro;
        this.costeMadera = costeMadera;
        this.costePiedra = costePiedra;
        this.costehierro = costehierro;
        this.TrabajadoresXminMaximos = TrabajadoresXminMaximos;
        //PRODUCTORES
        this.produceMaderaXmin = produceMaderaXmin;
        this.producePiedraXmin = producePiedraXmin;
        this.produceHierroXmin = produceHierroXmin;
        this.produceComidaXmin = produceComidaXmin;
        this.costeOroXmin = costeOroXmin;
        this.produceFelicidadXmin = produceFelicidadXmin;
        this.produceInvestigacionXmin = produceInvestigacionXmin;
        //ALMACEN
        this.maderaAlmacen = maderaAlmacen;
        this.piedraAlmacen = piedraAlmacen;
        this.hierroAlmacen = hierroAlmacen;
        this.comidaAlmacen = comidaAlmacen;

        listaEdificiosPreCargada.put(id,this);
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

    public boolean isConstruible() {
        return construible;
    }

    public int getMaximoEdificiosDelMismoTipo() {
        return maximoEdificiosDelMismoTipo;
    }

    public int getNivelMaximo() {
        return nivelMaximo;
    }
    public int getNivelCastilloNecesario() {
        return nivelCastilloNecesario;
    }

    public int getCosteOro() {
        return costeOro;
    }

    public int getCosteMadera() {
        return costeMadera;
    }

    public int getCostePiedra() {
        return costePiedra;
    }

    public int getCostehierro() {
        return costehierro;
    }

    public int getTrabajadoresXminMaximos() {
        return TrabajadoresXminMaximos;
    }

    public int getProduceMaderaXmin() {
        return produceMaderaXmin;
    }

    public int getProducePiedraXmin() {
        return producePiedraXmin;
    }

    public int getProduceHierroXmin() {
        return produceHierroXmin;
    }

    public int getProduceComidaXmin() {
        return produceComidaXmin;
    }

    public int getCosteOroXmin() {
        return costeOroXmin;
    }

    public int getProduceFelicidadXmin() {
        return produceFelicidadXmin;
    }

    public int getProduceInvestigacionXmin() {
        return produceInvestigacionXmin;
    }

    public int getMaderaAlmacen() {
        return maderaAlmacen;
    }

    public int getPiedraAlmacen() {
        return piedraAlmacen;
    }

    public int getHierroAlmacen() {
        return hierroAlmacen;
    }

    public int getComidaAlmacen() {
        return comidaAlmacen;
    }
}

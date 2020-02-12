package main.java.juego.mapas;

public class EdificiosPreCargados{
    private int id;
    private String nombre;
    private boolean destruible;
    private boolean construible;
    private String image;
    private String imageClicable;
    //Mejorables
    private int nivel;
    private int nivelCastilloNecesario;
    private int costeOro;
    private int costeMadera;
    private int costePiedra;
    private int costehierro;
    private int necesitaTrabajadoresXmin;
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

    public EdificiosPreCargados(int id, String nombre) {
        //NO MEJORABLES
        this(id, nombre, false, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public EdificiosPreCargados(int id, String nombre, int nivel, int costeOro, int costeMadera, int costePiedra, int costehierro, int necesitaTrabajadoresXmin) {
        //MEJORABLES
        this(id, nombre, false, nivel, costeOro, costeMadera, costePiedra, costehierro, necesitaTrabajadoresXmin, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public EdificiosPreCargados(int id, String nombre, int nivel, int costeOro, int costeMadera, int costePiedra, int costehierro, int necesitaTrabajadoresXmin, int produceMaderaXmin, int producePiedraXmin, int produceHierroXmin, int produceComidaXmin, int costeOroXmin, int produceFelicidadXmin, int produceInvestigacionXmin) {
        //MEJORABLES //PRODUCTORES
        this(id, nombre, true, nivel, costeOro, costeMadera, costePiedra, costehierro, necesitaTrabajadoresXmin, produceMaderaXmin, producePiedraXmin, produceHierroXmin, produceComidaXmin, costeOroXmin, produceFelicidadXmin, produceInvestigacionXmin, 0, 0, 0, 0);
    }

    public EdificiosPreCargados(int id, String nombre, boolean destruible, int nivel, int costeOro, int costeMadera, int costePiedra, int costehierro, int necesitaTrabajadoresXminint, int maderaAlmacen, int piedraAlmacen, int hierroAlmacen, int comidaAlmacen) {
        //MEJORABLES //ALMACEN
        this(id, nombre, destruible, nivel, costeOro, costeMadera, costePiedra, costehierro, necesitaTrabajadoresXminint, 0, 0, 0, 0, 0, 0, 0, maderaAlmacen, piedraAlmacen, hierroAlmacen, comidaAlmacen);
    }
    public EdificiosPreCargados(int id, String nombre, boolean destruible, int nivel, int costeOro, int costeMadera, int costePiedra, int costehierro, int necesitaTrabajadoresXmin, int produceMaderaXmin, int producePiedraXmin, int produceHierroXmin, int produceComidaXmin, int costeOroXmin, int produceFelicidadXmin, int produceInvestigacionXmin, int maderaAlmacen, int piedraAlmacen, int hierroAlmacen, int comidaAlmacen) {
        this.id = id;
        this.nombre = nombre;
        this.destruible = destruible;
        //MEJORABLES
        this.nivel = nivel;
        this.costeOro = costeOro;
        this.costeMadera = costeMadera;
        this.costePiedra = costePiedra;
        this.costehierro = costehierro;
        this.necesitaTrabajadoresXmin = necesitaTrabajadoresXmin;
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

        this.image = getPathNameImage();
        this.imageClicable = getPathNameImageClicable();
    }
    private String getPathNameImage() {
        return id + "_" + nivel;
    }
    private String getPathNameImageClicable() {
        return id + "_" + nivel + "@clic";
    }
}

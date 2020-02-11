package main.java.mapas.city.edificios;

import javafx.scene.image.Image;
import main.java.mapas.CallImages;

import java.util.Objects;

import static main.java.mapas.city.Main.listaEdificios;

public class Edificio implements ImageGetter {
    static String RUTEIMAGES = "mapas/city/";
    private int id;//GUARDAR
    private String nombre;
    private boolean destruible;
    private String image;
    private String imageClicable;
    //Mejorables
    private int nivel;//GUARDAR
    private int costeOro;
    private int costeMadera;
    private int costePiedra;
    private int costehierro;
    private int necesitaTrabajadoresXmin;//GUARDAR
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


    private Edificio(int id) {
        //SOLO PARA BUSCAR
        this.id = id;
    }

    static public Edificio returnEdificio(int edificioID) {
        return (Edificio) listaEdificios.get(listaEdificios.indexOf(new Edificio(edificioID)));
    }


    //CONSTRUCTORES
    public Edificio(int id, String nombre) {
        //NO MEJORABLES
        this(id, nombre, false, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public Edificio(int id, String nombre, boolean destruible, int nivel, int costeOro, int costeMadera, int costePiedra, int costehierro, int necesitaTrabajadoresXmin) {
        //MEJORABLES
        this(id, nombre, destruible, nivel, costeOro, costeMadera, costePiedra, costehierro, necesitaTrabajadoresXmin, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public Edificio(int id, String nombre, int nivel, int costeOro, int costeMadera, int costePiedra, int costehierro, int necesitaTrabajadoresXmin, int produceMaderaXmin, int producePiedraXmin, int produceHierroXmin, int produceComidaXmin, int costeOroXmin, int produceFelicidadXmin, int produceInvestigacionXmin) {
        //MEJORABLES //PRODUCTORES
        this(id, nombre, true, nivel, costeOro, costeMadera, costePiedra, costehierro, necesitaTrabajadoresXmin, produceMaderaXmin, producePiedraXmin, produceHierroXmin, produceComidaXmin, costeOroXmin, produceFelicidadXmin, produceInvestigacionXmin, 0, 0, 0, 0);
    }

    public Edificio(int id, String nombre, int nivel, int costeOro, int costeMadera, int costePiedra, int costehierro, int necesitaTrabajadoresXminint, int maderaAlmacen, int piedraAlmacen, int hierroAlmacen, int comidaAlmacen) {
        //MEJORABLES //ALMACEN
        this(id, nombre, true, nivel, costeOro, costeMadera, costePiedra, costehierro, necesitaTrabajadoresXminint, 0, 0, 0, 0, 0, 0, 0, maderaAlmacen, piedraAlmacen, hierroAlmacen, comidaAlmacen);
    }

    public Edificio(int id, String nombre, boolean destruible, int nivel, int costeOro, int costeMadera, int costePiedra, int costehierro, int necesitaTrabajadoresXmin, int produceMaderaXmin, int producePiedraXmin, int produceHierroXmin, int produceComidaXmin, int costeOroXmin, int produceFelicidadXmin, int produceInvestigacionXmin, int maderaAlmacen, int piedraAlmacen, int hierroAlmacen, int comidaAlmacen) {
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
        new CallImages(RUTEIMAGES, image);
        new CallImages(RUTEIMAGES, imageClicable);
        listaEdificios.add(this);
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
    public Image getImage() {
        return CallImages.getImage(RUTEIMAGES, image);

    }

    @Override
    public Image getImageClicable() {
        return CallImages.getImage(RUTEIMAGES, imageClicable);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edificio)) return false;
        Edificio edificio = (Edificio) o;
        return getId() == edificio.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


    private String getPathNameImage() {
        return getId() + "_" + nivel;
    }

    private String getPathNameImageClicable() {
        return getId() + "_" + nivel + "@clic";
    }
}


interface ImageGetter {
    abstract public Image getImage();

    abstract public Image getImageClicable();
}

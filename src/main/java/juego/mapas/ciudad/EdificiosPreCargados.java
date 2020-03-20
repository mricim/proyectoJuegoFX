package main.java.juego.mapas.ciudad;

import javafx.scene.image.Image;
import main.java.utils.ImageGetter;
import main.java.juego.mapas.Recursos;

import java.util.Objects;
import java.util.TreeMap;

import static main.java.jugadores.Jugador.listaEdificiosKeys;
import static main.java.jugadores.Jugador.listaEdificiosPreCargados;

//https://github.com/k33ptoo/JavaFX-MySQL-Login
public class EdificiosPreCargados implements ImageGetter {
    private int id;
    private int tipo;
    private String nombre;
    private String descripcion;
    private boolean destruible;
    private boolean construible;
    private int maximoEdificiosDelMismoTipo;
    //Mejorables
    private int nivel;
    private int nivelCastilloNecesario;
    public TreeMap<Integer, Recursos> recursosBuild = new TreeMap<>();
    //Productores
    public TreeMap<Integer, Recursos> recursosProductores = new TreeMap<>();
    //ALMACEN
    public TreeMap<Integer, Recursos> recursosAlmacen = new TreeMap<>();

    static String RUTEIMAGES = "mapas/city/";
    private String imagePath;
    private String imageClicablePath;


    public EdificiosPreCargados(int id, boolean destruible, boolean construible, int maximoEdificiosDelMismoTipo, int nivel, int nivelCastilloNecesario, int menuEspecial, String nombre, String descripcion, int costeOro, int costeMadera, int costePiedra, int costehierro, int TrabajadoresXminMaximos, int produceMaderaXmin, int producePiedraXmin, int produceHierroXmin, int produceComidaXmin, int costeOroXmin, int produceFelicidadXmin, int produceInvestigacionXmin, int maderaAlmacen, int piedraAlmacen, int hierroAlmacen, int comidaAlmacen, int poblacionAlmacen) {
        this.id = id;
        this.tipo = menuEspecial;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.destruible = destruible;
        this.construible = construible;
        this.maximoEdificiosDelMismoTipo = maximoEdificiosDelMismoTipo;
        //MEJORABLES
        this.nivel = nivel;
        this.nivelCastilloNecesario = nivelCastilloNecesario;
        recursosBuild.put(0, new Recursos(0, costeOro));
        recursosBuild.put(1, new Recursos(1, costeMadera));
        recursosBuild.put(2, new Recursos(2, costePiedra));
        recursosBuild.put(3, new Recursos(3, costehierro));
        recursosBuild.put(5, new Recursos(5, TrabajadoresXminMaximos));
        //PRODUCTORES
        if (costeOroXmin > 0) {
            recursosProductores.put(0, new Recursos(0, costeOroXmin));
        }
        if (produceMaderaXmin > 0) {
            recursosProductores.put(1, new Recursos(1, produceMaderaXmin));
        }
        if (producePiedraXmin > 0) {
            recursosProductores.put(2, new Recursos(2, producePiedraXmin));
        }
        if (produceHierroXmin > 0) {
            recursosProductores.put(3, new Recursos(3, produceHierroXmin));
        }
        if (produceComidaXmin > 0) {
            recursosProductores.put(4, new Recursos(4, produceComidaXmin));
        }
        if (produceFelicidadXmin > 0) {
            recursosProductores.put(6, new Recursos(6, produceFelicidadXmin));
        }
        if (produceInvestigacionXmin > 0) {
            recursosProductores.put(7, new Recursos(7, produceInvestigacionXmin));
        }
        //ALMACEN
        recursosAlmacen.put(1, new Recursos(1, maderaAlmacen));
        recursosAlmacen.put(2, new Recursos(2, piedraAlmacen));
        recursosAlmacen.put(3, new Recursos(3, hierroAlmacen));
        recursosAlmacen.put(4, new Recursos(4, comidaAlmacen));
        recursosAlmacen.put(7, new Recursos(7, poblacionAlmacen));


        String resultado = id + "-" + nivel;
        this.imagePath = resultado;
        this.imageClicablePath = resultado + "@h";

        listaEdificiosPreCargados.put(resultado, this);
        listaEdificiosKeys.add(id);
    }

    public int getId() {
        return id;
    }

    public int getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
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

    public int getNivel() {
        return nivel;
    }

    public int getNivelCastilloNecesario() {
        return nivelCastilloNecesario;
    }

    public TreeMap<Integer, Recursos> getRecursosBuild() {
        return recursosBuild;
    }

    /*
        public int getCosteOro() {
            return recursosCostes.get(0).getCantidad();
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
    */

    public TreeMap<Integer, Recursos> getRecursosProductores() {
        return recursosProductores;
    }

    /*
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
    */
    public TreeMap<Integer, Recursos> getRecursosAlmacen() {
        return recursosAlmacen;
    }

    /*
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
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EdificiosPreCargados)) return false;
        EdificiosPreCargados that = (EdificiosPreCargados) o;
        return getId() == that.getId() &&
                getNivel() == that.getNivel();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNivel());
    }

    @Override
    public String toString() {
        return "EdificiosPreCargados{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nivel=" + nivel +
                '}';
    }

    @Override
    public Image getImage() {
        return getImage(RUTEIMAGES, imagePath);
        //return CallImages.getImage(RUTEIMAGES, imagePath);
    }

    @Override
    public Image getImageClicable() {
        return getImageClicable(RUTEIMAGES, imageClicablePath);
        //return CallImages.getImage(RUTEIMAGES, imageClicablePath);
    }

    @Override
    public Image getImageIcon() {
        return null;
    }
}

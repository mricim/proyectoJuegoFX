package main.java.juego.mapas.ciudad;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.java.juego.mapas.RecursosPrecargados;
import main.java.juego.mapas.ciudad.contenidoCiudad.Edificio;
import main.java.jugadores.Jugador;
import main.java.utils.ImageGetter;
import main.java.juego.mapas.Recursos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import static main.java.Inicio.PantallaInicialController.elTemaSeleccionado;

//https://github.com/k33ptoo/JavaFX-MySQL-Login

/**
 * Representa los datos utilizados en la clase {@link main.java.juego.mapas.ciudad.contenidoCiudad.Edificio}.
 * @author Eric Casanova y Alejandro Riera
 * @version 1.0
 * @since 1.0
 */
public class EdificiosPreCargados implements ImageGetter {
    private Integer idDb;
    private final int id;
    private final int tipo;
    private final String nombre;
    private final String descripcion;
    private final boolean destruible;
    private final boolean construible;
    private final int maximoEdificiosDelMismoTipo;
    //Mejorables
    private final int nivel;
    private final int nivelCastilloNecesario;

    //RECURSOS - CONSTRUIR
    private Map<Integer, Recursos> recursosBuild = null;
    //RECURSOS - Productores
    private Map<Integer, Recursos> recursosProductores = null;
    //RECURSOS - COSTE X MIN
    private Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXmin = null;
    //RECURSOS -ALMACEN
    private Map<Integer, Recursos> recursosAlmacen = null;

    private static final String RUTEIMAGES = "mapas/city/";
    private final String imagePath;
    private final String imageClicablePath;


    /**
     * Datos utilizados en la clase {@link main.java.juego.mapas.ciudad.contenidoCiudad.Edificio} para la creación de los mismos.
     * @param id Un integer que contiene el valor del id del {@code Edificio}.
     * @param nivel Un integer que contiene el valor del nivel del {@code Edificio}.
     * @param destruible Un {@code boolean} que indica si el {@code Edificio} se puede destruir o no.
     * @param construible Un {@code boolean} que indica si el {@code Edificio} se puede construir o no.
     * @param maximoEdificiosDelMismoTipo Un integer que indica el número máximo de edificios de un mismo tipo.
     * @param nivelCastilloNecesario Un integer que indica el nivel necesario del castillo para construir este {@code Edificio}.
     * @param menuEspecial Un integer que indica si el {@code Edificio} que menu tiene que tener.
     * @param nombre Un String que contiene el nombre del {@code Edificio};
     * @param descripcion Un String que contiene la descripción del {@code Edificio};
     * @param recursosBuild Un TreeMap que contiene los valores del coste en recursos  de construcción del edificio, que se mostrarán en {@link CiudadController} en la cajaEdificio.
     * @param recursosProductores Un TreeMap que contiene los valores del coste en recursos de producción del edificio, que se mostrarán en {@link CiudadController} en la cajaEdificio.
     * @param recursosCosteXmin Un TreeMap que contiene los valores del coste en recursos por minuto del edificio, que se mostrarán en {@link CiudadController} en la cajaEdificio.
     * @param recursosAlmacen Un TreeMap que contiene los valores de los recursos almacenados en el edificio, que se mostrarán en {@link CiudadController} en la cajaEdificio.
     */
    public EdificiosPreCargados(int id, int nivel, boolean destruible, boolean construible, int maximoEdificiosDelMismoTipo, int nivelCastilloNecesario, int menuEspecial, String nombre, String descripcion, Map<Integer, Recursos> recursosBuild, Map<Integer, Recursos> recursosProductores, Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXmin, Map<Integer, Recursos> recursosAlmacen) {
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
        //RECURSOS - CONSTRUIR
        if (recursosBuild != null) {
            this.recursosBuild = recursosBuild;
        }
        //RECURSOS -Productores
        if (recursosProductores != null) {
            this.recursosProductores = recursosProductores;
        }
        //RECURSOS - COSTE X MIN
        if (recursosCosteXmin != null) {
            this.recursosCosteXmin = recursosCosteXmin;
        }
        //RECURSOS -ALMACEN
        if (recursosAlmacen != null) {
            this.recursosAlmacen = recursosAlmacen;
        }


        String resultado = id + "_" + nivel;
        this.imagePath = resultado;
        this.imageClicablePath = resultado + "@h";

        elTemaSeleccionado.listaEdificiosPreCargados.put(resultado, this);
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edificio_precargado_id", unique = true, nullable = true)
    public Integer getIdDb() {
        return idDb;
    }

    public void setIdDb(Integer idDb) {
        this.idDb = idDb;
    }
    /**
     * Devuelve el id del edificio.
     * @return Un integer que contiene el valor del id del edificio.
     */
    public int getId() {
        return id;
    }

    /**
     * Devuelve el tipo de edificio.
     * @return Un integer que contiene el valor del tipo  edificio.
     */
    public int getTipo() {
        return tipo;
    }

    //TODO continuar javadoc
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

    public Map<Integer, Recursos> getRecursosBuild() {
        return recursosBuild;
    }

    public Map<Integer, Recursos> getRecursosProductores() {
        return recursosProductores;
    }

    public Map<RecursosPrecargados, ArrayList<Recursos>> getRecursosCosteXmin() {
        return recursosCosteXmin;
    }

    public Map<Integer, Recursos> getRecursosAlmacen() {
        return recursosAlmacen;
    }

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

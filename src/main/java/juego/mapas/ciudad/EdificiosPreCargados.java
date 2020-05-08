package main.java.juego.mapas.ciudad;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.java.hibernate.DbOperations;
import main.java.juego.mapas.RecursosPrecargados;
import main.java.juego.mapas.ciudad.contenidoCiudad.Edificio;
import main.java.jugadores.Jugador;
import main.java.utils.ImageGetter;
import main.java.juego.mapas.Recursos;

import javax.persistence.*;
import java.io.Serializable;
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
@Entity
@Table(name = "EdificioPrecargado", schema = "proyecto")
public class EdificiosPreCargados implements ImageGetter, Serializable {
    private Integer idDb;
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

    //RECURSOS - CONSTRUIR
    private Map<Integer, Recursos> recursosBuild = null;
    //RECURSOS - Productores
    private Map<Integer, Recursos> recursosProductores = null;
    //RECURSOS - COSTE X MIN
    private Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXmin = null;
    //RECURSOS -ALMACEN
    private Map<Integer, Recursos> recursosAlmacen = null;

    private static String RUTEIMAGES = "mapas/city/";
    private String imagePath;
    private String imageClicablePath;


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

//        DbOperations.createRecord(this);

        String resultado = id + "_" + nivel;
        this.imagePath = resultado;
        this.imageClicablePath = resultado + "@h";

        elTemaSeleccionado.listaEdificiosPreCargados.put(resultado, this);
        DbOperations.updateRecord(elTemaSeleccionado);
    }

    public EdificiosPreCargados() {
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
    @Basic
    @Column(name = "idEdificioPrecargado",nullable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDestruible(boolean destruible) {
        this.destruible = destruible;
    }

    public void setConstruible(boolean construible) {
        this.construible = construible;
    }

    public void setMaximoEdificiosDelMismoTipo(int maximoEdificiosDelMismoTipo) {
        this.maximoEdificiosDelMismoTipo = maximoEdificiosDelMismoTipo;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setNivelCastilloNecesario(int nivelCastilloNecesario) {
        this.nivelCastilloNecesario = nivelCastilloNecesario;
    }

    public void setRecursosBuild(Map<Integer, Recursos> recursosBuild) {
        this.recursosBuild = recursosBuild;
    }

    public void setRecursosProductores(Map<Integer, Recursos> recursosProductores) {
        this.recursosProductores = recursosProductores;
    }

    public void setRecursosCosteXmin(Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXmin) {
        this.recursosCosteXmin = recursosCosteXmin;
    }

    public void setRecursosAlmacen(Map<Integer, Recursos> recursosAlmacen) {
        this.recursosAlmacen = recursosAlmacen;
    }

    /**
     * Devuelve el tipo de edificio.
     * @return Un integer que contiene el valor del tipo  edificio.
     */
    @Basic
    @Column(name = "tipo",nullable = true)
    public int getTipo() {
        return tipo;
    }

    //TODO continuar javadoc
    @Basic
    @Column(name = "nombre",nullable = true)
    public String getNombre() {
        return nombre;
    }

    @Basic
    @Column(name = "descripcion",nullable = true)
    public String getDescripcion() {
        return descripcion;
    }

    @Basic
    @Column(name = "sePuedeDestruir",nullable = true)
    public boolean isDestruible() {
        return destruible;
    }

    @Basic
    @Column(name = "sePuedeConstruir",nullable = true)
    public boolean isConstruible() {
        return construible;
    }

    @Basic
    @Column(name = "maximoEdificios",nullable = true)
    public int getMaximoEdificiosDelMismoTipo() {
        return maximoEdificiosDelMismoTipo;
    }

    @Basic
    @Column(name = "nivelEdificio",nullable = true)
    public int getNivel() {
        return nivel;
    }

    @Basic
    @Column(name = "nivelCastillo",nullable = true)
    public int getNivelCastilloNecesario() {
        return nivelCastilloNecesario;
    }

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Recursos.class)
    @JoinColumn(name="recursos_build_fk")
    public Map<Integer, Recursos> getRecursosBuild() {
        return recursosBuild;
    }

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Recursos.class)
    @JoinColumn(name="recursos_productores_fk")
    public Map<Integer, Recursos> getRecursosProductores() {
        return recursosProductores;
    }

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Recursos.class)
    @JoinColumn(name="coste_recursos_fk")
    public Map<RecursosPrecargados, ArrayList<Recursos>> getRecursosCosteXmin() {
        return recursosCosteXmin;
    }

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Recursos.class)
    @JoinColumn(name="recursos_almacen_fk")
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

    @Transient
    @Override
    public Image getImage() {
        return getImage(RUTEIMAGES, imagePath);
        //return CallImages.getImage(RUTEIMAGES, imagePath);
    }

    @Transient
    @Override
    public Image getImageClicable() {
        return getImageClicable(RUTEIMAGES, imageClicablePath);
        //return CallImages.getImage(RUTEIMAGES, imageClicablePath);
    }

    @Transient
    @Override
    public Image getImageIcon() {
        return null;
    }

    public static void setRUTEIMAGES(String RUTEIMAGES) {
        EdificiosPreCargados.RUTEIMAGES = RUTEIMAGES;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setImageClicablePath(String imageClicablePath) {
        this.imageClicablePath = imageClicablePath;
    }
}

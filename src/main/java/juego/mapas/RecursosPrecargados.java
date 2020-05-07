package main.java.juego.mapas;

import javafx.scene.image.Image;
import main.java.hibernate.DbOperations;
import main.java.utils.ImageGetter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "RecursosPrecargados", schema = "proyecto")
public class RecursosPrecargados implements ImageGetter, Comparable<RecursosPrecargados>, Serializable {
    static String RUTEIMAGES = "icons/recursos/";
    public static Map<Integer,RecursosPrecargados> recursosPrecargadosList=new HashMap<>();
    //oro=0;
    //madera=1;
    //piedra=2;
    //comida=3;
    //hierro=4;
    //poblacion=5;
    //felicidad=6;
    //investigacion=7
    private Integer idDB;
    private int id;
    private String name;

    private boolean menuCiudad;//?
    private boolean menuMundo;//?
    private boolean menupelea;//?

    private boolean seConsumeEnEdificios;//?

    public RecursosPrecargados() {
    }

    public RecursosPrecargados(int id, String name, boolean menuCiudad, boolean menuMundo, boolean menupelea, boolean seConsumeEnEdificios) {
        this.id = id;
        this.name = name;
        this.menuCiudad=menuCiudad;
        this.menuMundo=menuMundo;
        this.menupelea=menupelea;
        this.seConsumeEnEdificios = seConsumeEnEdificios;
        getImage();
        recursosPrecargadosList.put(id,this);
//        DbOperations.createRecord(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recurso_precargado_id", unique = true, nullable = false)
    public Integer getIdDB() {
        return idDB;
    }

    public void setIdDB(Integer idDB) {
        this.idDB = idDB;
    }

    @Basic
    @Column(name = "id_recurso_precargado",nullable = true)
    public Integer getId() {
        return id;
    }

    public static void setRUTEIMAGES(String RUTEIMAGES) {
        RecursosPrecargados.RUTEIMAGES = RUTEIMAGES;
    }

    public static void setRecursosPrecargadosList(Map<Integer, RecursosPrecargados> recursosPrecargadosList) {
        RecursosPrecargados.recursosPrecargadosList = recursosPrecargadosList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMenuCiudad(boolean menuCiudad) {
        this.menuCiudad = menuCiudad;
    }

    public void setMenuMundo(boolean menuMundo) {
        this.menuMundo = menuMundo;
    }

    public void setMenupelea(boolean menupelea) {
        this.menupelea = menupelea;
    }

    public void setSeConsumeEnEdificios(boolean seConsumeEnEdificios) {
        this.seConsumeEnEdificios = seConsumeEnEdificios;
    }

    @Basic
    @Column(name = "nombre",nullable = true)
    public String getName() {
        return name;
    }

    @Basic
    @Column(name = "esMenuCiudad",nullable = true)
    public boolean isMenuCiudad() {
        return menuCiudad;
    }

    @Basic
    @Column(name = "esMenuMundo",nullable = true)
    public boolean isMenuMundo() {
        return menuMundo;
    }

    @Basic
    @Column(name = "esMenuPelea",nullable = true)
    public boolean isMenupelea() {
        return menupelea;
    }

    @Basic
    @Column(name = "esConsumeEnEdificios",nullable = true)
    public boolean isSeConsumeEnEdificios() {
        return seConsumeEnEdificios;
    }



    @Transient
    @Override
    public Image getImage() {
        return getImage(RUTEIMAGES, String.valueOf(id));
    }

    @Transient
    @Override
    public Image getImageClicable() {
        System.err.println("getImageClicable() en Recursos no funciona!");
        return null;
    }

    @Transient
    @Override
    public Image getImageIcon() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecursosPrecargados)) return false;
        RecursosPrecargados that = (RecursosPrecargados) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public int compareTo(RecursosPrecargados o) {
        return this.getId().compareTo( o.getId() );
    }

    @Override
    public String toString() {
        return "RecursosPrecargados{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}

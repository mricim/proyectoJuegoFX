package main.java.juego.mapas;

import javafx.scene.image.Image;
import main.java.utils.ImageGetter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RecursosPrecargados implements ImageGetter, Comparable<RecursosPrecargados>{
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

    public RecursosPrecargados(int id, String name,boolean menuCiudad,boolean menuMundo,boolean menupelea,boolean seConsumeEnEdificios) {
        this.id = id;
        this.name = name;
        this.menuCiudad=menuCiudad;
        this.menuMundo=menuMundo;
        this.menupelea=menupelea;
        this.seConsumeEnEdificios = seConsumeEnEdificios;
        getImage();
        recursosPrecargadosList.put(id,this);
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recurso_precargado_id", unique = true, nullable = true)
    public Integer getIdDB() {
        return idDB;
    }

    public void setIdDB(Integer idDB) {
        this.idDB = idDB;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isMenuCiudad() {
        return menuCiudad;
    }

    public boolean isMenuMundo() {
        return menuMundo;
    }

    public boolean isMenupelea() {
        return menupelea;
    }

    public boolean isSeConsumeEnEdificios() {
        return seConsumeEnEdificios;
    }



    @Override
    public Image getImage() {
        return getImage(RUTEIMAGES, String.valueOf(id));
    }

    @Override
    public Image getImageClicable() {
        System.err.println("getImageClicable() en Recursos no funciona!");
        return null;
    }

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

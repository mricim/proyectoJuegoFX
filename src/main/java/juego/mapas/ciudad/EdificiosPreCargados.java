package main.java.juego.mapas.ciudad;

import javafx.scene.image.Image;
import main.java.juego.mapas.RecursosPrecargados;
import main.java.utils.ImageGetter;
import main.java.juego.mapas.Recursos;

import java.util.ArrayList;
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

    //RECURSOS - CONSTRUIR
    public ArrayList<Recursos> recursosBuild = null;
    //RECURSOS - Productores
    public ArrayList<Recursos> recursosProductores = null;
    //RECURSOS - COSTE X MIN
    public TreeMap<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXmin = null;
    //RECURSOS -ALMACEN
    public ArrayList<Recursos> recursosAlmacen = null;

    static String RUTEIMAGES = "mapas/city/";
    private String imagePath;
    private String imageClicablePath;


    public EdificiosPreCargados(int id, int nivel, boolean destruible, boolean construible, int maximoEdificiosDelMismoTipo, int nivelCastilloNecesario, int menuEspecial, String nombre, String descripcion, ArrayList<Recursos> recursosBuild, ArrayList<Recursos> recursosProductores, TreeMap<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXmin, ArrayList<Recursos> recursosAlmacen) {
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

    public ArrayList<Recursos> getRecursosBuild() {
        return recursosBuild;
    }

    public ArrayList<Recursos> getRecursosProductores() {
        return recursosProductores;
    }

    public TreeMap<RecursosPrecargados, ArrayList<Recursos>> getRecursosCosteXmin() {
        return recursosCosteXmin;
    }

    public ArrayList<Recursos> getRecursosAlmacen() {
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

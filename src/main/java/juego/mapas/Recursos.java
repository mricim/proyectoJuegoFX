package main.java.juego.mapas;

import javafx.scene.image.Image;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

public class Recursos {
    //oro=0;
    //madera=1;
    //piedra=2;
    //comida=3;
    //hierro=4;
    //poblacion=5;
    //felicidad=6;
    //investigacion=7
    private Integer idDB;
    private RecursosPrecargados recursosPrecargados;
    private int cantidad;

    public Recursos(int idRecursosPrecargados, int cantidad) {
        this(RecursosPrecargados.recursosPrecargadosList.get(idRecursosPrecargados), cantidad);
    }

    public Recursos(RecursosPrecargados recursosPrecargados, int cantidad) {
        this.recursosPrecargados = recursosPrecargados;
        this.cantidad = cantidad;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recurso_id", unique = true, nullable = true)
    public Integer getIdDB() {
        return idDB;
    }

    public void setIdDB(Integer idDB) {
        this.idDB = idDB;
    }
    public RecursosPrecargados getRecursosPrecargados() {
        return recursosPrecargados;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void addCantidad(int cantidad) {
        this.cantidad += cantidad;
    }

    public void removeCantidad(int cantidad) {
        this.cantidad -= cantidad;
    }

    public String getName() {
        return recursosPrecargados.getName();
    }

    public Image getImage() {
        return recursosPrecargados.getImage();
    }

    public int getId() {
        return recursosPrecargados.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recursos)) return false;
        Recursos recursos = (Recursos) o;
        return getRecursosPrecargados().equals(recursos.getRecursosPrecargados());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRecursosPrecargados());
    }

    @Override
    public String toString() {
        return "Recurso{" + "id="+getRecursosPrecargados().getId()+", cantidad=" + cantidad + '}';
    }
}

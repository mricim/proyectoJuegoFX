package main.java.juego.mapas;

import javafx.scene.image.Image;
import main.java.hibernate.DbOperations;
import main.java.juego.mapas.ciudad.EdificiosPreCargados;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
@Entity
@Table(name = "Recursos", schema = "proyecto")
public class Recursos implements Serializable {
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

    public Recursos() {
    }

    public Recursos(int idRecursosPrecargados, int cantidad) {
        this(RecursosPrecargados.recursosPrecargadosList.get(idRecursosPrecargados), cantidad);
        DbOperations.createRecord(this);
    }

    public Recursos(RecursosPrecargados recursosPrecargados, int cantidad) {
        this.recursosPrecargados = recursosPrecargados;
        this.cantidad = cantidad;
        DbOperations.createRecord(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recurso_id", unique = true, nullable = false)
    public Integer getIdDB() {
        return idDB;
    }

    public void setIdDB(Integer idDB) {
        this.idDB = idDB;
    }

    @ManyToOne(cascade = CascadeType.ALL,targetEntity = RecursosPrecargados.class)
    @JoinColumn(name="recurso_precargado_fk")
    public RecursosPrecargados getRecursosPrecargados() {
        return recursosPrecargados;
    }

    @Basic
    @Column(name = "cantidad",nullable = false)
    public int getCantidad() {
        return cantidad;
    }

    public void addCantidad(int cantidad) {
        this.cantidad += cantidad;
    }

    public void removeCantidad(int cantidad) {
        this.cantidad -= cantidad;
    }

    @Basic
    @Column(name = "nombre",nullable = false)
    public String getName() {
        return recursosPrecargados.getName();
    }

    public void  setName(String name){
        recursosPrecargados.setName(name);
    }


    @Transient
    public Image getImage() {
        return recursosPrecargados.getImage();
    }

    @Basic
    @Column(name = "idRecursoPrecargado",nullable = false)
    public int getId() {
        return recursosPrecargados.getId();
    }

    public void setId(int id){
        recursosPrecargados.setId(id);
    }

    public void setRecursosPrecargados(RecursosPrecargados recursosPrecargados) {
        this.recursosPrecargados = recursosPrecargados;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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

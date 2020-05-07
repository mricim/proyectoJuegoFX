package main.java.juego.mapas.pelea;

import javafx.scene.image.Image;
import main.java.hibernate.DbOperations;
import main.java.juego.mapas.ciudad.EdificiosPreCargados;
import main.java.juego.mapas.ciudad.contenidoCiudad.Edificio;
import main.java.utils.Posicion;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Unidades", schema = "proyecto")
public class Unidades extends Posicion implements Cloneable, Serializable {
    private Integer idDB;
    private int cantidad;
    private UnidadesPreCargadas unidadesPreCargadas;
    int municion;


    public Unidades() {
    }

    public Unidades(UnidadesPreCargadas unidadesPreCargadas, int cantidad, int municion, int filas, int columnas) {
        super(filas, columnas);
        this.unidadesPreCargadas = unidadesPreCargadas;
        this.cantidad = cantidad;
        this.municion = municion;
        DbOperations.createRecord(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unidades_id", unique = true, nullable = false)
    public Integer getId() {
        return idDB;
    }

    @Transient
    public int getTipeUnit() {
        return unidadesPreCargadas.getIdType();
    }


    @Basic
    @Column(name = "cantidad",nullable = false)
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void addCantidad(int cantidad) {
        int conversorAPositivo = (cantidad < 0 ? -cantidad : cantidad);
        this.cantidad += conversorAPositivo;
    }

    public void removeCantidad(int cantidad) {
        int conversorAPositivo = (cantidad < 0 ? -cantidad : cantidad);
        this.cantidad -= conversorAPositivo;
    }

    public void setId(Integer idDB) {
        this.idDB = idDB;
    }

    public void setUnidadesPreCargadas(UnidadesPreCargadas unidadesPreCargadas) {
        this.unidadesPreCargadas = unidadesPreCargadas;
    }

    public void setMunicion(int municion) {
        this.municion = municion;
    }

    @ManyToOne(cascade = CascadeType.ALL,targetEntity = UnidadesPreCargadas.class)
    @JoinColumn(name="unidad_precargado_fk")
    public UnidadesPreCargadas getUnidadesPreCargadas() {
        return unidadesPreCargadas;
    }

    @Transient
    public Image getImage() {
        return unidadesPreCargadas.getImage();
    }

    @Transient
    public Image getImageClicable() {
        return unidadesPreCargadas.getImageClicable();
    }

    @Transient
    public Image getImageIcon() {
        return unidadesPreCargadas.getImageIcon();
    }

    public Unidades clone() {
        try {
            return (Unidades) super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unidades)) return false;
        Unidades un = (Unidades) o;
        return getUnidadesPreCargadas().equals(un.getUnidadesPreCargadas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUnidadesPreCargadas());
    }


}

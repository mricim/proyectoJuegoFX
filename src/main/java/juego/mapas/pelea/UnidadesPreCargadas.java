package main.java.juego.mapas.pelea;


import javafx.scene.image.Image;
import main.java.hibernate.DbOperations;
import main.java.utils.ImageGetter;
import main.java.juego.mapas.Recursos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import static main.java.Inicio.PantallaInicialController.elTemaSeleccionado;

@Entity
@Table(name = "UnidadesPrecargado", schema = "proyecto")
public class UnidadesPreCargadas implements ImageGetter , Serializable {

    private Integer idDB;
    private int idType;
    private String nombre;
    private int maxMunicion;

    static String RUTEIMAGES = "mapas/pelea/unidades/";
    static String RUTEICON = "icons/soldados/";
    private String imagePath;
    private List<Recursos> costes;
    private String imageClicablePath;
    private String imageIconPath;
    private int tipoLucha;

    public UnidadesPreCargadas(int idType, String nombre, int maxMunicion, List<Recursos>costes, int tipoLucha) {
        this.idType = idType;
        this.nombre = nombre;
        this.maxMunicion=maxMunicion;
        this.costes=costes;
        String imagePath= String.valueOf(idType);
        this.imagePath =imagePath;
        this.imageClicablePath = imagePath + "@H";
        this.imageIconPath = imagePath;
        this.tipoLucha = tipoLucha;
//        DbOperations.createRecord(this);
        elTemaSeleccionado.listaSoldadosPreCargada.put(idType,this);
        DbOperations.updateRecord(elTemaSeleccionado);
    }

    public UnidadesPreCargadas() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unidades_precargadas_id", unique = true, nullable = true)
    public Integer getIdDB() {
        return idDB;
    }

    public void setIdDB(Integer idDB) {
        this.idDB = idDB;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMaxMunicion(int maxMunicion) {
        this.maxMunicion = maxMunicion;
    }

    public void setCostes(List<Recursos> costes) {
        this.costes = costes;
    }

    public void setTipoLucha(int tipoLucha) {
        this.tipoLucha = tipoLucha;
    }

    @Basic
    @Column(name = "id_tipo",unique = true,nullable = false)
    public int getIdType() {
        return idType;
    }

    @Basic
    @Column(name = "nombre",nullable = true)
    public String getNombre() {
        return nombre;
    }

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Recursos.class)
    @JoinColumn(name="coste_unidades_fk")
    public List<Recursos> getCostes() {
        return costes;
    }

    @Basic
    @Column(name = "tipo_lucha",nullable = true)
    public int getTipoLucha() {
        return tipoLucha;
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
        return getImageClicable(RUTEICON, imageIconPath);
        //return CallImages.getImage(RUTEIMAGES, imageClicablePath);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnidadesPreCargadas that = (UnidadesPreCargadas) o;
        return idType == that.idType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idType);
    }
}

package main.java.juego.mapas.ciudad;

import main.java.hibernate.DbOperations;
import main.java.juego.mapas.RecursosPrecargados;
import main.java.utils.Posicion;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.ciudad.contenidoCiudad.Edificio;
import main.java.juego.mapas.pelea.*;
import main.java.jugadores.Clan;
import main.java.jugadores.Jugador;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import javax.annotation.Nonnull;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import static main.java.juego.mapas.RecursosPrecargados.recursosPrecargadosList;
import static main.java.jugadores.Jugador.*;

@Entity
@Table(name = "Ciudad", schema = "proyecto")
public class Ciudad extends Posicion implements Serializable {
    private Map<String, Edificio> listaPosicionesEdificios = new TreeMap<>();
    private Integer idCiudad;
    private String nameCity;
    private Map<Integer, Recursos> recursosTreeMap = new TreeMap<>();//TODO COMPROBAR QUE NO PUEDE PASAR DEL LIMITE DE CAPACIDAD DE LA CIUDAD
    private int nivelCiudad;
    private Map<Integer, Unidades> listSoldadosCity = new TreeMap<>();

    public Ciudad(Jugador jugador,  String nameCity, int fila, int columna, int nivelCiudad, ArrayList<Recursos> recursosDeLaCity) {
        super(fila, columna);
        this.nameCity = nameCity;
        this.nivelCiudad = nivelCiudad;




        for (Map.Entry<Integer, RecursosPrecargados> integerRecursosPrecargadosEntry : recursosPrecargadosList.entrySet()) {
            RecursosPrecargados a = integerRecursosPrecargadosEntry.getValue();
            if (a.isMenuCiudad()) {
                int i = integerRecursosPrecargadosEntry.getKey();
                try {
                    Recursos recursos = recursosDeLaCity.get(i);
                    recursosTreeMap.put(i, recursos);
                }catch (Exception e){
                    recursosTreeMap.put(i, new Recursos(i, 0));
                }
            }
        }


        for (UnidadesPreCargadas soldaditos : listaSoldadosPreCargada.values()) {
            this.listSoldadosCity.put(soldaditos.getIdType(), new Unidades(soldaditos, 0, 0, 0, 0));
        }


        //todo Solicitar a la bd la tabla de posiciones y edificios que tiene el usuario
        try {
            if (getIdCiudad() == 2) {
                new Edificio(listaEdificiosPreCargados.get(0 + "_" + 0), 8, 8, this);//parcela
                new Edificio(listaEdificiosPreCargados.get(2 + "_" + 1), 1, 12, this);
                new Edificio(listaEdificiosPreCargados.get(0 + "_" + 0), 6, 8, this);//parcela
                new Edificio(listaEdificiosPreCargados.get(1 + "_" + 0), 8, 15, this);
            } else if (getIdCiudad() == 1) {
                new Edificio(listaEdificiosPreCargados.get(1 + "_" + 1), 8, 7, this);
                new Edificio(listaEdificiosPreCargados.get(2 + "_" + 0), 1, 12, this);
            }
            new Edificio(listaEdificiosPreCargados.get(10 + "_" + 0), 10, 4, this);
            new Edificio(listaEdificiosPreCargados.get(11 + "_" + 0), 8, 12, this);
            new Edificio(listaEdificiosPreCargados.get(0 + "_" + 0), 5, 7, this);
            new Edificio(listaEdificiosPreCargados.get(10 + "_" + 0), 7, 13, this);//todos tendrian "parcela"
            new Edificio(listaEdificiosPreCargados.get(11 + "_" + 0), 12, 2, this);//todos tendrian "parcela"
            new Edificio(listaEdificiosPreCargados.get(12 + "_" + 1), 5, 18, this);//todos tendrian "parcela"

            new Edificio(listaEdificiosPreCargados.get(13 + "_" + 0), 7, 15, this);//todos tendrian "parcela"
            new Edificio(listaEdificiosPreCargados.get(14 + "_" + 0), 7, 16, this);//todos tendrian "parcela"
            new Edificio(listaEdificiosPreCargados.get(15 + "_" + 0), 7, 17, this);//todos tendrian "parcela"
            new Edificio(listaEdificiosPreCargados.get(16 + "_" + 0), 12, 17, this);//todos tendrian "parcela"
            new Edificio(listaEdificiosPreCargados.get(17 + "_" + 0), 13, 5, this);//todos tendrian "parcela"
            new Edificio(listaEdificiosPreCargados.get(18 + "_" + 0), 14, 10, this);//todos tendrian "parcela"
            new Edificio(listaEdificiosPreCargados.get(19 + "_" + 0), 3, 7, this);//todos tendrian "parcela"
            new Edificio(listaEdificiosPreCargados.get(20 + "_" + 0), 4, 8, this);//todos tendrian "parcela"
        } catch (Exception e) {
            System.err.println("Error: Ciudad (Edificio no creado)\n");
            e.printStackTrace();
        }
        jugador.listaCiudadesPropias.put(getPosition(), this);
        if (Clan.jugadoresQueEstanEnUnClan.containsKey(jugador)) {
            Clan.jugadoresQueEstanEnUnClan.get(jugador).addCiudades(this);
        }
        listaCiudades.put(getPosition(), this);
        DbOperations.createRecord(this);
    }

    public Ciudad() {
    }

    public Ciudad(String position) {
        super(position);
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ciudad_id", unique = false, nullable = false)
    public Integer getIdCiudad() {
        return idCiudad;
    }

    public void setListaPosicionesEdificios(Map<String, Edificio> listaPosicionesEdificios) {
        this.listaPosicionesEdificios = listaPosicionesEdificios;
    }

    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public void setListSoldadosCity(Map<Integer, Unidades> listSoldadosCity) {
        this.listSoldadosCity = listSoldadosCity;
    }

    //TODO cambiar unique ciudad a true
    @Basic
    @Column(name = "nombreCiudad",unique = false,nullable = true)
    public String getNameCity() {
        return nameCity;
    }

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Edificio.class)
    @JoinColumn(name="posicion_edificio_fk")
    public Map<String, Edificio> getListaPosicionesEdificios() {
        return listaPosicionesEdificios;
    }

    public void addListaPosicionesEdificios(int colum, int row, Edificio posicionEdificio) {
        this.listaPosicionesEdificios.put(colum + "_" + row, posicionEdificio);
    }

    public void addListaPosicionesEdificios(String colum_row, Edificio posicionEdificio) {
        this.listaPosicionesEdificios.put(colum_row, posicionEdificio);
    }

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Recursos.class)
    @JoinColumn(name="recursos_fk")
    public Map<Integer, Recursos> getRecursosTreeMap() {
        return recursosTreeMap;
    }

    public void setRecursosTreeMap(Map<Integer, Recursos> recursosTreeMap) {
        this.recursosTreeMap = recursosTreeMap;
    }

    @Basic
    @Column(name = "nivelCiudad",nullable = true)
    public int getNivelCiudad() {
        return nivelCiudad;
    }

    public void setNivelCiudad(int nivelCiudad) {
        this.nivelCiudad = nivelCiudad;
    }

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Unidades.class)
    @JoinColumn(name="unidades_fk")
    public Map<Integer, Unidades> getListSoldadosCity() {
        return listSoldadosCity;
    }

    public void addSoldados(TreeMap<Integer, Unidades> soldados) {
        for (Unidades soldado : soldados.values()) {
            Unidades soldados2 = this.listSoldadosCity.get(soldado.getUnidadesPreCargadas().getIdType());
            System.out.println(soldados2.getCantidad() + soldado.getCantidad());
            soldados2.addCantidad(soldado.getCantidad());
        }
    }




//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Ciudad ciudad = (Ciudad) o;
//        return Objects.equals(nameCity, ciudad.nameCity);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(nameCity);
//    }


    @Override
    public String toString() {
        return "Ciudad{" +
                "idCiudad=" + idCiudad +
                ", nameCity='" + nameCity + '\'' +
                ", nivelCiudad=" + nivelCiudad +
                '}';
    }
}

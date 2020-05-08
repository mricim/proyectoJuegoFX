package main.java.juego.mapas.pelea;


import main.java.Inicio.PantallaInicialController;
import main.java.hibernate.DbOperations;
import main.java.juego.mapas.ciudad.Ciudad;
import main.java.juego.mapas.ciudad.contenidoCiudad.Edificio;
import main.java.jugadores.Clan;
import main.java.jugadores.Jugador;
import main.java.utils.Posicion;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.Character.UnicodeBlock;
import java.util.*;

import static main.java.Inicio.PantallaInicialController.elTemaSeleccionado;

@Entity
@Table(name = "Batallon", schema = "proyecto")
public class Batallon extends Posicion implements Serializable {
    private Map<Integer, Unidades> SoldadoHashMap = new HashMap<>();
    private Integer idBatallon;
    private String nombre;
    private Ciudad ciudadVolver;
    private int proyectiles;//TODO MAS QUE PROYECTILES SERIA COMO RACIONES O MUNICIONES O ALGO ASI


    public Batallon() {
    }

    public Batallon(String nombre, int filas, int columnas, int proyectiles, Jugador jugador, Ciudad ciudadVolver) {
        super(filas, columnas);
        this.nombre = nombre;
        this.proyectiles = proyectiles;
        this.ciudadVolver = ciudadVolver;
//        DbOperations.createRecord(this);
        jugador.listaBatallonesPropios.put(idBatallon, this);
        for (Clan clan : Clan.clanArrayList) {
            if (clan.getJugadoresDelClan().containsKey(jugador.getId())) {
                clan.addBatallon(this);
                break;
            }
        }

    }

    public Batallon(String position) {
        super(position);
    }

    public void setSoldadoHashMap(Map<Integer, Unidades> soldadoHashMap) {
        SoldadoHashMap = soldadoHashMap;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setProyectiles(int proyectiles) {
        this.proyectiles = proyectiles;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batallon_id", unique = true, nullable = false)
    public Integer getIdBatallon() {
        return idBatallon;
    }

    public  void setIdBatallon(int idBatallon) {
        this.idBatallon = idBatallon;
    }
    //TODO cambiar unique batallon a true
    @Basic
    @Column(name = "nombreBatallon",unique = true,nullable = true)
    public String getNombre() {
        return nombre;
    }


    @OneToMany(cascade = CascadeType.ALL,targetEntity = Unidades.class)
    @JoinColumn(name="unidades_batallon_fk")
    public Map<Integer, Unidades> getSoldadoHashMap() {
        return SoldadoHashMap;
    }

    public void setSoldadoHashMap(Unidades soldado) {
        SoldadoHashMap.put(soldado.getId(), soldado);
    }

    @OneToOne
    @JoinColumn(name = "ciudad_fk" , nullable = false)
    public Ciudad getCiudadVolver() {
        return ciudadVolver;
    }

    public void setCiudadVolver(Ciudad ciudadVolver) {
        this.ciudadVolver = ciudadVolver;
    }

    public void addSoldados(Map<Integer, Unidades> anadir) {

        Collection<Unidades> a = anadir.values();
        for (Unidades unidadAnadir : a) {//TODO
            int tipo = unidadAnadir.getTipeUnit();
            boolean seanadio = false;
            for (Unidades value : SoldadoHashMap.values()) {
                if (value.getTipeUnit() == tipo) {
                    value.addCantidad(unidadAnadir.getCantidad());
                    seanadio = true;
                }
            }
            if (!seanadio) {
                SoldadoHashMap.put(unidadAnadir.getId(), unidadAnadir);
            }
        }

    }

    @Override
    public void setFilaColumna(int fila, int columna) {
        try {
            PantallaInicialController.elTemaSeleccionado.listaPosicionesBatallones.get(getPosition()).remove(this);
        } catch (Exception ignored) {
        }
        super.setFilaColumna(fila, columna);
        ArrayList<Batallon> batallon2 = PantallaInicialController.elTemaSeleccionado.listaPosicionesBatallones.get(getPosition());
        if (batallon2 == null) {
            ArrayList<Batallon> x = new ArrayList<>();
            x.add(this);
            elTemaSeleccionado.listaPosicionesBatallones.put(getPosition(), x);
        } else {
            batallon2.add(this);
        }
    }

    public void remove(Jugador jugador) {
        try {
            PantallaInicialController.elTemaSeleccionado.listaPosicionesBatallones.get(getPosition()).remove(this);
        } catch (Exception ignored) {
        }
        jugador.listaBatallonesPropios.remove(getIdBatallon());

        for (Clan clan : Clan.clanArrayList) {
            if (clan.getJugadoresDelClan().containsKey(jugador.getId())) {
                clan.removeBatallon(this);
                break;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Batallon)) return false;
        if (!super.equals(o)) return false;
        Batallon batallon = (Batallon) o;
        return getIdBatallon().equals(batallon.getIdBatallon());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getIdBatallon());
    }
}

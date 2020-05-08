package main.java.jugadores;

import main.java.hibernate.DbOperations;
import main.java.juego.mapas.ciudad.Ciudad;
import main.java.juego.mapas.pelea.Batallon;
import main.java.juego.mapas.pelea.Unidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Clan", schema = "proyecto")
public class Clan implements Serializable {
    public static ArrayList<Clan> clanArrayList = new ArrayList<>();
    public static Map<Jugador,Clan> jugadoresQueEstanEnUnClan = new HashMap<>();

    private Integer id;
    private Map<Integer, Jugador> jugadoresDelClan = new HashMap<>();
    private Set<Ciudad> ciudadesDelClan = new HashSet<>();
    private Set<Batallon> batallonesDelClan = new HashSet<>();
    private String name;

    public Clan() {
    }

    public Clan(String name) {
        this.name = name;
        clanArrayList.add(this);
//        DbOperations.createRecord(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clan_id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Jugador.class)
    @JoinColumn(name="clan_fk")
    public Map<Integer, Jugador> getJugadoresDelClan() {
        return jugadoresDelClan;
    }

    //TODO cambiar unique a true
    @Basic
    @Column(name = "nombreClan",unique = false,nullable = true)
    public String getName() {
        return name;
    }

    @OneToMany(targetEntity = Ciudad.class)
    @JoinColumn(name="ciudades_clan_fk")
    public Set<Ciudad> getCiudadesDelClan() {
        return ciudadesDelClan;
    }
//
    @OneToMany(targetEntity = Batallon.class)
    @JoinColumn(name="batallones_clan_fk")
    public Set<Batallon> getBatallonesDelClan() {
        return batallonesDelClan;
    }

    public void addJugadorClan(int jugadorId) {
        addJugadorClan(Jugador.returnJugador(jugadorId));
    }

    public void addJugadorClan(Jugador jugador) {
        for (Ciudad ciudad : jugador.listaCiudadesPropias.values()) {
            addCiudades(ciudad);
        }
        for (Batallon batallon : jugador.listaBatallonesPropios.values()) {
            addBatallon(batallon);
        }
        jugadoresQueEstanEnUnClan.put(jugador,this);
        this.jugadoresDelClan.put(jugador.getId(), jugador);
    }

    public void removeJugadorClan(int jugadorId) {
        removeJugadorClan(Jugador.returnJugador(jugadorId));
    }

    public void removeJugadorClan(Jugador jugador) {
        for (Ciudad ciudad : jugador.listaCiudadesPropias.values()) {
            removeCiudad(ciudad);
        }
        for (Batallon batallon : jugador.listaBatallonesPropios.values()) {
            removeBatallon(batallon);
        }
        jugadoresQueEstanEnUnClan.remove(jugador);
        this.jugadoresDelClan.remove(jugador.getId());
    }

    public void addCiudades(Ciudad ciudad) {
        ciudadesDelClan.add(ciudad);
    }
    public void addBatallon(Batallon batallon) {
        batallonesDelClan.add(batallon);
    }
    public void removeCiudad(Ciudad ciudad) {
        ciudadesDelClan.add(ciudad);
    }
    public void removeBatallon(Batallon batallon) {
        batallonesDelClan.add(batallon);
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setJugadoresDelClan(Map<Integer, Jugador> jugadoresDelClan) {
        this.jugadoresDelClan = jugadoresDelClan;
    }

    public void setCiudadesDelClan(Set<Ciudad> ciudadesDelClan) {
        this.ciudadesDelClan = ciudadesDelClan;
    }

    public void setBatallonesDelClan(Set<Batallon> batallonesDelClan) {
        this.batallonesDelClan = batallonesDelClan;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Clan clan = (Clan) o;
//        return Objects.equals(name, clan.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name);
//    }
}

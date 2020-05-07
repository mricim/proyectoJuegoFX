package main.java.jugadores;

import main.java.juego.mapas.ciudad.Ciudad;
import main.java.juego.mapas.pelea.Batallon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Clan implements Serializable {
    public static ArrayList<Clan> clanArrayList = new ArrayList<>();
    public static Map<Jugador,Clan> jugadoresQueEstanEnUnClan = new HashMap<>();

    private int id;
    private Map<Integer, Jugador> jugadoresDelClan = new HashMap<>();
    private HashSet<Ciudad> ciudadesDelClan = new HashSet<>();
    private HashSet<Batallon> batallonesDelClan = new HashSet<>();
    private String name;

    public Clan(int id, String name) {
        this.id = id;
        this.name = name;
        clanArrayList.add(this);
    }


    public int getId() {
        return id;
    }

    public Map<Integer, Jugador> getJugadoresDelClan() {
        return jugadoresDelClan;
    }

    public String getName() {
        return name;
    }

    public HashSet<Ciudad> getCiudadesDelClan() {
        return ciudadesDelClan;
    }

    public HashSet<Batallon> getBatallonesDelClan() {
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


}

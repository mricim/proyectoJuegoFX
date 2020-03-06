package main.java.jugadores;

import main.java.juego.mapas.ciudad.Ciudad;
import main.java.juego.mapas.pelea.Batallon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Clan {
    public static ArrayList<Clan> clanArrayList = new ArrayList<>();
    public static HashMap<Jugador,Clan> jugadoresQueEstanEnUnClan = new HashMap<>();

    private int id;
    private HashMap<Integer, Jugador> jugadoresDelClan = new HashMap<>();
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

    public HashMap<Integer, Jugador> getJugadoresDelClan() {
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

    private void addCiudades(Ciudad ciudad) {
        ciudadesDelClan.add(ciudad);
    }
    private void addBatallon(Batallon batallon) {
        batallonesDelClan.add(batallon);
    }
    private void removeCiudad(Ciudad ciudad) {
        ciudadesDelClan.add(ciudad);
    }
    private void removeBatallon(Batallon batallon) {
        batallonesDelClan.add(batallon);
    }


}

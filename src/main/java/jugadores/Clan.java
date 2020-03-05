package main.java.jugadores;

import java.util.ArrayList;
import java.util.HashMap;

public class Clan {
    public static ArrayList<Clan> clanArrayList = new ArrayList<>();

    private int id;
    private HashMap<Integer, Jugador> jugadoresDelClan = new HashMap<>();
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

    public void addJugadorClan(int jugadorId) {
        addJugadorClan(Jugador.returnJugador(jugadorId));
    }

    public void addJugadorClan(Jugador jugador) {
        this.jugadoresDelClan.put(jugador.getId(), jugador);
    }

    public void removeJugadorClan(int jugadorId) {
        this.jugadoresDelClan.remove(jugadorId);
    }

    public void removeJugadorClan(Jugador jugador) {
        removeJugadorClan(jugador.getId());
    }


}

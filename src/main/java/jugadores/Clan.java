package main.java.jugadores;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.juego.mapas.ciudad.Ciudad;
import main.java.juego.mapas.pelea.Batallon;

import java.util.*;

public class Clan {
    public static int costeCrear = 100;
    public static int costeBaseUnirse = 10;
    public static int lastId = 1;
    public static ObservableList<Clan> clanArrayList = FXCollections.observableArrayList();
    public static Map<Jugador, Clan> jugadoresQueEstanEnUnClan = new HashMap<>();

    private final int id;
    private final Map<Long, Jugador> jugadoresDelClan = new HashMap<>();
    private final Map<String, Ciudad> ciudadesDelClan = new TreeMap<>();
    private final HashSet<Batallon> batallonesDelClan = new HashSet<>();
    private final SimpleStringProperty name;
    private Jugador creador;
    private SimpleStringProperty contrasenya = null;
    private final SimpleIntegerProperty coste;

    public Clan(String name, int coste) {
        this.id = lastId++;
        this.name = new SimpleStringProperty(name);
        this.coste = new SimpleIntegerProperty(coste);
        clanArrayList.add(this);
    }


    public int getId() {
        return id;
    }

    public Map<Long, Jugador> getJugadoresDelClan() {
        return jugadoresDelClan;
    }

    public SimpleStringProperty getName() {
        return name;
    }

    public String getNameString() {
        return name.getValue();
    }

    public Map<String, Ciudad> getCiudadesDelClan() {
        return ciudadesDelClan;
    }

    public HashSet<Batallon> getBatallonesDelClan() {
        return batallonesDelClan;
    }

    public Jugador getCreador() {
        return creador;
    }

    public void setCreador(Jugador creador) {
        this.creador = creador;
    }

    public int getCoste() {
        return coste.get();
    }

    public SimpleIntegerProperty costeProperty() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste.set(coste);
    }

    public String getContrasenya() {
        return contrasenya.get();
    }

    public SimpleStringProperty contrasenyaProperty() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = new SimpleStringProperty(contrasenya);
    }

    public void addJugadorClan(long jugadorId) {
        addJugadorClan(Jugador.returnJugador(jugadorId));
    }

    public void addJugadorClan(Jugador jugador) {
        for (Ciudad ciudad : jugador.listaCiudadesPropias.values()) {
            addCiudades(ciudad);
        }
        for (Batallon batallon : jugador.listaBatallonesPropios.values()) {
            addBatallon(batallon);
        }
        jugadoresQueEstanEnUnClan.put(jugador, this);
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
        ciudadesDelClan.put(ciudad.getPosition(), ciudad);
    }

    public void addBatallon(Batallon batallon) {
        batallonesDelClan.add(batallon);
    }

    public void removeCiudad(Ciudad ciudad) {
        ciudadesDelClan.remove(ciudad.getPosition());
    }

    public void removeCiudad(String posicionCiudad) {
        ciudadesDelClan.remove(posicionCiudad);
    }

    public void removeBatallon(Batallon batallon) {
        batallonesDelClan.remove(batallon);
    }


}

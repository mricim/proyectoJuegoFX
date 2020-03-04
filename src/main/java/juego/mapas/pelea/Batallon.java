package main.java.juego.mapas.pelea;


import main.java.jugadores.Jugador;
import main.java.Utils.Posicion;

import java.util.HashMap;

public class Batallon extends Posicion {
    private HashMap<String, Soldados> SoldadoHashMap = new HashMap<>();
    private int idBatallon;
    private String nombre;
    private int proyectiles;//TODO MAS QUE PROYECTILES SERIA COMO RACIONES O MUNICIONES O ALGO ASI
    private double poderMilitar;


    public Batallon(int idBatallon, String nombre, int filas, int columnas, int proyectiles, Jugador jugador) {
        super(filas, columnas);
        this.idBatallon = idBatallon;
        this.nombre = nombre;
        this.proyectiles = proyectiles;

        jugador.listaBatallonesPropios.put(this.getPosition(), this);
        jugador.listaBatallones.put(this.getPosition(), this);
    }

    public int getIdBatallon() {
        return idBatallon;
    }

    public synchronized void setIdBatallon(int idBatallon) {
        this.idBatallon = idBatallon;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPoderMilitar() {
        return poderMilitar;
    }
/*
    public void setPoderMilitar() {
        double poderMilitar = 0;
        for (PosicionSoldado p : posicionSoldadoHashMap.values()) {
            //TODO iterar sobre el map de posicion de soldados y calcular el podermilitar del batallon
            poderMilitar += p.getSoldado().getCantidad() * p.getSoldado().getSoldadosPreCargados().getId();
        }
        this.poderMilitar = poderMilitar;
    }
 */

    public HashMap<String, Soldados> getSoldadoHashMap() {
        return SoldadoHashMap;
    }

    public void setSoldadoHashMap(Soldados soldado) {
        SoldadoHashMap.put(soldado.getPosition(), soldado);
    }
}

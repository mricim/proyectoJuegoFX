package main.java.juego.mapas.pelea;


import main.java.jugadores.Jugador;
import main.java.utils.Posicion;

import java.util.ArrayList;
import java.util.HashMap;

public class Batallon extends Posicion {
    private static int lastIdBatallon=1;
    private HashMap<String, Unidades> SoldadoHashMap = new HashMap<>();
    private int idBatallon;
    private String nombre;
    private int proyectiles;//TODO MAS QUE PROYECTILES SERIA COMO RACIONES O MUNICIONES O ALGO ASI
    private double poderMilitar;


    public Batallon(String nombre, int filas, int columnas, int proyectiles, Jugador jugador) {
        super(filas, columnas);
        this.idBatallon = lastIdBatallon++;
        this.nombre = nombre;
        this.proyectiles = proyectiles;

        String posicion = this.getPosition();
        jugador.listaBatallonesPropios.put(posicion, this);
        //Jugador.listaBatallones.put(posicion, this);
        if (Jugador.listaPosicionesBatallones.containsKey(this.getPosition())) {
            Jugador.listaPosicionesBatallones.get(posicion).add(this);
        } else {
            ArrayList<Batallon> batallonArrayList = new ArrayList<>();
            batallonArrayList.add(this);
            Jugador.listaPosicionesBatallones.put(posicion, batallonArrayList);
        }
    }

    public Batallon(String position) {
        super(position);
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

    public HashMap<String, Unidades> getSoldadoHashMap() {
        return SoldadoHashMap;
    }

    public void setSoldadoHashMap(Unidades soldado) {
        SoldadoHashMap.put(soldado.getPosition(), soldado);
    }
}

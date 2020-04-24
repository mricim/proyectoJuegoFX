package main.java.juego.mapas.pelea;


import main.java.juego.mapas.ciudad.Ciudad;
import main.java.jugadores.Jugador;
import main.java.utils.Posicion;

import java.lang.Character.UnicodeBlock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

import static main.java.jugadores.Jugador.listaPosicionesBatallones;

public class Batallon extends Posicion {
    private static int lastIdBatallon = 1;
    private HashMap<Integer, Unidades> SoldadoHashMap = new HashMap<>();
    private int idBatallon;
    private String nombre;
    private Ciudad ciudadVolver;
    private int proyectiles;//TODO MAS QUE PROYECTILES SERIA COMO RACIONES O MUNICIONES O ALGO ASI
    private double poderMilitar;


    public Batallon(String nombre, int filas, int columnas, int proyectiles, Jugador jugador, Ciudad ciudadVolver) {
        super(filas, columnas);
        this.idBatallon = lastIdBatallon++;
        this.nombre = nombre;
        this.proyectiles = proyectiles;
        this.ciudadVolver = ciudadVolver;

        jugador.listaBatallonesPropios.put(idBatallon, this);
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

    public HashMap<Integer, Unidades> getSoldadoHashMap() {
        return SoldadoHashMap;
    }

    public void setSoldadoHashMap(Unidades soldado) {
        SoldadoHashMap.put(soldado.getId(), soldado);
    }

    public void addSoldados(HashMap<Integer, Unidades> anadir) {
        Set<Integer> a = anadir.keySet();
        for (Integer integer : a){//TODO
            System.out.println(a);
            Unidades b = anadir.get(integer);
            System.out.println(b.getId()+" "+b.getCantidad());
            System.out.println("FFFFFF");
            System.out.println(getSoldadoHashMap().get(b.getId()));
            getSoldadoHashMap().get(b.getId()).addCantidad(b.getCantidad());
        }

    }

    @Override
    public void setFilaColumna(int fila, int columna) {
        try {
            listaPosicionesBatallones.get(getPosition()).remove(this);
        } catch (Exception ignored) {
        }
        super.setFilaColumna(fila, columna);
        ArrayList<Batallon> batallon2 = listaPosicionesBatallones.get(getPosition());
        if (batallon2 == null) {
            ArrayList<Batallon> x = new ArrayList<>();
            x.add(this);
            listaPosicionesBatallones.put(getPosition(), x);
        } else {
            batallon2.add(this);
        }
    }

    public void remove(Jugador jugador) {
        try {
            listaPosicionesBatallones.get(getPosition()).remove(this);
        } catch (Exception ignored) {
        }
        jugador.listaBatallonesPropios.remove(getIdBatallon());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Batallon)) return false;
        if (!super.equals(o)) return false;
        Batallon batallon = (Batallon) o;
        return getIdBatallon() == batallon.getIdBatallon();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getIdBatallon());
    }
}

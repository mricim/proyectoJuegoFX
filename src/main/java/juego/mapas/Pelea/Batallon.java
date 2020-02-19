package main.java.juego.mapas.Pelea;

import java.util.HashMap;

public class Batallon {
    private HashMap<String, PosicionSoldado> posicionSoldadoHashMap = new HashMap<>();
    private int idBatallon;
    private String nombre;
    private double poderMilitar;


    public Batallon(int idBatallon, String nombre) {
        this.idBatallon = idBatallon;
        this.nombre = nombre;
    }

    public void addListaPosicionesSoldados(String col_row, PosicionSoldado posicionSoldado) {
        this.posicionSoldadoHashMap.put(col_row,posicionSoldado);
    }

    public HashMap<String, PosicionSoldado> getPosicionSoldadoHashMap() {
        return posicionSoldadoHashMap;
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

    public void setPoderMilitar() {
        double poderMilitar = 0;
        for (PosicionSoldado p:posicionSoldadoHashMap.values()) {
            //TODO iterar sobre el map de posicion de soldados y calcular el podermilitar del batallon
           poderMilitar += p.getSoldado().getCantidad()*p.getSoldado().getSoldadosPreCargados().getId();
        }
        this.poderMilitar = poderMilitar;
    }
}

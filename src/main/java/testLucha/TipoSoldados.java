package main.java.testLucha;

import static main.java.testLucha.MainLucha1.tipoSoldadosTreeMap;

public class TipoSoldados {
    private int id;
    private String nombre;

    public TipoSoldados(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        tipoSoldadosTreeMap.put(id,this);
    }

    public String getNombre() {
        return nombre;
    }
}

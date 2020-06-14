package main.java.test.testLucha;

import static main.java.test.testLucha.MainLucha1.tipoSoldadosTreeMap;

public class TipoSoldados {
    private final int id;
    private final String nombre;

    public TipoSoldados(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        tipoSoldadosTreeMap.put(id,this);
    }

    public String getNombre() {
        return nombre;
    }
}

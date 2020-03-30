package main.java.juego.comercio;

import main.java.juego.mapas.Recursos;

import java.util.TimeZone;

public class Comercio {
    Recursos queSeOfrece;
    Recursos queSePide;
    TimeZone horaPublicacion;
    TimeZone horaFin;

    public Comercio(Recursos queSeOfrece, Recursos queSePide) {
        this.queSeOfrece = queSeOfrece;
        this.queSePide = queSePide;
        TimeZone timeZone=TimeZone.getTimeZone("UTC");
        this.horaPublicacion =timeZone;
        this.horaPublicacion =timeZone;
    }
}

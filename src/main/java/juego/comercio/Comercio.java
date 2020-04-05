package main.java.juego.comercio;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import main.java.juego.mapas.Recursos;
import main.java.jugadores.Jugador;
import main.java.utils.Time;

import java.time.LocalDateTime;
import java.util.TreeMap;


public class Comercio extends Time {

    public static TreeMap<Integer, Comercio> lista = new TreeMap<>();
    public static final ObservableList<Comercio> data = FXCollections.observableArrayList();
    private static Integer lastId = 0;

    private SimpleIntegerProperty id;
    private Recursos queSeOfrece;
    private Recursos queSePide;
    private ImageView recursoOferta;
    private ImageView recursoDemanda;
    private Jugador jugador;
    private LocalDateTime horaPublicacion;
    private LocalDateTime horaFin;


    public Comercio(Recursos queSeOfrece, Recursos queSePide, Jugador jugador) {
        id = new SimpleIntegerProperty(lastId++);
        this.queSeOfrece = queSeOfrece;
        this.queSePide = queSePide;
        this.jugador = jugador;
        this.recursoOferta = new ImageView(queSeOfrece.getRecursosPrecargados().getImage());
        this.recursoDemanda = new ImageView(queSePide.getRecursosPrecargados().getImage());

        horaPublicacion = horaActual_UTC;
        horaFin = horaPublicacion.plusHours(3);
        //LocalDateTime gmt = changeZone(horaPublicacion,ZONE_UTC,ZONE_GTM);
        //LocalDateTime gmtMasHoras = changeZone(horaFin,ZONE_UTC,ZONE_GTM);
        lista.put(id.intValue(), this);
        data.add(this);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public Recursos getQueSeOfrece() {
        return queSeOfrece;
    }

    public Recursos getQueSePide() {
        return queSePide;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public ImageView getRecursoOferta() {
        return recursoOferta;
    }

    public void setRecursoOferta(ImageView recursoOferta) {
        this.recursoOferta = recursoOferta;
    }

    public void setRecursoDemanda(ImageView recursoDemanda) {
        this.recursoDemanda = recursoDemanda;
    }

    public ImageView getRecursoDemanda() {
        return recursoDemanda;
    }

    public LocalDateTime getHoraPublicacionUTC() {
        return horaPublicacion;
    }

    public LocalDateTime getHoraPublicacionGMT() {
        return changeZoneUTC_GMT(horaPublicacion);
    }

    public LocalDateTime getHoraFinUTC() {
        return horaFin;
    }

    public LocalDateTime getHoraFinGMT() {
        return changeZoneUTC_GMT(horaFin);
    }

    @Override
    public String toString() {
        return "Comercio{" +
                "id=" + id +
                ", queSeOfrece=" + queSeOfrece +
                ", queSePide=" + queSePide +
                ", jugador=" + jugador +
                ", horaPublicacion=" + horaPublicacion +
                ", horaFin=" + horaFin +
                '}';
    }

    //METODOS
/*
    public static void main(String[] args) {
        Comercio comercio = new Comercio(2, 5);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Comercio comercio2 = new Comercio(2, 5);

        System.out.println(comercio);
        System.out.println("INICIO UTC: " + comercio.getHoraPublicacionUTC());
        System.out.println("FIN UTC: " + comercio.getHoraFinUTC());
        System.out.println("INICIO GMT: " + comercio.getHoraPublicacionGMT());
        System.out.println("FIN GMT: " + comercio.getHoraFinGMT());
        System.out.println(comercio2);
        System.out.println("INICIO UTC: " + comercio2.getHoraPublicacionUTC());
        System.out.println("FIN UTC: " + comercio2.getHoraFinUTC());
        System.out.println("INICIO GMT: " + comercio2.getHoraPublicacionGMT());
        System.out.println("FIN GMT: " + comercio2.getHoraFinGMT());

        System.out.println();
        System.out.println();


        System.out.println(getChronomether(comercio2.getHoraFinUTC()));
    }
*/

}

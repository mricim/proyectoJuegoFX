package main.java.juego.comercio;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import main.java.juego.mapas.Recursos;
import main.java.jugadores.Jugador;
import main.java.utils.Time;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.TreeMap;

import static main.java.utils.Time.changeZoneUTC_GMT;


public class Comercio {

    //public static TreeMap<Integer, Comercio> lista = new TreeMap<>();
    /**
     * La lista de comercios creados.
     */
    public static final ObservableList<Comercio> data = FXCollections.observableArrayList();
    private static Integer lastId = 0;

    private SimpleIntegerProperty id;

    private SimpleObjectProperty queSeOfrece;
    private SimpleObjectProperty queSePide;
    private Jugador jugador;
    private LocalDateTime horaPublicacion;
    private LocalDateTime horaFin;


    /**
     * Crea un comercio con los datos de la transacción y se añade a la lista
     * de Comercios creados.
     * @param queSeOfrece El Recurso que se ofrece en el comercio.
     * @param queSePide El Recurso que se pide a cambio.
     * @param jugador El jugador que ha creado la oferta de comercio.
     */
    public Comercio(Recursos queSeOfrece, Recursos queSePide, Jugador jugador) {
        id = new SimpleIntegerProperty(lastId++);
        this.queSeOfrece = new SimpleObjectProperty(queSeOfrece);
        this.queSePide = new SimpleObjectProperty(queSePide);
        this.jugador = jugador;


        horaPublicacion = Time.horaActual_UTC;
        horaFin = horaPublicacion.plusHours(3);

        //LocalDateTime gmt = changeZone(horaPublicacion,ZONE_UTC,ZONE_GTM);
        //LocalDateTime gmtMasHoras = changeZone(horaFin,ZONE_UTC,ZONE_GTM);
        //lista.put(id.intValue(), this);
        data.add(this);
    }

    /**
     * Devuelve el id del comercio.
     * @return Un integer que contiene el valor del id del comercio.
     */
    public int getId() {
        return id.get();
    }


    /**
     * Asigna el valor del id del comercio.
     * @param id Un integer que contiene el valor del id del comercio.
     */
    public void setId(int id) {
        this.id.set(id);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    /**
     * Devuelve el recurso
     * @return
     */
    public Object getQueSeOfrece() {
        return queSeOfrece.get();
    }

    public SimpleObjectProperty queSeOfreceProperty() {
        return queSeOfrece;
    }

    public void setQueSeOfrece(Object queSeOfrece) {
        this.queSeOfrece.set(queSeOfrece);
    }

    public Object getQueSePide() {
        return queSePide.get();
    }

    public SimpleObjectProperty queSePideProperty() {
        return queSePide;
    }

    public void setQueSePide(Object queSePide) {
        this.queSePide.set(queSePide);
    }

    public Jugador getJugador() {
        return jugador;
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

    public void remove() {
        data.remove(this);
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

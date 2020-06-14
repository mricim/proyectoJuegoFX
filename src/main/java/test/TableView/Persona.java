package main.java.test.TableView;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Persona {

    //public static TreeMap<Integer, Comercio> lista = new TreeMap<>();
    /**
     * La lista de comercios creados.
     */
    public static final ObservableList<Persona> data = FXCollections.observableArrayList();
    private static Integer lastId = 0;

    private final SimpleIntegerProperty id;
    private final SimpleObjectProperty<DatosPersona> datosDePersona;

    public Persona(DatosPersona datosPersona) {
        id = new SimpleIntegerProperty(lastId++);
        this.datosDePersona = new SimpleObjectProperty(datosPersona);
        //LocalDateTime gmt = changeZone(horaPublicacion,ZONE_UTC,ZONE_GTM);
        //LocalDateTime gmtMasHoras = changeZone(horaFin,ZONE_UTC,ZONE_GTM);
        //lista.put(id.intValue(), this);
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

    public DatosPersona getDatosDePersona() {
        return datosDePersona.get();
    }

    public SimpleObjectProperty<DatosPersona> datosDePersonaProperty() {
        return datosDePersona;
    }

    public void setDatosDePersona(DatosPersona datosDeDatosPersona) {
        this.datosDePersona.set(datosDeDatosPersona);
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

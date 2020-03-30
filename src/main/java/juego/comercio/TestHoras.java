package main.java.juego.comercio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TestHoras {

    public static void main(String[] args) {
        Date horaPublicacion;
        Date horaFin;

        horaPublicacion = getCurrentUtcTime();
        horaFin = getCurrentUtcTimePlus2();

        System.out.println(horaPublicacion.toString());
        System.out.println(horaFin.toString());
    }

    private static Date getCurrentUtcTimePlus2(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        /*Aqui le pones la timezone que quieras meterle , te pongo la lista de las diferentes zonas horarias.
        https://garygregory.wordpress.com/2013/06/18/what-are-the-java-timezone-ids/
        esta es la linea importante, setTimeZone, puedes coger la hora del usuario y pasarla a la timezone que convenga.
         */
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        try {
            return localDateFormat.parse( simpleDateFormat.format(new Date()) );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Date getCurrentUtcTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        try {
            return localDateFormat.parse( simpleDateFormat.format(new Date()) );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

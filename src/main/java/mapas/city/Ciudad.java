package main.java.mapas.city;

import javafx.scene.image.Image;
import main.java.mapas.CallImages;

import java.util.HashMap;

import static main.java.mapas.Jugador.listaCiudades;
import static main.java.mapas.Jugador.listaEdificiosPreCargada;

public class Ciudad{
    public static final Image ERRORIMAGE= CallImages.getImage("icons/","error");
    static HashMap<Integer[], Posiciones> listaPosiciones = new HashMap<>();

    int idCiudad;
    public Ciudad(int idCiudad) {
        this.idCiudad=idCiudad;
        listaCiudades.add(this);
        //Solicitar a la bd la tabla de posiciones y edificios que tiene el usuario

        try {
        listaPosiciones.put(new Integer[]{8,15},new Posiciones(8, 15, (Edificio) listaEdificiosPreCargada.get(1).clone()));//no tocar
        listaPosiciones.put(new Integer[]{8,14},new Posiciones(8, 14, (Edificio) listaEdificiosPreCargada.get(2).clone()));//no tocar
        listaPosiciones.put(new Integer[]{8,13},new Posiciones(8, 13, (Edificio) listaEdificiosPreCargada.get(2).clone()));//no tocar
        listaPosiciones.put(new Integer[]{8,12},new Posiciones(8, 12, (Edificio) listaEdificiosPreCargada.get(0).clone()));
        listaPosiciones.put(new Integer[]{8,11},new Posiciones(8, 11, (Edificio) listaEdificiosPreCargada.get(0).clone()));
        listaPosiciones.put(new Integer[]{7,12},new Posiciones(7, 12, (Edificio) listaEdificiosPreCargada.get(3).clone()));//todos tendrian "parcela"
        listaPosiciones.put(new Integer[]{6,12},new Posiciones(6, 12, (Edificio) listaEdificiosPreCargada.get(4).clone()));//todos tendrian "parcela"
        listaPosiciones.put(new Integer[]{5,12},new Posiciones(5, 12, (Edificio) listaEdificiosPreCargada.get(5).clone()));//todos tendrian "parcela"
        }catch (Exception e){
            System.err.println("Error: Ciudad (Edificio no creado)");
        }
    }
}

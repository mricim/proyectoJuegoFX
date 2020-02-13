package main.java.juego.mapas.city;

import javafx.scene.image.Image;
import main.java.juego.mapas.CallImages;

import java.util.HashMap;

import static main.java.juego.Jugador.listaEdificiosPreCargada;

public class Ciudad {
    public static final Image ERRORIMAGE = CallImages.getImage("icons/", "error");
    public HashMap<Integer[], PosicionEdificio> listaPosicionesEdificios = new HashMap<>();

    private int idCiudad;
    private String nameCity;

    public Ciudad(int idCiudad, String nameCity) {
        this.idCiudad = idCiudad;
        this.nameCity = nameCity;

        //todo Solicitar a la bd la tabla de posiciones y edificios que tiene el usuario

        try {
            if (idCiudad==2){
                new PosicionEdificio(8, 8,this, new Edificio(listaEdificiosPreCargada.get(0)));//no tocar
                new PosicionEdificio(7, 8,this, new Edificio(listaEdificiosPreCargada.get(2)));//no tocar
                new PosicionEdificio(6, 8,this, new Edificio(listaEdificiosPreCargada.get(0)));//no tocar
            }
            new PosicionEdificio(8, 15, this, new Edificio(listaEdificiosPreCargada.get(1)));//no tocar
            new PosicionEdificio(8, 14, this, new Edificio(listaEdificiosPreCargada.get(2)));//no tocar
            new PosicionEdificio(8, 13, this, new Edificio(listaEdificiosPreCargada.get(2)));//no tocar
            new PosicionEdificio(8, 12, this, new Edificio(listaEdificiosPreCargada.get(0)));
            new PosicionEdificio(8, 11, this, new Edificio(listaEdificiosPreCargada.get(0)));
            new PosicionEdificio(7, 12, this, new Edificio(listaEdificiosPreCargada.get(10)));//todos tendrian "parcela"
            new PosicionEdificio(6, 12, this, new Edificio(listaEdificiosPreCargada.get(11)));//todos tendrian "parcela"
            new PosicionEdificio(5, 12, this, new Edificio(listaEdificiosPreCargada.get(12)));//todos tendrian "parcela"
        } catch (Exception e) {
            System.err.println("Error: Ciudad (Edificio no creado)");
        }
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public String getNameCity() {
        return nameCity;
    }

    public HashMap<Integer[], PosicionEdificio> getListaPosicionesEdificios() {
        return listaPosicionesEdificios;
    }

    public void addListaPosicionesEdificios(Integer[] integers, PosicionEdificio posicionEdificio) {
        this.listaPosicionesEdificios.put(integers, posicionEdificio);
    }
}

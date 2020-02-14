package main.java.juego.mapas.city;

import javafx.scene.image.Image;
import main.java.juego.mapas.CallImages;

import java.util.HashMap;

import static main.java.juego.Jugador.listaEdificiosPreCargada;

public class Ciudad {
    public static final Image ERRORIMAGE = CallImages.getImage("icons/", "error");
    public HashMap<String, PosicionEdificio> listaPosicionesEdificios = new HashMap<>();

    private int idCiudad;
    private String nameCity;
    private int oro;
    private int madera;
    private int piedra;
    private int comida;
    private int hierro;
    private int poblacion;
    private int felicidad;

    public Ciudad(int idCiudad, String nameCity, int oro, int madera, int piedra, int comida, int hierro, int poblacion, int felicidad) {
        this.idCiudad = idCiudad;
        this.nameCity = nameCity;

        this.oro = oro;
        this.madera = madera;
        this.piedra = piedra;
        this.comida = comida;
        this.hierro = hierro;
        this.poblacion = poblacion;
        this.felicidad = felicidad;

        //todo Solicitar a la bd la tabla de posiciones y edificios que tiene el usuario
        try {
            if (idCiudad==2){
                new PosicionEdificio(8, 8,this, new Edificio(listaEdificiosPreCargada.get(0+"_"+0)));//no tocar
                new PosicionEdificio(7, 8,this, new Edificio(listaEdificiosPreCargada.get(2+"_"+0)));//no tocar
                new PosicionEdificio(6, 8,this, new Edificio(listaEdificiosPreCargada.get(0+"_"+0)));//no tocar
            }
            new PosicionEdificio(8, 15, this, new Edificio(listaEdificiosPreCargada.get(1+"_"+0)));//no tocar
            new PosicionEdificio(8, 14, this, new Edificio(listaEdificiosPreCargada.get(2+"_"+0)));//no tocar
            new PosicionEdificio(8, 13, this, new Edificio(listaEdificiosPreCargada.get(2+"_"+0)));//no tocar
            new PosicionEdificio(8, 12, this, new Edificio(listaEdificiosPreCargada.get(2+"_"+0)));
            new PosicionEdificio(8, 11, this, new Edificio(listaEdificiosPreCargada.get(0+"_"+0)));
            new PosicionEdificio(7, 12, this, new Edificio(listaEdificiosPreCargada.get(10+"_"+0)));//todos tendrian "parcela"
            new PosicionEdificio(6, 12, this, new Edificio(listaEdificiosPreCargada.get(11+"_"+0)));//todos tendrian "parcela"
            new PosicionEdificio(5, 12, this, new Edificio(listaEdificiosPreCargada.get(12+"_"+0)));//todos tendrian "parcela"
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

    public HashMap<String, PosicionEdificio> getListaPosicionesEdificios() {
        return listaPosicionesEdificios;
    }

    public void addListaPosicionesEdificios(int colum,int row, PosicionEdificio posicionEdificio) {
        this.listaPosicionesEdificios.put(colum+"_"+row, posicionEdificio);
    }
    public void addListaPosicionesEdificios(String colum_row, PosicionEdificio posicionEdificio) {
        this.listaPosicionesEdificios.put(colum_row, posicionEdificio);
    }
    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public int getMadera() {
        return madera;
    }

    public void setMadera(int madera) {
        this.madera = madera;
    }

    public int getPiedra() {
        return piedra;
    }

    public void setPiedra(int piedra) {
        this.piedra = piedra;
    }

    public int getComida() {
        return comida;
    }

    public void setComida(int comida) {
        this.comida = comida;
    }

    public int getHierro() {
        return hierro;
    }

    public void setHierro(int hierro) {
        this.hierro = hierro;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public int getFelicidad() {
        return felicidad;
    }

    public void setFelicidad(int felicidad) {
        this.felicidad = felicidad;
    }
}

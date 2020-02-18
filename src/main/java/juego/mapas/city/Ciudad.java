package main.java.juego.mapas.city;

import javafx.scene.image.Image;
import main.java.Utils.CallImages;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.city.ContentCity.Edificio;
import main.java.juego.mapas.city.ContentCity.PosicionEdificio;


import java.util.HashMap;
import java.util.TreeMap;

import static main.java.juego.Jugador.listaEdificiosPreCargada;

public class Ciudad {
    public static final Image ERRORIMAGE = CallImages.getImage("", "error");
    private HashMap<String, PosicionEdificio> listaPosicionesEdificios = new HashMap<>();

    private int idCiudad;
    private String nameCity;
    public TreeMap<Integer, Recursos> recursosTreeMap = new TreeMap<>();


    public Ciudad(int idCiudad, String nameCity, int oro, int madera, int piedra, int comida, int hierro, int poblacion, int felicidad) {
        this.idCiudad = idCiudad;
        this.nameCity = nameCity;

        recursosTreeMap.put(0, new Recursos(0, oro));
//        this.oro = oro;
        recursosTreeMap.put(1, new Recursos(1, madera));
//        this.madera = madera;
        recursosTreeMap.put(2, new Recursos(2, piedra));
//        this.piedra = piedra;
        recursosTreeMap.put(3, new Recursos(3, comida));
//        this.comida = comida;
        recursosTreeMap.put(4, new Recursos(4, hierro));
//        this.hierro = hierro;
        recursosTreeMap.put(5, new Recursos(5, poblacion));
//        this.poblacion = poblacion;
        recursosTreeMap.put(6, new Recursos(6, felicidad));
//        this.felicidad = felicidad;

        //todo Solicitar a la bd la tabla de posiciones y edificios que tiene el usuario
        try {
            if (idCiudad == 2) {
                new PosicionEdificio(8, 8, this, new Edificio(listaEdificiosPreCargada.get(0 + "_" + 0)));//no tocar
                new PosicionEdificio(7, 8, this, new Edificio(listaEdificiosPreCargada.get(2 + "_" + 0)));//no tocar
                new PosicionEdificio(6, 8, this, new Edificio(listaEdificiosPreCargada.get(0 + "_" + 0)));//no tocar
            }
            new PosicionEdificio(8, 15, this, new Edificio(listaEdificiosPreCargada.get(1 + "_" + 1)));
            new PosicionEdificio(8, 14, this, new Edificio(listaEdificiosPreCargada.get(2 + "_" + 1)));
            new PosicionEdificio(8, 13, this, new Edificio(listaEdificiosPreCargada.get(2 + "_" + 0)));
            new PosicionEdificio(8, 12, this, new Edificio(listaEdificiosPreCargada.get(2 + "_" + 0)));
            new PosicionEdificio(8, 11, this, new Edificio(listaEdificiosPreCargada.get(0 + "_" + 0)));
            new PosicionEdificio(7, 12, this, new Edificio(listaEdificiosPreCargada.get(10 + "_" + 0)));//todos tendrian "parcela"
            new PosicionEdificio(6, 12, this, new Edificio(listaEdificiosPreCargada.get(11 + "_" + 0)));//todos tendrian "parcela"
            new PosicionEdificio(5, 12, this, new Edificio(listaEdificiosPreCargada.get(12 + "_" + 0)));//todos tendrian "parcela"
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

    public void addListaPosicionesEdificios(int colum, int row, PosicionEdificio posicionEdificio) {
        this.listaPosicionesEdificios.put(colum + "_" + row, posicionEdificio);
    }

    public void addListaPosicionesEdificios(String colum_row, PosicionEdificio posicionEdificio) {
        this.listaPosicionesEdificios.put(colum_row, posicionEdificio);
    }

    public TreeMap<Integer, Recursos> getRecursosTreeMap() {
        return recursosTreeMap;
    }

    //ORO
    public int getOro() {
        return recursosTreeMap.get(0).getCantidad();
    }

    public synchronized void setOro(int oro) {
        recursosTreeMap.get(0).setCantidad(oro);
    }

    public Image getOroImage() {
        return recursosTreeMap.get(0).getImage();
    }

    //MADERA
    public int getMadera() {
        return recursosTreeMap.get(1).getCantidad();
    }

    public synchronized void setMadera(int madera) {
        recursosTreeMap.get(1).setCantidad(madera);
    }

    public Image getMaderaImage() {
        return recursosTreeMap.get(1).getImage();
    }

    //PIEDRA
    public int getPiedra() {
        return recursosTreeMap.get(2).getCantidad();
    }

    public synchronized void setPiedra(int piedra) {
        recursosTreeMap.get(2).setCantidad(piedra);
    }

    public Image getPiedraImage() {
        return recursosTreeMap.get(2).getImage();
    }

    //COMIDA
    public int getComida() {
        return recursosTreeMap.get(3).getCantidad();
    }

    public synchronized void setComida(int comida) {
        recursosTreeMap.get(3).setCantidad(comida);
    }

    public Image getComidaImage() {
        return recursosTreeMap.get(3).getImage();
    }

    //HIERRO
    public int getHierro() {
        return recursosTreeMap.get(4).getCantidad();
    }

    public synchronized void setHierro(int hierro) {
        recursosTreeMap.get(4).setCantidad(hierro);
    }

    public Image getHierroImage() {
        return recursosTreeMap.get(4).getImage();
    }

    //POBLACION
    public int getPoblacion() {
        return recursosTreeMap.get(5).getCantidad();
    }

    public synchronized void setPoblacion(int poblacion) {
        recursosTreeMap.get(5).setCantidad(poblacion);
    }

    public Image getPoblacionImage() {
        return recursosTreeMap.get(5).getImage();
    }

    //FELICIDAD
    public int getFelicidad() {
        return recursosTreeMap.get(6).getCantidad();
    }

    public void setFelicidad(int felicidad) {
        recursosTreeMap.get(6).setCantidad(felicidad);
    }

    public Image getFelicidadImage() {
        return recursosTreeMap.get(6).getImage();
    }
}

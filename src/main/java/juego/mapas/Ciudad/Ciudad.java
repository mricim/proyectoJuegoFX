package main.java.juego.mapas.Ciudad;

import javafx.scene.image.Image;
import main.java.Utils.Posiciones;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.Ciudad.ContentCity.Edificio;


import java.util.HashMap;
import java.util.TreeMap;

import static main.java.Jugadores.Jugador.listaEdificiosPreCargados;
import static main.java.Jugadores.Jugador.listaCiudades;

public class Ciudad extends Posiciones {

    private TreeMap<String, Edificio> listaPosicionesEdificios = new TreeMap<>();

    private int idCiudad;
    private String nameCity;
    public TreeMap<Integer, Recursos> recursosTreeMap = new TreeMap<>();
    private int nivelCiudad;

    public Ciudad(int idCiudad, String nameCity,int fila,int columna,int nivelCiudad, int oro, int madera, int piedra, int comida, int hierro, int poblacion, int felicidad) {
        super(fila,columna);
        this.idCiudad = idCiudad;
        this.nameCity = nameCity;
this.nivelCiudad=nivelCiudad;
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
                new Edificio(listaEdificiosPreCargados.get(0+"_"+0),8, 8, this);//parcela
                new Edificio(listaEdificiosPreCargados.get(2+"_"+0),7, 8, this);
                new Edificio(listaEdificiosPreCargados.get(0+"_"+0),6, 8, this);//parcela
                new Edificio(listaEdificiosPreCargados.get(1+"_"+0),8, 15, this);
            }else if(idCiudad==1){
                new Edificio(listaEdificiosPreCargados.get(1+"_"+1),8, 7, this);
            }
            new Edificio(listaEdificiosPreCargados.get(2+"_"+0),3, 13, this);
            new Edificio(listaEdificiosPreCargados.get(10+"_"+0),10, 4, this);
            new Edificio(listaEdificiosPreCargados.get(11+"_"+0),8, 12, this);
            new Edificio(listaEdificiosPreCargados.get(0+"_"+0),5, 7, this);
            new Edificio(listaEdificiosPreCargados.get(10+"_"+0),7, 13, this);//todos tendrian "parcela"
            new Edificio(listaEdificiosPreCargados.get(11+"_"+0),12, 2, this);//todos tendrian "parcela"
            new Edificio(listaEdificiosPreCargados.get(12+"_"+1),5, 18, this);//todos tendrian "parcela"
        } catch (Exception e) {
            System.err.println("Error: Ciudad (Edificio no creado)\n"+e);
        }
        listaCiudades.put(getPosition(),this);
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public String getNameCity() {
        return nameCity;
    }

    public TreeMap<String, Edificio> getListaPosicionesEdificios() {
        return listaPosicionesEdificios;
    }

    public void addListaPosicionesEdificios(int colum, int row, Edificio posicionEdificio) {
        this.listaPosicionesEdificios.put(colum + "_" + row, posicionEdificio);
    }

    public void addListaPosicionesEdificios(String colum_row, Edificio posicionEdificio) {
        this.listaPosicionesEdificios.put(colum_row, posicionEdificio);
    }

    public TreeMap<Integer, Recursos> getRecursosTreeMap() {
        return recursosTreeMap;
    }

    public int getNivelCiudad() {
        return nivelCiudad;
    }

    public void setNivelCiudad(int nivelCiudad) {
        this.nivelCiudad = nivelCiudad;
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

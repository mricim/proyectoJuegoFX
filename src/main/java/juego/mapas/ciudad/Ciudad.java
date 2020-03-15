package main.java.juego.mapas.ciudad;

import com.sun.istack.NotNull;
import main.java.Utils.Posicion;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.ciudad.contenidoCiudad.Edificio;
import main.java.juego.mapas.pelea.*;
import main.java.jugadores.Clan;
import main.java.jugadores.Jugador;


import java.util.TreeMap;

import static main.java.jugadores.Jugador.*;

public class Ciudad extends Posicion {
    private static int lastId = 1;
    private TreeMap<String, Edificio> listaPosicionesEdificios = new TreeMap<>();
    @NotNull
    private int idCiudad;
    private String nameCity;
    private TreeMap<Integer, Recursos> recursosTreeMap = new TreeMap<>();
    private int nivelCiudad;
    private TreeMap<Integer, Soldados> listSoldadosCity = new TreeMap<>();
    private TreeMap<Integer, MaquinasAsedio> listMaquinascity = new TreeMap<>();

    public Ciudad(Jugador jugador, String nameCity, int fila, int columna, int nivelCiudad, int oro, int madera, int piedra, int comida, int hierro, int poblacion, int felicidad) {
        super(fila, columna);
        this.idCiudad = lastId++;
        this.nameCity = nameCity;
        this.nivelCiudad = nivelCiudad;
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

        for (SoldadosPreCargados soldaditos : listaSoldadosPreCargada.values()) {
            this.listSoldadosCity.put(soldaditos.getTipe(),new Soldados(soldaditos,0,0,0,0));
        }
        for (MaquinasAsedioPreCargadas maquinitas : listaAsedioPreCargada.values()) {
            this.listMaquinascity.put(maquinitas.getTipe(),new MaquinasAsedio(maquinitas,0,0,0,0));

        }


        //todo Solicitar a la bd la tabla de posiciones y edificios que tiene el usuario
        try {
            if (idCiudad == 2) {
                new Edificio(listaEdificiosPreCargados.get(0 + "-" + 0), 8, 8, this);//parcela
                new Edificio(listaEdificiosPreCargados.get(2 + "-" + 0), 7, 8, this);
                new Edificio(listaEdificiosPreCargados.get(0 + "-" + 0), 6, 8, this);//parcela
                new Edificio(listaEdificiosPreCargados.get(1 + "-" + 0), 8, 15, this);
            } else if (idCiudad == 1) {
                new Edificio(listaEdificiosPreCargados.get(1 + "-" + 1), 8, 7, this);
            }
            new Edificio(listaEdificiosPreCargados.get(2 + "-" + 0), 3, 13, this);
            new Edificio(listaEdificiosPreCargados.get(10 + "-" + 0), 10, 4, this);
            new Edificio(listaEdificiosPreCargados.get(11 + "-" + 0), 8, 12, this);
            new Edificio(listaEdificiosPreCargados.get(0 + "-" + 0), 5, 7, this);
            new Edificio(listaEdificiosPreCargados.get(10 + "-" + 0), 7, 13, this);//todos tendrian "parcela"
            new Edificio(listaEdificiosPreCargados.get(11 + "-" + 0), 12, 2, this);//todos tendrian "parcela"
            new Edificio(listaEdificiosPreCargados.get(12 + "-" + 1), 5, 18, this);//todos tendrian "parcela"

            new Edificio(listaEdificiosPreCargados.get(13 + "-" + 0), 7, 15, this);//todos tendrian "parcela"
            new Edificio(listaEdificiosPreCargados.get(14 + "-" + 0), 7, 16, this);//todos tendrian "parcela"
            new Edificio(listaEdificiosPreCargados.get(15 + "-" + 0), 7, 17, this);//todos tendrian "parcela"
        } catch (Exception e) {
            System.err.println("Error: Ciudad (Edificio no creado)\n" + e);
        }
        jugador.listaCiudadesPropias.put(getPosition(), this);
        if (Clan.jugadoresQueEstanEnUnClan.containsKey(jugador)) {
            Clan.jugadoresQueEstanEnUnClan.get(jugador).addCiudades(this);
        }
        listaCiudades.put(getPosition(), this);
    }

    public Ciudad(String position) {
        super(position);
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
        this.listaPosicionesEdificios.put(colum + "-" + row, posicionEdificio);
    }

    public void addListaPosicionesEdificios(String colum_row, Edificio posicionEdificio) {
        this.listaPosicionesEdificios.put(colum_row, posicionEdificio);
    }

    public TreeMap<Integer, Recursos> getRecursosTreeMap() {
        return recursosTreeMap;
    }

    public void setRecursosTreeMap(TreeMap<Integer, Recursos> recursosTreeMap) {
        this.recursosTreeMap = recursosTreeMap;
    }

    public int getNivelCiudad() {
        return nivelCiudad;
    }

    public void setNivelCiudad(int nivelCiudad) {
        this.nivelCiudad = nivelCiudad;
    }

    public TreeMap<Integer, Soldados> getListSoldadosCity() {
        return listSoldadosCity;
    }

    public void addSoldados(TreeMap<Integer, Soldados> soldados) {
        for (Soldados soldado : soldados.values()) {
            Soldados soldados2=this.listSoldadosCity.get(soldado.getsoldadosPreCargados().getTipe());
            soldados2.setCantidad(soldados2.getCantidad()+soldado.getCantidad());
        }
    }

    public TreeMap<Integer, MaquinasAsedio> getListMaquinascity() {
        return listMaquinascity;
    }

    public void addMaquinas(TreeMap<Integer, MaquinasAsedio> maquinas) {
        for (MaquinasAsedio maquinasAsedio : maquinas.values()) {
            MaquinasAsedio maquinasAsedio1=this.listMaquinascity.get(maquinasAsedio.getsoldadosPreCargados().getTipe());
            maquinasAsedio1.setCantidad(maquinasAsedio1.getCantidad()+maquinasAsedio.getCantidad());
        }
    }
}

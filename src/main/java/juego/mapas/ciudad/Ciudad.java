package main.java.juego.mapas.ciudad;

import main.java.juego.mapas.RecursosPrecargados;
import main.java.utils.Posicion;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.ciudad.contenidoCiudad.Edificio;
import main.java.juego.mapas.pelea.*;
import main.java.jugadores.Clan;
import main.java.jugadores.Jugador;


import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static main.java.Inicio.PantallaInicialController.elTemaSeleccionado;
import static main.java.jugadores.Jugador.*;

public class Ciudad extends Posicion {
    private static int lastId = 1;
    private final Map<String, Edificio> listaPosicionesEdificios = new TreeMap<>();
    private int idCiudad;
    private String nameCity;
    private Map<Integer, Recursos> recursosTreeMap = new TreeMap<>();//TODO COMPROBAR QUE NO PUEDE PASAR DEL LIMITE DE CAPACIDAD DE LA CIUDAD
    private int nivelCiudad;
    private final Map<Integer, Unidades> listSoldadosCity = new TreeMap<>();

    public Ciudad(Jugador jugador, String nameCity, int fila, int columna, int nivelCiudad, ArrayList<Recursos> recursosDeLaCity) {
        super(fila, columna);
        this.idCiudad = lastId++;
        this.nameCity = nameCity;
        this.nivelCiudad = nivelCiudad;



        for (Map.Entry<Integer, RecursosPrecargados> integerRecursosPrecargadosEntry : elTemaSeleccionado.listaRecursosPreCargada.entrySet()) {
            RecursosPrecargados a = integerRecursosPrecargadosEntry.getValue();
            if (a.isMenuCiudad()) {
                int i = integerRecursosPrecargadosEntry.getKey();
                try {
                    Recursos recursos = recursosDeLaCity.get(i);
                    recursosTreeMap.put(i, recursos);
                }catch (Exception e){
                    recursosTreeMap.put(i, new Recursos(i, 0));
                }
            }
        }


        for (UnidadesPreCargadas soldaditos : elTemaSeleccionado.listaSoldadosPreCargada.values()) {
            this.listSoldadosCity.put(soldaditos.getIdType(), new Unidades(soldaditos, 0, 0, 0, 0));
        }


        //todo Solicitar a la bd la tabla de posiciones y edificios que tiene el usuario
        try {
            if (idCiudad == 2) {
                new Edificio(elTemaSeleccionado.listaEdificiosPreCargados.get(0 + "_" + 0), 8, 8, this);//parcela
                new Edificio(elTemaSeleccionado.listaEdificiosPreCargados.get(2 + "_" + 1), 1, 12, this);
                new Edificio(elTemaSeleccionado.listaEdificiosPreCargados.get(0 + "_" + 0), 6, 8, this);//parcela
                new Edificio(elTemaSeleccionado.listaEdificiosPreCargados.get(1 + "_" + 0), 8, 15, this);
            } else if (idCiudad == 1) {
                new Edificio(elTemaSeleccionado.listaEdificiosPreCargados.get(1 + "_" + 1), 8, 7, this);
                new Edificio(elTemaSeleccionado.listaEdificiosPreCargados.get(2 + "_" + 0), 1, 12, this);
            }
            new Edificio(elTemaSeleccionado.listaEdificiosPreCargados.get(10 + "_" + 0), 10, 4, this);
            new Edificio(elTemaSeleccionado.listaEdificiosPreCargados.get(11 + "_" + 0), 8, 12, this);
            new Edificio(elTemaSeleccionado.listaEdificiosPreCargados.get(0 + "_" + 0), 5, 7, this);
            new Edificio(elTemaSeleccionado.listaEdificiosPreCargados.get(10 + "_" + 0), 7, 13, this);//todos tendrian "parcela"
            new Edificio(elTemaSeleccionado.listaEdificiosPreCargados.get(11 + "_" + 0), 12, 2, this);//todos tendrian "parcela"
            new Edificio(elTemaSeleccionado.listaEdificiosPreCargados.get(12 + "_" + 1), 5, 18, this);//todos tendrian "parcela"

            new Edificio(elTemaSeleccionado.listaEdificiosPreCargados.get(13 + "_" + 0), 7, 15, this);//todos tendrian "parcela"
            new Edificio(elTemaSeleccionado.listaEdificiosPreCargados.get(14 + "_" + 0), 7, 16, this);//todos tendrian "parcela"
            new Edificio(elTemaSeleccionado.listaEdificiosPreCargados.get(15 + "_" + 0), 7, 17, this);//todos tendrian "parcela"
            new Edificio(elTemaSeleccionado.listaEdificiosPreCargados.get(16 + "_" + 0), 12, 17, this);//todos tendrian "parcela"
            new Edificio(elTemaSeleccionado.listaEdificiosPreCargados.get(17 + "_" + 0), 13, 5, this);//todos tendrian "parcela"
            new Edificio(elTemaSeleccionado.listaEdificiosPreCargados.get(18 + "_" + 0), 14, 10, this);//todos tendrian "parcela"
            new Edificio(elTemaSeleccionado.listaEdificiosPreCargados.get(19 + "_" + 0), 3, 7, this);//todos tendrian "parcela"
            new Edificio(elTemaSeleccionado.listaEdificiosPreCargados.get(20 + "_" + 0), 4, 8, this);//todos tendrian "parcela"
        } catch (Exception e) {
            System.err.println("Error: Ciudad (Edificio no creado)\n");
            e.printStackTrace();
        }
        jugador.listaCiudadesPropias.put(getPosition(), this);
        if (Clan.jugadoresQueEstanEnUnClan.containsKey(jugador)) {
            Clan.jugadoresQueEstanEnUnClan.get(jugador).addCiudades(this);
        }
        elTemaSeleccionado.listaCiudades.put(getPosition(), this);
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

    public Map<String, Edificio> getListaPosicionesEdificios() {
        return listaPosicionesEdificios;
    }

    public void addListaPosicionesEdificios(int colum, int row, Edificio posicionEdificio) {
        this.listaPosicionesEdificios.put(colum + "_" + row, posicionEdificio);
    }

    public void addListaPosicionesEdificios(String colum_row, Edificio posicionEdificio) {
        this.listaPosicionesEdificios.put(colum_row, posicionEdificio);
    }

    public Map<Integer, Recursos> getRecursosTreeMap() {
        return recursosTreeMap;
    }

    public void setRecursosTreeMap(Map<Integer, Recursos> recursosTreeMap) {
        this.recursosTreeMap = recursosTreeMap;
    }

    public int getNivelCiudad() {
        return nivelCiudad;
    }

    public void setNivelCiudad(int nivelCiudad) {
        this.nivelCiudad = nivelCiudad;
    }

    public Map<Integer, Unidades> getListSoldadosCity() {
        return listSoldadosCity;
    }

    public void addSoldados(Map<Integer, Unidades> soldados) {
        for (Unidades soldado : soldados.values()) {
            Unidades soldados2 = this.listSoldadosCity.get(soldado.getUnidadesPreCargadas().getIdType());
            System.out.println(soldados2.getCantidad() + soldado.getCantidad());
            soldados2.addCantidad(soldado.getCantidad());
        }
    }
}

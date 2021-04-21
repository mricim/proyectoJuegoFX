package main.java.temas;

import main.java.Main;
import main.java.hibernate.DbOperations;
import main.java.juego.mapas.RecursosPrecargados;
import main.java.juego.mapas.ciudad.Ciudad;
import main.java.juego.mapas.ciudad.EdificiosPreCargados;
import main.java.juego.mapas.pelea.Batallon;
import main.java.juego.mapas.pelea.UnidadesPreCargadas;
import main.java.jugadores.Jugador;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.*;

import static main.java.Main.pathImagesInternal;

public class Temas implements Serializable {
    public static List<Temas> listaDeTemas = new ArrayList<>();
    private static boolean filesIsExternal;
    public static String pathImagesExternal = System.getProperty("user.dir").replace("ProyectoJuegoFX", "") + File.separator + "res" + File.separator + "images" + File.separator;


    private static final String pathImagesTemaInternal = pathImagesInternal + "temas/";
    private static final String pathImagesTemaExternal = pathImagesExternal + "temas/";
    public static List<String> arrayListTemas = obtainListaTemas();//Justo antes de obtainListaTemas()

    private static List<String> obtainListaTemas() {
        List<String> listaTemas = new ArrayList<>();

//        System.out.println("TEMAS PATH: "+pathImagesTemaExternal);
        File temasDirectoryExternalFile = new File(pathImagesTemaExternal);
        File[] f;
        if (temasDirectoryExternalFile.exists()) {
            f = temasDirectoryExternalFile.listFiles();
            filesIsExternal = true;
        } else {
            File temasDirectoryInternalFile = new File(pathImagesTemaInternal);
            f = temasDirectoryInternalFile.listFiles();
            filesIsExternal = false;
        }

        int x = Objects.requireNonNull(f).length;
        for (int i = 0; i < x; i++) {
            String str = f[i].getName().replaceAll("[0-9]", "").replaceAll("(.)([A-Z])", "$1 $2");
            listaTemas.add((str.substring(0, 1).toUpperCase() + str.substring(1)));
        }
        //TODO BORRAR
        for (String listaTema : listaTemas) {
            listaDeTemas.add(new Temas(listaTema));
        }
        //TODO BORRAR
        return listaTemas;
    }

    public static String PATH_TEMA_USE;
    public static String PATH_USE;

    public static void ruteUse(String nameTema) {
        if (filesIsExternal) {
            PATH_TEMA_USE = pathImagesTemaExternal + nameTema;
            PATH_USE = pathImagesExternal;
        } else {
            PATH_TEMA_USE = pathImagesTemaInternal + nameTema;
            PATH_USE = pathImagesInternal;
        }
    }


    //

    //ESPIAS
    public Map<Integer, Integer> espias = new HashMap<>();
    //FIN ESPIAS

    private String name;
    private Integer id;

    public Map<String, EdificiosPreCargados> listaEdificiosPreCargados = new TreeMap<>();
    public Map<Integer, UnidadesPreCargadas> listaSoldadosPreCargada = new TreeMap();
    public Map<Integer, RecursosPrecargados> listaRecursosPreCargada = new TreeMap();

    public Map<Long, Jugador> listaTodosLosJugadores = new TreeMap<>();
    public Map<String, ArrayList<Batallon>> listaPosicionesBatallones = new TreeMap<>();
    public Map<String, Ciudad> listaCiudades = new TreeMap<>();

    public Temas() {
    }

    public Temas(String name) {
        this.name = name;
        System.out.println(name);
        //DbOperations.createRecord(this);
    }

    @Basic
    @Column(name = "nombreTema", unique = true, nullable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tema_id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = EdificiosPreCargados.class)
    @JoinColumn(name = "tema_edificio_precargado_fk")
    public Map<String, EdificiosPreCargados> getListaEdificiosPreCargados() {
        return listaEdificiosPreCargados;
    }

    public void setListaEdificiosPreCargados(Map<String, EdificiosPreCargados> listaEdificiosPreCargados) {
        this.listaEdificiosPreCargados = listaEdificiosPreCargados;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = UnidadesPreCargadas.class)
    @JoinColumn(name = "tema_unidades_precargado_fk")
    public Map<Integer, UnidadesPreCargadas> getListaSoldadosPreCargada() {
        return listaSoldadosPreCargada;
    }

    public void setListaSoldadosPreCargada(Map<Integer, UnidadesPreCargadas> listaSoldadosPreCargada) {
        this.listaSoldadosPreCargada = listaSoldadosPreCargada;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = RecursosPrecargados.class)
    @JoinColumn(name = "tema_recursos_precargado_fk")
    public Map<Integer, RecursosPrecargados> getListaRecursosPreCargada() {
        return listaRecursosPreCargada;
    }

    public void setListaRecursosPreCargada(Map<Integer, RecursosPrecargados> listaRecursosPreCargada) {
        this.listaRecursosPreCargada = listaRecursosPreCargada;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Jugador.class)
    @JoinColumn(name = "tema_jugador_fk")
    public Map<Long, Jugador> getListaTodosLosJugadores() {
        return listaTodosLosJugadores;
    }


    public void setListaTodosLosJugadores(Map<Long, Jugador> listaTodosLosJugadores) {
        this.listaTodosLosJugadores = listaTodosLosJugadores;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Batallon.class)
    @JoinColumn(name = "tema_batallon_fk")
    public Map<String, ArrayList<Batallon>> getListaPosicionesBatallones() {
        return listaPosicionesBatallones;
    }

    public void setListaPosicionesBatallones(Map<String, ArrayList<Batallon>> listaPosicionesBatallones) {
        this.listaPosicionesBatallones = listaPosicionesBatallones;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Ciudad.class)
    @JoinColumn(name = "tema_ciudad_fk")
    public Map<String, Ciudad> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(Map<String, Ciudad> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temas temas = (Temas) o;
        return Objects.equals(name, temas.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

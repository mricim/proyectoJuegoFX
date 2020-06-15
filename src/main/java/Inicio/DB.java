package main.java.Inicio;

import javafx.scene.control.ProgressBar;
import main.java.juego.comercio.Comercio;
import main.java.juego.mapas.Recursos;
import main.java.juego.mapas.RecursosPrecargados;
import main.java.juego.mapas.ciudad.EdificiosPreCargados;
import main.java.juego.mapas.pelea.UnidadesPreCargadas;
import main.java.jugadores.Clan;
import main.java.jugadores.Jugador;
import main.java.temas.Temas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static main.java.Inicio.PantallaInicialController.elTemaSeleccionado;
import static main.java.utils.PrimaryStageControler.*;

public class DB {
    //TODO Esto estaria en la DB

    public static void callbdAccordingTema(ProgressBar progresBar, Temas elTemaSeleccionado) {
        if (elTemaSeleccionado.getName().equals("Demo")) {
            callbdDemo(progresBar);
        } else {
            callbdCuerpoHumano(progresBar);
        }
    }

    static void callbdDemo(ProgressBar progresBar) {

        RecursosPrecargados oroPre = new RecursosPrecargados(0, TRADUCCIONES_THEMA.getString("recurso.oro"), true, true, false, true);
        RecursosPrecargados maderaPre = new RecursosPrecargados(1, TRADUCCIONES_THEMA.getString("recurso.madera"), true, true, false, true);
        RecursosPrecargados piedraPre = new RecursosPrecargados(2, TRADUCCIONES_THEMA.getString("recurso.piedra"), true, true, false, true);
        RecursosPrecargados comidaPre = new RecursosPrecargados(3, TRADUCCIONES_THEMA.getString("recurso.comida"), true, true, false, true);
        RecursosPrecargados hierroPre = new RecursosPrecargados(4, TRADUCCIONES_THEMA.getString("recurso.hierro"), true, true, false, true);
        RecursosPrecargados poblacionPre = new RecursosPrecargados(5, TRADUCCIONES_THEMA.getString("recurso.poblacion"), true, false, false, false);
        RecursosPrecargados felicidadPre = new RecursosPrecargados(6, TRADUCCIONES_THEMA.getString("recurso.felicidad"), true, false, true, true);
        RecursosPrecargados puntosInvestigacion = new RecursosPrecargados(7, TRADUCCIONES_THEMA.getString("recurso.investigacion"), false, true, false, true);
        RecursosPrecargados municionPre = new RecursosPrecargados(8, TRADUCCIONES_THEMA.getString("recurso.municion"), true, true, true, true);
        progresBar.setProgress(15);
        //TODO MENUS ESPECIALES
        //-1=noDanNiGeneranNada
        //0=EdificioNormal
        //1=NewCiudad
        //2=CreaSoldados
        //3=CreaMaquinas


        new EdificiosPreCargados(0, 0, false, false, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.parcelaConstruible"), TRADUCCIONES_THEMA.getString("edificio.descripcion.parcelaConstruible"), null, null, null, null);//NO MEJORABLE

        Map<Integer, Recursos> recursosBuildCastillo1 = new TreeMap<>();
        recursosBuildCastillo1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuildCastillo1.put(maderaPre.getId(), new Recursos(maderaPre, 5));
        recursosBuildCastillo1.put(piedraPre.getId(), new Recursos(piedraPre, 5));
        recursosBuildCastillo1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuildCastillo1.put(poblacionPre.getId(), new Recursos(poblacionPre, 5));
        //
        Map<Integer, Recursos> recursosProductoresCastillo1 = new TreeMap<>();
        recursosProductoresCastillo1.put(poblacionPre.getId(), new Recursos(poblacionPre, 5));
        recursosProductoresCastillo1.put(felicidadPre.getId(), new Recursos(felicidadPre, 5));
        //
        Map<Integer, Recursos> recursosAlmacenCastillo1 = new TreeMap<>();
        recursosAlmacenCastillo1.put(oroPre.getId(), new Recursos(oroPre, 100));
        recursosAlmacenCastillo1.put(maderaPre.getId(), new Recursos(maderaPre, 100));
        recursosAlmacenCastillo1.put(piedraPre.getId(), new Recursos(piedraPre, 100));
        recursosAlmacenCastillo1.put(comidaPre.getId(), new Recursos(comidaPre, 100));
        recursosAlmacenCastillo1.put(hierroPre.getId(), new Recursos(hierroPre, 100));
        recursosAlmacenCastillo1.put(poblacionPre.getId(), new Recursos(poblacionPre, 100));
        recursosAlmacenCastillo1.put(felicidadPre.getId(), new Recursos(felicidadPre, 100));
        recursosAlmacenCastillo1.put(puntosInvestigacion.getId(), new Recursos(puntosInvestigacion, 100));
        new EdificiosPreCargados(1, 0, false, false, 1, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.castillo"), TRADUCCIONES_THEMA.getString("edificio.descripcion.castillo"), recursosBuildCastillo1, recursosProductoresCastillo1, null, recursosAlmacenCastillo1);

        Map<Integer, Recursos> recursosBuildCastillo2 = new TreeMap<>();
        recursosBuildCastillo2.put(oroPre.getId(), new Recursos(oroPre, 100));
        recursosBuildCastillo2.put(maderaPre.getId(), new Recursos(maderaPre, 100));
        recursosBuildCastillo2.put(piedraPre.getId(), new Recursos(piedraPre, 100));
        recursosBuildCastillo2.put(hierroPre.getId(), new Recursos(hierroPre, 100));
        recursosBuildCastillo2.put(poblacionPre.getId(), new Recursos(poblacionPre, 100));
        //
        Map<Integer, Recursos> recursosProductoresCastillo2 = new TreeMap<>();
        recursosProductoresCastillo2.put(poblacionPre.getId(), new Recursos(poblacionPre, 10));
        recursosProductoresCastillo2.put(felicidadPre.getId(), new Recursos(felicidadPre, 10));
        //
        Map<Integer, Recursos> recursosAlmacenCastillo2 = new TreeMap<>();
        recursosAlmacenCastillo2.put(oroPre.getId(), new Recursos(oroPre, 600));
        recursosAlmacenCastillo2.put(maderaPre.getId(), new Recursos(maderaPre, 600));
        recursosAlmacenCastillo2.put(piedraPre.getId(), new Recursos(piedraPre, 600));
        recursosAlmacenCastillo2.put(comidaPre.getId(), new Recursos(comidaPre, 600));
        recursosAlmacenCastillo2.put(hierroPre.getId(), new Recursos(hierroPre, 600));
        recursosAlmacenCastillo2.put(poblacionPre.getId(), new Recursos(poblacionPre, 600));
        recursosAlmacenCastillo2.put(felicidadPre.getId(), new Recursos(felicidadPre, 600));
        recursosAlmacenCastillo2.put(puntosInvestigacion.getId(), new Recursos(puntosInvestigacion, 600));
        new EdificiosPreCargados(1, 1, false, true, 1, 1, 0, TRADUCCIONES_THEMA.getString("edificio.name.castillo"), TRADUCCIONES_THEMA.getString("edificio.descripcion.castillo"), recursosBuildCastillo2, recursosProductoresCastillo2, null, recursosAlmacenCastillo2);

        Map<Integer, Recursos> recursosBuildCastillo3 = new TreeMap<>();
        recursosBuildCastillo3.put(oroPre.getId(), new Recursos(oroPre, 200));
        recursosBuildCastillo3.put(maderaPre.getId(), new Recursos(maderaPre, 200));
        recursosBuildCastillo3.put(piedraPre.getId(), new Recursos(piedraPre, 200));
        recursosBuildCastillo3.put(hierroPre.getId(), new Recursos(hierroPre, 200));
        recursosBuildCastillo3.put(poblacionPre.getId(), new Recursos(poblacionPre, 200));
        //
        Map<Integer, Recursos> recursosProductoresCastillo3 = new TreeMap<>();
        recursosProductoresCastillo3.put(felicidadPre.getId(), new Recursos(poblacionPre, 30));
        recursosProductoresCastillo3.put(felicidadPre.getId(), new Recursos(felicidadPre, 30));
        //
        Map<Integer, Recursos> recursosAlmacenCastillo3 = new TreeMap<>();
        recursosAlmacenCastillo3.put(oroPre.getId(), new Recursos(oroPre, 1200));
        recursosAlmacenCastillo3.put(maderaPre.getId(), new Recursos(maderaPre, 1200));
        recursosAlmacenCastillo3.put(piedraPre.getId(), new Recursos(piedraPre, 1200));
        recursosAlmacenCastillo3.put(comidaPre.getId(), new Recursos(comidaPre, 1200));
        recursosAlmacenCastillo3.put(hierroPre.getId(), new Recursos(hierroPre, 1200));
        recursosAlmacenCastillo3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1200));
        recursosAlmacenCastillo3.put(felicidadPre.getId(), new Recursos(felicidadPre, 1200));
        recursosAlmacenCastillo3.put(puntosInvestigacion.getId(), new Recursos(puntosInvestigacion, 1200));


        new EdificiosPreCargados(1, 2, false, true, 1, 2, 0, TRADUCCIONES_THEMA.getString("edificio.name.castillo"), TRADUCCIONES_THEMA.getString("edificio.descripcion.castillo"), recursosBuildCastillo3, recursosProductoresCastillo3, null, recursosAlmacenCastillo3);
        //
        //
        //
        Map<Integer, Recursos> recursosBuildMuralla1 = new TreeMap<>();
        recursosBuildMuralla1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuildMuralla1.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuildMuralla1.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuildMuralla1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuildMuralla1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(2, 0, false, false, 2, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.muralla"), TRADUCCIONES_THEMA.getString("edificio.descripcion.muralla"), recursosBuildMuralla1, null, null, null);

        Map<Integer, Recursos> recursosBuildMuralla2 = new TreeMap<>();
        recursosBuildMuralla2.put(oroPre.getId(), new Recursos(oroPre, 10));
        recursosBuildMuralla2.put(maderaPre.getId(), new Recursos(maderaPre, 50));
        recursosBuildMuralla2.put(piedraPre.getId(), new Recursos(piedraPre, 100));
        recursosBuildMuralla2.put(hierroPre.getId(), new Recursos(hierroPre, 50));
        recursosBuildMuralla2.put(poblacionPre.getId(), new Recursos(poblacionPre, 30));
        new EdificiosPreCargados(2, 1, true, true, 2, 1, 0, TRADUCCIONES_THEMA.getString("edificio.name.muralla"), TRADUCCIONES_THEMA.getString("edificio.descripcion.muralla"), recursosBuildMuralla2, null, null, null);

        Map<Integer, Recursos> recursosBuildMuralla3 = new TreeMap<>();
        recursosBuildMuralla3.put(oroPre.getId(), new Recursos(oroPre, 15));
        recursosBuildMuralla3.put(maderaPre.getId(), new Recursos(maderaPre, 200));
        recursosBuildMuralla3.put(piedraPre.getId(), new Recursos(piedraPre, 1000));
        recursosBuildMuralla3.put(hierroPre.getId(), new Recursos(hierroPre, 100));
        recursosBuildMuralla3.put(poblacionPre.getId(), new Recursos(poblacionPre, 83));
        new EdificiosPreCargados(2, 2, true, true, 2, 2, 0, TRADUCCIONES_THEMA.getString("edificio.name.muralla"), TRADUCCIONES_THEMA.getString("edificio.descripcion.muralla"), recursosBuildMuralla3, null, null, null);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Almacen1 = new TreeMap<>();
        recursosBuild_Almacen1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Almacen1.put(maderaPre.getId(), new Recursos(maderaPre, 150));
        recursosBuild_Almacen1.put(piedraPre.getId(), new Recursos(piedraPre, 200));
        recursosBuild_Almacen1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Almacen1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosAlmacen_Almacen1 = new TreeMap<>();
        recursosAlmacen_Almacen1.put(oroPre.getId(), new Recursos(oroPre, 100));
        recursosAlmacen_Almacen1.put(maderaPre.getId(), new Recursos(maderaPre, 100));
        recursosAlmacen_Almacen1.put(piedraPre.getId(), new Recursos(piedraPre, 100));
        recursosAlmacen_Almacen1.put(comidaPre.getId(), new Recursos(comidaPre, 100));
        recursosAlmacen_Almacen1.put(hierroPre.getId(), new Recursos(hierroPre, 100));
        recursosAlmacen_Almacen1.put(poblacionPre.getId(), new Recursos(poblacionPre, 100));
        recursosAlmacen_Almacen1.put(felicidadPre.getId(), new Recursos(felicidadPre, 100));
        recursosAlmacen_Almacen1.put(puntosInvestigacion.getId(), new Recursos(puntosInvestigacion, 100));
        new EdificiosPreCargados(10, 0, true, true, 3, -1, 0, TRADUCCIONES_THEMA.getString("edificio.name.almacen"), TRADUCCIONES_THEMA.getString("edificio.descripcion.almacen"), recursosBuild_Almacen1, null, null, recursosAlmacen_Almacen1);

        Map<Integer, Recursos> recursosBuild_Almacen2 = new TreeMap<>();
        recursosBuild_Almacen2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Almacen2.put(maderaPre.getId(), new Recursos(maderaPre, 300));
        recursosBuild_Almacen2.put(piedraPre.getId(), new Recursos(piedraPre, 600));
        recursosBuild_Almacen2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Almacen2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosAlmacen_Almacen2 = new TreeMap<>();
        recursosAlmacen_Almacen2.put(oroPre.getId(), new Recursos(oroPre, 500));
        recursosAlmacen_Almacen2.put(maderaPre.getId(), new Recursos(maderaPre, 500));
        recursosAlmacen_Almacen2.put(piedraPre.getId(), new Recursos(piedraPre, 500));
        recursosAlmacen_Almacen2.put(comidaPre.getId(), new Recursos(comidaPre, 500));
        recursosAlmacen_Almacen2.put(hierroPre.getId(), new Recursos(hierroPre, 500));
        recursosAlmacen_Almacen2.put(poblacionPre.getId(), new Recursos(poblacionPre, 500));
        recursosAlmacen_Almacen2.put(felicidadPre.getId(), new Recursos(felicidadPre, 500));
        recursosAlmacen_Almacen2.put(puntosInvestigacion.getId(), new Recursos(puntosInvestigacion, 500));
        new EdificiosPreCargados(10, 1, true, true, 3, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.almacen"), TRADUCCIONES_THEMA.getString("edificio.descripcion.almacen"), recursosBuild_Almacen2, null, null, recursosAlmacen_Almacen2);

        Map<Integer, Recursos> recursosBuild_Almacen3 = new TreeMap<>();
        recursosBuild_Almacen3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Almacen3.put(maderaPre.getId(), new Recursos(maderaPre, 600));
        recursosBuild_Almacen3.put(piedraPre.getId(), new Recursos(piedraPre, 800));
        recursosBuild_Almacen3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Almacen3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosAlmacen_Almacen3 = new TreeMap<>();
        recursosAlmacen_Almacen3.put(oroPre.getId(), new Recursos(oroPre, 1300));
        recursosAlmacen_Almacen3.put(maderaPre.getId(), new Recursos(maderaPre, 1300));
        recursosAlmacen_Almacen3.put(piedraPre.getId(), new Recursos(piedraPre, 1300));
        recursosAlmacen_Almacen3.put(comidaPre.getId(), new Recursos(comidaPre, 1300));
        recursosAlmacen_Almacen3.put(hierroPre.getId(), new Recursos(hierroPre, 1300));
        recursosAlmacen_Almacen3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1300));
        recursosAlmacen_Almacen3.put(felicidadPre.getId(), new Recursos(felicidadPre, 1300));
        recursosAlmacen_Almacen3.put(puntosInvestigacion.getId(), new Recursos(puntosInvestigacion, 1300));
        new EdificiosPreCargados(10, 2, true, true, 3, 1, 0, TRADUCCIONES_THEMA.getString("edificio.name.almacen"), TRADUCCIONES_THEMA.getString("edificio.descripcion.almacen"), recursosBuild_Almacen3, null, null, recursosAlmacen_Almacen3);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Palacio1 = new TreeMap<>();
        recursosBuild_Palacio1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Palacio1.put(maderaPre.getId(), new Recursos(maderaPre, 150));
        recursosBuild_Palacio1.put(piedraPre.getId(), new Recursos(piedraPre, 200));
        recursosBuild_Palacio1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Palacio1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(11, 0, true, true, 1, 0, 1, TRADUCCIONES_THEMA.getString("edificio.name.palacio"), TRADUCCIONES_THEMA.getString("edificio.descripcion.palacio"), recursosBuild_Palacio1, null, null, null);

        Map<Integer, Recursos> recursosBuild_Palacio2 = new TreeMap<>();
        recursosBuild_Palacio2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Palacio2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Palacio2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Palacio2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Palacio2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(11, 1, true, true, 1, 2, 1, TRADUCCIONES_THEMA.getString("edificio.name.palacio"), TRADUCCIONES_THEMA.getString("edificio.descripcion.palacio"), recursosBuild_Palacio2, null, null, null);

        Map<Integer, Recursos> recursosBuild_Palacio3 = new TreeMap<>();
        recursosBuild_Palacio3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Palacio3.put(maderaPre.getId(), new Recursos(maderaPre, 468));
        recursosBuild_Palacio3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Palacio3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Palacio3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(11, 2, true, true, 1, 2, 1, TRADUCCIONES_THEMA.getString("edificio.name.palacio"), TRADUCCIONES_THEMA.getString("edificio.descripcion.palacio"), recursosBuild_Palacio3, null, null, null);
        progresBar.setProgress(20);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_CCientifico1 = new TreeMap<>();
        recursosBuild_CCientifico1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_CCientifico1.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_CCientifico1.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_CCientifico1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_CCientifico1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosProductores_CCientifico1 = new TreeMap<>();
        recursosProductores_CCientifico1.put(puntosInvestigacion.getId(), new Recursos(puntosInvestigacion, 10));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_CCientifico1 = new ArrayList<>();
        recursosCosteXtime_coste_CCientifico1.add(new Recursos(poblacionPre, 10));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_CCientifico1 = new TreeMap<>();
        recursosCosteXtime_produce_CCientifico1.put(puntosInvestigacion, recursosCosteXtime_coste_CCientifico1);
        new EdificiosPreCargados(12, 0, true, true, 1, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.centroCientifico"), TRADUCCIONES_THEMA.getString("edificio.descripcion.centroCientifico"), recursosBuild_CCientifico1, recursosProductores_CCientifico1, recursosCosteXtime_produce_CCientifico1, null);

        Map<Integer, Recursos> recursosBuild_CCientifico2 = new TreeMap<>();
        recursosBuild_CCientifico2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_CCientifico2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_CCientifico2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_CCientifico2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_CCientifico2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosProductores_CCientifico2 = new TreeMap<>();
        recursosProductores_CCientifico2.put(puntosInvestigacion.getId(), new Recursos(puntosInvestigacion, 15));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_CCientifico2 = new ArrayList<>();
        recursosCosteXtime_coste_CCientifico2.add(new Recursos(poblacionPre, 10));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_CCientifico2 = new TreeMap<>();
        recursosCosteXtime_produce_CCientifico2.put(puntosInvestigacion, recursosCosteXtime_coste_CCientifico2);
        new EdificiosPreCargados(12, 1, true, true, 1, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.centroCientifico"), TRADUCCIONES_THEMA.getString("edificio.descripcion.centroCientifico"), recursosBuild_CCientifico2, recursosProductores_CCientifico2, recursosCosteXtime_produce_CCientifico2, null);


        Map<Integer, Recursos> recursosBuild_CCientifico3 = new TreeMap<>();
        recursosBuild_CCientifico3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_CCientifico3.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_CCientifico3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_CCientifico3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_CCientifico3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosProductores_CCientifico3 = new TreeMap<>();
        recursosProductores_CCientifico3.put(puntosInvestigacion.getId(), new Recursos(puntosInvestigacion, 40));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_CCientifico3 = new ArrayList<>();
        recursosCosteXtime_coste_CCientifico3.add(new Recursos(poblacionPre, 15));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_CCientifico3 = new TreeMap<>();
        recursosCosteXtime_produce_CCientifico3.put(puntosInvestigacion, recursosCosteXtime_coste_CCientifico3);
        new EdificiosPreCargados(12, 2, true, true, 1, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.centroCientifico"), TRADUCCIONES_THEMA.getString("edificio.descripcion.centroCientifico"), recursosBuild_CCientifico3, recursosProductores_CCientifico3, recursosCosteXtime_produce_CCientifico3, null);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Cantera1 = new TreeMap<>();
        recursosBuild_Cantera1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Cantera1.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Cantera1.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Cantera1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Cantera1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosProductores_Cantera1 = new TreeMap<>();
        recursosProductores_Cantera1.put(piedraPre.getId(), new Recursos(piedraPre, 10));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Cantera1 = new ArrayList<>();
        recursosCosteXtime_coste_Cantera1.add(new Recursos(poblacionPre, 10));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Cantera1 = new TreeMap<>();
        recursosCosteXtime_produce_Cantera1.put(piedraPre, recursosCosteXtime_coste_Cantera1);
        new EdificiosPreCargados(13, 0, true, true, 1, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.cantera"), TRADUCCIONES_THEMA.getString("edificio.descripcion.cantera"), recursosBuild_Cantera1, recursosProductores_Cantera1, recursosCosteXtime_produce_Cantera1, null);

        Map<Integer, Recursos> recursosBuild_Cantera2 = new TreeMap<>();
        recursosBuild_Cantera2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Cantera2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Cantera2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Cantera2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Cantera2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosProductores_Cantera2 = new TreeMap<>();
        recursosProductores_Cantera2.put(piedraPre.getId(), new Recursos(piedraPre, 10));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Cantera2 = new ArrayList<>();
        recursosCosteXtime_coste_Cantera2.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Cantera2 = new TreeMap<>();
        recursosCosteXtime_produce_Cantera2.put(piedraPre, recursosCosteXtime_coste_Cantera2);
        new EdificiosPreCargados(13, 1, true, true, 2, 1, 0, TRADUCCIONES_THEMA.getString("edificio.name.cantera"), TRADUCCIONES_THEMA.getString("edificio.descripcion.cantera"), recursosBuild_Cantera2, recursosProductores_Cantera2, recursosCosteXtime_produce_Cantera2, null);

        Map<Integer, Recursos> recursosBuild_Cantera3 = new TreeMap<>();
        recursosBuild_Cantera3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Cantera3.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Cantera3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Cantera3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Cantera3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosProductores_Cantera3 = new TreeMap<>();
        recursosProductores_Cantera3.put(piedraPre.getId(), new Recursos(piedraPre, 10));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Cantera3 = new ArrayList<>();
        recursosCosteXtime_coste_Cantera3.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Cantera3 = new TreeMap<>();
        recursosCosteXtime_produce_Cantera3.put(piedraPre, recursosCosteXtime_coste_Cantera3);
        new EdificiosPreCargados(13, 2, true, true, 3, 2, 0, TRADUCCIONES_THEMA.getString("edificio.name.cantera"), TRADUCCIONES_THEMA.getString("edificio.descripcion.cantera"), recursosBuild_Cantera3, recursosProductores_Cantera3, recursosCosteXtime_produce_Cantera3, null);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Aserradero1 = new TreeMap<>();
        recursosBuild_Aserradero1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Aserradero1.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Aserradero1.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Aserradero1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Aserradero1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosProductores_Aserradero1 = new TreeMap<>();
        recursosProductores_Aserradero1.put(maderaPre.getId(), new Recursos(maderaPre, 10));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Aserradero1 = new ArrayList<>();
        recursosCosteXtime_coste_Aserradero1.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Aserradero1 = new TreeMap<>();
        recursosCosteXtime_produce_Aserradero1.put(maderaPre, recursosCosteXtime_coste_Aserradero1);
        new EdificiosPreCargados(14, 0, true, true, 1, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.aserradero"), TRADUCCIONES_THEMA.getString("edificio.descripcion.aserradero"), recursosBuild_Aserradero1, recursosProductores_Aserradero1, recursosCosteXtime_produce_Aserradero1, null);

        Map<Integer, Recursos> recursosBuild_Aserradero2 = new TreeMap<>();
        recursosBuild_Aserradero2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Aserradero2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Aserradero2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Aserradero2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Aserradero2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosProductores_Aserradero2 = new TreeMap<>();
        recursosProductores_Aserradero2.put(maderaPre.getId(), new Recursos(maderaPre, 10));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Aserradero2 = new ArrayList<>();
        recursosCosteXtime_coste_Aserradero2.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Aserradero2 = new TreeMap<>();
        recursosCosteXtime_produce_Aserradero2.put(maderaPre, recursosCosteXtime_coste_Aserradero2);
        new EdificiosPreCargados(14, 1, true, true, 2, 1, 0, TRADUCCIONES_THEMA.getString("edificio.name.aserradero"), TRADUCCIONES_THEMA.getString("edificio.descripcion.aserradero"), recursosBuild_Aserradero2, recursosProductores_Aserradero2, recursosCosteXtime_produce_Aserradero2, null);

        Map<Integer, Recursos> recursosBuild_Aserradero3 = new TreeMap<>();
        recursosBuild_Aserradero3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Aserradero3.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Aserradero3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Aserradero3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Aserradero3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosProductores_Aserradero3 = new TreeMap<>();
        recursosProductores_Aserradero3.put(maderaPre.getId(), new Recursos(maderaPre, 10));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Aserradero3 = new ArrayList<>();
        recursosCosteXtime_coste_Aserradero3.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Aserradero3 = new TreeMap<>();
        recursosCosteXtime_produce_Aserradero3.put(maderaPre, recursosCosteXtime_coste_Aserradero3);
        new EdificiosPreCargados(14, 2, true, true, 3, 1, 0, TRADUCCIONES_THEMA.getString("edificio.name.aserradero"), TRADUCCIONES_THEMA.getString("edificio.descripcion.aserradero"), recursosBuild_Aserradero3, recursosProductores_Aserradero3, recursosCosteXtime_produce_Aserradero3, null);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Fundicion1 = new TreeMap<>();
        recursosBuild_Fundicion1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Fundicion1.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Fundicion1.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Fundicion1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Fundicion1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosProductores_Fundicion1 = new TreeMap<>();
        recursosProductores_Fundicion1.put(hierroPre.getId(), new Recursos(hierroPre, 10));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Fundicion1 = new ArrayList<>();
        recursosCosteXtime_coste_Fundicion1.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Fundicion1 = new TreeMap<>();
        recursosCosteXtime_produce_Fundicion1.put(hierroPre, recursosCosteXtime_coste_Fundicion1);
        new EdificiosPreCargados(15, 0, true, true, 1, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.fundicion"), TRADUCCIONES_THEMA.getString("edificio.descripcion.fundicion"), recursosBuild_Fundicion1, recursosProductores_Fundicion1, recursosCosteXtime_produce_Fundicion1, null);

        Map<Integer, Recursos> recursosBuild_Fundicion2 = new TreeMap<>();
        recursosBuild_Fundicion2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Fundicion2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Fundicion2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Fundicion2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Fundicion2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosProductores_Fundicion2 = new TreeMap<>();
        recursosProductores_Fundicion2.put(hierroPre.getId(), new Recursos(hierroPre, 10));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Fundicion2 = new ArrayList<>();
        recursosCosteXtime_coste_Fundicion2.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Fundicion2 = new TreeMap<>();
        recursosCosteXtime_produce_Fundicion2.put(hierroPre, recursosCosteXtime_coste_Fundicion2);
        new EdificiosPreCargados(15, 1, true, true, 1, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.fundicion"), TRADUCCIONES_THEMA.getString("edificio.descripcion.fundicion"), recursosBuild_Fundicion2, recursosProductores_Fundicion2, recursosCosteXtime_produce_Fundicion2, null);

        Map<Integer, Recursos> recursosBuild_Fundicion3 = new TreeMap<>();
        recursosBuild_Fundicion3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Fundicion3.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Fundicion3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Fundicion3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Fundicion3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosProductores_Fundicion3 = new TreeMap<>();
        recursosProductores_Fundicion3.put(hierroPre.getId(), new Recursos(hierroPre, 10));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Fundicion3 = new ArrayList<>();
        recursosCosteXtime_coste_Fundicion3.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Fundicion3 = new TreeMap<>();
        recursosCosteXtime_produce_Fundicion3.put(hierroPre, recursosCosteXtime_coste_Fundicion3);
        new EdificiosPreCargados(15, 2, true, true, 1, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.fundicion"), TRADUCCIONES_THEMA.getString("edificio.descripcion.fundicion"), recursosBuild_Fundicion3, recursosProductores_Fundicion3, recursosCosteXtime_produce_Fundicion3, null);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Granja1 = new TreeMap<>();
        recursosBuild_Granja1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Granja1.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Granja1.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Granja1.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Granja1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Granja1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosProductores_Granja1 = new TreeMap<>();
        recursosProductores_Granja1.put(comidaPre.getId(), new Recursos(comidaPre, 10));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Granja1 = new ArrayList<>();
        recursosCosteXtime_coste_Granja1.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_Granja1 = new TreeMap<>();
        recursosCosteXtime_Granja1.put(comidaPre, recursosCosteXtime_coste_Granja1);
        new EdificiosPreCargados(16, 0, true, true, 1, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.granja"), TRADUCCIONES_THEMA.getString("edificio.descripcion.granja"), recursosBuild_Granja1, recursosProductores_Granja1, recursosCosteXtime_Granja1, null);

        Map<Integer, Recursos> recursosBuild_Granja2 = new TreeMap<>();
        recursosBuild_Granja2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Granja2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Granja2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Granja2.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Granja2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Granja2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosProductores_Granja2 = new TreeMap<>();
        recursosProductores_Granja2.put(comidaPre.getId(), new Recursos(comidaPre, 10));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Granja2 = new ArrayList<>();
        recursosCosteXtime_coste_Granja2.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_Granja2 = new TreeMap<>();
        recursosCosteXtime_Granja2.put(comidaPre, recursosCosteXtime_coste_Granja2);
        new EdificiosPreCargados(16, 1, true, true, 1, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.granja"), TRADUCCIONES_THEMA.getString("edificio.descripcion.granja"), recursosBuild_Granja2, recursosProductores_Granja2, recursosCosteXtime_Granja2, null);

        Map<Integer, Recursos> recursosBuild_Granja3 = new TreeMap<>();
        recursosBuild_Granja3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Granja3.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Granja3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Granja3.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Granja3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Granja3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosProductores_Granja3 = new TreeMap<>();
        recursosProductores_Granja3.put(comidaPre.getId(), new Recursos(comidaPre, 10));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Granja3 = new ArrayList<>();
        recursosCosteXtime_coste_Granja3.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_Granja3 = new TreeMap<>();
        recursosCosteXtime_Granja3.put(comidaPre, recursosCosteXtime_coste_Granja3);
        new EdificiosPreCargados(16, 2, true, true, 1, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.granja"), TRADUCCIONES_THEMA.getString("edificio.descripcion.granja"), recursosBuild_Granja3, recursosProductores_Granja3, recursosCosteXtime_Granja3, null);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Taberna1 = new TreeMap<>();
        recursosBuild_Taberna1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Taberna1.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Taberna1.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Taberna1.put(comidaPre.getId(), new Recursos(comidaPre, 100));
        recursosBuild_Taberna1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Taberna1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosProductores_Taberna1 = new TreeMap<>();
        recursosProductores_Taberna1.put(felicidadPre.getId(), new Recursos(felicidadPre, 10));
        recursosProductores_Taberna1.put(poblacionPre.getId(), new Recursos(poblacionPre, 5));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Taberna1 = new ArrayList<>();
        recursosCosteXtime_coste_Taberna1.add(new Recursos(poblacionPre, 20));
        recursosCosteXtime_coste_Taberna1.add(new Recursos(comidaPre, 15));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteX_productor_Taberna1 = new TreeMap<>();
        recursosCosteX_productor_Taberna1.put(felicidadPre, recursosCosteXtime_coste_Taberna1);
        new EdificiosPreCargados(17, 0, true, true, 1, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.taberna"), TRADUCCIONES_THEMA.getString("edificio.descripcion.taberna"), recursosBuild_Taberna1, recursosProductores_Taberna1, recursosCosteX_productor_Taberna1, null);

        Map<Integer, Recursos> recursosBuild_Taberna2 = new TreeMap<>();
        recursosBuild_Taberna2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Taberna2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Taberna2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Taberna2.put(comidaPre.getId(), new Recursos(comidaPre, 500));
        recursosBuild_Taberna2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Taberna2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosProductores_Taberna2 = new TreeMap<>();
        recursosProductores_Taberna2.put(felicidadPre.getId(), new Recursos(felicidadPre, 20));
        recursosProductores_Taberna2.put(poblacionPre.getId(), new Recursos(poblacionPre, 10));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Taberna2 = new ArrayList<>();
        recursosCosteXtime_coste_Taberna2.add(new Recursos(poblacionPre, 20));
        recursosCosteXtime_coste_Taberna2.add(new Recursos(comidaPre, 15));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteX_productor_Taberna2 = new TreeMap<>();
        recursosCosteX_productor_Taberna2.put(felicidadPre, recursosCosteXtime_coste_Taberna2);
        new EdificiosPreCargados(17, 1, true, true, 1, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.taberna"), TRADUCCIONES_THEMA.getString("edificio.descripcion.taberna"), recursosBuild_Taberna2, recursosProductores_Taberna2, recursosCosteX_productor_Taberna2, null);

        Map<Integer, Recursos> recursosBuild_Taberna3 = new TreeMap<>();
        recursosBuild_Taberna3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Taberna3.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Taberna3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Taberna3.put(comidaPre.getId(), new Recursos(comidaPre, 1100));
        recursosBuild_Taberna3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Taberna3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosProductores_Taberna3 = new TreeMap<>();
        recursosProductores_Taberna1.put(felicidadPre.getId(), new Recursos(felicidadPre, 30));
        recursosProductores_Taberna1.put(poblacionPre.getId(), new Recursos(poblacionPre, 15));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Taberna3 = new ArrayList<>();
        recursosCosteXtime_coste_Taberna3.add(new Recursos(poblacionPre, 20));
        recursosCosteXtime_coste_Taberna3.add(new Recursos(comidaPre, 15));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteX_productor_Taberna3 = new TreeMap<>();
        recursosCosteX_productor_Taberna3.put(felicidadPre, recursosCosteXtime_coste_Taberna3);
        new EdificiosPreCargados(17, 2, true, true, 1, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.taberna"), TRADUCCIONES_THEMA.getString("edificio.descripcion.taberna"), recursosBuild_Taberna3, recursosProductores_Taberna3, recursosCosteX_productor_Taberna3, null);
        progresBar.setProgress(30);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Puerto1 = new TreeMap<>();
        recursosBuild_Puerto1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Puerto1.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Puerto1.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Puerto1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Puerto1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(18, 0, false, false, 1, 0, 4, TRADUCCIONES_THEMA.getString("edificio.name.puerto"), TRADUCCIONES_THEMA.getString("edificio.descripcion.puerto"), recursosBuild_Puerto1, null, null, null);

        Map<Integer, Recursos> recursosBuild_Puerto2 = new TreeMap<>();
        recursosBuild_Puerto2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Puerto2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Puerto2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Puerto2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Puerto2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(18, 1, true, true, 1, 0, 4, TRADUCCIONES_THEMA.getString("edificio.name.puerto"), TRADUCCIONES_THEMA.getString("edificio.descripcion.puerto"), recursosBuild_Puerto2, null, null, null);

        Map<Integer, Recursos> recursosBuild_Puerto3 = new TreeMap<>();
        recursosBuild_Puerto3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Puerto3.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Puerto3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Puerto3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Puerto3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(18, 2, true, true, 1, 0, 4, TRADUCCIONES_THEMA.getString("edificio.name.puerto"), TRADUCCIONES_THEMA.getString("edificio.descripcion.puerto"), recursosBuild_Puerto3, null, null, null);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Cuartel1 = new TreeMap<>();
        recursosBuild_Cuartel1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Cuartel1.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Cuartel1.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Cuartel1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Cuartel1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(19, 0, true, true, 1, 0, 2, TRADUCCIONES_THEMA.getString("edificio.name.cuartel"), TRADUCCIONES_THEMA.getString("edificio.descripcion.cuartel"), recursosBuild_Cuartel1, null, null, null);

        Map<Integer, Recursos> recursosBuild_Cuartel2 = new TreeMap<>();
        recursosBuild_Cuartel2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Cuartel2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Cuartel2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Cuartel2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Cuartel2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(19, 1, true, true, 1, 0, 2, TRADUCCIONES_THEMA.getString("edificio.name.cuartel"), TRADUCCIONES_THEMA.getString("edificio.descripcion.cuartel"), recursosBuild_Cuartel2, null, null, null);

        Map<Integer, Recursos> recursosBuild_Cuartel3 = new TreeMap<>();
        recursosBuild_Cuartel3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Cuartel3.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Cuartel3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Cuartel3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Cuartel3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(19, 2, true, true, 1, 0, 2, TRADUCCIONES_THEMA.getString("edificio.name.cuartel"), TRADUCCIONES_THEMA.getString("edificio.descripcion.cuartel"), recursosBuild_Cuartel3, null, null, null);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Asedio1 = new TreeMap<>();
        recursosBuild_Asedio1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Asedio1.put(maderaPre.getId(), new Recursos(maderaPre, 1000));
        recursosBuild_Asedio1.put(piedraPre.getId(), new Recursos(piedraPre, 2000));
        recursosBuild_Asedio1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Asedio1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(20, 0, true, true, 1, 0, 3, TRADUCCIONES_THEMA.getString("edificio.name.tallerArmasAsedio"), TRADUCCIONES_THEMA.getString("edificio.descripcion.tallerArmasDeAsedio"), recursosBuild_Asedio1, null, null, null);

        Map<Integer, Recursos> recursosBuild_Asedio2 = new TreeMap<>();
        recursosBuild_Asedio2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Asedio2.put(maderaPre.getId(), new Recursos(maderaPre, 1500));
        recursosBuild_Asedio2.put(piedraPre.getId(), new Recursos(piedraPre, 2500));
        recursosBuild_Asedio2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Asedio2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(20, 1, true, true, 1, 0, 3, TRADUCCIONES_THEMA.getString("edificio.name.tallerArmasAsedio"), TRADUCCIONES_THEMA.getString("edificio.descripcion.tallerArmasDeAsedio"), recursosBuild_Asedio2, null, null, null);

        Map<Integer, Recursos> recursosBuild_Asedio3 = new TreeMap<>();
        recursosBuild_Asedio3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Asedio3.put(maderaPre.getId(), new Recursos(maderaPre, 5000));
        recursosBuild_Asedio3.put(piedraPre.getId(), new Recursos(piedraPre, 3000));
        recursosBuild_Asedio3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Asedio3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(20, 2, true, true, 1, 0, 3, TRADUCCIONES_THEMA.getString("edificio.name.tallerArmasAsedio"), TRADUCCIONES_THEMA.getString("edificio.descripcion.tallerArmasDeAsedio"), recursosBuild_Asedio3, null, null, null);

        //
        //
        progresBar.setProgress(40);
        //
        //

        List<Recursos> recursosList0 = new ArrayList<>();
        recursosList0.add(new Recursos(oroPre, 1));
        recursosList0.add(new Recursos(maderaPre, 5));
        recursosList0.add(new Recursos(comidaPre, 10));
        recursosList0.add(new Recursos(hierroPre, 3));
        recursosList0.add(new Recursos(poblacionPre, 1));
        new UnidadesPreCargadas(0, TRADUCCIONES_THEMA.getString("unidades.soldados.espadachines"), 0, recursosList0, 0);
        List<Recursos> recursosList1 = new ArrayList<>();
        recursosList1.add(new Recursos(oroPre, 1));
        recursosList1.add(new Recursos(maderaPre, 10));
        recursosList1.add(new Recursos(comidaPre, 10));
        recursosList1.add(new Recursos(hierroPre, 3));
        recursosList1.add(new Recursos(poblacionPre, 1));
        new UnidadesPreCargadas(1, TRADUCCIONES_THEMA.getString("unidades.soldados.lanceros"), 0, recursosList1, 0);
        List<Recursos> recursosList2 = new ArrayList<>();
        recursosList2.add(new Recursos(oroPre, 1));
        recursosList2.add(new Recursos(maderaPre, 50));
        recursosList2.add(new Recursos(comidaPre, 10));
        recursosList2.add(new Recursos(hierroPre, 8));
        recursosList2.add(new Recursos(municionPre, 1));
        recursosList2.add(new Recursos(poblacionPre, 2));
        new UnidadesPreCargadas(2, TRADUCCIONES_THEMA.getString("unidades.soldados.arqueros"), 100, recursosList2, 0);
        List<Recursos> recursosList3 = new ArrayList<>();
        recursosList3.add(new Recursos(oroPre, 3));
        recursosList3.add(new Recursos(maderaPre, 10));
        recursosList3.add(new Recursos(piedraPre, 5));
        recursosList3.add(new Recursos(comidaPre, 20));
        recursosList3.add(new Recursos(hierroPre, 50));
        recursosList3.add(new Recursos(poblacionPre, 3));
        new UnidadesPreCargadas(3, TRADUCCIONES_THEMA.getString("unidades.soldados.caballeros"), 50, recursosList3, 0);
        List<Recursos> recursosList4 = new ArrayList<>();
        recursosList4.add(new Recursos(oroPre, 10));
        recursosList4.add(new Recursos(maderaPre, 100));
        recursosList4.add(new Recursos(piedraPre, 200));
        recursosList4.add(new Recursos(hierroPre, 5));
        recursosList4.add(new Recursos(poblacionPre, 5));
        new UnidadesPreCargadas(4, TRADUCCIONES_THEMA.getString("unidades.asedio.catapultas"), 50, recursosList4, 5);


        //
        //
        //
        //
        progresBar.setProgress(50);
        //TODO En pantalla
        Clan clan = new Clan("Los mejores", 10);
        Clan clan2 = new Clan("Los peores", 10);
        clan2.setContrasenya("1234");
        ArrayList<Recursos> jugador1ListaRecuros = new ArrayList<>();
        jugador1ListaRecuros.add(new Recursos(puntosInvestigacion, 5));
        Jugador jugador = new Jugador(0l,"pepito", jugador1ListaRecuros);
        setJugadorPrimaryStageController(jugador);
        setClanPrimaryStageController(clan);
        progresBar.setProgress(60);
        ArrayList<Recursos> jugador2ListaRecuros = new ArrayList<>();
        jugador2ListaRecuros.add(new Recursos(puntosInvestigacion, 25));
        Jugador jugador2 = new Jugador(1l,"juan", jugador2ListaRecuros);
        ArrayList<Recursos> jugador3ListaRecuros = new ArrayList<>();
        jugador3ListaRecuros.add(new Recursos(puntosInvestigacion, 80));
        Jugador jugador3 = new Jugador(2l,"pedro", jugador3ListaRecuros);
        Jugador jugador4 = new Jugador(3l,"julito", jugador3ListaRecuros);
        clan2.setCreador(jugador4);
        clan2.addJugadorClan(jugador4);
        progresBar.setProgress(70);
        clan.addJugadorClan(jugador);
        clan.setCreador(jugador);
        clan.addJugadorClan(jugador3);


        elTemaSeleccionado.espias.put(1, 10);
        elTemaSeleccionado.espias.put(2, 25);
        elTemaSeleccionado.espias.put(5, 50);
        elTemaSeleccionado.espias.put(10, 80);
        progresBar.setProgress(80);

        //TODO TEST
        Comercio comercio0 = new Comercio(new Recursos(oroPre, 5), new Recursos(maderaPre, 300), jugador);
        Comercio.data.add(comercio0);
        Comercio comercio1 = new Comercio(new Recursos(hierroPre, 6), new Recursos(comidaPre, 11), jugador2);
        Comercio.data.add(comercio1);
        Comercio comercio2 = new Comercio(new Recursos(oroPre, 7), new Recursos(maderaPre, 12), jugador3);
        Comercio.data.add(comercio2);
        Comercio comercio3 = new Comercio(new Recursos(hierroPre, 7000000), new Recursos(maderaPre, 15), jugador4);
        Comercio.data.add(comercio3);

/*
        Timeline thirtySeconds = new Timeline(new KeyFrame(Duration.seconds(30), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Thread Running --> PANTALLA INICIAL CONTROLLER: ADD Comercio");
                Comercio comercio5 = new Comercio( new Recursos(maderaPre, 7), new Recursos(maderaPre, 15), jugador);
                Comercio.data.add(comercio5);
                Comercio comercio6 = new Comercio( new Recursos(maderaPre, 7), new Recursos(maderaPre, 15), jugador2);
                Comercio.data.add(comercio6);
            }
        }));
        thirtySeconds.setCycleCount(Timeline.INDEFINITE);
        thirtySeconds.play();
*/


        Thread thread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(25000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread Running --> PANTALLA INICIAL CONTROLLER: REMOVE Comercio");
                Comercio.data.remove(comercio1);
                Comercio.data.remove(comercio2);
                Comercio comercio4 = new Comercio(new Recursos(piedraPre, 7), new Recursos(maderaPre, 15), jugador3);
                Comercio.data.add(comercio4);
            }
        };
        thread.start();
        Thread thread2 = new Thread() {
            public void run() {
                try {
                    Thread.sleep(40000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("CLAN");
                clan.removeJugadorClan(3);
                try {
                    Thread.sleep(40000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("CLAN2");
                clan2.addJugadorClan(3);
            }
        };
        thread2.start();


    }

    static void callbdCuerpoHumano(ProgressBar progresBar) {
        RecursosPrecargados oxigenoPre = new RecursosPrecargados(0, TRADUCCIONES_THEMA.getString("recurso.oro"), true, true, false, true);
        RecursosPrecargados CarbonoPre = new RecursosPrecargados(1, TRADUCCIONES_THEMA.getString("recurso.madera"), true, true, false, true);
        RecursosPrecargados hidrogenoPre = new RecursosPrecargados(2, TRADUCCIONES_THEMA.getString("recurso.piedra"), true, true, false, true);
        RecursosPrecargados nitrogenoPre = new RecursosPrecargados(3, TRADUCCIONES_THEMA.getString("recurso.comida"), true, true, false, true);
        RecursosPrecargados fosforoPre = new RecursosPrecargados(4, TRADUCCIONES_THEMA.getString("recurso.hierro"), true, true, false, true);
        RecursosPrecargados calcioPre = new RecursosPrecargados(5, TRADUCCIONES_THEMA.getString("recurso.poblacion"), true, false, false, false);
        RecursosPrecargados PotasioPre = new RecursosPrecargados(6, TRADUCCIONES_THEMA.getString("recurso.felicidad"), true, false, true, true);
        progresBar.setProgress(15);
        //TODO MENUS ESPECIALES
        //-1=noDanNiGeneranNada
        //0=EdificioNormal
        //1=NewCiudad
        //2=CreaSoldados
        //3=CreaMaquinas


        new EdificiosPreCargados(0, 0, false, false, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.parcelaConstruible"), TRADUCCIONES_THEMA.getString("edificio.descripcion.parcelaConstruible"), null, null, null, null);//NO MEJORABLE

        Map<Integer, Recursos> recursosBuildCastillo1 = new TreeMap<>();
        recursosBuildCastillo1.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuildCastillo1.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 5));
        recursosBuildCastillo1.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 5));
        recursosBuildCastillo1.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuildCastillo1.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuildCastillo1.put(calcioPre.getId(), new Recursos(calcioPre, 5));
        //
        Map<Integer, Recursos> recursosProductoresCastillo1 = new TreeMap<>();
        recursosProductoresCastillo1.put(calcioPre.getId(), new Recursos(calcioPre, 10));
        recursosProductoresCastillo1.put(PotasioPre.getId(), new Recursos(PotasioPre, 10));
        //
        Map<Integer, Recursos> recursosAlmacenCastillo1 = new TreeMap<>();
        recursosAlmacenCastillo1.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 100));
        recursosAlmacenCastillo1.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 100));
        recursosAlmacenCastillo1.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 100));
        recursosAlmacenCastillo1.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 100));
        recursosAlmacenCastillo1.put(fosforoPre.getId(), new Recursos(fosforoPre, 100));
        recursosAlmacenCastillo1.put(calcioPre.getId(), new Recursos(calcioPre, 100));
        recursosAlmacenCastillo1.put(PotasioPre.getId(), new Recursos(PotasioPre, 100));
        new EdificiosPreCargados(1, 0, false, false, 1, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.castillo"), TRADUCCIONES_THEMA.getString("edificio.descripcion.castillo"), recursosBuildCastillo1, recursosProductoresCastillo1, null, recursosAlmacenCastillo1);

        Map<Integer, Recursos> recursosBuildCastillo2 = new TreeMap<>();
        recursosBuildCastillo2.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuildCastillo2.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 5));
        recursosBuildCastillo2.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 5));
        recursosBuildCastillo2.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuildCastillo2.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuildCastillo2.put(calcioPre.getId(), new Recursos(calcioPre, 5));
        //
        Map<Integer, Recursos> recursosProductoresCastillo2 = new TreeMap<>();
        recursosProductoresCastillo2.put(calcioPre.getId(), new Recursos(calcioPre, 10));
        recursosProductoresCastillo2.put(PotasioPre.getId(), new Recursos(PotasioPre, 10));
        //
        Map<Integer, Recursos> recursosAlmacenCastillo2 = new TreeMap<>();
        recursosAlmacenCastillo2.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 100));
        recursosAlmacenCastillo2.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 100));
        recursosAlmacenCastillo2.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 100));
        recursosAlmacenCastillo2.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 100));
        recursosAlmacenCastillo2.put(fosforoPre.getId(), new Recursos(fosforoPre, 100));
        recursosAlmacenCastillo2.put(calcioPre.getId(), new Recursos(calcioPre, 100));
        recursosAlmacenCastillo2.put(PotasioPre.getId(), new Recursos(PotasioPre, 100));
        new EdificiosPreCargados(1, 1, false, true, 1, 1, 0, TRADUCCIONES_THEMA.getString("edificio.name.castillo"), TRADUCCIONES_THEMA.getString("edificio.descripcion.castillo"), recursosBuildCastillo2, recursosProductoresCastillo2, null, recursosAlmacenCastillo2);

        Map<Integer, Recursos> recursosBuildCastillo3 = new TreeMap<>();
        recursosBuildCastillo3.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuildCastillo3.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 5));
        recursosBuildCastillo3.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 5));
        recursosBuildCastillo3.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuildCastillo3.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuildCastillo3.put(calcioPre.getId(), new Recursos(calcioPre, 5));
        //
        Map<Integer, Recursos> recursosProductoresCastillo3 = new TreeMap<>();
        recursosProductoresCastillo3.put(calcioPre.getId(), new Recursos(calcioPre, 10));
        recursosProductoresCastillo3.put(PotasioPre.getId(), new Recursos(PotasioPre, 10));
        //
        Map<Integer, Recursos> recursosAlmacenCastillo3 = new TreeMap<>();
        recursosAlmacenCastillo3.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 100));
        recursosAlmacenCastillo3.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 100));
        recursosAlmacenCastillo3.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 100));
        recursosAlmacenCastillo3.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 100));
        recursosAlmacenCastillo3.put(fosforoPre.getId(), new Recursos(fosforoPre, 100));
        recursosAlmacenCastillo3.put(calcioPre.getId(), new Recursos(calcioPre, 100));
        recursosAlmacenCastillo3.put(PotasioPre.getId(), new Recursos(PotasioPre, 100));
        new EdificiosPreCargados(1, 2, false, true, 1, 2, 0, TRADUCCIONES_THEMA.getString("edificio.name.castillo"), TRADUCCIONES_THEMA.getString("edificio.descripcion.castillo"), recursosBuildCastillo3, recursosProductoresCastillo3, null, recursosAlmacenCastillo3);
        //
        //
        //
        Map<Integer, Recursos> recursosBuildMuralla1 = new TreeMap<>();
        recursosBuildMuralla1.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuildMuralla1.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuildMuralla1.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuildMuralla1.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuildMuralla1.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuildMuralla1.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        new EdificiosPreCargados(2, 0, false, false, 2, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.muralla"), TRADUCCIONES_THEMA.getString("edificio.descripcion.muralla"), recursosBuildMuralla1, null, null, null);

        Map<Integer, Recursos> recursosBuildMuralla2 = new TreeMap<>();
        recursosBuildMuralla2.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuildMuralla2.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuildMuralla2.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuildMuralla2.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuildMuralla2.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuildMuralla2.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        new EdificiosPreCargados(2, 1, true, true, 2, 1, 0, TRADUCCIONES_THEMA.getString("edificio.name.muralla"), TRADUCCIONES_THEMA.getString("edificio.descripcion.muralla"), recursosBuildMuralla2, null, null, null);

        Map<Integer, Recursos> recursosBuildMuralla3 = new TreeMap<>();
        recursosBuildMuralla3.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuildMuralla3.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuildMuralla3.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuildMuralla3.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuildMuralla3.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuildMuralla3.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        new EdificiosPreCargados(2, 2, true, true, 2, 2, 0, TRADUCCIONES_THEMA.getString("edificio.name.muralla"), TRADUCCIONES_THEMA.getString("edificio.descripcion.muralla"), recursosBuildMuralla3, null, null, null);
        //
        //
        //
        Map<Integer, Recursos> recursosBuildPalacio1 = new TreeMap<>();
        recursosBuildPalacio1.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuildPalacio1.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuildPalacio1.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuildPalacio1.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuildPalacio1.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuildPalacio1.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        new EdificiosPreCargados(11, 0, true, true, 0, 1, 1, TRADUCCIONES_THEMA.getString("edificio.name.palacio"), TRADUCCIONES_THEMA.getString("edificio.descripcion.palacio"), recursosBuildPalacio1, null, null, null);

        Map<Integer, Recursos> recursosBuildPalacio2 = new TreeMap<>();
        recursosBuildPalacio2.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuildPalacio2.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuildPalacio2.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuildPalacio2.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuildPalacio2.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuildPalacio2.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        new EdificiosPreCargados(11, 1, true, true, 0, 2, 1, TRADUCCIONES_THEMA.getString("edificio.name.almacen"), TRADUCCIONES_THEMA.getString("edificio.descripcion.palacio"), recursosBuildPalacio2, null, null, null);

        Map<Integer, Recursos> recursosBuildPalacio3 = new TreeMap<>();
        recursosBuildPalacio3.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuildPalacio3.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuildPalacio3.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuildPalacio3.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuildPalacio3.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuildPalacio3.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        new EdificiosPreCargados(11, 2, true, true, 0, 2, 1, TRADUCCIONES_THEMA.getString("edificio.name.almacen"), TRADUCCIONES_THEMA.getString("edificio.descripcion.palacio"), recursosBuildPalacio3, null, null, null);
        progresBar.setProgress(20);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_CCientifico1 = new TreeMap<>();
        recursosBuild_CCientifico1.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuild_CCientifico1.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuild_CCientifico1.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuild_CCientifico1.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuild_CCientifico1.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuild_CCientifico1.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_CCientifico1 = new ArrayList<>();
        recursosCosteXtime_coste_CCientifico1.add(new Recursos(calcioPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_CCientifico1 = new TreeMap<>();
        recursosCosteXtime_produce_CCientifico1.put(hidrogenoPre, recursosCosteXtime_coste_CCientifico1);
        new EdificiosPreCargados(12, 0, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.centroCientifico"), TRADUCCIONES_THEMA.getString("edificio.descripcion.centroCientifico"), recursosBuild_CCientifico1, null, recursosCosteXtime_produce_CCientifico1, null);

        Map<Integer, Recursos> recursosBuild_CCientifico2 = new TreeMap<>();
        recursosBuild_CCientifico2.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuild_CCientifico2.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuild_CCientifico2.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuild_CCientifico2.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuild_CCientifico2.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuild_CCientifico2.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_CCientifico2 = new ArrayList<>();
        recursosCosteXtime_coste_CCientifico2.add(new Recursos(calcioPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_CCientifico2 = new TreeMap<>();
        recursosCosteXtime_produce_CCientifico2.put(hidrogenoPre, recursosCosteXtime_coste_CCientifico2);
        new EdificiosPreCargados(12, 1, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.centroCientifico"), TRADUCCIONES_THEMA.getString("edificio.descripcion.centroCientifico"), recursosBuild_CCientifico2, null, recursosCosteXtime_produce_CCientifico2, null);


        Map<Integer, Recursos> recursosBuild_CCientifico3 = new TreeMap<>();
        recursosBuild_CCientifico3.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuild_CCientifico3.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuild_CCientifico3.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuild_CCientifico3.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuild_CCientifico3.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuild_CCientifico3.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_CCientifico3 = new ArrayList<>();
        recursosCosteXtime_coste_CCientifico3.add(new Recursos(calcioPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_CCientifico3 = new TreeMap<>();
        recursosCosteXtime_produce_CCientifico3.put(hidrogenoPre, recursosCosteXtime_coste_CCientifico3);
        new EdificiosPreCargados(12, 2, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.centroCientifico"), TRADUCCIONES_THEMA.getString("edificio.descripcion.centroCientifico"), recursosBuild_CCientifico3, null, recursosCosteXtime_produce_CCientifico3, null);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Cantera1 = new TreeMap<>();
        recursosBuild_Cantera1.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuild_Cantera1.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuild_Cantera1.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuild_Cantera1.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuild_Cantera1.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuild_Cantera1.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Cantera1 = new ArrayList<>();
        recursosCosteXtime_coste_Cantera1.add(new Recursos(calcioPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Cantera1 = new TreeMap<>();
        recursosCosteXtime_produce_Cantera1.put(hidrogenoPre, recursosCosteXtime_coste_Cantera1);
        //
        Map<Integer, Recursos> recursosAlmacen_Cantera1 = new TreeMap<>();
        recursosAlmacen_Cantera1.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 100));
        recursosAlmacen_Cantera1.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 100));
        recursosAlmacen_Cantera1.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 100));
        recursosAlmacen_Cantera1.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 100));
        recursosAlmacen_Cantera1.put(fosforoPre.getId(), new Recursos(fosforoPre, 100));
        recursosAlmacen_Cantera1.put(calcioPre.getId(), new Recursos(calcioPre, 100));
        recursosAlmacen_Cantera1.put(PotasioPre.getId(), new Recursos(PotasioPre, 100));
        new EdificiosPreCargados(13, 0, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.cantera"), TRADUCCIONES_THEMA.getString("edificio.descripcion.cantera"), recursosBuild_Cantera1, null, recursosCosteXtime_produce_Cantera1, recursosAlmacen_Cantera1);

        Map<Integer, Recursos> recursosBuild_Cantera2 = new TreeMap<>();
        recursosBuild_Cantera2.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuild_Cantera2.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuild_Cantera2.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuild_Cantera2.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuild_Cantera2.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuild_Cantera2.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Cantera2 = new ArrayList<>();
        recursosCosteXtime_coste_Cantera2.add(new Recursos(calcioPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Cantera2 = new TreeMap<>();
        recursosCosteXtime_produce_Cantera2.put(hidrogenoPre, recursosCosteXtime_coste_Cantera2);
        //
        Map<Integer, Recursos> recursosAlmacen_Cantera2 = new TreeMap<>();
        recursosAlmacen_Cantera2.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 100));
        recursosAlmacen_Cantera2.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 100));
        recursosAlmacen_Cantera2.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 100));
        recursosAlmacen_Cantera2.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 100));
        recursosAlmacen_Cantera2.put(fosforoPre.getId(), new Recursos(fosforoPre, 100));
        recursosAlmacen_Cantera2.put(calcioPre.getId(), new Recursos(calcioPre, 100));
        recursosAlmacen_Cantera2.put(PotasioPre.getId(), new Recursos(PotasioPre, 100));
        new EdificiosPreCargados(13, 1, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.cantera"), TRADUCCIONES_THEMA.getString("edificio.descripcion.cantera"), recursosBuild_Cantera2, null, recursosCosteXtime_produce_Cantera2, recursosAlmacen_Cantera2);

        Map<Integer, Recursos> recursosBuild_Cantera3 = new TreeMap<>();
        recursosBuild_Cantera3.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuild_Cantera3.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuild_Cantera3.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuild_Cantera3.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuild_Cantera3.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuild_Cantera3.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Cantera3 = new ArrayList<>();
        recursosCosteXtime_coste_Cantera3.add(new Recursos(calcioPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Cantera3 = new TreeMap<>();
        recursosCosteXtime_produce_Cantera3.put(hidrogenoPre, recursosCosteXtime_coste_Cantera3);
        //
        Map<Integer, Recursos> recursosAlmacen_Cantera3 = new TreeMap<>();
        recursosAlmacen_Cantera3.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 100));
        recursosAlmacen_Cantera3.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 100));
        recursosAlmacen_Cantera3.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 100));
        recursosAlmacen_Cantera3.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 100));
        recursosAlmacen_Cantera3.put(fosforoPre.getId(), new Recursos(fosforoPre, 100));
        recursosAlmacen_Cantera3.put(calcioPre.getId(), new Recursos(calcioPre, 100));
        recursosAlmacen_Cantera3.put(PotasioPre.getId(), new Recursos(PotasioPre, 100));
        new EdificiosPreCargados(13, 2, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.cantera"), TRADUCCIONES_THEMA.getString("edificio.descripcion.cantera"), recursosBuild_Cantera3, null, recursosCosteXtime_produce_Cantera3, recursosAlmacen_Cantera3);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Granja1 = new TreeMap<>();
        recursosBuild_Granja1.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuild_Granja1.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuild_Granja1.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuild_Granja1.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuild_Granja1.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuild_Granja1.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Granja1 = new ArrayList<>();
        recursosCosteXtime_coste_Granja1.add(new Recursos(calcioPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_Granja1 = new TreeMap<>();
        recursosCosteXtime_Granja1.put(nitrogenoPre, recursosCosteXtime_coste_Granja1);
        new EdificiosPreCargados(16, 0, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.granja"), TRADUCCIONES_THEMA.getString("edificio.descripcion.granja"), recursosBuild_Granja1, null, recursosCosteXtime_Granja1, null);

        Map<Integer, Recursos> recursosBuild_Granja2 = new TreeMap<>();
        recursosBuild_Granja2.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuild_Granja2.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuild_Granja2.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuild_Granja2.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuild_Granja2.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuild_Granja2.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Granja2 = new ArrayList<>();
        recursosCosteXtime_coste_Granja2.add(new Recursos(calcioPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_Granja2 = new TreeMap<>();
        recursosCosteXtime_Granja2.put(nitrogenoPre, recursosCosteXtime_coste_Granja2);
        new EdificiosPreCargados(16, 1, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.granja"), TRADUCCIONES_THEMA.getString("edificio.descripcion.granja"), recursosBuild_Granja2, null, recursosCosteXtime_Granja2, null);

        Map<Integer, Recursos> recursosBuild_Granja3 = new TreeMap<>();
        recursosBuild_Granja3.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuild_Granja3.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuild_Granja3.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuild_Granja3.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuild_Granja3.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuild_Granja3.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Granja3 = new ArrayList<>();
        recursosCosteXtime_coste_Granja3.add(new Recursos(calcioPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_Granja3 = new TreeMap<>();
        recursosCosteXtime_Granja3.put(nitrogenoPre, recursosCosteXtime_coste_Granja3);
        new EdificiosPreCargados(16, 2, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.granja"), TRADUCCIONES_THEMA.getString("edificio.descripcion.granja"), recursosBuild_Granja3, null, recursosCosteXtime_Granja3, null);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Taberna1 = new TreeMap<>();
        recursosBuild_Taberna1.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuild_Taberna1.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuild_Taberna1.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuild_Taberna1.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuild_Taberna1.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuild_Taberna1.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Taberna1 = new ArrayList<>();
        recursosCosteXtime_coste_Taberna1.add(new Recursos(calcioPre, 20));
        recursosCosteXtime_coste_Taberna1.add(new Recursos(nitrogenoPre, 15));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteX_productor_Taberna1 = new TreeMap<>();
        recursosCosteX_productor_Taberna1.put(PotasioPre, recursosCosteXtime_coste_Taberna1);
        new EdificiosPreCargados(17, 0, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.taberna"), TRADUCCIONES_THEMA.getString("edificio.descripcion.taberna"), recursosBuild_Taberna1, null, recursosCosteX_productor_Taberna1, null);

        Map<Integer, Recursos> recursosBuild_Taberna2 = new TreeMap<>();
        recursosBuild_Taberna2.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuild_Taberna2.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuild_Taberna2.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuild_Taberna2.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuild_Taberna2.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuild_Taberna2.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Taberna2 = new ArrayList<>();
        recursosCosteXtime_coste_Taberna2.add(new Recursos(calcioPre, 20));
        recursosCosteXtime_coste_Taberna2.add(new Recursos(nitrogenoPre, 15));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteX_productor_Taberna2 = new TreeMap<>();
        recursosCosteX_productor_Taberna2.put(PotasioPre, recursosCosteXtime_coste_Taberna2);
        new EdificiosPreCargados(17, 1, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.taberna"), TRADUCCIONES_THEMA.getString("edificio.descripcion.taberna"), recursosBuild_Taberna2, null, recursosCosteX_productor_Taberna2, null);

        Map<Integer, Recursos> recursosBuild_Taberna3 = new TreeMap<>();
        recursosBuild_Taberna3.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuild_Taberna3.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuild_Taberna3.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuild_Taberna3.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuild_Taberna3.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuild_Taberna3.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Taberna3 = new ArrayList<>();
        recursosCosteXtime_coste_Taberna3.add(new Recursos(calcioPre, 20));
        recursosCosteXtime_coste_Taberna3.add(new Recursos(nitrogenoPre, 15));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteX_productor_Taberna3 = new TreeMap<>();
        recursosCosteX_productor_Taberna3.put(PotasioPre, recursosCosteXtime_coste_Taberna3);
        new EdificiosPreCargados(17, 2, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.taberna"), TRADUCCIONES_THEMA.getString("edificio.descripcion.taberna"), recursosBuild_Taberna3, null, recursosCosteX_productor_Taberna3, null);
        progresBar.setProgress(30);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Puerto1 = new TreeMap<>();
        recursosBuild_Puerto1.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuild_Puerto1.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuild_Puerto1.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuild_Puerto1.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuild_Puerto1.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuild_Puerto1.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        new EdificiosPreCargados(18, 0, false, false, 0, 0, 4, TRADUCCIONES_THEMA.getString("edificio.name.puerto"), TRADUCCIONES_THEMA.getString("edificio.descripcion.puerto"), recursosBuild_Puerto1, null, null, null);

        Map<Integer, Recursos> recursosBuild_Puerto2 = new TreeMap<>();
        recursosBuild_Puerto2.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuild_Puerto2.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuild_Puerto2.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuild_Puerto2.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuild_Puerto2.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuild_Puerto2.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        new EdificiosPreCargados(18, 1, true, true, 0, 0, 4, TRADUCCIONES_THEMA.getString("edificio.name.puerto"), TRADUCCIONES_THEMA.getString("edificio.descripcion.puerto"), recursosBuild_Puerto2, null, null, null);

        Map<Integer, Recursos> recursosBuild_Puerto3 = new TreeMap<>();
        recursosBuild_Puerto3.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuild_Puerto3.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuild_Puerto3.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuild_Puerto3.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuild_Puerto3.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuild_Puerto3.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        new EdificiosPreCargados(18, 2, true, true, 0, 0, 4, TRADUCCIONES_THEMA.getString("edificio.name.puerto"), TRADUCCIONES_THEMA.getString("edificio.descripcion.puerto"), recursosBuild_Puerto3, null, null, null);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Cuartel1 = new TreeMap<>();
        recursosBuild_Cuartel1.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuild_Cuartel1.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuild_Cuartel1.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuild_Cuartel1.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuild_Cuartel1.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuild_Cuartel1.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        new EdificiosPreCargados(19, 0, true, true, 0, 0, 2, TRADUCCIONES_THEMA.getString("edificio.name.cuartel"), TRADUCCIONES_THEMA.getString("edificio.descripcion.cuartel"), recursosBuild_Cuartel1, null, null, null);

        Map<Integer, Recursos> recursosBuild_Cuartel2 = new TreeMap<>();
        recursosBuild_Cuartel2.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuild_Cuartel2.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuild_Cuartel2.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuild_Cuartel2.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuild_Cuartel2.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuild_Cuartel2.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        new EdificiosPreCargados(19, 1, true, true, 0, 0, 2, TRADUCCIONES_THEMA.getString("edificio.name.cuartel"), TRADUCCIONES_THEMA.getString("edificio.descripcion.cuartel"), recursosBuild_Cuartel2, null, null, null);

        Map<Integer, Recursos> recursosBuild_Cuartel3 = new TreeMap<>();
        recursosBuild_Cuartel3.put(oxigenoPre.getId(), new Recursos(oxigenoPre, 5));
        recursosBuild_Cuartel3.put(CarbonoPre.getId(), new Recursos(CarbonoPre, 15));
        recursosBuild_Cuartel3.put(hidrogenoPre.getId(), new Recursos(hidrogenoPre, 20));
        recursosBuild_Cuartel3.put(nitrogenoPre.getId(), new Recursos(nitrogenoPre, 5));
        recursosBuild_Cuartel3.put(fosforoPre.getId(), new Recursos(fosforoPre, 5));
        recursosBuild_Cuartel3.put(calcioPre.getId(), new Recursos(calcioPre, 1));
        new EdificiosPreCargados(19, 2, true, true, 0, 0, 2, TRADUCCIONES_THEMA.getString("edificio.name.cuartel"), TRADUCCIONES_THEMA.getString("edificio.descripcion.cuartel"), recursosBuild_Cuartel3, null, null, null);

        //
        //
        progresBar.setProgress(40);
        //
        //

        List<Recursos> recursosList0 = new ArrayList<>();
        recursosList0.add(new Recursos(oxigenoPre, 0));
        recursosList0.add(new Recursos(CarbonoPre, 5));
        recursosList0.add(new Recursos(hidrogenoPre, 15));
        recursosList0.add(new Recursos(nitrogenoPre, 0));
        recursosList0.add(new Recursos(fosforoPre, 3));
        recursosList0.add(new Recursos(PotasioPre, 1));
        new UnidadesPreCargadas(0, TRADUCCIONES_THEMA.getString("unidades.soldados.espadachines"), 0, recursosList0, 0);
        List<Recursos> recursosList1 = new ArrayList<>();
        recursosList1.add(new Recursos(oxigenoPre, 0));
        recursosList1.add(new Recursos(CarbonoPre, 5));
        recursosList1.add(new Recursos(hidrogenoPre, 15));
        recursosList1.add(new Recursos(nitrogenoPre, 0));
        recursosList1.add(new Recursos(fosforoPre, 3));
        recursosList1.add(new Recursos(PotasioPre, 1));
        new UnidadesPreCargadas(1, TRADUCCIONES_THEMA.getString("unidades.soldados.lanceros"), 0, recursosList1, 0);
        List<Recursos> recursosList2 = new ArrayList<>();
        recursosList2.add(new Recursos(oxigenoPre, 0));
        recursosList2.add(new Recursos(CarbonoPre, 5));
        recursosList2.add(new Recursos(hidrogenoPre, 15));
        recursosList2.add(new Recursos(nitrogenoPre, 0));
        recursosList2.add(new Recursos(fosforoPre, 3));
        recursosList2.add(new Recursos(PotasioPre, 1));
        new UnidadesPreCargadas(2, TRADUCCIONES_THEMA.getString("unidades.soldados.arqueros"), 100, recursosList2, 0);
        List<Recursos> recursosList3 = new ArrayList<>();
        recursosList3.add(new Recursos(oxigenoPre, 3));
        recursosList3.add(new Recursos(CarbonoPre, 6));
        recursosList3.add(new Recursos(hidrogenoPre, 65));
        recursosList3.add(new Recursos(nitrogenoPre, 7));
        recursosList3.add(new Recursos(fosforoPre, 8));
        recursosList3.add(new Recursos(PotasioPre, 3));
        new UnidadesPreCargadas(3, TRADUCCIONES_THEMA.getString("unidades.soldados.caballeros"), 50, recursosList3, 0);
        List<Recursos> recursosList4 = new ArrayList<>();
        recursosList4.add(new Recursos(oxigenoPre, 3));
        recursosList4.add(new Recursos(CarbonoPre, 6));
        recursosList4.add(new Recursos(hidrogenoPre, 65));
        recursosList4.add(new Recursos(nitrogenoPre, 7));
        recursosList4.add(new Recursos(fosforoPre, 8));
        recursosList4.add(new Recursos(PotasioPre, 3));
        new UnidadesPreCargadas(4, TRADUCCIONES_THEMA.getString("unidades.asedio.catapultas"), 50, recursosList4, 5);


        //
        //
        //
        //
        progresBar.setProgress(50);
        //TODO En pantalla
        Clan clan = new Clan("Los mejores", 10);
        Clan clan2 = new Clan("Los peores", 10);
        clan2.setContrasenya("1234");
        ArrayList<Recursos> jugador1ListaRecuros = new ArrayList<>();
        Jugador jugador = new Jugador(0l,"pepito", jugador1ListaRecuros);
        setJugadorPrimaryStageController(jugador);
        setClanPrimaryStageController(clan);
        progresBar.setProgress(60);
        ArrayList<Recursos> jugador2ListaRecuros = new ArrayList<>();
        Jugador jugador2 = new Jugador(1l,"juan", jugador2ListaRecuros);
        ArrayList<Recursos> jugador3ListaRecuros = new ArrayList<>();
        Jugador jugador3 = new Jugador(2l,"pedro", jugador3ListaRecuros);
        Jugador jugador4 = new Jugador(3l,"julito", jugador3ListaRecuros);
        clan2.setCreador(jugador4);
        clan2.addJugadorClan(jugador4);
        progresBar.setProgress(70);
        clan.addJugadorClan(jugador);
        clan.setCreador(jugador);
        clan.addJugadorClan(3);


        elTemaSeleccionado.espias.put(1, 10);
        elTemaSeleccionado.espias.put(2, 25);
        elTemaSeleccionado.espias.put(5, 50);
        elTemaSeleccionado.espias.put(10, 80);
        progresBar.setProgress(80);

        //TODO TEST
        Comercio comercio0 = new Comercio(new Recursos(oxigenoPre, 5), new Recursos(CarbonoPre, 300), jugador);
        Comercio.data.add(comercio0);
        Comercio comercio1 = new Comercio(new Recursos(fosforoPre, 6), new Recursos(nitrogenoPre, 11), jugador2);
        Comercio.data.add(comercio1);
        Comercio comercio2 = new Comercio(new Recursos(oxigenoPre, 7), new Recursos(CarbonoPre, 12), jugador3);
        Comercio.data.add(comercio2);
        Comercio comercio3 = new Comercio(new Recursos(fosforoPre, 7000000), new Recursos(CarbonoPre, 15), jugador4);
        Comercio.data.add(comercio3);

/*
        Timeline thirtySeconds = new Timeline(new KeyFrame(Duration.seconds(30), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Thread Running --> PANTALLA INICIAL CONTROLLER: ADD Comercio");
                Comercio comercio5 = new Comercio( new Recursos(maderaPre, 7), new Recursos(maderaPre, 15), jugador);
                Comercio.data.add(comercio5);
                Comercio comercio6 = new Comercio( new Recursos(maderaPre, 7), new Recursos(maderaPre, 15), jugador2);
                Comercio.data.add(comercio6);
            }
        }));
        thirtySeconds.setCycleCount(Timeline.INDEFINITE);
        thirtySeconds.play();
*/


        Thread thread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread Running --> PANTALLA INICIAL CONTROLLER: REMOVE Comercio");
                Comercio.data.remove(comercio1);
                Comercio.data.remove(comercio2);
                Comercio comercio4 = new Comercio(new Recursos(hidrogenoPre, 7), new Recursos(CarbonoPre, 15), jugador3);
                Comercio.data.add(comercio4);
            }
        };
        thread.start();
        Thread thread2 = new Thread() {
            public void run() {
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("CLAN");
                clan.removeJugadorClan(3);
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("CLAN2");
                clan2.addJugadorClan(3);
            }
        };
        thread2.start();
    }
}

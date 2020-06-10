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
        if (elTemaSeleccionado.getName().equals("Demo")){
            callbdDemo(progresBar);
        }else {

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
        RecursosPrecargados investigacionPre = new RecursosPrecargados(7, TRADUCCIONES_THEMA.getString("recurso.investigacion"), false, true, false, true);
        RecursosPrecargados municionPre = new RecursosPrecargados(8, TRADUCCIONES_THEMA.getString("recurso.municion"), true, true, true, true);
        progresBar.setProgress(15);
        //TODO MENUS ESPECIALES
        //-1=noDanNiGeneranNada
        //0=EdificioNormal
        //1=NewCiudad
        //2=CreaSoldados
        //3=CreaMaquinas

        //RECURSOS - CONSTRUIR
        Map<Integer, Recursos> recursosBuild = new TreeMap<>();
        recursosBuild.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild.put(maderaPre.getId(), new Recursos(maderaPre, 5));
        recursosBuild.put(piedraPre.getId(), new Recursos(piedraPre, 5));
        recursosBuild.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild.put(poblacionPre.getId(), new Recursos(poblacionPre, 5));
        //RECURSOS -Productores
        Map<Integer, Recursos> recursosProductores = new TreeMap<>();
        recursosProductores.put(oroPre.getId(), new Recursos(oroPre, 10));
        recursosProductores.put(maderaPre.getId(), new Recursos(maderaPre, 10));
        recursosProductores.put(piedraPre.getId(), new Recursos(piedraPre, 10));
        recursosProductores.put(comidaPre.getId(), new Recursos(comidaPre, 10));
        recursosProductores.put(hierroPre.getId(), new Recursos(hierroPre, 10));
        recursosProductores.put(poblacionPre.getId(), new Recursos(poblacionPre, 10));
        recursosProductores.put(felicidadPre.getId(), new Recursos(felicidadPre, 10));
        recursosProductores.put(investigacionPre.getId(), new Recursos(investigacionPre, 10));
        //RECURSOS - COSTE X MIN
        ArrayList<Recursos> recursosCosteXtime_coste = new ArrayList<>();
        recursosCosteXtime_coste.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_productor = new TreeMap<>();
        recursosCosteXtime_productor.put(maderaPre, recursosCosteXtime_coste);
        recursosCosteXtime_productor.put(piedraPre, recursosCosteXtime_coste);
        recursosCosteXtime_productor.put(comidaPre, recursosCosteXtime_coste);
        recursosCosteXtime_productor.put(hierroPre, recursosCosteXtime_coste);
        //RECURSOS -ALMACEN
        Map<Integer, Recursos> recursosAlmacen = new TreeMap<>();
        recursosAlmacen.put(oroPre.getId(), new Recursos(oroPre, 31));
        recursosAlmacen.put(maderaPre.getId(), new Recursos(maderaPre, 31));
        recursosAlmacen.put(piedraPre.getId(), new Recursos(piedraPre, 31));
        recursosAlmacen.put(comidaPre.getId(), new Recursos(comidaPre, 31));
        recursosAlmacen.put(hierroPre.getId(), new Recursos(hierroPre, 31));
        recursosAlmacen.put(poblacionPre.getId(), new Recursos(poblacionPre, 31));
        recursosAlmacen.put(felicidadPre.getId(), new Recursos(felicidadPre, 31));
        recursosAlmacen.put(investigacionPre.getId(), new Recursos(investigacionPre, 31));




        new EdificiosPreCargados(0, 0, false, false, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.parcelaConstruible"), TRADUCCIONES_THEMA.getString("edificio.descripcion.parcelaConstruible"), null, null, null, null);//NO MEJORABLE

        Map<Integer, Recursos> recursosBuildCastillo1 = new TreeMap<>();
        recursosBuildCastillo1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuildCastillo1.put(maderaPre.getId(), new Recursos(maderaPre, 5));
        recursosBuildCastillo1.put(piedraPre.getId(), new Recursos(piedraPre, 5));
        recursosBuildCastillo1.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuildCastillo1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuildCastillo1.put(poblacionPre.getId(), new Recursos(poblacionPre, 5));
        //
        Map<Integer, Recursos> recursosProductoresCastillo1 = new TreeMap<>();
        recursosProductoresCastillo1.put(poblacionPre.getId(), new Recursos(poblacionPre, 10));
        recursosProductoresCastillo1.put(felicidadPre.getId(), new Recursos(felicidadPre, 10));
        //
        Map<Integer, Recursos> recursosAlmacenCastillo1 = new TreeMap<>();
        recursosAlmacenCastillo1.put(oroPre.getId(), new Recursos(oroPre, 100));
        recursosAlmacenCastillo1.put(maderaPre.getId(), new Recursos(maderaPre, 100));
        recursosAlmacenCastillo1.put(piedraPre.getId(), new Recursos(piedraPre, 100));
        recursosAlmacenCastillo1.put(comidaPre.getId(), new Recursos(comidaPre, 100));
        recursosAlmacenCastillo1.put(hierroPre.getId(), new Recursos(hierroPre, 100));
        recursosAlmacenCastillo1.put(poblacionPre.getId(), new Recursos(poblacionPre, 100));
        recursosAlmacenCastillo1.put(felicidadPre.getId(), new Recursos(felicidadPre, 100));
        recursosAlmacenCastillo1.put(investigacionPre.getId(), new Recursos(investigacionPre, 100));
        new EdificiosPreCargados(1, 0, false, false, 1, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.castillo"), TRADUCCIONES_THEMA.getString("edificio.descripcion.castillo"), recursosBuildCastillo1, recursosProductoresCastillo1, null, recursosAlmacenCastillo1);

        Map<Integer, Recursos> recursosBuildCastillo2 = new TreeMap<>();
        recursosBuildCastillo2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuildCastillo2.put(maderaPre.getId(), new Recursos(maderaPre, 5));
        recursosBuildCastillo2.put(piedraPre.getId(), new Recursos(piedraPre, 5));
        recursosBuildCastillo2.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuildCastillo2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuildCastillo2.put(poblacionPre.getId(), new Recursos(poblacionPre, 5));
        //
        Map<Integer, Recursos> recursosProductoresCastillo2 = new TreeMap<>();
        recursosProductoresCastillo2.put(poblacionPre.getId(), new Recursos(poblacionPre, 10));
        recursosProductoresCastillo2.put(felicidadPre.getId(), new Recursos(felicidadPre, 10));
        //
        Map<Integer, Recursos> recursosAlmacenCastillo2 = new TreeMap<>();
        recursosAlmacenCastillo2.put(oroPre.getId(), new Recursos(oroPre, 100));
        recursosAlmacenCastillo2.put(maderaPre.getId(), new Recursos(maderaPre, 100));
        recursosAlmacenCastillo2.put(piedraPre.getId(), new Recursos(piedraPre, 100));
        recursosAlmacenCastillo2.put(comidaPre.getId(), new Recursos(comidaPre, 100));
        recursosAlmacenCastillo2.put(hierroPre.getId(), new Recursos(hierroPre, 100));
        recursosAlmacenCastillo2.put(poblacionPre.getId(), new Recursos(poblacionPre, 100));
        recursosAlmacenCastillo2.put(felicidadPre.getId(), new Recursos(felicidadPre, 100));
        recursosAlmacenCastillo2.put(investigacionPre.getId(), new Recursos(investigacionPre, 100));
        new EdificiosPreCargados(1, 1, false, true, 1, 1, 0, TRADUCCIONES_THEMA.getString("edificio.name.castillo"), TRADUCCIONES_THEMA.getString("edificio.descripcion.castillo"), recursosBuildCastillo2, recursosProductoresCastillo2, null, recursosAlmacenCastillo2);

        Map<Integer, Recursos> recursosBuildCastillo3 = new TreeMap<>();
        recursosBuildCastillo3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuildCastillo3.put(maderaPre.getId(), new Recursos(maderaPre, 5));
        recursosBuildCastillo3.put(piedraPre.getId(), new Recursos(piedraPre, 5));
        recursosBuildCastillo3.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuildCastillo3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuildCastillo3.put(poblacionPre.getId(), new Recursos(poblacionPre, 5));
        //
        Map<Integer, Recursos> recursosProductoresCastillo3 = new TreeMap<>();
        recursosProductoresCastillo3.put(poblacionPre.getId(), new Recursos(poblacionPre, 10));
        recursosProductoresCastillo3.put(felicidadPre.getId(), new Recursos(felicidadPre, 10));
        //
        Map<Integer, Recursos> recursosAlmacenCastillo3 = new TreeMap<>();
        recursosAlmacenCastillo3.put(oroPre.getId(), new Recursos(oroPre, 100));
        recursosAlmacenCastillo3.put(maderaPre.getId(), new Recursos(maderaPre, 100));
        recursosAlmacenCastillo3.put(piedraPre.getId(), new Recursos(piedraPre, 100));
        recursosAlmacenCastillo3.put(comidaPre.getId(), new Recursos(comidaPre, 100));
        recursosAlmacenCastillo3.put(hierroPre.getId(), new Recursos(hierroPre, 100));
        recursosAlmacenCastillo3.put(poblacionPre.getId(), new Recursos(poblacionPre, 100));
        recursosAlmacenCastillo3.put(felicidadPre.getId(), new Recursos(felicidadPre, 100));
        recursosAlmacenCastillo3.put(investigacionPre.getId(), new Recursos(investigacionPre, 100));
        new EdificiosPreCargados(1, 2, false, true, 1, 2, 0, TRADUCCIONES_THEMA.getString("edificio.name.castillo"), TRADUCCIONES_THEMA.getString("edificio.descripcion.castillo"), recursosBuildCastillo3, recursosProductoresCastillo3, null, recursosAlmacenCastillo3);
        //
        //
        //
        Map<Integer, Recursos> recursosBuildMuralla1 = new TreeMap<>();
        recursosBuildMuralla1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuildMuralla1.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuildMuralla1.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuildMuralla1.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuildMuralla1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuildMuralla1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(2, 0, false, false, 2, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.muralla"), TRADUCCIONES_THEMA.getString("edificio.descripcion.muralla"), recursosBuildMuralla1, null, null, null);

        Map<Integer, Recursos> recursosBuildMuralla2 = new TreeMap<>();
        recursosBuildMuralla2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuildMuralla2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuildMuralla2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuildMuralla2.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuildMuralla2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuildMuralla2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(2, 1, true, true, 2, 1, 0, TRADUCCIONES_THEMA.getString("edificio.name.muralla"), TRADUCCIONES_THEMA.getString("edificio.descripcion.muralla"), recursosBuildMuralla2, null, null, null);

        Map<Integer, Recursos> recursosBuildMuralla3 = new TreeMap<>();
        recursosBuildMuralla3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuildMuralla3.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuildMuralla3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuildMuralla3.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuildMuralla3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuildMuralla3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(2, 2, true, true, 2, 2, 0, TRADUCCIONES_THEMA.getString("edificio.name.muralla"), TRADUCCIONES_THEMA.getString("edificio.descripcion.muralla"), recursosBuildMuralla3, null, null, null);
        //
        //
        //
        Map<Integer, Recursos> recursosBuildAlmacen1 = new TreeMap<>();
        recursosBuildAlmacen1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuildAlmacen1.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuildAlmacen1.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuildAlmacen1.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuildAlmacen1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuildAlmacen1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosAlmacenAlmacen1 = new TreeMap<>();
        recursosAlmacenAlmacen1.put(oroPre.getId(), new Recursos(oroPre, 100));
        recursosAlmacenAlmacen1.put(maderaPre.getId(), new Recursos(maderaPre, 100));
        recursosAlmacenAlmacen1.put(piedraPre.getId(), new Recursos(piedraPre, 100));
        recursosAlmacenAlmacen1.put(comidaPre.getId(), new Recursos(comidaPre, 100));
        recursosAlmacenAlmacen1.put(hierroPre.getId(), new Recursos(hierroPre, 100));
        recursosAlmacenAlmacen1.put(poblacionPre.getId(), new Recursos(poblacionPre, 100));
        recursosAlmacenAlmacen1.put(felicidadPre.getId(), new Recursos(felicidadPre, 100));
        recursosAlmacenAlmacen1.put(investigacionPre.getId(), new Recursos(investigacionPre, 100));
        new EdificiosPreCargados(10, 0, true, true, 0, -1, 0, TRADUCCIONES_THEMA.getString("edificio.name.almacen"), TRADUCCIONES_THEMA.getString("edificio.descripcion.almacen"), recursosBuildAlmacen1, null, null, recursosAlmacenAlmacen1);

        Map<Integer, Recursos> recursosBuildAlmacen2 = new TreeMap<>();
        recursosBuildAlmacen2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuildAlmacen2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuildAlmacen2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuildAlmacen2.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuildAlmacen2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuildAlmacen2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosAlmacenAlmacen2 = new TreeMap<>();
        recursosAlmacenAlmacen2.put(oroPre.getId(), new Recursos(oroPre, 100));
        recursosAlmacenAlmacen2.put(maderaPre.getId(), new Recursos(maderaPre, 100));
        recursosAlmacenAlmacen2.put(piedraPre.getId(), new Recursos(piedraPre, 100));
        recursosAlmacenAlmacen2.put(comidaPre.getId(), new Recursos(comidaPre, 100));
        recursosAlmacenAlmacen2.put(hierroPre.getId(), new Recursos(hierroPre, 100));
        recursosAlmacenAlmacen2.put(poblacionPre.getId(), new Recursos(poblacionPre, 100));
        recursosAlmacenAlmacen2.put(felicidadPre.getId(), new Recursos(felicidadPre, 100));
        recursosAlmacenAlmacen2.put(investigacionPre.getId(), new Recursos(investigacionPre, 100));
        new EdificiosPreCargados(10, 1, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.almacen"), TRADUCCIONES_THEMA.getString("edificio.descripcion.almacen"), recursosBuildAlmacen2, null, null, recursosAlmacenAlmacen2);

        Map<Integer, Recursos> recursosBuildAlmacen3 = new TreeMap<>();
        recursosBuildAlmacen3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuildAlmacen3.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuildAlmacen3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuildAlmacen3.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuildAlmacen3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuildAlmacen3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        Map<Integer, Recursos> recursosAlmacenAlmacen3 = new TreeMap<>();
        recursosAlmacenAlmacen3.put(oroPre.getId(), new Recursos(oroPre, 100));
        recursosAlmacenAlmacen3.put(maderaPre.getId(), new Recursos(maderaPre, 100));
        recursosAlmacenAlmacen3.put(piedraPre.getId(), new Recursos(piedraPre, 100));
        recursosAlmacenAlmacen3.put(comidaPre.getId(), new Recursos(comidaPre, 100));
        recursosAlmacenAlmacen3.put(hierroPre.getId(), new Recursos(hierroPre, 100));
        recursosAlmacenAlmacen3.put(poblacionPre.getId(), new Recursos(poblacionPre, 100));
        recursosAlmacenAlmacen3.put(felicidadPre.getId(), new Recursos(felicidadPre, 100));
        recursosAlmacenAlmacen3.put(investigacionPre.getId(), new Recursos(investigacionPre, 100));
        new EdificiosPreCargados(10, 2, true, true, 0, 1, 0, TRADUCCIONES_THEMA.getString("edificio.name.almacen"), TRADUCCIONES_THEMA.getString("edificio.descripcion.almacen"), recursosBuildAlmacen3, null, null, recursosAlmacenAlmacen3);
        //
        //
        //
        Map<Integer, Recursos> recursosBuildPalacio1 = new TreeMap<>();
        recursosBuildPalacio1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuildPalacio1.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuildPalacio1.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuildPalacio1.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuildPalacio1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuildPalacio1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(11, 0, true, true, 0, 1, 1, TRADUCCIONES_THEMA.getString("edificio.name.palacio"), TRADUCCIONES_THEMA.getString("edificio.descripcion.palacio"), recursosBuildPalacio1, null, null, null);

        Map<Integer, Recursos> recursosBuildPalacio2 = new TreeMap<>();
        recursosBuildPalacio2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuildPalacio2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuildPalacio2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuildPalacio2.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuildPalacio2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuildPalacio2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(11, 1, true, true, 0, 2, 1, TRADUCCIONES_THEMA.getString("edificio.name.almacen"), TRADUCCIONES_THEMA.getString("edificio.descripcion.palacio"), recursosBuildPalacio2, null, null, null);

        Map<Integer, Recursos> recursosBuildPalacio3 = new TreeMap<>();
        recursosBuildPalacio3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuildPalacio3.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuildPalacio3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuildPalacio3.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuildPalacio3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuildPalacio3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(11, 2, true, true, 0, 2, 1, TRADUCCIONES_THEMA.getString("edificio.name.almacen"), TRADUCCIONES_THEMA.getString("edificio.descripcion.palacio"), recursosBuildPalacio3, null, null, null);
        progresBar.setProgress(20);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_CCientifico1 = new TreeMap<>();
        recursosBuild_CCientifico1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_CCientifico1.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_CCientifico1.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_CCientifico1.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_CCientifico1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_CCientifico1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_CCientifico1 = new ArrayList<>();
        recursosCosteXtime_coste_CCientifico1.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_CCientifico1 = new TreeMap<>();
        recursosCosteXtime_produce_CCientifico1.put(investigacionPre, recursosCosteXtime_coste_CCientifico1);
        new EdificiosPreCargados(12, 0, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.centroCientifico"), TRADUCCIONES_THEMA.getString("edificio.descripcion.centroCientifico"), recursosBuild_CCientifico1, null, recursosCosteXtime_produce_CCientifico1, null);

        Map<Integer, Recursos> recursosBuild_CCientifico2 = new TreeMap<>();
        recursosBuild_CCientifico2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_CCientifico2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_CCientifico2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_CCientifico2.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_CCientifico2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_CCientifico2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_CCientifico2 = new ArrayList<>();
        recursosCosteXtime_coste_CCientifico2.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_CCientifico2 = new TreeMap<>();
        recursosCosteXtime_produce_CCientifico2.put(investigacionPre, recursosCosteXtime_coste_CCientifico2);
        new EdificiosPreCargados(12, 1, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.centroCientifico"), TRADUCCIONES_THEMA.getString("edificio.descripcion.centroCientifico"), recursosBuild_CCientifico2, null, recursosCosteXtime_produce_CCientifico2, null);


        Map<Integer, Recursos> recursosBuild_CCientifico3 = new TreeMap<>();
        recursosBuild_CCientifico3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_CCientifico3.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_CCientifico3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_CCientifico3.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_CCientifico3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_CCientifico3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_CCientifico3 = new ArrayList<>();
        recursosCosteXtime_coste_CCientifico3.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_CCientifico3 = new TreeMap<>();
        recursosCosteXtime_produce_CCientifico3.put(investigacionPre, recursosCosteXtime_coste_CCientifico3);
        new EdificiosPreCargados(12, 2, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.centroCientifico"), TRADUCCIONES_THEMA.getString("edificio.descripcion.centroCientifico"), recursosBuild_CCientifico3, null, recursosCosteXtime_produce_CCientifico3, null);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Cantera1 = new TreeMap<>();
        recursosBuild_Cantera1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Cantera1.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Cantera1.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Cantera1.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Cantera1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Cantera1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Cantera1 = new ArrayList<>();
        recursosCosteXtime_coste_Cantera1.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Cantera1 = new TreeMap<>();
        recursosCosteXtime_produce_Cantera1.put(piedraPre, recursosCosteXtime_coste_Cantera1);
        new EdificiosPreCargados(13, 0, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.cantera"), TRADUCCIONES_THEMA.getString("edificio.descripcion.cantera"), recursosBuild_Cantera1, null, recursosCosteXtime_produce_Cantera1, null);

        Map<Integer, Recursos> recursosBuild_Cantera2 = new TreeMap<>();
        recursosBuild_Cantera2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Cantera2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Cantera2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Cantera2.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Cantera2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Cantera2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Cantera2 = new ArrayList<>();
        recursosCosteXtime_coste_Cantera2.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Cantera2 = new TreeMap<>();
        recursosCosteXtime_produce_Cantera2.put(piedraPre, recursosCosteXtime_coste_Cantera2);
        new EdificiosPreCargados(13, 1, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.cantera"), TRADUCCIONES_THEMA.getString("edificio.descripcion.cantera"), recursosBuild_Cantera2, null, recursosCosteXtime_produce_Cantera2, null);

        Map<Integer, Recursos> recursosBuild_Cantera3 = new TreeMap<>();
        recursosBuild_Cantera3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Cantera3.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Cantera3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Cantera3.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Cantera3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Cantera3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Cantera3 = new ArrayList<>();
        recursosCosteXtime_coste_Cantera3.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Cantera3 = new TreeMap<>();
        recursosCosteXtime_produce_Cantera3.put(piedraPre, recursosCosteXtime_coste_Cantera3);
        new EdificiosPreCargados(13, 2, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.cantera"), TRADUCCIONES_THEMA.getString("edificio.descripcion.cantera"), recursosBuild_Cantera3, null, recursosCosteXtime_produce_Cantera3, null);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Aserradero1 = new TreeMap<>();
        recursosBuild_Aserradero1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Aserradero1.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Aserradero1.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Aserradero1.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Aserradero1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Aserradero1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Aserradero1 = new ArrayList<>();
        recursosCosteXtime_coste_Aserradero1.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Aserradero1 = new TreeMap<>();
        recursosCosteXtime_produce_Aserradero1.put(maderaPre, recursosCosteXtime_coste_Aserradero1);
        new EdificiosPreCargados(14, 0, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.aserradero"), TRADUCCIONES_THEMA.getString("edificio.descripcion.aserradero"), recursosBuild_Aserradero1, null, recursosCosteXtime_produce_Aserradero1, null);

        Map<Integer, Recursos> recursosBuild_Aserradero2 = new TreeMap<>();
        recursosBuild_Aserradero2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Aserradero2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Aserradero2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Aserradero2.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Aserradero2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Aserradero2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Aserradero2 = new ArrayList<>();
        recursosCosteXtime_coste_Aserradero2.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Aserradero2 = new TreeMap<>();
        recursosCosteXtime_produce_Aserradero2.put(maderaPre, recursosCosteXtime_coste_Aserradero2);
        new EdificiosPreCargados(14, 1, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.aserradero"), TRADUCCIONES_THEMA.getString("edificio.descripcion.aserradero"), recursosBuild_Aserradero2, null, recursosCosteXtime_produce_Aserradero2, null);

        Map<Integer, Recursos> recursosBuild_Aserradero3 = new TreeMap<>();
        recursosBuild_Aserradero3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Aserradero3.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Aserradero3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Aserradero3.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Aserradero3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Aserradero3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Aserradero3 = new ArrayList<>();
        recursosCosteXtime_coste_Aserradero3.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Aserradero3 = new TreeMap<>();
        recursosCosteXtime_produce_Aserradero3.put(maderaPre, recursosCosteXtime_coste_Aserradero3);
        new EdificiosPreCargados(14, 2, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.aserradero"), TRADUCCIONES_THEMA.getString("edificio.descripcion.aserradero"), recursosBuild_Aserradero3, null, recursosCosteXtime_produce_Aserradero3, null);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Fundicion1 = new TreeMap<>();
        recursosBuild_Fundicion1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Fundicion1.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Fundicion1.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Fundicion1.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Fundicion1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Fundicion1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Fundicion1 = new ArrayList<>();
        recursosCosteXtime_coste_Fundicion1.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Fundicion1 = new TreeMap<>();
        recursosCosteXtime_produce_Fundicion1.put(hierroPre, recursosCosteXtime_coste_Fundicion1);
        new EdificiosPreCargados(15, 0, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.fundicion"), TRADUCCIONES_THEMA.getString("edificio.descripcion.fundicion"), recursosBuild_Fundicion1, null, recursosCosteXtime_produce_Fundicion1, null);

        Map<Integer, Recursos> recursosBuild_Fundicion2 = new TreeMap<>();
        recursosBuild_Fundicion2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Fundicion2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Fundicion2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Fundicion2.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Fundicion2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Fundicion2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Fundicion2 = new ArrayList<>();
        recursosCosteXtime_coste_Fundicion2.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Fundicion2 = new TreeMap<>();
        recursosCosteXtime_produce_Fundicion2.put(hierroPre, recursosCosteXtime_coste_Fundicion2);
        new EdificiosPreCargados(15, 1, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.fundicion"), TRADUCCIONES_THEMA.getString("edificio.descripcion.fundicion"), recursosBuild_Fundicion2, null, recursosCosteXtime_produce_Fundicion2, null);

        Map<Integer, Recursos> recursosBuild_Fundicion3 = new TreeMap<>();
        recursosBuild_Fundicion3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Fundicion3.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Fundicion3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Fundicion3.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Fundicion3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Fundicion3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Fundicion3 = new ArrayList<>();
        recursosCosteXtime_coste_Fundicion3.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_produce_Fundicion3 = new TreeMap<>();
        recursosCosteXtime_produce_Fundicion3.put(hierroPre, recursosCosteXtime_coste_Fundicion3);
        new EdificiosPreCargados(15, 2, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.fundicion"), TRADUCCIONES_THEMA.getString("edificio.descripcion.fundicion"), recursosBuild_Fundicion3, null, recursosCosteXtime_produce_Fundicion3, null);
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
        ArrayList<Recursos> recursosCosteXtime_coste_Granja1 = new ArrayList<>();
        recursosCosteXtime_coste_Granja1.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_Granja1 = new TreeMap<>();
        recursosCosteXtime_Granja1.put(comidaPre, recursosCosteXtime_coste_Granja1);
        new EdificiosPreCargados(16, 0, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.granja"), TRADUCCIONES_THEMA.getString("edificio.descripcion.granja"), recursosBuild_Granja1, null, recursosCosteXtime_Granja1, null);

        Map<Integer, Recursos> recursosBuild_Granja2 = new TreeMap<>();
        recursosBuild_Granja2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Granja2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Granja2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Granja2.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Granja2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Granja2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Granja2 = new ArrayList<>();
        recursosCosteXtime_coste_Granja2.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_Granja2 = new TreeMap<>();
        recursosCosteXtime_Granja2.put(comidaPre, recursosCosteXtime_coste_Granja2);
        new EdificiosPreCargados(16, 1, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.granja"), TRADUCCIONES_THEMA.getString("edificio.descripcion.granja"), recursosBuild_Granja2, null, recursosCosteXtime_Granja2, null);

        Map<Integer, Recursos> recursosBuild_Granja3 = new TreeMap<>();
        recursosBuild_Granja3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Granja3.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Granja3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Granja3.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Granja3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Granja3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Granja3 = new ArrayList<>();
        recursosCosteXtime_coste_Granja3.add(new Recursos(poblacionPre, 20));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteXtime_Granja3 = new TreeMap<>();
        recursosCosteXtime_Granja3.put(comidaPre, recursosCosteXtime_coste_Granja3);
        new EdificiosPreCargados(16, 2, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.granja"), TRADUCCIONES_THEMA.getString("edificio.descripcion.granja"), recursosBuild_Granja3, null, recursosCosteXtime_Granja3, null);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Taberna1 = new TreeMap<>();
        recursosBuild_Taberna1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Taberna1.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Taberna1.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Taberna1.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Taberna1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Taberna1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Taberna1 = new ArrayList<>();
        recursosCosteXtime_coste_Taberna1.add(new Recursos(poblacionPre, 20));
        recursosCosteXtime_coste_Taberna1.add(new Recursos(comidaPre, 15));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteX_productor_Taberna1 = new TreeMap<>();
        recursosCosteX_productor_Taberna1.put(felicidadPre, recursosCosteXtime_coste_Taberna1);
        new EdificiosPreCargados(17, 0, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.taberna"), TRADUCCIONES_THEMA.getString("edificio.descripcion.taberna"), recursosBuild_Taberna1, recursosProductores, recursosCosteX_productor_Taberna1, recursosAlmacen);

        Map<Integer, Recursos> recursosBuild_Taberna2 = new TreeMap<>();
        recursosBuild_Taberna2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Taberna2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Taberna2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Taberna2.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Taberna2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Taberna2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Taberna2 = new ArrayList<>();
        recursosCosteXtime_coste_Taberna2.add(new Recursos(poblacionPre, 20));
        recursosCosteXtime_coste_Taberna2.add(new Recursos(comidaPre, 15));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteX_productor_Taberna2 = new TreeMap<>();
        recursosCosteX_productor_Taberna2.put(felicidadPre, recursosCosteXtime_coste_Taberna2);
        new EdificiosPreCargados(17, 1, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.taberna"), TRADUCCIONES_THEMA.getString("edificio.descripcion.taberna"), recursosBuild_Taberna2, recursosProductores, recursosCosteX_productor_Taberna2, recursosAlmacen);

        Map<Integer, Recursos> recursosBuild_Taberna3 = new TreeMap<>();
        recursosBuild_Taberna3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Taberna3.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Taberna3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Taberna3.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Taberna3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Taberna3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        //
        ArrayList<Recursos> recursosCosteXtime_coste_Taberna3 = new ArrayList<>();
        recursosCosteXtime_coste_Taberna3.add(new Recursos(poblacionPre, 20));
        recursosCosteXtime_coste_Taberna3.add(new Recursos(comidaPre, 15));
        Map<RecursosPrecargados, ArrayList<Recursos>> recursosCosteX_productor_Taberna3 = new TreeMap<>();
        recursosCosteX_productor_Taberna3.put(felicidadPre, recursosCosteXtime_coste_Taberna3);
        new EdificiosPreCargados(17, 2, true, true, 0, 0, 0, TRADUCCIONES_THEMA.getString("edificio.name.taberna"), TRADUCCIONES_THEMA.getString("edificio.descripcion.taberna"), recursosBuild_Taberna3, recursosProductores, recursosCosteX_productor_Taberna3, recursosAlmacen);
        progresBar.setProgress(30);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Puerto1 = new TreeMap<>();
        recursosBuild_Puerto1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Puerto1.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Puerto1.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Puerto1.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Puerto1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Puerto1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(18, 0, false, false, 0, 0, 4, TRADUCCIONES_THEMA.getString("edificio.name.puerto"), TRADUCCIONES_THEMA.getString("edificio.descripcion.puerto"), recursosBuild_Puerto1, null, null, null);

        Map<Integer, Recursos> recursosBuild_Puerto2 = new TreeMap<>();
        recursosBuild_Puerto2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Puerto2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Puerto2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Puerto2.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Puerto2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Puerto2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(18, 1, true, true, 0, 0, 4, TRADUCCIONES_THEMA.getString("edificio.name.puerto"), TRADUCCIONES_THEMA.getString("edificio.descripcion.puerto"), recursosBuild_Puerto2, null, null, null);

        Map<Integer, Recursos> recursosBuild_Puerto3 = new TreeMap<>();
        recursosBuild_Puerto3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Puerto3.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Puerto3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Puerto3.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Puerto3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Puerto3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(18, 2, true, true, 0, 0, 4, TRADUCCIONES_THEMA.getString("edificio.name.puerto"), TRADUCCIONES_THEMA.getString("edificio.descripcion.puerto"), recursosBuild_Puerto3, null, null, null);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Cuartel1 = new TreeMap<>();
        recursosBuild_Cuartel1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Cuartel1.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Cuartel1.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Cuartel1.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Cuartel1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Cuartel1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(19, 0, true, true, 0, 0, 2, TRADUCCIONES_THEMA.getString("edificio.name.cuartel"), TRADUCCIONES_THEMA.getString("edificio.descripcion.cuartel"), recursosBuild_Cuartel1, null, null, null);

        Map<Integer, Recursos> recursosBuild_Cuartel2 = new TreeMap<>();
        recursosBuild_Cuartel2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Cuartel2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Cuartel2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Cuartel2.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Cuartel2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Cuartel2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(19, 1, true, true, 0, 0, 2, TRADUCCIONES_THEMA.getString("edificio.name.cuartel"), TRADUCCIONES_THEMA.getString("edificio.descripcion.cuartel"), recursosBuild_Cuartel2, null, null, null);

        Map<Integer, Recursos> recursosBuild_Cuartel3 = new TreeMap<>();
        recursosBuild_Cuartel3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Cuartel3.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Cuartel3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Cuartel3.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Cuartel3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Cuartel3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(19, 2, true, true, 0, 0, 2, TRADUCCIONES_THEMA.getString("edificio.name.cuartel"), TRADUCCIONES_THEMA.getString("edificio.descripcion.cuartel"), recursosBuild_Cuartel3, null, null, null);
        //
        //
        //
        Map<Integer, Recursos> recursosBuild_Asedio1 = new TreeMap<>();
        recursosBuild_Asedio1.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Asedio1.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Asedio1.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Asedio1.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Asedio1.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Asedio1.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(20, 0, true, true, 0, 0, 3, TRADUCCIONES_THEMA.getString("edificio.name.tallerArmasAsedio"), TRADUCCIONES_THEMA.getString("edificio.descripcion.tallerArmasDeAsedio"), recursosBuild_Asedio1, null, null, null);

        Map<Integer, Recursos> recursosBuild_Asedio2 = new TreeMap<>();
        recursosBuild_Asedio2.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Asedio2.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Asedio2.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Asedio2.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Asedio2.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Asedio2.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(20, 1, true, true, 0, 0, 3, TRADUCCIONES_THEMA.getString("edificio.name.tallerArmasAsedio"), TRADUCCIONES_THEMA.getString("edificio.descripcion.tallerArmasDeAsedio"), recursosBuild_Asedio2, null, null, null);

        Map<Integer, Recursos> recursosBuild_Asedio3 = new TreeMap<>();
        recursosBuild_Asedio3.put(oroPre.getId(), new Recursos(oroPre, 5));
        recursosBuild_Asedio3.put(maderaPre.getId(), new Recursos(maderaPre, 15));
        recursosBuild_Asedio3.put(piedraPre.getId(), new Recursos(piedraPre, 20));
        recursosBuild_Asedio3.put(comidaPre.getId(), new Recursos(comidaPre, 5));
        recursosBuild_Asedio3.put(hierroPre.getId(), new Recursos(hierroPre, 5));
        recursosBuild_Asedio3.put(poblacionPre.getId(), new Recursos(poblacionPre, 1));
        new EdificiosPreCargados(20, 2, true, true, 0, 0, 3, TRADUCCIONES_THEMA.getString("edificio.name.tallerArmasAsedio"), TRADUCCIONES_THEMA.getString("edificio.descripcion.tallerArmasDeAsedio"), recursosBuild_Asedio3, null, null, null);

        //
        //
        progresBar.setProgress(40);
        //
        //

        List<Recursos> recursosList0 = new ArrayList<>();
        recursosList0.add(new Recursos(oroPre, 0));
        recursosList0.add(new Recursos(maderaPre, 5));
        recursosList0.add(new Recursos(piedraPre, 15));
        recursosList0.add(new Recursos(comidaPre, 0));
        recursosList0.add(new Recursos(hierroPre, 3));
        recursosList0.add(new Recursos(felicidadPre, 1));
        recursosList0.add(new Recursos(municionPre, 0));
        new UnidadesPreCargadas(0, TRADUCCIONES_THEMA.getString("unidades.soldados.espadachines"), 0, recursosList0, 0);
        List<Recursos> recursosList1 = new ArrayList<>();
        recursosList1.add(new Recursos(oroPre, 0));
        recursosList1.add(new Recursos(maderaPre, 5));
        recursosList1.add(new Recursos(piedraPre, 15));
        recursosList1.add(new Recursos(comidaPre, 0));
        recursosList1.add(new Recursos(hierroPre, 3));
        recursosList1.add(new Recursos(felicidadPre, 1));
        recursosList1.add(new Recursos(municionPre, 0));
        new UnidadesPreCargadas(1, TRADUCCIONES_THEMA.getString("unidades.soldados.lanceros"), 0, recursosList1, 0);
        List<Recursos> recursosList2 = new ArrayList<>();
        recursosList2.add(new Recursos(oroPre, 0));
        recursosList2.add(new Recursos(maderaPre, 5));
        recursosList2.add(new Recursos(piedraPre, 15));
        recursosList2.add(new Recursos(comidaPre, 0));
        recursosList2.add(new Recursos(hierroPre, 3));
        recursosList2.add(new Recursos(felicidadPre, 1));
        recursosList2.add(new Recursos(municionPre, 0));
        new UnidadesPreCargadas(2, TRADUCCIONES_THEMA.getString("unidades.soldados.arqueros"), 100, recursosList2, 0);
        List<Recursos> recursosList3 = new ArrayList<>();
        recursosList3.add(new Recursos(oroPre, 3));
        recursosList3.add(new Recursos(maderaPre, 6));
        recursosList3.add(new Recursos(piedraPre, 65));
        recursosList3.add(new Recursos(comidaPre, 7));
        recursosList3.add(new Recursos(hierroPre, 8));
        recursosList3.add(new Recursos(felicidadPre, 3));
        recursosList3.add(new Recursos(municionPre, 5));
        new UnidadesPreCargadas(3, TRADUCCIONES_THEMA.getString("unidades.soldados.caballeros"), 50, recursosList3, 0);
        List<Recursos> recursosList4 = new ArrayList<>();
        recursosList4.add(new Recursos(oroPre, 3));
        recursosList4.add(new Recursos(maderaPre, 6));
        recursosList4.add(new Recursos(piedraPre, 65));
        recursosList4.add(new Recursos(comidaPre, 7));
        recursosList4.add(new Recursos(hierroPre, 8));
        recursosList4.add(new Recursos(felicidadPre, 3));
        recursosList4.add(new Recursos(municionPre, 5));
        new UnidadesPreCargadas(4, TRADUCCIONES_THEMA.getString("unidades.asedio.catapultas"), 50, recursosList4, 5);


        //
        //
        //
        //
        progresBar.setProgress(50);
        //TODO En pantalla
        Clan clan = new Clan("Los mejores",10);
        Clan clan2 = new Clan("Los peores",10);
        clan2.setContrasenya("1234");
        ArrayList<Recursos> jugador1ListaRecuros = new ArrayList<>();
        jugador1ListaRecuros.add(new Recursos(investigacionPre, 5));
        Jugador jugador = new Jugador("pepito", jugador1ListaRecuros);
        setJugadorPrimaryStageController(jugador);
        setClanPrimaryStageController(clan);
        progresBar.setProgress(60);
        ArrayList<Recursos> jugador2ListaRecuros = new ArrayList<>();
        jugador2ListaRecuros.add(new Recursos(investigacionPre, 25));
        Jugador jugador2 = new Jugador("juan", jugador2ListaRecuros);
        ArrayList<Recursos> jugador3ListaRecuros = new ArrayList<>();
        jugador3ListaRecuros.add(new Recursos(investigacionPre, 80));
        Jugador jugador3 = new Jugador("pedro", jugador3ListaRecuros);
        Jugador jugador4 = new Jugador("julito", jugador3ListaRecuros);
        clan2.setCreador(jugador4);
        clan2.addJugadorClan(jugador4);
        progresBar.setProgress(70);
        clan.addJugadorClan(jugador);
        clan.setCreador(jugador);
        clan.addJugadorClan(3);



        elTemaSeleccionado.espias.put(1,10);
        elTemaSeleccionado.espias.put(2,25);
        elTemaSeleccionado.espias.put(5,50);
        elTemaSeleccionado.espias.put(10,80);
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
                    Thread.sleep(20000);
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
    static void callbdCuerpoHumano(ProgressBar progresBar) {}
}

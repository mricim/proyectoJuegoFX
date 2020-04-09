package main.java.update.updater;

import main.java.update.utils.XML.GererateXMLtoUpdater;
import main.java.update.utils.XML.aXML;
import main.java.update.utils.jsoup.ListaDeRutas;
import main.java.update.utils.jsoup.ListWeb;
import org.jsoup.nodes.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import static main.java.Main.*;

public class CallUpdater {
    public static void main(String[] args) {
        //TODO File file=new File(PATH+"/bin/toUpdater.xml");
        File file = new File(PATH + "/toUpdater.xml");
        System.out.println(file.getAbsolutePath());
        try {
            Document dowloadPrincipal = ListWeb.parseFile(WEB_DOWNLOADS, "list.html");
            ///aXML toXML = chekUpdateMajor(dowloadPrincipal);
            //TODO PROVAR XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX aXML toXML = null;
            aXML toXML = null;
            if (toXML == null) {
                System.out.println("SUBVERSION");
                toXML = chekUpdateMinor(dowloadPrincipal);
            }
            if (toXML != null) {
                System.out.println(toXML);
                GererateXMLtoUpdater.generar(file);
            } else {
                System.out.println("NO UPDATES");
                //TODO TODO CORRECTO
            }
        } catch (IOException | ParserConfigurationException | TransformerException e) {
            //} catch (IOException e) {
            e.printStackTrace();
            //TODO LANZAR ERROR
        }
        try {
            //Process process = new ProcessBuilder("C:\\PathToExe\\MyExe.exe", "param1", "param2").start();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static aXML chekUpdateMajor(Document doc) throws IOException {
        TreeMap<String, ListaDeRutas> listaDowloadsApp = ListWeb.getListVersionsWeb(doc, "div[id=aplication] div[id=installer]");
        TreeMap<String, ListaDeRutas> dowloadPrincipal = null;
        ArrayList<aXML> versionesSuperiores = new ArrayList<>();

        String versionOldForIf = VERSION.substring(0, VERSION.indexOf("."));

        for (ListaDeRutas value : listaDowloadsApp.values()) {
            String rute = WEB_DOWNLOADS + value.getHref();
            dowloadPrincipal = ListWeb.getListVersionsWeb(rute, value.getFile(), null);
            //Buscamos
            for (Map.Entry<String, ListaDeRutas> stringListaDeRutasEntry : dowloadPrincipal.entrySet()) {
                String versionNew = stringListaDeRutasEntry.getKey();
                if (Integer.parseInt(versionNew.substring(versionNew.lastIndexOf(".")+1)) >= 4) {//COMPROBAR QUE la version es release x.x.>=4
                    String versionNewForIf = versionNew.substring(0, versionNew.indexOf("."));
                System.out.println(versionNewForIf+">"+versionOldForIf);
                System.out.println(versionNew+">"+VERSION);
                    if (versionNewForIf.compareTo(versionOldForIf) > 0) {//COMPROBAR QUE LA VERSION ES SUPERIOR A LA QUE TENEMOS
                        ListaDeRutas objeto = stringListaDeRutasEntry.getValue();
                        String urlFile = rute + objeto.getHref();
                        String nameFile = objeto.getFile();
                        versionesSuperiores.add(new aXML(urlFile, VERSION, versionNew, true, nameFile));
                    }
                }
            }
        }
        Collections.sort(versionesSuperiores);
        aXML toXML = null;
        for (aXML aXMLLista : versionesSuperiores) {
            toXML = aXMLLista;
            break;
        }
        return toXML;
    }

    public static aXML chekUpdateMinor(Document doc) {
        TreeMap<String, ListaDeRutas> listaDowloadsJar = ListWeb.getListVersionsWeb(doc, "div[id=aplication] div[id=jar]");
        TreeMap<String, ListaDeRutas> dowloadPrincipal = null;
        ArrayList<aXML> versionesSuperiores = new ArrayList<>();

        String versionOldForIf = VERSION.substring(VERSION.indexOf(".")+1);

        for (ListaDeRutas value : listaDowloadsJar.values()) {
            String rute = WEB_DOWNLOADS + value.getHref();
            dowloadPrincipal = ListWeb.getListVersionsWeb(rute, value.getFile(), null);
            //Buscamos
            for (Map.Entry<String, ListaDeRutas> stringListaDeRutasEntry : dowloadPrincipal.entrySet()) {
                String versionNew = stringListaDeRutasEntry.getKey();
                int posicionUltimoPunto=versionNew.lastIndexOf(".");
                if (Integer.parseInt(versionNew.substring(posicionUltimoPunto+1)) >= 4) {//COMPROBAR QUE la version es release x.x.>=4
                    String versionNewForIf = versionNew.substring(versionNew.indexOf(".")+1);
                System.out.println(versionNewForIf+">"+versionOldForIf);
                System.out.println(versionNew+">"+VERSION);
                    if (versionNewForIf.compareTo(versionOldForIf) > 0) {//COMPROBAR QUE LA VERSION ES SUPERIOR A LA QUE TENEMOS x.>?.>4
                        ListaDeRutas objeto = stringListaDeRutasEntry.getValue();
                        String urlFile = rute + objeto.getHref();
                        String nameFile = objeto.getFile();
                        versionesSuperiores.add(new aXML(urlFile, VERSION, versionNew, true, nameFile));
                    }
                }
            }
        }
        Collections.sort(versionesSuperiores);
        aXML toXML = null;
        for (aXML aXMLLista : versionesSuperiores) {
            toXML = aXMLLista;
            break;
        }
        return toXML;
    }


}

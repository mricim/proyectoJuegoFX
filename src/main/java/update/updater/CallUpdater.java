package main.java.update.updater;

import main.java.update.utils.XML.GererateXMLtoUpdater;
import main.java.update.utils.XML.XMLtoUploader;
import main.java.update.utils.jsoup.ListaDeRutas;
import main.java.update.utils.jsoup.ListWeb;
import org.jsoup.nodes.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static main.java.Main.*;

public class CallUpdater {
    public static void main(String[] args) {
        //TODO File file=new File(PATH+"/bin/toUpdater.xml");
        File file = new File(PATH + "/toUpdater.xml");
        System.out.println(file.getAbsolutePath());
        try {
            Document dowloadPrincipal = ListWeb.parseFile(WEB_DOWNLOADS, "list.html");
            XMLtoUploader toXML = chekUpdateMajor(dowloadPrincipal);
            //TODO PROVAR XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX aXML toXML = null;
            //aXML toXML = null;
            if (toXML == null) {
                System.out.println("SUBVERSION");
                toXML = chekUpdateMinor(dowloadPrincipal);
            }
            if (toXML != null) {
                System.out.println(toXML);
                GererateXMLtoUpdater.generar(file,toXML);
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

    public static XMLtoUploader chekUpdateMajor(Document doc) throws IOException {
        TreeMap<String, ListaDeRutas> listaDowloadsApp = ListWeb.getListVersionsWeb(doc, "div[id=aplication] div[id=installer]");
        TreeMap<String, ListaDeRutas> dowloadPrincipal = null;
        ArrayList<XMLtoUploader> versionesSuperiores = new ArrayList<>();

        String[] versionOld = VERSION.split("\\.");

        for (ListaDeRutas value : listaDowloadsApp.values()) {
            String rute = WEB_DOWNLOADS + value.getHref();
            dowloadPrincipal = ListWeb.getListVersionsWeb(rute, value.getFile(), null);
            //Buscamos
            for (Map.Entry<String, ListaDeRutas> stringListaDeRutasEntry : dowloadPrincipal.entrySet()) {
                String versionNew = stringListaDeRutasEntry.getKey();
                String[] versionNewSplit = versionNew.split("\\.");
                System.out.println(versionNewSplit[0]);
                if (Integer.parseInt(versionNewSplit[2]) > 3) {//COMPROBAR QUE la version es release x.x.? > 3
                    System.out.println(versionNewSplit[0] + ">" + versionOld[0]);
                    System.out.println(versionNew + ">" + VERSION);
                    if (versionNewSplit[0].compareTo(versionOld[0]) > 0) {//COMPROBAR QUE LA VERSION ES SUPERIOR A LA QUE TENEMOS ?.x.x > actual
                        System.out.println("ENTRO 1 INSTALLER");
                        ListaDeRutas objeto = stringListaDeRutasEntry.getValue();
                        String urlFile = rute + objeto.getHref();
                        String nameFile = objeto.getFile();
                        versionesSuperiores.add(new XMLtoUploader(urlFile, VERSION, versionNew, true, nameFile));
                    }
                }
            }
        }
        Collections.sort(versionesSuperiores);
        XMLtoUploader toXML = null;
        for (XMLtoUploader XMLtoUploaderLista : versionesSuperiores) {
            toXML = XMLtoUploaderLista;
            break;
        }
        return toXML;
    }

    public static XMLtoUploader chekUpdateMinor(Document doc) {
        TreeMap<String, ListaDeRutas> listaDowloadsJar = ListWeb.getListVersionsWeb(doc, "div[id=aplication] div[id=jar]");
        TreeMap<String, ListaDeRutas> dowloadPrincipal = null;
        ArrayList<XMLtoUploader> versionesSuperiores = new ArrayList<>();

        String[] versionOld = VERSION.split("\\.");

        for (ListaDeRutas value : listaDowloadsJar.values()) {
            String rute = WEB_DOWNLOADS + value.getHref();
            dowloadPrincipal = ListWeb.getListVersionsWeb(rute, value.getFile(), null);
            //Buscamos
            for (Map.Entry<String, ListaDeRutas> stringListaDeRutasEntry : dowloadPrincipal.entrySet()) {
                String versionNew = stringListaDeRutasEntry.getKey();
                String[] versionNewSplit = versionNew.split("\\.");
                System.out.println(versionNewSplit[0]);
                if (Integer.parseInt(versionNewSplit[2]) > 3) {//COMPROBAR QUE la version es release x.x.>=4
                    System.out.println(versionNewSplit[0] + "." + versionNewSplit[1] + ">" + versionOld[0] + "." + versionOld[1]);
                    System.out.println(versionNew + ">" + VERSION);
                    if (versionNewSplit[0].compareTo(versionOld[0]) >= 0) {//COMPROBAR QUE LA VERSION ES SUPERIOR O IGUAL A LA QUE TENEMOS ?.x.x >= actual
                        System.out.println("ENTRO 1 JAR");
                        if (versionNewSplit[1].compareTo(versionOld[1]) >= 0) {//COMPROBAR QUE LA VERSION ES SUPERIOR A LA QUE TENEMOS x.?.x >= actual
                            System.out.println("ENTRO 2 JAR");
                            if (versionNewSplit[2].compareTo(versionOld[2]) > 0) {//COMPROBAR QUE LA VERSION ES SUPERIOR A LA QUE TENEMOS x.x.? > actual
                                System.out.println("ENTRO 3 JAR");
                                ListaDeRutas objeto = stringListaDeRutasEntry.getValue();
                                String urlFile = rute + objeto.getHref();
                                String nameFile = objeto.getFile();
                                versionesSuperiores.add(new XMLtoUploader(urlFile, VERSION, versionNew, false, nameFile));
                            }
                        }
                    }
                }
            }
        }
        Collections.sort(versionesSuperiores);
        XMLtoUploader toXML = null;
        for (XMLtoUploader XMLtoUploaderLista : versionesSuperiores) {
            toXML = XMLtoUploaderLista;
            break;
        }
        return toXML;
    }


}

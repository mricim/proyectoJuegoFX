package main.java.test.update.updater;

import main.java.test.update.utils.XML.GererateXMLtoUpdater;
import main.java.test.update.utils.XML.XMLtoUploader;
import main.java.test.update.utils.jsoup.ListaDeRutas;
import main.java.test.update.utils.jsoup.ListWeb;
import org.jsoup.nodes.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static main.java.Main.*;

public class CallUpdater {
    public static void main(String[] args) {
        //System.out.println(System.getProperty("java.io.tmpdir"));
        //TODO File file=new File(PATH+"/bin/toUpdater.xml");
        File file = new File(PATH + "/toUpdater.xml");
        System.out.println(file.getAbsolutePath());
        try {
            Document dowloadPrincipal = ListWeb.parseFile(HOST+PROJECT, "list.html");
            XMLtoUploader toXML = chekUpdateMajor(dowloadPrincipal);
            //TODO XMLtoUploader toXML = null;
            if (toXML == null) {
                System.out.println("SUBVERSION");
                toXML = chekUpdateMinor(dowloadPrincipal);
            }
            if (toXML != null) {
                System.out.println(toXML);
                GererateXMLtoUpdater.generar(file, toXML);
            } else {
                System.out.println("NO UPDATES");
                //TODO TODO CORRECTO
            }
        } catch (IOException | ParserConfigurationException | TransformerException e) {
            //} catch (IOException e) {
            e.printStackTrace();
            //TODO LANZAR ERROR
        }
    }

    public static XMLtoUploader chekUpdateMajor(Document doc) throws IOException {
        String[] versionOld = VERSION.split("\\.");
        Map<String, ListaDeRutas> listaDowloadsApp = ListWeb.getListVersionsWeb(doc, "div[id=aplication] div[id=installer]");
        Map<String, ListaDeRutas> dowloadPrincipal = null;
        String rute = null;
        for (ListaDeRutas value : listaDowloadsApp.values()) {//donde esta la lista?
            rute = HOST+PROJECT + value.getHref();//dowloads/list.html
            dowloadPrincipal = ListWeb.getListVersionsWeb(rute, value.getFile(), null);
            break;
        }

        String rute2 = null;
        for (Map.Entry<String, ListaDeRutas> entry : dowloadPrincipal.entrySet()) {//donde esta la lista?
            if (entry.getKey().equals(OS)) {
                ListaDeRutas value = entry.getValue();
                rute2 = rute + value.getHref();//operative system/
                dowloadPrincipal = ListWeb.getListVersionsWeb(rute2, value.getFile(), null);
                break;
            }
        }
        //TODO SACAR EXCEPCION

        //Buscamos versiones
        for (Map.Entry<String, ListaDeRutas> stringListaDeRutasEntry : dowloadPrincipal.entrySet()) {
            String versionNew = stringListaDeRutasEntry.getKey();
            String[] versionNewSplit = versionNew.split("\\.");
            System.out.println(versionNew);
            if (Integer.parseInt(versionNewSplit[3]) > 3) {//COMPROBAR QUE la version es release x.x.x.? > 3
                System.out.println(versionNewSplit[0] + "." + versionNewSplit[1] + ">" + versionOld[0] + "." + versionOld[1]);
                System.out.println(versionNew + ">" + VERSION);
                if (versionNewSplit[0].compareTo(versionOld[0]) >= 0 && versionNewSplit[1].compareTo(versionOld[1]) > 0) {// ?.x.x.x >= actual &&  x.?.x.x > actual
                    System.out.println("ENTRO 1 INSTALLER");
                    ListaDeRutas objeto = stringListaDeRutasEntry.getValue();
                    return new XMLtoUploader(rute2 + objeto.getHref(), VERSION, versionNew,true,objeto.getMd5(), objeto.getPath(),objeto.getFile());
                } else if (versionNewSplit[0].compareTo(versionOld[0]) <= 0 && versionNewSplit[1].compareTo(versionOld[1]) < 0) {
                    break;
                }
            }
        }
        System.out.println("NO INSTALLER");
        return null;
    }

    public static XMLtoUploader chekUpdateMinor(Document doc) {
        String[] versionOld = VERSION.split("\\.");
        Map<String, ListaDeRutas> listaDowloadsJar = ListWeb.getListVersionsWeb(doc, "div[id=aplication] div[id=jar]");
        Map<String, ListaDeRutas> dowloadPrincipal = null;

        String rute = null;
        for (ListaDeRutas value : listaDowloadsJar.values()) {//obtenemos de donde tenemos que mirar
            rute = HOST+PROJECT + value.getHref();//DE dowloads/list.html
            dowloadPrincipal = ListWeb.getListVersionsWeb(rute, value.getFile(), null);
            break;
        }
        //Buscamos versiones
        for (Map.Entry<String, ListaDeRutas> stringListaDeRutasEntry : dowloadPrincipal.entrySet()) {
            String versionNew = stringListaDeRutasEntry.getKey();
            String[] versionNewSplit = versionNew.split("\\.");
            if (Integer.parseInt(versionNewSplit[3]) > 3) {//COMPROBAR QUE la version es release x.x.x.? > 3
                System.out.println(versionNewSplit[0] + "." + versionNewSplit[1] + "." + versionNewSplit[2] + ">" + versionOld[0] + "." + versionOld[1] + "." + versionOld[2]);
                System.out.println(versionNew + ">" + VERSION);
                if (versionNewSplit[0].compareTo(versionOld[0]) >= 0 && versionNewSplit[1].compareTo(versionOld[1]) >= 0 && versionNewSplit[2].compareTo(versionOld[2]) > 0) {// ?.x.x.x > actual &&  x.?.x.x >= actual &&  x.x.?.x > actual
                    System.out.println("ENTRO 1 JAR");
                    ListaDeRutas objeto = stringListaDeRutasEntry.getValue();
                    return new XMLtoUploader(rute + objeto.getHref(), VERSION, versionNew,true,objeto.getMd5(), objeto.getPath(),objeto.getFile());
                } else if (versionNewSplit[0].compareTo(versionOld[0]) <= 0 && versionNewSplit[1].compareTo(versionOld[1]) <= 0 && versionNewSplit[2].compareTo(versionOld[2]) < 0) {
                    break;
                }
            }
        }
        System.out.println("NO JAR");
        return null;
    }


}

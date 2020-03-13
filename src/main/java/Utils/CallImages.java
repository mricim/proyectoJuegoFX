package main.java.Utils;

import javafx.scene.image.Image;
import main.java.temas.Temas;

import java.io.File;
import java.util.HashMap;

import static main.java.jugadores.Jugador.ERRORIMAGE;
import static main.java.temas.Temas.*;

public class CallImages {
    private static HashMap<String, Image> listImage = new HashMap<>();

    private CallImages(boolean genericOrTemaF, String rute, String name, int width, int height) {
        String ruteName = rute + name;
        Image image;
        String ruteUse;
        if (!genericOrTemaF) {
            ruteUse = PATH_TEMA_USE + rute + name;
        } else {
            ruteUse = PATH_USE + rute + name;
        }
        try {
            try {
                File file = new File(ruteUse + ".png");
                if (!file.exists()) {
                    throw new Exception("nove");
                }
                image = new Image(file.toURI().toString(), width, height, true, true);
                //System.out.println(ruteUse + ".png");

            } catch (Exception e) {
                String format = searchFiles(PATH_TEMA_USE + rute, name);
                File file = new File(ruteUse + "." + format);
                if (!file.exists()) {
                    throw new Exception("nove");
                }
                image = new Image(file.toURI().toString(), width, height, true, true);
                System.out.println("catch - " + file.toURI().toString());
            }
            listImage.put(ruteName, image);
        } catch (Exception e) {
            listImage.put(ruteName, ERRORIMAGE);
            System.err.println("Error: CallImages (Image not found) = " + ruteUse);
        }
    }

    private String searchFiles(String posibleRute, String name) {
        File filex = new File(posibleRute);
        File[] list = filex.listFiles();
        for (File file : list) {
            String nameFile = file.getName();
            if (name.equalsIgnoreCase(nameFile.replaceFirst("[.][^.]+$", ""))) {
                return nameFile.split("\\.", 2)[1];
            }
        }
        System.err.println("Error: CallImages (Extension not found) = " + posibleRute+" "+name);
        return "";
    }

    public static Image getImageNoTema(String rute, String name) {
        return getImage(true, rute, name, 100, 100);
    }

    public static Image getImage(String rute, String name) {
        return getImage(false, rute, name, 100, 100);
    }

    public static Image getImageNoTema(String rute, String name, int width, int height) {
        return getImage(true, rute, name, width, height);
    }

    public static Image getImage(String rute, String name, int width, int height) {
        return getImage(false, rute, name, width, height);
    }

    private static Image getImage(boolean genericOrTemaF, String rute, String name, int width, int height) {
        String ruteName = rute + name;
        if (listImage.containsKey(ruteName)) {
            return listImage.get(ruteName);
        } else {
            try {
                new CallImages(genericOrTemaF, rute, name, width, height);
                return listImage.get(ruteName);
            } catch (Exception e) {
            }
        }
        return ERRORIMAGE;
    }
}

package main.java.Utils;

import javafx.scene.image.Image;

import java.io.File;
import java.util.HashMap;

import static main.java.Jugadores.Jugador.ERRORIMAGE;

public class CallImages {
    private static final String RUTE = System.getProperty("user.dir");
    private static final String RUTEEXTERNAL = System.getProperty("user.dir").replace("proyectoJuegoFX", "") + "/videoJuegoresources/images/";
    public static final String RUTEIMAGES = RUTE + "/src/main/resources/images/";

    public static String RUTEIMAGE = "../../resources/images/";
    private static HashMap<String, Image> listImage = new HashMap<>();

    private CallImages(String rute, String name) {
        this(rute, name, 100, 100);
    }

    private CallImages(String rute, String name, int width, int height) {
        String ruteName = rute + name;
        Image image;
        if (new File(RUTEEXTERNAL).exists()) {
            try {
                try {
                    File file = new File(RUTEEXTERNAL + ruteName + ".png");
                    if (!file.exists()) {
                        throw new Exception("nove");
                    }
                    image = new Image(file.toURI().toString(), width, height, true, true);
                    System.out.println(RUTEEXTERNAL + ruteName + ".png");

                } catch (Exception e) {
                    String format = searchFiles(RUTEEXTERNAL + rute, name);
                    File file = new File(RUTEEXTERNAL + ruteName + "."+format);
                    if (!file.exists()) {
                        throw new Exception("nove");
                    }
                    image = new Image(file.toURI().toString(), width, height, true, true);
                    System.out.println("catch - " + file.toURI().toString());
                }
                listImage.put(ruteName, image);
            } catch (Exception e) {
                listImage.put(ruteName, ERRORIMAGE);
                //Main.class.getResource(rute).toURI();
                System.out.println(RUTEEXTERNAL + ruteName);
                System.err.println("Error: CallImages (Image not found) = " + rute + " " + name);
            }

        } else {
            try {
                try {
                    System.out.println(RUTEIMAGE + ruteName + ".png");
                    image = new Image(getClass().getResource(RUTEIMAGE + ruteName + ".png").toExternalForm(), width, height, true, true);
                } catch (Exception e) {
                    image = new Image(getClass().getResource(RUTEIMAGE + ruteName + "." + searchFiles(RUTEIMAGES + rute, name)).toExternalForm(), width, height, true, true);
                }
                listImage.put(ruteName, image);
            } catch (Exception e) {
                listImage.put(ruteName, ERRORIMAGE);
                System.err.println("Error: CallImages (Image not found) = " + rute + " " + name);
            }
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
        System.err.println("Error: CallImages (Extension not found) = " + posibleRute + " " + name);
        return "";
    }

    public static Image getImage(String rute, String name) {
        String ruteName = rute + name;
        if (listImage.containsKey(ruteName)) {
            return listImage.get(ruteName);
        } else {
            new CallImages(rute, name);
            try {
                return listImage.get(ruteName);
            } catch (Exception e) {
            }
        }
        return ERRORIMAGE;
    }
}

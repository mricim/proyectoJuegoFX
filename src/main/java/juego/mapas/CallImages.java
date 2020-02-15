package main.java.juego.mapas;

import javafx.scene.image.Image;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

import static main.java.juego.mapas.city.Ciudad.ERRORIMAGE;

public class CallImages {
    public static final String RUTE=System.getProperty("user.dir");
    public static final String RUTEIMAGES=RUTE+"/src/main/resources/images/";

    public static String RUTEIMAGE = "../../../resources/images/";
    private static HashMap<String, Image> listImage = new HashMap<>();

    public CallImages(String rute, String name) {
        System.out.println(RUTEIMAGE + rute + name + ".png");
        try {

            Image image = new Image(getClass().getResource(RUTEIMAGE + rute + name +"."+ searchFiles(RUTEIMAGES+rute,name)).toExternalForm(), 100, 100, false, true);
            //Image image = new Image(getClass().getResource(RUTEIMAGES + rute + name + ".png").toExternalForm(), 100, 100, false, true);
            listImage.put(name, image);
        } catch (Exception e) {
            listImage.put(name,ERRORIMAGE);
            System.err.println("Error: CallImages (Image not found) = "+rute+" "+name);
        }
    }

    private String searchFiles(String posibleRute,String name) {
        File filex = new File(posibleRute);
        File[] list=filex.listFiles();
        for (File file : list) {
            String nameFile=file.getName();
            if (name.equalsIgnoreCase(nameFile.replaceFirst("[.][^.]+$", ""))){
                return nameFile.split("\\.", 2)[1];
            }
        }
        System.err.println("Error: CallImages (Extension not found) = "+posibleRute+" "+name);
        return "";
    }

    public CallImages(String rute, String name, int width, int height) {
        new Image(getClass().getResource(rute + name + ".png").toExternalForm(), width, height, false, true);
    }

    public static Image getImage(String rute, String name) {
        if (listImage.containsKey(name)) {
            return listImage.get(name);
        } else {
            new CallImages(rute, name);
            try {
                return listImage.get(name);
            }catch (Exception e){}
        }
        return ERRORIMAGE;
    }
}

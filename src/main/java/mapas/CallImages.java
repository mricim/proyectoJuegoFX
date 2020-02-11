package main.java.mapas;

import javafx.scene.image.Image;

import java.util.HashMap;

import static main.java.mapas.city.Main.ERRORIMAGE;

public class CallImages {
    public static String RUTEIMAGES = "../../resources/images/";
    private static HashMap<String, Image> listImage = new HashMap<>();

    public CallImages(String rute, String name) {
        System.out.println(RUTEIMAGES + rute + name + ".png");
        try {
            Image image = new Image(getClass().getResource(RUTEIMAGES + rute + name + ".png").toExternalForm(), 100, 100, false, true);
            listImage.put(name, image);
        } catch (Exception e) {
            listImage.put(name,ERRORIMAGE);
            System.err.println("Error: CallImages (Image not found)");
        }
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

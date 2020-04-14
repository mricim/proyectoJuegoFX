package main.java.temas;

import main.java.Main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static main.java.Main.pathImagesInternal;

public class Temas {
    private static boolean filesIsExternal;
    public static String pathImagesExternal = System.getProperty("user.dir").replace("proyectoJuegoFX", "") + "/res/images/";


    private static String pathImagesTemaInternal = pathImagesInternal + "temas/";
    private static String pathImagesTemaExternal = pathImagesExternal + "temas/";
    public static List<String> arrayListTemas = obtainListaTemas();//Justo antes de obtainListaTemas()

    private static List<String> obtainListaTemas() {
        List<String> listaTemas = new ArrayList<>();

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
        for (
                int i = 0;
                i < x; i++) {
            String str = f[i].getName().replaceAll("[0-9]", "").replaceAll("(.)([A-Z])", "$1 $2");
            listaTemas.add((str.substring(0, 1).toUpperCase() + str.substring(1)));
        }
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

}

package main.java.temas;

import main.java.Main;
import main.java.Utils.CallImages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static main.java.Utils.PrimaryStageControler.NAME_TEMA;

public class Temas {
    public static List<String> arrayListTemas = obtainListaTemas();
    private static String pathImagesTema = Main.RUTEINTERNAL + "resources/images/temas/";
    private static String pathImagesTemaExternal = CallImages.RUTEEXTERNAL + "temas/";
    public static File temasDirectoryInternalFile = new File(pathImagesTema);
    public static File temasDirectoryExternalFile = new File(pathImagesTemaExternal);
    private static List<String> obtainListaTemas() {
        List<String> listaTemas = new ArrayList<>();
        File[] f;
        if (temasDirectoryInternalFile.exists()) {
            f = temasDirectoryInternalFile.listFiles();
        } else {
            f = temasDirectoryExternalFile.listFiles();
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

    public static String PATH_TEMA= pathImagesTema+NAME_TEMA+"/";//TODO AQUI ME QUEDO...

}

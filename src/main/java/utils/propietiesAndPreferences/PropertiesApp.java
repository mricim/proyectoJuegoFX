package main.java.utils.propietiesAndPreferences;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.stream.Stream;

import static main.java.Main.*;

public class PropertiesApp {
    public static void createPropieties() throws IOException {
        boolean exist=true;
        System.out.println(PATH);
        File file = new File(PATH + "\\config.properties");
        if (file.exists()){
            file.delete();
        }else{
            exist=false;
        }
        try (OutputStream outputStream = new FileOutputStream(file)) {

            Properties prop = new Properties();
            // set key and value
            prop.setProperty("name", NAME);
            prop.setProperty("version", VERSION);
            prop.setProperty("host", HOST);
            prop.setProperty("project", PROJECT);
            prop.setProperty("fileList", FILELIST);

            // save a properties file
            prop.store(outputStream, "ericcasanova.m@gmail.com");
        } catch (IOException io) {
            io.printStackTrace();
        }
        if (!exist){
            String iniciar=PATH+"/";
            String app=iniciar+"run.exe";
            Runtime.getRuntime().exec(app, null, new File(iniciar));
            System.exit(0);
        }
    }
}

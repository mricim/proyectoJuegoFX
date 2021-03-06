package main.java.utils.propietiesAndPreferences;

import java.io.*;
import java.util.Properties;

import static main.java.Main.*;

public class PropertiesApp {
    private static File filePropieties;

    public static void createPropieties() throws IOException {
        //boolean exist=true;
        System.out.println("PATH var: "+PATH);
        System.out.println("PropFileName:"+ propFile);
        filePropieties=new File(propFile);
        if (filePropieties.exists()){
            filePropieties.delete();
        }
        //else{
//            exist=false;
//        }
        try (OutputStream outputStream = new FileOutputStream(filePropieties)) {

            Properties prop = new Properties();
            // set key and value
            prop.setProperty("name", NAME);
            prop.setProperty("version", VERSION);
            prop.setProperty("host", HOST);
            prop.setProperty("project", PROJECT);
            prop.setProperty("fileList", FILELIST);

            // save a properties file
            prop.store(outputStream, "Owner: ericcasanova.m@gmail.com");
        } catch (IOException io) {
            io.printStackTrace();
        }
//        if (!exist){
//            String iniciar=PATH+"/";
//            String app=iniciar+"run.exe";
//            Runtime.getRuntime().exec(app, null, new File(iniciar));
//            System.exit(0);
//        }
    }
}

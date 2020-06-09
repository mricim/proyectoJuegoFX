package main.java.utils.propietiesAndPreferences;

import main.java.Main;
import main.java.utils.PrimaryStageControler;

import java.io.*;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import static main.java.Main.*;
import static main.java.utils.PrimaryStageControler.LOCALE;

public class PreferencesApp {
    private static File filePreferences;

    private static String language;
    private String id;
    private String name;
    private String email;
    private String password;
    private String date_register;
    private String last_conecxion;


    public static boolean createFilePrefereces() throws IOException {
        //boolean exist=true;
        filePreferences = new File(prefFile);
        if (! filePreferences.exists()) {
            if (filePreferences.createNewFile()){
                return false;
            }
            throw new IOException(prefFile+" Not created");
        }else {
            return true;
        }
    }
    public static void readFilePrefereces() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = new FileInputStream(filePreferences);
        prop.load(inputStream);

        LOCALE = new Locale(prop.getProperty("language"));
/*
        // get the property value and print it out
        name = prop.getProperty("name");
        versionOld = prop.getProperty("version");
        host = prop.getProperty("host");
        project = prop.getProperty("project");
        fileList = prop.getProperty("fileList");

        System.out.println("name= \"" + name + "\"");
        System.out.println("version= \"" + versionOld + "\"");
        System.out.println("host= \"" + host + "\"");
        System.out.println("project= \"" + project + "\"");
        System.out.println("fileList= \"" + project + "\"");
        System.out.println("Program Ran on " + new Date(System.currentTimeMillis()));
*/
        inputStream.close();

    }
    public static void writeFilePrefereces() {
        try (OutputStream outputStream = new FileOutputStream(filePreferences)) {
            Properties prop = new Properties();
            // set key and value
            prop.setProperty("language", PrimaryStageControler.LOCALE.getLanguage());
            /*
            prop.setProperty("name", NAME);
            prop.setProperty("version", VERSION);
            prop.setProperty("host", HOST);
            prop.setProperty("project", PROJECT);
            prop.setProperty("fileList", FILELIST);
             */

            // save a properties file
            prop.store(outputStream, "Owner: ericcasanova.m@gmail.com");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     *
     * @return
     */
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file the file or null to remove the path
     */

    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
            // Update the stage title.
            //PrimaryStageControler.getStagePrimaryStageController().setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");
            // Update the stage title.
            //primaryStage.setTitle("AddressApp");
        }
    }
}

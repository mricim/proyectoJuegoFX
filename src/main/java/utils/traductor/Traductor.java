package main.java.utils.traductor;

import javafx.scene.control.ComboBox;
import main.java.utils.PrimaryStageControler;
import main.java.utils.tagsFX.CustomComboBoxImager;

import java.util.ArrayList;

public class Traductor {
    public static ArrayList<String> listaIdiomasPath = new ArrayList<>();

    public static ComboBox<String> traduccciones(ArrayList<String> arrayList, Class clasS) {
        ComboBox<String> comboBox = CustomComboBoxImager.createCombox(arrayList);
        String nameLocale = PrimaryStageControler.TRADUCCIONES.getLocale().getLanguage();
        String elValue = getLanguagePathEquals(nameLocale,clasS);
        comboBox.setValue(elValue);

        return comboBox;
    }

    public static String getLanguageEquals(String nameLocale, Class clasS) {
        for (String s : listaIdiomasPath) {
            if (nameLocale.equals(s.substring(1 + s.lastIndexOf("/")))) {
                return nameLocale;
            }
        }
        System.err.println(clasS.getClass() + " Not" + nameLocale + " --> " + listaIdiomasPath);
        return null;
    }
    public static String getLanguagePathEquals(String nameLocale, Class clasS) {
        for (String s : listaIdiomasPath) {
            if (nameLocale.equals(s.substring(1 + s.lastIndexOf("/")))) {
                return s;
            }
        }
        System.err.println(clasS.getClass() + " Not" + nameLocale + " --> " + listaIdiomasPath);
        return null;
    }
}

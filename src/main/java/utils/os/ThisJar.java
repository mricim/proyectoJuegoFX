package main.java.utils.os;

import main.java.Main;

public class ThisJar {
    public static boolean checkJar(){
        if (Main.class.getResource("Main.class").toString().split(":")[0].equals("file")){
            return false;
        }
        return true;
    }
}

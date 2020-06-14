package main.java.utils.os;

import main.java.Main;

public class ThisJar {
    public static boolean checkJar(){
        return !Main.class.getResource("Main.class").toString().split(":")[0].equals("file");
    }
}

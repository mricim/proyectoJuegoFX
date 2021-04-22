package main.java.dataBase;

import main.java.utils.Encriptacio;

public class PersonSQL {
    private final long id;
    private final String name;
    private final String nameEncripted;
    private final String email;
    private final String emailEncripted;
    private final String password;
    private final String date_register;
    private final String last_conexion;

    public PersonSQL(long id, String nameEncripted, String emailEncripted, String password, String date_register,boolean nada) {//todo borrar cuando este la DB
        this.id = id;
        this.name = nameEncripted;
        this.nameEncripted = nameEncripted;
        this.email = emailEncripted;
        this.emailEncripted = emailEncripted;
        this.password = password;
        this.date_register = date_register;
        this.last_conexion = null;
    }
    public PersonSQL(long id, String nameEncripted, String emailEncripted, String password, String date_register) {
        this.id = id;
        this.name = Encriptacio.decrypt(nameEncripted);
        this.nameEncripted = nameEncripted;
        this.email = Encriptacio.decrypt(emailEncripted);
        this.emailEncripted = emailEncripted;
        this.password = password;
        this.date_register = date_register;
        this.last_conexion = null;
    }
    public PersonSQL(long id, String nameEncripted, String emailEncripted, String password, String date_register, String last_conexion) {
        this.id = id;
        this.name = Encriptacio.decrypt(nameEncripted);
        this.nameEncripted = nameEncripted;
        this.email = Encriptacio.decrypt(emailEncripted);
        this.emailEncripted = emailEncripted;
        this.password = password;
        this.date_register = date_register;
        this.last_conexion = last_conexion;
    }

    public long getId() {
        return id;
    }

    public String getNameEncripted() {
        return nameEncripted;
    }

    public String getName() {
        return name;
    }

    public String getEmailEncripted() {
        return emailEncripted;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDate_register() {
        return date_register;
    }

    public String getLast_conexion() {
        return last_conexion;
    }
}

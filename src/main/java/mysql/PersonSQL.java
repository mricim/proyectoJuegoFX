package main.java.mysql;

import main.java.utils.Encriptacio;

public class PersonSQL {
    private final int id;
    private final String name;
    private final String nameEncripted;
    private final String email;
    private final String emailEncripted;
    private final String password;
    private final String date_register;
    private final String last_conexion;

    public PersonSQL(int id, String nameEncripted, String emailEncripted, String password, String date_register, String last_conexion) {
        this.id = id;
        this.name = Encriptacio.decrypt(nameEncripted);
        this.nameEncripted = nameEncripted;
        this.email = Encriptacio.decrypt(emailEncripted);
        this.emailEncripted = emailEncripted;
        this.password = password;
        this.date_register = date_register;
        this.last_conexion = last_conexion;
    }

    public int getId() {
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

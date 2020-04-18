package main.java.test.TableView;


import javafx.scene.image.Image;

public class DatosPersona {
    Image image;
    String name;
    int dni;

    public DatosPersona(Image image, String name, int dni) {
        this.image = image;
        this.name = name;
        this.dni = dni;
    }

    public DatosPersona(Image image, int i, int i1, boolean b, boolean b1) {
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
}

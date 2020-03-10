package main.java.juego.mapas;

import javafx.scene.image.Image;
import main.java.Utils.ImageGetter;
import main.java.juego.mapas.ciudad.CiudadController;

public class Recursos {
    //oro=0;
    //madera=1;
    //piedra=2;
    //comida=3;
    //hierro=4;
    //poblacion=5;
    //felicidad=6;
    //investigacion=7
    private RecursosPrecargados recursosPrecargados;
    private int cantidad;

    public Recursos(int idRecursosPrecargados, int cantidad) {
        this(RecursosPrecargados.recursosPrecargadosList.get(idRecursosPrecargados), cantidad);
    }

    public Recursos(RecursosPrecargados recursosPrecargados, int cantidad) {
        this.recursosPrecargados = recursosPrecargados;
        this.cantidad = cantidad;
    }

    public RecursosPrecargados getRecursosPrecargados() {
        return recursosPrecargados;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getName() {
        return recursosPrecargados.getName();
    }

    public Image getImage() {
        return recursosPrecargados.getImage();
    }

    public int getId() {
        return recursosPrecargados.getId();
    }
}

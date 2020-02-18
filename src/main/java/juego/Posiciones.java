package main.java.juego;


public abstract class Posiciones {
    private int y;
    private int x;
//Create objects contain

    public Posiciones(int y, int x) {
        this.y = y;
        this.x = x;
        //this.object=object
        //add list objects *
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "Posiciones{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }
}

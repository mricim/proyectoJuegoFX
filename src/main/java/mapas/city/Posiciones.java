package main.java.mapas.city;

class Posiciones {
    private int y;
    private int x;
    private String pathImage;
    private String pathImageOnMouseOver;

    public Posiciones(int y, int x, String pathImage,String pathImageOnMouseOver) {
        this.y = y;
        this.x = x;
        this.pathImage = pathImage;
        this.pathImageOnMouseOver= pathImageOnMouseOver;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public String getPathImageOnMouseOver() {
        return pathImageOnMouseOver;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public void setPathImageOnMouseOver(String pathImageOnMouseOver) {
        this.pathImageOnMouseOver = pathImageOnMouseOver;
    }
}

package main.java.utils.tagsFX;

import javafx.scene.image.Image;

public class TextImage {
    private final String string;
    private final Image image;
    private final int width;
    private final int height;

    public TextImage(String string, Image image) {
        this.string = string;
        this.image = image;
        this.width = 25;
        this.height = 25;
    }
    public TextImage(String string, Image image,int width,int height) {
        this.string = string;
        this.image = image;
        this.width = width;
        this.height = height;
    }

    public String getString() {
        return string;
    }

    public Image getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

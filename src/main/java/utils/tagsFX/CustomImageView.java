package main.java.utils.tagsFX;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CustomImageView extends ImageView{

    public CustomImageView(Image image, int width,int height){
        this.setImage(image);
        this.setFitWidth(width);
        this.setFitHeight(height);
    }
}

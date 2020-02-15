package main.java.juego.mapas;

import com.sun.istack.internal.Nullable;
import javafx.scene.image.Image;

public interface ImageGetter {

    abstract Image getImage( /* call "getImage(RUTEIMAGES, image)" */ );
    default Image getImage(String RUTEIMAGES, String image) {
        return CallImages.getImage(RUTEIMAGES, image);
    }
    @Nullable
    abstract Image getImageClicable( /* call "getImageClicable(RUTEIMAGES, image)" */ );
    @Nullable
    default Image getImageClicable(String RUTEIMAGES, String imageClicable) {
        return CallImages.getImage(RUTEIMAGES, imageClicable);
    }
}

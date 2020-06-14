package main.java.utils;

import com.sun.istack.internal.Nullable;
import javafx.scene.image.Image;

public interface ImageGetter {

    Image getImage( /* call "getImage(RUTEIMAGES, image)" */);

    default Image getImage(String RUTEIMAGES, String image) {
        return CallImages.getImage(RUTEIMAGES, image);
    }

    @Nullable
    Image getImageClicable( /* call "getImageClicable(RUTEIMAGES, image)" */);

    @Nullable
    default Image getImageClicable(String RUTEIMAGES, String imageClicable) {
        return CallImages.getImage(RUTEIMAGES, imageClicable);
    }

    @Nullable
    Image getImageIcon( /* call "getImageIcon(RUTEIMAGES, image)" */);

    @Nullable
    default Image getImageIcon(String RUTEIMAGES, String imageIcon) {
        return CallImages.getImage(RUTEIMAGES, imageIcon);
    }
}

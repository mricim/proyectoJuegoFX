package main.java.Utils;

import com.sun.istack.internal.Nullable;
import javafx.scene.image.Image;

public interface ImageGetter {

    abstract Image getImage( /* call "getImage(RUTEIMAGES, image)" */);

    default Image getImage(String RUTEIMAGES, String image) {
        return CallImages.getImage(RUTEIMAGES, image);
    }

    @Nullable
    abstract Image getImageClicable( /* call "getImageClicable(RUTEIMAGES, image)" */);

    @Nullable
    default Image getImageClicable(String RUTEIMAGES, String imageClicable) {
        return CallImages.getImage(RUTEIMAGES, imageClicable);
    }

    @Nullable
    abstract Image getImageIcon( /* call "getImageIcon(RUTEIMAGES, image)" */);

    @Nullable
    default Image getImageIcon(String RUTEIMAGES, String imageIcon) {
        return CallImages.getImage(RUTEIMAGES, imageIcon);
    }
}

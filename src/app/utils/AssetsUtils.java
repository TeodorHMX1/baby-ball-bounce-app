package app.utils;

import javax.swing.*;

public class AssetsUtils {

    public AssetsUtils()
    {
        // empty constructor
    }

    // method that retries the relevant image based on the image path
    // the path is passed as a parameter into the function
    // the parameter name is 'img'
    public ImageIcon getImageIcon(String img)
    {
        return new ImageIcon(img);
    }

    public static final String IMG_BRICKS = "assets/images/bricks2.jpg";
    public static final String IMG_WHITE_SQUARE = "assets/images/white32x32.jpg";


}

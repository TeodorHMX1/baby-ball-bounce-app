/**
 * Program: Assignment 2: Application – Baby Ball Bounce
 * Filename: AssetsUtils.java
 *
 * @author: © Teodor Grigor (GitHub - TeodorHMX1)
 * Course: BSc Computing Year 1
 * Module: CSY1020 Problem Solving & Programming
 * Tutor: Gary Hill
 * @version: 1.0 AssetsUtils created
 * Date: 18/05/20
 */
package app.utils;

import javax.swing.*;

/**
 * AssetsUtils can be used to retrieve images without knowing the path
 */
public class AssetsUtils
{

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

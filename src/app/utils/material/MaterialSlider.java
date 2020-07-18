/**
 * Program: Assignment 2: Application – Baby Ball Bounce
 * Filename: MaterialSlider.java
 *
 * @author: © Teodor Grigor (GitHub - TeodorHMX1)
 * Course: BSc Computing Year 1
 * Module: CSY1020 Problem Solving & Programming
 * Tutor: Gary Hill
 * @version: 1.0 File Created
 * Date: 21/06/20
 */
package app.utils.material;

import javax.swing.*;

/**
 * MaterialSlider Class creates a slider with a pre-defined min
 * and max
 */
public class MaterialSlider extends JSlider
{

    public MaterialSlider(int min, int max)
    {
        super(min, max);
    }

}

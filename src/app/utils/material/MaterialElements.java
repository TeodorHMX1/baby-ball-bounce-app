/**
 * Program: Assignment 2: Application – Baby Ball Bounce
 * Filename: MaterialElements.java
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
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

/**
 * MaterialElements Class can be used to create different
 * material elements such as buttons, labels and sliders
 */
public class MaterialElements
{

    public MaterialElements()
    {

    }

    public MaterialButton createButton(Icon icon, String text)
    {
        MaterialButton btn = new MaterialButton(text);
        btn.setBackground(new Color(255, 255, 255));
        btn.setHoverBackgroundColor(new Color(243, 243, 243));
        btn.setPressedBackgroundColor(new Color(230, 230, 230));
        btn.setIcon(icon);
        btn.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(0, 0, 0), 1),
                        BorderFactory.createEmptyBorder(3, 8, 3, 8)
                )
        );
        return btn;
    }

    public MaterialLabel createLabel(String text)
    {

        return new MaterialLabel(text);

    }

    public MaterialSlider createHorizontalSlider(int min, int max, int current)
    {

        MaterialSlider materialSlider = new MaterialSlider(min, max);
        materialSlider.setMajorTickSpacing(1);
        materialSlider.setMinorTickSpacing(1);
        materialSlider.setBackground(new Color(241, 241, 241));
        materialSlider.setPaintTicks(true);
        materialSlider.setPaintLabels(true);
        materialSlider.setValue(current);
        Font font = new Font("Serif", Font.PLAIN, 13);
        materialSlider.setFont(font);
        return materialSlider;

    }

}

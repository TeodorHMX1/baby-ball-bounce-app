/**
 * Program: Assignment 2: Application – Baby Ball Bounce
 * Filename: RoundedBorder.java
 *
 * @author: © Teodor Grigor (GitHub - TeodorHMX1)
 * Course: BSc Computing Year 1
 * Module: CSY1020 Problem Solving & Programming
 * Tutor: Gary Hill
 * @version: 1.2 radius added
 * Date: 18/05/20
 */

package app.utils;

import javax.swing.border.Border;
import java.awt.*;

/**
 * RoundedBorder class can be used to apply borders to an object
 */
public class RoundedBorder implements Border
{

    private int radius;

    public RoundedBorder(int radius)
    {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c)
    {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    public boolean isBorderOpaque()
    {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

}

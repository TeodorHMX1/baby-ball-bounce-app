/**
 * Program: Assignment 2: Application – Baby Ball Bounce
 * Filename: MaterialButton.java
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
import java.awt.*;

/**
 * MaterialButton is a class that extends JButton
 *
 * It has the role to create a material button with
 * fewer steps than creating a JBUtton
 */
public class MaterialButton extends JButton
{

    private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;

    public MaterialButton()
    {
        this(null);
    }

    public MaterialButton(String text)
    {
        super(text);
        super.setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        if (getModel().isPressed())
        {
            g.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover())
        {
            g.setColor(hoverBackgroundColor);
        } else
        {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    public void setContentAreaFilled(boolean b)
    {
    }

    public Color getHoverBackgroundColor()
    {
        return hoverBackgroundColor;
    }

    public void setHoverBackgroundColor(Color hoverBackgroundColor)
    {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public Color getPressedBackgroundColor()
    {
        return pressedBackgroundColor;
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor)
    {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }
}

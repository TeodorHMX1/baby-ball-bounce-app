/**
 * Program: Assignment 2: Application – Baby Ball Bounce
 * Filename: Menu UI.java
 *
 * @author: © Teodor Grigor (GitHub - TeodorHMX1)
 * Course: BSc Computing Year 1
 * Module: CSY1020 Problem Solving & Programming
 * Tutor: Gary Hill
 * @version: 1.0 File created
 * Date: 17/05/20
 */
package app.menu;

import javax.swing.*;
import java.awt.*;

/**
 * MenuUI changes the style for an element
 */
public class MenuUI extends JMenuBar
{

    @Override
    protected void paintComponent(Graphics g)
    {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

    }
}

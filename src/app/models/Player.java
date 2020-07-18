/**
 * Program: Assignment 2: Application – Baby Ball Bounce
 * Filename: Player.java
 *
 * @author: © Teodor Grigor (GitHub - TeodorHMX1)
 * Course: BSc Computing Year 1
 * Module: CSY1020 Problem Solving & Programming
 * Tutor: Gary Hill
 * @version: 1.6 Team variable removed
 * Date: 12/05/20
 */
package app.models;

import javax.swing.*;

/**
 * Player Class contains all the relevant details for the player
 */
public class Player
{

    private final ImageIcon playerImg;

    public Player(ImageIcon image)
    {
        this.playerImg = image;
    }

    public ImageIcon getPlayerImg()
    {
        return playerImg;
    }

}

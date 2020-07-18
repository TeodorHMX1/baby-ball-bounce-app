/**
 * Program: Assignment 2: Application – Baby Ball Bounce
 * Filename: Ball.java
 *
 * @author: © Teodor Grigor (GitHub - TeodorHMX1)
 * Course: BSc Computing Year 1
 * Module: CSY1020 Problem Solving & Programming
 * Tutor: Gary Hill
 * @version: 1.3 Ball location point deleted
 * Date: 18/06/20
 */
package app.models;

import app.utils.enums.Directions;

import javax.swing.*;

/**
 * Ball Class contains all the relevant details for the ball
 */
public class Ball
{

    private final ImageIcon ballImage;

    public Ball(ImageIcon image)
    {
        this.ballImage = image;
    }

    public ImageIcon getBallImage()
    {
        return ballImage;
    }
}

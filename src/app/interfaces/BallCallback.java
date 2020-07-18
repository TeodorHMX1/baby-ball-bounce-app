/**
 * Program: Assignment 2: Application – Baby Ball Bounce
 * Filename: BallCallback.java
 *
 * @author: © Teodor Grigor (GitHub - TeodorHMX1)
 * Course: BSc Computing Year 1
 * Module: CSY1020 Problem Solving & Programming
 * Tutor: Gary Hill
 * @version: 1.0 File created
 * Date: 02/06/20
 */
package app.interfaces;

import app.utils.enums.Directions;

public interface BallCallback
{

    interface AutoMoveBall
    {
        void moveTo(Directions direction);
    }

}

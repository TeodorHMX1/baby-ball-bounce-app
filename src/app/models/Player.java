package app.models;

import javax.swing.*;
import java.awt.*;

public class Player
{

    private ImageIcon babyImage;
    private int x, y;
    private int team;
    private int id;

    public Player(int x, int y, ImageIcon image, int team, int id)
    {
        this.x = x;
        this.y = y;
        this.babyImage = image;
        this.id = id;
        this.team = team;
    }

}

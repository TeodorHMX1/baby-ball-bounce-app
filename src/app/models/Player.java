package app.models;

import java.awt.*;

public class Player
{

    private Image babyImage;
    private int x, y;
    private int team;
    private int id;

    public Player(int x, int y, Image image, int team, int id)
    {
        this.x = x;
        this.y = y;
        this.team = team;
    }

}

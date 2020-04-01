package app.models;

import javax.swing.*;

public class Player {

    private ImageIcon babyImage;
    private int x, y;
    private int team;
    private int id;

    public Player(int x, int y, ImageIcon image, int team, int id) {
        this.x = x;
        this.y = y;
        this.babyImage = image;
        this.id = id;
        this.team = team;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public ImageIcon getBabyImage() {
        return babyImage;
    }

    public int getId() {
        return id;
    }

    public int getTeam() {
        return team;
    }

}

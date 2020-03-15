package app.models;

import javax.swing.*;

public class Ball {

    private ImageIcon ballImage;
    private int x, y;

    public Ball(int x, int y, ImageIcon image) {
        this.x = x;
        this.y = y;
        this.ballImage = image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ImageIcon getBallImage() {
        return ballImage;
    }

}

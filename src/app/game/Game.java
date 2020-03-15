package app.game;

import java.awt.*;

import static app.utils.AppUtils.getAppWindow;

public class Game
{

    public Game()
    {

    }

    public void prepareGame()
    {

        Container windowContainer = getAppWindow().getContentPane();
        windowContainer.setLayout(new BorderLayout());

    }

}

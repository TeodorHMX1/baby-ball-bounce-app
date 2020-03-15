package app.game;

import app.game.world.GameWorld;

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
        windowContainer.setBackground(new Color(241, 241, 241));

        //add the game world to the container
        GameWorld gameWorld = new GameWorld();
        windowContainer.add(gameWorld.getGameWorldContainer(), BorderLayout.WEST);

//        getAppWindow().pack();

    }

}

package app.game.world;

import javax.swing.*;
import java.awt.*;

public class GameWorld
{

    private JPanel gameWorld;

    public GameWorld()
    {

        gameWorld = new JPanel();
        gameWorld.setBackground(Color.BLUE);
        gameWorld.setPreferredSize(new Dimension(570, 440));

    }

    public JPanel getGameWorldContainer()
    {
        return gameWorld;
    }

}

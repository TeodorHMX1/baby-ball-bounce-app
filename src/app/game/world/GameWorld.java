package app.game.world;

import javax.swing.*;
import java.awt.*;

public class GameWorld
{

    private JPanel gameWorld;

    public GameWorld()
    {

        gameWorld = new JPanel();
        gameWorld.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        gameWorld.setLayout(new BorderLayout());
        gameWorld.setPreferredSize(new Dimension(570, 440));
        gameWorld.setBackground(new Color(241, 241, 241));

        initializeGameSubHolder();

    }

    private void initializeGameSubHolder() {

        JPanel field = new JPanel();
        field.setPreferredSize(new Dimension(530, 380));
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        field.setBackground(Color.white);
        gameWorld.add(field, BorderLayout.CENTER);

    }


    public JPanel getGameWorldContainer()
    {
        return gameWorld;
    }
    
}

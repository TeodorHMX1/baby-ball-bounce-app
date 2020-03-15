package app.game.options;

import javax.swing.*;
import java.awt.*;

public class GameOptions
{

    private JPanel gameWorld;

    public GameOptions()
    {

        gameWorld = new JPanel();
        gameWorld.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        gameWorld.setPreferredSize(new Dimension(570, 440));
        gameWorld.setBackground(new Color(241, 241, 241));

        initializeGameSubHolder();

    }

    private void initializeGameSubHolder() {

        JPanel field = new JPanel();
        field.setPreferredSize(new Dimension(530, 440));
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        field.setBackground(Color.white);
        gameWorld.add(field);

    }

    public JPanel getGameOptionsContainer()
    {
        return gameWorld;
    }
    
}

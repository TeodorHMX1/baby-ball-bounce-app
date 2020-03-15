package app.game.options;

import javax.swing.*;
import java.awt.*;

public class GameOptions
{

    private JPanel gameOptions;

    public GameOptions()
    {

        gameOptions = new JPanel();
        gameOptions.setBackground(new Color(241, 241, 241));
        gameOptions.setPreferredSize(new Dimension(255, 585));

    }

    public JPanel getGameOptionsContainer()
    {
        return gameOptions;
    }
    
}

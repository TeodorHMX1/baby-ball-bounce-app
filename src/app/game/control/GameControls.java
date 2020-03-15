package app.game.control;

import javax.swing.*;
import java.awt.*;

public class GameControls
{

    private JPanel gameOptions;

    public GameControls()
    {

        gameOptions = new JPanel();
        gameOptions.setBackground(new Color(241, 241, 241));

    }

    public JPanel getGameControlsContainer()
    {
        return gameOptions;
    }
    
}

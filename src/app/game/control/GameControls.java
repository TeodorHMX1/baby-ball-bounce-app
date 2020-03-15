package app.game.control;

import javax.swing.*;
import java.awt.*;

public class GameControls
{

    private JPanel gameControls;

    public GameControls()
    {

        gameControls = new JPanel();
        gameControls.setBackground(new Color(241, 241, 241));

    }

    public JPanel getGameControlsContainer()
    {
        return gameControls;
    }
    
}

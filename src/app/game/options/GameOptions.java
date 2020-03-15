package app.game.options;

import app.utils.material.MaterialElements;
import app.utils.material.MaterialLabel;

import javax.swing.*;
import java.awt.*;

public class GameOptions
{

    private JPanel gameOptions;
    private MaterialElements materialElements;

    public GameOptions()
    {

        materialElements = new MaterialElements();
        gameOptions = new JPanel();
//        gameOptions.setLayout(new FlowLayout());
        gameOptions.setBackground(new Color(238, 241, 93));
//        gameOptions.setBackground(new Color(241, 241, 241));
        gameOptions.setPreferredSize(new Dimension(255, 585));

        createDigitalTimer();

    }

    private void createDigitalTimer() {

        JPanel timerLabelHolder = new JPanel();
        timerLabelHolder.setBackground(new Color(0, 0, 0, 0));
        timerLabelHolder.setLayout(new BorderLayout());
        MaterialLabel timerLabel = materialElements.createLabel("DIGITAL TIMER");
        timerLabelHolder.add(timerLabel, BorderLayout.CENTER);
        gameOptions.add(timerLabelHolder);

    }

    public JPanel getGameOptionsContainer()
    {
        return gameOptions;
    }
    
}

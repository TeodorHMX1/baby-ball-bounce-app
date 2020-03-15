package app.game.options;

import app.utils.material.MaterialElements;
import app.utils.material.MaterialLabel;

import javax.swing.*;
import java.awt.*;

public class GameOptions
{

    private JPanel gameOptions;
    private MaterialElements materialElements;
    private MaterialLabel timerMinutes, timerSeconds, timerMilliseconds;
    private MaterialLabel scoreTeamLeft, scoreTeamRight;

    public GameOptions()
    {

        materialElements = new MaterialElements();
        gameOptions = new JPanel();
        gameOptions.setLayout(new FlowLayout());
        gameOptions.setBackground(new Color(238, 241, 93));
//        gameOptions.setBackground(new Color(241, 241, 241));
        gameOptions.setPreferredSize(new Dimension(255, 585));

        createDigitalTimer();
        createScoreContainer();

    }

    private void createDigitalTimer()
    {

        JPanel timerLabelHolder = new JPanel();
        timerLabelHolder.setBackground(new Color(0, 0, 0, 0));
        timerLabelHolder.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        timerLabelHolder.setLayout(new BorderLayout());

        MaterialLabel timerLabel = materialElements.createLabel("DIGITAL TIMER");
        timerLabelHolder.add(timerLabel, BorderLayout.CENTER);
        gameOptions.add(timerLabelHolder);

        JPanel timerHolder = new JPanel();
        timerHolder.setBackground(new Color(0, 0, 0, 0));
        timerHolder.setPreferredSize(new Dimension(255, 15));
        timerHolder.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0, 0));

        timerMinutes = createTimerElement("00");
        timerSeconds = createTimerElement("00");
        timerMilliseconds = createTimerElement("00");

        timerHolder.add(timerMinutes);
        timerHolder.add(materialElements.createLabel(" : "));
        timerHolder.add(timerSeconds);
        timerHolder.add(materialElements.createLabel(" : "));
        timerHolder.add(timerMilliseconds);

        gameOptions.add(timerHolder);

    }

    private void createScoreContainer()
    {

        JPanel scoreLabelHolder = new JPanel();
        scoreLabelHolder.setBackground(new Color(0, 0, 0, 0));
        scoreLabelHolder.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scoreLabelHolder.setLayout(new BorderLayout());

        MaterialLabel scoreLabel = materialElements.createLabel("SCORE");
        scoreLabelHolder.add(scoreLabel, BorderLayout.CENTER);
        gameOptions.add(scoreLabelHolder);

        JPanel scoreHolder = new JPanel();
        scoreHolder.setBackground(new Color(0, 0, 0, 0));
        scoreHolder.setPreferredSize(new Dimension(255, 15));
        scoreHolder.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0, 0));
        scoreTeamLeft = createTimerElement("00");
        scoreTeamRight = createTimerElement("00");
        scoreHolder.add(scoreTeamLeft);
        scoreHolder.add(scoreTeamRight);
        gameOptions.add(scoreHolder);


    }

    public MaterialLabel createTimerElement(String text)
    {
        MaterialLabel materialLabel = materialElements.createLabel(text);
        materialLabel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
        materialLabel.setOpaque(true);
        materialLabel.setBackground(Color.black);
        materialLabel.setForeground(Color.white);
        return materialLabel;
    }

    public MaterialLabel createScoreElement(String text)
    {
        MaterialLabel materialLabel = materialElements.createLabel(text);
        materialLabel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
        materialLabel.setOpaque(true);
        materialLabel.setBackground(Color.black);
        materialLabel.setForeground(Color.white);
        return materialLabel;
    }

    public JPanel getGameOptionsContainer()
    {
        return gameOptions;
    }
    
}

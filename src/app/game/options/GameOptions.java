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
    private int teamLeft = 0, teamRight = 0;

    public GameOptions()
    {

        materialElements = new MaterialElements();
        gameOptions = new JPanel();
        gameOptions.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        gameOptions.setBackground(new Color(238, 241, 93));
//        gameOptions.setBackground(new Color(241, 241, 241));
        gameOptions.setPreferredSize(new Dimension(255, 585));

        createDigitalTimer();
        createScoreContainer();
        createOptions();

    }

    private void createDigitalTimer()
    {

        JPanel timerLabelHolder = new JPanel();
        timerLabelHolder.setBackground(new Color(0, 0, 0, 0));
        timerLabelHolder.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0));
        timerLabelHolder.setLayout(new BorderLayout());

        MaterialLabel timerLabel = materialElements.createLabel("DIGITAL TIMER");
        timerLabelHolder.add(timerLabel, BorderLayout.CENTER);
        gameOptions.add(timerLabelHolder);

        JPanel timerHolder = new JPanel();
        timerHolder.setBackground(new Color(0, 0, 0, 0));
        timerHolder.setPreferredSize(new Dimension(255, 18));
        timerHolder.setBorder(BorderFactory.createEmptyBorder(-3, 0, 0, 0));

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
        scoreLabelHolder.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0));
        scoreLabelHolder.setLayout(new BorderLayout());

        MaterialLabel scoreLabel = materialElements.createLabel("SCORE");
        scoreLabelHolder.add(scoreLabel, BorderLayout.CENTER);
        gameOptions.add(scoreLabelHolder);

        JPanel scoreHolder = new JPanel();
        scoreHolder.setBackground(new Color(0, 0, 0, 0));
        scoreHolder.setPreferredSize(new Dimension(255, 16));
        scoreHolder.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0, 0));

        scoreTeamLeft = createScoreElement(teamLeft);
        scoreTeamRight = createScoreElement(teamRight);

        scoreHolder.add(scoreTeamLeft);
        scoreHolder.add(materialElements.createLabel(" < L : R > "));
        scoreHolder.add(scoreTeamRight);

        gameOptions.add(scoreHolder);

    }

    private void createOptions()
    {

        JPanel scoreLabelHolder = new JPanel();
        scoreLabelHolder.setBackground(new Color(0, 0, 0, 0));
        scoreLabelHolder.setPreferredSize(new Dimension(255, 30));
        scoreLabelHolder.setBorder(BorderFactory.createEmptyBorder(7, 30, 0, 10));
        scoreLabelHolder.setLayout(new BorderLayout());

        MaterialLabel scoreLabel = materialElements.createLabel("Option:");
        scoreLabelHolder.add(scoreLabel, BorderLayout.WEST);

        JPanel scoreLabelHolder2 = new JPanel();
        scoreLabelHolder2.setLayout(new GridBagLayout());
        scoreLabelHolder2.setBackground(new Color(255, 255, 255));
        scoreLabelHolder2.setPreferredSize(new Dimension(100, 25));
        MaterialLabel scoreLabel2 = createOptionItem("SCOREww");
        scoreLabelHolder2.add(scoreLabel2);
        scoreLabelHolder.add(scoreLabelHolder2, BorderLayout.EAST);
        gameOptions.add(scoreLabelHolder);

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

    public MaterialLabel createScoreElement(int score)
    {
        String content = String.valueOf(score);
        if (score<10)
        {
            content = "0" + content;
        }
        MaterialLabel materialLabel = materialElements.createLabel(content);
        materialLabel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
        materialLabel.setOpaque(true);
        materialLabel.setBackground(Color.black);
        materialLabel.setForeground(Color.white);
        return materialLabel;
    }

    public MaterialLabel createOptionItem(String text)
    {
        MaterialLabel materialLabel = materialElements.createLabel(text);
        materialLabel.setBorder(BorderFactory.createEmptyBorder(2, 3, 2, 3));
        materialLabel.setOpaque(true);
        materialLabel.setBackground(Color.white);
        return materialLabel;
    }

    public JPanel getGameOptionsContainer()
    {
        return gameOptions;
    }
    
}

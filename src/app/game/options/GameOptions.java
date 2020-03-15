package app.game.options;

import app.utils.material.MaterialButton;
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
        createBallMovementControls();
        createCompass();

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

        MaterialLabel optionLabel = createOptionItem("2 Player");
        gameOptions.add(createOptionItem("Option:", optionLabel));

        MaterialLabel squareLabel = createOptionItem("101");
        gameOptions.add(createOptionItem("Square:", squareLabel));

        MaterialLabel directionLabel = createOptionItem("SE");
        gameOptions.add(createOptionItem("Direction:", directionLabel));

    }

    private void createBallMovementControls()
    {

        GridLayout experimentLayout = new GridLayout(3,3);
        JPanel compsToExperiment = new JPanel();
        compsToExperiment.setBackground(new Color(0, 0, 0, 0));
        compsToExperiment.setPreferredSize(new Dimension(255, 90));
        compsToExperiment.setBorder(BorderFactory.createEmptyBorder(7, 35, 0, 20));
        compsToExperiment.setLayout(experimentLayout);
        compsToExperiment.add(squareGridElement());
        compsToExperiment.add(createGridElementChoice("up"));
        compsToExperiment.add(squareGridElement());
        compsToExperiment.add(createGridElementChoice("left"));
        compsToExperiment.add(createGridElementMiddle());
        compsToExperiment.add(createGridElementChoice("right"));
        compsToExperiment.add(squareGridElement());
        compsToExperiment.add(createGridElementChoice("down"));
        compsToExperiment.add(squareGridElement());
        gameOptions.add(compsToExperiment);

    }

    private void createCompass()
    {

        JPanel optionItemChoseHolder = new JPanel();
        optionItemChoseHolder.setLayout(new GridBagLayout());
        MaterialLabel contentLabel = materialElements.createLabel("");

        ImageIcon imageIcon = new ImageIcon("assets/images/ball.png");
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        contentLabel.setIcon(imageIcon);

        contentLabel.setPreferredSize(new Dimension(100, 100));
        optionItemChoseHolder.add(contentLabel);

        GridLayout experimentLayout = new GridLayout(1,1);
        JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        compsToExperiment.setBackground(new Color(0, 0, 0, 0));
        compsToExperiment.setPreferredSize(new Dimension(255, 100));
        compsToExperiment.setBorder(BorderFactory.createEmptyBorder(7, 35, 0, 20));
        compsToExperiment.add(optionItemChoseHolder);
        gameOptions.add(compsToExperiment);


    }

    private MaterialLabel createTimerElement(String text)
    {
        MaterialLabel materialLabel = materialElements.createLabel(text);
        materialLabel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
        materialLabel.setOpaque(true);
        materialLabel.setBackground(Color.black);
        materialLabel.setForeground(Color.white);
        return materialLabel;
    }

    private MaterialLabel createScoreElement(int score)
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

    private MaterialLabel createOptionItem(String text)
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

    private JPanel createOptionItem(String content, MaterialLabel label)
    {

        JPanel optionItemHolder = new JPanel();
        optionItemHolder.setBackground(new Color(0, 0, 0, 0));
        optionItemHolder.setPreferredSize(new Dimension(255, 30));
        optionItemHolder.setBorder(BorderFactory.createEmptyBorder(7, 35, 0, 20));
        optionItemHolder.setLayout(new BorderLayout());

        MaterialLabel contentLabel = materialElements.createLabel(content);
        optionItemHolder.add(contentLabel, BorderLayout.WEST);

        JPanel optionItemChoseHolder = new JPanel();
        optionItemChoseHolder.setLayout(new GridBagLayout());
        optionItemChoseHolder.setBackground(new Color(255, 255, 255));
        optionItemChoseHolder.setPreferredSize(new Dimension(80, 25));
        optionItemChoseHolder.add(label);
        optionItemHolder.add(optionItemChoseHolder, BorderLayout.EAST);

        return optionItemHolder;

    }

    private JPanel squareGridElement()
    {

        JPanel squareElement = new JPanel();
        squareElement.setBackground(new Color(0, 0, 0, 0));
        return squareElement;

    }

    private JPanel createGridElementMiddle()
    {

        JPanel optionItemChoseHolder = new JPanel();
        optionItemChoseHolder.setLayout(new GridBagLayout());
        optionItemChoseHolder.setBackground(new Color(255, 255, 255));
        optionItemChoseHolder.setPreferredSize(new Dimension(80, 25));
        MaterialLabel contentLabel = materialElements.createLabel("");
        Icon iconAct = new ImageIcon("assets/images/ball.png");
        contentLabel.setIcon(iconAct);
        optionItemChoseHolder.add(contentLabel);

        return optionItemChoseHolder;

    }

    private MaterialButton createGridElementChoice(String content)
    {

        MaterialButton optionItemChoseHolder = materialElements.createButton(null, "");
        optionItemChoseHolder.setLayout(new GridBagLayout());
        optionItemChoseHolder.setBackground(new Color(255, 255, 255));
        optionItemChoseHolder.setPreferredSize(new Dimension(80, 25));
        MaterialLabel contentLabel = materialElements.createLabel(content);
        optionItemChoseHolder.add(contentLabel);

        return optionItemChoseHolder;

    }
    
}

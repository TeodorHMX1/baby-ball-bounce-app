/**
 * Program: Assignment 2: Application – Baby Ball Bounce
 * Filename: GameOptions.java
 *
 * @author: © Teodor Grigor (GitHub - TeodorHMX1)
 * Course: BSc Computing Year 1
 * Module: CSY1020 Problem Solving & Programming
 * Tutor: Gary Hill
 * @version: 1.9 compass label added
 * Date: 17/06/20
 */
package app.game.options;

import app.interfaces.GoalScored;
import app.utils.enums.TeamMembers;
import app.interfaces.BallSquare;
import app.utils.AppUtils;
import app.utils.enums.Directions;
import app.utils.material.MaterialButton;
import app.utils.material.MaterialElements;
import app.utils.material.MaterialLabel;

import javax.swing.*;
import java.awt.*;

import static app.utils.AppUtils.getBallPosition;

public class GameOptions
{

    private JPanel gameOptions;
    private MaterialElements materialElements;
    private MaterialLabel timerHours, timerMinutes, timerSeconds;
    private MaterialLabel scoreTeamLeft, scoreTeamRight;
    private MaterialLabel optionLabel, directionLabel, squareLabel;
    private MaterialLabel compassLabel;

    enum Players
    {
        TWO,
        FOUR
    }

    public GameOptions()
    {

        materialElements = new MaterialElements();
        gameOptions = new JPanel();
        gameOptions.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        gameOptions.setBackground(new Color(241, 241, 241));
        gameOptions.setPreferredSize(new Dimension(255, 585));

        createDigitalTimer();
        createScoreContainer();
        createOptions();
        createBallMovementControls();
        createCompass();
        createChoices();

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

        timerHours = createTimerElement("00");
        timerMinutes = createTimerElement("00");
        timerSeconds = createTimerElement("00");

        timerHolder.add(timerHours);
        timerHolder.add(materialElements.createLabel(" : "));
        timerHolder.add(timerMinutes);
        timerHolder.add(materialElements.createLabel(" : "));
        timerHolder.add(timerSeconds);

        gameOptions.add(timerHolder);

        Timer timer = new Timer(1000, actionEvent ->
        {
            if (AppUtils.isGameStarted())
            {
                setTime();
            }
        });
        timer.setInitialDelay(0);
        AppUtils.addOnTimerCallback(running ->
        {
            if (running)
            {
                timer.start();
            } else
            {
                timer.restart();
                timerHours.setText(timeBased(AppUtils.getSeconds() / (60 * 60)));
                timerMinutes.setText(timeBased(AppUtils.getSeconds() / (60) % 60));
                timerSeconds.setText(timeBased(AppUtils.getSeconds() % 60));
            }
        });


    }

    private void setTime()
    {
        AppUtils.increaseSeconds();
        timerHours.setText(timeBased(AppUtils.getSeconds() / (60 * 60)));
        timerMinutes.setText(timeBased(AppUtils.getSeconds() / (60) % 60));
        timerSeconds.setText(timeBased(AppUtils.getSeconds() % 60));
    }

    private String timeBased(int time)
    {
        if (time < 10)
        {
            return "0" + time;
        }
        return String.valueOf(time);
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

        scoreTeamLeft = createScoreElement(AppUtils.teamA);
        scoreTeamRight = createScoreElement(AppUtils.teamB);

        scoreHolder.add(scoreTeamLeft);
        scoreHolder.add(materialElements.createLabel(" < L : R > "));
        scoreHolder.add(scoreTeamRight);

        AppUtils.addGoalScoredCallback(new GoalScored()
        {
            @Override
            public void scored()
            {
                scoreTeamLeft.setText(String.valueOf(AppUtils.teamA));
                scoreTeamRight.setText(String.valueOf(AppUtils.teamB));
            }
        });

        gameOptions.add(scoreHolder);

    }

    private void createOptions()
    {

        optionLabel = createOptionItem("2 Player");
        gameOptions.add(createOptionItem("Option:", optionLabel));

        squareLabel = createOptionItem(getBallPosition());
        gameOptions.add(createOptionItem("Square:", squareLabel));

        AppUtils.addBallSquareCallback((BallSquare) () -> squareLabel.setText(getBallPosition()));

        directionLabel = createOptionItem("E");
        gameOptions.add(createOptionItem("Direction:", directionLabel));

    }

    private void createBallMovementControls()
    {

        MaterialButton btnUp = createGridElementChoice("up");
        MaterialButton btnDown = createGridElementChoice("down");
        MaterialButton btnLeft = createGridElementChoice("left");
        MaterialButton btnRight = createGridElementChoice("right");

        btnUp.addActionListener(actionEvent -> ballTo(Directions.UP));
        btnDown.addActionListener(actionEvent -> ballTo(Directions.DOWN));
        btnLeft.addActionListener(actionEvent -> ballTo(Directions.LEFT));
        btnRight.addActionListener(actionEvent -> ballTo(Directions.RIGHT));

        GridLayout experimentLayout = new GridLayout(3, 3);
        JPanel compsToExperiment = new JPanel();
        compsToExperiment.setBackground(new Color(0, 0, 0, 0));
        compsToExperiment.setPreferredSize(new Dimension(255, 90));
        compsToExperiment.setBorder(BorderFactory.createEmptyBorder(7, 35, 0, 20));
        compsToExperiment.setLayout(experimentLayout);
        compsToExperiment.add(squareGridElement());
        compsToExperiment.add(btnUp);
        compsToExperiment.add(squareGridElement());
        compsToExperiment.add(btnLeft);
        compsToExperiment.add(createGridElementMiddle());
        compsToExperiment.add(btnRight);
        compsToExperiment.add(squareGridElement());
        compsToExperiment.add(btnDown);
        compsToExperiment.add(squareGridElement());
        gameOptions.add(compsToExperiment);

    }

    private void createCompass()
    {

        JPanel optionItemChoseHolder = new JPanel();
        optionItemChoseHolder.setLayout(new GridBagLayout());
        optionItemChoseHolder.setBackground(new Color(0, 0, 0, 0));
        compassLabel = materialElements.createLabel("");
        compassLabel.setBackground(new Color(0, 0, 0, 0));

        ImageIcon imageIcon = new ImageIcon("assets/images/east.jpg");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        compassLabel.setIcon(imageIcon);

        optionItemChoseHolder.add(compassLabel);

        GridLayout experimentLayout = new GridLayout(1, 1);
        JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        compsToExperiment.setBackground(new Color(0, 0, 0, 0));
        compsToExperiment.setPreferredSize(new Dimension(255, 110));
        compsToExperiment.setBorder(BorderFactory.createEmptyBorder(7, 35, 0, 20));
        compsToExperiment.add(optionItemChoseHolder);
        gameOptions.add(compsToExperiment);

    }

    private void createChoices()
    {

        MaterialButton btnPlayers2 = createOptionChoice("2 Player");
        MaterialButton btnPlayers4 = createOptionChoice("4 Player");
        MaterialButton btnMulti = createOptionChoice("Multi");
        MaterialButton btnExit = createOptionChoice("Exit");

        btnPlayers2.addActionListener(actionEvent -> playersChoice(Players.TWO));
        btnPlayers4.addActionListener(actionEvent -> playersChoice(Players.FOUR));
        btnMulti.addActionListener(actionEvent -> multiChoice());
        btnExit.addActionListener(actionEvent -> System.exit(0));

        GridLayout experimentLayout = new GridLayout(2, 2, 10, 10);
        JPanel compsToExperiment = new JPanel();
        compsToExperiment.setBackground(new Color(0, 0, 0, 0));
        compsToExperiment.setPreferredSize(new Dimension(255, 70));
        compsToExperiment.setBorder(BorderFactory.createEmptyBorder(20, 35, 0, 20));
        compsToExperiment.setLayout(experimentLayout);

        compsToExperiment.add(btnPlayers2);
        compsToExperiment.add(btnPlayers4);
        compsToExperiment.add(btnMulti);
        compsToExperiment.add(btnExit);
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
        if (score < 10)
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

    private void ballTo(Directions direction)
    {

        ImageIcon imageIcon;
        Image image;

        switch (direction)
        {
            case DOWN:
                imageIcon = new ImageIcon("assets/images/south.jpg");
                image = imageIcon.getImage();
                image = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(image);
                compassLabel.setIcon(imageIcon);
                directionLabel.setText("S");
                break;
            case UP:
                imageIcon = new ImageIcon("assets/images/north.jpg");
                image = imageIcon.getImage();
                image = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(image);
                compassLabel.setIcon(imageIcon);
                directionLabel.setText("N");
                break;
            case LEFT:
                imageIcon = new ImageIcon("assets/images/west.jpg");
                image = imageIcon.getImage();
                image = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(image);
                compassLabel.setIcon(imageIcon);
                directionLabel.setText("W");
                break;
            case RIGHT:
                imageIcon = new ImageIcon("assets/images/east.jpg");
                image = imageIcon.getImage();
                image = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(image);
                compassLabel.setIcon(imageIcon);
                directionLabel.setText("E");
                break;
            default:
                break;
        }

        if (AppUtils.getAutoMoveBall() != null)
        {
            AppUtils.getAutoMoveBall().moveTo(direction);
        }

    }

    private MaterialButton createOptionChoice(String content)
    {

        MaterialButton optionItemChoseHolder = materialElements.createButton(null, "");
        optionItemChoseHolder.setLayout(new GridBagLayout());
        optionItemChoseHolder.setBackground(new Color(255, 255, 255));
        optionItemChoseHolder.setPreferredSize(new Dimension(80, 30));
        MaterialLabel contentLabel = materialElements.createLabel(content);
        optionItemChoseHolder.add(contentLabel);

        return optionItemChoseHolder;

    }

    private void playersChoice(Players choice)
    {

        switch (choice)
        {
            case TWO:
                optionLabel.setText("2 Player");
                AppUtils.setPlayers(TeamMembers.players_2);
                break;
            case FOUR:
                optionLabel.setText("4 Player");
                AppUtils.setPlayers(TeamMembers.players_4);
                break;
            default:
                break;
        }

    }

    private void multiChoice()
    {

    }

    public JPanel getGameOptionsContainer()
    {
        return gameOptions;
    }

}

/**
 * Program: Assignment 2: Application – Baby Ball Bounce
 * Filename: GameControls.java
 *
 * @author: © Teodor Grigor (GitHub - TeodorHMX1)
 * Course: BSc Computing Year 1
 * Module: CSY1020 Problem Solving & Programming
 * Tutor: Gary Hill
 * @version: 1.8 speed added
 * Date: 18/06/20
 */
package app.game.control;

import app.utils.AppUtils;
import app.utils.enums.Directions;
import app.utils.material.MaterialButton;
import app.utils.material.MaterialElements;
import app.utils.material.MaterialLabel;
import app.utils.material.MaterialSlider;

import javax.swing.*;
import java.awt.*;

public class GameControls
{

    private JPanel gameControls;
    private MaterialElements materialElements;
    private MaterialButton btnState;
    private MaterialSlider slider;

    public GameControls()
    {

        materialElements = new MaterialElements();
        gameControls = new JPanel();
        gameControls.setLayout(new FlowLayout());
        gameControls.setBackground(new Color(241, 241, 241));
        gameControls.setPreferredSize(new Dimension(570, 60));

        prepareButtons();
        prepareSlider();

    }

    private void prepareButtons()
    {

        loadActBtn();
        loadStateBtn();
        loadResetBtn();

    }

    private void loadActBtn()
    {

        Icon iconAct = new ImageIcon("assets/images/step.png");
        MaterialButton btnAct = materialElements.createButton(iconAct, "Act");
        btnAct.addActionListener(actionEvent ->
        {
            if (AppUtils.getAutoMoveBall() != null)
            {
                AppUtils.getAutoMoveBall().moveTo(Directions.DEFAULT);
            }
        });
        gameControls.add(btnAct);

    }

    private void loadStateBtn()
    {

        Icon iconAct = new ImageIcon("assets/images/run.png");
        btnState = materialElements.createButton(iconAct, "Run");
        btnState.addActionListener(actionEvent ->
        {

            if (!AppUtils.isGameStarted())
            {
                btnState.setIcon(new ImageIcon("assets/images/pause.png"));
                btnState.setText("Pause");
            } else
            {
                btnState.setIcon(new ImageIcon("assets/images/run.png"));
                btnState.setText("Run");
            }
            AppUtils.changeGameState();
        });
        gameControls.add(btnState);

    }

    private void loadResetBtn()
    {

        Icon iconAct = new ImageIcon("assets/images/reset.png");
        MaterialButton btnReset = materialElements.createButton(iconAct, "Reset");
        btnReset.addActionListener(actionEvent ->
        {
            AppUtils.resetSeconds();
            btnState.setIcon(new ImageIcon("assets/images/run.png"));
            btnState.setText("Run");
        });
        gameControls.add(btnReset);

    }

    private void prepareSlider()
    {

        MaterialLabel sliderTitle = materialElements.createLabel("Speed:");
        sliderTitle.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
        gameControls.add(sliderTitle);

        slider = materialElements.createHorizontalSlider(1, 5, AppUtils.getGameSpeed());
        slider.addChangeListener(changed -> sliderChanged());
        gameControls.add(slider);

    }

    private void sliderChanged()
    {
        AppUtils.setGameSpeed(slider.getValue());
    }

    public JPanel getGameControlsContainer()
    {
        return gameControls;
    }

}

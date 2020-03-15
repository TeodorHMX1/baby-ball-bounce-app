package app.game.world;

import app.models.Ball;
import app.models.Player;
import app.utils.AppUtils;
import app.utils.material.MaterialElements;
import app.utils.material.MaterialLabel;

import javax.swing.*;
import java.awt.*;

public class GameWorld
{

    private JPanel gameWorld;
    private MaterialElements materialElements;

    public GameWorld()
    {

        materialElements = new MaterialElements();
        gameWorld = new JPanel();
        gameWorld.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
        gameWorld.setLayout(new BorderLayout());
        gameWorld.setPreferredSize(new Dimension(570, 440));
        gameWorld.setBackground(new Color(241, 241, 241));

        initializeGameSubHolder();

    }

    private final int rows = 13;
    private final int columns = 16;
    private MaterialLabel gameGrid[][] = new MaterialLabel[rows][columns];
    private void initializeGameSubHolder() {

        JPanel field = new JPanel();
        field.setPreferredSize(new Dimension(530, 360));
        field.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        field.setBackground(Color.white);
        FlowLayout layout = (FlowLayout)field.getLayout();
        layout.setVgap(0);

        initializeGameGrid();

        GridLayout experimentLayout = new GridLayout(13, 16, 0, 0);
        JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        compsToExperiment.setBackground(new Color(0, 0, 0, 0));
        compsToExperiment.setPreferredSize(new Dimension(530, 360));
        compsToExperiment.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        int i, j;
        for(i=0; i<rows; i++)
        {
            for(j=0; j<columns; j++)
            {
                compsToExperiment.add(gameGrid[i][j]);
            }
        }
        field.add(compsToExperiment);

        addBall();
        addPlayers();

        gameWorld.add(field, BorderLayout.CENTER);

    }

    private void addPlayers()
    {

        addTeamA();
        addTeamB();

    }

    private void addTeamA()
    {

        Player mPlayer1 = AppUtils.getPlayer(1);
        gameGrid[mPlayer1.getX()][mPlayer1.getY()].setIcon(mPlayer1.getBabyImage());
        if(AppUtils.getNoPlayers() == 4)
        {
            Player mPlayer2 = AppUtils.getPlayer(3);
            gameGrid[mPlayer2.getX()][mPlayer2.getY()].setIcon(mPlayer2.getBabyImage());
        }

    }

    private void addTeamB()
    {

        Player mPlayer1 = AppUtils.getPlayer(2);
        gameGrid[mPlayer1.getX()][mPlayer1.getY()].setIcon(mPlayer1.getBabyImage());
        if(AppUtils.getNoPlayers() == 4)
        {
            Player mPlayer2 = AppUtils.getPlayer(4);
            gameGrid[mPlayer2.getX()][mPlayer2.getY()].setIcon(mPlayer2.getBabyImage());
        }

    }

    private void addBall()
    {

        Ball mBall = AppUtils.getBall();
        gameGrid[mBall.getX()][mBall.getY()].setIcon(mBall.getBallImage());

    }

    private void initializeGameGrid()
    {

        int i, j;
        for(i=0; i<rows; i++)
        {
            for(j=0; j<columns; j++)
            {
                if(j == columns/2 || j == columns/2 - 1)
                {
                    gameGrid[i][j] = materialElements.createLabel("");
                    gameGrid[i][j].setIcon(new ImageIcon("assets/images/bricks2.jpg"));
                }
                else
                {
                    gameGrid[i][j] = materialElements.createLabel("");
                    gameGrid[i][j].setIcon(new ImageIcon("assets/images/white32x32.jpg"));
                }
            }
        }

    }

    public JPanel getGameWorldContainer()
    {
        return gameWorld;
    }
    
}

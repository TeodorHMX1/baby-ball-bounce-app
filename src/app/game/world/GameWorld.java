/**
 * Program: Assignment 2: Application – Baby Ball Bounce
 * Filename: GameWorld.java
 *
 * @author: © Teodor Grigor (GitHub - TeodorHMX1)
 * Course: BSc Computing Year 1
 * Module: CSY1020 Problem Solving & Programming
 * Tutor: Gary Hill
 * @version: 2.3 middle wall added
 * Date: 19/06/20
 */
package app.game.world;

import app.enumerations.TeamMembers;
import app.models.Ball;
import app.models.Player;
import app.utils.AppUtils;
import app.utils.AssetsUtils;
import app.utils.enums.Directions;
import app.utils.material.MaterialElements;
import app.utils.material.MaterialLabel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static app.utils.AssetsUtils.IMG_BRICKS;
import static app.utils.AssetsUtils.IMG_WHITE_SQUARE;

public class GameWorld
{

    private JPanel gameWorld;
    private MaterialElements materialElements;
    private JLayeredPane gameWorldHolder;
    private final int rows = 13;
    private final int columns = 16;
    private MaterialLabel[][] gameGrid = new MaterialLabel[rows][columns];
    public JPanel mBallObj;
    private ArrayList<JPanel> mPlayers = new ArrayList<>();

    public GameWorld()
    {

        materialElements = new MaterialElements();
        gameWorld = new JPanel();
        gameWorld.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
        gameWorld.setPreferredSize(new Dimension(570, 440));
        gameWorld.setBackground(new Color(241, 241, 241));

        initializeGameSubHolder();

    }

    private void initializeGameSubHolder()
    {

        initializeGameWorldHolder();
        initializeGameGrid();
        addGameObjects();
        gameWorld.add(gameWorldHolder);

        Timer timer = new Timer(1000 - AppUtils.getGameSpeed() * 200, actionEvent ->
        {
            if (AppUtils.isGameStarted())
            {
                moveBallTo(Directions.DEFAULT);
            }
        });
        timer.setInitialDelay(0);
        timer.start();
        AppUtils.addOnGameSpeedCallback(() ->
        {
            if (AppUtils.isGameStarted())
            {
                timer.setDelay(1000 - AppUtils.getGameSpeed() * 200);
            }
        });
        AppUtils.addOnTeamMembersListener(players ->
        {
            if (players == TeamMembers.players_2)
            {
                mBallObj.setLocation(530 / 2 - 120 - 23 / 2, 360 / 2 - 23 / 2);
                mPlayers.get(1).setVisible(false);
                mPlayers.get(3).setVisible(false);
                mPlayers.get(0).setLocation(530 / 2 - 150 - 31 / 2, 360 / 2 - 31 / 2);
                mPlayers.get(2).setLocation(530 / 2 + 150 - 31 / 2, 360 / 2 - 31 / 2);
            } else if (players == TeamMembers.players_4)
            {
                mBallObj.setLocation(530 / 2 - 120 - 23 / 2, 360 / 2 - 23 / 2 - 60);
                mPlayers.get(1).setVisible(true);
                mPlayers.get(3).setVisible(true);
                mPlayers.get(0).setLocation(530 / 2 - 150 - 31 / 2, 360 / 2 - 31 / 2 - 60);
                mPlayers.get(2).setLocation(530 / 2 + 150 - 31 / 2, 360 / 2 - 31 / 2 - 60);
                mPlayers.get(1).setLocation(530 / 2 - 150 - 31 / 2, 360 / 2 - 31 / 2 + 60);
                mPlayers.get(3).setLocation(530 / 2 + 150 - 31 / 2, 360 / 2 - 31 / 2 + 60);
            }
        });
        AppUtils.addOnAutoMoveBallCallback(this::moveBallTo);

        mBallObj.setLocation(530 / 2 - 120 - 23 / 2, 360 / 2 - 23 / 2);
        mPlayers.get(1).setVisible(false);
        mPlayers.get(3).setVisible(false);
        mPlayers.get(0).setLocation(530 / 2 - 150 - 31 / 2, 360 / 2 - 31 / 2);
        mPlayers.get(2).setLocation(530 / 2 + 150 - 31 / 2, 360 / 2 - 31 / 2);

    }

    private boolean isCollidingA(Point mPlayer, Point mBallObj)
    {
        return mBallObj.x >= mPlayer.x && mBallObj.x <= mPlayer.x + 32 &&
                mBallObj.y >= mPlayer.y && mBallObj.y <= mPlayer.y + 32;
    }

    private boolean isCollidingB(Point mPlayer, Point mBallObj)
    {
        return mBallObj.x >= mPlayer.x - 32 && mBallObj.x <= mPlayer.x &&
                mBallObj.y >= mPlayer.y && mBallObj.y <= mPlayer.y + 32;
    }

    private void moveBallTo(Directions direction)
    {

        switch (direction)
        {
            case DOWN:
                if (mBallObj.getLocation().y + 33 <= 334)
                {
                    mBallObj.setLocation(mBallObj.getLocation().x, mBallObj.getLocation().y + 33);
                    AppUtils.setBallDirection(direction);
                } else
                {
                    AppUtils.setBallDirection(Directions.UP);
                    moveBallTo(AppUtils.getBallDirection());
                }
                break;
            case UP:
                if (mBallObj.getLocation().y - 33 >= 4)
                {
                    mBallObj.setLocation(mBallObj.getLocation().x, mBallObj.getLocation().y - 33);
                    AppUtils.setBallDirection(direction);
                } else
                {
                    AppUtils.setBallDirection(Directions.DOWN);
                    moveBallTo(AppUtils.getBallDirection());
                }
                break;
            case LEFT:
                if (mBallObj.getLocation().x - 33 >= 2)
                {
                    mBallObj.setLocation(mBallObj.getLocation().x - 33, mBallObj.getLocation().y);
                    AppUtils.setBallDirection(direction);
                } else
                {
                    AppUtils.setBallDirection(Directions.RIGHT);
                    moveBallTo(AppUtils.getBallDirection());
                }
                break;
            case RIGHT:
                if (mBallObj.getLocation().x + 33 <= 497)
                {
                    mBallObj.setLocation(mBallObj.getLocation().x + 33, mBallObj.getLocation().y);
                    AppUtils.setBallDirection(direction);
                } else
                {
                    AppUtils.setBallDirection(Directions.LEFT);
                    moveBallTo(AppUtils.getBallDirection());
                }
                break;
            case DEFAULT:
                moveBallTo(AppUtils.getBallDirection());
                break;
            default:
                break;
        }
        for (int i = 0; i < 4; i++)
        {
            if (mPlayers.get(i).isVisible())
            {
                if (i == 0 || i == 1)
                {
                    if (isCollidingA(mPlayers.get(i).getLocation(), mBallObj.getLocation()))
                    {
                        bounceBall();
                    }
                } else
                {
                    if (isCollidingB(mPlayers.get(i).getLocation(), mBallObj.getLocation()))
                    {
                        bounceBall();
                    }
                }
            }
        }

    }

    private void bounceBall()
    {
        if (AppUtils.getBallDirection() == Directions.RIGHT)
        {
            moveBallTo(Directions.LEFT);
            moveBallTo(Directions.LEFT);
        } else if (AppUtils.getBallDirection() == Directions.LEFT)
        {
            moveBallTo(Directions.RIGHT);
            moveBallTo(Directions.RIGHT);
        } else if (AppUtils.getBallDirection() == Directions.UP)
        {
            moveBallTo(Directions.DOWN);
            moveBallTo(Directions.DOWN);
        } else if (AppUtils.getBallDirection() == Directions.DOWN)
        {
            moveBallTo(Directions.UP);
            moveBallTo(Directions.UP);
        }
    }

    private void initializeGameWorldHolder()
    {

        gameWorldHolder = new JLayeredPane();
        gameWorldHolder.setPreferredSize(new Dimension(530, 360));
        gameWorldHolder.setBackground(Color.white);
        gameWorldHolder.setOpaque(true);
        gameWorldHolder.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        gameWorldHolder.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

    }

    private void initializeGameGrid()
    {

        int i, j;
        for (i = 0; i < rows; i++)
        {
            for (j = 0; j < columns; j++)
            {
                if (j == columns / 2 || j == columns / 2 - 1)
                {
                    gameGrid[i][j] = materialElements.createLabel("");
                    gameGrid[i][j].setIcon(new AssetsUtils().getImageIcon(IMG_BRICKS));
                } else
                {
                    gameGrid[i][j] = materialElements.createLabel("");
                    gameGrid[i][j].setIcon(new AssetsUtils().getImageIcon(IMG_WHITE_SQUARE));
                }
            }
        }

        GridLayout mGridLayoutGameWord = new GridLayout(13, 16, 0, 0);
        JPanel mGameWorldHolder = new JPanel();
        mGameWorldHolder.setLayout(mGridLayoutGameWord);
        mGameWorldHolder.setBackground(new Color(0, 0, 0, 0));
        mGameWorldHolder.setPreferredSize(new Dimension(530, 360));
        mGameWorldHolder.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mGameWorldHolder.setVisible(true);

        for (i = 0; i < rows; i++)
        {
            for (j = 0; j < columns; j++)
            {
                mGameWorldHolder.add(gameGrid[i][j]);
            }
        }

        JPanel field = new JPanel();
        field.setBounds(0, 0, 530, 360);
        field.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
        field.setBackground(Color.white);
        field.setOpaque(true);
        field.setLocation(0, 0);
        field.add(mGameWorldHolder);

        gameWorldHolder.add(field, Integer.valueOf(1));

    }

    private void addGameObjects()
    {

        addBall();
        addPlayers();

    }

    private void addBall()
    {

        JPanel ballHolder = new JPanel();
        ballHolder.setBounds(0, 0, 530, 360);
        ballHolder.setLocation(0, 0);
        ballHolder.setOpaque(false);
        ballHolder.setLayout(null);

        Ball mBall = AppUtils.getBall();
        MaterialLabel ball = materialElements.createLabel("");
        ball.setIcon(mBall.getBallImage());
        ball.setLocation(0, 0);

        mBallObj = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mBallObj.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mBallObj.setBounds(0, 0, 23, 23);
        mBallObj.setOpaque(false);
        mBallObj.add(ball);
        ballHolder.add(mBallObj);

        gameWorldHolder.add(ballHolder, Integer.valueOf(2));

    }

    private void addPlayers()
    {

        addTeamA();
        addTeamB();

    }

    private void addTeamA()
    {

        Player mPlayer1 = AppUtils.getPlayer(1);

        MaterialLabel mPlayerLabel = materialElements.createLabel("");
        mPlayerLabel.setIcon(mPlayer1.getPlayerImg());
        mPlayerLabel.setLocation(0, 0);

        JPanel mPlayerJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mPlayerJPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mPlayerJPanel.setBounds(0, 0, 31, 31);
        mPlayerJPanel.setOpaque(false);
        mPlayerJPanel.add(mPlayerLabel);
        mPlayers.add(mPlayerJPanel);

        gameWorldHolder.add(mPlayers.get(0), Integer.valueOf(3));

        Player mPlayer3 = AppUtils.getPlayer(3);

        mPlayerLabel = materialElements.createLabel("");
        mPlayerLabel.setIcon(mPlayer3.getPlayerImg());
        mPlayerLabel.setLocation(0, 0);

        mPlayerJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mPlayerJPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mPlayerJPanel.setBounds(0, 0, 31, 31);
        mPlayerJPanel.setOpaque(false);
        mPlayerJPanel.add(mPlayerLabel);
        mPlayers.add(mPlayerJPanel);

        gameWorldHolder.add(mPlayers.get(1), Integer.valueOf(4));

        if (AppUtils.getNoPlayers() == 4)
        {

        }

    }

    private void addTeamB()
    {

        Player mPlayer1 = AppUtils.getPlayer(2);

        MaterialLabel mPlayerLabel = materialElements.createLabel("");
        mPlayerLabel.setIcon(mPlayer1.getPlayerImg());
        mPlayerLabel.setLocation(0, 0);

        JPanel mPlayerJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mPlayerJPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mPlayerJPanel.setBounds(0, 0, 31, 31);
        mPlayerJPanel.setOpaque(false);
        mPlayerJPanel.add(mPlayerLabel);
        mPlayers.add(mPlayerJPanel);

        gameWorldHolder.add(mPlayers.get(2), Integer.valueOf(5));

        Player mPlayer3 = AppUtils.getPlayer(4);

        mPlayerLabel = materialElements.createLabel("");
        mPlayerLabel.setIcon(mPlayer3.getPlayerImg());
        mPlayerLabel.setLocation(0, 0);

        mPlayerJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mPlayerJPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mPlayerJPanel.setBounds(0, 0, 31, 31);
        mPlayerJPanel.setOpaque(false);
        mPlayerJPanel.add(mPlayerLabel);
        mPlayers.add(mPlayerJPanel);

        gameWorldHolder.add(mPlayers.get(3), Integer.valueOf(6));

        if (AppUtils.getNoPlayers() == 4)
        {

        }

//        Player mPlayer1 = AppUtils.getPlayer(2);
//        gameGrid[mPlayer1.getX()][mPlayer1.getY()].setIcon(mPlayer1.getBabyImage());
//        if (AppUtils.getNoPlayers() == 4) {
//            Player mPlayer2 = AppUtils.getPlayer(4);
//            gameGrid[mPlayer2.getX()][mPlayer2.getY()].setIcon(mPlayer2.getBabyImage());
//        }

    }

    public JPanel getGameWorldContainer()
    {
        return gameWorld;
    }

}

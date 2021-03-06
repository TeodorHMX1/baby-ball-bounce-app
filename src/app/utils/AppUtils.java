/**
 * Program: Assignment 2: Application – Baby Ball Bounce
 * Filename: AppUtils.java
 *
 * @author: © Teodor Grigor (GitHub - TeodorHMX1)
 * Course: BSc Computing Year 1
 * Module: CSY1020 Problem Solving & Programming
 * Tutor: Gary Hill
 * @version: 2.6 Ball Direction added
 * Date: 23/06/20
 */
package app.utils;

import app.utils.enums.TeamMembers;
import app.interfaces.*;
import app.models.Ball;
import app.models.Player;
import app.utils.enums.Directions;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * AppUtils is the static class that keeps the values of the current
 * launch in the same place
 */
public class AppUtils
{

    public AppUtils()
    {

    }

    private static Directions ballDirection = Directions.RIGHT;
    private static JFrame mainActivityGlobal;
    public static JPanel mBallObj;
    public static int teamA = 0, teamB = 0;

    public static void setMainActivity(JFrame mainActivity)
    {
        mainActivityGlobal = mainActivity;
        initializePlayers();
        initializeBall();
    }

    public static JFrame getAppWindow()
    {
        return mainActivityGlobal;
    }

    private static TeamMembersNumber mTeamMembersNumber;

    public static void addOnTeamMembersListener(TeamMembersNumber teamMembersNumber)
    {
        mTeamMembersNumber = teamMembersNumber;
    }

    private static TeamMembers players = TeamMembers.players_2;

    public static void setPlayers(TeamMembers noPlayers)
    {
        players = noPlayers;
        if (mTeamMembersNumber != null)
        {
            mTeamMembersNumber.onMembersChanged(noPlayers);
        }
    }

    public static int getNoPlayers()
    {
        if (players == TeamMembers.players_2)
        {
            return 2;
        } else if (players == TeamMembers.players_4)
        {
            return 4;
        }
        return 2;
    }


    private static List<Player> mPlayers = new ArrayList<>();

    public static void initializePlayers()
    {
        ImageIcon imageIcon1 = new ImageIcon("assets/images/baby1.png");
        Image image1 = imageIcon1.getImage();
        Image newimg1 = image1.getScaledInstance(31, 31, Image.SCALE_SMOOTH);
        imageIcon1 = new ImageIcon(newimg1);

        ImageIcon imageIcon2 = new ImageIcon("assets/images/baby2.png");
        Image image2 = imageIcon2.getImage();
        Image newimg2 = image2.getScaledInstance(31, 31, Image.SCALE_SMOOTH);
        imageIcon2 = new ImageIcon(newimg2);

        mPlayers.clear();
        Player mPlayer1 = new Player(imageIcon1);
        mPlayers.add(mPlayer1);
        Player mPlayer2 = new Player(imageIcon2);
        mPlayers.add(mPlayer2);
        Player mPlayer3 = new Player(imageIcon1);
        mPlayers.add(mPlayer3);
        Player mPlayer4 = new Player(imageIcon2);
        mPlayers.add(mPlayer4);
    }

    public static Player getPlayer(int player)
    {
        return mPlayers.get(player - 1);
    }

    public static List<Player> getPlayer()
    {
        return mPlayers;
    }

    public static void setBallDirection(Directions directionN)
    {
        ballDirection = directionN;
    }

    public static Directions getBallDirection()
    {
        return ballDirection;
    }

    private static Ball mBall;

    public static void initializeBall()
    {
        ImageIcon imageIcon = new ImageIcon("assets/images/ball.png");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(23, 23, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        mBall = new Ball(imageIcon);
    }

    public static Ball getBall()
    {
        return mBall;
    }

    private static int seconds = 0;

    public static void increaseSeconds()
    {
        seconds++;
    }

    public static void resetSeconds()
    {
        seconds = 0;
        gameStarted = false;
        teamA = 0;
        teamB = 0;
        ballDirection = Directions.RIGHT;
        if (mGameStarted != null)
        {
            mGameStarted.isRunning(gameStarted);
        }
        setPlayers(players);
        initializePlayers();
        initializeBall();
    }

    public static void goalScored()
    {
        ballDirection = Directions.RIGHT;
        if (mGoalScored != null)
        {
            mGoalScored.scored();
        }
        setPlayers(players);
        initializePlayers();
        initializeBall();
    }

    public static int getSeconds()
    {
        return seconds;
    }

    private static boolean gameStarted = false;

    public static void changeGameState()
    {
        gameStarted = !gameStarted;
        if (mGameStarted != null && gameStarted)
        {
            mGameStarted.isRunning(gameStarted);
        }
    }

    public static boolean isGameStarted()
    {
        return gameStarted;
    }

    private static GameTimer.GameStarted mGameStarted;

    public static void addOnTimerCallback(GameTimer.GameStarted gameStarted)
    {
        mGameStarted = gameStarted;
    }

    private static BallCallback.AutoMoveBall mAutoMoveBall;

    public static void addOnAutoMoveBallCallback(BallCallback.AutoMoveBall autoMoveBall)
    {
        mAutoMoveBall = autoMoveBall;
    }

    public static BallCallback.AutoMoveBall getAutoMoveBall()
    {
        return mAutoMoveBall;
    }

    private static int gameSpeed = 1;

    public static int getGameSpeed()
    {
        return gameSpeed;
    }

    public static void setGameSpeed(int speed)
    {
        gameSpeed = speed;
        if (mGameSpeedCallback != null)
        {
            mGameSpeedCallback.onSpeedChanged();
        }
    }

    private static GameSpeed mGameSpeedCallback;

    public static void addOnGameSpeedCallback(GameSpeed gameSpeed)
    {
        mGameSpeedCallback = gameSpeed;
    }

    public static GameSpeed getGameSpeedCallback()
    {
        return mGameSpeedCallback;
    }

    public static String getBallPosition()
    {
        return mBallObj.getLocation().x/33 + "x" + (mBallObj.getLocation().y/26);
    }

    public static void setBallObj(JPanel ballObj)
    {
        mBallObj = ballObj;
    }

    private static BallSquare mBallSquare;

    public static void addBallSquareCallback(BallSquare ballSquare)
    {
        mBallSquare = ballSquare;
    }

    public static BallSquare getBallSquare()
    {
        return mBallSquare;
    }

    private static GoalScored mGoalScored;

    public static void addGoalScoredCallback(GoalScored goalScored)
    {
        mGoalScored = goalScored;
    }

}

package app.utils;

import app.interfaces.GameTimer;
import app.models.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AppUtils
{

    public AppUtils()
    {

    }

    private static JFrame mainActivityGlobal;
    public static void setMainActivity(JFrame mainActivity)
    {
        mainActivityGlobal = mainActivity;
        initializePlayers();
    }
    public static JFrame getAppWindow() {
        return mainActivityGlobal;
    }

    private static int players = 2;
    public static void setPlayers(int noPlayers)
    {
        players = noPlayers;
    }
    public static int getNoPlayers()
    {
        return players;
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
        Player mPlayer1 = new Player(6, 4, imageIcon1, 1, 1);
        mPlayers.add(mPlayer1);
        Player mPlayer2 = new Player(6, 11, imageIcon2, 2, 2);
        mPlayers.add(mPlayer2);
        Player mPlayer3 = new Player(6, 4, imageIcon1, 1, 3);
        mPlayers.add(mPlayer3);
        Player mPlayer4 = new Player(6, 11, imageIcon2, 2, 4);
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

    private static int seconds = 0;
    public static void increaseSeconds()
    {
        seconds++;
    }
    public static void resetSeconds()
    {
        seconds = 0;
        gameStarted = false;
        if(mGameStarted!=null)
        {
            mGameStarted.isRunning(gameStarted);
        }
    }
    public static int getSeconds()
    {
        return seconds;
    }

    private static boolean gameStarted = false;
    public static void changeGameState()
    {
        gameStarted = !gameStarted;
        if(mGameStarted!=null && gameStarted)
        {
            mGameStarted.isRunning(gameStarted);
        }
    }
    public static boolean isGameStarted()
    {
        return gameStarted;
    }

    private static GameTimer.GameStarted mGameStarted;
    public static void addOnTimerCallback(GameTimer.GameStarted gameStarted) {
        mGameStarted = gameStarted;
    }

}

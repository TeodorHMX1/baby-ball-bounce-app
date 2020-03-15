package app.utils;

import javax.swing.*;

public class AppUtils
{

    public AppUtils()
    {

    }

    private static JFrame mainActivityGlobal;
    public static void setMainActivity(JFrame mainActivity)
    {
        mainActivityGlobal = mainActivity;
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

}

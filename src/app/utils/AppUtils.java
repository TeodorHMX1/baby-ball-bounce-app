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

}

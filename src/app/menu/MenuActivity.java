package app.menu;
import app.MainActivity;

import javax.swing.*;
import java.awt.event.*;

public class MenuActivity
{

    private MainActivity mainActivity;

    public MenuActivity(MainActivity mainActivityN)
    {
        this.mainActivity = mainActivityN;
    }

    public void load()
    {

        var menuBar = new MenuUI();

        var fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        var eMenuItem = new JMenuItem("Exit");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener((event) -> System.exit(0));

        fileMenu.add(eMenuItem);
        menuBar.add(fileMenu);

        mainActivity.setJMenuBar(menuBar);

    }

}

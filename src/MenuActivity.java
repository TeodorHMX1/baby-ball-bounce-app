import javax.swing.*;
import java.awt.*;
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

        var menuBar = new JMenuBar();
        var exitIcon = new ImageIcon("src/resources/exit.png");

        var fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        var eMenuItem = new JMenuItem("Exit", exitIcon);
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener((event) -> System.exit(0));

        fileMenu.add(eMenuItem);
        menuBar.add(fileMenu);

        mainActivity.setJMenuBar(menuBar);

    }

}

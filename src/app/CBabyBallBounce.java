/**
 * Program: Assignment 2: Application – Baby Ball Bounce
 * Filename: CBabyBallBounce.java
 *
 * @author: © Teodor Grigor
 * Course: BSc Computing Year 1
 * Module: CSY1020 Problem Solving & Programming
 * Tutor: Apkar Salatian
 * @version: 4.6 All classes merged into this class
 * Date: 08/08/20
 */

package app;

import app.game.Game;
import app.utils.AssetsUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static app.utils.AppUtils.getAppWindow;
import static app.utils.AppUtils.setMainActivity;
import static app.utils.AssetsUtils.IMG_BRICKS;

public class CBabyBallBounce extends JFrame
{

    public static void main(String[] args)
    {

        //creating the app screen
        EventQueue.invokeLater(CBabyBallBounce::new);

    }

    //the class method
    public CBabyBallBounce()
    {

        initializeWindow();
        initializeAppUtils();
        createUIX();

    }

    private void initializeWindow()
    {

        // prepare window details
        // set window title
        setTitle("CBabyBallBounce - Baby Ball Bounce Application");
        // set screen size
        setSize(825, 585);
        // make the screen to always be at the same size
        // is not resizable
        setResizable(false);
        // close on 'x' clicked
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // set window visible
        setVisible(true);
        // setting the JFrame Icon
        setIconImage(new AssetsUtils().getImageIcon(IMG_BRICKS).getImage());

        // make the window to appear in the middle of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

    }

    private void initializeAppUtils()
    {

        setMainActivity(CBabyBallBounce.this);

    }

    private void createUIX()
    {

        createMenu();
        createGame();

    }

    private void createMenu()
    {

        //create app menu
        var menuBar = new JMenuBar();

        //prepare file option from menu
        menuBar.add(fileOptionMenu());

        //prepare scenario option from menu
        menuBar.add(scenarioOptionMenu());

        //prepare edit option from menu
        menuBar.add(editOptionMenu());

        //prepare controls option from menu
        menuBar.add(controlsOptionMenu());

        //prepare controls option from menu
        menuBar.add(toolsOptionMenu());

        //prepare help option from menu
        menuBar.add(helpOptionMenu());

        getAppWindow().setJMenuBar(menuBar);

    }

    private JMenu fileOptionMenu()
    {

        var fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        var eMenuItem = new JMenuItem("Exit");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.addActionListener((event) -> System.exit(0));
        fileMenu.add(eMenuItem);

        return fileMenu;

    }

    private JMenu scenarioOptionMenu()
    {

        var scenarioMenu = new JMenu("Scenario");
        scenarioMenu.setMnemonic(KeyEvent.VK_S);
        return scenarioMenu;

    }

    private JMenu editOptionMenu()
    {

        var editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        return editMenu;

    }

    private JMenu controlsOptionMenu()
    {

        var controlsMenu = new JMenu("Controls");
        controlsMenu.setMnemonic(KeyEvent.VK_C);
        return controlsMenu;

    }

    private JMenu toolsOptionMenu()
    {

        var controlsMenu = new JMenu("Tools");
        controlsMenu.setMnemonic(KeyEvent.VK_T);
        return controlsMenu;

    }

    private JMenu helpOptionMenu()
    {

        var helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        var hMenuItem1 = new JMenuItem("Help Topic");
        hMenuItem1.setMnemonic(KeyEvent.VK_H);
        helpMenu.add(hMenuItem1);

        var hMenuItem2 = new JMenuItem("About");
        hMenuItem2.setMnemonic(KeyEvent.VK_A);
        helpMenu.add(hMenuItem2);

        return helpMenu;

    }

    private void createGame()
    {

        // create app game
        Game game = new Game();
        game.prepareGame();

    }


}

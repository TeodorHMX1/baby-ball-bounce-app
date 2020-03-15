package app;

import app.menu.MenuActivity;

import javax.swing.*;
import java.awt.*;

public class MainActivity extends JFrame
{

    public static void main(String[] args)
    {

        EventQueue.invokeLater(() -> {

            new MainActivity().setVisible(true);

        });

    }

    public MainActivity()
    {

        initializeWindow();

        createUIX();

    }

    private void initializeWindow() {

        // prepare window details
        // set window title
        setTitle("CBabyBallBounce – Baby Ball Bounce Application");
        // set screen size
        setSize(825, 585);
        // make the screen to always be at the same size
        // is not resizable
        setResizable(false);
        //close on 'x' clicked
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void createUIX()
    {

        createMenu();

    }

    private void createMenu() {

        //create app.menu
        MenuActivity menuActivity = new MenuActivity(this);
        menuActivity.load();

    }


}
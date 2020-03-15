package app;

import app.game.Game;
import app.menu.MenuActivity;

import javax.swing.*;
import java.awt.*;

import static app.utils.AppUtils.setMainActivity;

public class MainActivity extends JFrame
{

    public static void main(String[] args)
    {

        EventQueue.invokeLater(() -> {

            new MainActivity();

        });

    }

    public MainActivity()
    {

        initializeWindow();
        initializeAppUtils();
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
        // close on 'x' clicked
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // set window visible
        setVisible(true);

        // make the window to appear in the middle of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

    }

    private void initializeAppUtils() {

        setMainActivity(MainActivity.this);

    }

    private void createUIX()
    {

        createMenu();
        createGame();

    }

    private void createMenu() {

        //create app menu
        MenuActivity menuActivity = new MenuActivity();
        menuActivity.prepareMenu();

    }

    private void createGame() {

        // create app game
        Game game = new Game();
        game.prepareGame();

    }


}

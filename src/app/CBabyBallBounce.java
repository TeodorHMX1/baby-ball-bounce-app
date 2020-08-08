/**
 * Program: Assignment 2: Application – Baby Ball Bounce
 * Filename: CBabyBallBounce.java
 *
 * @author: © Teodor Grigor
 * Course: BSc Computing Year 1
 * Module: CSY1020 Problem Solving & Programming
 * Tutor: Apkar Salatian
 * @version: 4.6 All classes merged into one class
 * Updated: 08/08/20
 */

package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class CBabyBallBounce extends JFrame
{

    // variables declaration section

    // final variables
    // strings
    // path to different assets
    private final String IMG_BRICKS = "assets/images/bricks2.jpg"; // path to the brick image
    private final String IMG_WHITE_SQUARE = "assets/images/white32x32.jpg"; // path to the white square
    private final String IMG_COMPASS_EAST = "assets/images/east.jpg"; // path to the east image - compass
    private final String IMG_COMPASS_WEST = "assets/images/west.jpg"; // path to the west image - compass
    private final String IMG_COMPASS_NORTH = "assets/images/north.jpg"; // path to the north image - compass
    private final String IMG_COMPASS_SOUTH = "assets/images/south.jpg"; // path to the south image - compass
    private final String IMG_STEP = "assets/images/step.png"; // path to the step icon
    private final String IMG_RUN = "assets/images/run.png"; // path to the run icon
    private final String IMG_PAUSE = "assets/images/pause.png"; // path to the pause icon
    private final String IMG_RESET = "assets/images/reset.png"; // path to the reset icon
    private final String IMG_BALL = "assets/images/ball.png"; // path to the ball icon
    private final String IMG_BABY1 = "assets/images/baby1.png"; // path to the baby1 icon
    private final String IMG_BABY2 = "assets/images/baby2.png"; // path to the baby2 icon

    // integers
    private final int rows = 13; // number of rows
    private final int columns = 16; // number of columns

    // JLabel Array
    private final JLabel[][] gameGrid = new JLabel[rows][columns]; // game grid

    // JPanel ArrayList
    private final ArrayList<JPanel> mPlayers = new ArrayList<>();

    // List
    private final java.util.List<Player> mPlayersData = new ArrayList<>();

    // normal variables
    // JPanels
    private JPanel gameWorld;
    private JPanel mBallObj;
    private JPanel gameControls;
    private JPanel gameOptions;

    // JButtons
    private JButton btnState;

    // JLabels
    private JLabel timerHours, timerMinutes, timerSeconds;
    private JLabel scoreTeamLeft, scoreTeamRight;
    private JLabel optionLabel, directionLabel, squareLabel;
    private JLabel compassLabel;

    // JLayeredPane
    private JLayeredPane gameWorldHolder;

    // JSlider
    private JSlider slider;

    // JFrame
    private JFrame mainActivityGlobal;

    // boolean
    private boolean gameStarted = false;

    // integers
    private int teamA = 0, teamB = 0;
    private int seconds = 0;
    private int gameSpeed = 1;

    // interfaces variables
    private BallSquare mBallSquare;
    private GameStarted mGameStarted;
    private AutoMoveBall mAutoMoveBall;
    private GameSpeed mGameSpeedCallback;
    private GoalScored mGoalScored;
    private TeamMembersNumber mTeamMembersNumber;

    // enum variables
    private Directions ballDirection = Directions.RIGHT;
    private TeamMembers players = TeamMembers.players_2;

    // models variables
    private Ball mBall;

    // interfaces models
    interface AutoMoveBall
    {
        void moveTo(Directions direction);
    }

    interface BallSquare
    {
        void newSquare();
    }

    interface GameSpeed
    {
        void onSpeedChanged();
    }

    interface GameStarted
    {
        void isRunning(boolean running);
    }

    interface GoalScored
    {
        void scored();
    }

    interface TeamMembersNumber
    {
        void onMembersChanged(TeamMembers players);
    }

    // enum declaration
    private enum TeamMembers
    {
        players_2,
        players_4
    }

    private enum Directions
    {
        DEFAULT,
        LEFT,
        RIGHT,
        UP,
        DOWN,
        UP_LEFT,
        UP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }

    // models declaration
    static class Ball
    {

        private final ImageIcon ballImage;

        private Ball(ImageIcon image)
        {
            this.ballImage = image;
        }

        private ImageIcon getBallImage()
        {
            return ballImage;
        }
    }

    static class Player
    {

        private final ImageIcon playerImg;

        private Player(ImageIcon image)
        {
            this.playerImg = image;
        }

        private ImageIcon getPlayerImg()
        {
            return playerImg;
        }

    }

    // main method
    public static void main(String[] args)
    {

        //creating the app screen
        EventQueue.invokeLater(CBabyBallBounce::new);

    }

    // the class method
    private CBabyBallBounce()
    {

        initializeWindow();
        initializeAppUtils();
        createUIX();

    }

    // initialize the JFrame with the System Requirements
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
        setIconImage(getImageIcon(IMG_BRICKS).getImage());

        // make the window to appear in the middle of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

    }

    // initialize background runner
    private void initializeAppUtils()
    {

        // setting the main activity
        // *useful when more classes are present*
        setMainActivity(CBabyBallBounce.this);

    }

    // create the UI and UX for the Application
    private void createUIX()
    {

        // start the menu creation
        createMenu();
        // start the game creation
        createGame();

    }

    // create the top menu with the 'file', 'scenario', etc. options
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

    // create the game
    private void createGame()
    {

        // create app game
        prepareGame();

    }

    // prepare the game environment
    private void prepareGame()
    {

        Container windowContainer = getAppWindow().getContentPane();
        windowContainer.setLayout(new BorderLayout());
        windowContainer.setBackground(new Color(241, 241, 241));

        //add the game world to the container
        gameWorld();
        windowContainer.add(gameWorld, BorderLayout.WEST);

        //add the game options to the container
        gameOptions();
        windowContainer.add(gameOptions, BorderLayout.EAST);

        //add the game controls to the container
        gameControls();
        windowContainer.add(gameControls, BorderLayout.SOUTH);

    }

    // prepare the game world
    private void gameWorld()
    {

        gameWorld = new JPanel();
        gameWorld.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
        gameWorld.setPreferredSize(new Dimension(570, 440));
        gameWorld.setBackground(new Color(241, 241, 241));

        initializeGameSubHolder();

    }

    // initialize the game world subholder with its content
    private void initializeGameSubHolder()
    {

        initializeGameWorldHolder();
        initializeGameGrid();
        addGameObjects();
        gameWorld.add(gameWorldHolder);

        Timer timer = new Timer(1000 - getGameSpeed() * 200, actionEvent ->
        {
            if (isGameStarted())
            {
                moveBallTo(Directions.DEFAULT);
                movePlayers();
            }
        });
        timer.setInitialDelay(0);
        timer.start();
        addOnGameSpeedCallback(() ->
        {
            if (isGameStarted())
            {
                timer.setDelay(1000 - getGameSpeed() * 200);
            }
        });
        addOnTeamMembersListener(players ->
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
        addOnAutoMoveBallCallback(this::moveBallTo);

        mBallObj.setLocation(530 / 2 - 120 - 23 / 2, 360 / 2 - 23 / 2);
        mPlayers.get(1).setVisible(false);
        mPlayers.get(3).setVisible(false);
        mPlayers.get(0).setLocation(530 / 2 - 150 - 31 / 2, 360 / 2 - 31 / 2);
        mPlayers.get(2).setLocation(530 / 2 + 150 - 31 / 2, 360 / 2 - 31 / 2);

    }

    // move a player towards the ball
    private void movePlayerToBall(JPanel player)
    {
        if (mBallObj.getLocation().y <= player.getLocation().y)
        {
            player.setLocation(player.getLocation().x, player.getLocation().y - 15);
        } else
        {
            player.setLocation(player.getLocation().x, player.getLocation().y + 15);
        }
    }

    // move a player towards the ball
    // *used when in a team ar 2 members*
    private void movePlayerToBall(JPanel player, boolean limitUp)
    {
        if (limitUp && (player.getLocation().y < 160 || player.getLocation().y > mBallObj.getLocation().y))
        {
            if (mBallObj.getLocation().y <= player.getLocation().y)
            {
                player.setLocation(player.getLocation().x, player.getLocation().y - 15);
            } else
            {
                player.setLocation(player.getLocation().x, player.getLocation().y + 15);
            }
        } else if (!limitUp && (player.getLocation().y > 200 || player.getLocation().y < mBallObj.getLocation().y))
        {
            if (mBallObj.getLocation().y <= player.getLocation().y)
            {
                player.setLocation(player.getLocation().x, player.getLocation().y - 15);
            } else
            {
                player.setLocation(player.getLocation().x, player.getLocation().y + 15);
            }
        }
    }

    // move the players on the field
    private void movePlayers()
    {
        if (getNoPlayers() == 2)
        {
            movePlayerToBall(mPlayers.get(0));
            movePlayerToBall(mPlayers.get(2));
        } else if (getNoPlayers() == 4)
        {
            movePlayerToBall(mPlayers.get(0), true);
            movePlayerToBall(mPlayers.get(1), false);
            movePlayerToBall(mPlayers.get(2), true);
            movePlayerToBall(mPlayers.get(3), false);
        }
    }

    // check if team a is colliding with the ball
    private boolean isCollidingA(Point mPlayer, Point mBallObj)
    {
        return mBallObj.x >= mPlayer.x && mBallObj.x <= mPlayer.x + 32 &&
                mBallObj.y >= mPlayer.y && mBallObj.y <= mPlayer.y + 32;
    }

    // check if team b is colliding with the ball
    private boolean isCollidingB(Point mPlayer, Point mBallObj)
    {
        return mBallObj.x >= mPlayer.x && mBallObj.x <= mPlayer.x + 32 &&
                mBallObj.y >= mPlayer.y && mBallObj.y <= mPlayer.y + 32;
    }

    // return a random number
    // used for random direction
    private int randomNumber()
    {
        return (int) (Math.random() * ((3 - 1) + 1)) + 1;
    }

    // based on the random number retrieve a direction
    private Directions randomDirection(Directions direction)
    {
        int randomNo = randomNumber();
        switch (direction)
        {
            case DOWN:
                if (randomNo == 1)
                {
                    return Directions.BOTTOM_LEFT;
                } else if (randomNo == 2)
                {
                    return Directions.DOWN;
                } else
                {
                    return Directions.BOTTOM_RIGHT;
                }
            case UP:
                if (randomNo == 1)
                {
                    return Directions.UP_LEFT;
                } else if (randomNo == 2)
                {
                    return Directions.UP;
                } else
                {
                    return Directions.UP_RIGHT;
                }
            case LEFT:
                if (randomNo == 1)
                {
                    return Directions.UP_LEFT;
                } else if (randomNo == 2)
                {
                    return Directions.LEFT;
                } else
                {
                    return Directions.BOTTOM_LEFT;
                }
            case RIGHT:
                if (randomNo == 1)
                {
                    return Directions.UP_RIGHT;
                } else if (randomNo == 2)
                {
                    return Directions.RIGHT;
                } else
                {
                    return Directions.BOTTOM_RIGHT;
                }
            default:
                return Directions.RIGHT;
        }
    }

    // move the ball to a direction
    private void moveBallTo(Directions direction)
    {

        switch (direction)
        {
            case DOWN:
                if (mBallObj.getLocation().y + 33 <= 334)
                {
                    mBallObj.setLocation(mBallObj.getLocation().x, mBallObj.getLocation().y + 33);
                    setBallDirection(direction);
                } else
                {
                    setBallDirection(randomDirection(Directions.UP));
                    moveBallTo(getBallDirection());
                }
                break;
            case UP:
                if (mBallObj.getLocation().y - 33 >= 4)
                {
                    mBallObj.setLocation(mBallObj.getLocation().x, mBallObj.getLocation().y - 33);
                    setBallDirection(direction);
                } else
                {
                    setBallDirection(randomDirection(Directions.DOWN));
                    moveBallTo(getBallDirection());
                }
                break;
            case LEFT:
                if (mBallObj.getLocation().x - 33 >= 2)
                {
                    mBallObj.setLocation(mBallObj.getLocation().x - 33, mBallObj.getLocation().y);
                    setBallDirection(direction);
                } else
                {
                    setBallDirection(randomDirection(Directions.RIGHT));
                    moveBallTo(getBallDirection());
                }
                break;
            case RIGHT:
                if (mBallObj.getLocation().x + 33 <= 497)
                {
                    mBallObj.setLocation(mBallObj.getLocation().x + 33, mBallObj.getLocation().y);
                    setBallDirection(direction);
                } else
                {
                    setBallDirection(randomDirection(Directions.LEFT));
                    moveBallTo(getBallDirection());
                }
                break;
            case BOTTOM_LEFT:
                moveBallTo(Directions.LEFT);
                moveBallTo(Directions.DOWN);
                setBallDirection(Directions.BOTTOM_LEFT);
                break;
            case BOTTOM_RIGHT:
                moveBallTo(Directions.RIGHT);
                moveBallTo(Directions.DOWN);
                setBallDirection(Directions.BOTTOM_RIGHT);
                break;
            case UP_LEFT:
                moveBallTo(Directions.LEFT);
                moveBallTo(Directions.UP);
                setBallDirection(Directions.UP_LEFT);
                break;
            case UP_RIGHT:
                moveBallTo(Directions.RIGHT);
                moveBallTo(Directions.UP);
                setBallDirection(Directions.UP_RIGHT);
                break;
            case DEFAULT:
                moveBallTo(getBallDirection());
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

        if (mBallObj.getLocation().x < 100)
        {
            goalScored();
            teamB++;
        } else if (mBallObj.getLocation().x > 450)
        {
            goalScored();
            teamA++;
        }

        if (getBallSquare() != null)
        {
            getBallSquare().newSquare();
        }

    }

    // bounce the ball against the borders or players
    private void bounceBall()
    {
        if (getBallDirection() == Directions.RIGHT)
        {
            setBallDirection(randomDirection(Directions.LEFT));
            moveBallTo(getBallDirection());
        } else if (getBallDirection() == Directions.LEFT)
        {
            setBallDirection(randomDirection(Directions.RIGHT));
            moveBallTo(getBallDirection());
        } else if (getBallDirection() == Directions.UP)
        {
            setBallDirection(randomDirection(Directions.DOWN));
            moveBallTo(getBallDirection());
        } else if (getBallDirection() == Directions.DOWN)
        {
            setBallDirection(randomDirection(Directions.UP));
            moveBallTo(getBallDirection());
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
                    gameGrid[i][j] = new JLabel("");
                    gameGrid[i][j].setIcon(getImageIcon(IMG_BRICKS));
                } else
                {
                    gameGrid[i][j] = new JLabel("");
                    gameGrid[i][j].setIcon(getImageIcon(IMG_WHITE_SQUARE));
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

    enum Players
    {
        TWO,
        FOUR
    }

    private void gameOptions()
    {

        gameOptions = new JPanel();
        gameOptions.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        gameOptions.setBackground(new Color(241, 241, 241));
        gameOptions.setPreferredSize(new Dimension(255, 585));

        createDigitalTimer();
        createScoreContainer();
        createOptions();
        createBallMovementControls();
        createCompass();
        createChoices();

    }

    private void createDigitalTimer()
    {

        JPanel timerLabelHolder = new JPanel();
        timerLabelHolder.setBackground(new Color(0, 0, 0, 0));
        timerLabelHolder.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0));
        timerLabelHolder.setLayout(new BorderLayout());

        JLabel timerLabel = new JLabel("DIGITAL TIMER");
        timerLabelHolder.add(timerLabel, BorderLayout.CENTER);
        gameOptions.add(timerLabelHolder);

        JPanel timerHolder = new JPanel();
        timerHolder.setBackground(new Color(0, 0, 0, 0));
        timerHolder.setPreferredSize(new Dimension(255, 18));
        timerHolder.setBorder(BorderFactory.createEmptyBorder(-3, 0, 0, 0));

        timerHours = createTimerElement();
        timerMinutes = createTimerElement();
        timerSeconds = createTimerElement();

        timerHolder.add(timerHours);
        timerHolder.add(new JLabel(" : "));
        timerHolder.add(timerMinutes);
        timerHolder.add(new JLabel(" : "));
        timerHolder.add(timerSeconds);

        gameOptions.add(timerHolder);

        Timer timer = new Timer(1000, actionEvent ->
        {
            if (isGameStarted())
            {
                setTime();
            }
        });
        timer.setInitialDelay(0);
        addOnTimerCallback(running ->
        {
            if (running)
            {
                timer.start();
            } else
            {
                timer.restart();
                timerHours.setText(timeBased(getSeconds() / (60 * 60)));
                timerMinutes.setText(timeBased(getSeconds() / (60) % 60));
                timerSeconds.setText(timeBased(getSeconds() % 60));
            }
        });


    }

    private String timeBased(int time)
    {
        if (time < 10)
        {
            return "0" + time;
        }
        return String.valueOf(time);
    }

    private void createScoreContainer()
    {

        JPanel scoreLabelHolder = new JPanel();
        scoreLabelHolder.setBackground(new Color(0, 0, 0, 0));
        scoreLabelHolder.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0));
        scoreLabelHolder.setLayout(new BorderLayout());

        JLabel scoreLabel = new JLabel("SCORE");
        scoreLabelHolder.add(scoreLabel, BorderLayout.CENTER);
        gameOptions.add(scoreLabelHolder);

        JPanel scoreHolder = new JPanel();
        scoreHolder.setBackground(new Color(0, 0, 0, 0));
        scoreHolder.setPreferredSize(new Dimension(255, 16));
        scoreHolder.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0, 0));

        scoreTeamLeft = createScoreElement(teamA);
        scoreTeamRight = createScoreElement(teamB);

        scoreHolder.add(scoreTeamLeft);
        scoreHolder.add(new JLabel(" < L : R > "));
        scoreHolder.add(scoreTeamRight);

        addGoalScoredCallback(() ->
        {
            scoreTeamLeft.setText(String.valueOf(teamA));
            scoreTeamRight.setText(String.valueOf(teamB));
        });

        gameOptions.add(scoreHolder);

    }

    private void createOptions()
    {

        optionLabel = createOptionItem("2 Player");
        gameOptions.add(createOptionItem("Option:", optionLabel));

        squareLabel = createOptionItem(getBallPosition());
        gameOptions.add(createOptionItem("Square:", squareLabel));

        addBallSquareCallback(() -> squareLabel.setText(getBallPosition()));

        directionLabel = createOptionItem("E");
        gameOptions.add(createOptionItem("Direction:", directionLabel));

    }

    private void createBallMovementControls()
    {

        JButton btnUp = createGridElementChoice("up");
        JButton btnDown = createGridElementChoice("down");
        JButton btnLeft = createGridElementChoice("left");
        JButton btnRight = createGridElementChoice("right");

        btnUp.addActionListener(actionEvent -> ballTo(Directions.UP));
        btnDown.addActionListener(actionEvent -> ballTo(Directions.DOWN));
        btnLeft.addActionListener(actionEvent -> ballTo(Directions.LEFT));
        btnRight.addActionListener(actionEvent -> ballTo(Directions.RIGHT));

        GridLayout experimentLayout = new GridLayout(3, 3);
        JPanel compsToExperiment = new JPanel();
        compsToExperiment.setBackground(new Color(0, 0, 0, 0));
        compsToExperiment.setPreferredSize(new Dimension(255, 90));
        compsToExperiment.setBorder(BorderFactory.createEmptyBorder(7, 35, 0, 20));
        compsToExperiment.setLayout(experimentLayout);
        compsToExperiment.add(squareGridElement());
        compsToExperiment.add(btnUp);
        compsToExperiment.add(squareGridElement());
        compsToExperiment.add(btnLeft);
        compsToExperiment.add(createGridElementMiddle());
        compsToExperiment.add(btnRight);
        compsToExperiment.add(squareGridElement());
        compsToExperiment.add(btnDown);
        compsToExperiment.add(squareGridElement());
        gameOptions.add(compsToExperiment);

    }

    private void createCompass()
    {

        JPanel optionItemChoseHolder = new JPanel();
        optionItemChoseHolder.setLayout(new GridBagLayout());
        optionItemChoseHolder.setBackground(new Color(0, 0, 0, 0));
        compassLabel = new JLabel("");
        compassLabel.setBackground(new Color(0, 0, 0, 0));

        ImageIcon imageIcon = new ImageIcon(IMG_COMPASS_EAST);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        compassLabel.setIcon(imageIcon);

        optionItemChoseHolder.add(compassLabel);

        GridLayout experimentLayout = new GridLayout(1, 1);
        JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        compsToExperiment.setBackground(new Color(0, 0, 0, 0));
        compsToExperiment.setPreferredSize(new Dimension(255, 110));
        compsToExperiment.setBorder(BorderFactory.createEmptyBorder(7, 35, 0, 20));
        compsToExperiment.add(optionItemChoseHolder);
        gameOptions.add(compsToExperiment);

    }

    private void createChoices()
    {

        JButton btnPlayers2 = createOptionChoice("2 Player");
        JButton btnPlayers4 = createOptionChoice("4 Player");
        JButton btnMulti = createOptionChoice("Multi");
        JButton btnExit = createOptionChoice("Exit");

        btnPlayers2.addActionListener(actionEvent -> playersChoice(Players.TWO));
        btnPlayers4.addActionListener(actionEvent -> playersChoice(Players.FOUR));
        btnMulti.addActionListener(actionEvent -> multiChoice());
        btnExit.addActionListener(actionEvent -> System.exit(0));

        GridLayout experimentLayout = new GridLayout(2, 2, 10, 10);
        JPanel compsToExperiment = new JPanel();
        compsToExperiment.setBackground(new Color(0, 0, 0, 0));
        compsToExperiment.setPreferredSize(new Dimension(255, 70));
        compsToExperiment.setBorder(BorderFactory.createEmptyBorder(20, 35, 0, 20));
        compsToExperiment.setLayout(experimentLayout);

        compsToExperiment.add(btnPlayers2);
        compsToExperiment.add(btnPlayers4);
        compsToExperiment.add(btnMulti);
        compsToExperiment.add(btnExit);
        gameOptions.add(compsToExperiment);

    }

    private JLabel createTimerElement()
    {
        JLabel materialLabel = new JLabel("00");
        materialLabel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
        materialLabel.setOpaque(true);
        materialLabel.setBackground(Color.black);
        materialLabel.setForeground(Color.white);
        return materialLabel;
    }

    private JLabel createScoreElement(int score)
    {
        String content = String.valueOf(score);
        if (score < 10)
        {
            content = "0" + content;
        }
        JLabel materialLabel = new JLabel(content);
        materialLabel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
        materialLabel.setOpaque(true);
        materialLabel.setBackground(Color.black);
        materialLabel.setForeground(Color.white);
        return materialLabel;
    }

    private JLabel createOptionItem(String text)
    {
        JLabel materialLabel = new JLabel(text);
        materialLabel.setBorder(BorderFactory.createEmptyBorder(2, 3, 2, 3));
        materialLabel.setOpaque(true);
        materialLabel.setBackground(Color.white);
        return materialLabel;
    }

    private JPanel createOptionItem(String content, JLabel label)
    {

        JPanel optionItemHolder = new JPanel();
        optionItemHolder.setBackground(new Color(0, 0, 0, 0));
        optionItemHolder.setPreferredSize(new Dimension(255, 30));
        optionItemHolder.setBorder(BorderFactory.createEmptyBorder(7, 35, 0, 20));
        optionItemHolder.setLayout(new BorderLayout());

        JLabel contentLabel = new JLabel(content);
        optionItemHolder.add(contentLabel, BorderLayout.WEST);

        JPanel optionItemChoseHolder = new JPanel();
        optionItemChoseHolder.setLayout(new GridBagLayout());
        optionItemChoseHolder.setBackground(new Color(255, 255, 255));
        optionItemChoseHolder.setPreferredSize(new Dimension(80, 25));
        optionItemChoseHolder.add(label);
        optionItemHolder.add(optionItemChoseHolder, BorderLayout.EAST);

        return optionItemHolder;

    }

    private JPanel squareGridElement()
    {

        JPanel squareElement = new JPanel();
        squareElement.setBackground(new Color(0, 0, 0, 0));
        return squareElement;

    }

    private JPanel createGridElementMiddle()
    {

        JPanel optionItemChoseHolder = new JPanel();
        optionItemChoseHolder.setLayout(new GridBagLayout());
        optionItemChoseHolder.setBackground(new Color(255, 255, 255));
        optionItemChoseHolder.setPreferredSize(new Dimension(80, 25));
        JLabel contentLabel = new JLabel("");
        Icon iconAct = new ImageIcon(IMG_BALL);
        contentLabel.setIcon(iconAct);
        optionItemChoseHolder.add(contentLabel);

        return optionItemChoseHolder;

    }

    private JButton createGridElementChoice(String content)
    {

        JButton optionItemChoseHolder = createButton(null, "");
        optionItemChoseHolder.setLayout(new GridBagLayout());
        optionItemChoseHolder.setBackground(new Color(255, 255, 255));
        optionItemChoseHolder.setPreferredSize(new Dimension(80, 25));
        JLabel contentLabel = new JLabel(content);
        optionItemChoseHolder.add(contentLabel);

        return optionItemChoseHolder;

    }

    private void ballTo(Directions direction)
    {

        ImageIcon imageIcon;
        Image image;

        switch (direction)
        {
            case DOWN:
                imageIcon = new ImageIcon(IMG_COMPASS_SOUTH);
                image = imageIcon.getImage();
                image = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(image);
                compassLabel.setIcon(imageIcon);
                directionLabel.setText("S");
                break;
            case UP:
                imageIcon = new ImageIcon(IMG_COMPASS_NORTH);
                image = imageIcon.getImage();
                image = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(image);
                compassLabel.setIcon(imageIcon);
                directionLabel.setText("N");
                break;
            case LEFT:
                imageIcon = new ImageIcon(IMG_COMPASS_WEST);
                image = imageIcon.getImage();
                image = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(image);
                compassLabel.setIcon(imageIcon);
                directionLabel.setText("W");
                break;
            case RIGHT:
                imageIcon = new ImageIcon(IMG_COMPASS_EAST);
                image = imageIcon.getImage();
                image = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(image);
                compassLabel.setIcon(imageIcon);
                directionLabel.setText("E");
                break;
            default:
                break;
        }

        getAutoMoveBall().moveTo(direction);

    }

    private JButton createOptionChoice(String content)
    {

        JButton optionItemChoseHolder = createButton(null, "");
        optionItemChoseHolder.setLayout(new GridBagLayout());
        optionItemChoseHolder.setBackground(new Color(255, 255, 255));
        optionItemChoseHolder.setPreferredSize(new Dimension(80, 30));
        JLabel contentLabel = new JLabel(content);
        optionItemChoseHolder.add(contentLabel);

        return optionItemChoseHolder;

    }

    private void playersChoice(Players choice)
    {

        switch (choice)
        {
            case TWO:
                optionLabel.setText("2 Player");
                setPlayers(TeamMembers.players_2);
                break;
            case FOUR:
                optionLabel.setText("4 Player");
                setPlayers(TeamMembers.players_4);
                break;
            default:
                break;
        }

    }

    private void multiChoice()
    {

    }

    private void gameControls()
    {

        gameControls = new JPanel();
        gameControls.setLayout(new FlowLayout());
        gameControls.setBackground(new Color(241, 241, 241));
        gameControls.setPreferredSize(new Dimension(570, 60));

        prepareButtons();
        prepareSlider();

    }

    private void prepareButtons()
    {

        loadActBtn();
        loadStateBtn();
        loadResetBtn();

    }

    private void loadActBtn()
    {

        Icon iconAct = new ImageIcon(IMG_STEP);
        JButton btnAct = createButton(iconAct, "Act");
        btnAct.addActionListener(actionEvent ->
        {
            if (getAutoMoveBall() != null)
            {
                getAutoMoveBall().moveTo(Directions.DEFAULT);
            }
        });
        gameControls.add(btnAct);

    }

    private void loadStateBtn()
    {

        Icon iconAct = new ImageIcon(IMG_RUN);
        btnState = createButton(iconAct, "Run");
        btnState.addActionListener(actionEvent ->
        {

            if (!isGameStarted())
            {
                btnState.setIcon(new ImageIcon(IMG_PAUSE));
                btnState.setText("Pause");
            } else
            {
                btnState.setIcon(new ImageIcon(IMG_RUN));
                btnState.setText("Run");
            }
            changeGameState();
        });
        gameControls.add(btnState);

    }

    private void loadResetBtn()
    {

        Icon iconAct = new ImageIcon(IMG_RESET);
        JButton btnReset = createButton(iconAct, "Reset");
        btnReset.addActionListener(actionEvent ->
        {
            resetSeconds();
            btnState.setIcon(new ImageIcon(IMG_RUN));
            btnState.setText("Run");
        });
        gameControls.add(btnReset);

    }

    private void prepareSlider()
    {

        JLabel sliderTitle = new JLabel("Speed:");
        sliderTitle.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
        gameControls.add(sliderTitle);

        slider = createHorizontalSlider(getGameSpeed());
        slider.addChangeListener(changed -> sliderChanged());
        gameControls.add(slider);

    }

    private void sliderChanged()
    {
        setGameSpeed(slider.getValue());
    }

    private void initializePlayers()
    {
        ImageIcon imageIcon1 = new ImageIcon(IMG_BABY1);
        Image image1 = imageIcon1.getImage();
        Image newimg1 = image1.getScaledInstance(31, 31, Image.SCALE_SMOOTH);
        imageIcon1 = new ImageIcon(newimg1);

        ImageIcon imageIcon2 = new ImageIcon(IMG_BABY2);
        Image image2 = imageIcon2.getImage();
        Image newimg2 = image2.getScaledInstance(31, 31, Image.SCALE_SMOOTH);
        imageIcon2 = new ImageIcon(newimg2);

        mPlayersData.clear();
        Player mPlayer1 = new Player(imageIcon1);
        mPlayersData.add(mPlayer1);
        Player mPlayer2 = new Player(imageIcon2);
        mPlayersData.add(mPlayer2);
        Player mPlayer3 = new Player(imageIcon1);
        mPlayersData.add(mPlayer3);
        Player mPlayer4 = new Player(imageIcon2);
        mPlayersData.add(mPlayer4);
    }

    private void initializeBall()
    {
        ImageIcon imageIcon = new ImageIcon(IMG_BALL);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(23, 23, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        mBall = new Ball(imageIcon);
    }

    private void increaseSeconds()
    {
        seconds++;
    }

    private void resetSeconds()
    {
        seconds = 0;
        gameStarted = false;
        teamA = 0;
        teamB = 0;
        ballDirection = Directions.RIGHT;
        if (mGameStarted != null)
        {
            mGameStarted.isRunning(false);
        }
        setPlayers(players);
        initializePlayers();
        initializeBall();
    }

    private void goalScored()
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

    private void changeGameState()
    {
        gameStarted = !gameStarted;
        if (mGameStarted != null && gameStarted)
        {
            mGameStarted.isRunning(true);
        }
    }

    private boolean isGameStarted()
    {
        return gameStarted;
    }

    private void setTime()
    {
        increaseSeconds();
        timerHours.setText(timeBased(getSeconds() / (60 * 60)));
        timerMinutes.setText(timeBased(getSeconds() / (60) % 60));
        timerSeconds.setText(timeBased(getSeconds() % 60));
    }

    private void setMainActivity(JFrame mainActivity)
    {
        mainActivityGlobal = mainActivity;
        initializePlayers();
        initializeBall();
    }

    private void setPlayers(TeamMembers noPlayers)
    {
        players = noPlayers;
        if (mTeamMembersNumber != null)
        {
            mTeamMembersNumber.onMembersChanged(noPlayers);
        }
    }

    private void setBallDirection(Directions directionN)
    {
        ballDirection = directionN;
    }

    private void setGameSpeed(int speed)
    {
        gameSpeed = speed;
        if (mGameSpeedCallback != null)
        {
            mGameSpeedCallback.onSpeedChanged();
        }
    }

    private void setBallObj()
    {
    }

    private JFrame getAppWindow()
    {
        return mainActivityGlobal;
    }

    private int getNoPlayers()
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

    private Player getPlayer(int player)
    {
        return mPlayersData.get(player - 1);
    }

    private Directions getBallDirection()
    {
        return ballDirection;
    }

    private Ball getBall()
    {
        return mBall;
    }

    private int getSeconds()
    {
        return seconds;
    }

    private AutoMoveBall getAutoMoveBall()
    {
        return mAutoMoveBall;
    }

    private int getGameSpeed()
    {
        return gameSpeed;
    }

    private String getBallPosition()
    {
        return mBallObj.getLocation().x / 33 + "x" + (mBallObj.getLocation().y / 26);
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

        Ball mBall = getBall();
        JLabel ball = new JLabel("");
        ball.setIcon(mBall.getBallImage());
        ball.setLocation(0, 0);

        mBallObj = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mBallObj.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mBallObj.setBounds(0, 0, 23, 23);
        mBallObj.setOpaque(false);
        mBallObj.add(ball);
        ballHolder.add(mBallObj);
        setBallObj();

        gameWorldHolder.add(ballHolder, Integer.valueOf(2));

    }

    private void addPlayers()
    {

        addTeamA();
        addTeamB();

    }

    private void addTeamA()
    {

        Player mPlayer1 = getPlayer(1);

        JLabel mPlayerLabel = new JLabel("");
        mPlayerLabel.setIcon(mPlayer1.getPlayerImg());
        mPlayerLabel.setLocation(0, 0);

        JPanel mPlayerJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mPlayerJPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mPlayerJPanel.setBounds(0, 0, 31, 31);
        mPlayerJPanel.setOpaque(false);
        mPlayerJPanel.add(mPlayerLabel);
        mPlayers.add(mPlayerJPanel);

        gameWorldHolder.add(mPlayers.get(0), Integer.valueOf(3));

        Player mPlayer3 = getPlayer(3);

        mPlayerLabel = new JLabel("");
        mPlayerLabel.setIcon(mPlayer3.getPlayerImg());
        mPlayerLabel.setLocation(0, 0);

        mPlayerJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mPlayerJPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mPlayerJPanel.setBounds(0, 0, 31, 31);
        mPlayerJPanel.setOpaque(false);
        mPlayerJPanel.add(mPlayerLabel);
        mPlayers.add(mPlayerJPanel);

        gameWorldHolder.add(mPlayers.get(1), Integer.valueOf(4));

    }

    private void addTeamB()
    {

        Player mPlayer1 = getPlayer(2);

        JLabel mPlayerLabel = new JLabel("");
        mPlayerLabel.setIcon(mPlayer1.getPlayerImg());
        mPlayerLabel.setLocation(0, 0);

        JPanel mPlayerJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mPlayerJPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mPlayerJPanel.setBounds(0, 0, 31, 31);
        mPlayerJPanel.setOpaque(false);
        mPlayerJPanel.add(mPlayerLabel);
        mPlayers.add(mPlayerJPanel);

        gameWorldHolder.add(mPlayers.get(2), Integer.valueOf(5));

        Player mPlayer3 = getPlayer(4);

        mPlayerLabel = new JLabel("");
        mPlayerLabel.setIcon(mPlayer3.getPlayerImg());
        mPlayerLabel.setLocation(0, 0);

        mPlayerJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mPlayerJPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mPlayerJPanel.setBounds(0, 0, 31, 31);
        mPlayerJPanel.setOpaque(false);
        mPlayerJPanel.add(mPlayerLabel);
        mPlayers.add(mPlayerJPanel);

        gameWorldHolder.add(mPlayers.get(3), Integer.valueOf(6));

    }

    private void addBallSquareCallback(BallSquare ballSquare)
    {
        mBallSquare = ballSquare;
    }

    private void addGoalScoredCallback(GoalScored goalScored)
    {
        mGoalScored = goalScored;
    }

    private void addOnGameSpeedCallback(GameSpeed gameSpeed)
    {
        mGameSpeedCallback = gameSpeed;
    }

    private void addOnTimerCallback(GameStarted gameStarted)
    {
        mGameStarted = gameStarted;
    }

    private void addOnAutoMoveBallCallback(AutoMoveBall autoMoveBall)
    {
        mAutoMoveBall = autoMoveBall;
    }

    private void addOnTeamMembersListener(TeamMembersNumber teamMembersNumber)
    {
        mTeamMembersNumber = teamMembersNumber;
    }

    private BallSquare getBallSquare()
    {
        return mBallSquare;
    }

    // method that retries the relevant image based on the image path
    // the path is passed as a parameter into the function
    // the parameter name is 'img'
    private ImageIcon getImageIcon(String img)
    {
        return new ImageIcon(img);
    }

    private JButton createButton(Icon icon, String text)
    {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(255, 255, 255));
        btn.setIcon(icon);
        btn.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(0, 0, 0), 1),
                        BorderFactory.createEmptyBorder(3, 8, 3, 8)
                )
        );
        return btn;
    }

    JSlider createHorizontalSlider(int current)
    {

        JSlider materialSlider = new JSlider(1, 5);
        materialSlider.setMajorTickSpacing(1);
        materialSlider.setMinorTickSpacing(1);
        materialSlider.setBackground(new Color(241, 241, 241));
        materialSlider.setPaintTicks(true);
        materialSlider.setPaintLabels(true);
        materialSlider.setValue(current);
        Font font = new Font("Serif", Font.PLAIN, 13);
        materialSlider.setFont(font);
        return materialSlider;

    }

}

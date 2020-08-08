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

import app.models.Player;
import app.utils.material.MaterialButton;
import app.utils.material.MaterialElements;
import app.utils.material.MaterialLabel;
import app.utils.material.MaterialSlider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
        setIconImage(getImageIcon(IMG_BRICKS).getImage());

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
        prepareGame();

    }

    public void prepareGame()
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

    private JPanel gameWorld;
    private MaterialElements materialElements;
    private JLayeredPane gameWorldHolder;
    private final int rows = 13;
    private final int columns = 16;
    private MaterialLabel[][] gameGrid = new MaterialLabel[rows][columns];
    public JPanel mBallObj;
    private ArrayList<JPanel> mPlayers = new ArrayList<>();

    public void gameWorld()
    {

        materialElements = new MaterialElements();
        gameWorld = new JPanel();
        gameWorld.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
        gameWorld.setPreferredSize(new Dimension(570, 440));
        gameWorld.setBackground(new Color(241, 241, 241));

        initializeGameSubHolder();

    }

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

    private boolean isCollidingA(Point mPlayer, Point mBallObj)
    {
        return mBallObj.x >= mPlayer.x && mBallObj.x <= mPlayer.x + 32 &&
                mBallObj.y >= mPlayer.y && mBallObj.y <= mPlayer.y + 32;
    }

    private boolean isCollidingB(Point mPlayer, Point mBallObj)
    {
        return mBallObj.x >= mPlayer.x && mBallObj.x <= mPlayer.x + 32 &&
                mBallObj.y >= mPlayer.y && mBallObj.y <= mPlayer.y + 32;
    }

    private int randomNumber()
    {
        return (int) (Math.random() * ((3 - 1) + 1)) + 1;
    }

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
                    gameGrid[i][j] = materialElements.createLabel("");
                    gameGrid[i][j].setIcon(getImageIcon(IMG_BRICKS));
                } else
                {
                    gameGrid[i][j] = materialElements.createLabel("");
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
        MaterialLabel ball = materialElements.createLabel("");
        ball.setIcon(mBall.getBallImage());
        ball.setLocation(0, 0);

        mBallObj = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mBallObj.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mBallObj.setBounds(0, 0, 23, 23);
        mBallObj.setOpaque(false);
        mBallObj.add(ball);
        ballHolder.add(mBallObj);
        setBallObj(mBallObj);

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

        MaterialLabel mPlayerLabel = materialElements.createLabel("");
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

        mPlayerLabel = materialElements.createLabel("");
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

        MaterialLabel mPlayerLabel = materialElements.createLabel("");
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

        mPlayerLabel = materialElements.createLabel("");
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

    private JPanel gameOptions;
    private MaterialLabel timerHours, timerMinutes, timerSeconds;
    private MaterialLabel scoreTeamLeft, scoreTeamRight;
    private MaterialLabel optionLabel, directionLabel, squareLabel;
    private MaterialLabel compassLabel;

    enum Players
    {
        TWO,
        FOUR
    }

    public void gameOptions()
    {

        materialElements = new MaterialElements();
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

        MaterialLabel timerLabel = materialElements.createLabel("DIGITAL TIMER");
        timerLabelHolder.add(timerLabel, BorderLayout.CENTER);
        gameOptions.add(timerLabelHolder);

        JPanel timerHolder = new JPanel();
        timerHolder.setBackground(new Color(0, 0, 0, 0));
        timerHolder.setPreferredSize(new Dimension(255, 18));
        timerHolder.setBorder(BorderFactory.createEmptyBorder(-3, 0, 0, 0));

        timerHours = createTimerElement("00");
        timerMinutes = createTimerElement("00");
        timerSeconds = createTimerElement("00");

        timerHolder.add(timerHours);
        timerHolder.add(materialElements.createLabel(" : "));
        timerHolder.add(timerMinutes);
        timerHolder.add(materialElements.createLabel(" : "));
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

    private void setTime()
    {
        increaseSeconds();
        timerHours.setText(timeBased(getSeconds() / (60 * 60)));
        timerMinutes.setText(timeBased(getSeconds() / (60) % 60));
        timerSeconds.setText(timeBased(getSeconds() % 60));
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

        MaterialLabel scoreLabel = materialElements.createLabel("SCORE");
        scoreLabelHolder.add(scoreLabel, BorderLayout.CENTER);
        gameOptions.add(scoreLabelHolder);

        JPanel scoreHolder = new JPanel();
        scoreHolder.setBackground(new Color(0, 0, 0, 0));
        scoreHolder.setPreferredSize(new Dimension(255, 16));
        scoreHolder.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0, 0));

        scoreTeamLeft = createScoreElement(teamA);
        scoreTeamRight = createScoreElement(teamB);

        scoreHolder.add(scoreTeamLeft);
        scoreHolder.add(materialElements.createLabel(" < L : R > "));
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

        MaterialButton btnUp = createGridElementChoice("up");
        MaterialButton btnDown = createGridElementChoice("down");
        MaterialButton btnLeft = createGridElementChoice("left");
        MaterialButton btnRight = createGridElementChoice("right");

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
        compassLabel = materialElements.createLabel("");
        compassLabel.setBackground(new Color(0, 0, 0, 0));

        ImageIcon imageIcon = new ImageIcon("assets/images/east.jpg");
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

        MaterialButton btnPlayers2 = createOptionChoice("2 Player");
        MaterialButton btnPlayers4 = createOptionChoice("4 Player");
        MaterialButton btnMulti = createOptionChoice("Multi");
        MaterialButton btnExit = createOptionChoice("Exit");

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

    private MaterialLabel createTimerElement(String text)
    {
        MaterialLabel materialLabel = materialElements.createLabel(text);
        materialLabel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
        materialLabel.setOpaque(true);
        materialLabel.setBackground(Color.black);
        materialLabel.setForeground(Color.white);
        return materialLabel;
    }

    private MaterialLabel createScoreElement(int score)
    {
        String content = String.valueOf(score);
        if (score < 10)
        {
            content = "0" + content;
        }
        MaterialLabel materialLabel = materialElements.createLabel(content);
        materialLabel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
        materialLabel.setOpaque(true);
        materialLabel.setBackground(Color.black);
        materialLabel.setForeground(Color.white);
        return materialLabel;
    }

    private MaterialLabel createOptionItem(String text)
    {
        MaterialLabel materialLabel = materialElements.createLabel(text);
        materialLabel.setBorder(BorderFactory.createEmptyBorder(2, 3, 2, 3));
        materialLabel.setOpaque(true);
        materialLabel.setBackground(Color.white);
        return materialLabel;
    }

    private JPanel createOptionItem(String content, MaterialLabel label)
    {

        JPanel optionItemHolder = new JPanel();
        optionItemHolder.setBackground(new Color(0, 0, 0, 0));
        optionItemHolder.setPreferredSize(new Dimension(255, 30));
        optionItemHolder.setBorder(BorderFactory.createEmptyBorder(7, 35, 0, 20));
        optionItemHolder.setLayout(new BorderLayout());

        MaterialLabel contentLabel = materialElements.createLabel(content);
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
        MaterialLabel contentLabel = materialElements.createLabel("");
        Icon iconAct = new ImageIcon("assets/images/ball.png");
        contentLabel.setIcon(iconAct);
        optionItemChoseHolder.add(contentLabel);

        return optionItemChoseHolder;

    }

    private MaterialButton createGridElementChoice(String content)
    {

        MaterialButton optionItemChoseHolder = materialElements.createButton(null, "");
        optionItemChoseHolder.setLayout(new GridBagLayout());
        optionItemChoseHolder.setBackground(new Color(255, 255, 255));
        optionItemChoseHolder.setPreferredSize(new Dimension(80, 25));
        MaterialLabel contentLabel = materialElements.createLabel(content);
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
                imageIcon = new ImageIcon("assets/images/south.jpg");
                image = imageIcon.getImage();
                image = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(image);
                compassLabel.setIcon(imageIcon);
                directionLabel.setText("S");
                break;
            case UP:
                imageIcon = new ImageIcon("assets/images/north.jpg");
                image = imageIcon.getImage();
                image = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(image);
                compassLabel.setIcon(imageIcon);
                directionLabel.setText("N");
                break;
            case LEFT:
                imageIcon = new ImageIcon("assets/images/west.jpg");
                image = imageIcon.getImage();
                image = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(image);
                compassLabel.setIcon(imageIcon);
                directionLabel.setText("W");
                break;
            case RIGHT:
                imageIcon = new ImageIcon("assets/images/east.jpg");
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

    private MaterialButton createOptionChoice(String content)
    {

        MaterialButton optionItemChoseHolder = materialElements.createButton(null, "");
        optionItemChoseHolder.setLayout(new GridBagLayout());
        optionItemChoseHolder.setBackground(new Color(255, 255, 255));
        optionItemChoseHolder.setPreferredSize(new Dimension(80, 30));
        MaterialLabel contentLabel = materialElements.createLabel(content);
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

    private JPanel gameControls;
    private MaterialButton btnState;
    private MaterialSlider slider;

    public void gameControls()
    {

        materialElements = new MaterialElements();
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

        Icon iconAct = new ImageIcon("assets/images/step.png");
        MaterialButton btnAct = materialElements.createButton(iconAct, "Act");
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

        Icon iconAct = new ImageIcon("assets/images/run.png");
        btnState = materialElements.createButton(iconAct, "Run");
        btnState.addActionListener(actionEvent ->
        {

            if (!isGameStarted())
            {
                btnState.setIcon(new ImageIcon("assets/images/pause.png"));
                btnState.setText("Pause");
            } else
            {
                btnState.setIcon(new ImageIcon("assets/images/run.png"));
                btnState.setText("Run");
            }
            changeGameState();
        });
        gameControls.add(btnState);

    }

    private void loadResetBtn()
    {

        Icon iconAct = new ImageIcon("assets/images/reset.png");
        MaterialButton btnReset = materialElements.createButton(iconAct, "Reset");
        btnReset.addActionListener(actionEvent ->
        {
            resetSeconds();
            btnState.setIcon(new ImageIcon("assets/images/run.png"));
            btnState.setText("Run");
        });
        gameControls.add(btnReset);

    }

    private void prepareSlider()
    {

        MaterialLabel sliderTitle = materialElements.createLabel("Speed:");
        sliderTitle.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
        gameControls.add(sliderTitle);

        slider = materialElements.createHorizontalSlider(1, 5, getGameSpeed());
        slider.addChangeListener(changed -> sliderChanged());
        gameControls.add(slider);

    }

    private void sliderChanged()
    {
        setGameSpeed(slider.getValue());
    }

    public interface BallCallback
    {
        interface AutoMoveBall
        {
            void moveTo(Directions direction);
        }
    }

    public interface BallSquare
    {
        void newSquare();
    }

    public interface GameSpeed
    {
        void onSpeedChanged();
    }

    public interface GameTimer
    {
        interface GameStarted
        {
            void isRunning(boolean running);
        }
    }

    public interface GoalScored
    {
        void scored();
    }

    public interface TeamMembersNumber
    {
        void onMembersChanged(TeamMembers players);
    }

    public enum TeamMembers
    {
        players_2,
        players_4
    }

    public enum Directions
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



    private Directions ballDirection = Directions.RIGHT;
    private JFrame mainActivityGlobal;
    public int teamA = 0, teamB = 0;

    public void setMainActivity(JFrame mainActivity)
    {
        mainActivityGlobal = mainActivity;
        initializePlayers();
        initializeBall();
    }

    public JFrame getAppWindow()
    {
        return mainActivityGlobal;
    }

    private TeamMembersNumber mTeamMembersNumber;

    public void addOnTeamMembersListener(TeamMembersNumber teamMembersNumber)
    {
        mTeamMembersNumber = teamMembersNumber;
    }

    private TeamMembers players = TeamMembers.players_2;

    public void setPlayers(TeamMembers noPlayers)
    {
        players = noPlayers;
        if (mTeamMembersNumber != null)
        {
            mTeamMembersNumber.onMembersChanged(noPlayers);
        }
    }

    public int getNoPlayers()
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


    private final java.util.List<Player> mPlayersData = new ArrayList<>();

    public void initializePlayers()
    {
        ImageIcon imageIcon1 = new ImageIcon("assets/images/baby1.png");
        Image image1 = imageIcon1.getImage();
        Image newimg1 = image1.getScaledInstance(31, 31, Image.SCALE_SMOOTH);
        imageIcon1 = new ImageIcon(newimg1);

        ImageIcon imageIcon2 = new ImageIcon("assets/images/baby2.png");
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

    public Player getPlayer(int player)
    {
        return mPlayersData.get(player - 1);
    }

    public java.util.List<Player> getPlayer()
    {
        return mPlayersData;
    }

    public void setBallDirection(Directions directionN)
    {
        ballDirection = directionN;
    }

    public Directions getBallDirection()
    {
        return ballDirection;
    }

    private Ball mBall;

    public void initializeBall()
    {
        ImageIcon imageIcon = new ImageIcon("assets/images/ball.png");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(23, 23, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        mBall = new Ball(imageIcon);
    }

    public Ball getBall()
    {
        return mBall;
    }

    private int seconds = 0;

    public void increaseSeconds()
    {
        seconds++;
    }

    public void resetSeconds()
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

    public void goalScored()
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

    public int getSeconds()
    {
        return seconds;
    }

    private boolean gameStarted = false;

    public void changeGameState()
    {
        gameStarted = !gameStarted;
        if (mGameStarted != null && gameStarted)
        {
            mGameStarted.isRunning(gameStarted);
        }
    }

    public boolean isGameStarted()
    {
        return gameStarted;
    }

    private GameTimer.GameStarted mGameStarted;

    public void addOnTimerCallback(GameTimer.GameStarted gameStarted)
    {
        mGameStarted = gameStarted;
    }

    private BallCallback.AutoMoveBall mAutoMoveBall;

    public void addOnAutoMoveBallCallback(BallCallback.AutoMoveBall autoMoveBall)
    {
        mAutoMoveBall = autoMoveBall;
    }

    public BallCallback.AutoMoveBall getAutoMoveBall()
    {
        return mAutoMoveBall;
    }

    private int gameSpeed = 1;

    public int getGameSpeed()
    {
        return gameSpeed;
    }

    public void setGameSpeed(int speed)
    {
        gameSpeed = speed;
        if (mGameSpeedCallback != null)
        {
            mGameSpeedCallback.onSpeedChanged();
        }
    }

    private GameSpeed mGameSpeedCallback;

    public void addOnGameSpeedCallback(GameSpeed gameSpeed)
    {
        mGameSpeedCallback = gameSpeed;
    }

    public GameSpeed getGameSpeedCallback()
    {
        return mGameSpeedCallback;
    }

    public String getBallPosition()
    {
        return mBallObj.getLocation().x/33 + "x" + (mBallObj.getLocation().y/26);
    }

    public void setBallObj(JPanel ballObj)
    {
    }

    private BallSquare mBallSquare;

    public void addBallSquareCallback(BallSquare ballSquare)
    {
        mBallSquare = ballSquare;
    }

    public BallSquare getBallSquare()
    {
        return mBallSquare;
    }

    private GoalScored mGoalScored;

    public void addGoalScoredCallback(GoalScored goalScored)
    {
        mGoalScored = goalScored;
    }

    // method that retries the relevant image based on the image path
    // the path is passed as a parameter into the function
    // the parameter name is 'img'
    public ImageIcon getImageIcon(String img)
    {
        return new ImageIcon(img);
    }

    public static final String IMG_BRICKS = "assets/images/bricks2.jpg";
    public static final String IMG_WHITE_SQUARE = "assets/images/white32x32.jpg";

    class Ball
    {

        private final ImageIcon ballImage;

        public Ball(ImageIcon image)
        {
            this.ballImage = image;
        }

        public ImageIcon getBallImage()
        {
            return ballImage;
        }
    }

    class Player
    {

        private final ImageIcon playerImg;

        public Player(ImageIcon image)
        {
            this.playerImg = image;
        }

        public ImageIcon getPlayerImg()
        {
            return playerImg;
        }

    }

}

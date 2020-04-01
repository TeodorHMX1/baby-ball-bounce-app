package app.game.world;

import app.models.Ball;
import app.models.Player;
import app.utils.AppUtils;
import app.utils.enums.Directions;
import app.utils.material.MaterialElements;
import app.utils.material.MaterialLabel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameWorld {

    private JPanel gameWorld;
    private MaterialElements materialElements;
    private JLayeredPane gameWorldHolder;
    private final int rows = 13;
    private final int columns = 16;
    private MaterialLabel[][] gameGrid = new MaterialLabel[rows][columns];
    private JPanel mBallObj;
    private ArrayList<JPanel> mPlayers = new ArrayList<>();

    public GameWorld() {

        materialElements = new MaterialElements();
        gameWorld = new JPanel();
        gameWorld.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
        gameWorld.setPreferredSize(new Dimension(570, 440));
        gameWorld.setBackground(new Color(241, 241, 241));

        initializeGameSubHolder();

    }

    private void initializeGameSubHolder() {

        initializeGameWorldHolder();
        initializeGameGrid();
        addGameObjects();
        gameWorld.add(gameWorldHolder);

        Timer timer = new Timer(1000 - AppUtils.getGameSpeed() * 200, actionEvent -> {
            if(AppUtils.isGameStarted()) {
                moveBallTo(Directions.RIGHT);
            }
        });
        timer.setInitialDelay(0);
        timer.start();
        AppUtils.addOnGameSpeedCallback(() -> {
            if(AppUtils.isGameStarted()) {
                timer.setDelay(1000 - AppUtils.getGameSpeed() * 200);
            }
        });

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

    private void initializeGameGrid() {

        int i, j;
        for (i = 0; i < rows; i++) {
            for (j = 0; j < columns; j++) {
                if (j == columns / 2 || j == columns / 2 - 1) {
                    gameGrid[i][j] = materialElements.createLabel("");
                    gameGrid[i][j].setIcon(new ImageIcon("assets/images/bricks2.jpg"));
                } else {
                    gameGrid[i][j] = materialElements.createLabel("");
                    gameGrid[i][j].setIcon(new ImageIcon("assets/images/white32x32.jpg"));
                }
            }
        }

        GridLayout mGridLayoutGameWord = new GridLayout(13, 16, 0, 0);
        JPanel mGameWorldHolder = new JPanel();
        mGameWorldHolder.setLayout(mGridLayoutGameWord);
        mGameWorldHolder.setBackground(new Color(0, 0, 0, 0));
        mGameWorldHolder.setPreferredSize(new Dimension(530, 360));
        mGameWorldHolder.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        for (i = 0; i < rows; i++) {
            for (j = 0; j < columns; j++) {
                mGameWorldHolder.add(gameGrid[i][j]);
            }
        }

        JPanel field = new JPanel();
        field.setBounds(0, 0, 530, 360);
        field.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        field.setBackground(Color.white);
        field.setOpaque(true);
        FlowLayout layout = (FlowLayout) field.getLayout();
        layout.setVgap(0);
        field.add(mGameWorldHolder);
        field.setLocation(0, 0);

        gameWorldHolder.add(field, Integer.valueOf(1));

    }

    private void addGameObjects()
    {

        addBall();
        addPlayers();

    }

    private void addBall() {

        JPanel ballHolder = new JPanel();
        ballHolder.setBounds(0, 0, 530, 360);
        ballHolder.setLocation(0, 0);
        ballHolder.setOpaque(false);
        ballHolder.setLayout(null);

        Ball mBall = AppUtils.getBall();
        MaterialLabel ball = materialElements.createLabel("");
        ball.setIcon(mBall.getBallImage());
        ball.setLocation(0, 0);

        mBallObj = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mBallObj.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mBallObj.setBounds(0, 0, 23, 23);
        mBallObj.setOpaque(false);
        mBallObj.setLocation(20, 70);
        mBallObj.add(ball);
        ballHolder.add(mBallObj);

        gameWorldHolder.add(ballHolder, Integer.valueOf(2));

//        AppUtils.addOnAutoMoveBallCallback(this::moveBallTo);

    }

    private void moveBallTo(Directions direction) {

//        AppUtils.getBall().moveTo(direction);
//        Ball mBall = AppUtils.getBall();
//        gameGrid[mBall.getX()][mBall.getY()].setBounds(100, 100, 100, 100);
//        gameGrid[mBall.getX()][mBall.getY()].setIcon(mBall.getBallImage());

    }

    private void addPlayers() {

        addTeamA();
        addTeamB();

    }

    private void addTeamA() {

        Player mPlayer1 = AppUtils.getPlayer(1);

        MaterialLabel mPlayerLabel = materialElements.createLabel("");
        mPlayerLabel.setIcon(mPlayer1.getBabyImage());
        mPlayerLabel.setLocation(0, 0);

        JPanel mPlayerJPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mPlayerJPanel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mPlayerJPanel1.setBounds(0, 0, 31, 31);
        mPlayerJPanel1.setOpaque(false);
        mPlayerJPanel1.setLocation(0, 0);
        mPlayerJPanel1.add(mPlayerLabel);
        mPlayers.add(mPlayerJPanel1);

        gameWorldHolder.add(mPlayers.get(0), Integer.valueOf(3));

        Player mPlayer3 = AppUtils.getPlayer(1);

        mPlayerLabel = materialElements.createLabel("");
        mPlayerLabel.setIcon(mPlayer3.getBabyImage());
        mPlayerLabel.setLocation(0, 0);

        JPanel mPlayerJPanel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mPlayerJPanel3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mPlayerJPanel3.setBounds(0, 0, 31, 31);
        mPlayerJPanel3.setOpaque(false);
        mPlayerJPanel3.setLocation(35, 0);
        mPlayerJPanel3.add(mPlayerLabel);
        mPlayers.add(mPlayerJPanel3);

        gameWorldHolder.add(mPlayers.get(1), Integer.valueOf(4));

//        if (AppUtils.getNoPlayers() == 4) {
//            Player mPlayer2 = AppUtils.getPlayer(3);
//            gameGrid[mPlayer2.getX()][mPlayer2.getY()].setIcon(mPlayer2.getBabyImage());
//        }

    }

    private void addTeamB() {

        Player mPlayer1 = AppUtils.getPlayer(2);

        MaterialLabel mPlayerLabel = materialElements.createLabel("");
        mPlayerLabel.setIcon(mPlayer1.getBabyImage());
        mPlayerLabel.setLocation(0, 0);

        JPanel mPlayerJPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mPlayerJPanel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mPlayerJPanel1.setBounds(0, 0, 31, 31);
        mPlayerJPanel1.setOpaque(false);
        mPlayerJPanel1.setLocation(0, 35);
        mPlayerJPanel1.add(mPlayerLabel);
        mPlayers.add(mPlayerJPanel1);

        gameWorldHolder.add(mPlayers.get(2), Integer.valueOf(5));

        Player mPlayer3 = AppUtils.getPlayer(4);

        mPlayerLabel = materialElements.createLabel("");
        mPlayerLabel.setIcon(mPlayer3.getBabyImage());
        mPlayerLabel.setLocation(0, 0);

        JPanel mPlayerJPanel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mPlayerJPanel3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mPlayerJPanel3.setBounds(0, 0, 31, 31);
        mPlayerJPanel3.setOpaque(false);
        mPlayerJPanel3.setLocation(35, 35);
        mPlayerJPanel3.add(mPlayerLabel);
        mPlayers.add(mPlayerJPanel3);

        gameWorldHolder.add(mPlayers.get(3), Integer.valueOf(6));

//        Player mPlayer1 = AppUtils.getPlayer(2);
//        gameGrid[mPlayer1.getX()][mPlayer1.getY()].setIcon(mPlayer1.getBabyImage());
//        if (AppUtils.getNoPlayers() == 4) {
//            Player mPlayer2 = AppUtils.getPlayer(4);
//            gameGrid[mPlayer2.getX()][mPlayer2.getY()].setIcon(mPlayer2.getBabyImage());
//        }

    }

    public JPanel getGameWorldContainer() {
        return gameWorld;
    }

}

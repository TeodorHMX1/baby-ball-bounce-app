package app.game.world;

import app.interfaces.GameSpeed;
import app.models.Ball;
import app.models.Player;
import app.utils.AppUtils;
import app.utils.enums.Directions;
import app.utils.material.MaterialElements;
import app.utils.material.MaterialLabel;

import javax.swing.*;
import java.awt.*;

public class GameWorld {

    private JPanel gameWorld;
    private MaterialElements materialElements;

    public GameWorld() {

        materialElements = new MaterialElements();
        gameWorld = new JPanel();
        gameWorld.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
//        gameWorld.setLayout(new BorderLayout());
        gameWorld.setPreferredSize(new Dimension(570, 440));
        gameWorld.setBackground(new Color(241, 241, 241));

        initializeGameSubHolder();

    }

    private final int rows = 13;
    private final int columns = 16;
    private MaterialLabel[][] gameGrid = new MaterialLabel[rows][columns];
    private MaterialLabel ball;

    private void initializeGameSubHolder() {

        initializeGameGrid();

        GridLayout experimentLayout = new GridLayout(13, 16, 0, 0);
        JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        compsToExperiment.setBackground(new Color(0, 0, 0, 0));
        compsToExperiment.setPreferredSize(new Dimension(530, 360));
        compsToExperiment.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        int i, j;
        for (i = 0; i < rows; i++) {
            for (j = 0; j < columns; j++) {
                compsToExperiment.add(gameGrid[i][j]);
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
        field.add(compsToExperiment);
        field.setLocation(0, 0);
//        gameWorld.add(field);

        Ball mBall = AppUtils.getBall();
        ball = materialElements.createLabel("");
        ball.setIcon(mBall.getBallImage());
        ball.setBounds(100, 100, 100, 100);

        JPanel ballHolder = new JPanel();
        ballHolder.setBounds(0, 0, 530, 360);
        ballHolder.add(ball);
        ballHolder.setLocation(0, 0);
//        gameWorld.add(ballHolder);//, BorderLayout.SOUTH);
        JLayeredPane lp = new JLayeredPane();
        lp.setPreferredSize(new Dimension(530, 360));
        lp.setBackground(Color.white);
        lp.setOpaque(true);
        lp.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//        lp.add(field, 1);

        lp.add(field, Integer.valueOf(1));
//        lp.add(ballHolder, Integer.valueOf(2));
        gameWorld.add(lp);

//        addBall();
        addPlayers();

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

    private void addPlayers() {

        addTeamA();
        addTeamB();

    }

    private void addTeamA() {

        Player mPlayer1 = AppUtils.getPlayer(1);
        gameGrid[mPlayer1.getX()][mPlayer1.getY()].setIcon(mPlayer1.getBabyImage());
        if (AppUtils.getNoPlayers() == 4) {
            Player mPlayer2 = AppUtils.getPlayer(3);
            gameGrid[mPlayer2.getX()][mPlayer2.getY()].setIcon(mPlayer2.getBabyImage());
        }

    }

    private void addTeamB() {

        Player mPlayer1 = AppUtils.getPlayer(2);
        gameGrid[mPlayer1.getX()][mPlayer1.getY()].setIcon(mPlayer1.getBabyImage());
        if (AppUtils.getNoPlayers() == 4) {
            Player mPlayer2 = AppUtils.getPlayer(4);
            gameGrid[mPlayer2.getX()][mPlayer2.getY()].setIcon(mPlayer2.getBabyImage());
        }

    }

    private void addBall() {

        Ball mBall = AppUtils.getBall();
        ball.setIcon(mBall.getBallImage());

        AppUtils.addOnAutoMoveBallCallback(this::moveBallTo);

    }

    private void moveBallTo(Directions direction) {

//        AppUtils.getBall().moveTo(direction);
        Ball mBall = AppUtils.getBall();
        gameGrid[mBall.getX()][mBall.getY()].setBounds(100, 100, 100, 100);
//        gameGrid[mBall.getX()][mBall.getY()].setIcon(mBall.getBallImage());

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

    }

    public JPanel getGameWorldContainer() {
        return gameWorld;
    }

}

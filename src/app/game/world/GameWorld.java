package app.game.world;

import app.utils.material.MaterialElements;
import app.utils.material.MaterialLabel;

import javax.swing.*;
import java.awt.*;

public class GameWorld
{

    private JPanel gameWorld;
    private MaterialElements materialElements;

    public GameWorld()
    {

        materialElements = new MaterialElements();
        gameWorld = new JPanel();
        gameWorld.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
        gameWorld.setLayout(new BorderLayout());
        gameWorld.setPreferredSize(new Dimension(570, 440));
        gameWorld.setBackground(new Color(241, 241, 241));

        initializeGameSubHolder();

    }

    private final int rows = 13;
    private final int columns = 16;
    private MaterialLabel gameGrid[][] = new MaterialLabel[rows][columns];
    private void initializeGameSubHolder() {

        JPanel field = new JPanel();
        field.setPreferredSize(new Dimension(530, 360));
        field.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        field.setBackground(Color.white);
        FlowLayout layout = (FlowLayout)field.getLayout();
        layout.setVgap(0);
        int i, j;
        for(i=0; i<rows; i++)
        {
            for(j=0; j<columns; j++)
            {
                if(i == 6 && j == 4)
                {
                    gameGrid[i][j] = materialElements.createLabel("");
                    ImageIcon imageIcon = new ImageIcon("assets/images/baby1.png");
                    Image image = imageIcon.getImage();
                    Image newimg = image.getScaledInstance(31, 31, Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(newimg);
                    gameGrid[i][j].setIcon(imageIcon);
                }
                else if(i == 6 && j == 11)
                {
                    gameGrid[i][j] = materialElements.createLabel("");
                    ImageIcon imageIcon = new ImageIcon("assets/images/baby2.png");
                    Image image = imageIcon.getImage();
                    Image newimg = image.getScaledInstance(31, 31, Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(newimg);
                    gameGrid[i][j].setIcon(imageIcon);
                }
                else if(i == 6 && j == 5)
                {
                    gameGrid[i][j] = materialElements.createLabel("");
                    ImageIcon imageIcon = new ImageIcon("assets/images/ball.png");
                    Image image = imageIcon.getImage();
                    Image newimg = image.getScaledInstance(23, 23, Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(newimg);
                    gameGrid[i][j].setIcon(imageIcon);
                }
                else if(j == columns/2 || j == columns/2 - 1)
                {
                    gameGrid[i][j] = materialElements.createLabel("");
                    gameGrid[i][j].setIcon(new ImageIcon("assets/images/bricks2.jpg"));
                }
                else
                {
                    gameGrid[i][j] = materialElements.createLabel("");
                    gameGrid[i][j].setIcon(new ImageIcon("assets/images/white32x32.jpg"));
                }
            }
        }

        GridLayout experimentLayout = new GridLayout(13, 16, 0, 0);
        JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        compsToExperiment.setBackground(new Color(0, 0, 0, 0));
        compsToExperiment.setPreferredSize(new Dimension(530, 360));
        compsToExperiment.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        for(i=0; i<rows; i++)
        {
            for(j=0; j<columns; j++)
            {
                compsToExperiment.add(gameGrid[i][j]);
            }
        }
        field.add(compsToExperiment);

        gameWorld.add(field, BorderLayout.CENTER);

    }

    public JPanel getGameWorldContainer()
    {
        return gameWorld;
    }
    
}

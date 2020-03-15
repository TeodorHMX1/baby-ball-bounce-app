package app.game.control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameControls
{

    private JPanel gameControls;
    private boolean btnStateRun = false;

    public GameControls()
    {

        gameControls = new JPanel();
        gameControls.setLayout(new FlowLayout());
        gameControls.setBackground(new Color(241, 241, 241));
        gameControls.setPreferredSize(new Dimension(570, 60));

        prepareButtons();
        prepareSlider();

    }

    private void prepareButtons() {

        loadActBtn();
        loadStateBtn();
        loadResetBtn();

    }

    private void loadActBtn() {

        Icon iconAct = new ImageIcon("assets/images/step.png");
        JButton btnAct = new JButton(iconAct);
        btnAct.setText("Act");
        btnAct.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        gameControls.add(btnAct);

    }

    private void loadStateBtn() {

        Icon iconAct = new ImageIcon("assets/images/run.png");
        JButton btnState = new JButton(iconAct);
        btnState.setText("Run");
        btnState.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if(!btnStateRun)
                {
                    btnState.setIcon(new ImageIcon("assets/images/pause.png"));
                    btnState.setText("Pause");
                }
                else
                {
                    btnState.setIcon(new ImageIcon("assets/images/run.png"));
                    btnState.setText("Run");
                }
                btnStateRun = !btnStateRun;
            }
        });
        gameControls.add(btnState);

    }

    private void loadResetBtn() {

        Icon iconAct = new ImageIcon("assets/images/reset.png");
        JButton btnReset = new JButton(iconAct);
        btnReset.setText("Reset");
        gameControls.add(btnReset);

    }

    private void prepareSlider() {



    }

    public JPanel getGameControlsContainer()
    {
        return gameControls;
    }
    
}

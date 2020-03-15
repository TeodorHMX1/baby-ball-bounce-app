package app.game.control;

import app.utils.material.MaterialButton;
import app.utils.material.MaterialElements;
import app.utils.material.MaterialLabel;

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
        MaterialButton btnAct = new MaterialElements()
                .newButton(iconAct, "Act");
        gameControls.add(btnAct);

    }

    private void loadStateBtn() {

        Icon iconAct = new ImageIcon("assets/images/run.png");
        MaterialButton btnState = new MaterialElements()
                .newButton(iconAct, "Run");
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
        MaterialButton btnReset = new MaterialElements()
                .newButton(iconAct, "Reset");
        gameControls.add(btnReset);

    }

    private void prepareSlider() {

        MaterialLabel sliderTitle = new MaterialLabel("Speed:");
        sliderTitle.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 0));
        gameControls.add(sliderTitle);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);
        gameControls.add(slider);

    }

    public JPanel getGameControlsContainer()
    {
        return gameControls;
    }
    
}

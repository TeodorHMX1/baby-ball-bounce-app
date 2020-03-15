package app.utils.material;

import javax.swing.*;
import java.awt.*;

public class MaterialElements {

    public MaterialElements()
    {

    }

    public JButton newButton(Icon icon, String text)
    {
        JButton btn = new JButton(icon);
        btn.setText(text);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 0), 1),
                BorderFactory.createEmptyBorder(3, 8, 3, 8)));
        btn.setBackground(new Color(255, 255, 255));
        return btn;
    }
}

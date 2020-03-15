package app.utils.material;

import javax.swing.*;
import java.awt.*;

public class MaterialElements
{

    public MaterialElements()
    {

    }

    public MaterialButton newButton(Icon icon, String text)
    {
        MaterialButton btn = new MaterialButton(text);
        btn.setBackground(new Color(255, 255, 255));
        btn.setHoverBackgroundColor(new Color(243, 243, 243));
        btn.setPressedBackgroundColor(new Color(230, 230, 230));
        btn.setIcon(icon);
        btn.setBorder(
                BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(0, 0, 0), 1),
                    BorderFactory.createEmptyBorder(3, 8, 3, 8)
                )
        );
        return btn;
    }
}

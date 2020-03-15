import javax.swing.*;
import java.awt.*;

public class MainActivity extends JFrame {

    static MainActivity activity;

    public static void main(String[] args)
    {

        // TODO Auto-generated method stub
        activity = new MainActivity();
        activity.setTitle("CBabyBallBounce – Baby Ball Bounce Application");
        activity.setSize(825, 585);
        activity.createUIX();
        activity.setResizable(false);
        activity.setVisible(true);

    }
    public void createUIX()
    {
        //close on 'x' clicked
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}

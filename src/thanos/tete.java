package thanos;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class tete extends JFrame {

    private JLabel stat_bar;

    public tete() {

        initEsto();
    }

    private void initEsto() {

        stat_bar = new JLabel(" 0");
        add(stat_bar, BorderLayout.SOUTH);

        var tab_x = new tablero(this);
        add(tab_x);
        tab_x.start_game();

        setTitle("Tetris");
        int x = 0;
        int y = 0;
        if (theter.x_dim * 18 >= 1250) {
            x = 1250;
        }else{
            x=theter.x_dim * 18;
        }
        if (theter.y_dim * 18 >= 700) {
            y = 700;
        }else{
            y=theter.y_dim * 18;
        }
        setSize(x, y);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    JLabel getStatBar() {

        return stat_bar;
    }
}

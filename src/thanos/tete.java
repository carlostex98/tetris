package thanos;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class tete extends JFrame{
    private JLabel stat_bar;
    public tete() {

        initEsto();
    }
    
    private void initEsto() {

        stat_bar = new JLabel(" 0");
        add(stat_bar, BorderLayout.SOUTH);

        var board = new tablero (this);
        add(board);
        board.start();

        setTitle("Tetris");
        setSize(200, 400);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    JLabel getStatusBar() {

        return stat_bar;
    }
}

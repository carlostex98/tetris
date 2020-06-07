package thanos;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class tete extends JFrame{
    private JLabel statusbar;
    public tete() {

        initEsto();
    }
    
    private void initEsto() {

        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);

        var board = new tablero (this);
        add(board);
        board.start();

        setTitle("Tetris");
        setSize(200, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    JLabel getStatusBar() {

        return statusbar;
    }
}

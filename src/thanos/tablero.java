package thanos;

import thanos.shape.temte;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class tablero extends JPanel {

    int BOARD_WIDTH = 10;
    int BOARD_HEIGHT = 22;
    int PERIOD_INTERVAL = 300; //300 normal  100->super fast

    Timer timex;
    boolean isFallEnd = false;
    boolean spause = false;
    int lnDrop = 0;
    int cur_x = 0;
    int cur_y = 0;
    JLabel status_bar;
    shape curr_pieza;
    temte[] board;

    public tablero(tete parent) {

        initBoard(parent);
        //throw new UnsupportedOperationException("Not supported yet."); 
    }

    void initBoard(tete parent) {

        setFocusable(true);
        status_bar = parent.getStatBar();
        addKeyListener(new TAdapter());
    }

    int squareWidth() {

        return (int) getSize().getWidth() / BOARD_WIDTH;
    }

    int squareHeight() {

        return (int) getSize().getHeight() / BOARD_HEIGHT;
    }

    temte shapeAt(int x, int y) {

        return board[(y * BOARD_WIDTH) + x];
    }

    void start_game() {

        curr_pieza = new shape();
        board = new temte[BOARD_WIDTH * BOARD_HEIGHT];

        clearBoard();
        newPiece();

        timex = new Timer(PERIOD_INTERVAL, new GameCycle());
        timex.start();
    }

    private void paused() {

        spause = !spause;

        if (spause) {

            status_bar.setText("En pausa");
        } else {

            status_bar.setText(String.valueOf(lnDrop));
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        var size = getSize();
        int boardTop = (int) size.getHeight() - BOARD_HEIGHT * squareHeight();

        for (int i = 0; i < BOARD_HEIGHT; i++) {

            for (int j = 0; j < BOARD_WIDTH; j++) {

                temte shape = shapeAt(j, BOARD_HEIGHT - i - 1);

                if (shape != temte.NShape) {

                    drawSquare(g, j * squareWidth(),
                            boardTop + i * squareHeight(), shape);
                }
            }
        }

        if (curr_pieza.getShape() != temte.NShape) {

            for (int i = 0; i < 4; i++) {

                int x = cur_x + curr_pieza.x(i);
                int y = cur_y - curr_pieza.y(i);

                drawSquare(g, x * squareWidth(),
                        boardTop + (BOARD_HEIGHT - y - 1) * squareHeight(),
                        curr_pieza.getShape());
            }
        }
    }

    private void drop() {

        int newY = cur_y;

        while (newY > 0) {

            if (!Move(curr_pieza, cur_x, newY - 1)) {

                break;
            }

            newY--;
        }

        pieceDropped();
    }

    private void oneDown() {

        if (!Move(curr_pieza, cur_x, cur_y - 1)) {

            pieceDropped();
        }
    }

    private void clearBoard() {

        for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; i++) {

            board[i] = temte.NShape;
        }
    }

    private void pieceDropped() {

        for (int i = 0; i < 4; i++) {

            int x = cur_x + curr_pieza.x(i);
            int y = cur_y - curr_pieza.y(i);
            board[(y * BOARD_WIDTH) + x] = curr_pieza.getShape();
        }

        removeFullLines();

        if (!isFallEnd) {

            newPiece();
        }
    }

    private void newPiece() {

        curr_pieza.setRandomShape();
        //aca movemos la chingadera
        cur_x = BOARD_WIDTH / 2 + 1;
        cur_y = BOARD_HEIGHT - 1 + curr_pieza.minY();
        
        int x = theter.gameList.ret_index();
        theter.gameList.adx();
        switch(x){
            case 1:
                //natural shape
                break;
            case 2:
                Move(curr_pieza.rotateR(), cur_x, cur_y);
                break;
            case 3:
                Move(curr_pieza.rotateL(), cur_x, cur_y);
                Move(curr_pieza.rotateL(), cur_x, cur_y);
                break;
            case 4:
                //natshape
                break;
            case 5:
                Move(curr_pieza.rotateR(), cur_x, cur_y);
                break;
            case 6:
                Move(curr_pieza.rotateL(), cur_x, cur_y);
                break;
            case 7:
                Move(curr_pieza.rotateL(), cur_x, cur_y);
                Move(curr_pieza.rotateL(), cur_x, cur_y);
                break;
            case 8:
                //natshape
                break;
            case 9:
                Move(curr_pieza.rotateR(), cur_x, cur_y);
                break;
            case 10:
                Move(curr_pieza.rotateL(), cur_x, cur_y);
                break;
            case 11:
                //nada
                break;
            case 12:
                Move(curr_pieza.rotateL(), cur_x, cur_y);
                //nada
                break;
            case 13:
                //nada
                break;
            case 14:
                Move(curr_pieza.rotateL(), cur_x, cur_y);
                //nada
                break;
            case 15:
                //nada
                break;
            case 16:
                Move(curr_pieza.rotateL(), cur_x, cur_y);
                Move(curr_pieza.rotateL(), cur_x, cur_y);
                break;
            case 17:
                //nada
                break;
            case 18:
                Move(curr_pieza.rotateR(), cur_x, cur_y);
                //nada
                break;
            case 19:
                Move(curr_pieza.rotateL(), cur_x, cur_y);
                break;       
        }

        

        if (!Move(curr_pieza, cur_x, cur_y)) {

            curr_pieza.setShape(temte.NShape);
            timex.stop();

            var msg = String.format("Game over. Score: %d", lnDrop);
            status_bar.setText(msg);
        }
    }

    private boolean Move(shape newPiece, int newX, int newY) {

        for (int i = 0; i < 4; i++) {

            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);

            if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {

                return false;
            }

            if (shapeAt(x, y) != temte.NShape) {

                return false;
            }
        }

        curr_pieza = newPiece;
        cur_x = newX;
        cur_y = newY;

        repaint();

        return true;
    }

    private void removeFullLines() {

        int numFullLines = 0;

        for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {

            boolean lineIsFull = true;

            for (int j = 0; j < BOARD_WIDTH; j++) {

                if (shapeAt(j, i) == temte.NShape) {

                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {

                numFullLines++;

                for (int k = i; k < BOARD_HEIGHT - 1; k++) {
                    for (int j = 0; j < BOARD_WIDTH; j++) {
                        board[(k * BOARD_WIDTH) + j] = shapeAt(j, k + 1);
                    }
                }
            }
        }

        if (numFullLines > 0) {

            lnDrop += numFullLines;

            status_bar.setText(String.valueOf(lnDrop));
            isFallEnd = true;
            curr_pieza.setShape(temte.NShape);
        }
    }

    private void drawSquare(Graphics gx, int xx, int yy, temte shape) {

        Color colores[] = {new Color(0, 0, 0), new Color(204, 102, 102),
            new Color(102, 204, 102), new Color(102, 102, 204),
            new Color(204, 204, 102), new Color(204, 102, 204),
            new Color(102, 204, 204), new Color(218, 170, 0)
        };

        var colorix = colores[shape.ordinal()];

        gx.setColor(colorix);
        gx.fillRect(xx + 1, yy + 1, squareWidth() - 2, squareHeight() - 2);

        gx.setColor(colorix.brighter());
        gx.drawLine(xx, yy + squareHeight() - 1, xx, yy);
        gx.drawLine(xx, yy, xx + squareWidth() - 1, yy);

        gx.setColor(colorix.darker());
        gx.drawLine(xx + 1, yy + squareHeight() - 1, xx + squareWidth() - 1, yy + squareHeight() - 1);
        gx.drawLine(xx + squareWidth() - 1, yy + squareHeight() - 1, xx + squareWidth() - 1, yy + 1);
    }

    class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }

    private void doGameCycle() {
        update_board();
        //repintamos
        repaint();
    }

    private void update_board() {

        if (spause) {
            return;
        }
        if (isFallEnd) {
            isFallEnd = false;
            newPiece();
        } else {
            oneDown();
        }
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (curr_pieza.getShape() == temte.NShape) {
                return;
            }
            int keyPress = e.getKeyCode();
            // Java 12 switch expressions
            switch (keyPress) {

                case KeyEvent.VK_P ->
                    paused();
                case KeyEvent.VK_LEFT ->
                    Move(curr_pieza, cur_x - 1, cur_y);
                case KeyEvent.VK_RIGHT ->
                    Move(curr_pieza, cur_x + 1, cur_y);
                case KeyEvent.VK_DOWN ->
                    Move(curr_pieza.rotateR(), cur_x, cur_y);
                case KeyEvent.VK_UP ->
                    Move(curr_pieza.rotateL(), cur_x, cur_y);
                case KeyEvent.VK_SPACE ->
                    drop();
                case KeyEvent.VK_D ->
                    oneDown();
            }
        }
    }

}

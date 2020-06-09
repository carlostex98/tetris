package thanos;

import java.util.Random;

public class shape {

    protected enum temte{ NShape, Zshape, Sshape, LineShape,
        TShape, SquareShape, LShape, MirroredLShape }

    temte pieceShape;
    int coordenadas[][];
    int[][][] tabla_cords;


    public shape() {

        initForma();
    }

    private void initForma() {

        coordenadas = new int[4][2];

        tabla_cords = new int[][][] {
                { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
                { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
                { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },
                { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },
                { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },
                { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
                { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },
                { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
        };

        setShape(temte.NShape);
    }

    protected void setShape(temte shape) {

        for (int i = 0; i < 4 ; i++) {

            for (int j = 0; j < 2; ++j) {

                coordenadas[i][j] = tabla_cords[shape.ordinal()][i][j];
            }
        }

        pieceShape = shape;
    }

    private void set_x(int index, int x) { coordenadas[index][0] = x; }
    private void set_y(int index, int y) { coordenadas[index][1] = y; }
    public int x(int index) { return coordenadas[index][0]; }
    public int y(int index) { return coordenadas[index][1]; }
    public temte getShape()  { return pieceShape; }

    public void setRandomShape() {

        var r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        //System.out.println(x);
        x=3;
        temte[] values = temte.values();
        setShape(values[x]);
    }

    public int minX() {

        int m = coordenadas[0][0];

        for (int i=0; i < 4; i++) {

            m = Math.min(m, coordenadas[i][0]);
        }

        return m;
    }


    public int minY() {

        int m = coordenadas[0][1];

        for (int i=0; i < 4; i++) {

            m = Math.min(m, coordenadas[i][1]);
        }

        return m;
    }

    public shape rotateL() {

        if (pieceShape == temte.SquareShape) {

            return this;
        }

        var result1 = new shape();
        result1.pieceShape = pieceShape;

        for (int i = 0; i < 4; ++i) {

            result1.set_x(i, y(i));
            result1.set_y(i, -x(i));
        }

        return result1;
    }

    public shape rotateR() {

        if (pieceShape == temte.SquareShape) {
            return this;
        }
        var res = new shape();
        res.pieceShape = pieceShape;
        for (int i = 0; i < 4; ++i) {
            res.set_x(i, -y(i));
            res.set_y(i, x(i));
        }

        return res;
    }
}

package thanos;

import java.util.LinkedList;

public class listas {

    LinkedList<String[]> niveles = new LinkedList<String[]>();
    LinkedList<String[]> pice = new LinkedList<String[]>();

    int currPiece = 0;

    public int ret_index() {
        int n = 0;
        if (currPiece >= pice.size()) {//lo hace circular
            currPiece = 0;
        }
        String a = pice.get(currPiece)[0];
        String b = pice.get(currPiece)[1];
        if (a.equals("I")) {
            if (b.equals("^")) {
                n = 1;
            }
            if (b.equals("v")) {
                n = 1;
            }
            if (b.equals("<")) {
                n = 2;
            }
            if (b.equals(">")) {
                n = 2;
            }
        }
        if (a.equals("J")) {
            if (b.equals("^")) {
                n = 3;
            }
            if (b.equals("v")) {
                n = 4;
            }
            if (b.equals("<")) {
                n = 5;
            }
            if (b.equals(">")) {
                n = 6;
            }
        }
        if (a.equals("L")) {
            if (b.equals("^")) {
                n = 7;
            }
            if (b.equals("v")) {
                n = 8;
            }
            if (b.equals("<")) {
                n = 9;
            }
            if (b.equals(">")) {
                n = 10;
            }
        }
        if (a.equals("O")) {
            n = 11;
        }
        if (a.equals("S")) {
            if (b.equals("^")) {
                n = 12;
            }
            if (b.equals("v")) {
                n = 12;
            }
            if (b.equals("<")) {
                n = 13;
            }
            if (b.equals(">")) {
                n = 13;
            }
        }
        if (a.equals("Z")) {
            if (b.equals("^")) {
                n = 14;
            }
            if (b.equals("v")) {
                n = 14;
            }
            if (b.equals("<")) {
                n = 15;
            }
            if (b.equals(">")) {
                n = 15;
            }
        }
        if (a.equals("T")) {
            if (b.equals("^")) {
                n = 16;
            }
            if (b.equals("v")) {
                n = 17;
            }
            if (b.equals("<")) {
                n = 18;
            }
            if (b.equals(">")) {
                n = 19;
            }
        }
        
        return n;
    }

    public void adx(){
        currPiece++;
    }
    
    public void a_level(String a, String b, String c) {
        String[] lev = {a, b, c};//lins, cols, nombre
        niveles.add(lev);
    }

    public void a_pice(String a, String b) {
        String[] lev = {a, b};//lins, cols, nombre
        pice.add(lev);
    }

    public void cl_all() {
        niveles = null;
    }

}

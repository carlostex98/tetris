package thanos;

import java.util.LinkedList;

public class analizador {

    LinkedList<String[]> tokens = new LinkedList<>();
    LinkedList<String[]> errores = new LinkedList<>();

    LinkedList<String> piezas = new LinkedList<>();

    public void a_token(String tipo, String contenido, int linea, int columna) {
        String[] s = {Integer.toString(tokens.size()), tipo, contenido, Integer.toString(linea), Integer.toString(columna)};
        tokens.add(s);
    }

    public void a_error(String contenido, int linea, int columna) {
        String[] s = {Integer.toString(errores.size()), contenido, Integer.toString(linea), Integer.toString(columna)};
        errores.add(s);
    }

    public void reset_list() {
        tokens.clear();
        errores.clear();
    }

    public void startAnalisis(String a, String b) {
        reset_list();
        analizaTablero(a);
        analizaPiezas(b);
    }

    public void analizaTablero(String data) {
        char c = ' ';  //variables
        char v = ' ';  //var predictiva

        int caso = 0;

        int ln = 1; //linea
        int cl = 1; //columna

        String f = "";//variable de suma

        for (int i = 0; i < data.length(); i++) {
            c = data.charAt(i);
            cl++;
            if (i < data.length() - 1) {
                c = data.charAt(i + 1);
            }

            switch (caso) {
                case 0:
                    if (c == '/' && v == '/') {
                        caso = 1;
                        f = "//";

                    } else if (c == '<' && v == '!') {
                        caso = 2;
                        f = "<!";
                    } else if (Character.isLetter(c) || c == '_') {
                        caso = 3;
                        //identificador
                        f = Character.toString(c);
                    } else if (Character.isDigit(c)) {
                        //digito
                        caso = 4;
                        f = Character.toString(c);
                    } else if (c == '*' || c == '#') {
                        //nos mantenemos aqui, pero añade 
                        a_token("Elemento", Character.toString(c), ln, cl);
                    } else if (c == '\n' || c == '\n' || c == '\t') {
                        //nos mantenemos aqui salto de linea
                    } else {
                        //error
                        a_error("Elemento lexico desconocido " + Character.toString(c), ln, cl);
                    }
                    break;
                case 1:
                    if (c == '\n') {
                        //jump
                        caso = 0;
                        a_token("Comentario", f, ln, cl);
                    } else {
                        f += c;
                    }
                    break;

                case 2:
                    if (c == '!' && v == '>') {
                        //agregamos el token
                        caso = 0;
                        a_token("Comentario multilinea", f, ln, cl);
                    } else {
                        f += c;
                    }

                    break;

                case 3:
                    if (Character.isLetterOrDigit(c) || c == '_') {
                        f += c;
                    } else {
                        a_token("Indentificador", f, ln, cl);
                        caso = 0;
                    }
                    break;

                case 4:
                    if (Character.isDigit(c)) {
                        f += c;
                    } else {
                        a_token("Numero", f, ln, cl);
                        caso = 0;
                    }
                    break;

            }

            if (c == '\n') {
                ln++;
                cl = 1;
            }
        }
    }

    public void analizaPiezas(String data) {
        char c = ' ';
        int ln = 1;
        int cl = 1;
        String elementos = "IJLOSZTv^<>,";
        for (int i = 0; i < data.length(); i++) {
            cl++;
            c = data.charAt(i);
            if (elementos.contains(Character.toString(c))) {
                //ok
                piezas.add(Character.toString(c));
            } else if (c == '\n' || c == '\t' || c == ' ') {
                ln++;
                cl = 1;
            } else {
                //error
                a_error("Elemento lexico desconocido " + Character.toString(c), ln, cl);
            }
        }
    }

    public void gramarPiezas() {
        //en base a piezas
        //(letra coma orientacion)+
        String letra = "IJLOSZT";
        String orientacion = "v^<>";
        int e = 0;
        for (String pieza : piezas) {
            switch (e) {
                case 0:
                    if (letra.contains(pieza)) {
                        e = 1;
                    } else {
                        //error
                        a_error("Elemento sintactico desconocido " + pieza, 0, 0);
                    }
                    break;
                case 1:
                    if (pieza.equals(",")) {
                        e = 2;
                    } else {
                        //error
                        a_error("Elemento sintactico desconocido " + pieza, 0, 0);
                    }
                    break;
                case 2:
                    if (letra.contains(orientacion)) {
                        e = 0;
                    } else {
                        //error
                        a_error("Elemento sintactico desconocido " + pieza, 0, 0);
                    }
                    break;
            }
        }
    }

}

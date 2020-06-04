package thanos;

import java.util.LinkedList;

public class analizador {

    LinkedList<String[]> tokens = new LinkedList<String[]>();
    LinkedList<String[]> errores = new LinkedList<String[]>();

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

    public void verTablero(String data) {
        char c = ' ';  //variables
        char v = ' ';  //var predictiva

        int caso = 0;

        int ln = 1; //linea
        int cl = 1; //columna

        String f = "";//variable de suma

        for (int i = 0; i < data.length(); i++) {
            c = data.charAt(i);

            if (i < data.length() - 1) {
                c = data.charAt(i + 1);
            }

            switch (caso) {
                case 0:
                    if (c == '/' && v == '/') {
                        caso = 1;
                        f="//";
                    } else if (c == '<' && v == '!') {
                        caso = 2;
                        f="<!";
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
                    } else if (c == '\n' || c == '\n' || c == '\t') {
                        //nos mantenemos aqui salto de linea
                    } else {
                        //error 
                    }
                    break;
                case 1:
                    if (c == '\n') {
                        //jump
                        caso = 0;
                    }
                    break;

                case 2:
                    if (c == '!' && v == '>') {
                        //agregamos el token
                        caso = 0;
                    } else {
                        f += c;
                    }

                    break;

                case 3:
                    if (Character.isLetterOrDigit(c) || c == '_') {
                        f += c;
                    } else {

                        caso = 0;
                    }
                    break;

                case 4:
                    if (Character.isDigit(c)) {
                        f += c;
                    } else {

                        caso = 0;
                    }
                    break;

            }
        }
    }
}

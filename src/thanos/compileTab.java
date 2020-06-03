package thanos;

public class compileTab {

    public void verTablero(String data) {
        char c = ' ';
        char v = ' ';
        int caso = 0;
        for (int i = 0; i < data.length(); i++) {
            c = data.charAt(i);
            if (i < data.length() - 1) {
                c = data.charAt(i + 1);
            }
            switch (caso) {
                case 0:
                    if (c == '/' && v == '/') {
                        caso = 1;
                    } else if (c == '<' && v == '!') {
                        caso = 2;
                    } else if (Character.isLetter(c) || c == '_') {
                        caso = 4;
                    } else if (Character.isDigit(c)) {
                        //declara mismo caso
                        caso = 5;
                    } else if (Character.isLetter(c) || c == '_') {
                        caso = 6;
                    }
                    break;
                case 1:
                    if(c=='\n'){
                        //jump
                        caso=0;
                    }
                    break;
            }
        }
    }

}

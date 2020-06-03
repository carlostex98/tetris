
package thanos;

public class compileTab {
   public void verTablero(String data){
       char c=' ';
       char v=' ';
       int caso=0;
       for (int i = 0; i < data.length(); i++) {
           c=data.charAt(i);
           if(i<data.length()-1){
               c=data.charAt(i+1);
           }
           switch (caso){
               case 0:
                   if(c=='/'){
                       caso=1;
                   }else if(c=='<'){
                       caso=2;
                   }else if(Character.isLetter(c)||c=='_'){
                       caso=4;
                   }
                   
                   break;
           }
       }
   } 
   
}


package thanos;

import java.util.LinkedList;


public class listas {
    LinkedList <String[]> niveles=new LinkedList<String[]>();
    LinkedList <String[]> pice=new LinkedList<String[]>();
    
    int currPiece=0;
    
    public int ret_index(){
        int n=0;
        
        
        currPiece++;
        return n;
    }
    
    
    public void a_level(String a, String b, String c){
        String[] lev={a, b, c};//lins, cols, nombre
        niveles.push(lev);
    }
    
    public void cl_all(){
        niveles = null;
    }
    
}

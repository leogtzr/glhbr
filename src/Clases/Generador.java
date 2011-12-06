package Clases;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/* @author Leonardo Gutiérrez Ramírez <leogutierrezramirez.gmail.com> */
/* Dec 5, 2011 */

public class Generador {
    private int tope = 0;
    ArrayList<String> pila = null;
    String programName = null;
    public Generador(ArrayList<String> pila_2, String programName) {
        tope = 0;
        pila = null;
        pila = (ArrayList<String>)pila_2.clone();
        this.programName = programName;
    }
    
        public static boolean isOperator(String op) {
    	if(op.equals("+")) {
    		return true;
    	} else if(op.equals("-")) {
    		return true;
    	} else if(op.equals("*")) {
    		return true;
    	} else if(op.equals("/")) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public void generar() {
        
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        try {
            fichero = new FileWriter("source.asm");
            pw = new PrintWriter(fichero);
            pw.println("; Assembly code para el programa \"" + programName + "\"");
            pw.println();
            
        for(int i = 0; i < pila.size() - 1; i++) {
            
            if(isOperator(pila.get(i)) == false) {
                pw.println("mov " + ("R" + tope) + ",#" + pila.get(i) + " ; Cargamos la constante " + pila.get(i));
                tope++;
            } else {
                switch(pila.get(i).charAt(0)) {
                    case '+':
                        pw.println("add R" + (tope - 2) + ",R" + (tope - 1) + "     ; Sumamos");
                        tope--;
                        break;
                    case '-':
                        pw.println("sub R" + (tope - 2) + ",R" + (tope - 1) + "        ; Restamos");
                        tope--;
                        break;
                    case '*':
                        pw.println("mul R" + (tope - 2) + ",R" + (tope - 1) + "     ;  Multiplicamos");
                        tope--;
                        break;    
                    case '/':
                        pw.println("div R" + (tope - 2) + ",R" + (tope - 1) + "     ; Dividimos");
                        tope--;
                        break;
                }
            }
        }
        pw.println("mov " + pila.get(pila.size() - 1) + ",R0    ; fin de la sentencia");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
}

// PENDIENTE Ver si podría generar triplos o cuadruplos con el algoritmo, o quizás código p.
package Clases;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/* @author Leonardo Gutiérrez Ramírez <leogutierrezramirez.gmail.com> */
/* Dec 5, 2011 */

public class Generador {
    
    private int tope = 0;
    private ArrayList<String> pila = null;
    String programName = null;
    
    public String getProgramName() {
        return programName;
    }
    
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
                pw.println("MOV " + ("R" + tope) + ",#" + pila.get(i) + " ; Cargamos la constante " + pila.get(i));
                tope++;
            } else {
                switch(pila.get(i).charAt(0)) {
                    case '+':
                        pw.println("ADD R" + (tope - 2) + ",R" + (tope - 1) + " ; Sumamos");
                        tope--;
                        break;
                    case '-':
                        pw.println("SUB R" + (tope - 2) + ",R" + (tope - 1) + "        ; Restamos");
                        tope--;
                        break;
                    case '*':
                        pw.println("MUL R" + (tope - 2) + ",R" + (tope - 1) + "     ;  Multiplicamos");
                        tope--;
                        break;    
                    case '/':
                        pw.println("DIV R" + (tope - 2) + ",R" + (tope - 1) + "     ; Dividimos");
                        tope--;
                        break;
                }
            }
        }
        pw.println("MOV " + pila.get(pila.size() - 1) + ",R0    ; fin de la sentencia");
        pw.println("; Rutina para mostrar el resultado de " + pila.get(pila.size() - 1));
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
        
        tope = 0;
        
    }
    
    // PENDIENTE Generar código P para asignaciones.
    public void generarCodigoP() {
        
    }
    
}

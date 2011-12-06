package Clases;

import java.util.ArrayList;

/* @author Leonardo Gutiérrez Ramírez <leogutierrezramirez.gmail.com> */
/* Dec 5, 2011 */
public class Generador {
    private int tope = 0;
    ArrayList<String> pila = null;
    public Generador(ArrayList<String> pila_2) {
        tope = 0;
        pila = null;
        pila = (ArrayList<String>)pila_2.clone();
        
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
    
    // Genera código ensamblador basado en una asignación.
    public void generar() {
        //pila.get(pila.size() - 1)
        System.out.println();
        for(int i = 0; i < pila.size() - 1; i++) {
            
            if(isOperator(pila.get(i)) == false) {
                System.out.println("mov " + ("R" + tope) + ",#" + pila.get(i) + " ; Movimiento");
                tope++;
            } else {
                switch(pila.get(i).charAt(0)) {
                    case '+':
                        System.out.println("add R" + (tope - 2) + ",R" + (tope - 1) + "     ; Sumamos");
                        tope--;
                        break;
                    case '-':
                        System.out.println("sub R" + (tope - 2) + ",R" + (tope - 1) + "        ; Restamos");
                        tope--;
                        break;
                    case '*':
                        System.out.println("mul R" + (tope - 2) + ",R" + (tope - 1) + "     ;  Multiplicamos");
                        tope--;
                        break;    
                    case '/':
                        System.out.println("div R" + (tope - 2) + ",R" + (tope - 1) + "     ; Dividimos");
                        tope--;
                        break;
                }
            }
        }
        System.out.println("mov " + pila.get(pila.size() - 1) + ",R0    ; fin de la sentencia");
    }
    
}
